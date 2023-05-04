# Weekly HW 2

## 1.4

23. Determine a scalar $r$ such that $Ax = rx$, where 

$$
A = 
\begin{bmatrix}
1 & 2 & -1 \\ 
1 & 0 & 1 \\ 
4 & -4 & 5
\end{bmatrix}
\text{and }
x = 
\begin{bmatrix}
\frac{-1}{2} \\ 
\frac{1}{4} \\ 
1
\end{bmatrix}
$$

$$
Ax = 
\begin{bmatrix}
-0.5 + 0.5 + -1 \\ 
-0.5 + 0 + 1 \\ 
-2 -1 + 5
\end{bmatrix} = 
\begin{bmatrix}
-1 \\ 
0.5 \\ 
2
\end{bmatrix}
$$

$$
\fbox{$r = 2$}
$$

## 1.5

38. The linear system $A^2x = b$ is such that $A$ is nonsingular with 

$$
A^{-1} = 
\begin{bmatrix}
3 & 0 \\
2 & 1
\end{bmatrix} \text{ and }
b=
\begin{bmatrix}
-1 \\ 2
\end{bmatrix}
$$

Find the solution $x$. 
$$
A^{-1^{-1}} = A = 
\begin{bmatrix}
1/3 & 0 \\
-2/3 & 1
\end{bmatrix}
$$

$$
A^2 =
\begin{bmatrix}
1/3 & 0 \\
-2/3 & 1
\end{bmatrix} \cross 
\begin{bmatrix}
1/3 & 0 \\
-2/3 & 1
\end{bmatrix} = 
\begin{bmatrix}
1/9 & 0 \\
-8/9 & 1
\end{bmatrix}
$$

$$
\begin{bmatrix}
1/9 & 0 \\
-8/9 & 1
\end{bmatrix} \cross
\begin{bmatrix}
x \\
y
\end{bmatrix} = 
\begin{bmatrix}
-1 \\
2
\end{bmatrix}
$$

$$
\frac{x}{9} = -1 \\
\frac{-8x}{9} + y = -2
$$

$$
\fbox{$x = -9, y = -6$}
$$

## 1.6

In the following exercises, let $f : \R^7 \rightarrow \R^3$ for the matrix transformation defined by $f(x) = Ax$, where 
$$
A = 
\begin{bmatrix}
1 & 2 \\
0 & 1 \\
1 & 1
\end{bmatrix}
$$
Determine whether each given vector $w$ is in the range of $f$. 

9. $w = \begin{bmatrix} 1 \\ -1 \\ 2 \end{bmatrix}$ 

$$
\begin{bmatrix}
1 & 2 \\
0 & 1 \\
1 & 1
\end{bmatrix} 
\begin{bmatrix}
x \\
y
\end{bmatrix} = 
\begin{bmatrix} 
1 \\ -1 \\ 2 
\end{bmatrix}
$$

$$
x + 2y = 1 \\
y = -1 \\
x + y = 2
$$

If $y = -1$, $x = 3$. This works for the first equation too. Therefore, $\fbox{this vector is in the range of $f$}$. 

