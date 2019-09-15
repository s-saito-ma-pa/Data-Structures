/*-----------------------------------------------------------------------------------------
ANSWER THE QUESTIONS FROM THE DOCUMENT HERE

(1) Which graph representation did you choose, and why?
Adjacency matrix. It takes O(1) to check if an edge exists whereas an adjacency list takes O(n). 
Therefore using an adjacency matrix would be more reasonable for our purposes. 
In order to check if there's a wall between two cells, we need to check if an edge exists because 
an edge being between vertices means the absence of a wall between the corresponding two cells. 


(2) Which search algorithm did you choose, and why?
DFS. For our purposes, DFS is more efficient than BFS because there's a possiblity that DFS can find the solution immediately
whereas BFS has to visit all the dead ends. This is problematic especially if the path to the solution is longer than the paths to the dead ends. 
Once DFS finds the solution, the execution can terminate. 
Therefore DFS is more efficient for this assignment. 


  ----------------------------------------------------------------------------------------*/

import java.io.*;
import java.lang.Math;
import java.util.Stack;

public class MazeSolver {

	public Node DFS(Graph g) {
		Stack<Node> found = new Stack<Node>();
		found.push(g.nodes[0]);
		while(!found.isEmpty()) {
			Node x = found.pop();
			if(x.index == g.nodes.length-1) { //finding the last node
				//System.out.println("reurned a node");
				x.visited = true;
				return x;
			}
			if(!x.visited) {
				x.visited = true;
				for(Node n : g.nodes) {
					if(g.edgeExists(x.index, n.index)) //checking x's neighbors
						if(n.visited==false) {
							n.parent = x;
							found.push(n);	
						}	
				}
			}

		}
		//System.out.println("did not return a node");
		return null;
	}

	public Node[] solve(Graph g) { //find the last node by DFS and backtrack
		
		Node lastNode = DFS(g);
		Stack<Node> s = new Stack<Node>();
		while(lastNode != null) {
			lastNode.inSolution = true;
			//System.out.println("here");
			s.push(lastNode);	
			lastNode = lastNode.parent; //updating lastNode
		}
		Node[] toReturn = new Node[s.size()];
		while(!s.isEmpty()) {
			int l = 0;
			toReturn[l] = s.pop(); //
			l++;
		}
		return toReturn;
	}

    public void run(String filename) throws IOException {

	// read the input file to extract relevant information about the maze
	String[] readFile = parse(filename);
	int mazeSize = Integer.parseInt(readFile[0]);
	int numNodes = mazeSize*mazeSize;
	String mazeData = readFile[1];

	// construct a maze based on the information read from the file
	Graph mazeGraph = buildGraph(mazeData, numNodes);

	// do something here to solve the maze
	solve(mazeGraph);
	// print out the final maze with the solution path
	printMaze(mazeGraph.nodes, mazeData, mazeSize);
    }

    // prints out the maze in the format used for HW8
    // includes the final path from entrance to exit, if one has been recorded,
    // and which cells have been visited, if this has been recorded
    public void printMaze(Node[] mazeCells, String mazeData, int mazeSize) {
	
	int ind = 0;
	int inputCtr = 0;

	System.out.print("+");
	for(int i = 0; i < mazeSize; i++) {
	    System.out.print("--+");
	}
	System.out.println();

	for(int i = 0; i < mazeSize; i++) {
	    if(i == 0) System.out.print(" ");
	    else System.out.print("|");

	    for(int j = 0; j < mazeSize; j++) {
		System.out.print(mazeCells[ind] + "" + mazeCells[ind] +  mazeData.charAt(inputCtr));
		inputCtr++;
		ind++;
	    }
	    System.out.println();

	    System.out.print("+");
	    for(int j = 0; j < mazeSize; j++) {
		System.out.print(mazeData.charAt(inputCtr) + "" +  mazeData.charAt(inputCtr) + "+");
		inputCtr++;
	    }
	    System.out.println();
	}
	
    }

    // reads in a maze from an appropriately formatted file (this matches the format of the 
    // mazes you generated in HW8)
    // returns an array of Strings, where position 0 stores the size of the maze grid (i.e., the
    // length/width of the grid) and position 1 minimal information about which walls exist
    public String[] parse(String filename) throws IOException {
	FileReader fr = new FileReader(filename);

	// determine size of maze
	int size = 0;
	int nextChar = fr.read();
	while(nextChar >= 48 && nextChar <= 57) {
	    size = 10*size + nextChar - 48;
	    nextChar = fr.read();
	}

	String[] result = new String[2];
	result[0] = size + "";
	result[1] = "";


	// skip over up walls on first row
	for(int j = 0; j < size; j++) {
	    fr.read();
	    fr.read();
	    fr.read();
	}
	fr.read();
	fr.read();

	for(int i = 0; i < size; i++) {
	    // skip over left wall on each row
	    fr.read();
	    
	    for(int j = 0; j < size; j++) {
		// skip over two spaces for the cell
		fr.read();
		fr.read();

		// read wall character
		nextChar = fr.read();
		result[1] = result[1] + (char)nextChar;

	    }
	    // clear newline character at the end of the row
	    fr.read();
	    
	    // read down walls on next row of input file
	    for(int j = 0; j < size; j++)  {
		// skip over corner
		fr.read();
		
		//skip over next space, then handle wall
		fr.read();
		nextChar = fr.read();
		result[1] = result[1] + (char)nextChar;
	    }

	    // clear last wall and newline character at the end of the row
	    fr.read();
	    fr.read();
	    
	}

	return result;
    }
    
    public Graph buildGraph(String maze, int numNodes) {

	Graph mazeGraph = new Graph(numNodes);
	int size = (int)Math.sqrt(numNodes);

	int mazeInd = 0;
	for(int i = 0; i < size; i++) {
	    // create edges for right walls in row i
	    for(int j = 0; j < size; j++) {
		char nextChar = maze.charAt(mazeInd);
		mazeInd++;
		if(nextChar == ' ') {
		    // add an edge corresponding to a right wall, using the indexing convention 
		    // for nodes
		    mazeGraph.addEdge(size*i + j, size*i + j + 1);
		}
	    }

	    // create edges for down walls below row i
	    for(int j = 0; j < size; j++)  {
		char nextChar = maze.charAt(mazeInd);
		mazeInd++;
		if(nextChar == ' ') {
		    // add an edge corresponding to a down wall, using the indexing convention
		    // for nodes
		    mazeGraph.addEdge(size*i + j, size*(i+1) + j);
		}
	    }    
	}

	return mazeGraph;
    }
       
    public static void main(String [] args) {
	if(args.length < 1) {
	    System.out.println("USAGE: java MazeSolver <filename>");
	}
	else{
	    try{
		new MazeSolver().run(args[0]);
	    }
	    catch(IOException e) {
		e.printStackTrace();
	    }
	}
    }

}