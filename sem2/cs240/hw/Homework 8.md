# Homework 8

## Question 1

Prove that in any group of $n$ people, there must be two people with the same number of friends within the group. Assume that friendship is mutual, and nobody is their own friend. 

---

**Proof by Contradiction**

Assume that there are no two people with the same number of friends within the group. That means there can be someone who is friends with everyone else in the group, and someone who is friends with no one, in a group of 2. This is impossible, since friendship is mutual. Therefore, there is always two people with the same number of friends within the group. 

## Question 2

Let $n \geq 2$ and let $G$ be a graph with $n$ vertices. 

(a) Prove that any path in $G$ has length at most $n - 1$. 

A path is a walk where all edges and vertices are distinct. Therefore, you cannot go backwards a vertex, since it would invalidate the path definition. Therefore, all paths have a length at most $n - 1$. 

(b) Use the extremal principle to prove that if there is a walk between distinct vertices $u$ and $v$, then there is a path between $u$ and $v$. 

The extremal principle tells us to pick examples with extreme properties. Therefore, say you have a vertex $u$ that is one edge away from the vertex $v$. Since there are no repeated vertices in this walk, this also qualifies as a path. Since the minimum walk qualifies as a path, any walk between $u$ and $v$ will qualify as a path. 

## Question 3 

Prove that the "bowtie" graph on slide 29 in 07_graphs.pdf is not 2-vertex-connected and not 3-edge-connected. 

---

If you take away vertex 1, the graph will not be connected. Therefore, the graph is not 2-vertex-connected. 

If you take away the edge between vertex 1 and 5 and the edge between vertex 1 and 4, the graph will not be connected. Therefore, the graph is not 3-edge-connected. 

## Question 4

Determine whether the graph has an

(a) Euler trail

A Euler trail is a trail which contains every edge and every vertex (can be multiple times). 
$$
v_1 \rightarrow v_5 \rightarrow v_2 \rightarrow v_6 \rightarrow v_3 \rightarrow v_1 \rightarrow v_4 \rightarrow v_5 \rightarrow v_6
$$
This graph has an Euler trail. 

(b) Euler circuit

This graph does not have Euler circuit, since you always have to go back to go back to the beginning vertex. 

## Question 5

Suppose you are given a graph $G$. All you know about it is that it is connected, it has 6 vertices, and it has 14 edges. 

(a) If $G$ were to be planar, how many regions would it have? 

$G$ would have 10 regions. 

(b) Prove that $G$ cannot be planar. 

$G$ can only be planar if $m \leq 3n - 6$. 14 is not less than or equal to than 3. 

## Question 6

For $k \geq 3$, let $C_k$ denote a cycle graph with $k$ vertices. 

(a) How many edges are there in $C_k$? 
$$
k - 1
$$
(b) Prove that if $k$ is even, then $C_k$ has chromatic number 2. 

If $k$ is even, you can color every vertex one color and every other vertex another color. 

(c) Prove that if $k$ is odd, then $C_k$ has chromatic number 3. 

If $C_k$ has an odd amount of vertices, say 3, it cannot be colored with 2 colors. Therefore, $C_k$ has chromatic number 3 if $k$ is odd. 

## Question 7

(a) Let $T$ be a (free) tree with at least two vertices. Prove that if $l$ is a leaf in $T$, then $T - \{l\}$ is still a tree. 

Since trees' vertices only have 1 branch connecting vertices, removing a leaf will still result in a tree, since you're only removing the branch that connects it to another leaf, therefore not changing the structure of the tree at all. 

(b) Prove by induction on $n \geq 1$ that if a (free) tree $T$ has $n$ vertices, then it has exactly $n - 1$ edges. (Use (a) and the theorem from lecture about leaves in trees.) 

Assume a free tree $T$ has 1 vertex. Therefore, it will have 0 edges. The base case passes. 

**Inductive Hypothesis**: Assume that tree $T$ has exactly $k - 1$ edges for $k$ vertices. We will prove that $T$ has $k$ edges for $k + 1$ vertices.

Adding a vertex to a tree adds an edge to the tree. 



