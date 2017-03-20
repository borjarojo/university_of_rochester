#include <QtGui/QApplication>
#include "gui.h"
#include "gui_process_cmdline.h"
#include <fstream>

using namespace std;
int
main( int argc,
		char * argv[] ){
	gengetopt_args_info args;
	cmdline_parser( argc, argv, &args );
	QApplication app( argc, argv );
	ros::init( argc, argv, "gui" );
	ros::NodeHandle node_handle;
	GUI gui;
	ros::Subscriber subscriber_reset_odometry = node_handle.subscribe( "/scan", 1, &GUI::handle_laserscan, &gui );
	ros::Subscriber subscriber_odom = node_handle.subscribe( "/odom", 1, &GUI::handle_odom, &gui );
	ros::Subscriber subscriber_posewithco = node_handle.subscribe( "/posewithco", 1, &GUI::handle_posewithco, &gui );
	ros::Subscriber subscriber_pose = node_handle.subscribe("/pose", 1, &GUI::handle_pose, &gui );
	ros::Subscriber subscriber_path = node_handle.subscribe("/path", 1, &GUI::handle_path, &gui );
	gui.show();
	
	return app.exec();
}
