import itertools

def diff(x, y):
    return abs(x-y)

def cut(x):
    return x.lstrip('0')

def solve(n, k):
    P = [''.join(x) for x in list(itertools.permutations(n))]
    N = []
    D = {}
    for p in P:
        N.append(int(p,36))
        D[int(p,36)] = p
    N = sorted(N)
    smallest = cut(D[N[0]])
    largest = cut(D[N[-1]])
    fifty = cut(D[N[-50]])
    kth = cut(D[N[k-1]])
    
    mean = (N[0]+N[-1])/2
    minDiff = diff(mean,N[0])
    closest = [N[0]]
    for n in N:
        d = diff(mean, n)
        if d == minDiff:
            closest.append(n)
        elif d < minDiff:
            closest = [n]
            minDiff = d
    closest = [cut(D[x]) for x in closest]
            
    return [smallest, largest, fifty, kth, closest]

for i in range(1,3):
    try:
        raw = input(str(i)+'. ').replace(' ','').split(',')
        n = raw[0]
        k = int(raw[1])
        O = solve(n,k)
        O[4] = ' '.join(O[4])
        for j in range(5):
            print(str((i-1)*5+1+j)+'. '+O[j])
    except:
        print('error')
    
