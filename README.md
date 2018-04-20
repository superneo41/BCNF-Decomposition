# BCNF-Decompsition

BCNF Decomposition   

All algorithms are implemented in 4 classes(relation, realist, fd, fdList).   
run main to read in data and get output.   


1. Relation class: A relation is a set of attributes and can be implemented easily using and array A of integers with values of 0 or 1, where A[i]=1 means that the attribute with ASCII (UNICODE) code i is in the relation.   
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
• void insert(Relation r) • Relation getFirst()    
• Relation getNext()    

