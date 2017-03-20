/* Includes */
#include "iostream"
#include "ros_simulator_cmdline.h"
#include "cstdlib"
#include "math.h"
#include <vector>

#include "ros/ros.h"
#include "geometry_msgs/Twist.h"	// "/cmd_vel_mux/input/navi"
#include "std_msgs/Empty.h"			// "/mobile_base/commands/reset_odometry"
#include "nav_msgs/Odometry.h"		// "/odom"
#include "sensor_msgs/LaserScan.h"
#include "tf/transform_datatypes.h"

/* Namespaces */
using namespace std;
using namespace ros;

double myatan(double, double);

/* Classes */

/*	This class handles publishing and subscribing to the navigator about the state it in
and holds the values which represent the state.
*/
class Odometry
{
	vector<double> linPos, angPos, linVel, angVel;
	vector<double> a;

	//time variables
	Time t;		//as a time class
	double dt;	//in seconds
	int firstSub;

	//For Subscribing
	NodeHandle odom_node;
	NodeHandle empty_node;

	Publisher odom_pub;

	Subscriber twist_sub;
	Subscriber empty_sub;

	//For Publishing
	nav_msgs::Odometry odom_msg;
public:
	// Constructors
	Odometry()
	{
		linPos.resize(3, 0.0);
		angPos.resize(3, 0.0);
		linVel.resize(3, 0.0);
		angVel.resize(3, 0.0);

		t = Time::now();
		dt = 0.0;
		firstSub = 0;
		
		odom_pub = odom_node.advertise<nav_msgs::Odometry>("/odom", 1000 );
		twist_sub = odom_node.subscribe("/cmd_vel_mux/input/navi", 1, &Odometry::twist_callback, this);
		empty_sub = empty_node.subscribe("/mobile_base/commands/reset_odometry", 1, &Odometry::empty_callback, this);
	}

	Odometry(vector<double> v)
	{
		a = v;
		linPos.resize(3, 0.0);
		angPos.resize(3, 0.0);
		linVel.resize(3, 0.0);
		angVel.resize(3, 0.0);

		t = Time::now();
		dt = 0.0;
		firstSub = 0;
		
		odom_pub = odom_node.advertise<nav_msgs::Odometry>("/odom", 1000 );
		twist_sub = odom_node.subscribe("/cmd_vel_mux/input/navi", 1, &Odometry::twist_callback, this);
		empty_sub = empty_node.subscribe("/mobile_base/commands/reset_odometry", 1, &Odometry::empty_callback, this);
	}

	//Functions
	void publishOdom()
	{
		// msg is a class member | nav_msgs::Odometry msg;

		// Set new values for msg
		odom_msg.pose.pose.position.x = linPos[0];
		odom_msg.pose.pose.position.y = linPos[1];
		odom_msg.pose.pose.position.z = linPos[2];

		//Set quaternion in terms of yaw, which is angPos[2]
		odom_msg.pose.pose.orientation = tf::createQuaternionMsgFromYaw(angPos[2]);

		odom_msg.twist.twist.linear.x = linVel[0];
		odom_msg.twist.twist.linear.y = linVel[1];
		odom_msg.twist.twist.linear.z = linVel[2];

		odom_msg.twist.twist.angular.x = angVel[0];
		odom_msg.twist.twist.angular.y = angVel[1];
		odom_msg.twist.twist.angular.z = angVel[2];

		// Publish msg
		odom_pub.publish(odom_msg);
	}

	//Setters
	void setxLinPos(double x){linPos[0] = x;}
	void setyLinPos(double y){linPos[1] = y;}
	void setzAngVel(double z){angVel[2] = z;}
	void setdt(double d){dt = d;}
	void seta(vector<double> v){a = v;}

	//getters

	double getxLinPos(){return linPos[0];}
	double getyLinPos(){return linPos[1];}
	double getzAngPos(){return angPos[2];} 

	//Stored as euler angles
	double getxLinVel(){return linVel[0];}
	double getzAngVel(){return angVel[2];}

	//Callbacks
	void empty_callback(const std_msgs::Empty::ConstPtr& msgs)
	{
		cout << "reset" << endl;
		linPos.resize(3, 0.0);
		angPos.resize(3, 0.0);
		linVel.resize(3, 0.0);
		angVel.resize(3, 0.0);

		t = Time::now();
		updatedt();
	}

	//Callback that gets Twist information to update current position
	//Evaluates the probabilistic velocity motion model
	void twist_callback(const geometry_msgs::Twist::ConstPtr& msgs)
	{
		cout << "--Handle twist--" << endl;
		//Algorithm for updating current state
		if(firstSub == 0)
		{
			updatedt();
			firstSub = 1;
		}
		updatedt();		//Set the new time difference

		linVel[0] = msgs->linear.x + noise(a[0] * pow(linVel[0], 2) + a[1] * pow(angVel[2], 2));
		angVel[2] = msgs->angular.z + noise(a[2] * pow(linVel[0], 2) + a[3] * pow(angVel[2], 2));

		if(angVel[2] == 0.0){			//This section accounts for no angular velocity (very unlikely)
			
			linPos[0] += linVel[0] * dt * cos(angPos[2]);
			linPos[1] += linVel[0] * dt * sin(angPos[2]);
			angPos[2] += noise(a[4]*pow(linVel[0], 2) + a[5]*pow(angVel[2], 2) * dt);
		}
		else						//This section implements the algorithm on table 5.3
		{
			double vw = linVel[0] / angVel[2];

			linPos[0] += -1.0*vw*sin(angPos[2]) + vw*sin(angPos[2] + angVel[2] * dt);
			linPos[1] += vw*cos(angPos[2]) - vw*cos(angPos[2] + angVel[2] * dt);
			angPos[2] += angVel[2] * dt + noise(a[4] * pow(linVel[0], 2) + a[5] * pow(angVel[2], 2) * dt);
		}
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
			noise += (((double)rand() / (double)RAND_MAX) * 2) - 1;
		}
		noise /= 2.0;	//Divide by 2 as shown in lab
		noise *= sqrt(b);		//Scale to b

		return noise;
	}

	//Yaw to quaternion conversion
	// vector<double> yawToQ()
	// {	
	// 	/*	Simplified expressions taken from the rotaion matrix
	// 	assuming only a yaw. 
	// 	*/
	// 	vector<double> Q;
	// 	Q.push_back(0.0);		
	// 	Q.push_back(0.0);
	// 	Q.push_back(sgn(2.0 * sin(zAngPos) * sqrt(2.0 - 2.0 * cos(zAngPos))));
	// 	Q.push_back((1.0 / 2.0) * sqrt(2.0 + 2.0 * cos(zAngPos)));

	// 	return Q;
	// }

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

class Wall
{
	double x, y, angle;

	public:
		Wall(double xPoint, double yPoint, double a)
		{
			x = xPoint;
			y = yPoint;
			angle = a;
		}
		//setters
		void setx(double xin){x = xin;}
		void sety(double yin){y = yin;}
		void setang(double angin){angle = angin;}
		/*
			returns the distance between the bot and the point where the
			rangefinder hits the wall. It uses derived equations.
			Takes angles as radians
		*/
		double depth(double xb, double yb, double angb, double angr)
		{	
			/*
				1. add bot angle with rangefinder angle
				2. solve for the intersection of the wall and rangefinder lines
				3. return the distance
			*/

			double angrb = angb + angr;
			double xp = ((tan(angrb)*xb - yb) - (tan(angle)*x - y)) / (tan(angrb) - tan(angle));
			double yp = tan(angrb)*(xp - xb) + yb;
			double d = sqrt(pow((xb - xp), 2) + pow((yb - yp), 2));

			if(	atan2(sin(angrb), cos(angrb)) - atan2(yp - yb, xp - xb) < -1.0*M_PI/4.0 ||
				atan2(sin(angrb), cos(angrb)) - atan2(yp - yb, xp - xb) > M_PI/4.0 )
			{
				return 5.0;
			}



			//cout << d << endl;
			return d;
		}
};

class Cone
{
	double x, y, r;

	public:
		Cone(double xPoint, double yPoint, double radius)
		{
			x = xPoint;
			y = yPoint;
			r = radius;
		}
		//setters
		void setx(double xin){x = xin;}
		void sety(double yin){y = yin;}
		void setr(double rin){r = rin;}

		//getters
		double getx(){return x;}
		double gety(){return y;}
		double getr(){return r;}
		/*
			This method provides a way of getting the closest point that a rangefinder
			would see if pointed at a cone. This is done using the law of cosines to
			calculate the distance from the bot to the edge of the cone where both
			the bot pose and cone position, as well as radius, is known. 

			First, the algoithm checks if the ragnefinder would hit the cone at all. 
			This is done by using geometry, noting that the rangfinder would first
			hit the cone at a point tangent to the cone.

			Then, the algorithm uses the law of cosines and solves for the smallest
			of two possible ones, and returns that.
		*/
		double depth(double xb, double yb, double angb, double angr)
		{
			double angTotal = angb + angr;

			double m = tan(angTotal);
			double c = yb - m * xb;
			double p = x;
			double q = y;

			double A = m*m + 1;
			double B = 2 * (m*c - m*q - p);
			double C = q*q - r*r + p*p - 2*c*q + c*c;

			double determinant = B*B - 4*A*C;

			double x1 = (-1.0*B+sqrt(determinant))/(2*A);
			double x2 = (-1.0*B-sqrt(determinant))/(2*A);
			double y1 = m*(-1.0*B+sqrt(determinant))/(2*A) + c;
			double y2 = m*(-1.0*B-sqrt(determinant))/(2*A) + c;

			if(	atan2(sin(angTotal), cos(angTotal)) - atan2(y1 - yb, x1 - xb) < -1.0*M_PI/4.0 ||
				atan2(sin(angTotal), cos(angTotal)) - atan2(y1 - yb, x1 - xb) > M_PI/4.0 )
			{
				return 5.0;
			}
			else if(determinant < 0)
			{
				return 5.0 ;
			}
			else if(determinant == 0.0)
			{
				return sqrt( pow( ( ((-1.0*B)/(2*A)) - xb), 2 ) + pow( (m*((-1.0*B)/(2*A)) + c - yb) , 2));
			}
			else if(determinant > 0.0)
			{
				

				double dist1 = sqrt(pow((x1 - xb), 2) + pow((y1 - yb), 2));
				double dist2 = sqrt(pow((x2 - xb), 2) + pow((y2 - yb), 2));

				return (dist1 > dist2 ? dist2 : dist1);
			}

		}
};

class RangeFinder
{
	double disc, maxAng, minAng;
	double error;
	vector<double> distances;

	NodeHandle range_node;
	Publisher range_pub;

	sensor_msgs::LaserScan sensor_msg;
	public:
		//Constuctor
		RangeFinder()
		{	
			range_pub = range_node.advertise<sensor_msgs::LaserScan>("/scan", 1000);
		}

		RangeFinder(double discritization, double max_angle, double min_angle, double e)
		{	
			disc = discritization;
			maxAng = max_angle;
			minAng = min_angle;
			error = e;

			distances.resize(discritization, 5.0);
			range_pub = range_node.advertise<sensor_msgs::LaserScan>("/scan", 1000);
		}
		//setters
		void setDisc(double d){disc = d;}
		void setmaxAng(double max){maxAng = max;}
		void setminAng(double min){minAng = min;}
		void seterror(double e){error = e;}

		void publishDistances()
		{
			sensor_msg.angle_min = minAng;							//set max angle
			sensor_msg.angle_max = maxAng;							//set min angle
			sensor_msg.angle_increment = (maxAng - minAng) / disc;	//set increment
			sensor_msg.ranges.resize(disc);
			//cout << msg.ranges.size() << endl;

			for(int x = 0; x < sensor_msg.ranges.size(); x++)		//for the size of range
			{
				sensor_msg.ranges[x] = distances[x];				//set range values to distances
			}

			range_pub.publish(sensor_msg);
			
		}

		void resetDistances()
		{
			int bound = distances.size();
			for(int j = 0; j < bound; j++)
			{
				distances[j] = 5.0;
			}
			return;
		}

		//Rangefinder taking measurements, overloaded for different objects
		vector<double> takeMeasurement(Odometry odom, Wall wall)
		{
			vector<double> data;
			double beamAngle = minAng;

			for(double inc = 0; inc < disc; inc++)
			{
				data.push_back(wall.depth(odom.getxLinPos(), 
										odom.getyLinPos(), 
										odom.getzAngPos(), 
										beamAngle) + noise());

				//Update angle. Incement * a fraction of the full range.
				beamAngle += (maxAng - minAng) / disc;
			}

			return data;
		}

		vector<double> takeMeasurement(Odometry odom, Cone cone)
		{
			vector<double> data;
			double beamAngle = minAng;
			
			for(double inc = 0; inc < disc; inc++)
			{
				data.push_back(cone.depth(odom.getxLinPos(), 
										odom.getyLinPos(), 
										odom.getzAngPos(), 
										beamAngle) + noise());
				//Update angle. Incement * a fraction of the full range.
				beamAngle += (maxAng - minAng) / disc;
			}

			return data;
		}

		vector<double> takeMeasurement(Odometry odom)
		{
			vector<double> data;
			data.resize(disc, 5.0 + noise());
			return data;
		}

		void updateDistances(vector<double> data)
		{
			//cout << "data size: " << data.size() << "; distances size: " << distances.size() << endl;
			for(int index = 0; index < data.size(); index++)
			{
				if(data[index] < distances[index]) distances[index] = data[index];
			}
		}

		double noise()
		{
			double noise = 0.0;	//Instantiate

			int x = 0;
			while(x++ < 12)		//12 cycles
			{
				//Sample from -1 to 1
				noise += (((double)rand() / (double)RAND_MAX) * 2) - 1;
			}
			noise /= 2.0;	//Divide by 2 as shown in lab
			noise *= error;		//Scale to b

			return noise;
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
		a.push_back(0.001);
		a.push_back(0.001);
		a.push_back(0.001);
		a.push_back(0.001);
		a.push_back(0.001);
		a.push_back(0.001);

	//Create objects in field
	Odometry odom(a);
	RangeFinder scanner(640, 1.0*M_PI/4.0, -1.0*M_PI/4.0, .01);

	Wall wall1(2.0, 0.0, M_PI/2.0);
	Wall wall2(0.0, 2.0, 0.0);
	Wall wall3(-2.0, 0.0, M_PI/2.0);
	Wall wall4(0.0, -2.0, 0.0);

	Cone cone1(1.0, 0.0, 0.1);
	Cone cone2(2.0, 0.0, 0.1);
	Cone cone3(3.0, 0.0, 0.1);
	Cone cone4(5.0, 0.0, 0.1);
	Cone cone5(6.0, 0.0, 0.1);
	Cone cone6(7.0, 0.0, 0.1);

	// Cone cone1(1.0, 0.0, 0.1);
	// Cone cone2(0.0, 1.0, 0.1);
	// Cone cone3(-1.0, 0.0, 0.1);
	// Cone cone4(0.0, -1.0, 0.1);
	//odom.setyLinPos(-1.0);

	// cout << "CONE: " << "x " << cone.getx() << ", y " << cone.gety() << endl;
	Rate loop_rate(60);
	while(ok())
	{
		scanner.resetDistances();
		//scanner.updateDistances(scanner.takeMeasurement(odom, wall1));
		//scanner.updateDistances(scanner.takeMeasurement(odom, wall2));
		//scanner.updateDistances(scanner.takeMeasurement(odom, wall3));
		//scanner.updateDistances(scanner.takeMeasurement(odom, wall4));
		cout << "check" << endl;
		scanner.updateDistances(scanner.takeMeasurement(odom, cone1));
		scanner.updateDistances(scanner.takeMeasurement(odom, cone2));
		scanner.updateDistances(scanner.takeMeasurement(odom, cone3));
		scanner.updateDistances(scanner.takeMeasurement(odom, cone4));
		scanner.updateDistances(scanner.takeMeasurement(odom, cone5));
		scanner.updateDistances(scanner.takeMeasurement(odom, cone6));
		scanner.publishDistances();
		odom.publishOdom();

		spinOnce();
		loop_rate.sleep();
	}

	cout << "End of Program" << endl;

	return 0;
}

