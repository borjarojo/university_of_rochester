#include "iostream"
#include "ros/ros.h"
#include "nav_msgs/Odometry.h"
#include "fstream"
#include "ros_subscriber_cmdline.h"
#include "kobuki_msgs/BumperEvent.h"

using namespace ros;
using namespace std;

ofstream outfile;

void print_odom_msg(const kobuki_msgs::BumperEvent::ConstPtr& msg)
{

	outfile << "bumper:" << endl;
	outfile << "\treleased = " << msg->RELEASED << endl;
	outfile << "\tpressed = " << msg->PRESSED << endl;

	outfile << "\tleft = " << msg->LEFT << endl;
	outfile << "\tcenter = " << msg->CENTER << endl;
	outfile << "\tright = " << msg->RIGHT << endl;
	
	outfile << "\tstate = " << msg->state << endl;
	outfile << "\tbumper = " << msg->bumper << endl;

}

int main(int argc, char * argv[])
{
	cout << "start of program" << endl;
	gengetopt_args_info args;
	cmdline_parser(argc, argv, &args);

	outfile.open(args.fileOutputName_arg);
	
	init(argc, argv, "listener");
	
	NodeHandle n;
	Subscriber sub = n.subscribe("/kobuki_msgs/BumperEvent", 1000, &print_odom_msg);
	
	spin();
	
	outfile.close();
	cout << "end of program" << endl;
}


