/* Includes */
#include "iostream"
#include "ros_simulator_cmdline.h"
#include "cstdlib"
#include "math.h"

#include "ros/ros.h"
#include "geometry_msgs/Twist.h"	// "/cmd_vel_mux/input/navi"
#include "std_msgs/Empty.h"			// "/mobile_base/commands/reset_odometry"
#include "nav_msgs/Odometry.h"		// "/odom"
#include "sensor_msgs/LaserScan.h"

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

 		t = Time::now();

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

	//getters

	double getxLinPos(){return xLinPos;}
	double getyLinPos(){return yLinPos;}
	double getzAngPos(){return zAngPos;} 

	//Stored as euler angles
	double getxLinVel(){return xLinVel;}
	double getzAngVel(){return zAngVel;}

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
		cin.get();
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
		dt = (s - t).toSec();
		t = s;
	}

};

class Wall
{
	double x, y, angle;

	public:
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

			//cout << d << endl;
			return d;
		}
};

class Cone
{
	double x, y, r;

	public:
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
			double dist = sqrt(pow((yb - y), 2) + pow((xb - x), 2));
			double tang = angb + angr - atan((yb - y) / (xb - x));

			if(tang > asin(r / dist))
			{
				//cout << "inf" << endl;
				return INFINITY;
			}
			else
			{
				double a = dist*cos(tang) - sqrt(pow(dist, 2)*pow(cos(tang), 2) - pow(dist, 2) + pow(r, 2));
				double b = dist*cos(tang) + sqrt(pow(dist, 2)*pow(cos(tang), 2) - pow(dist, 2) + pow(r, 2));
				//cout << a << endl;
				return (a < b ? a : b); 
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

			vector<double> d(discritization, INFINITY);
			distances = d;
			range_pub = range_node.advertise<sensor_msgs::LaserScan>("/scan", 1000);
		}
		//setters
		void setDisc(double d){disc = d;}
		void setmaxAng(double max){maxAng = max;}
		void setminAng(double min){minAng = min;}
		void seterror(double e){error = e;}

		void publishDistances()
		{
			sensor_msgs::LaserScan msg;
			msg.angle_min = minAng;							//set max angle
			msg.angle_max = maxAng;							//set min angle
			msg.angle_increment = (maxAng - minAng) / disc;	//set increment
			msg.ranges.resize(disc);
			//cout << msg.ranges.size() << endl;

			for(int x = 0; x < msg.ranges.size(); x++)		//for the size of range
			{
				msg.ranges[x] = distances[x];				//set range values to distances
			}

			range_pub.publish(msg);
			
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
				beamAngle = ((maxAng - minAng) / disc) * inc;
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
				beamAngle = ((maxAng - minAng) / disc) * inc;
			}

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
				noise += (((double)rand() / RAND_MAX) * 2) - 1;
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
		a.push_back(0.01);
		a.push_back(0.01);
		a.push_back(0.01);
		a.push_back(0.01);
		a.push_back(0.01);
		a.push_back(0.01);

	//Create objects in field
	Odometry odom;
	odom.seta(a);
	cout << "x " << odom.getxLinPos() << ", y " << odom.getyLinPos() << endl;
	RangeFinder scanner(64, M_PI/4.0, -1.0*M_PI/4.0, .1);

	Wall wall;
	wall.setx(odom.getxLinPos() + 2.0); wall.sety(odom.getyLinPos()); wall.setang(-1.0*M_PI/4);

	Cone cone;
	//cone.setx(odom.getxLinPos() + 1.0); wall.sety(odom.getyLinPos()); cone.setr(0.1);
	cone.setx(2.0); cone.sety(0.0); cone.setr(0.1);
	cout << "CONE: " << "x " << cone.getx() << ", y " << cone.gety() << endl;

	Rate loop_rate(100);
	while(ok())
	{
		scanner.updateDistances(scanner.takeMeasurement(odom, wall));
		scanner.updateDistances(scanner.takeMeasurement(odom, cone));
		scanner.publishDistances();
		odom.publishOdom();

		spinOnce();
		loop_rate.sleep();
	}

	cout << "End of Program" << endl;

	return 0;
}
