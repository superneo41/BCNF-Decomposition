# BCNF Decompsition
Final project for CS 539 Spring2018 @ Rutgers University

# Debug     
05/03/2018 16:39 EST    
Something is wrong in Fdlist class, causing some wrong results for some specific cases.
Fixing...   

05/03/2018 20:47 EST     
Fixed...      
I will upload later...   

05/04/2018 15:38 EST       
Upload the new Fdlist class. Modified the BCNFDecomp function. Dealed with the cases when X closure > relation r    







# Author
# Master Yi

# Log   
All APIs are implemented in 4 classes(relation, realist, fd, fdList).     

The core BCNF-Decomposition algorithm is written in fdlist class.     

Run main to read in data and get output. There are test driver in each class to test the APIs    

# APIs (NOT REALLY)
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

