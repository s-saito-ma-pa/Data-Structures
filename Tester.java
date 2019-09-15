public class Tester{
public static void main(String[] args) {
	BinarySearchTree bst = new BinarySearchTree();
	bst.add("b",5);
	bst.add("a",1);
	bst.add("c",3);
	bst.add("d", 4);
	bst.add("e", 6);
	//bst.remove("b");
	//bst.remove("d");
	System.out.println(bst.lookup("a"));
	bst.inOrderTraversal();
	WordCount wc = new WordCount();
	System.out.println(wc.count("the"));
	}
}