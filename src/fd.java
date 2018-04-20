package bcnf_decomposition;

public class fd {
	
	private final relation lhs;
	private final relation rhs;
	public fd() {
		this(null,null);
	}
	public fd(relation left, relation right) {
		this.lhs = left;
		this.rhs = right;
	}
	
	//getLHS
	public relation getLHS() {
		return this.lhs;
	}
	//getRHS
	public relation getRHS() {
		return this.rhs;
	}
	//to string
	public String toString() {
		String s ="";
		s = s + this.lhs.toString() + "-->" + this.rhs.toString();
		return s;
	}
	
	// BCNF Violation 
	// the one returns fdlist of BCNF is written in fdList
	public boolean BCNFvio(fdList fdlist, relation r) {
		relation closure = fdlist.closure(this.getLHS());
		if (closure.size() > r.size()) return false;
		if (!closure.equals(r)) return true;
		return false;
	}

	
	
	
	// test drive
	public static void main(String[] args) {
		relation r1 = new relation();
		relation r2 = new relation();
		r1.add("ab");
		r2.add("cd");
		fd fd1 = new fd(r1,r2);
		System.out.println(fd1.toString());
		System.out.println(fd1.getLHS().toString());
		

	}

}
