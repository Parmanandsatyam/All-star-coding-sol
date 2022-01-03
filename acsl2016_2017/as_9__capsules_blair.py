import itertools

def cap(row, col, hexString):
    RS = []
    for i in range(1,row+1):
        for j in range(1,col+1):
            h = hexString[(i-1)*col+(j-1)]
            h = bin(int(h,16))[2:]
            h = (4-len(h))*'0'+h
            T = []
            for k in range(len(RS)):
                R = RS[k]
                for r in R:
                    t = r[2]
                    if r[0]-i == 1 and r[1] == j and \
                       t[2] == '0' and h[0] == '0':
                        T.append(k)
                        break
                    if r[0]-i == -1 and r[1] == j and \
                       t[0] == '0' and h[2] == '0':
                        T.append(k)
                        break
                    if r[1]-j == 1 and r[0] == i and \
                       t[3] == '0' and h[1] == '0':
                        T.append(k)
                        break
                    if r[1]-j == -1 and r[0] == i and \
                       t[1] == '0' and h[3] == '0':
                        T.append(k)
                        break
            if T:
                N = []
                for k in range(len(T)):
                    N += RS.pop(T[k]-k)
                N.append([i,j,h])
                RS.append(N)
            else:
                RS.append([[i,j,h]])
    for i in range(1,len(RS)):
        j = i
        while len(RS[j]) < len(RS[j-1]):
            t = RS[j][:]
            RS[j] = RS[j-1][:]
            RS[j-1] = t
            j -= 1
            if j == 0:
                break
    return RS

def build(level, array, groups, initial):
    Plist = list(itertools.permutations(groups[level]))
    for P in Plist:
        for i in range(len(P)):
            array[P[i][0]-1][P[i][1]-1] = i+1
        if (check(initial,array)):
        
            if (len(groups)-1 == level):
                return array, True
            array, done = build(level+1, array[:], groups, initial)
            if done:
                return array, True
        else:
            for i in range(len(P)):
                array[P[i][0]-1][P[i][1]-1] = 0
            for i in range(len(initial)):
                array[initial[i][0]-1][initial[i][1]-1] = initial[i][2]
    return array, False
    
def check(initial, array):
    #print("HI")
    for i in range(len(array)):
        for j in range(len(array[0])-1):
            if (array[i][j] != 0) and (array[i][j] == array[i][j+1]):
                #print("HI1")
                return False
    for i in range(len(array)-1):
        for j in range(len(array[0])):
            if (array[i][j] != 0) and (array[i][j] == array[i+1][j]):
                #print("HI2")
                return False
    for i in range(len(array)-1):
        for j in range(len(array[0])-1):
            if (array[i][j] != 0) and (array[i][j] == array[i+1][j+1]):
                #print("HI3")
                return False
    for i in range(len(array)-1):
        for j in range(len(array[0])-1):
            if (array[i+1][j] != 0) and (array[i+1][j] == array[i][j+1]):
                return False
    for point in initial:
        if array[point[0]-1][point[1]-1] != point[2]:
            #print("HI4")
            return False
    return True

def solve(row, col, hexString, V):
    groups = cap(row, col, hexString)
    array = [[0 for j in range(col)] for i in range(row)]
    for v in V:
        array[v[0]-1][v[1]-1] = v[2]
    initial = V
    O = build(0,array[:],groups,initial)
    final = O[0]
    numRegions = len(groups)
    noneInitial = len(groups)
    used = []
    for v in V:
        for g in groups:
            for c in g:
                if c[0] == v[0] and c[1] == v[1] and g not in used:
                    used.append(g)
                    noneInitial -= 1
                    break
    sumTop = sum(final[-1])
    sumLeft = sum([x[0] for x in final])

    intersect = 1

    return [intersect, numRegions, noneInitial, sumTop, sumLeft]

for i in range(1,3):
    try:
        raw = input(str(i)+'. ').replace(' ','').split(',')
        row = int(raw[0])
        col = int(raw[1])
        hexString = raw[2]
        n = int(raw[3])
        V = []
        for j in range(n):
            t = raw[4+j]
            V.append([int(t[0]),int(t[1]),int(t[2])])
        O = solve(row,col,hexString,V)
        print('\n'.join([str(x) for x in O]))
    except:
        print('error')
    
    
