#include <iostream>
#include "ros_localizer_cmdline.h"
#include "cstdlib"
#include "math.h"
#include <vector>
#include <unistd.h>
#include <fstream>
#include "string.h"

#include "ros/ros.h"
#include "geometry_msgs/Twist.h"	// "/cmd_vel_mux/input/navi"
#include "geometry_msgs/PoseWithCovariance.h"
#include "sensor_msgs/LaserScan.h"
#include "tf/transform_datatypes.h"


/* Namespaces */
using namespace std;
using namespace ros;

void check(int num)
{
	cout << "check: " << num << endl;
}

class Matrix
{
	/*
		This matrix is going to be modeled by vectors in a vector.
		It will also use the custom of m rows by n columns.
	*/
public:
	vector< vector<double> > matrix;

public:
	//Constuctors
	//default constuctor makes a 3 x 3 zero vector
	Matrix()
	{
		vector<double> v;
		v.resize(3, 0.0);
		matrix.resize(3, v);
	}

	Matrix(int row)
	{
		vector<double> v;
		v.resize(row, 0.0);
		matrix.resize(row, v);
	}

	Matrix(int row, double diag)
	{
		vector<double> v;
		v.resize(row, 0.0);
		matrix.resize(row, v);

		for(int x = 0; x < matrix.size(); x++)
		{
			matrix[x][x] = diag;
		}
	}


	Matrix(int row, int column)
	{
		vector<double> v;
		v.resize(column, 0.0);
		matrix.resize(row, v);
	}

	Matrix(double diag)
	{
		vector<double> v;
		v.resize(3, 0.0);
		matrix.resize(3, v);

		for(int x = 0; x < matrix.size(); x++)
		{
				matrix[x][x] = diag;
		}
	}

	//////////////////////////////////////////////////
	vector< vector<double> > getMatrix(){return matrix;}
	void setMatrix(vector< vector<double> > m)
	{
		matrix = m;
	}

	void setElement(int row, int column, double number)
	{
		matrix[row][column] = number;
	}

	void mult(double scalar)
	{
		for(int x = 0; x < matrix.size(); x++)
		{
			for(int y = 0; y < matrix[0].size(); y++)
			{
				matrix[x][y] *= scalar;
			}
		}
	}

	vector< vector<double> > mult(double scalar, vector< vector<double> > A)
	{
		vector< vector<double> > B = A;

		for(int x = 0; x < B.size(); x++)
		{
			for(int y = 0; y < B[0].size(); y++)
			{
				B[x][y] *= scalar;
			}
		}

		return B;
	}

	void mult(vector< vector<double> > B)
	{
		int	Mrow = matrix.size(),
			Mcol = matrix[0].size(),
			Brow = B.size(),
			Bcol = B[0].size();

		if(Mcol == Brow)			// If A's columns match B's rows
		{
			vector< vector<double> > C;
			vector<double> row;
			row.resize(Bcol, 0.0);	// Column count is that of B's
			C.resize(Mrow, row);	// Row count is that of A's

			for(int x = 0; x < Mrow; x++)
			{
				for(int y = 0; y < Bcol; y++)
				{
					for(int j = 0; j < Mcol; j++)	// Run through the sum
					{
						C[x][y] += matrix[x][j] * B[j][y];
					}
				}
			}

			matrix = C;
		}
	}

	vector< vector<double> > add(	vector< vector<double> > A,
									vector< vector<double> > B)
	{
		vector< vector<double> > C = A;
		if(A.size() == B.size() && A[0].size() == B[0].size())
		{
			for(int x = 0; x < C.size(); x++)
			{
				for(int y = 0; y < C[0].size(); y++)
				{
					C[x][y] = A[x][y] + B[x][y];
				}
			}
		}

		return C;
	}

	void mult(Matrix B)
	{
		int	Mrow = matrix.size(),
			Mcol = matrix[0].size(),
			Brow = B.matrix.size(),
			Bcol = B.matrix[0].size();

		if(Mcol == Brow)			// If A's columns match B's rows
		{
			vector< vector<double> > C;
			vector<double> row;
			row.resize(Bcol, 0.0);	// Column count is that of B's
			C.resize(Mrow, row);	// Row count is that of A's

			for(int x = 0; x < Mrow; x++)
			{
				for(int y = 0; y < Bcol; y++)
				{
					for(int j = 0; j < Mcol; j++)	// Run through the sum
					{
						C[x][y] += matrix[x][j] * B.matrix[j][y];
					}
				}
			}

			matrix = C;
		}
	}
	// A*B = C
	vector< vector<double> > mult(	vector< vector<double> > A,
									vector< vector<double> > B)
	{
		int	Arow = A.size(),
			Acol = A[0].size(),
			Brow = B.size(),
			Bcol = B[0].size();

		if(Acol == Brow)			// If A's columns match B's rows
		{
			vector< vector<double> > C;
			vector<double> row;
			row.resize(Bcol, 0.0);	// Column count is that of B's
			C.resize(Arow, row);	// Row count is that of A's

			for(int x = 0; x < Arow; x++)
			{
				for(int y = 0; y < Bcol; y++)
				{
					for(int j = 0; j < Acol; j++)	// Run through the sum
					{
						C[x][y] += A[x][j] * B[j][y];
					}
				}
			}

			return C;
		}
	}

	// Transposes the member matrix
	void trans()
	{
		int mRow = matrix.size();
		int mCol = matrix[0].size();
		vector< vector<double> > transpose;
		vector<double> v;
		v.resize(mRow, 0.0);
		transpose.resize(mCol, v);

		for(int x = 0; x < mRow; x++)
		{
			for(int y = 0; y < mCol; y++)
			{
				transpose[y][x] = matrix[x][y];
			}
		}
		matrix = transpose;
	}

	vector< vector<double> > trans(vector< vector<double> > m)
	{
		int mRow = m.size();
		int mCol = m[0].size();
		vector< vector<double> > transpose;
		vector<double> v;
		v.resize(mRow, 0.0);
		transpose.resize(mCol, v);

		for(int x = 0; x < mRow; x++)
		{
			for(int y = 0; y < mCol; y++)
			{
				transpose[y][x] = m[x][y];
			}
		}
		return transpose;
	}

	// Inverts the matrix in this class
	void invert()
	{
		matrix = invert(matrix);
	}

	// Inverts the matrix passed through
	vector< vector<double> > invert(vector< vector<double> > A)
	{
		//cout << "Enter Invert" << endl;
		if(A.size() == A[0].size())
		{	
			// cout << "First row d matches column d" << endl;
			// cout << det3(A) << endl;
			if(A.size() == 3 && det3(A) != 0.0)
			{
				// cout << "Invert3" << endl;
				return mult((1.0 / det3(A)), adjoint(A));
			}
			else if(A.size() == 2 && det2(A) != 0.0)
			{
				cout << "Invert2" << endl;
				return invert2(A);
			}
		}
	}

	vector< vector<double> > invert2(vector< vector<double> > A)
	{
		vector< vector<double> > B = A;
		B[0][0] = A[1][1];
		B[1][1] = A[0][0];
		B[0][1] *= -1.0;
		B[1][0] *= -1.0;
		return mult((1.0/det2(A)), B);
	}

	double det3()
	{
		if(matrix.size() != 3 || matrix[0].size() != 3)
		{
			return 0.0;
		}
		else
		{
			return	matrix[0][0]*(matrix[1][1]*matrix[2][2] - matrix[1][2]*matrix[2][1])
					- matrix[0][1]*(matrix[1][0]*matrix[2][2] - matrix[1][2]*matrix[2][0])
					+ matrix[0][2]*(matrix[1][0]*matrix[2][1] - matrix[1][1]*matrix[2][10]);
		}
	}

	double det3(vector< vector<double> > m)
	{
		if(m.size() != 3 || m[0].size() != 3)
		{
			return 0.0;
		}
		else
		{
			return	m[0][0]*(m[1][1]*m[2][2] - m[1][2]*m[2][1])
					- m[0][1]*(m[1][0]*m[2][2] - m[1][2]*m[2][0])
					+ m[0][2]*(m[1][0]*m[2][1] - m[1][1]*m[2][10]);
		}
	}

	vector< vector<double> > cofactor(vector< vector<double> > A)
	{
		vector< vector<double> > B = A;

		B[0][0] = det2(A[1][1], A[1][2], A[2][1], A[2][2]);
		B[0][1] = -1.0 * det2(A[1][0], A[1][2], A[2][0], A[2][2]);
		B[0][2] = det2(A[1][0], A[1][1], A[2][0], A[2][1]);
		B[1][0] = -1.0 * det2(A[0][1], A[0][2], A[2][1], A[2][2]);
		B[1][1] = det2(A[0][0], A[0][2], A[2][0], A[2][2]);
		B[1][2] = -1.0 * det2(A[0][0], A[0][1], A[2][0], A[2][1]);
		B[2][0] = det2(A[0][1], A[0][2], A[1][1], A[1][2]);
		B[2][1] = -1.0 * det2(A[0][0], A[0][2], A[1][0], A[1][2]);
		B[2][2] = det2(A[0][0], A[0][1], A[1][0], A[1][1]);

		return B;
	}

	vector< vector<double> > adjoint(vector< vector<double> > A)
	{
		return trans(cofactor(A));
	}

	double det2(double a, double b, double c, double d)
	{
		return a*d - b*c;
	}

	double det2(vector< vector<double> > A)
	{
		return A[0][0]*A[1][1] - A[1][0]*A[0][1];
	}


	void printEl(int row, int column)
	{
		cout << matrix[row][column] << endl;
	}

	void printMat()
	{
		for(int x = 0; x < matrix.size(); x++)
		{
			for(int y = 0; y < matrix[0].size(); y++)
			{
				cout << matrix[x][y] << "\t";
			}
			cout << endl;
		}
	}

	void printMat(vector< vector<double> > matr)
	{
		for(int x = 0; x < matr.size(); x++)
		{
			for(int y = 0; y < matr[x].size(); y++)
			{
				cout << matr[x][y] << "\t";
			}
			cout << endl;
		}
	}
};

class Cone
{
	double x, y, r;

	public:
		Cone()
		{
			x = 0.0;
			y = 0.0;
			r = 0.0;
		}

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

class Localizer
{
	NodeHandle node;

	Publisher pose_pub;
	Subscriber twist_sub, laser_sub;

	geometry_msgs::PoseWithCovariance pose_msg;
public:
	vector<double> mu;
	Matrix sigma, Gt, Vt, Mt, proj_sigma, Kt, Qt, I;
	Matrix matObj;

	vector<double> alpha, proj_mu;
	vector<Cone> glLandmark;

	vector< vector<double> > zest, z;
	vector<Matrix> Ht, St;

	double vt, wt, dt, rate;
	Time t;
	double firstDt;
	double q;


	double minAng;
	double maxAng;
	double increment;
	vector<double> ranges;

	vector< vector<double> > muPrint;
	ofstream outfile;
public:
	Localizer(vector<Cone> glm): sigma(1.0), Vt(3, 2), Mt(2), proj_sigma(1.0), Qt(0.001), I(1.0)
	{
		mu.resize(3, 0.0);

		//sigma(1.0); 
		//Matrix Gt, Vt(3, 2), Mt(2), proj_sigma(1.0), Qt(0.001), Kt, I(1.0);

		vt = 0; wt = 0;
		q = 0.0;

		alpha.resize(4, 0.01);
		proj_mu.resize(3, 0.0);

		glLandmark = glm;

		vector<double> temp; temp.resize(3, 0.0);
		zest.resize(glLandmark.size(), temp);
		vector< vector<double> > z;

		Matrix matTemp;
		Ht.resize(glLandmark.size(), matTemp);
		St.resize(glLandmark.size(), matTemp);

		minAng = 0.0;
		maxAng = 0.0;
		increment = 0.0;
		ranges.resize(0, 0.0);

		muPrint.resize(0);


		pose_pub = node.advertise<geometry_msgs::PoseWithCovariance>("/posewithco", 1);
		twist_sub = node.subscribe("/cmd_vel_mux/input/navi", 1, &Localizer::handle_twist, this);
		laser_sub = node.subscribe("/scan", 1, &Localizer::handle_laserscan,this);

		rate = 0.1;
		t = Time::now();
		dt = 0.0;
		firstDt = 0;
	}

	//////////////////////////////////////
	void setvt(double v){vt = v;}
	void setwt(double w){wt = w;}
	void setdt(double d){dt = d;}

	void handle_laserscan( const sensor_msgs::LaserScan::ConstPtr& msg)
	{
		//cout << "--Saving scans--" << endl;
		minAng = msg->angle_min;
		maxAng = msg->angle_max;
		increment = msg->angle_increment;

		int rangesSize = msg->ranges.size();
		ranges.resize(rangesSize, 0.0);
		for(int x = 0; x < rangesSize; x++)
		{
			ranges[x] = msg->ranges[x];
		}
	}

	void updatez()
	{
		//cout << "--updatez--" << endl;
		updatezest();
		int apature = 5;

		Cone c;
		double x1, y1, x2, y2, x3, y3;
		double predConex, predConey, lastConex, lastConey;
		double margin, lastMargin;

		vector<double> ob, lastCone;
		ob.resize(3, 0.0); lastCone.resize(3, 0.0);
		z.resize(0, ob);

		/*
			For every predicted object
				Find the position of the predicted object
				For all the ranges
					Take three consecutive ranges as long as they are numbers
						calculate the endpoints for the beams
						use those to calculate a best fit circle that returns the center and radius
						use the circle to create an observed feature, which is the distance to the circle
							center from the robot and the beaing from the robot
					Calculate the distance from the measured cone to the predicted cone
					Calculate the distance from the last measured cone to the predicted cone
					If current cone is closer than the last cone AND the new cone is off by less than .2
						Set the lastCone to measured cone
				Add measured cone to the measured stack

		*/
		for(int predConeNum = 0; predConeNum < zest.size(); predConeNum++)
		{
			predConex = zest[predConeNum][0] * cos(mu[2] + zest[predConeNum][1]);
			predConey = zest[predConeNum][0] * sin(mu[2] + zest[predConeNum][1]);
			//cout << "\tPredicted Cone X Y: " << predConex << " " << predConey << endl;
			for(int x = 0; ranges.size() != 0 && x < (ranges.size() - apature*2); x++)
			{
				if(!(isnan(ranges[x]) || isnan(ranges[x + apature]) || isnan(ranges[x + apature*2])))
				{
					x1 = mu[0] + ranges[x] * cos(mu[2] + x*increment + minAng);
					y1 = mu[1] + ranges[x] * sin(mu[2] + x*increment + minAng);

					x2 = mu[0] + ranges[x + apature] * cos(mu[2] + (x + apature)*increment + minAng);
					y2 = mu[1] + ranges[x + apature] * sin(mu[2] + (x + apature)*increment + minAng);

					x3 = mu[0] + ranges[x + apature*2] * cos(mu[2] + (x + apature*2)*increment + minAng);
					y3 = mu[1] + ranges[x + apature*2] * sin(mu[2] + (x + apature*2)*increment + minAng);

					c = findCicle(	x1, y1,
									x2, y2,
									x3, y3);

					ob[0] = sqrt(pow((c.getx() - mu[0]), 2) + pow((c.gety() - mu[1]), 2));
					ob[1] = atan2(c.gety() - mu[1], c.getx() - mu[0]) - mu[2];
					ob[2] = 0.0;
				}

				margin = sqrt(pow(predConex - c.getx(), 2) + pow(predConey - c.gety(), 2));

				lastConex = lastCone[0] * cos(mu[2] + lastCone[1]);
				lastConey = lastCone[0] * sin(mu[2] + lastCone[1]);
				lastMargin = sqrt(pow(predConex - lastConex, 2) + pow(predConey - lastConey, 2));

				if(margin < lastMargin && margin < .05)
				{
					lastCone = ob;
					//cout << "\t\tNew Best Cone X Y: " << c.getx() << " " << c.gety() << endl;
				}
			}
			if(lastCone[0] != 0.0)
			{
				z.push_back(lastCone);
				//cout << "\tAdded Cone R B S: " << lastCone[0] << " " << lastCone[1] << " " << lastCone[2] << endl;
			}
			lastCone.resize(0); lastCone.resize(3, 0.0);
		}
		// int index;
		// Cone c;
		// double x1, y1, x2, y2, x3, y3;
		// double margin;

		// vector< vector<double> > zob;
		// vector<double> ob;
		// ob.resize(3, 0.0);

		// cout << "--UpdateZ--" << endl;
		// cout << "minAng: " << minAng << endl;
		// cout << "maxAng: " << maxAng << endl;
		// cout << "increment: " << increment << endl;

		// for(int x = 0; x < zest.size(); x++)
		// {
		// 	cout << "\tEnter Scan Loop" << endl;
		// 	if(zest[x][1] < maxAng + mu[2] && zest[x][1] > minAng + mu[2])
		// 	{
		// 		index = (zest[x][1] - minAng) / increment;

		// 		cout << "\tIndex: " << index << endl;
		// 		cout << "\tRange: " << ranges[index] << endl;
		// 		cout << "\tzestAng" << x << ": " << zest[x][1] << endl;
		// 		if(index < 10)
		// 		{
		// 			cout << "\t\tEnter est calc1" << endl;
		// 			x1 = mu[0] + ranges[index] * cos(mu[2] + index*increment + minAng + minAng);
		// 			y1 = mu[1] + ranges[index] * sin(mu[2] + index*increment + minAng + minAng);

		// 			x2 = mu[0] + ranges[index + 3] * cos(mu[2] + (index + 3)*increment + minAng);
		// 			y2 = mu[1] + ranges[index + 3] * sin(mu[2] + (index + 3)*increment + minAng);

		// 			x3 = mu[0] + ranges[index + 6] * cos(mu[2] + (index + 6)*increment + minAng);
		// 			y3 = mu[1] + ranges[index + 6] * sin(mu[2] + (index + 6)*increment + minAng);

		// 			c = findCicle(	x1, y1,
		// 							x2, y2,
		// 							x3, y3);
		// 			cout << "\t\t\t" << "mux " << mu[0] << " muy " << mu[1] << endl;
		// 			cout << "\t\t\t" << "x1 " << x1 << " y1 " << y1 << " range " << ranges[index] << endl;
		// 			cout << "\t\t\t" << "x2 " << x2 << " y2 " << y2 << " range " << ranges[index + 3] << endl;
		// 			cout << "\t\t\t" << "x3 " << x3 << " y3 " << y3 << " range " << ranges[index + 6] << endl;
		// 			cout << "\t\t\tCircle: " << c.getx() << " " << c.gety() << " " << c.getr() << endl;

		// 			ob[0] = sqrt(pow((c.getx() - mu[0]), 2) + pow((c.gety() - mu[1]), 2));
		// 			ob[1] = atan2(c.gety() - mu[1], c.getx() - mu[0]) - mu[2];
		// 			ob[2] = 0.0;
		// 		}
		// 		else if(index > (int)(((maxAng - minAng) / increment) - 11))
		// 		{
		// 			cout << "\t\tEnter est calc2" << endl;
		// 			x1 = mu[0] + ranges[index - 6] * cos(mu[2] + (index - 6)*increment + minAng);
		// 			y1 = mu[1] + ranges[index - 6] * sin(mu[2] + (index - 6)*increment + minAng);

		// 			x2 = mu[0] + ranges[index - 3] * cos(mu[2] + (index - 3)*increment + minAng);
		// 			y2 = mu[1] + ranges[index - 3] * sin(mu[2] + (index - 3)*increment + minAng);

		// 			x3 = mu[0] + ranges[index] * cos(mu[2] + index*increment + minAng);
		// 			y3 = mu[1] + ranges[index] * sin(mu[2] + index*increment + minAng);

		// 			c = findCicle(	x1, y1,
		// 							x2, y2,
		// 							x3, y3);
		// 			cout << "\t\t\t" << "mux " << mu[0] << " muy " << mu[1] << endl;
		// 			cout << "\t\t\t" << "x1 " << x1 << " y1 " << y1 << " range " << ranges[index - 6] << endl;
		// 			cout << "\t\t\t" << "x2 " << x2 << " y2 " << y2 << " range " << ranges[index - 3] << endl;
		// 			cout << "\t\t\t" << "x3 " << x3 << " y3 " << y3 << " range " << ranges[index] << endl;
		// 			cout << "\t\t\tCircle: " << c.getx() << " " << c.gety() << " " << c.getr() << endl;

		// 			ob[0] = sqrt(pow((c.getx() - mu[0]), 2) + pow((c.gety() - mu[1]), 2));
		// 			ob[1] = atan2(c.gety() - mu[1], c.getx() - mu[0]) - mu[2];
		// 			ob[2] = 0.0;
		// 		}
		// 		else
		// 		{
		// 			cout << "\t\tEnter est calc3" << endl;
		// 			x1 = mu[0] + ranges[index - 3] * cos(mu[2] + (index - 3)*increment + minAng);
		// 			y1 = mu[1] + ranges[index - 3] * sin(mu[2] + (index - 3)*increment + minAng);

		// 			x2 = mu[0] + ranges[index] * cos(mu[2] + index*increment + minAng);
		// 			y2 = mu[1] + ranges[index] * sin(mu[2] + index*increment + minAng);

		// 			x3 = mu[0] + ranges[index + 3] * cos(mu[2] + (index + 3)*increment + minAng);
		// 			y3 = mu[1] + ranges[index + 3] * sin(mu[2] + (index + 3)*increment + minAng);

		// 			c = findCicle(	x1, y1,
		// 							x2, y2,
		// 							x3, y3);

		// 			cout << "\t\t\t" << "mux " << mu[0] << " muy " << mu[1] << endl;
		// 			cout << "\t\t\t" << "x1 " << x1 << " y1 " << y1 << " range " << ranges[index - 3] << endl;
		// 			cout << "\t\t\t" << "x2 " << x2 << " y2 " << y2 << " range " << ranges[index] << endl;
		// 			cout << "\t\t\t" << "x3 " << x3 << " y3 " << y3 << " range " << ranges[index + 3] << endl;
		// 			cout << "\t\t\tCircle: " << c.getx() << " " << c.gety() << " " << c.getr() << endl;

		// 			ob[0] = sqrt(pow((c.getx() - mu[0]), 2) + pow((c.gety() - mu[1]), 2));
		// 			ob[1] = atan2(c.gety() - mu[1], c.getx() - mu[0]) - mu[2];
		// 			ob[2] = 0.0;
		// 		}

		// 		margin = sqrt(pow((c.getx() - zest[x][0]*cos(zest[x][1])+mu[0]), 2) + pow((c.gety() - zest[x][0]*sin(zest[x][1])+mu[1]), 2));
		// 		cout << "\t\t\tMargin: " << margin << endl;
		// 		if(margin <= 0.3)
		// 		{
		// 			cout << "\t\t\tadding estimate" << endl;
		// 			zob.push_back(ob);
		// 		}
		// 	}
		// }

		// z = zob;
	}

	void handle_twist(const geometry_msgs::Twist::ConstPtr& msgs)
	{
		vt = msgs->linear.x;
		wt = msgs->angular.z;
	}

	void EKF()
	{
		if(firstDt == 0)
		{
			firstDt = 1;
			updatedt();
		}
		updatedt();
		// cout << "--EKF--" << endl;
		// cout << "\tdt: " << dt << endl;
		// cout << "mu: " << mu[0] << " " << mu[1] << endl;
		//Gt
		if(wt == 0.0)
		{
			Gt.matrix[0][0] = 1.0;
			Gt.matrix[0][2] = -vt * sin(mu[2]) * dt;
			Gt.matrix[1][1] = 1.0;
			Gt.matrix[1][2] = vt * cos(mu[2]) * dt;
			Gt.matrix[2][2] = 1.0;

			Vt.matrix[0][0] = dt*cos(mu[2]);
			Vt.matrix[0][1] = 0.0;
			Vt.matrix[1][0] = dt*sin(mu[2]);
			Vt.matrix[1][1] = 0.0;
			Vt.matrix[2][0] = 0.0;
			Vt.matrix[2][1] = 0.0;

			proj_mu[0] = mu[0] + vt*dt*cos(mu[2]);
			proj_mu[1] = mu[1] + vt*dt*sin(mu[2]),
			proj_mu[2] = mu[2];
		}
		else
		{
			Gt.matrix[0][0] = 1.0;
			Gt.matrix[0][2] = (-vt/wt) * cos(mu[2]) + (vt/wt) * cos(mu[2] + wt*dt);
			Gt.matrix[1][1] = 1.0;
			Gt.matrix[1][2] = (-vt/wt) * sin(mu[3]) + (vt/wt) * sin(mu[2] + wt*dt);
			Gt.matrix[2][2] = 1.0;

			Vt.matrix[0][0] = (-sin(mu[2]) + sin(mu[2] + wt*dt))/wt;
			Vt.matrix[0][1] = vt*(sin(mu[2]) - sin(mu[2] + wt*dt))/(wt*wt) + vt*cos(mu[2] + wt*dt)*dt/wt;
			Vt.matrix[1][0] = (cos(mu[2]) - cos(mu[2] + wt*dt))/wt;
			Vt.matrix[1][1] = -vt*(cos(mu[2]) - cos(mu[2] + wt*dt))/(wt*wt) + vt*sin(mu[2] + wt*dt)*dt/wt;
			Vt.matrix[2][0] = 0.0;
			Vt.matrix[2][1] = dt;

			proj_mu[0] = mu[0] + (-vt/wt)*sin(mu[2]) + (vt/wt)*sin(mu[2] + wt*dt);
			proj_mu[1] = mu[1] + (vt/wt)*cos(mu[2]) + (-vt/wt)*cos(mu[2] + wt*dt),
			proj_mu[2] = mu[2] + wt*dt;
		}

		Mt.matrix[0][0] = alpha[0]*vt*vt + alpha[1]*wt*wt;
		Mt.matrix[0][1] = 0.0;
		Mt.matrix[1][0] = 0.0;
		Mt.matrix[1][1] = alpha[2]*vt*vt + alpha[3]*wt*wt;

		proj_sigma.matrix = proj_sigma.add(	proj_sigma.mult( proj_sigma.mult(Gt.matrix, sigma.matrix), proj_sigma.trans(Gt.matrix)),
											proj_sigma.mult( proj_sigma.mult(Vt.matrix, Mt.matrix), proj_sigma.trans(Vt.matrix)));

		cout << "proj_Sigma: X " << proj_sigma.matrix[0][0] << " Y " << proj_sigma.matrix[1][1] << " A " << proj_sigma.matrix[2][2] << endl;

		if(z.size() == 0)
		{
			mu = proj_mu;
			sigma.matrix = proj_sigma.matrix;
			return;
		}

		//cout << "\tFor all estimated objects" << endl;
		for(int x = 0; x < z.size(); x++)
		{

			int j = 0; double prob = 0.0; double maxProb =  0.0;
			//cout << "\t\tFor all predicted objects" << endl;
			for( int y = 0; y < zest.size(); y++)
			{
				q = pow((zest[y][0] - proj_mu[0]), 2) + pow((zest[y][1] - proj_mu[1]), 2);

				zest[y][0] = sqrt(q);
				zest[y][1] = atan2(zest[y][1] - proj_mu[1], zest[y][0] - proj_mu[0]) - proj_mu[2];
				zest[y][2] = 0.0;

				Ht[y].matrix[0][0] = -(zest[y][0] - proj_mu[0])/sqrt(q);
				Ht[y].matrix[0][1] = -(zest[y][1] - proj_mu[1])/sqrt(q);
				Ht[y].matrix[1][0] = (zest[y][1] - proj_mu[1])/q;
				Ht[y].matrix[1][1] = -(zest[y][0] - proj_mu[0])/q;
				Ht[y].matrix[1][2] = -1.0;

				St[y].matrix = 	matObj.add(
									matObj.mult(
										Ht[y].matrix,
										matObj.mult(
											proj_sigma.matrix,
											matObj.trans(Ht[y].matrix)
										)
									),
									Qt.matrix
								);
				prob =	(1.0/sqrt(matObj.det3(matObj.mult(2*M_PI, St[y].matrix)))) *
							exp(matObj.mult(-0.5,
											matObj.mult(
												matObj.add(
													vectToMat(z[x]),
													matObj.mult(-1.0, vectToMat(zest[y]))
												),
												matObj.mult(
													matObj.invert(St[y].matrix),
													matObj.trans(
														matObj.add(
															vectToMat(z[x]),
															matObj.mult(-1.0, vectToMat(zest[y]))
														)
													)
												)
											)
										)[0][0]
								);

				if(prob > maxProb)
				{
					maxProb = prob;
					j = y;
				}
			}

			Kt.matrix = matObj.mult(
							proj_sigma.matrix,
							matObj.mult(
								matObj.trans(Ht[j].matrix),
								matObj.invert(St[j].matrix)
							)
						);

			proj_mu =	matObj.trans(
							matObj.add(
								matObj.trans(vectToMat(proj_mu)),
									matObj.mult(
										Kt.matrix,
										matObj.trans(
											matObj.add(
												vectToMat(z[x]),
												matObj.mult(
													-1.0,
													vectToMat(zest[j])
												)
											)
										)
									)
							)
						)[0];

			proj_sigma.matrix = matObj.mult(matObj.add(I.matrix, 
										matObj.mult(-1.0,
											matObj.mult(Kt.matrix,
														Ht[j].matrix)
											)
										), 
										proj_sigma.matrix
								);
		}
		mu = proj_mu;
		sigma = proj_sigma;

		cout << "Sigma: X " << sigma.matrix[0][0] << " Y " << sigma.matrix[1][1] << " A " << sigma.matrix[2][2] << endl;
	}

	void updatezest()
	{
		//cout << "--updatezest--" << endl;
		Cone glCone;



		for(int y = 0; y < zest.size(); y++)
		{
			glCone = glLandmark[y];
			zest[y][0] = sqrt(pow((glCone.getx() - mu[0]), 2) + pow((glCone.gety() - mu[1]), 2));
			zest[y][1] = atan2(glCone.gety() - mu[1], glCone.getx() - proj_mu[0]) - mu[2];
			zest[y][2] = 0.0;

			//cout << "\tCone " << y << ": " << zest[y][0] << "\t" << zest[y][1] << "\t" << zest[y][2] << endl;
		}
		//cout << "exit zest" << endl;
	}

	vector< vector<double> > vectToMat(vector<double> a)
	{
		vector< vector<double> > c;
		c.push_back(a);
		return c;
	}

	vector<double> vectAdd( vector<double> a, vector<double> b)
	{
		if(a.size() == b.size())
		{
			vector<double> c; c.resize(a.size(), 0.0);
			for(int x = 0; x < a.size(); x++)
			{
				c[x] = a[x] + b[x];
			}
			return c;
		}
	}

	vector<double> vectSub( vector<double> a, vector<double> b)
	{
		if(a.size() == b.size())
		{
			vector<double> c; c.resize(a.size(), 0.0);
			for(int x = 0; x < a.size(); x++)
			{
				c[x] = a[x] - b[x];
			}
			return c;
		}
	}

	vector<double> vectScaleMult( double scaler, vector<double> a)
	{
		vector<double> c; c.resize(a.size(), 0.0);
		for(int x = 0; x < a.size(); x++)
		{
			c[x] = a[x]*scaler ;
		}
		return c;
	}

	Cone findCicle(	double x1, double y1,
					double x2, double y2,
					double x3, double y3)
	{
		/*
			Use the perpendicular bisectors and find the interection for the midpoint,
			then find the radius with the distance formula
		*/

			double mx12, my12, ms12, mx23, my23, ms23;
			double a, c, b, d;
			double cx, cy, cr;

			mx12 = (x1 + x2) / 2.0;
			my12 = (y1 + y2) / 2.0;
			mx23 = (x2 + x3) / 2.0;
			my23 = (y2 + y3) / 2.0;

			ms12 = -1.0 * (x2 - x1) / (y2 - y1);
			ms23 = -1.0 * (x3 - x2) / (y3 - y2);

			a = ms12;
			c = -1.0 * ms12*mx12 + my12;

			b = ms23;
			d = -1.0 * ms23*mx23 + my23;

			cx = (d - c) / (a - b);
			cy = (a*d - b*c) / (a - b);
			cr = sqrt(pow((x1 - cx), 2) + pow((y1 - cy), 2));

			Cone cone(cx, cy, cr);

			return cone;

	}

	void publish_pose()
	{
		pose_msg.pose.position.x = mu[0];
		pose_msg.pose.position.y = mu[1];
		pose_msg.pose.orientation = tf::createQuaternionMsgFromYaw(mu[2]);

		pose_msg.covariance[0] = sigma.matrix[0][0];
		pose_msg.covariance[7] = sigma.matrix[1][1];
		pose_msg.covariance[14]= sigma.matrix[2][2];

		pose_pub.publish(pose_msg);
	}

	void printMuSig()
	{
		cout << mu[0] << "\t";
		cout << mu[1] << "\t";
		cout << mu[2] << endl;

		cout << sigma.matrix[0][0] << "\t";
		cout << sigma.matrix[1][1] << "\t";
		cout << sigma.matrix[2][2] << endl;


	}

	void writeMu(char filename[])
	{
		outfile.open(filename);
		for(int x = 0; x < muPrint.size(); x++)
		{
			outfile << muPrint[x][0] << "," << muPrint[x][1] << "," << muPrint[x][2] << endl;
		}
		outfile.close();
	}

	void updatedt(){
		Time s = Time::now();
		dt = s.toSec() - t.toSec();
		t = s;
	}

	void setMuX(double x){mu[0] = x;}
	void setMuY(double y){mu[1] = y;}
};

int main(int argc, char *argv[])
{
	gengetopt_args_info args;
	cmdline_parser(argc, argv, &args);
	init(argc, argv, "localizer");


	vector<Cone> glCones;
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

	glCones.push_back(cone1);
	glCones.push_back(cone2);
	glCones.push_back(cone3);
	glCones.push_back(cone4);
	glCones.push_back(cone5);
	glCones.push_back(cone6);



	Localizer l(glCones);
	Rate loop_rate(60);
	while(ok())
	{
		l.updatez();
		l.EKF();
		l.publish_pose();
		spinOnce();
		loop_rate.sleep();
	}

	//l.writeMu(args.fileName_arg);
	return 0;
}
