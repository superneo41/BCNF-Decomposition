# BCNF-Decompsition

BCNF Decomposition   

All APIs are implemented in 4 classes(relation, realist, fd, fdList).    
The core BCNF-Decomposition algorithm is written in fdlist class.   
run main to read in data and get output. There are test driver in each class to test the APIs    


1. Relation class: A relation is a set of attributes  
• Relation(String in r)    
• String toString()    
• boolean equals(Relation r2)    
• boolean contains(char c)    
• boolean subset(Relation r2)    
• Relation powerSetFirst()    
• Relation powerSetNext()      
• Relation union(Relation r2)     
• Relation intersect(Relation r2)   

2. Fd class: A functional dependency is an object containing two relations (sets). One on the left hand side and one on the right hand side.     
• Fd(Relation in lhs,Relation in rhs)     
• String toString()     
• boolean BCNFviolation(Relation s)     
check if this functional dependency is a BCNF violation with respect to the given set of attributes of relation s     
• Relation getLHS()     
• Relation getRHS()     

3. FdList class: A list of functional dependencies.     
• FDList()     
• String toString()     
• void insert(Fd f)     
• Fd getFirst()     
• Fd getNext()     
• Relation closure(Relation r)     
computes the closure with respect to a list of functional dependencies     

4. RelList class: A list of relations.     
• RelList()    
• String toString()    
• void insert(Relation r)    
• Relation getFirst()    
• Relation getNext()    

