# Homework 4

## Question 1

Use induction to prove that $n^2 - 5n$ is even, for every $n \in \mathbb{N}$. 

---

Try base case first: 

For $n = 1$, 
$$
1 - 5 = -4,
$$
which is even. 

Let $k \in \mathbb{N}$ and suppose the expression is true for $n = k$. Then, 
$$
k^2 - 5k \text{ is even.}
$$
We must prove that $(k +1)^2 - 5(k + 1)$ is even as well. 
$$
k^2 +2k + 1 -5k -5 = k^2-3k-4
$$

$$
= (k^2 - 5k) + (2k - 4)
$$

Since $2k - 4$ always ends in an even number, and adding two even numbers always results in an even number, the expression works for $(k + 1)$. Therefore, the expression is true. $\blacksquare$ 

## Question 2 

Define a sequence $\{a_n\}_{n \in \mathbb{N}}$ by 
$$
a_0 = 0 \text{ and } a_n = a_{n -  1} + n^3 \text{ for } n \geq 1
$$
Prove that for each $n \in \mathbb{N}$, 
$$
a_n = \left(\frac{n(n + 1)}{2}\right)
$$

---

Try base case first: 
$$
a_0 = \left(\frac{0 (0 + 1)}{2}\right) = 0 \ \ \checkmark
$$
Let $a_k \in \{a_n\}$ and assume the expression is true for $a_k$. We must prove that 
$$
a_{k + 1} = \left(\frac{(k + 1)(k + 2)}{2}\right) \\ 
= \frac{k^2 + 3k + 2}{2} \\ 
= \frac{k^2 +k + 2k + 2}{2} \\ 
= \frac{k(k + 1)}{2} + k + 1 \\ 
a_{k + 1} = a_k + k + 1 \ \ \checkmark
$$

## Question 3

(a) For each positive integer $n \leq 4$, compute whether $n! \geq n^2$ or not. 

---

For $n = 4$, $4! \geq 4^2$. 

For $n = 3$, $3! < 3^2$. 

For $n = 2$, $2! < 2^2$. 

For $n = 1$, $1! = 1^2$. 

(b) Prove that for all integers $n \geq 4$, we have $n! \geq n^2$.  

---

We have already proved the base case for $n = 4$ above. 

Assume that $k = n$, and $k! \geq k^2$. 

We must prove that $(k + 1)! \geq (k + 1)^2$. 
$$
(k + 1) \times k! \geq (k + 1)(k + 1) \\ 
k! \geq (k + 1)
$$
Since this is true, the expression is true as well. 

## Question 4

The post office sells an unlimited amount of 2-cent stamps and 5-cent stamps. Prove that for any integer $n \geq 4$, we can buy exactly $n$ cents worth of stamps. 

---

For base case $n = 4$, 
$$
5(2) - 3(2) = 4 \ \ \checkmark
$$
Inductive hypothesis: 

Any value $j$ ($k \geq j \geq 4$) can be expressed as $j = 2a + 5b$ with $a$ and $b$ being non-negative integers. 

We must prove that we can express $k+1$ as $2a+5b$. We can use $k-1$ since $k$ works for the inductive hypothesis. 
$$
k - 1 = 2a+5b \\
k -1 + 2 = 2a+5b + 2 \\ 
k + 1 = 2(a+1) + 5b \ \ \checkmark
$$


By the principle of strong induction, the statement is true for every $n$ greater than 4. 

## Question 6

Let $k = 1$. $4 \times 1 + 1$ is 5. 

Given that A starts, 

5 A - 2 --> 3 B - 2 --> 1 A - 1 = 0, so $B$ won the game. Let it be true for some $n$ that $4n + 1$. Therefore, we have to show that $n + 1$ is true. 
$$
4(n + 1) + 1 =4n + 1 + 4
$$
We know that if B removes the stone the last stone is 1 for $4n$ stones are left after $B$ remove the stone. 
$$
1 + 4 = 5
$$
Now we have already shown if 5 is the number that $B$ will win. 

Therefore, we show that for $4k + 1$, $k \in N$, $B$ will always win the game. 





