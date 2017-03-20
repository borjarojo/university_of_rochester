#include "iostream"
#include "fstream"
#include "ros_navigator_cmdline.h"
#include "math.h"

#include "ros/ros.h"
#include "geometry_msgs/Twist.h"	// "/cmd_vel_mux/input/navi"
#include "std_msgs/Empty.h"			// "/mobile_base/commands/reset_odometry"
#include "nav_msgs/Odometry.h"		// "/odom"
#include "geometry_msgs/Pose.h"
#include <unistd.h>
#include "tf/transform_datatypes.h"

/* Namespaces */
using namespace std;
using namespace ros;

/*	This class was created to handle all the commands that the robot would get from
the machines. It has linear and angular velocity variables, as well as time for duration.
These variables can also be set individually.
*/
class Commander
{
	vector<double> linVel, angVel;
	double time, rate;

	NodeHandle comm_node;
	Publisher twist_pub;		//Twist publisher
	Publisher empty_pub;		//Empty publisher
	Subscriber pose_sub;

	Time t;
	double dt;

	vector<double> goal;

	
public:
	// Constuctors
	Commander()
	{
		linVel.resize(3, 0.0); linVel[0] = 0.0;
		angVel.resize(3, 0.0); angVel[2] = 0.0;
		time = 10;
		rate = 0.1;

		dt = 0.0;
		t = Time::now();

		goal.resize(3, 0.0);

		twist_pub = comm_node.advertise<geometry_msgs::Twist>("/cmd_vel_mux/input/navi", 1 );
		empty_pub = comm_node.advertise<std_msgs::Empty>("/mobile_base/commands/reset_odometry", 1, true);
		pose_sub = comm_node.subscribe("/pose", 1, &Commander::handle_pose, this);
	}

	Commander(double spd, double rot, double tm, double rt)
	{
		linVel.resize(3, 0.0); linVel[0] = spd;
		angVel.resize(3, 0.0); angVel[2] = rot;
		time = tm;
		rate = rt;
		twist_pub = comm_node.advertise<geometry_msgs::Twist>("/cmd_vel_mux/input/navi", 1000 );
		empty_pub = comm_node.advertise<std_msgs::Empty>("/mobile_base/commands/reset_odometry", 1, true);
	}

	//Setters
	void setxLinVel(double x) {linVel[0] = x;}
	void setzAngVel(double x) {angVel[2] = x;}
	void settime(double x) {time = x;}
	void setrate(double x) {rate = x;}
	void setAll(double spd, double rot, double tm, double rt)
	{
		linVel.resize(3, 0.0); linVel[0] = spd;
		angVel.resize(3, 0.0); angVel[2] = rot;
		time = tm;
		rate = rt;
	}

	//Getters
	double getTime() {return time;}
	double getRate() {return rate;}
	double getdt() {return dt;}

	void handle_pose( const geometry_msgs::Pose::ConstPtr& msg)
	{
		cout << "--handle_pose--" << endl;
		goal[0] = msg->position.x;
		goal[1] = msg->position.y;
		goal[2] = tf::getYaw(msg->orientation);
	}
	//Publish commands
	void publishCommands()
	{
		cout << "--publishCommands" << endl;
		geometry_msgs::Twist msg;

		msg.linear.x = linVel[0];
		msg.linear.y = linVel[1];
		msg.linear.z = linVel[2];

		msg.angular.x = angVel[0];
		msg.angular.y = angVel[1];
		msg.angular.z = angVel[2];

		cout << "Lin: " << msg.linear.x << "Ang :" << msg.angular.z << endl;

		twist_pub.publish(msg);
	}

	void publishStart()
	{
		std_msgs::Empty empty_msg;
		empty_pub.publish(empty_msg);
	}

	void updatedt()
	{	
		Time s = Time::now();
		if(dt == 0.0)
		{
			dt = s.toSec() - t.toSec();
			t = s;
		}
		dt = s.toSec() - t.toSec();
		t = s;
	}

	void updateCommands()
	{
		//cout << "--updateCommands--" << endl;
		linVel[0] = 0.1;
		angVel[2] = linVel[0] * ( (2 * goal[1]) / (goal[0]*goal[0] + goal[1]*goal[1]));
		
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
	vector< vector<double> > linPos, angPos, linVel, angVel;

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
	// double qToYaw(const vector<double> &q)
	// {
	// 	//double yaw = atan2(2.0 * q[2] * q[3], 1.0 - 2.0 * pow(q[2], 2));
	// 	double yaw = acos(2 * (pow(q[3], 2) + pow(q[0], 2)) - 1);
	// 	return yaw;
	// }

	//Custom appends
	// void addState(double xLinP, double yLinP, double zAngP, double xLinV, double zAngV)
	// {
	// 	xLinPos.push_back(xLinP);
	// 	yLinPos.push_back(yLinP);
	// 	zAngPos.push_back(zAngP);
	// 	xLinVel.push_back(xLinV);
	// 	zAngVel.push_back(zAngV);
	// }

	//Callback
	void addState(const nav_msgs::Odometry::ConstPtr& msg)	//Used as a callback function to auto update
	{
		vector<double> lp, ap, lv, av;

		lp.push_back(msg->pose.pose.position.x);
		lp.push_back(msg->pose.pose.position.y);
		lp.push_back(msg->pose.pose.position.z);

		//yaw conversion
		ap.push_back(0.0);
		ap.push_back(0.0);
		ap.push_back(tf::getYaw(msg->pose.pose.orientation));
		
		lv.push_back(msg->twist.twist.linear.x);
		lv.push_back(msg->twist.twist.linear.y);
		lv.push_back(msg->twist.twist.linear.z);

		av.push_back(msg->twist.twist.angular.x);
		av.push_back(msg->twist.twist.angular.y);
		av.push_back(msg->twist.twist.angular.z);

		linPos.push_back(lp);
		angPos.push_back(ap);
		linVel.push_back(lv);
		angVel.push_back(av);
	}

	void printStates()
	{
		/*
			linPos.x
			linpos.y
			linpos.z
			angPos.x
			angpos.y
			angpos.z
			linVel.x
			linVel.y
			linVel.z
			angVel.x
			angVel.y
			angVel.z

		*/
		for(int x = 0; x < linPos.size(); x++)
		{
			cout << "linPos" << endl;
			for(int y = 0; y < linPos[x].size(); y++)
			{
				cout << linPos[x][y] << endl;
			}
			cout << "angPos" << endl;
			for(int y = 0; y < angPos[x].size(); y++)
			{
				cout << angPos[x][y] << endl;
			}
			cout << "linVel" << endl;
			for(int y = 0; y < linVel[x].size(); y++)
			{
				cout << linVel[x][y] << endl;
			}
			cout << "angVel" << endl;
			for(int y = 0; y < angVel[x].size(); y++)
			{
				cout << angVel[x][y] << endl;
			}
			cout << "-" << endl;
		}
	}

	void writeStates(char fileName[])
	{
		cout << "Writing file...";
		outfile.open(fileName);

		for(int x = 0; x < linPos.size(); x++)
		{
			outfile << "linPos" << endl;
			for(int y = 0; y < linPos[x].size(); y++)
			{
				outfile << linPos[x][y] << endl;
			}
			outfile << "angPos" << endl;
			for(int y = 0; y < angPos[x].size(); y++)
			{
				outfile << angPos[x][y] << endl;
			}
			outfile << "linVel" << endl;
			for(int y = 0; y < linVel[x].size(); y++)
			{
				outfile << linVel[x][y] << endl;
			}
			outfile << "angVel" << endl;
			for(int y = 0; y < angVel[x].size(); y++)
			{
				outfile << angVel[x][y] << endl;
			}
			outfile << "-" << endl;
		}
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
	// bot.setAll(
	// 	args.linearVelocity_arg,
	// 	args.angularVelocity_arg,
	// 	args.time_arg,
	// 	args.rate_arg); 	//Set the contructor arguments to those from GenGetOpt
	StateTracker state;
	

	//Lab specific states
	// int set = args.set_arg;
	// switch (set)
	// {
	// 	case 1: bot.setAll(.25, 0, 4, 10); break;
	// 	case 2: bot.setAll(.25, 1.0, 4, 10); break;
	// 	case 3: bot.setAll(.25, -1.0, 4, 10); break;
	// 	case 4: bot.setAll(.25, 0.0, 10, 10); break;
	// 	case 5: bot.setAll(0.0, .25, 10, 10); break;
	// 	default: bot.setAll(0, 0, 1, 1); break;
	// }



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
	bot.publishStart();				//Prime the odometry in the simulator of the robot
	bot.setxLinVel(0.0);
	bot.setzAngVel(0.0);
	bot.publishCommands();
	spinOnce();

	Rate loop_rate(60);
	while(ok())
	{	
		// switch(set)
		// {
		// 	case 1: bot.publishCommands(); break;
		// 	case 2: bot.publishCommands(); break;
		// 	case 3: bot.publishCommands(); break;
		// 	case 4: bot.publishCommands(.25*sin((double)inc/(double)bot.getRate()), 0.0); break;
		// 	case 5: bot.publishCommands(0.0, sin((double)inc/(double)bot.getRate())); break;
		// 	default: bot.publishCommands(); break;
		// }
		cout << "spin" << endl;
		bot.updateCommands();
		bot.publishCommands();
		spinOnce();
		loop_rate.sleep();
	}

	bot.setxLinVel(0.0);
	bot.setzAngVel(0.0);
	bot.publishCommands();
	spinOnce();
	//When the robot is done commanding, write the states aquired
	state.writeStates(args.fileOutputName_arg);

	//End
	cout << "End of program" << endl;
	return 0;
}

