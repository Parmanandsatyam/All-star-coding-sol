# ACSL All-Star 2016-2017
# MBHS Senior 5
# Problem 7

def area(a, b, c):
    return abs((a[0]*b[1]+b[0]*c[1]+c[0]*a[1])-(a[1]*b[0]+b[1]*c[0]+c[1]*a[0]))/2

def inter(t1, t2, l1, l2):
    if t1[0]==t2[0] and l1[0]==l2[0]:
        return 0
    elif t1[0]==t2[0]:
        x = t1[0]
        y = l1[1]+(l1[1]-l2[1])/(l1[0]-l2[0])*(x-l1[0])
        if y>max(t1[1],t2[1]) or y<min(t1[1],t2[1]):
            return 0
    elif l1[0]==l2[0]:
        x = l1[0]
        y = t1[1]+(t1[1]-t2[1])/(t1[0]-t2[0])*(x-t1[0])
        if y>max(t1[1],t2[1]) or y<min(t1[1],t2[1]):
            return 0
    elif (t2[1]-t1[1])/(t2[0]-t1[0])==(l2[1]-l1[1])/(l2[0]-l1[0]):
        return 0
    else:
        slopet = (t2[1]-t1[1])/(t2[0]-t1[0])
        slopel = (l2[1]-l1[1])/(l2[0]-l1[0])
        x = (t1[0]*slopet-l1[0]*slopel+l1[1]-t1[1])/(slopet-slopel)
        y = slopet*(x-t1[0])+t1[1]
        if x>max(t1[0],t2[0]) or x<min(t1[0],t2[0]) or y>max(t1[1],t2[1]) or y<min(t1[1],t2[1]):
            return 0
    return [x,y]       

input1 = input('1. ').split(', ')
vertices = []
for i in range(1, len(input1), 2):
    vertices.append([int(input1[i]),int(input1[i+1])])

for i in range(2, 12):
    try:
        pair = input(str(i)+'. ').split(', ')
        t1 = vertices[ord(pair[0][0])-65]
        t2 = vertices[ord(pair[0][1])-65]
        t3 = vertices[ord(pair[0][2])-65]
        l1 = vertices[ord(pair[1][0])-65]
        l2 = vertices[ord(pair[1][1])-65]

        a = inter(t1, t2, l1, l2)
        b = inter(t1, t3, l1, l2)
        c = inter(t2, t3, l1, l2)

        if a == 0:
            print(round(area(t3, b, c),3))
        elif b == 0:
            print(round(area(t2, a, c),3))
        elif c == 0:
            print(round(area(t1, a, b),3))
    except Exception:
        print(0)

