package bcnf_decomposition;

import java.io.*;
import java.util.*;

public class main {
	
	


	
	// test drive

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("/Users/NEO/Desktop/input.txt");	
		// creating relation r and fdlist------------------------
		relation r = new relation();
		fdList fdlist = new fdList();
		//-------------------------------------------------------
		
		// reading data into r and fdlist------------------------
		Scanner sc = new Scanner(file);
		String r_str = sc.nextLine();
		r.add(r_str);
		while(sc.hasNextLine()) {
			String s = sc.nextLine();
			int i = s.indexOf("-");
			String s1 = s.substring(0, i-1);
			int j = s.indexOf(">");
			String s2 = s.substring(j+1);
			relation r1 = new relation();
			relation r2 = new relation();
			r1.add(s1); r2.add(s2);
			fd fd = new fd(r1,r2);
			fdlist.insert(fd);
		}
		sc.close();
		//--------------------------------------------------------
				
		//test
		System.out.println("input data");
		System.out.println("relation: "+r.toString());
		System.out.println("functional dependencies: " +fdlist.toString());
		// non-trivial fd
		System.out.println("\n"+"Activity 1:");
		fdList nt = fdlist.NonTrivialFd();
		System.out.println("Non-trivial fd list: "+"\n"+nt.toString());
		// check BCNF Violation
		System.out.println("\n"+"Activity 2:");
		// return the BCNO Violation list
		System.out.println("BCNF Violation list:"+"\n"+fdlist.BCNFvio(r));
		// final BCNFDecomp
		System.out.println("\n"+"Activity 3:");
		System.out.println("BCNF decomp list:"+"\n"+fdlist.BcnfDecomp(r));
		
		

		

	}

}
