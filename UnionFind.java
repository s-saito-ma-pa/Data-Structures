public class UnionFind {

	public Cell makeset (Cell c) {
		LLAddOnly llao = new LLAddOnly();
		llao.add(c);
		return c;
	}

	public LLAddOnly find (Cell c) {
		return c.head;
	}

	public void union (Cell c1, Cell c2) {
		if(c1.head == c2.head)
			return;
		
		//c1.head.last.next = c2.head.first;

		Cell c2First = c2.head.first;
		Cell c2FirstNext = c2First.next;
		while(c2First != null) {
			
			c1.head.add(c2First);
     		c2First = c2FirstNext;
				
			if(c2FirstNext != null) {
				c2FirstNext = c2FirstNext.next;
			}
			else 
				break;
			}
	}
}