order = [str(i) for i in range(10)] + ["A", "B", "C", "D", "E", "F", "G"]

for count in range(1, 11):
    try:
        a, b, c = input(str(count)+". ").replace(" ","").upper().split(",")
        b = int(b) #base
        c = int(c) #index of change
        originalc = c
        used = order[0:b]
        c = a.index(".")+c
        decimalfromend = len(a)-a.index(".")
        a = a[0:len(a)-decimalfromend] + a[len(a)-decimalfromend+1:len(a)]
        if int(order.index(a[c]))/b>0.49:
            a = a[0:c-1]+(order[order.index(a[c-1])+1])+"0"+a[c+1:len(a)]
            c-=1
        while not a[c] in used:
            if c==0:
                a = "10"+a[1:len(a)]
                c-=1
            else:
                a = a[0:c-1]+(order[order.index(a[c-1])+1])+"0"+a[c+1:len(a)]
                c-=1
        rightpart = a[len(a)-decimalfromend+1:len(a)-decimalfromend+1+originalc]
        print(str(count)+". "+a[0:len(a)-decimalfromend+1]+"."+rightpart)
    except:
        None
input()
