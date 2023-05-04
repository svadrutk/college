# Homework 10

## Question 1

Suppose *p* and *q* are distinct prime numbers. Find the number of positive integers $x \leq pq$ such that neither $p$ nor $q$ divide $x$. 

---

$pq - 1 - q - p$ 

## Question 2

(a) What is the maximum number of edges in an undirected graph which has $n$ vertices ? 
$$
\frac{n(n - 1)}{2}
$$


(b) Find a formula for the number of undirected graphs with vertex set $\{1,2,...,n\}$ and $m$ edges, in terms of $n$ and $m$. 

Using the equation from the previous problem, we get 
$$
\frac{n(n - 1)}{2} \ \choose m
$$

 ## Question 3

(a) Describe a bijection from the set of binary strings of length $n$ to the set of binary strings of length $n + 1$ which have an even number of $1$s. 

The set of binary strings with length $n + 1$ begins with a prefix of length $n$ with an extra bit. Therefore, every element from the first set maps to the second set, since it is always included in the prefix. 

(b) How many binary strings of length $n + 1$ have an even number of 1s? 

If $n + 1$ is even: 
$$
{n + 1 \choose 2} + {n + 1 \choose 4} + ... {n + 1 \choose n+1}
$$
If $n + 1$ is odd: 
$$
{n + 1 \choose 2} + {n + 1 \choose 4} + ... {n + 1 \choose n}
$$

## Question 4

(a) How many total orders are there on $\{1,2,...,n\}$? 
$$
\fbox{$n!$}
$$
(b) How many total orders ≺ are there on $\{1,2,...,n\}$ where 1 ≺ 2? 

Since there are $n$ elements, there would be $n - 1$ total orders. 

## Question 5

We have six people (A,B,C,D,E,F) who wish to sit at a circular table with six seats. We regard two seatings as the same if each person has the same left neighbor and the same right neighbor in both seatings. 

---

(a) How many seatings are there? 
$$
\fbox{120}
$$
(b) How many seatings are there where A sits directly opposite B? 
$$
\fbox{24}
$$

## Question 6

A professor has 15 books. They wish to distribute 4 books each to their 3 favorite students. How many ways are there to do this? 
$$
{15 \choose 12} = \fbox{455}
$$
