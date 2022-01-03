#ACSL ALL-STAR
#Herndon TestPrep
#Program1
import itertools
inp=input('Input:').split(',')
k=inp[1]
l=[]
for i in inp[0]:
    l.append(i)
output=sorted(l)
x=0
for i in range(len(output)):
    output[i] = str(output[i])
stroutput = ''.join(output)
for i in stroutput:
    if i=='0':
        x+=1
        continue
    else:
        stroutput = stroutput[x:]
        break
output2=sorted(output)
output2=output2[::-1]
p=''
for i in output2:
    if not i == " ":
        p += i
print(stroutput)
print(p)
output3 = sorted(output)
ilist = []
for v in range(0, len(output3)+1):
    for i in itertools.permutations(output3, v):
        if len(i) == len(output3):
            ilist.append(i)
ilist=sorted(set(ilist))
o=''
c=ilist[-50]
c=list(c)
for i in c:
    if not i==' ':
        o+=i
print(o)
fourth=ilist[int(k)-1]
fourth=list(fourth)
for i in range(len(fourth)):
    fourth[i]=str(fourth[i])
output4=''.join(fourth)
x=0
for i in output4:
    if i=='0':
        x+=1
        continue
    else:
        output4 = output4[x:]
        break
print(output4)
av=((int(output4)+int(o))/2)
x=1234567889098765
d=0
for i in ilist:
    b=''
    i=list(i)
    for l in i:
        if not l==' ':
            b+=l
    u=abs(int(b)-av)
    if u<=x:
        x=u
    else:
        break
for i in ilist:
    b = ''
    i = list(i)
    for l in i:
        if not l == ' ':
            b += l
    u = abs(int(b) - av)
    if x==u:
        print(b)

