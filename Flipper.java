import java.lang.Math;
import java.util.*;

public class Flipper {

    // input: the number of pancakes
    public void run(int numPancakes) {
	Stack<Integer> s = new Stack<Integer>();
	
	initialize(s, numPancakes);

	printPancakes(s);
	
	flipAllPancakes(s);
	
	printPancakes(s);
	
    } 
    ///////////////////////////////////////////////////////
    public void flipAllPancakes(Stack<Integer> pancakes) {
	// FILL IN THIS METHOD
    	int initialsize = pancakes.size();
    	for(int sorted=0; sorted<pancakes.size(); sorted++) {
	    	Stack<Integer> aux = new Stack<Integer>();
	    	int index=0;
			int largest=0;
			int largestindex=0;
			while(largest<initialsize-sorted) {
				int top = pancakes.pop();

				aux.push(top);	   		
				if(largest<top) {
					largest = top;
					largestindex = index;
				}
				index++;
			}
			
			while(aux.isEmpty()==false) {
				pancakes.push(aux.pop());
			}
			flip(pancakes, largestindex);
			printPancakes(pancakes);
			//here
			flip(pancakes, pancakes.size()-1-sorted);
			printPancakes(pancakes);
    	}
    }
    	
    	
    public void printPancakes (Stack<Integer> pancakes) {
    	Stack<Integer> aux = new Stack<Integer>();
    	if(pancakes.isEmpty()) {
    		return;
    	}
    	
    	while(pancakes.isEmpty() == false) {

    		aux.push(pancakes.pop());
    		
    	}	
    	
    	while(aux.isEmpty() == false) {
    		pancakes.push(aux.pop());
    		System.out.print(pancakes.peek() + " ");
    	}
    	
    	System.out.println();
    	 
    }

    public void flip(Stack<Integer> pancakes, int pos) {
	// FILL IN THIS METHOD
    	Queue<Integer> q = new Queue<Integer>();
    	for(int i=0; i<=pos; i++) {
    		q.enqueue(pancakes.pop());
    	}
    	for (int i=0; i<=pos; i++) {
    		pancakes.push(q.dequeue());
    	}

    	
    }

    // fill in the Stack with the specified number of pancakes in a random order
    public void initialize(Stack<Integer> s, int n) {
	// create an array with the desired number of elements,
	// then initialize it with pancake sizes 1...n
	int[] order = new int[n];
	for(int i = 0; i < order.length; i++) {
	    order[i] = i + 1;
	}
	// randomly permute the order of the pancakes
	shuffle(order);

	// add all of the pancakes to the stack
	for(int i = 0; i < n; i++) {
	    s.push(order[i]);
	}
    }

    // randomly permute the elements in the input array
    public void shuffle(int[] toShuffle) {
	for(int i = toShuffle.length - 1; i > 0; i--) {
	    int which = (int)(Math.random() * (i+1));
	    int temp = toShuffle[i];
	    toShuffle[i] = toShuffle[which];
	    toShuffle[which] = temp;
	}
    }

    // command line input: number of pancakes in the original pile
    public static void main(String [] args) {
	if(args.length > 0) new Flipper().run(Integer.parseInt(args[0]));
	else new Flipper().run(5); // default is 5 pancakes

    }
    
}
    


    	
    	
    	
   