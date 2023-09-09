import sys
import math


def get_parameter_vectors():
    '''
    This function parses e.txt and s.txt to get the  26-dimensional multinomial
    parameter vector (characters probabilities of English and Spanish) as
    descibed in section 1.2 of the writeup

    Returns: tuple of vectors e and s
    '''
    #Implementing vectors e,s as lists (arrays) of length 26
    #with p[0] being the probability of 'A' and so on
    e=[0]*26
    s=[0]*26

    with open('e.txt',encoding='utf-8') as f:
        for line in f:
            #strip: removes the newline character
            #split: split the string on space character
            char,prob=line.strip().split(" ")
            #ord('E') gives the ASCII (integer) value of character 'E'
            #we then subtract it from 'A' to give array index
            #This way 'A' gets index 0 and 'Z' gets index 25.
            e[ord(char)-ord('A')]=float(prob)
    f.close()

    with open('s.txt',encoding='utf-8') as f:
        for line in f:
            char,prob=line.strip().split(" ")
            s[ord(char)-ord('A')]=float(prob)
    f.close()

    return (e,s)

def shred(filename):
    #Using a dictionary here. You may change this to any data structure of
    #your choice such as lists (X=[]) etc. for the assignment
    X= {"A": 0, "B": 0, "C": 0, "D": 0, "E": 0, "F": 0, "G": 0, "H": 0, "I": 0, "J": 0, "K": 0, "L": 0, "M": 0, "N": 0, "O": 0, "P": 0, "Q": 0, "R": 0, "S": 0, "T": 0, "U": 0, "V": 0, "W": 0, "X": 0, "Y": 0, "Z": 0}
    with open (filename,encoding='utf-8') as f:
        # TODO: add your code here
        while 1: 
            char = f.read(1)
            if not char: 
                break
            if char.isalnum():
                char = char.upper()
                X[char] += 1
    keys = list(X.keys())
    keys.sort()
    X = {i: X[i] for i in keys}
    return X



# TODO: add your code here for the assignment
# You are free to implement it as you wish!
# Happy Coding!

print("Q1") 
dictionary = shred("letter.txt")
# print dictionary out
for keyval in dictionary: 
    print(keyval + " " + str(dictionary[keyval]))
Q1List = list(dictionary.values())
lists = get_parameter_vectors()
eArr = lists[0]
sArr = lists[1]
######################################################
print("Q2") 
print("{:.4f}".format(round(Q1List[0] * math.log(eArr[0]), 4)))
print("{:.4f}".format(round(Q1List[0] * math.log(sArr[0]), 4)))
######################################################

####
eProb = 0
for i in range(len(eArr)): 
    if eArr[i] != 0 and Q1List[i] != 0:
        eProb = eProb + (math.log(eArr[i]) * Q1List[i])

sProb = 0
for i in range(len(sArr)): 
    if sArr[i] != 0 and Q1List[i] != 0:
        sProb = sProb + (math.log(sArr[i]) * Q1List[i])

print("Q3")
english = round(eProb + math.log(0.6), 4)
spanish = round(sProb + math.log(0.4), 4)
print("{:.4f}".format(english))
print("{:.4f}".format(spanish))
print("Q4")
if spanish - english >= 100: 
    print("0.0000")
elif spanish - english <= -100: 
    print("1.0000")
else: 
    result = 1 / (1 + (math.e ** (spanish - english)))
    print("{:.4f}".format(round(result, 4)))


