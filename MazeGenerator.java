import java.lang.Math;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class MazeGenerator {

    public void run(int n) {

	// creates all cells
	Cell[][] mazeMap = new Cell[n][n];
	initializeCells(mazeMap);

	// create a list of all internal walls, and links the cells and walls
	Wall[] walls = getWalls(mazeMap);

	createMaze(walls, mazeMap);

	printMaze(mazeMap);

    }


    public void createMaze(Wall[] walls, Cell[][] mazeMap) {
	// FILL IN THIS METHOD
    	UnionFind uf = new UnionFind();
    	for(int i=0; i<mazeMap[0].length; i++) {
    		for(int j=0; j<mazeMap[0].length; j++) {
    			uf.makeset(mazeMap[i][j]);
    		}
    	}

    	Random rand = new Random();

    	while(!done(mazeMap)) {

    		int randInt = rand.nextInt(walls.length);
    		Wall randwall = walls[randInt];
    		if(randwall.visible == true) {
    			//System.out.println("in the first if statement");
    			if((randwall.first != null && randwall.second != null) && !randwall.first.head.equals(randwall.second.head)) {
    				uf.union(randwall.first, randwall.second);
    				randwall.visible = false;
    				//System.out.println("in the double if statement");
    			}
    		}


    	}

    	makeEntryAndExit(mazeMap, walls);
    }


    public boolean done(Cell[][] mazeMap) {
    LLAddOnly firstHead = mazeMap[0][0].head;
    	for(int i=0; i<mazeMap[0].length; i++) {
    		for(int j=0; j<mazeMap[0].length; j++) {
    			if(!(mazeMap[i][j].head.equals(firstHead)))
    				return false;
    		}
    	}
    	return true;
    }


/*
    public boolean notAllSafe(Wall[] walls) {
    	boolean safe = false;
    	for(int i=0; i<walls.length; i++) {
    		if(!walls[i].visible)
    			break;
    		else if(walls[i].first != null && walls[i].second != null)
    			safe = true;

    		else if(walls[i].first != null && walls[i].second ==null || walls[i].first == null || walls[i].second.head !=null) {
    			System.out.println(i + "th wall has exactly one null cell");
    			break;
    		}
    		else if(walls[i].first == null && walls[i].second ==null) {
    			System.out.println(i + "th wall's cells are both null");
    			break;
    		}
			else if(walls[i].first.head == walls[i].second.head) { //if both of the adjacent cells belong to the same set
    			System.out.println(i + "th wall's cells belong to the same set");
    			break;
    		}
    		else 
    			break;
    	}
    
    	return safe;
    }
    */

    public void makeEntryAndExit(Cell[][] mazeMap, Wall[] walls) {
    	mazeMap[0][0].left.visible = false;
    	walls[walls.length-1].visible = false;
    }


    // print out the maze in a specific format
    public void printMaze(Cell[][] maze) {
	for(int i = 0; i < maze.length; i++) {
	    // print the up walls for row i
	    for(int j = 0; j < maze.length; j++) {
		Wall up = maze[i][j].up;
		if(up != null && up.visible) System.out.print("+--");
		else System.out.print("+  ");
	    }
	    System.out.println("+");

	    // print the left walls and the cells in row i
	    for(int j = 0; j < maze.length; j++) {
		Wall left = maze[i][j].left;
		if(left != null && left.visible) System.out.print("|  ");
		else System.out.print("   ");
	    }

	    //print the last wall on the far right of row i
	    Wall lastRight = maze[i][maze.length-1].right;
	    if(lastRight != null && lastRight.visible) System.out.println("|");
	    else System.out.println(" ");
	}

	// print the last row's down walls
	for(int i = 0; i < maze.length; i++) {
	    Wall down = maze[maze.length-1][i].down;
	    if(down != null && down.visible) System.out.print("+--");
	    else System.out.print("+  ");
	}
	System.out.println("+");


    }

    // create a new Cell for each position of the maze
    public void initializeCells(Cell[][] maze) {
	for(int i = 0; i < maze.length; i++) {
	    for(int j = 0; j < maze[0].length; j++) {
		maze[i][j] = new Cell();
	    }
	}
    }

    // create all walls and link walls and cells
    public Wall[] getWalls(Cell[][] mazeMap) {

	int n = mazeMap.length;

	Wall[] walls = new Wall[2*n*(n+1)];
	int wallCtr = 0;

	// each "inner" cell adds its right and down walls
	for(int i = 0; i < n; i++) {
	    for(int j = 0; j < n; j++) {
		// add down wall
		if(i < n-1) {
		    walls[wallCtr] = new Wall(mazeMap[i][j], mazeMap[i+1][j]);
		    mazeMap[i][j].down = walls[wallCtr];
		    mazeMap[i+1][j].up = walls[wallCtr];
		    wallCtr++;
		}
		
		// add right wall
		if(j < n-1) {
		    walls[wallCtr] = new Wall(mazeMap[i][j], mazeMap[i][j+1]);
		    mazeMap[i][j].right = walls[wallCtr];
		    mazeMap[i][j+1].left = walls[wallCtr];
		    wallCtr++;
		}
	    }
	}

	// "outer" cells add their outer walls
	for(int i = 0; i < n; i++) {
	    // add left walls for the first column
	    walls[wallCtr] = new Wall(null, mazeMap[i][0]);
	    mazeMap[i][0].left = walls[wallCtr];
	    wallCtr++;

	    // add up walls for the top row
	    walls[wallCtr] = new Wall(null, mazeMap[0][i]);
	    mazeMap[0][i].up = walls[wallCtr];
	    wallCtr++;

	    // add down walls for the bottom row
	    walls[wallCtr] = new Wall(null, mazeMap[n-1][i]);
	    mazeMap[n-1][i].down = walls[wallCtr];
	    wallCtr++;

	    // add right walls for the last column
	    walls[wallCtr] = new Wall(null, mazeMap[i][n-1]);
	    mazeMap[i][n-1].right = walls[wallCtr];
	    wallCtr++;
	}


	return walls;
    }


    public static void main(String [] args) {
	if(args.length > 0) {
	    int n = Integer.parseInt(args[0]);
	    new MazeGenerator().run(n);
	}
	else new MazeGenerator().run(5);
    }

}