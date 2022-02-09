# Homework 1

### Problem 1

Show that $\forall x P(x) \or \forall x Q(x)$ and $\forall x (P(x) \or Q(x))$ are not logically equivalent, in general. 

---

Say you have predicates $P(x)$ and $Q(x)$ such that 
$$
P(x): x\ \text{is even} \\
Q(x): x\ \text{is odd} \\
$$
For the first expression, $\forall x P(x) \or \forall x Q(x)$, the expression would be false, since $\forall x$ for each expression includes both even AND odd numbers, which is impossible, since a number cannot be even or odd at the same time. 

For the second expression, $\forall x (P(x) \or Q(x))$, the expression would be true, since this expression allows both even AND odd numbers to be included in a true result. 

Therefore, since these two expressions have a different result for the same predicates, they are not logically equivalent. ∎

### Problem 2

Prove that if $x$ is a nonzero rational number and $y$ is irrational, then their product $xy$ is irrational. 

---

We will use **proof by contrapositive**. 

We can write the above expression as 
$$
\forall x \forall y (R(x) \and \lnot R(y) \implies Q(x,y))
$$
where 
$$
R(x): x \in \mathbb{Q}, x \neq 0 \\
Q(x,y): xy \in \mathbb{P}
$$
turning that into a contrapositive, 
$$
\forall x \forall y(\lnot Q(x,y) \implies \lnot R(x) \or R(y))
$$

$$
\forall x \forall y(Q(x,y) \or (\lnot R(x) \or R(y))
$$

The second part of the expression is a tautology; this expression is true. Therefore, if the contrapositive is true, the original expression is true as well. ∎

### Problem 3

Disprove the following statement: If $x$ and $y$ are irrational, then $x + y$ is irrational. 

---

We will use **proof by counterexample**. 

Say $x$ = $\pi$ and $y = 4 - \pi$. 
$$
x + y = \pi + 4 - \pi = 4
$$
Since 4 is rational, the statement is false. ∎

### Problem 4

Assume that $n$ is an integer. Prove that if $3n + 2$ is even, then $n$ is even as well. 

---

We will use **proof by contrapositive**. 

The contrapositive of this expression is 
$$
\text{If $n$ is odd, then $3n+2$ is odd. }
$$
Therefore, $n = 2k + 1$, for some integer $k$. Substituting that in $3n + 2$, 
$$
3(2k + 1) + 2 = 6k + 5 =  2(3k + 2)  + 1
$$
Making $3k + 2 = k'$ , 
$$
3n + 2= 2k' + 1
$$
where $k' \in \mathbb{Z}$. Therefore, $3n + 2$ is odd. If the contrapositive of the expression is true, then the original expression is true as well. ∎





