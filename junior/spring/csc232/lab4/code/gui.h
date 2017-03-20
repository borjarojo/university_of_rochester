#include <iostream>
#include "ros/ros.h"
#include "nav_msgs/Odometry.h"
#include "sensor_msgs/LaserScan.h"
#include "nav_msgs/Path.h"
#include <QtGui/QApplication>
#include <QtGui/QWidget>
#include <QtOpenGL/QGLWidget>
#include <QtCore>
#include <QTimer>
#include <GL/glu.h>

using namespace std;
using namespace ros;

class GUI: public QGLWidget {
	Q_OBJECT

	vector<double> linPos, angPos, linVel, angVel;
	vector<double> mu, sigma;
	vector<double> scan;
	vector<double> goal;
	vector< vector<double> > path;
	double minAng, maxAng, increment;

	Subscriber odom_sub, laser_sub, posewithco_sub, pose_sub, path_sub;
public:
	GUI( QWidget * parent = NULL );
	virtual ~GUI();
	void handle_laserscan( const sensor_msgs::LaserScan::ConstPtr& msg );
	void handle_odom( const nav_msgs::Odometry::ConstPtr& msg );
	void handle_posewithco( const geometry_msgs::PoseWithCovariance::ConstPtr& msg);
	void handle_pose(const geometry_msgs::Pose::ConstPtr& msg);
	void handle_path(const nav_msgs::Path::ConstPtr& msg);
	void drawCircle(double x, double y, double r, int edgeCount);
	void drawLine(double x1, double y1, double x2, double y2);
	void drawPredRobot();
	void drawEstRobot();
	void drawBeams();
	void drawCones();
	void drawGoal();
	void drawPath();

	QTimer* timer;

protected slots:
	void timer_callback( void );

protected:
	virtual void initializeGL();
	virtual void paintGL();
};
