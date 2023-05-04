# Weekly Homework 1

### Section 1.1

22. Is there a value of $r$ so that $x=1$, $y=2$, $z=r$ is a solution to the following linear system? If there is, find it. 
    $$
    2x + 3y -z = 11 \\
    x - y + 2z = -7 \\
    4x + y - 2z = 12
    $$
    $\fbox{$r$ can be equal to -3 for the system of equations to be consistent.}$

---

### Section 1.2

11. Is the matrix $\begin{bmatrix}4 & 1 \\ 0 & -3\end{bmatrix}$ a linear combination of the matrices $\begin{bmatrix}1 & 0 \\ 0 & 1\end{bmatrix}$ and $\begin{bmatrix}1 & 0 \\ 0 & 0\end{bmatrix}$? Justify your answer. 

    A linear combination can be obtained by multiplying none, one, or both of the matrices by a scalar and adding them together. Note that for the top right value of the solution matrix, $1$, it is impossible to multiply $0$ by a scalar to get a non-zero number. Therefore, 
    $$
    \fbox{The matrix $\begin{bmatrix}4 & 1 \\ 0 & -3\end{bmatrix}$ is not a linear combination of matrices $A$ and $B$}
    $$

## Section 1.3

43. If $A = [a_{ij}]$ is an $n\cross n$ matrix, then the **trace** of $A$, $\text{Tr}(A)$, is defined as the sum of all elements on the main diagonal of $A$, $\text{Tr}(A) = \sum_{i=1}^n a_{ii}$. Show each of the following: 

    1. $\text{Tr}(cA) = c\text{Tr}(A)$, where $c$ is a real number

       $$
       \text{Tr}(cA) = \sum_{i=1}^nca_{ii} = c\sum_{i=1}^na_{ii} = cTr(A)
       $$
       
       $$
       \fbox{QED}
       $$
    
    2. $\text{Tr}(A+B) = \text{Tr}(A) + \text{Tr}(B)$
       $$
       \text{Tr}(A+B) = \sum_{i=1}^n(a_{ii} + b_{ii}) = \sum_{i=1}^na_{ii} + \sum_{i=1}^nb_{ii} = \text{Tr}(A) + \text{Tr}(B)
       $$
    
       $$
       \fbox{QED}
       $$
    
    3. $\text{Tr}(AB) = \text{Tr}(BA)$
       $$
       \text{Tr}(AB) = \text{Tr}(C) = \sum_{j=1}^n c_{ii} = \sum_{i=1}^n \sum_{k=1}^n (a_{ii} \cross b_{ii}) \\
       \text{Tr}(BA) = \sum_{k=1}^n \sum_{i=1}^n (b_{ii} \cross a_{ii}) \Rightarrow \sum_{i=1}^n \sum_{k=1}^n(a_{ii} \cross b_{ii})
       $$
    
       $$
       \text{Tr}(AB) = \text{Tr}(BA)
       $$
    
       $$
       \fbox{QED}
       $$
    
    4. $\text{Tr}(A^T) = \text{Tr}(A)$
       $$
       A = \begin{bmatrix}
       a_{ii} & k & k \\ 
       j & a_{ii} & k \\ 
       j & j & a_{ii}
       \end{bmatrix}
       $$
    
       $$
       A^T = 
       \begin{bmatrix}
       a_{ii} & j & j \\
       k & a_{ii} & j \\
       k & k & a_{ii}
       \end{bmatrix}
       $$
    
       $$
       a_{ii} + a_{ii} + ... + \ a_{ii} = a_{ii} + a_{ii} + ... + \ a_{ii}
       $$
    
       $$
       \text{Tr}(A^T) = \text{Tr}(A)
       $$
    
       $$
       \fbox{QED}
       $$
    
    5. $\text{Tr}(A^TA) \geq 0$
    
       Transposing does not change the main diagonal's sign. Therefore, if you multiply two of the same diagonals together, each of the numbers will result in a zero or a positive, which results in the sum being either zero or positive. $\fbox{QED}$.
    
     
