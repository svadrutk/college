counter = input()
stuffToPrint = []
for i in range(int(counter)):
    stuffToPrint.append(input())
for i in range(len(stuffToPrint)): 
    print("Hello, " + stuffToPrint[i] + "!")