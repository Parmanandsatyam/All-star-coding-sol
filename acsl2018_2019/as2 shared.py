#AS2 - 2018-19 - Stretch
def printGrid():
    global grid, rows, cols
    for x in range(rows):
        line = ""
        for y in range(cols):
            line += str(grid[x][y])
        print(line)

def checkPos(y,srow,rpos,scol,cpos,begin,end):
    global grid, rows, cols, direction, erow, ecol
    letters = ["A","B","C","D","E"]
    found = True
    if srow+rpos >= rows or scol+cpos >= cols-1 or srow+rpos < 0 or scol+cpos < 1:
        found = False
    elif y != begin and y != end-1 and (srow+rpos == 0 or srow+rpos == rows-1):
        found = False
    elif grid[srow+rpos][scol+cpos] != "0":
        found = False
    if y > begin:
        if srow+rpos-1 >= 0 and grid[srow+rpos-1][scol+cpos] in letters:
            found = False
        if srow+rpos+1 <= rows-1 and grid[srow+rpos+1][scol+cpos] in letters:
            found = False
        if scol+cpos-1 >= 0 and grid[srow+rpos][scol+cpos-1] in letters:
            found = False
        if scol+cpos+1 <= cols-1 and grid[srow+rpos][scol+cpos+1] in letters:
            found = False
    else:
        if srow-1 != erow and scol != ecol and srow+rpos-1 >= 0 and grid[srow+rpos-1][scol+cpos] in letters:
            found = False
        if srow+1 != erow and scol != ecol and srow+rpos+1 <= rows-1 and grid[srow+rpos+1][scol+cpos] in letters:
            found = False
        if srow != erow and scol-1 != ecol and scol+cpos-1 >= 0 and grid[srow+rpos][scol+cpos-1] in letters:
            found = False
        if srow != erow and scol+1 != ecol and scol+cpos+1 <= cols-1 and grid[srow+rpos][scol+cpos+1] in letters:
            found = False
    return found

totpieces = 5
piecesdn = [[0,0],[1,0],[2,0],[0,0],[1,0],[1,1],[0,0],[0,1],[1,1],[2,1]]
piecesup = [[0,0],[-1,0],[-2,0],[0,0],[0,-1],[-1,-1],[0,0],[-1,0],[-2,0],[-2,-1]]
apiecelt = [[0,0],[0,-1],[0,-2]]
apiecert = [[0,0],[0,1],[0,2]]
epiece = [[0,0],[0,1],[1,1],[1,2],[0,0],[0,-1],[-1,-1],[-1,-2]]
which = input("sample or test?")
filename = "f:\\as2-"+which+".txt"
inf = open(filename,'r')
lines = inf.readlines()
for cnt in range(len(lines)):
    line = lines[cnt].rstrip().split()
    #print(line)
    path = ""
    rows = int(line[0])
    cols = int(line[1])
    start = int(line[2])
    srow = (start-1) // cols
    scol = (start-1) % cols
    if srow == 0:
        direction = "d"
    elif srow == rows - 1:
        direction = "u"
    grid = []
    for y in range(rows):
        row = []
        for z in range(cols):
            row.append("0")
        grid.append(row)
    num = int(line[3])
    for y in range(num):
        pos = int(line[4+y])
        prow = (pos-1) // cols
        pcol = (pos-1) % cols
        grid[prow][pcol] = "x"
    piece = totpieces
    erow = srow; ecol = scol; nsrow = srow; nscol = scol
    numTrys = 1
    printGrid()
    wrongpieces = []
    while (direction == "d" and erow < rows-1) or (direction == "u" and erow > 0) and len(wrongpieces) < 5:
        if piece == totpieces:
            piece = 0
        numpieces = 3
        if piece >= 3:
            numpieces = 4
        if piece < 2:
            begin = 0
            end = 3
        elif piece == 2:
            begin = 3
            end = 6
        elif piece == 3:
            begin = 6
            end = 10
        else:
            begin = 0
            end = 4
        ok = True
        for t in range(numTrys):
            if numTrys != 1:
                ok = False
                if t == 0:
                    if scol<cols-1 and grid[srow][scol+1] == "0":
                        nsrow = srow
                        nscol = scol + 1
                        ok = True
                elif t == 1:
                    if direction == "d" and srow<rows-1 and grid[srow+1][scol] == "0":
                        nsrow = srow + 1
                        nscol = scol
                        ok = True
                    elif direction == "u" and srow>0 and grid[srow-1][scol] == "0":
                        nsrow = srow - 1
                        nscol = scol
                        ok = True
                elif t == 2:
                    if scol>0 and grid[srow][scol-1] == "0":
                        nsrow = srow
                        nscol = scol - 1
                        ok = True
                if t == 3:
                    if ecol<cols-1 and grid[erow][ecol+1] == "0":
                        nsrow = erow
                        nscol = ecol + 1
                        ok = True
                elif t == 4:
                    if direction == "d" and grid[erow+1][ecol] == "0":
                        nsrow = erow + 1
                        nscol = ecol
                        ok = True
                    elif direction == "u" and grid[erow-1][ecol] == "0":
                        nsrow = erow - 1
                        nscol = ecol
                        ok = True
                elif t == 5:
                    if ecol>0 and grid[erow][ecol-1] == "0":
                        nsrow = erow
                        nscol = ecol - 1
                        ok = True
            if ok:
                left = False
                right = False
                found = True
                if piece == 4:
                    begin = 0
                    end = 4
                    if direction == "d":
                        for y in range(begin,end):
                            rpos = epiece[y][0]
                            cpos = epiece[y][1]
                            found = checkPos(y,nsrow,rpos,nscol,cpos,begin,end)
                            if not found:
                                break
                    else:
                        for y in range(begin,end):
                            rpos = epiece[y][0]
                            cpos = epiece[y][1]
                            found = checkPos(y,nsrow,rpos,nscol,cpos,begin,end)
                            if not found:
                                break
                        if found:
                            right = True
                        else:
                            begin = 4
                            end = 8
                            found = True
                            for y in range(begin,end):
                                rpos = epiece[y][0]
                                cpos = epiece[y][1]
                                found = checkPos(y,nsrow,rpos,nscol,cpos,begin,end)
                                if not found:
                                    break
                            if found:
                                left = True
                elif piece != 0:
                    for y in range(begin,end):
                        if direction == "d":
                            rpos = piecesdn[y][0]
                            cpos = piecesdn[y][1]
                        else:
                            rpos = piecesup[y][0]
                            cpos = piecesup[y][1]
                        found = checkPos(y,nsrow,rpos,nscol,cpos,begin,end)
                        if not found:
                            break
                else:
                    for y in range(begin,end):
                        rpos = apiecelt[y][0]
                        cpos = apiecelt[y][1]
                        found = checkPos(y,nsrow,rpos,nscol,cpos,begin,end)
                        if not found:
                            break
                    if found:
                        left = True
                    else:
                        found = True
                        for y in range(begin,end):
                            rpos = apiecert[y][0]
                            cpos = apiecert[y][1]
                            found = checkPos(y,nsrow,rpos,nscol,cpos,begin,end)
                            if not found:
                                break
                        if found:
                            right = True
                if found:         
                    break
        if found:
            srow = nsrow
            scol = nscol
            path += chr(piece+65)
            for y in range(begin,end):
                if piece == 4:
                    rpos = epiece[y][0]
                    cpos = epiece[y][1]
                elif piece != 0:
                    if direction == "d":
                        rpos = piecesdn[y][0]
                        cpos = piecesdn[y][1]
                    else:
                        rpos = piecesup[y][0]
                        cpos = piecesup[y][1]
                else:
                    if left:
                        rpos = apiecelt[y][0]
                        cpos = apiecelt[y][1]
                    if right:
                        rpos = apiecert[y][0]
                        cpos = apiecert[y][1]
                grid[nsrow+rpos][nscol+cpos] = chr(piece+65)
             if piece == 4:
                erow = nsrow + epiece[end-1][0]
                ecol = nscol + epiece[end-1][1]
            elif piece != 0:
                if direction == "d":
                    if nsrow+piecesdn[end-1][0] == rows-1:
                        erow = rows-1   
                    else:
                        erow = nsrow + piecesdn[end-1][0]
                        ecol = nscol + piecesdn[end-1][1]
                else:
                    if nsrow+piecesup[end-1][0] == 0:
                        erow = 0
                    else:
                        erow = nsrow + piecesup[end-1][0]
                        ecol = nscol + piecesup[end-1][1] 
            else:
                if left:
                    erow = nsrow + apiecelt[end-1][0]
                    ecol = nscol + apiecelt[end-1][1]
                else:
                    erow = nsrow + apiecert[end-1][0]
                    ecol = nscol + apiecert[end-1][1]
            numTrys = 6
        elif not found and not (piece in wrongpieces):
            wrongpieces.append(piece)
        piece += 1
    printGrid()
    print(path)
    if len(wrongpieces) == 5:
        print("NOT POSSIBLE")
    ch = input("")
