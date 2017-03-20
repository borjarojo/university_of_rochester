#include "iostream"
#include "fstream"
#include "ros_navigator_cmdline.h"
#include "math.h"

#include "ros/ros.h"
#include "geometry_msgs/Twist.h"	// "/cmd_vel_mux/input/navi"
#include "std_msgs/Empty.h"			// "/mobile_base/commands/reset_odometry"
#include "nav_msgs/Odometry.h"		// "/odom"
#include <unistd.h>

/* Namespaces */
using namespace std;
using namespace ros;

/*	This class was created to handle all the commands that the robot would get from
the machines. It has linear and angular velocity variables, as well as time for duration.
These variables can also be set individually.
*/
class Commander
{
	double xLinVel;
	double zAngVel;
	double time, rate;

	NodeHandle comm_node;
	Publisher twist_pub;		//Twist publisher
	Publisher empty_pub;		//Empty publisher
public:
	// Constuctors
	Commander()
	{
		xLinVel = 0.25;
		zAngVel = 0.0;
		time = 1.0;
		rate = 10.0;
		twist_pub = comm_node.advertise<geometry_msgs::Twist>("/cmd_vel_mux/input/navi", 1000 );
		empty_pub = comm_node.advertise<std_msgs::Empty>("/mobile_base/commands/reset_odometry", 1000);
	}

	Commander(double spd, double rot, double tm, double rt)
	{
		xLinVel = spd;
		zAngVel = rot;
		time = tm;
		rate = rt;
		twist_pub = comm_node.advertise<geometry_msgs::Twist>("/cmd_vel_mux/input/navi", 1000 );
		empty_pub = comm_node.advertise<std_msgs::Empty>("/mobile_base/commands/reset_odometry", 1000);
	}

	//Setters
	void setxLinVel(double x) {xLinVel = x;}
	void setzAngVel(double x) {zAngVel = x;}
	void settime(double x) {time = x;}
	void setrate(double x) {rate = x;}
	void setAll(double spd, double rot, double tm, double rt)
	{
		xLinVel = spd;
		zAngVel = rot;
		time = tm;
		rate = rt;
	}

	//Getters
	double getTime() {return time;}
	double getRate() {return rate;}

	//Publish commands
	void publishCommands()
	{
		cout << "Publishing Commands..." << endl;
		geometry_msgs::Twist msg;

		msg.linear.x = xLinVel;
		msg.angular.z = zAngVel;

		twist_pub.publish(msg);
	}

	//Publish commands
	void publishCommands(double xlv, double zav)
	{
		cout << "Publishing Commands..." << endl;
		geometry_msgs::Twist msg;

		msg.linear.x = xlv;
		msg.angular.z = zav;

		twist_pub.publish(msg);
	}

	void publishStart()
	{
		cout << "Publishing Start..." << endl;
		std_msgs::Empty msg;
		empty_pub.publish(msg);
	}
};

/*	This class will read information from the robot about its odometry and store them.

*/
class StateTracker
{	
	/*	This class keeps track of the states by indexing each value in
	its own vector. If it wants to be accessed, then it can just be printed
	in sequence. Each vector is appeneded together, so the should be the
	same length. This huristic will allow the printing of the states to be
	rather straight forward
	*/
	vector<double> xLinPos, yLinPos;
	vector<double> zAngPos;

	//Stored as yaw
	vector<double> xLinVel;
	vector<double> zAngVel;

	NodeHandle state_node;
	Subscriber odom_sub;		//Odometry subscriber

	ofstream outfile;
public:
	//Constructor
	StateTracker()
	{
		odom_sub = state_node.subscribe("/odom", 1000, &StateTracker::addState,this);
	}

	//Quaternion to yaw
	double qToYaw(const vector<double> &q)
	{
		//double yaw = atan2(2.0 * q[2] * q[3], 1.0 - 2.0 * pow(q[2], 2));
		double yaw = acos(2 * (pow(q[3], 2) + pow(q[0], 2)) - 1);
		return yaw;
	}

	//Custom appends
	void addState(double xLinP, double yLinP, double zAngP, double xLinV, double zAngV)
	{
		xLinPos.push_back(xLinP);
		yLinPos.push_back(yLinP);
		zAngPos.push_back(zAngP);
		xLinVel.push_back(xLinV);
		zAngVel.push_back(zAngV);
	}

	//Callback
	void addState(const nav_msgs::Odometry::ConstPtr& msg)	//Used as a callback function to auto update
	{
		cout << "Pushing..." << endl;
		xLinPos.push_back(msg->pose.pose.position.x);
		yLinPos.push_back(msg->pose.pose.position.y);

		//yaw conversion
		vector<double> q;
		q.push_back(msg->pose.pose.orientation.x);
		q.push_back(msg->pose.pose.orientation.y);
		q.push_back(msg->pose.pose.orientation.z);
		q.push_back(msg->pose.pose.orientation.w);
		cout << "x = " << q[0] << " y = " << q[1] << " z = " << q[2] << " w = " << q[3] << endl;
		zAngPos.push_back(qToYaw(q)); //Auto converts to yaw

		xLinVel.push_back(msg->twist.twist.linear.x);
		zAngVel.push_back(msg->twist.twist.angular.z);
	}

	void printStates()
	{
		int size = xLinPos.size();
		int inc = 0;
		cout << "x\ty\tyaw\tv\tw" << endl;	//Header
		while(inc < size)
		{
			//lines 
			cout << xLinPos[inc] << "\t" << yLinPos[inc] << "\t" << zAngPos[inc] << "\t" << xLinVel[inc] << "\t" << zAngVel[inc] << endl;
		inc++;
		}
	}

	void writeStates(char fileName[])
	{
		cout << "Writing file...";
		outfile.open(fileName);

		int size = xLinPos.size();
		int inc = 0;
		outfile << "x\ty\tyaw\tv\tw" << endl;	//Header
		while(inc < size)
		{
			//lines 
			outfile << xLinPos[inc] << "\t" << yLinPos[inc] << "\t" << zAngPos[inc] << "\t" << xLinVel[inc] << "\t" << zAngVel[inc] << endl;
			//fprintf(&outfile, "%.4f\t%.4f\t%.4f\t%.4f\t%.4f", xLinPos[inc], yLinPos[inc], zAngPos[inc], xLinVel[inc], zAngVel[inc]);
	

			inc++;
		}

		cout << "Done" << endl;
		outfile.close();
	}

	
};

int main(int argc, char *argv[])
{	
	//Start
	cout << "Start of program" << endl;

	//GenGetOpt Command line parsing
	gengetopt_args_info args;
	cmdline_parser(argc, argv, &args);
	init( argc, argv, "navigator" );

	//Instantiate my handling objects using commandline variables
	Commander bot;
	bot.setAll(
		args.linearVelocity_arg,
		args.angularVelocity_arg,
		args.time_arg,
		args.rate_arg); 	//Set the contructor arguments to those from GenGetOpt
	StateTracker state;
	Rate loopRate(bot.getRate());

	//Lab specific states
	int set = args.set_arg;
	switch (set)
	{
		case 1: bot.setAll(.25, 0, 4, 10); break;
		case 2: bot.setAll(.25, 1.0, 4, 10); break;
		case 3: bot.setAll(.25, -1.0, 4, 10); break;
		case 4: bot.setAll(.25, 0.0, 10, 10); break;
		case 5: bot.setAll(0.0, .25, 10, 10); break;
		default: bot.setAll(0, 0, 1, 1); break;
	}



	/*	This is a skeleton for running commands:
	First, some variables are set up to account for the robots frames.
		1. An incrementor
		2. A count, gotten by multiplying the rate with the time
		3. Clear the odometry of the robot
	Then, the robot is run
		1. Spin
		2. Publish commands
		3. Sleep for the rest of the cycle duration (Rate object created earlier)
	*/
	double inc = 0;
	double count = bot.getTime() * bot.getRate();

	spinOnce();
	loopRate.sleep();
	bot.publishStart();				//Prime the odometry in the simulator of the robot
	
	while(ok() && (inc++ < count))
	{	
		switch(set)
		{
			case 1: bot.publishCommands(); break;
			case 2: bot.publishCommands(); break;
			case 3: bot.publishCommands(); break;
			case 4: bot.publishCommands(.25*sin((double)inc/(double)bot.getRate()), 0.0); break;
			case 5: bot.publishCommands(0.0, sin((double)inc/(double)bot.getRate())); break;
			default: bot.publishCommands(); break;
		}
		
		spinOnce();
		loopRate.sleep();
	}

	//When the robot is done commanding, write the states aquired
	state.writeStates(args.fileOutputName_arg);

	//End
	cout << "End of program" << endl;
	return 0;
}

