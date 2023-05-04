# Homework 3

## Problem 1

We attempt to define a function $f : \mathbb{Q}^+ \rightarrow \mathbb{Z}$ by 
$$
f(x) = p
$$
where $x = p/q$ with $p$ and $q$ being positive integers. 

Show that $f$ is **not** well-defined. 

---

This is false. Here is a counterexample: 

Say you have a variable $x$, expressed as $2$, where $p$ is $4$ and $q$ is $2$. Therefore, 
$$
f(2) = 4
$$
Have another variable $x = 2$, where $p$ is $8$ and $q$ is $4$. Therefore, 
$$
f(2) = 8
$$
One element in the domain maps to more than $1$ element. Therefore, $f(x)$ is not well-defined. $\blacksquare$

## Problem 2

Suppose $f : A \rightarrow B$ is a function which is one-to-one. Suppose $C_1$ and $C_2$ are subsets of $A$. Prove that 
$$
\{ f(x): x \in C_1\} \cap \{f(x) : x \in C_2 \} \subseteq \{f(x) : x \in C_1 \cap C_2\}
$$

---

 Let 
$$
y \in \{f(x): x \in C_1\} \cap \{f(x): x \in C_2\} \\ 
y \in \{f(): x \in C_1\}, y \in \{f(x): x \in C_2\} \\ 
y = f(x_1) \text{ for }x_1 \in C_1, y = f(x_2) \text{ for some }x_2 \in C_2 \\
f(x_1) = f(x_2) 
$$
Since $f$ is one-to-one, $f(x_1) = f(x_2) \rightarrow x_1 = x_2$ 

Since $x_1 \in C_1$ and $x_2 \in C_2$ and $x_1 = x_2$: 
$$
x_1 = x_2 = C_1 \cap C_2 \\
y \in f(x_1), x_1 \in C_1 \cap C_2 \\
y \in \{f(x) : x \in C_1 \cap C_2\} \blacksquare
$$


## Problem 3

Throughout this question, we fix a set $X$. 

1. Suppose $A \in P(x)$. Prove that $A = X - (X - A)$. 

Since you are first removing the elements that are in $A$ from set $X$, then subtracting this from $X$ again, you are left with $A$. 

2. Prove that for any set $A$, we have $X - A \in P(x)$

Since $P(x)$ is all the subsets of $X$, simply subtracting any amount of values from set $X$ will still result in another subset inside $P(x)$. 

3. Define $f : P(x) \rightarrow P(x)$ by $f(A) = X - A$. Prove that $f$ is well-defined. 

The definition of well-defined is if every element of the domain is mapped to an element in the target. Since we proved that $X - A \in P(x)$, we know that $f$ is injective. Therefore, $f$ is well-defined. 

4.  Prove that $f$ is one-to-one. 

See above. 

5. Prove that $f$ is onto. 

Since $X - A$ is always in the range, it can always be expressed in terms of the domain. Therefore, the function is onto. 

6. We know that $f^{-1}$ is a function. Prove that $f^{-1}$ equals $f$. 

Since $f: P(x) \rightarrow P(x)$, $f^{-1}$ must also be $P(x) \rightarrow P(x)$. Therefore, $f^{-1} = f$. 

## Problem 4

Suppose that $A, B, C, D$ are sets such that $B \subseteq C$. Suppose $g : A \rightarrow B$ and $f : C \rightarrow D$ are functions. Prove that if $f \circ g$ is onto, then $f$ is onto. 

---

Let $d \in D$. Then since $f \circ g$ is onto, there exists $a \in A$ such that $(f \circ g) a = f(g(a)) = d$. 

Therefore, if we let $y = g(a) \in B$, then $f(y) = c$. Thus, $f$ is onto. $\blacksquare$ 

