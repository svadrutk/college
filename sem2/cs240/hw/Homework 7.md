# Homework 7 

## Question 1

Solve the following recurrence relations. 

(a) $g_0 = 3, g_1 = 6$, and $g_{n- 1} + 6g_{n - 2}$ for $n \geq 2$. 
$$
g_n = g_{n - 1} + 6g_{n - 2} \\
x^n = x^{n - 1} + 6x^{n - 2} \\ 
\fbox{$x^2 = x + 6$}
$$
(b) $g_0 = 0, g_1 = 6$, $g_2 = 2$ and $g_n = 3g_{n - 1} + 4g_{n - 3}$ for $n \geq 3$. 
$$
g_n = 3g_{n - 1} + 4g_{n - 3} \\ 
x^n = 3x^{n - 1} + 4g^{n - 3} \\ 
\fbox{$x^3 = 3x^2 + 4$}
$$
(c) $g_0 = -11/8,g_1 = 1, g_2 = 2,$ and $g_n = 6g_{n - 2} - g_{n - 1} + 2n$ for $n \geq 2$. 
$$
g_n = 6g_{n - 2} - g_{n - 1} + 2n \\ 
x^n = 6x^{n - 2} - x^{n - 1} + 2n \\ 
\fbox{$x^2 = 12 - x$}
$$

## Question 2

Prove that there is no graph which has 7 vertices which have degrees 1,1,2,3,3,3,6. 

The handshake lemma implies that every graph $G$ has an even number of vertices of odd degree. 
$$
1 + 1 + 2  +3 +  3 + 3 + 6 = 19
$$
Therefore, this graph must have an even number of vertices. This graph does not exist. 

## Question 3

Suppose a graph has 7 vertices, which have degrees 1,1,2,3,3,4,6. How many edges are there in this graph? 

The handshake lemma says that if a graph $G$ has $m$ edges, then 
$$
\sum_{v \in V} \text{deg}(v) = 2m
$$

$$
1 + 1 + 2 +  3 + 3 + 4 + 6 = 20 \\
20 / 2 = \fbox{10}
$$

## Question 4

In this question, we investigate a possible definition of the difference of two graphs. For graphs $G = (V, E)$ and $G' = (V', E')$, define 
$$
G - G' = (V -V', E - E')
$$
(This definition is only for this question, don't use it anywhere else! 

Give an example to show that this is a "bad" definition, i.e. write down graphs $G$ and $G'$ such that $V - V'$ and $E - E'$ are nonempty, and $G - G'$ is not a graph. Be sure to explain why $G - G'$ is not a graph. 

Assume graph $G$ has 5 edges and graph $G'$ has 3 edges. Therefore, graph $G$ has 10 edges and graph $G'$ has 6 edges. $G - G'$ has 4 edges. Since the lemma implies that this has 8 degrees total, $G - G'$ is not a graph. 

## Question 5

For a graph $G = (V, E)$ and a subset $V'$ of $V$, define
$$
G - V' = (V - V', \{\{u,v\}\in E: u, v \notin V'\})
$$
i.e. remove all vertices in $V'$ from $V$, and remove all edges which have at least one endpoint in $V'$ from $E$. (For example, if $v$ is a vertex in $G$, then $G - \{v\}$ is the graph obtained by removing $v$ as well as any edge incident with $v$.) 

(a) Prove that $G - V'$ is a graph. In your solution, be sure to point out where you're using the assumption that $G$ is a graph. 

Assume $G$ is a graph. Then, if you remove all vertices in $V$ and include edges that connect to this vertex, the resulting graph will simply be a subgraph of $G$. 

(b) Prove that $G - V'$ is a subgraph of $G$. 

See above. 

(c) Prove that $G - V'$ is an induced subgraph of $G$. 

An induced subgraph is another graph formed from a subset of the vertices of the graph and ALL edges connecting pairs of vertices in that subset. Since you are removing both edges and vertices that are part of the original subgraph. $G - V'$ is an induced subgraph. 

## Question 6

In a graph a cycle is defined to be a circuit of length at least 1 with no repeated vertex except the first and last vertex. Prove that in a bipartite graph, there cannot be a cycle of odd length

If a graph $G$ is bipartite with vertex sets $V_1$ and $V_2$, every step takes you from $V_1$ to $V_2$ or from $V_2$ to $V_1$. Therefore, to end up where you started from, you must always take an even number of steps. 

## Question 7

Suppose a graph $G = (V,E)$ has $n$ vertices and $m$ edges. Further suppose that $m < n$ and every $v \in V$ has degree at least 1. 

(a) Prove that $2m \geq n$. 

The handshake lemma says that if a graph has $m$ edges, then the sum of all the degrees is $2m$. Therefore, $2m \geq n$. 

(b) Prove that there are at least $2(n - m)$ vertices which have degree exactly 1. 

Every graph $G$ has an even number of vertices of odd degree. Therefore, there are $2(n - m)$ vertices which have degree of exactly 1. 
