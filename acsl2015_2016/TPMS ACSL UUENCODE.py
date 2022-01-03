"""
Takoma Park Middle School
Jr-5
All-Star Program 4: ACSL UUENCODE
"""

import re
def main():
    ansList = []
    tempList1 = []
    tempList2 = []
    tempList3 = []
    userInput = list(input("INPUT: "))
    while len(userInput) / 3 != int(len(userInput) / 3):
        userInput.append("0")
    for item in userInput:
        tempList1.append('%08.d'%int(str(bin(ord(item)))[2:]))

    temp = "".join(tempList1)
    tempList2 = re.findall("..?.?.?.?.?",temp)
    for item in tempList2:
        item = '%06.d'%int(item)
        tempList3.append(int(item,base=2)+32)
    for item in tempList3:
        ansList.append(chr(item))
    return "".join(ansList).replace(" ","~")
for count in range(10):
    try:
        print(main())
    except:
        print("error")

