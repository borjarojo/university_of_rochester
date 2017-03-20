#include <iostream>
#include "ros_executer_cmdline.h"
#include "cstdlib"
#include "math.h"
#include <vector>
#include <unistd.h>
#include <fstream>
#include "string.h"

#include "ros/ros.h"
#include "geometry_msgs/PoseWithCovariance.h"
#include "geometry_msgs/Pose.h"
#include "nav_msgs/Path.h"
#include "tf/transform_datatypes.h"

using namespace std;
using namespace ros;

/*
	Class for a Node on the map.

	It will hold its location, in meters, as a vector<double> with two dimensions.
	It will hold a vector<int> that will hold the indexing for a parent node. This
		will be use in a Map class, which will then run A* on itself.
*/

class Node
{

// Class variables
public:
	// Data
	vector<double> location;		// In meters
	double weight, g, f;

	// Indexing
	vector<int> myIndex;
	vector<int> parentIndex;

// Methods
public:
	Node()
	{
		location.resize(2, 0.0);
		parentIndex.resize(2, 0.0);
		myIndex.resize(2, 0.0);
		parentIndex[0] = 0.0;
		parentIndex[1] = 0.0;
	}

	// Create a node at a specific point in space
	Node(double xLocation, double yLocation)
	{
		location.resize(2, 0.0);
		parentIndex.resize(2, 0.0);
		myIndex.resize(2, 0.0);
		location[0] 	= xLocation;
		location[1] 	= yLocation;
		parentIndex[0] 	= 0.0;
		parentIndex[1] 	= 0.0;
	}
};

/*
	Class for a Map

	This map should be able to run through a 2-D set of Nodes to do a path planning
		algorithm using A*. This will then be used to get the robot to go along a set
		way points to make it to it's goal

	It will hold a set of Nodes that represent the floor space of the lab where the
		turtlebot will follow. 4x8m, divisions of 1/4 a meter

*/

class Map
{
public:
	vector< vector<Node> > nodes;	// Map
	vector<Node*> path;

	// A*n Vars
	vector<Node*> closedSet;	
	vector<Node*> openSet;

	// A* members, preset
	Node* current;
	vector<Node*> neighbors;
	double tentG;

	int depth, width, resolution;

	int nodeCount;

public:
	Map()
	{
		//Set Nodes
		Node pushNode;		// Create a node to play with
		vector<Node> row;	// Create a row to play with
		depth = 8;
		width = 4;
		resolution = 4;

		nodeCount = 0;
		for(int y = 0; y < (width * resolution + 1); y++)						// Run through every row
		{
			row.resize(0);								// Zero the row
			for(int x = 0; x < (depth * resolution + 1); x++)					// Run through every column
			{
				pushNode.location[0] = (1/(double)resolution) * x;	// Set the node to have an x dependent on the column
				pushNode.location[1] = (double)width/2.0 - (1/(double)resolution) * y;// Set the node to have a y dependent on the row
				pushNode.weight = 0.0;
				pushNode.g = INFINITY;
				pushNode.f = INFINITY;

				pushNode.myIndex[0] = x;				// Save the index of each Node
				pushNode.myIndex[1] = y;

				row.push_back(pushNode);				// push

				nodeCount++;
			}
			nodes.push_back(row);
		}
		setHeavyNodes();
		//Set path
		path.resize(0);
	}

	//This method sets the weight of nodes that intersect with cones to be infinity
	void setHeavyNodes()
	{
		// Cone 1
		nodes[8][3].weight = INFINITY;
		nodes[8][4].weight = INFINITY;
		nodes[8][5].weight = INFINITY;
		nodes[7][4].weight = INFINITY;
		nodes[9][4].weight = INFINITY;

		// Cone 2
		nodes[8][7].weight = INFINITY;
		nodes[8][8].weight = INFINITY;
		nodes[8][9].weight = INFINITY;
		nodes[7][8].weight = INFINITY;
		nodes[9][8].weight = INFINITY;

		// Cone 3
		nodes[8][11].weight = INFINITY;
		nodes[8][12].weight = INFINITY;
		nodes[8][13].weight = INFINITY;
		nodes[7][12].weight = INFINITY;
		nodes[9][12].weight = INFINITY;

		// Cone 4
		nodes[8][19].weight = INFINITY;
		nodes[8][20].weight = INFINITY;
		nodes[8][21].weight = INFINITY;
		nodes[7][20].weight = INFINITY;
		nodes[9][20].weight = INFINITY;

		// Cone 5
		nodes[8][23].weight = INFINITY;
		nodes[8][24].weight = INFINITY;
		nodes[8][25].weight = INFINITY;
		nodes[7][24].weight = INFINITY;
		nodes[9][24].weight = INFINITY;

		// Cone 6
		nodes[8][27].weight = INFINITY;
		nodes[8][28].weight = INFINITY;
		nodes[8][29].weight = INFINITY;
		nodes[7][28].weight = INFINITY;
		nodes[9][28].weight = INFINITY;
	}

	void printMap()
	{
		cout << "--Map Print--" << endl;
		for(int y = 0; y < nodes.size(); y++)
		{
			for(int x = 0; x < nodes[y].size(); x++)
			{
				cout << "\t" << "(" << nodes[y][x].location[0] << ", " << 
					nodes[y][x].location[1] << ")" << "(" << nodes[y][x].myIndex[0] << ", " << 
					nodes[y][x].myIndex[1] << ")" ;
			}
			cout << endl;
		}
	}

	void printPath()
	{
		for(int x = 0; x < path.size(); x++)
		{
			cout << "(" << path[x]->myIndex[0] << ", " << path[x]->myIndex[1] << ")" << endl;
		}
	}

	void printNeighbors()
	{
		for(int x = 0; x < neighbors.size(); x++)
		{
			cout << "(" << neighbors[x]->myIndex[0] << ", " << neighbors[x]->myIndex[1] << ")" << endl;
		}
	}

	/*
		Updates the set of path nodes to be the path that is created through A*
	*/
	bool aStar(Node* start, Node* end)
	{
		// cout << "--A*--" << endl;
		// Closed Set
		closedSet.resize(0);			// Reset the closed set
		// cout << "\tresize closedSet to 0" << endl;

		// Open Set
		openSet.resize(0);				// Reset the open set
		// cout << "\tresize openSet to 0" << endl;
		openSet.push_back(start);
		// cout << "\t\tpush start onto openSet" << endl;

		// Reset f and G
		for(int y = 0; y < nodes.size(); y++)
		{
			for(int x = 0; x < nodes[y].size(); x++)
			{
				nodes[y][x].g = INFINITY;
				nodes[y][x].f = INFINITY;
			}
		}
		// cout << "\treset g and f of the map nodes to INFINITY" << endl;

		// Set f ang g for start
		start->g = 0.0;
		start->f = 0.0;
		// cout << "\tset start g and f to 0" << endl;

		// Algorithm
		while(!openSet.empty())					// While openSet is not empty
		{
			// cout << "\t\twhile openSet is not empty" << endl;
			setCurrentToLowestFInOpenSet();			// Set the current node to the one with the lowest F value in openSet
			// cout << "\t\tset current not to lowest f in openSet" << endl;
			if(current == end)					// If the current node is the end node
			{
				// cout << "\t\t\tcurrent node is the end node" << endl;
				setPath(current, start);				// Set the class variable path to be the set of nodes that
				// cout << "\t\t\tset class path to be of the current node to the start node" << endl;
				return true;								// lead to the current node from the start
			}

			removeNodeFromOpenSet(current);		// Current is being evaluated, so remove it from openSet
			closedSet.push_back(current);		// Current is being evaluated, so push it to closedSet
			setNodeNeighbors(current);			// Set neighbors to be a vector of pointers for the nieghbors of the current node
			for(int x = 0; x < neighbors.size(); x++)
			{
				if(isInClosedSet(neighbors[x]))
				{
					continue;
				}
				else if(isinf(neighbors[x]->weight))
				{
					closedSet.push_back(neighbors[x]);
					continue;
				}

				tentG = current->g + distBetweenTwoNodes(current, neighbors[x]);
				if(isNotInOpenSet(neighbors[x]))
				{
					openSet.push_back(neighbors[x]);
				}
				else if(tentG >= neighbors[x]->g)
				{
					continue;
				}

				neighbors[x]->parentIndex = current->myIndex;
				neighbors[x]->g = tentG;
				neighbors[x]->f = neighbors[x]->g + h(neighbors[x], end);

			}
		}
		return false;
	}

	bool aStar(int x1, int y1, int x2, int y2)
	{
		aStar(&nodes[y1][x1], &nodes[y2][x2]);
	}

	// Returns a pointer to the node with lowest F value of the open set
	void setCurrentToLowestFInOpenSet()
	{
		Node* lowest = openSet[0];

		for(int x = 0; x < openSet.size(); x++)
		{
			if(lowest->f > openSet[x]->f)
			{
				lowest = openSet[x];
			}
		}

		current = lowest;
	}

	void removeNodeFromOpenSet(Node* currentNode)
	{
		for(int x = 0; x < openSet.size(); x++)
		{
			if(currentNode == openSet[x])
			{
				openSet.erase(openSet.begin()+x); 	//erase takes a reference
			}
		}
	}

	void setPath(Node* currentNode, Node* startNode)
	{
		//cout << "--setPath--" << endl;
		Node* parentNode;
		path.resize(0);
		

		while(startNode != currentNode)
		{
			path.push_back(currentNode);
			parentNode = &nodes[currentNode->parentIndex[1]][currentNode->parentIndex[0]];
			currentNode = parentNode;
		}
		path.push_back(currentNode);
	}

	// Constant Time ?
	void setNodeNeighbors(Node* currentNode)
	{
		neighbors.resize(0);	// Reset Neighbors

		if(currentNode->myIndex[0] == 0 || currentNode->myIndex[0] == (nodes[0].size() - 1))	// If on the side edge
		{
			if(currentNode->myIndex[1] == 0 || currentNode->myIndex[1] == (nodes.size() - 1))	// If on the top or bottom
			{
				// Corner cases
				if(currentNode->myIndex[0] == 0 && currentNode->myIndex[1] == 0)	// Top right
				{
					neighbors.push_back(&nodes[0][1]);	// right of node
					neighbors.push_back(&nodes[1][0]);	// under node
					neighbors.push_back(&nodes[1][1]);	// diagonal to node
				}
				else if(currentNode->myIndex[0] == (nodes[0].size() - 1) && currentNode->myIndex[1] == 0)	// Top left
				{
					neighbors.push_back(&nodes[0][(nodes[0].size() - 2)]);	// left of node
					neighbors.push_back(&nodes[1][(nodes[0].size() - 1)]);	// under node
					neighbors.push_back(&nodes[1][(nodes[0].size() - 2)]);	// diagonal to node
				}
				else if(currentNode->myIndex[0] == 0 && currentNode->myIndex[1] == (nodes.size() - 1))	// Bottom Right
				{
					neighbors.push_back(&nodes[(nodes.size() - 1)][1]);	// right of node
					neighbors.push_back(&nodes[(nodes.size() - 2)][0]);	// over node
					neighbors.push_back(&nodes[(nodes.size() - 2)][1]);	// diagonal to node
				}
				else if(currentNode->myIndex[0] == (nodes[0].size() - 1) && currentNode->myIndex[1] == (nodes.size() - 1))																				//Bottom Left
				{
					neighbors.push_back(&nodes[(nodes.size() - 1)][(nodes[0].size() - 2)]);	// left of node
					neighbors.push_back(&nodes[(nodes.size() - 2)][(nodes[0].size() - 1)]);	// over node
					neighbors.push_back(&nodes[(nodes.size() - 2)][(nodes[0].size() - 2)]);	// diagonal to node
				}
			}
			else																			// Side, but not top or bottom
			{
				if(currentNode->myIndex[0] == 0)	// If on right
				{
					neighbors.push_back(&nodes[currentNode->myIndex[1] - 1][0]);	// Up
					neighbors.push_back(&nodes[currentNode->myIndex[1] - 1][1]);	// Up Diag
					neighbors.push_back(&nodes[currentNode->myIndex[1]	][1]);	// Side
					neighbors.push_back(&nodes[currentNode->myIndex[1] + 1][1]);	// Down Diag
					neighbors.push_back(&nodes[currentNode->myIndex[1] + 1][0]);	// Down
				}
				else if(currentNode->myIndex[0] == (nodes[0].size() - 1)) // If on left
				{
					neighbors.push_back(&nodes[currentNode->myIndex[1] - 1][(nodes[0].size() - 1)]);	// Up
					neighbors.push_back(&nodes[currentNode->myIndex[1] - 1][(nodes[0].size() - 2)]);	// Up Diag
					neighbors.push_back(&nodes[currentNode->myIndex[1]	][(nodes[0].size() - 2)]);	// Side
					neighbors.push_back(&nodes[currentNode->myIndex[1] + 1][(nodes[0].size() - 2)]);	// Down Diag
					neighbors.push_back(&nodes[currentNode->myIndex[1] + 1][(nodes[0].size() - 1)]);	// Down
				}
			}
		}
		else																			// Not on side
		{
			if(currentNode->myIndex[1] == 0 || currentNode->myIndex[1] == nodes.size() - 1)	// If on the top or bottom
			{
				if(currentNode->myIndex[1] == 0)	// On top
				{
					neighbors.push_back(&nodes[0][currentNode->myIndex[0] + 1]);	// right
					neighbors.push_back(&nodes[1][currentNode->myIndex[0] + 1]);	// right bottom
					neighbors.push_back(&nodes[1][currentNode->myIndex[0]]);	// bottom
					neighbors.push_back(&nodes[1][currentNode->myIndex[0] - 1]);	// left bottom
					neighbors.push_back(&nodes[0][currentNode->myIndex[0] - 1]);	// left
				}
				else if(currentNode->myIndex[1] == nodes.size() - 1)	// On bottom
				{
					neighbors.push_back(&nodes[nodes.size() - 1][currentNode->myIndex[0] + 1]);	// right
					neighbors.push_back(&nodes[nodes.size() - 2][currentNode->myIndex[0] + 1]);	// right up
					neighbors.push_back(&nodes[nodes.size() - 2][currentNode->myIndex[0]]);	// up
					neighbors.push_back(&nodes[nodes.size() - 2][currentNode->myIndex[0] - 1]);	// left up
					neighbors.push_back(&nodes[nodes.size() - 1][currentNode->myIndex[0] - 1]);	// left
				}
			}
			else
			{
				neighbors.push_back(&nodes[currentNode->myIndex[1] + 1][currentNode->myIndex[0]]); // top
				neighbors.push_back(&nodes[currentNode->myIndex[1] + 1][currentNode->myIndex[0] + 1]);	// top right
				neighbors.push_back(&nodes[currentNode->myIndex[1]][currentNode->myIndex[0] + 1]);	// right
				neighbors.push_back(&nodes[currentNode->myIndex[1] - 1][currentNode->myIndex[0] + 1]);	// bottom right
				neighbors.push_back(&nodes[currentNode->myIndex[1] - 1][currentNode->myIndex[0]]);	// bottom 
				neighbors.push_back(&nodes[currentNode->myIndex[1] - 1][currentNode->myIndex[0] - 1]);	// bottom left
				neighbors.push_back(&nodes[currentNode->myIndex[1]][currentNode->myIndex[0] - 1]);	//  left
				neighbors.push_back(&nodes[currentNode->myIndex[1] + 1][currentNode->myIndex[0] - 1]);	// top left
			}
		}
	}

	bool isInClosedSet(Node* neighborNode)
	{
		for(int x = 0; x < closedSet.size(); x++)
		{
			if(closedSet[x] == neighborNode)
			{
				return true;
			}
		}
		return false;
	}

	bool isNotInOpenSet(Node* neighborNode)
	{
		for(int x = 0; x < openSet.size(); x++)
		{
			if(openSet[x] == neighborNode)
			{
				return false;
			}
		}
		return true;
	}

	double distBetweenTwoNodes(Node* currentNode, Node* neighborNode)
	{
		return sqrt((neighborNode->location[0] -currentNode->location[0])*(neighborNode->location[0] -currentNode->location[0]) +
					(neighborNode->location[1] - currentNode->location[1])*(neighborNode->location[1] - currentNode->location[1]));
	}

	/*
		This is the h function for my A* algorithm. For this problem, it takes the 
		raw shortest euclidian distance to the end node from the one being evaluated.
	*/
	double h(Node* at, Node* end)
	{
		return distBetweenTwoNodes(at, end);
	}

};

/*
	Class that holds the master path and publishes the point the navigator should aim for
*/
class Path
{
	NodeHandle n;

	Subscriber pose_sub;
	Publisher pose_pub;
	Publisher path_pub;

	geometry_msgs::Pose pose_msg;
	nav_msgs::Path path_msg;
public:
	Map map;
	vector<Node*> waypoints;	//Create a set of way points
	vector<Node*> masterPath;

	vector<double> position;
	vector<double> sigma;


	vector<double> currentGoal;

public:
	Path()
	{
		cout << "--Goal Tracker--" << endl;
		//Set the way points
		waypoints.resize(0);
			//Up
		waypoints.push_back(&map.nodes[8][0]);
		waypoints.push_back(&map.nodes[4][4]);
		waypoints.push_back(&map.nodes[8][6]);
		waypoints.push_back(&map.nodes[12][8]);
		waypoints.push_back(&map.nodes[8][10]);
		waypoints.push_back(&map.nodes[4][12]);
		waypoints.push_back(&map.nodes[8][16]);
		waypoints.push_back(&map.nodes[12][20]);
		waypoints.push_back(&map.nodes[8][22]);
		waypoints.push_back(&map.nodes[4][24]);
		waypoints.push_back(&map.nodes[8][26]);
		waypoints.push_back(&map.nodes[12][28]);
		waypoints.push_back(&map.nodes[8][30]);
			//Down
		waypoints.push_back(&map.nodes[4][28]);
		waypoints.push_back(&map.nodes[8][26]);
		waypoints.push_back(&map.nodes[12][24]);
		waypoints.push_back(&map.nodes[8][22]);
		waypoints.push_back(&map.nodes[4][20]);
		waypoints.push_back(&map.nodes[8][16]);
		waypoints.push_back(&map.nodes[12][12]);
		waypoints.push_back(&map.nodes[8][10]);
		waypoints.push_back(&map.nodes[4][8]);
		waypoints.push_back(&map.nodes[8][6]);
		waypoints.push_back(&map.nodes[12][4]);
		waypoints.push_back(&map.nodes[9][0]);

		cout << "\tSet Waypoints" << endl;

		position.resize(3, 0.0);
		sigma.resize(3, 1.0);
		masterPath.resize(0);
		currentGoal.resize(3, 0.0);

		pose_sub = n.subscribe("/posewithco", 1, &Path::handle_pose, this);
		pose_pub = n.advertise<geometry_msgs::Pose>("/pose", 1);
		path_pub = n.advertise<nav_msgs::Path>("/path", 1, true);
	}

	void printPath()
	{
		cout << "--Print master path--" << endl;
		for(int x = 0; x < masterPath.size(); x++)
		{
			cout << "(" << masterPath[x]->myIndex[0] << ", " << masterPath[x]->myIndex[1] << ")" << endl;
		}
	}


	void printNodeIndex(Node* point)
	{
		cout << "(" << point->myIndex[0] << ", " << point->myIndex[1] << ")" << endl;
	}

	void printWaypointsIndex()
	{
		for(int x = 0; x < waypoints.size(); x++)
		{
			printNodeIndex(waypoints[x]);
		}
	}

	void mapPath()
	{
		//cout << "--mapPath--" << endl;
		for(int x = 0; x < waypoints.size() - 1; x++)
		{
			//cout << "\twaypoint " << x << endl; 
			//cout << "\tcheck" << endl;
			//cout << "\tmap.aStar" << endl;
			map.aStar(waypoints[x], waypoints[x+1]);

			for(int y = map.path.size() -2; y >= 0; y--)	//skip the first due to repitition
			{	
				//cout << "\t\tmap.path" << y << ": " << map.path[y]->myIndex[0] << ", " << map.path[y]->myIndex[1] << endl;
				//printNodeIndex(map.path[y]);
				masterPath.push_back(map.path[y]);
			}
		}
	}



	void handle_pose( const geometry_msgs::PoseWithCovariance::ConstPtr& msg)
	{
		position[0] = msg->pose.position.x;
		position[1] = msg->pose.position.y;
		position[2] = tf::getYaw(msg->pose.orientation);

		sigma[0] = msg->covariance[0];
		sigma[0] = msg->covariance[7];
		sigma[0] = msg->covariance[14];
	}

	void publish_path()
	{
		//cout << "publishPath" << endl;
		path_msg.poses.resize(masterPath.size());
		//cout << "mstpath.size()" << masterPath.size() << endl;
		for(int x = 0; x < masterPath.size(); x++)
		{
			//cout << "pathset" << endl;
			path_msg.poses[x].pose.position.x = masterPath[x]->location[0];
			path_msg.poses[x].pose.position.y = masterPath[x]->location[1];
		}

		path_pub.publish(path_msg);
	}

	void publish_pose()
	{
		cout << "--publish_pose--" << endl;
		//Find closest node and distacne to it
		cout << "\t--check1--" << endl;
		Node* closestPathPoint; int cppIndex = 0;
		double bestDistance = INFINITY;
		double currDistance = 0.0;
		for(int x = 0; x < masterPath.size() - 1; x++)
		{
			currDistance =	sqrt( 	(position[0] - masterPath[x]->location[0]) * (position[0] - masterPath[x]->location[0]) +
								(position[1] - masterPath[x]->location[1]) * (position[1] - masterPath[x]->location[1])
						);
			if(currDistance < bestDistance)
			{
				bestDistance = currDistance;
				closestPathPoint = masterPath[x];
				cppIndex = x;
			}
		}

		cout << "\t--check2--" << endl;
		//Find global goal
		vector<double> globalGoal; globalGoal.resize(2, 0.0);

		if(cppIndex < masterPath.size() - 1)
		{
			if(bestDistance < 0.1)
			{
				globalGoal[0] = 0.2 * 	cos(
											atan2(	
												(masterPath[cppIndex+1]->location[1] - masterPath[cppIndex]->location[1]),
												(masterPath[cppIndex+1]->location[0] - masterPath[cppIndex]->location[0])
											)
										) + masterPath[cppIndex]->location[0];
				globalGoal[1] = 0.2  * 	sin(
											atan2(	
												(masterPath[cppIndex+1]->location[1] - masterPath[cppIndex]->location[1]),
												(masterPath[cppIndex+1]->location[0] - masterPath[cppIndex]->location[0])
											)
										) + masterPath[cppIndex]->location[1];
			}
			else
			{
				globalGoal[0] = masterPath[cppIndex]->location[0];
				globalGoal[1] = masterPath[cppIndex]->location[1];
			}


		}
		else
		{
				globalGoal[0] = masterPath[cppIndex]->location[0];
				globalGoal[1] = masterPath[cppIndex]->location[1];
		}



		cout << "\tGG: " << "x " << globalGoal[0] << " y " << globalGoal[1] << endl;
		//transform globalGoal into local frome
		currentGoal[0] = globalGoal[0] - position[0];
		currentGoal[1] = globalGoal[1] - position[0];
		currentGoal[2] = atan2(currentGoal[0], currentGoal[1]) - position[2];

		cout << "\tCG: " << "x " << currentGoal[0] << " y " << currentGoal[1] << endl;
		pose_msg.position.x = currentGoal[0];
		pose_msg.position.y = currentGoal[1];
		pose_msg.orientation = tf::createQuaternionMsgFromYaw(currentGoal[2]);

		pose_pub.publish(pose_msg);
	}
};

int main(int argc, char *argv[])
{
	gengetopt_args_info args;
	cmdline_parser(argc, argv, &args);
	init(argc, argv, "executer");

	

	Path tracker;
	tracker.mapPath();
	Rate loop_rate(60);
	while(ok())
	{
		tracker.publish_path();
		tracker.publish_pose();
		spinOnce();
		loop_rate.sleep();
		
	}
	
	// while(ok())
	// {
	// 	tracker.gogogo();
	// 	cout << "Bot Position: " << "X " << tracker.position[0] << " Y " << tracker.position[1] << endl;
	// 	cout << "Distance : " << tracker.distanceToGoal << endl;
	// 	cout << "Current Goal: " << "X " << tracker.currentGoal->myIndex[0] << " Y " << tracker.currentGoal->myIndex[1] << endl;



	// 	spinOnce();
	// 	loop_rate.sleep();
	// }

	/* code */
	return 0;
}
