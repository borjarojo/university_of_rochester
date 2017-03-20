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
#include "tf/transform_datatypes.h"

using namespace std;
using namespace ros;

ofstream outfile;

GUI::
GUI( QWidget * parent ) : QGLWidget( parent), timer()
{
	linPos.resize(3, 0.0);
	angPos.resize(3, 0.0);
	linVel.resize(3, 0.0);
	angVel.resize(3, 0.0);
	mu.resize(3, 0.0);
	sigma.resize(3, 0.0);
	goal.resize(3, 0.0);
	scan.resize(640, 0.0);
	path.resize(0);
	minAng = 0.0;
	maxAng = 0.0;
	increment = 0.0;

	setMinimumSize( 600, 400);
	timer = new QTimer(this);
	connect(timer, SIGNAL( timeout() ), this, SLOT( timer_callback() ) );
	timer->start( 100); // call timer_callback at .01 Hz (period is 100ms)

	NodeHandle gui_node;
	Subscriber odom_sub = gui_node.subscribe("/odom", 1, &GUI::handle_odom,this);
	Subscriber laser_sub = gui_node.subscribe("/scan", 1, &GUI::handle_laserscan,this);
	Subscriber posewithco_sub = gui_node.subscribe("/posewithco", 1, &GUI::handle_posewithco, this);
	Subscriber pose_sub = gui_node.subscribe("/pose", 1, &GUI::handle_pose, this);
	Subscriber path_sub = gui_node.subscribe("/path", 1, &GUI::handle_path, this);
}

GUI::
~GUI () 
{

}

void
GUI::
handle_laserscan( const sensor_msgs::LaserScan::ConstPtr& msg)
{
	//implent storing of laserscan message here
	//vector<double> scan;
	minAng = msg->angle_min;
	maxAng = msg->angle_max;
	increment = msg->angle_increment;

	int bound = msg->ranges.size();
	for(int x = 0; x < bound; x++)
	{
		scan[x] = msg->ranges[x];
	}

	this->repaint();
	return;
}

void
GUI::
handle_odom( const nav_msgs::Odometry::ConstPtr& msg)
{
	// implement storing of robot pose here
	linPos[0] = msg->pose.pose.position.x;
	linPos[1] = msg->pose.pose.position.y;
	linPos[2] = msg->pose.pose.position.z;

	angPos[0] = 0.0;
	angPos[1] = 0.0;
	angPos[2] = tf::getYaw(msg->pose.pose.orientation);

	linVel[0] = msg->twist.twist.linear.x;
	linVel[1] = msg->twist.twist.linear.y;
	linVel[2] = msg->twist.twist.linear.z;

	angVel[0] = msg->twist.twist.angular.x;
	angVel[1] = msg->twist.twist.angular.y;
	angVel[2] = msg->twist.twist.angular.z;

	this->repaint();
	return;
}

void
GUI::
handle_posewithco( const geometry_msgs::PoseWithCovariance::ConstPtr& msg)
{
	mu[0] = msg->pose.position.x;
	mu[1] = msg->pose.position.y;
	mu[2] = tf::getYaw(msg->pose.orientation);

	sigma[0] = msg->covariance[0];
	sigma[1] = msg->covariance[7];
	sigma[2] = msg->covariance[14];

	this->repaint();
}

void
GUI::
handle_pose( const geometry_msgs::Pose::ConstPtr& msg)
{
	goal[0] = msg->position.x;
	goal[1] = msg->position.y;
	goal[2] = tf::getYaw(msg->orientation);
	this->repaint();
}

void
GUI::
handle_path( const nav_msgs::Path::ConstPtr& msg)
{
	//cout << "handlePath" << endl;
	vector<double> point; point.resize(2, 0.0);
	for(int x = 0; x < msg->poses.size(); x++)
	{
		point[0] = msg->poses[x].pose.position.x;
		point[1] = msg->poses[x].pose.position.y;
		path.push_back(point);
	}
	this->repaint();
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
	gluOrtho2D( -1, 9, -5, 5 ); // L R B T
	glMatrixMode( GL_MODELVIEW ); 

	//gluOrtho2D(-5,5,-5,5);
	return;
}

void
GUI::
drawCircle(double x, double y, double r, int edgeCount)
{
	glBegin(GL_LINE_LOOP);
	for(int inc = 0; inc < edgeCount; inc++)
	{
		glVertex3f(	x + r*cos(2.0 * M_PI * (double)inc/(double)edgeCount),
					y + r*sin(2.0 * M_PI * (double)inc/(double)edgeCount),
					0.0);
	}
	glEnd();


	return;
}

void
GUI::
drawLine(double x1, double y1, double x2, double y2)
{
	glBegin(GL_LINES);
	glVertex3f( x1, y1, 0.0 );
	glVertex3f( x2, y2, 0.0 );
	glEnd();
}

void
GUI::
drawPredRobot()
{
	double botRadius = 0.1;
	glColor4f(0.0, 0.0, 0.0, 1.0); // Black
	drawCircle(linPos[0], linPos[1], botRadius, 32); //Body

	glColor4f(1.0, 0.0, 0.0, 1.0); // Red
	drawLine(	linPos[0], linPos[1], 
				linPos[0] + botRadius * 4.0 * cos(angPos[2]),
				linPos[1] + botRadius * 4.0 * sin(angPos[2])); // orientation
}

void
GUI::
drawEstRobot()
{
	double botRadius = 0.1;
	glColor4f(0.0, 0.0, 1.0, 1.0); // Black
	drawCircle(mu[0], mu[1], botRadius, 32);

	glColor4f(0.0, 0.0, 1.0, 1.0); // Red
	drawLine(	mu[0], mu[1], 
				mu[0] + botRadius * 4.0 * cos(mu[2]),
				mu[1] + botRadius * 4.0 * sin(mu[2])); 
}

void
GUI::
drawBeams()
{
	glColor4f(0.0, 1.0, 0.0, 0.5); // Green
	int bound = ((maxAng - minAng) / increment);
	for(double mark = 0; mark < bound; mark++)
	{
		drawLine(	linPos[0], linPos[1], 
					linPos[0] + scan[mark] * cos(mark*increment + minAng + angPos[2]),
					linPos[1] + scan[mark] * sin(mark*increment + minAng + angPos[2]));
	}
}


void
GUI::
drawCones()
{	
	glColor4f(255.0/255.0, 114.0/255.0, 33.0/255.0, 1.0);
	drawCircle(1.0, 0.0, 0.1, 32);
	drawCircle(2.0, 0.0, 0.1, 32);
	drawCircle(3.0, 0.0, 0.1, 32);
	drawCircle(5.0, 0.0, 0.1, 32);
	drawCircle(6.0, 0.0, 0.1, 32);
	drawCircle(7.0, 0.0, 0.1, 32);
}

void
GUI::
drawGoal()
{	
	glColor4f(0.4, 0.8, 0.3, 1.0);
	drawLine(	linPos[0], linPos[1], 
				linPos[0] + goal[0], linPos[1] + goal[1]);
}

void
GUI::
drawPath()
{
	glColor4f(0.6, 0.2, 0.7, 1.0);
	if(path.size() != 0){
		for(int x = 0; x < path.size() - 1; x++)
		{
			drawLine(path[x][0], path[x][1], path[x+1][0], path[x+1][1]);
		}
	}
}

void
GUI::
paintGL()
{
	glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
	glLoadIdentity();
	// draw a coordinate system at the origin
	//glBegin( GL_LINES );
	// glColor4f( 1.0, 0.0, 0.0, 1.0 );
	// glVertex3f( 0.0, 0.0, 0.0 );
	// glVertex3f( 1.0, 0.0, 0.0 );
	// glColor4f( 0.0, 1.0, 0.0, 1.0 );
	// glVertex3f( 0.0, 0.0, 0.0 );
	// glVertex3f( 0.0, 1.0, 0.0 );
	// glColor4f( 0.0, 0.0, 1.0, 1.0 );
	// glVertex3f( 0.0, 0.0, 0.0 );
	// glVertex3f( 0.0, 0.0, 1.0 );
	// glEnd();

	drawPredRobot();
	drawEstRobot();
	drawBeams();
	drawCones();
	drawGoal();
	drawPath();
	//drawWaypoints();
	
	return;
}
