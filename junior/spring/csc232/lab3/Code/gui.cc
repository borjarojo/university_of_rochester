#include "gui.h"
#include "iostream"
#include "cstdlib"
#include "math.h"
#include <fstream>

#include "ros/ros.h"
#include "geometry_msgs/Twist.h"	// "/cmd_vel_mux/input/navi"
#include "std_msgs/Empty.h"			// "/mobile_base/commands/reset_odometry"
#include "nav_msgs/Odometry.h"		// "/odom"
#include "sensor_msgs/LaserScan.h"

using namespace std;
using namespace ros;

ofstream outfile;

GUI::
GUI( QWidget * parent ) : QGLWidget( parent), timer()
{
	setMinimumSize( 1000, 1000);
	timer = new QTimer(this);
	connect(timer, SIGNAL( timeout() ), this, SLOT( timer_callback() ) );
	timer->start( 100); // call timer_callback at .01 Hz (period is 100ms)
}

GUI::
~GUI () 
{
	NodeHandle gui_node;
	Subscriber odom_sub = gui_node.subscribe("/odom", 1000, &GUI::handle_odom,this);
	Subscriber laser_sub = gui_node.subscribe("/scan", 1000, &GUI::handle_laserscan,this);
}

void
GUI::
handle_laserscan( const sensor_msgs::LaserScan::ConstPtr& msg)
{
	//implent storing of laserscan message here
	vector<double> scan;
	minAng.push_back(msg->angle_min);
	maxAng.push_back(msg->angle_max);
	increment.push_back(msg->angle_increment);
	for(int x = 0; x < msg->ranges.size(); x++)
	{
		scan.push_back(msg->ranges[x]);
	}
	scans.push_back(scan);

	outfile.open("lab3data.txt", std::ofstream::app);
		for(int y = 0; y < scan.size(); y++)
		{
			outfile << scan.at(y) << ",";
		}
		outfile << endl;
	outfile.close();
	
	return;
}

void
GUI::
handle_odom( const nav_msgs::Odometry::ConstPtr& msg)
{
	// implement storing of robot pose here
	//create Vector
	vector<double> odom;

	//get all Odometry in order
	odom.push_back(msg->pose.pose.position.x);		//0
	odom.push_back(msg->pose.pose.position.y);		//1
	odom.push_back(msg->pose.pose.position.z);		//2

	odom.push_back(msg->pose.pose.orientation.x);		//3
	odom.push_back(msg->pose.pose.orientation.y);		//4
	odom.push_back(msg->pose.pose.orientation.z);		//5
	odom.push_back(msg->pose.pose.orientation.w);		//6

	odom.push_back(msg->twist.twist.linear.x);		//7
	odom.push_back(msg->twist.twist.linear.y);		//8
	odom.push_back(msg->twist.twist.linear.z);		//9

	odom.push_back(msg->twist.twist.angular.x);		//10
	odom.push_back(msg->twist.twist.angular.y);		//11
	odom.push_back(msg->twist.twist.angular.z);		//12

	//save msg
	odoms.push_back(odom);

	return;
}

void
GUI::
timer_callback( void )
{
	ros::spinOnce();
	updateGL();
	return;
}

void
GUI::
initializeGL()
{
	glClearColor( 1.0, 1.0, 1.0, 1.0);

	glMatrixMode( GL_PROJECTION );
	gluOrtho2D( -5, 5, -5, 5 );
	glMatrixMode( GL_MODELVIEW ); 

	//gluOrtho2D(-5,5,-5,5);
	return;
}

void
GUI::
paintGL()
{
	glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
	glLoadIdentity();
	// draw a coordinate system at the origin
	glBegin( GL_LINES );
	glColor4f( 1.0, 0.0, 0.0, 1.0 );
	glVertex3f( 0.0, 0.0, 0.0 );
	glVertex3f( 1.0, 0.0, 0.0 );
	glColor4f( 0.0, 1.0, 0.0, 1.0 );
	glVertex3f( 0.0, 0.0, 0.0 );
	glVertex3f( 0.0, 1.0, 0.0 );
	glColor4f( 0.0, 0.0, 1.0, 1.0 );
	glVertex3f( 0.0, 0.0, 0.0 );
	glVertex3f( 0.0, 0.0, 1.0 );

	for(int x = 0; x < scans.size(); x++)
	{
		double botangle = odoms.at(x).at(5);
		double scannerangle = minAng.at(x);

		for(int y = 0; y < scans.at(x).size(); y++)
		{
			double dist = scans.at(x).at(y);
			scannerangle += increment.at(x);
			//Draw
			glColor4f( 0.0, x*.01, 0.0, 1.0 );
			glVertex3f( odoms.at(x).at(0),	//x, y, z
						odoms.at(x).at(1), 0.0 );
			//x + run, y + rise, z
			glVertex3f( odoms.at(x).at(0) + dist*cos(scannerangle), 
						odoms.at(x).at(1) + dist*sin(scannerangle), 0.0 );
		}
	}
	glEnd();
	// implement drawing of laserscan and robot posee here
	return;
}
