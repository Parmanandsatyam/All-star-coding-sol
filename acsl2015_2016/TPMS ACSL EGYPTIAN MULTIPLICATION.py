"""
Takoma Park Middle School
Jr-5
All-Star Program 3: ACSL EGYPTIAN MULTIPLICATION
"""

def main():
    ansList = []
    numList = []
    userInput = input("INPUT: ").split(",")
    userInput = list(map(int,userInput))
    userInput.sort(reverse=True)
    userInput1 = int(userInput[0])
    userInput2 = int(userInput[1])
    if userInput1 == 0:
        numList.append(0)
    while userInput1 != 0:
        temp = 2
        while userInput1 - temp >= 0:
            temp *= 2
        temp /= 2
        numList.append(temp)
        userInput1 -= temp
    for count in numList:
        ansList.append(str(int(userInput2 * count)))
    return ", ".join(ansList)

for count in range(10):
    try:
        print(main())
    except:
        print("error")
