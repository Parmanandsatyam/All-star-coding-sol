for x in range(1,11):
    try:
        inp=input(str(x)+". ")
        print(str(x)+". ",end="")
        inp=inp.strip().split(", ")
        start=inp[1]
        binary=int(start,16)
        binary=bin(binary)
        binary=str(binary[2:len(binary)])
        n=int(inp[0])
        while len(binary)<n:
            binary="0"+binary
        raw=[]
        for a in range(int(n*(n-1)/2)):
            raw+=inp[2+a][0]
        raw2=[]
        for b in range(int(n*(n-1)/2)):
            raw2+=inp[2+b][1]
        res=[[]]
        ops=[]
        ans=[[]]
        ans[0]=list(binary)
        res[0]=list(binary)
        for y in range(n-1):
            res+=[[]]
            ops+=[[]]
            ans+=[[]]
            for z in range(n-1-y):
                ops[len(ops)-1]+=[raw[0]]
                raw=raw[1:len(raw)]
                ans[len(ans)-1]+=[raw2[0]]
                raw2=raw2[1:len(raw2)]


        for c in range(n-1):
            for d in range(n-1-c):
                if ops[c][d]=="A":
                    res[c+1]+=[str(int(res[c][d]) & int(res[c][d+1]))]
                if ops[c][d]=="R":
                    res[c+1]+=[str(int(res[c][d]) | int(res[c][d+1]))]
                if ops[c][d]=="X":
                    res[c+1]+=[str(int(res[c][d]) ^ int(res[c][d+1]))]
        
        same=1
        break1=0

        for e in range(n):
            if break1==1:
                break
            for f in range(len(ans[e])):
                if ans[e][f]!=res[e][f]:
                    same=0
                    break1=0
                    pos=[e+1,f+1]
                    correct=int(ans[e][f])
                    gate=""
                    if correct==1:
                        if int(res[e-1][f]) & int(res[e-1][f+1])==1:
                            gate="AND1"
                            ops[e-1][f]="A"

                            res=[[]]
                            res[0]=list(binary)
                            for y in range(n-1):
                                res+=[[]]
                            for c in range(n-1):
                                for d in range(n-1-c):
                                    if ops[c][d]=="A":
                                        res[c+1]+=[str(int(res[c][d]) & int(res[c][d+1]))]
                                    if ops[c][d]=="R":
                                        res[c+1]+=[str(int(res[c][d]) | int(res[c][d+1]))]
                                    if ops[c][d]=="X":
                                        res[c+1]+=[str(int(res[c][d]) ^ int(res[c][d+1]))]
                            
                        elif int(res[e-1][f]) | int(res[e-1][f+1])==1:
                            gate="OR1"
                            ops[e-1][f]="R"

                            res=[[]]
                            res[0]=list(binary)
                            for y in range(n-1):
                                res+=[[]]
                            for c in range(n-1):
                                for d in range(n-1-c):
                                    if ops[c][d]=="A":
                                        res[c+1]+=[str(int(res[c][d]) & int(res[c][d+1]))]
                                    if ops[c][d]=="R":
                                        res[c+1]+=[str(int(res[c][d]) | int(res[c][d+1]))]
                                    if ops[c][d]=="X":
                                        res[c+1]+=[str(int(res[c][d]) ^ int(res[c][d+1]))]
                            
                        elif int(res[e-1][f]) ^ int(res[e-1][f+1])==1:
                            gate="XOR1"
                            ops[e-1][f]="X"

                            res=[[]]
                            res[0]=list(binary)
                            for y in range(n-1):
                                res+=[[]]
                            for c in range(n-1):
                                for d in range(n-1-c):
                                    if ops[c][d]=="A":
                                        res[c+1]+=[str(int(res[c][d]) & int(res[c][d+1]))]
                                    if ops[c][d]=="R":
                                        res[c+1]+=[str(int(res[c][d]) | int(res[c][d+1]))]
                                    if ops[c][d]=="X":
                                        res[c+1]+=[str(int(res[c][d]) ^ int(res[c][d+1]))]
                                        
                    elif correct==0:
                        if int(res[e-1][f]) & int(res[e-1][f+1])==0:
                            gate="AND0"
                            ops[e-1][f]="A"

                            res=[[]]
                            res[0]=list(binary)
                            for y in range(n-1):
                                res+=[[]]
                            for c in range(n-1):
                                for d in range(n-1-c):
                                    if ops[c][d]=="A":
                                        res[c+1]+=[str(int(res[c][d]) & int(res[c][d+1]))]
                                    if ops[c][d]=="R":
                                        res[c+1]+=[str(int(res[c][d]) | int(res[c][d+1]))]
                                    if ops[c][d]=="X":
                                        res[c+1]+=[str(int(res[c][d]) ^ int(res[c][d+1]))]
                                        
                        elif int(res[e-1][f]) | int(res[e-1][f+1])==0:
                            gate="OR0"
                            ops[e-1][f]="R"

                            res=[[]]
                            res[0]=list(binary)
                            for y in range(n-1):
                                res+=[[]]
                            for c in range(n-1):
                                for d in range(n-1-c):
                                    if ops[c][d]=="A":
                                        res[c+1]+=[str(int(res[c][d]) & int(res[c][d+1]))]
                                    if ops[c][d]=="R":
                                        res[c+1]+=[str(int(res[c][d]) | int(res[c][d+1]))]
                                    if ops[c][d]=="X":
                                        res[c+1]+=[str(int(res[c][d]) ^ int(res[c][d+1]))]
                                        
                        elif int(res[e-1][f]) ^ int(res[e-1][f+1])==0:
                            gate="XOR0"
                            ops[e-1][f]="X"

                            res=[[]]
                            res[0]=list(binary)
                            for y in range(n-1):
                                res+=[[]]
                            for c in range(n-1):
                                for d in range(n-1-c):
                                    if ops[c][d]=="A":
                                        res[c+1]+=[str(int(res[c][d]) & int(res[c][d+1]))]
                                    if ops[c][d]=="R":
                                        res[c+1]+=[str(int(res[c][d]) | int(res[c][d+1]))]
                                    if ops[c][d]=="X":
                                        res[c+1]+=[str(int(res[c][d]) ^ int(res[c][d+1]))]
                                        
                    if gate=="":
                        gate="NONE"
                        break1=1
                    print(str(pos[0])+", "+str(pos[1])+", "+gate)
                    if break1==1:
                        break
        if same==1:
            print("TRUE")
                        
    except:
        oops=1
