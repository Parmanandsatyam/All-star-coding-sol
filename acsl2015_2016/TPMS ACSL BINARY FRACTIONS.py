"""
Takoma Park Middle School
Jr-5
All-Star Program 2: ACSL BINARY FRACTIONS
"""

for count in range(5):
    try:
        fraction = input("INPUT: ")
        fraction = list(map(int,fraction.split(",")))
        decimal=fraction[0]/fraction[1]
        finalBin=[]
        finalDec=0
        a='0'
        if decimal>=1:
                    
            finalDec = int(decimal)
            a = bin(int(decimal))[2:]
            decimal -= int(decimal)
        for count1 in range(1, 7):
            if decimal >= 2**-count1:
                decimal-=2**-count1
                finalDec+=2**-count1
                finalBin.append(1)
            else:
                finalBin.append(0)
        print(a+'.'+''.join(list(map(str,finalBin))),end=', ')
        print('%6f'%finalDec)
    except:
        print("error")
