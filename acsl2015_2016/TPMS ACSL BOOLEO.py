"""
Takoma Park MS
Jr-5
"""
import operator as o

def fse(a,b):
    s=0
    for i in range(b):
        s+=a-i
    return s

ops={'A':o.and_,'R':o.or_,'X':o.xor}
negops={o.and_:'AND',o.or_:'OR',o.xor:'XOR'}
for j in range(1,6):
    try:
        x=list(map(lambda x: x.strip(),input("INPUT: ").split(',')))
        tree=[]
        for z in range(int(x[0])):
            tree.append([])
            for i in range(fse(int(x[0]),z),fse(int(x[0]),z)+int(x[0])-z):
                tree[-1].append(x[i+1])
        for i in range(len(tree)):
            if i==0:
                for q in range(len(tree[i])):
                    tree[i][q]=[0,int(tree[i][q])]
            else:
                for q in range(len(tree[i])):
                    tree[i][q]=[ops[tree[i][q][0]],tree[i][q][1]]
        flag=True
        for i in range(len(tree)):
            if len(tree[i])>1:
                for q in range(len(tree[i])-1):
                    if tree[i+1][q][0](int(tree[i][q][1]),int(tree[i][q+1][1]))!=int(tree[i+1][q][1]):
                        flag=False
                        break
                if not flag:
                    break
        if not flag:
            print(negops[tree[i+1][q][0]]+',',str(i+2)+',',q+1)
        else:
            print('TRUE')
    except:
        print('TRUE')
    
