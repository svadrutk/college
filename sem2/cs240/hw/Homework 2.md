# Homework 2

## Problem 1

Prove that if $A$ and $B$ are sets, then 
$$
(A \ \oplus \ B) \ \oplus \ B = A
$$

---

I will prove this using a **proof by cases**. 

Say that $A$ is an empty set ($\phi$) and $B$ is a non-empty set. 
$$
A \ \oplus \ B = B
$$

$$
B \ \oplus \ B = \varnothing
$$

$\varnothing = A$, so the equation works for this combination. 

Say that $A$ is a non-empty set and $B$ is an empty set ($\varnothing$). 
$$
A \ \oplus \ B = A
$$

$$
A \ \oplus \ B = A
$$

$A = A$, so the equation works for this combination. 

Say that both $A$ and $B$ are non-empty sets, with $A = \{1,2,3,4\}$ and $B = \{5,6\}$. 
$$
\{1,2,3,4\} \ \oplus \ \{5,6\} = \{1,2,3,4,5,6\}
$$

$$
\{1,2,3,4,5,6\} \ \oplus \ \{5,6\} = \{1,2,3,4\}
$$

$\{1,2,3,4\} = A$, so the equation works for this combination. 

Say that both $A$ and $B$ are empty sets. 
$$
\varnothing \ \oplus \ \varnothing = \varnothing
$$

$$
\varnothing \ \oplus \ \varnothing = \varnothing
$$

$\varnothing = A$, so the equation works for this combinations. 

Since the equation works for all combinations, this statement is true. $\blacksquare$ 

## Problem 2

Suppose $A$ and $B$ are subsets of $C$. Prove that $A \subseteq B$ if and only if $(C - A) \cup B = C$. 

---

We will break this up into two proofs. 

The first proof can be written as 
$$
\text{If }(C - A) \cup B = C, \text{then } A \subseteq B.
$$
Since you first subtract the elements in $A$ from $C$ and then add the elements in $B$ to get the same original set, it follows that $A$ and $B$ are made up of the same elements. Therefore, $A \subseteq B$. 

The second proof can be written as 
$$
\text{If } A \subseteq B, \text{ then } (C - A) \cup B = C
$$
If $A \subseteq B$, you can substitute one set for the other. 
$$
(C - B) \cup B = C \\ 
C = C
$$
Therefore, the statement is true. $\blacksquare$

## Problem 3

Prove or disprove each of the following statements. 
$$
\text{For every set }A\text{, we have } A \in P(A). \\
\text{For every set }A\text{, we have }A \subseteq P(A). 
$$

---

The definition of a power set is a combination of all the subsets of a set. This includes the original elements in the set. Therefore, $A \in P(A)$. 

However, the second statement is not true. Say we have a set $A = \{1,2,3\}$. The power set is $\{\{\}, 1,2,3, \{1,2\}, \{1,3\}, \{2,3\}, \{1,2,3\}\}$. This is not the same elements as $A$. The statement is false. $\blacksquare$

## Problem 4

Suppose $A$ is a subset of $B$. Prove that $P(A) \subseteq P(B)$. 

---

Suppose an element $X \in P(A)$. Then, $X \subseteq A$ (since by definition, $P(A)$ is all the subsets of $A$), and thus $X \subseteq B$. Therefore, $X \in P(B)$ and $P(A) \subseteq P(B)$. $\blacksquare$ 

## Problem 5

Suppose $A, B, C$ are sets such that $A$ is **nonempty**. Prove that $B \subseteq C$ if and only if $A \cross B \subseteq A \cross C$. 

---

Let an element $a \in A$. If $x \in B$, then $(a,x) \in A \cross B$. Then $(a,x) \in A \cross C$, so $x \in C$. Thus, $B \subseteq C$. $\blacksquare$

