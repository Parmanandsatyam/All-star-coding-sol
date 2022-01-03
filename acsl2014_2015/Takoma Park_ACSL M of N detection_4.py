def freq1(b):
    freq=0
    for ind in b:
        if ind=="1":
            freq+=1
    return freq
def bina(x,l):
    binrep=bin(x)[2:]
    return '0'*(l-len(binrep))+binrep
def findall(weightstr,ones,goal):
    retlist=[]
    for binnum in range(2**len(weightstr)):
        test=bina(binnum,len(weightstr))
        if freq1(test)==int(ones):
            goalsum=0
            for ele in range(len(weightstr)):
                goalsum+=int(weightstr[ele])*int(test[ele])
            if goalsum==int(goal):
                retlist+=[test]
    return retlist
def fermat(anslist):
    if len(anslist)==0:
        return 'NONE'
    else:
        retstr=''
        for ele in anslist:
            retstr+=ele+','
        return retstr[:-1]
for itraten in range(1,11):
    inputstr=input(str(itraten)+'. ').split(",")
    for ele in range(len(inputstr)):
        inputstr[ele]=inputstr[ele].strip()
    mask=inputstr.pop(0)
    ones=inputstr.pop(0)
    idealsum=inputstr.pop(0)
    print(str(itraten)+'. '+fermat(findall(mask,ones,idealsum)))
input("Press enter to exit the program.")
