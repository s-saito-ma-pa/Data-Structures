import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WordCount {

	//static Node root = null; 
	public static BinarySearchTree bst = null;


	public static void main(String[] args) {
		bst = new BinarySearchTree();
		addAll();
		bst.inOrderTraversal();
		//System.out.println(bst.lookup("the") + " <- DONE");
		
	}
	public static void addAll() { //add a new word to the tree, if it already exists, then increment its count
		
		Scanner sc = null;

		try {
   			sc = new Scanner(new File("sample.txt"));
		}
		catch (FileNotFoundException e) {
    		e.printStackTrace();
		}

		while(sc.hasNext()) {
			String key = sc.next().toString().toLowerCase();
			Integer value = bst.lookup(key);
			if(value == 0){ //if the word doesn't exist
				System.out.println("adding " + key);
				bst.add(key, 1);
			}

			else{ //if the word already exists
				bst.add(key, value+1);
			}
		}
		//bst.inOrderTraversal();
	}

}





	


		















