/* Includes */
#include "iostream"
#include "ros_simulator_cmdline.h"
#include "cstdlib"
#include "math.h"

#include "ros/ros.h"
#include "geometry_msgs/Twist.h"	// "/cmd_vel_mux/input/navi"
#include "std_msgs/Empty.h"			// "/mobile_base/commands/reset_odometry"
#include "nav_msgs/Odometry.h"		// "/odom"

/* Namespaces */
using namespace std;
using namespace ros;

/* Classes */

/*	This class handles publishing and subscribing to the navigator about the state it in
and holds the values which represent the state.
*/
class Odometry
{
		double xLinPos, yLinPos;
		double zAngPos;

		//Stored as euler angles
		double xLinVel;
		double zAngVel;

		//time variables
		Time t;		//as a time class
		double dt;	//in seconds

		vector<double> a;

		NodeHandle odom_node;
		Publisher odom_pub;
		Subscriber twist_sub;
		Subscriber empty_sub;
	public:
		// Constructors
		Odometry()
		{
			xLinPos = 0.0, yLinPos = 0.0;
			zAngPos = 0.0;

			//Stored as euler angles
			xLinVel = 0.0;
			zAngVel = 0.0;

			t = Time::now();
			
			odom_pub = odom_node.advertise<nav_msgs::Odometry>("/odom", 1000 );
			twist_sub = odom_node.subscribe("/cmd_vel_mux/input/navi", 1000, &Odometry::twist_callback,this);
			empty_sub = odom_node.subscribe("/mobile_base/commands/reset_odometry", 1000, &Odometry::empty_callback,this);
		}

		Odometry(vector<double> v)
		{
			cout << "Creating Odometry object ...";
			xLinPos = 0.0, yLinPos = 0.0;
			zAngPos = 0.0;

			//Stored as euler angles
			xLinVel = 0.0;
			zAngVel = 0.0;

			a = v;
			

			odom_pub = odom_node.advertise<nav_msgs::Odometry>("/odom", 1000 );
			twist_sub = odom_node.subscribe("/cmd_vel_mux/input/navi", 1000, &Odometry::twist_callback,this);
			empty_sub = odom_node.subscribe("/mobile_base/commands/reset_odometry", 1000, &Odometry::empty_callback,this);
			
			cout << "Done" << endl;
		}

		//Functions
		void publishOdom(){
			nav_msgs::Odometry msg;					//Create Message
			vector<double> q = yawToQ();
			
			//Set Parameters
			//Pose
			msg.pose.pose.position.x = xLinPos;		//LinPos
			msg.pose.pose.position.y = yLinPos;
			msg.pose.pose.position.z = 0.0;

			msg.pose.pose.orientation.x = q[0];		//AngPos as quaternion
			msg.pose.pose.orientation.y = q[1];
			msg.pose.pose.orientation.z = q[2];
			msg.pose.pose.orientation.w = q[3];

			//Twist
			msg.twist.twist.linear.x = xLinVel;		//Lin
			msg.twist.twist.linear.y = 0.0;
			msg.twist.twist.linear.z = 0.0;

			msg.twist.twist.angular.x = 0.0;		//Ang
			msg.twist.twist.angular.y = 0.0;
			msg.twist.twist.angular.z = zAngVel;

			odom_pub.publish(msg);
			spinOnce();
		}

		//Setters
		void setdt(double d)
		{
			dt = d;
		}

		void seta(vector<double> v)
		{
			a = v;
		}

		//Callbacks

		//Callback that gets Twist information to update current position
		//Evaluates the probabilistic velocity motion model
		void twist_callback(const geometry_msgs::Twist::ConstPtr& msgs)
		{
			//Algorithm for updating current state
			updatedt();		//Set the new time difference

			if(zAngVel == 0.0){			//This section accounts for no angular velocity (very unlikely)
				xLinVel = msgs->linear.x + noise(a[0]*xLinVel + a[1]*zAngVel);
				zAngVel = msgs->angular.z + noise(a[2]*xLinVel + a[3]*zAngVel);

				xLinPos += xLinVel * dt * sin(zAngPos);
				yLinPos += -xLinVel * dt * cos(zAngPos);
				zAngPos += noise(a[4]*xLinVel + a[5]*zAngVel) * dt;
			}
			else						//This section implements the algorithm on table 5.3
			{
				double vw = xLinVel / zAngVel;
				// Set read linear and angular velocities
				xLinVel = msgs->linear.x + noise(a[0]*xLinVel + a[1]*zAngVel);
				zAngVel = msgs->angular.z + noise(a[2]*xLinVel + a[3]*zAngVel);

				

				xLinPos += -1.0*vw*sin(zAngPos) + vw*sin(zAngPos + zAngVel * dt);
				yLinPos += vw*cos(zAngPos) - vw*cos(zAngPos + zAngVel * dt);
				zAngPos += zAngVel * dt + noise(a[4]*xLinVel + a[5]*zAngVel) * dt;
			}
		}

		void empty_callback(const std_msgs::Empty::ConstPtr& msgs)
		{
			cout << "Reset" << endl;
			xLinPos = 0.0, yLinPos = 0.0;
			zAngPos = 0.0;

			//Stored as euler angles
			xLinVel = 0.0;
			zAngVel = 0.0;

			t = Time::now();
		}

		//Sample from a normal distribution
		//Returns a double that simulates noise. 12 cycles, bounded between -b and b
		double noise(double b)
		{
			double noise = 0.0;	//Instantiate

			int x = 0;
			while(x++ < 12)		//12 cycles
			{
				//Sample from -1 to 1
				noise += (((double)rand() / RAND_MAX) * 2) - 1;
			}
			noise /= 2.0;	//Divide by 2 as shown in lab
			noise *= b;		//Scale to b

			return noise;
		}

		//Yaw to quaternion conversion
		vector<double> yawToQ()
		{	
			/*	Simplified expressions taken from the rotaion matrix
			assuming only a yaw. 
			*/
			vector<double> Q;
			Q.push_back(0.0);		
			Q.push_back(0.0);
			Q.push_back(sgn(2.0 * sin(zAngPos) * sqrt(2.0 - 2.0 * cos(zAngPos))));
			Q.push_back((1.0 / 2.0) * sqrt(2.0 + 2.0 * cos(zAngPos)));

			return Q;
		}

		//Custom sign functions
		int sgn(int num)
		{
			if(num < 0) return -1;
			return 1;
		}

		int sgn(double num)
		{
			if(num < 0.0) return -1;
			return 1;
		}

		void updatedt(){
			Time s = Time::now();
			dt = s.toSec() - t.toSec();
			t = s;
		}

};

/* Main */
int main(int argc, char *argv[])
{
	cout << "Start of Program" << endl;

	gengetopt_args_info args;
	cmdline_parser(argc, argv, &args);
	init( argc, argv, "simulator" );

	//Vector that holds coefficients for zero-mean normal distrobutions
	vector<double> a;
		a.push_back(0.01);
		a.push_back(0.01);
		a.push_back(0.1);
		a.push_back(0.1);
		a.push_back(0.01);
		a.push_back(0.01);
	vector<double> b;
		b.push_back(0.1);
		b.push_back(0.1);
		b.push_back(0.1);
		b.push_back(0.1);
		b.push_back(0.01);
		b.push_back(0.01);

	Odometry odom;
	odom.seta(a);
	
	Rate loop_rate(100);
	while(ok())
	{
		odom.publishOdom();
		spinOnce();
		loop_rate.sleep();
	}

	cout << "End of Program" << endl;

	return 0;
}
