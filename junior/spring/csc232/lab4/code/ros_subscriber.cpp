#include "iostream"
#include "ros/ros.h"
#include "nav_msgs/Odometry.h"
#include "fstream"
#include "ros_subscriber_cmdline.h"
#include "kobuki_msgs/BumperEvent.h"
using namespace ros;
using namespace std;

ofstream outfile;

void print_odom_msg(const nav_msgs::Odometry::ConstPtr& msg)
{

	outfile << "posistion:" << endl;
	outfile << "\tx = " << msg->pose.pose.position.x << endl;
	outfile << "\ty = " << msg->pose.pose.position.y << endl;
	outfile << "\tyaw = " << msg->pose.pose.orientation.z << endl;
	
	outfile << "twist:" << endl;
	outfile << "\tlin = " << msg->twist.twist.linear.x << endl;
	outfile << "\tang = " << msg->twist.twist.angular.z << endl;

}

int main(int argc, char * argv[])
{
	cout << "start of program" << endl;
	gengetopt_args_info args;
	cmdline_parser(argc, argv, &args);

	outfile.open(args.fileOutputName_arg);
	
	init(argc, argv, "listener");
	
	NodeHandle n;
	Subscriber sub = n.subscribe("/mobile_base/events/bumper", 1000, &print_odom_msg);
	
	spin();
	
	outfile.close();
	cout << "end of program" << endl;
}


