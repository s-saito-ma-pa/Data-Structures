class BinarySearchTree {
	private Node root = null;

	public BinarySearchTree() {
		
	}

	public void add(String key, Integer value){
		if(root == null) {
			root = new Node(key, value);
			return;
		} else {
			addAux(key, value, root);
		}
	}

	public void addAux(String key, Integer value, Node current) {
		if(key.compareTo(current.getKey()) > 0) {
			if(current.rightChild == null) {
				Node newNode = new Node(key, value);
				current.rightChild = newNode; 
				//current.rightChild.parent = current;
			} else {
				addAux(key, value, current.rightChild);
			}
		} else if(key.compareTo(current.getKey()) < 0) {
			if(current.leftChild == null) {
				Node newNode = new Node(key, value);
				current.leftChild = newNode;
				//current.leftChild.parent = current;
			} else {
				addAux(key, value, current.leftChild);
			}
		} else { //if the keys are equal
			current.setValue(value);
		}
	}

	public Integer lookup(String key) {
		Node curr = root;
		while(curr != null) {
			if(curr.getKey().equals(key)) {
				return curr.getValue();
			} 
			else if(key.compareTo(curr.getKey()) < 0) {
				curr = curr.leftChild;
			} else {
				curr = curr.rightChild;
			}
		}
		return 0;
	}

	public Integer remove (String key) {
		Node curr = root;
		Node toRemove = null;
		Node parentOfToRemove = null;
		Integer toReturn = null;

		//Find the node that has key
		while(curr != null) {
			if(curr.getKey() == key) {
				toRemove = curr;
				toReturn = toRemove.getValue();
				break;
			} 
			parentOfToRemove = curr;
			if(key.compareTo(curr.getKey()) < 0) {
				curr = curr.leftChild;
			} else {
				curr = curr.rightChild;
			}
		}

		if (toRemove == null) //if the element we're trying to remove is not there 
			return null;

		if(toRemove.rightChild != null && toRemove.leftChild == null) {
			//toRemove = toRemove.rightChild;
			toRemove.setKey(toRemove.rightChild.getKey());
			toRemove.setValue(toRemove.rightChild.getValue());
			toRemove.rightChild = null;

		} 

		else if(toRemove.leftChild != null && toRemove.rightChild == null){ //left
			toRemove.setKey(toRemove.leftChild.getKey());
			toRemove.setValue(toRemove.leftChild.getValue());
			toRemove.leftChild = null;
		}

		else if(toRemove.leftChild == null && toRemove.rightChild == null) {
			if (parentOfToRemove.getValue() > toRemove.getValue())
				parentOfToRemove.leftChild = null;
			else 
				parentOfToRemove.rightChild = null;
		}

		else if(toRemove.leftChild != null && toRemove.rightChild != null) {
			Node temp = findSmallest(toRemove.rightChild);
			
			if(temp.leftChild != null) {
			toRemove.setKey(temp.leftChild.getKey()); 
			toRemove.setValue(temp.leftChild.getValue());
			temp.leftChild = null;
			}

			else {
				toRemove.setKey(temp.getKey()); 
				toRemove.setValue(temp.getValue());
				if(temp.rightChild != null)
					toRemove.rightChild = temp.rightChild;
				else {
					toRemove.rightChild = null;
				}
			}
		}

		/*
		if(toRemove.rightChild != null) { //both or right
			Node plugin = findSmallest(toRemove.rightChild); 
			plugin.leftChild = toRemove.leftChild;
			toRemove.parent.leftChild = plugin;
			plugin.parent = toRemove.parent;
			toRemove = plugin;
		} else if(toRemove.leftChild == null && toRemove.rightChild == null) { //leaf
			toRemove = null;
		} else if(toRemove.leftChild != null && toRemove.rightChild == null){ //left
			toRemove = toRemove.leftChild;
		}
		*/

		return toReturn;
	}

	public void inOrderTraversal(){
		inOrderTraversalAux(root);

	}

	public void inOrderTraversalAux(Node p){
		Node pointer = p;
		if(pointer != null) {

		if(pointer.leftChild != null)
			inOrderTraversalAux(pointer.leftChild);
		System.out.println("(" + p.getKey() + "," + p.getValue() + ")");

		if(pointer.rightChild != null)
			inOrderTraversalAux(pointer.rightChild);
		}

	}

	//find the leftmost child 
	public Node findSmallest(Node current) {
		if(current.leftChild != null) {

		while(current.leftChild.leftChild != null) {
			current = current.leftChild;
		}
		
	}

		return current;

		/*
		if(current.leftChild == null)
			return current;
		else 
			return findSmallest(current.leftChild);
		*/
	}

	public Node findLargest(Node current) {
		if(current.rightChild == null)
			return current;
		else 
			return findLargest(current.rightChild);
	}


	/*
	public Node removeAux(String key, Node current) {
		if(current == null)
			return current;
		if(key.compareTo(current.getKey()) > 0)
			current.leftChild = removeAux(key, current.leftChild);
		else if(key.compareTo(current.getKey() < 0))
			current.rightChild = removeAux(key, current.rithChild);
		else {
			if(current.leftChild == null)
				return current.rightChild;
			else if(current.rightChild == null)
				return current.leftChild;

		current.key = root.rightChild.getKey();

		}







		if(current.leftChild == null && current.rightChild == null) {
			Integer temp = current.getValue();
			current = null;			
			return temp;
		} else {

		if(key.compareTo(current.getKey()) > 0)
			removeAux(key, current.rightChild);
		else if(key.compareTo(current.getKey()) < 0)
			removeAux(key, current.leftChild);
		else {
			Integer temp = current.getValue();
			current = null;
			return temp;
		}
		}

		

	}
	*/
}

	
