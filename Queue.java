public class Queue<E> {

	
	private int[] list = new int[30];
	private int end = 0;
	private int start = 0;
	
	

	
	public void enqueue (int toadd) {
		if(end==list.length)
			System.out.println("Can't add anymore");
		else {
			list[end]= toadd;
			end++;
			if (end >= 0.7 * list.length) {
				int[] list2 = new int[2*list.length];
			
				for(int i=0; i<end; i++) {
					for(int j=0; j<list2.length; j++) {
						list2[j] = list[i];
					}
				}
			}
		}
	}
		
	
	
	public int dequeue() {
		if (start == end) {
			int ret = list[start];
			list[start] = Integer.MAX_VALUE;
			start++;
			return ret;
		}
		else {
			start++;
			return list[start-1];
		}	
	}
	
	public int peek() {
		if (start-end==0) 
			throw new ArrayIndexOutOfBoundsException("There's nothing to see");
		else
			return list[start];
	}
	
	public int size() {
		return end;
	}
	
	public boolean isEmpty() {
		return end==0;
	}
	

}
