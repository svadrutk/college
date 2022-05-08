# Homework 6

## Question 1

Prove that $n^3 +3n + 4$ is O($2n^3$). 

---

Since the highest degree in this equation is $n^3$ and O($2n^3$) can be simplified to O($n^3$), this statement is true. 

## Question 2

Prove that $n^{3/2}$ is not O(n). 

---

Since 3/2 > 1, and O(n) only works for equations that have a degree of 1, $n^{3/2}$ is not O(n). 

## Question 3

Consider the following recursive algorithm Square($n$), which takes as input $n \in \mathbb{N}$: 

​	If $n = 0$, output 0. 

​	Otherwise, output Square($n - 1$) + $n + n - 1$. 

Use induction to prove that for all $n \in \mathbb{N}$, Square($n$) terminates and outputs $n^2$. 

**Base Case**: If we substitute $n = 1$, 
$$
\text{Square}(0) + 1 + 1 - 1 = 0 + 2 - 1 = 1
$$
Since $1^2 = 1$. 

**Inductive Step**: Assume that for $k \geq 0$, Square($k$) returns $k^2$ for all $k \in \mathbb{N}$. Then we will prove that Square($k+1$) returns $(k + 1)^2$ for all $k \in \mathbb{N}$. 
$$
\text{Square}(k + 1) = \text{Square}(k) + 2k + 1
$$

$$
\text{Square}(k) = \text{Square}(k) + \text{Square}(k - 1) + ... + \text{ Square}(1) = k^2
$$

$$
\text{Square}(k + 1) = k^2 + 2k + 1 = (k + 1)^2 \ \ \blacksquare
$$

---

Let $T(n)$ denote the number of additions or subtraction operations performed by Square($n$). Write down a recurrence relation for $T(n)$.
$$
3 \cdot (n - 1)
$$

 ## Question 4

Use strong induction to prove that for all lists $L$ of positive integers, LocalMin($L$) terminates, and its output (denoted by $B$) satisfies the following: Either $b = 0$ and $L$ has no local minimum or $b \neq 0$ and $b$ is a local minimum of $L$. 

---

**Base Case**: We must prove that LocalMin works for $L = 2$ and $L = 3$. 

If $L$ has length 2 or less, it outputs zero. $\checkmark$ 

If $L$ has length 2 or less, it will output zero or a local minimum. $\checkmark$ 

**Inductive Step**

Assume that for $k \geq 2$, LocalMin($k$) is true. Then, we will try to prove that LocalMin($k+1$) returns a local minimum or no local minimum. 

---

Write down a recurrence relation for $T(n)$. 

---

$$
2T(|L|/2) + \theta(1)
$$





