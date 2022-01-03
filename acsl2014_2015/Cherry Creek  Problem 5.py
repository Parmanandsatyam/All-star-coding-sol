#ACSL 2015 Problem 5


def maxAppear(text):
    maxVal='A'
    for x in letters:
        if text.count(x)>text.count(maxVal):
            maxVal=x
    return [maxVal,text.count(maxVal)]

def splitWords(text):
    text=[x if x in letters else ' ' for x in text]
    text=''.join(text)
    text=text.split()
    text=[x for x in text if x!='']
    return text

def wordCount(words, letter):
    return sum([1 if letter in x else 0 for x in words])
    
def wordsAppear(text):
    words=splitWords(text)
    maxVal='A'
    for x in letters:
        if wordCount(words,x)>wordCount(words, maxVal):
            maxVal=x
    return [maxVal, wordCount(words, maxVal)]
    
def anyAppear(text):
    text=splitWords(text)
    text=''.join(text)
    text=list(set(text))
    text=sorted(text, reverse=True)
    return ''.join(text)

def multAppear(text):
    text=splitWords(text)
    ans=[]
    for x in letters:
        for word in text:
            if word.count(x)>1:
                ans.append(x)
    ans=sorted(list(set(ans)))
    return ''.join(ans)

def appearDes(text):
    text=''.join(splitWords(text))
    ans=[]
    for x in letters:
        if text.count(x)<=1:
            continue
        #we need the subtraction so the sort works in increasing order
        ans.append([len(text)-text.count(x),x])
    ans.sort()
    ans=[x[1] for x in ans]
    return ''.join(ans)

print('ACSL 2015 Problem 5\nCherry Creek High School')

letters='abcdefghijklmnopqrstuvwxyz'.upper()

for repeat in range(2):
    text=input('Input  %d: '%(repeat+1)).upper()
    print('Output %d: %s %d'%(repeat*5+1, maxAppear(text)[0], maxAppear(text)[1]))
    print('Output %d: %s %d'%(repeat*5+2, wordsAppear(text)[0], wordsAppear(text)[1]))
    print('Output %d: %s'%(repeat*5+3, anyAppear(text)))
    print('Output %d: %s'%(repeat*5+4, multAppear(text)))
    print('Output %d: %s'%(repeat*5+5, appearDes(text)))
