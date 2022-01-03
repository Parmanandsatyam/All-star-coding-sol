#ACSL 2015 Problem 6

def percentCompare(A,B):
    #compareto for two players. 0 if same percent,-1 if A<B, 1 if A>B
    return sum(A[1:5])*B[5]-sum(B[1:5])*A[5]

def points(player):
    return sum([x*player[x] for x in range(1,5)])

def mean():
    return sum([points(x) for x in players])/10

def median():
    p=[points(x) for x in players]
    p.sort()
    return (p[4]+p[5])/2

def teamMax():
    X=players[0]
    for p in players[:5]:
        if points(p)>points(X):
            X=p
    Y=players[5]
    for p in players[5:]:
        if points(p)>points(Y):
            Y=p
    return "%s, %s"%(X[0],Y[0])

def totalMax():
    p=[[points(x),x[0]] for x in players]
    p.sort(reverse=True)
    return "%s, %s"%(p[0][1],p[1][1])

def gameScore():
    p=[points(x) for x in players]
    score=[sum(p[:5]), sum(p[5:])]
    score.sort(reverse=True)
    return "%d, %d"%(score[0],score[1])

def minScore():
    m=points(players[0])
    for x in players:
        if points(x)<m:
            m=points(x)
    ans=[]
    for x in players:
        if points(x)==m:
            ans.append(x[0])
    ans.sort()
    return ", ".join(ans)

def xHighPercent():
    ans=[players[0]]
    for p in players[:5]:
        if percentCompare(p,ans[0])>0:
            ans=[p]
        elif percentCompare(p,ans[0])==0:
            ans.append(p)
    ans=[x[0] for x in ans]
    ans=list(set(ans))
    ans.sort()
    return ','.join(ans)

def yLowPercent():
    ans=[players[5]]
    for p in players[5:]:
        if percentCompare(p,ans[0])<0:
            ans=[p]
        elif percentCompare(p,ans[0])==0:
            ans.append(p)
    ans=[x[0] for x in ans]
    ans=sorted(list(set(ans)))
    return ','.join(ans)

def zone2Max():
    ans=[players[0]]
    for x in players:
        if x[2]>ans[0][2]:
            ans=[x]
        elif x[2]==ans[0][2]:
            ans.append(x)
    ans=[x[0] for x in ans]
    ans=sorted(list(set(ans)))
    return ','.join(ans)

def zone1Max():
    ans=[players[0]]
    for x in players:
        if x[1]>ans[0][1]:
            ans=[x]
        elif x[1]==ans[0][1]:
            ans.append(x)
    ans=[x[0] for x in ans]
    ans=sorted(list(set(ans)))
    return ','.join(ans)
            

print('ACSL 2015 Problem 6\nCherry Creek High School')

players=[]

for repeat in range(10):
    p=input('Input  %d: '%(repeat+1))
    p=p.split(',')
    p=[x.strip() for x in p]
    pl=list(p[1])
    pl[-1]=int(pl[-1],16)
    pl=[int(x) for x in pl]
    pl.insert(0,p[0])
    players.append(pl)


print('Output 1: '+str(round(mean())))
print('Output 2: '+str(round(median())))
print('Output 3: '+teamMax())
print('Output 4: '+totalMax())
print('Output 5: '+gameScore())
print('Output 6: '+minScore())
print('Output 7: '+xHighPercent())
print('Output 8: '+yLowPercent())
print('Output 9: '+zone2Max())
print('Output 10: '+zone1Max())
