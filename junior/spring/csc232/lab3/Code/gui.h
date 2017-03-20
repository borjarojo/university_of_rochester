#include <iostream>
#include "ros/ros.h"
#include "nav_msgs/Odometry.h"
#include "sensor_msgs/LaserScan.h"
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

	vector< vector<double> > odoms;
	vector< vector<double> > scans;
	vector<double> minAng, maxAng, increment;
public:
	GUI( QWidget * parent = NULL );
	virtual ~GUI();
		void handle_laserscan( const sensor_msgs::LaserScan::ConstPtr& msg );
		void handle_odom( const nav_msgs::Odometry::ConstPtr& msg );

		QTimer* timer;
	vector< vector<double> > getScans(){return scans;}

protected slots:
	void timer_callback( void );

protected:
	virtual void initializeGL();
	virtual void paintGL();
};
