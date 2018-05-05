package bcnf_decomposition;

import java.util.ArrayList;

public class fdList {
	
	private final ArrayList<fd> fdlist = new ArrayList<fd>();
	private int first = 0;
	private int next = 0;
	
	
	// insert
	public void insert(fd f) {
		fdlist.add(f);
	}
	
	// size
	public int size() {
		return fdlist.size();
	}
	
	// toString
	public String toString() {
		String s = "";
		for(int i = 0; i < fdlist.size(); i++) {
			s += fdlist.get(i).toString();
			s += ", ";
		}
		return s;
	}
	
	// get
	public fd get(int i ) {
		return fdlist.get(i);
	}
	
	// has Next
	public boolean hasNext() {
		if (next > this.size() - 2) return false;
		else return true;
	}
	
	// getFirst
	public fd getFirst() {
		return this.get(first);
	}
	
	// getNext
	public fd getNext() {
		if(this.hasNext()) {
			next ++;
			return this.get(next);
		} else return null;
		//else return null;
	}
	
	// closure 
	public relation closure(relation r) {
		relation closure = new relation();
		closure.add(r.toString()); // clone relation r to keep r unchanged		
		for (int i = 0; i < 10000; i++) { // do the iteration until the closure won't change anymore
			relation r2 = closure;
			for (fd j : fdlist) {
				if(closure.subset(j.getLHS())) closure.union(j.getRHS());
			}
			if (r2 == closure) return closure;
		}
		return closure;
	}
	
	
	// non-trivial-------------------------------------------------------------------------
	public relation minus(relation r1, relation r2) {
		relation res = new relation();
		if (r1.size() > r2.size()) {
			for (int i = 0; i < r1.size(); i++) {
				if(!r2.contains(r1.get(i))) res.add(""+r1.get(i));//char to string
			}
		}else {
			for (int i = 0; i < r2.size(); i++) {
				if(!r1.contains(r2.get(i))) res.add(""+r2.get(i));
			}
		}
		return res;
	}
	
	public fdList NonTrivialFd() {
		fdList NTfdList = new fdList();
		relation r1 = this.getFirst().getLHS();
		relation NT = minus(this.closure(r1),r1);
		fd NTfd = new fd(r1,NT);
		NTfdList.insert(NTfd);
		
		while(this.hasNext()) {
			relation r2 = this.getNext().getLHS();
			relation NT2 = minus(this.closure(r2),r2);
			fd NTfd2 = new fd(r2,NT2);
			NTfdList.insert(NTfd2);	
		}
		this.next = 0; //resume next value to 0
		return NTfdList;
	}
	// non-trivial-------------------------------------------------------------------------
	
	
	// give BCNF Violation list------------------------------------------------------------
	public fdList BCNFvio(relation r) {
		fdList BCNFvio = new fdList();
		for (int i = 0; i < this.size(); i++) {
			if(this.get(i).BCNFvio(this, r)) BCNFvio.insert(this.get(i));
		}
		return BCNFvio;
	}
	// give BCNF Violation list------------------------------------------------------------
	
	
	// BCNF Decomposition------------------------------------------------------------------
	public relList BcnfDecomp(relation r) {
		relList res = new relList();
		fdList newlist = new fdList();
		// clone the fdlist, make it non-inplace
		for (int i = 0; i < this.size(); i++) {
			newlist.insert(this.get(i));
		}
		int count = newlist.size();
		BCNFDecomp(r,newlist,res,count);	
		return res;
	}
	
	public void BCNFDecomp(relation r, fdList fdlist, relList res, int count) {
		if (count == 0) return;
		for (int i = 0; i < fdlist.size(); i++) {			
			if (! fdlist.get(i).BCNFvio(fdlist, r)) {
				count --;
				if (count == 0) {
					res.insert(r);
					return;
				}
			}
			else if (fdlist.get(i).BCNFvio(fdlist, r)) {
				relation x = new relation();
				x.add(fdlist.get(i).getLHS().toString());
				relation y = new relation();
				y.add(minus(fdlist.closure(x),x).toString());
				relation z = new relation();
				z.add(minus(r,fdlist.closure(x)).toString());
				relation r2 = z.union(x);
				relation r1 = x.union(y);
				res.insert(r2);
				for(int j = 0; j < i ; j ++) {
					fdlist = fdlist.remove(fdlist.get(0));
				}
				for (int j = 0; j < fdlist.size(); j ++) {
					if (!r1.subset(fdlist.get(j).getLHS())) {
						fdlist = fdlist.remove(fdlist.get(j));
					}
				}
				r = r1;
			}
		}
		BCNFDecomp(r,fdlist,res,count);
	
	}
	
	// BCNF Decomposition------------------------------------------------------------------
	
	// Remove fd from fdlist, must be in-place for the recursion above
	public fdList remove(fd fd) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i) == fd) fdlist.remove(i);
		}
		return this;
	}
	
	// remove fd from dflist, non-inplace
//	public fdList remove(fd fd) {
//		fdList newlist = new fdList();
//		for (int i = 0; i < this.size(); i++) {
//			if(this.get(i) != fd) newlist.insert(this.get(i));
//		}
//		return newlist;
//	}
//	
	
	
	
	
	
	// test drive
	public static void main(String[] args) {
		relation r1 = new relation();
		relation r2 = new relation();
		relation r3 = new relation();
		relation r4 = new relation();
		r1.add("ab");
		r2.add("c");
		r3.add("d");
		r4.add("be");
		fd fd1 = new fd(r1,r2);
		fd fd2 = new fd(r2,r3);
		fd fd3 = new fd(r3,r4);
		fdList fdlist = new fdList();
		fdlist.insert(fd1);
		fdlist.insert(fd2);
		fdlist.insert(fd3);
		System.out.println("to string test  "+fdlist.toString());
		System.out.println("get first test  " + fdlist.getFirst().toString());
		System.out.println("get next test  "+fdlist.getNext().toString());
		System.out.println("get next test  "+fdlist.getNext().toString());
		System.out.println("closure r1  " + fdlist.closure(r1).toString());
		System.out.println("check r1 if changed  "+r1.toString());

	}

}
