public class Graph {

    int numNodes;
    int[][] matrix;

    public void addEdge(int i, int j) {
    	if(i < matrix.length && j < matrix.length) {
			matrix[i][j] = 1;
			matrix[j][i] = 1;
		}
	}
		
    public Graph(int num) {
		numNodes = num;
		nodes = new Node[numNodes];
		matrix = new int[num][num];
		for(int i = 0; i < numNodes; i++) {
	    	nodes[i] = new Node(i);
		}
	}

	public boolean edgeExists(int i, int j) {
		if(matrix[i][j] == 1 && matrix[j][i] == 1)
			return true;
		else
			return false;
	}

	// you might also want to do other things here
    

}