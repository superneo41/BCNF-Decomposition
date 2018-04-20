package bcnf_decomposition;

import java.util.ArrayList;

public class relList {
	
	// maybe the instructor wants us to use ListNode(based on the first and Next stuff)
	// but I am not good at ListNode
	// Here I use ArrayList to implement relList
	private final ArrayList<relation> rlist = new ArrayList<relation>();	
	private int next = 0;
	private int head = 0;
	
	// insert
	public void insert(relation r) {
		if (! this.contains(r)) rlist.add(r);
	}
	
	// contains
	public boolean contains(relation r) {
		for (int i = 0; i < rlist.size(); i++) {
			if (rlist.get(i).equals(r)) return true;
		}
		return false;
	}
	
	// size
	public int size() {
		return rlist.size();
	}
	
	//get
	public relation get(int i) {
		return rlist.get(i);
	}
	
	// toString
	public String toString() {
		String s = "";
		for(int i = 0; i < rlist.size() - 1; i++) {
			s += rlist.get(i).toString();
			s += ", ";
		}
		s = s + rlist.get(rlist.size()-1);
		return s;
	}
	
	// getFirst
	public relation getFirst() {
		return rlist.get(head);
	}
	
	// getNext
	public relation getNext() {
		if(this.hasNext()) {
			next += 1;
			return rlist.get(next);
		}else return null;
	}
	
	// has Next
	public boolean hasNext() {
		if (next > this.size() - 2) return false;
		else return true;
	}
	
	

	// test drive
	public static void main(String[] args) {
		relList rlist = new relList();
		relation r1 = new relation();
		relation r2 = new relation();
		relation r3 = new relation();
		r1.add("abc");
		r2.add("ab");
		r3.add("cd");
		rlist.insert(r1);
		rlist.insert(r2);
		rlist.insert(r3);
		System.out.println("print the rlist [r1,r2,r3]");
		System.out.println(rlist.toString());
		System.out.println("print the head "+rlist.getFirst().toString());
		System.out.println("print the next "+rlist.getNext().toString());
		System.out.println("print the next "+rlist.getNext().toString());
		
		
		


	}

}
