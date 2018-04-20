package bcnf_decomposition;
import java.util.ArrayList;
public class relation {
	
	private final ArrayList<Character> relation = new ArrayList<Character>();
	private int next = 0;
	private int head = 0;

	
	
	// read in string relation
	public void addAttribute(Character a) {
		relation.add(a);
	}
	public void add(String a) {
		a = a.replaceAll("\\s+","");
		for (int i = 0; i < a.length(); i++) {
			if (! this.contains(a.charAt(i))) { //make sure attributes in relation is unique
				relation.add(a.charAt(i));
			}
		}
	}
	
	
	// toString
	public String toString() {
		String s = "";
		for(int i = 0; i < relation.size(); i++) {
			s += relation.get(i).toString();
		}
		return s;
	}
	
	// size
	public int size() {
		return relation.size();
	}
	
	// get
	public char get(int i) {
		return relation.get(i);
	}
	
	// equals
	public boolean equals(relation r2) {
		if (this.size() != r2.size()) return false;
		for (int i = 0; i < this.size(); i++) {
			if (!r2.contains(this.get(i))) return false;
		}
		return true;
	}
	
	// contains
	public boolean contains(char c) {
		
		return (relation.contains(c));
	}
	
	// subset
	public boolean subset(relation r2) {
		if (r2.size() > this.size()) return false;
		for (int i = 0; i < r2.size(); i++) {
			if (! this.contains(r2.get(i))) return false;
		}
		return true;
	}
	
	// union
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! make it inplace !!!!!!!!!!!!!!!!!!!
	public relation union(relation r2) {
		this.add(r2.toString()); //add function has distinct value guaranteed
		return this;
	}
	
	// an non-inplace union
//	public relation union(relation r2) {
//		relation re = new relation();
//		re.add(this.toString());
//		re.add(r2.toString());
//		return re;
//	}
//	
	
	// intersect
	public relation intersect(relation r2) {
		relation inter = new relation();
		for (int i = 0; i < r2.size(); i++) {
			if(this.contains(r2.get(i))) inter.addAttribute(r2.get(i));
		}
		return inter;
	}
	
	// check empty
	public boolean isEmpty() {
		return(relation.isEmpty());
	}
	
	// powerSet
	// found pseudocode from:
	// https://stackoverflow.com/questions/15498281/generating-power-set-recursively-without-any-loops
	// easy to understand. just need to change string to relation here.
	//-------------------------------------------------------------------
	//	powerset(string) {
	//		  add string to set;
	//		  for each char in string {
	//		   let substring = string excluding char,
	//		   add powerset(substring) to set
	//		  }
	//		  return set;      
	//		}
	//-------------------------------------------------------------------
	 

	// remove character from a relation
//	public relation remove(char c) {
//		for(int i = 0; i < relation.size(); i++) {
//			if (relation.get(i) == c) relation.remove(i);
//		}
//		return this;
//	}
	// this method is in-place, cause problem in recursion, 
	// we don't want to change the origin relation
	public relation remove(char c) {
		int num = 0;
		for(int i = 0; i < relation.size(); i++) {
			if (this.get(i) == c) num = i;
		}
		String s = "";
		for (int i = 0; i < num; i++) {
			s += this.get(i);
		}
		for (int i = num + 1; i < relation.size(); i++) {
			s += this.get(i);
		}
		relation a = new relation();
		a.add(s);
		return a;
	}
	
	
	public relList powerSet() {
		relList rlist = new relList();
		relation r = this;
		powerset1(r,rlist);
		return rlist;
	}
	
	public void powerset1(relation r, relList rlist) {
		if (r.isEmpty()) return;
		rlist.insert(r);
		char[] char_arr = r.toString().toCharArray();
		for (char i : char_arr) {
			relation sub_r = r.remove(i);
			powerset1(sub_r,rlist);
		}	
		return;
	}
	
	public relation powerSetFirst() {
		relList rlist = this.powerSet();
		relation powerHead = rlist.get(head);
		return powerHead;
	}
	public relation powerSetNext() {
		if(this.hasNext()) {
			relList rlist = this.powerSet();
			next ++;
			relation powerNext = rlist.get(next);
			return powerNext;
		}else return null;

	}

	// has Next
	public boolean hasNext() {
		if (next > this.size() - 2) return false;
		else return true;
	}
	
	// test drive
	public static void main(String[] args) {
		relation r1 = new relation();
		r1.add("abcd");
		relation r2 = new relation();
		r2.add("ae");
		System.out.println("r1 "+r1.toString()+"; r2 "+r2.toString());
		System.out.println("r1 == r2 "+r1.equals(r2));
		System.out.println("r2 contains a "+r2.contains('a'));
		System.out.println("r1 subset r2 "+r1.subset(r2));
		System.out.println("r1 union r2 "+r1.union(r2));
		System.out.println("r1 intersect r2 "+r1.intersect(r2));
		
		//test powerSet
		relation r3 = new relation();
		r3.add("abcd");
		relList power = r3.powerSet();
		System.out.println("r3(abcd) power set "+power.toString());
		System.out.println("first power set "+r3.powerSetFirst());
		System.out.println("Next power set "+r3.powerSetNext());
		System.out.println("Next power set "+r3.powerSetNext());
		System.out.println("Next power set "+r3.powerSetNext());
		
		
		
		
//		//test remove--------------------------------------------------
//		relation r4 = new relation();
//		relation r5 = new relation();
//		r4.add("abcd");
//		r5 = r4.remove('c');
//		System.out.println("r4: "+r4.toString()+" r5: "+r5.toString());
//		//-------------------------------------------------------------
		
		
	}

}
