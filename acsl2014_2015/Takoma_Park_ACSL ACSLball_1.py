def function(li):
    maximum = max(li)
    i = 0
    while i < len(li):
        if li[i] == maximum:
            li.pop(i)
            break
        i += 1
    return max(li)
def main():
    p1 = input("1. ").strip().split(",")
    p2 = input("2. ").strip().split(",")
    p3 = input("3. ").strip().split(",")
    p4 = input("4. ").strip().split(",")
    p5 = input("5. ").strip().split(",")
    p6 = input("6. ").strip().split(",")
    p7 = input("7. ").strip().split(",")
    p8 = input("8. ").strip().split(",")
    tots = []
    scores = []
    count = 0
    for i in p1:
        i = i.strip()
        p1[count] = i
        if count > 0:
            p1[count] = int(p1[count])
        count += 1
    count = 0
    for i in p2:
        i = i.strip()
        p2[count] = i
        if count > 0:
            p2[count] = int(p2[count])
        count += 1
    count = 0
    for i in p3:
        i = i.strip()
        p3[count] = i
        if count > 0:
            p3[count] = int(p3[count])
        count += 1
    count = 0
    for i in p4:
        i = i.strip()
        p4[count] = i
        if count > 0:
            p4[count] = int(p4[count])
        count += 1
    count = 0
    for i in p5:
        i = i.strip()
        p5[count] = i
        if count > 0:
            p5[count] = int(p5[count])
        count += 1
    count = 0
    for i in p6:
        i = i.strip()
        p6[count] = i
        if count > 0:
            p6[count] = int(p6[count])
        count += 1
    count = 0
    for i in p7:
        i = i.strip()
        p7[count] = i
        if count > 0:
            p7[count] = int(p7[count])
        count += 1
    count = 0
    for i in p8:
        i = i.strip()
        p8[count] = i
        if count > 0:
            p8[count] = int(p8[count])
        count += 1
    tp1 = p1[1] + p1[2] + p1[3]
    tp2 = p2[1] + p2[2] + p2[3]
    tp3 = p3[1] + p3[2] + p3[3]
    tp4 = p4[1] + p4[2] + p4[3]
    tp5 = p5[1] + p5[2] + p5[3]
    tp6 = p6[1] + p6[2] + p6[3]
    tp7 = p7[1] + p7[2] + p7[3]
    tp8 = p8[1] + p8[2] + p8[3]

    tots.append(tp1)
    tots.append(tp2)
    tots.append(tp3)
    tots.append(tp4)
    tots.append(tp5)
    tots.append(tp6)
    tots.append(tp7)
    tots.append(tp8)

    sp1 = p1[1] + (p1[2] * 2) + (p1[3] * 3)
    sp2 = p2[1] + (p2[2] * 2) + (p2[3] * 3)
    sp3 = p3[1] + (p3[2] * 2) + (p3[3] * 3)
    sp4 = p4[1] + (p4[2] * 2) + (p4[3] * 3)
    sp5 = p5[1] + (p5[2] * 2) + (p5[3] * 3)
    sp6 = p6[1] + (p6[2] * 2) + (p6[3] * 3)
    sp7 = p7[1] + (p7[2] * 2) + (p7[3] * 3)
    sp8 = p8[1] + (p8[2] * 2) + (p8[3] * 3)

    scores.append(sp1)
    scores.append(sp2)
    scores.append(sp3)
    scores.append(sp4)
    scores.append(sp5)
    scores.append(sp6)
    scores.append(sp7)
    scores.append(sp8)

    names = []
    names.append(p1[0])
    names.append(p2[0])
    names.append(p3[0])
    names.append(p4[0])
    names.append(p5[0])
    names.append(p6[0])
    names.append(p7[0])
    names.append(p8[0])

    threes = p1[3] + p2[3] + p3[3] + p4[3] + p5[3] + p6[3] + p7[3] + p8[3]
    sTop = 0
    sName = ""
    tTop = 0
    tName = ""
    for i in range(8):
        if tots[i] > tTop:
            tTop = tots[i]
            tName = names[i]
        if scores[i] > sTop:
            sTop = scores[i]
            sName = names[i]

    team1 = scores[0] + scores[1] + scores[2] + scores[3]
    team2 = scores[4] + scores[5] + scores[6] + scores[7]

    topScore = 0
    lose = []
    lost = 6
    second = ""
    if team1 > team2:
        topScore = team1
        lose = [scores[4], scores[5], scores[6], scores[7]]
        lost = 2
    else:
        topScore = team2
        lose = [scores[0], scores[1], scores[2], scores[3]]
        lost = 1
    if lost == 1:
        secondLarge = function(lose)
        if secondLarge == sp1:
            second = names[0]
        elif secondLarge == sp2:
            second = names[1]
        elif secondLarge == sp3:
            second = names[2]
        else:
            second = names[3]
    else:
        secondLarge = function(lose)
        if secondLarge == sp5:
            second = names[4]
        elif secondLarge == sp6:
            second = names[5]
        elif secondLarge == sp7:
            second = names[6]
        else:
            second = names[7]
    print("1. ", threes)
    print("2.  " + tName)
    print("3.  " + sName)
    print("4. ", topScore)
    print("5.  " + second)
    input("Press enter to exit the program.")
main()
    
