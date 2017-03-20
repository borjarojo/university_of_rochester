#include "iostream"
#include "ros/ros.h"
#include "geometry_msgs/Twist.h"
#include "ros_publisher_cmdline.h"
#include "math.h"
using namespace ros;
using namespace std;

int main(int argc, char * argv[])
{
	cout << "start of program" << endl;

	gengetopt_args_info args;
	cmdline_parser(argc, argv, &args);

	init( argc, argv, "talker" );

	double linVel = args.linearVelocity_arg;
	double angVel = args.angularVelocity_arg*sin(0);
	double time = args.time_arg;	

	cout << "l = " << linVel << ", a = " << angVel << ", t = " << time << endl;

	NodeHandle n;
	Publisher pub = n.advertise<geometry_msgs::Twist>("/cmd_vel_mux/input/navi", 1000 );
		
	geometry_msgs::Twist msg;
	msg.linear.x = linVel;
	msg.angular.z = angVel;
	
	int rate = 10;
	Rate loop_rate(rate);

	int max_count = time * rate;
	int count = 0;
	while( ok() && (count++ < max_count))
	{	
		spinOnce();
		pub.publish(msg);
		angVel = args.angularVelocity_arg*sin(count/rate);
		msg.angular.z = angVel;
		loop_rate.sleep();
	}
	

	cout << "end of program" << endl;
	
	return 0;
}
