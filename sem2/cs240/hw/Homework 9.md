# Homework 9 

## Question 1

Below is an invalid "proof" that if $R$ is a relation on a set $A$ which is symmetric and transitive, then $R$ is reflexive. 

"Proof" 

i. Suppose $a \in A$. 

ii. Take an element $b \in A$ such that $(a,b) \in R$. 

iii. Because $R$ is symmetric, we also have $(b,a) \in R$. 

iv. Because $R$ is transitive, $(a,b) \in R$, and $(b,a) \in R$, we have $(a,a) \in R$, as desired. 

Explain what is wrong with the above. 

---

Step 2 is wrong, since you wrongfully assume that there is another element that exists which will create a relation. 

---

Write down an example of a nonempty relation $R$ on $\{1,2,3\}$ which is symmetric and transitive, but not reflexive. 
$$
(1,1), (1,2), (2,1), (2,2)
$$
This is not reflexive because 3 is not in $R$. 

## Question 2

Let $S$ be the relation on $\mathbb{R}$ defined by $(x,y) \in S$ if and only if $x - y$ is rational. Is $S$ symmetric? Anti-symmetric? Reflexive? Anti-reflexive? Transitive? Justify your answers with proofs. 

(i) 
$$
(x,x) \in S
$$
Since $x -x = 0$ is a rational number, $(x,y)$ is a reflexive relation. 

(ii) 
$$
(x,y) \in S
$$
Take $x = 2, y = 1$. 
$$
x - y = 2 - 1 = 1
$$
is a rational number. However, when we try $(y,x) \in S$: 
$$
y - x = 1 - 2 = -1
$$

$$
(x,y) \neq (y,x)
$$

Therefore, it is not a symmetric relation. 

(iii) 

Say $(x,y) \in S$, $(y, z) \in S$. That means $(x,z) \in S$ is a rational number. 

Take $x = 2, y = 1, z = 3$. 
$$
x - y = 2 - 1 = 1 
$$
is rational. 
$$
y - z = 1 - 3 = -2
$$
is rational. 
$$
x - z = 2 - 3 = -1
$$
is rational. Therefore, $(x,y) \in S$ is transitive. 

(iv) 
$$
(x,y) \in S
$$
$x - y$ is rational. 
$$
(y, x) \in S
$$
$y - x$ is rational. However, $x \neq y$. Therefore the relation is not anti-symmetric. Finally the relation is reflexive, so it is not anti-reflexive. 

## Question 3

Suppose $R$ is a strict order on a set $A$. Prove that $R^{-1}$ is a strict order on $A$. 

A strict order is a relation which is both transitive and anti-reflexive. 

(i) 

Assume that $(x,y) \in R^{-1}$ and $(y,z) \in R^{-1}$.  Then by definition, you have $(y,x) \in R$ and $(z,y) \in R$. Since $R$ is transitive, $(z,x) \in R$ and then $(x,z) \in R^{-1}$. 

(ii) 

Assume that $R$ is anti-reflexive. Then, $(x,x) \notin R$. Assume $R^{-1}$ is reflexive. Then, $(x,x) \in R^{-1}$. Then, $(x,x) \in R$. This is a contradiction, since $R$ is supposed to be anti-reflexive. Therefore, $R^{-1}$ is anti-reflexive. 

## Question 4

Prove or disprove: If $R$ is a transitive relation on a set $A$, then $R \circ R$ is transitive. 

Let $q \in R \circ R$ be arbitrary. Then, $q = (x,y) \in A$. There is a $z \in A$ such that $(x, z) \in R$ and $(z,y) \in R$. Since $q$ is arbitrary. $R \in R$ is transitive. 

## Question 5

Prove that if $R$ is a symmetric relation on a set $A$ then $R^+$ is symmetric as well. 

Let $(a,b) \in R$. That means $(b,a) \in R$. This means $(a,b) \in R^+$ as well, since $R \in R^+$. 

Let $(x,y)$ be an arbitrary element of $R^+$. Then, 
$$
(x,y) \in R^+, (y, x) \in R, (x,y) \in R
$$
Therefore, $R^+$ is symmetric. 

## Question 6

Write down a topological sort of the following DAG. 

3 2 6 4 1 5

## Question 7 

Prove that if $G$ is a DAG and _ is a strict order, then _ is a topological sort for $G$ iff _ is a topological sort for $G^+$. 

Since $G^+ \in G$, ≺ is a topological sort for both. You can say the same thing vice versa. 

## Question 8

Let $G = (V,E)$ be a nonempty DAG. Our goal is to construct a topological sort for $G$. 

(i) For each $v \in V$, we define $G - \{v\}$ to be the directed graph with vertex set $V - \{v\}$ and edge set $\{(u,w) \in E: u, w \neq v\}$. Prove that $G - \{v\}$ is acyclic. 

Since this removes all edges with the vertex as well as the vertex itself, $G$ remains a DAG. Therefore, $G$ is acyclic. 

(ii) 

Prove that in G, there is a vertex v such that there are no edges from any vertex u to v. 

Since $G$ is a DAG, that means there has to be at least one vertex where no edges go to it. 