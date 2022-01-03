import string

class Node():
    parent = None
    left = None
    right = None
    value = None
    def __init__(self, p, v):
        self.parent = p
        self.value = v

def search(node, level):
    maxDepth = level
    leafNodes = 0
    oneChild = 0
    internalPathLength = level
    externalPathLength = 0
    if node.left and node.right:
        R = search(node.left, level+1)
        maxDepth = max(maxDepth,R[0])
        leafNodes += R[1]
        oneChild += R[2]
        internalPathLength += R[3]
        externalPathLength += R[4]
        
        R = search(node.right, level+1)
        maxDepth = max(maxDepth,R[0])
        leafNodes += R[1]
        oneChild += R[2]
        internalPathLength += R[3]
        externalPathLength += R[4]
    elif node.left:
        oneChild += 1
        externalPathLength += level+1

        R = search(node.left, level+1)
        maxDepth = max(maxDepth,R[0])
        leafNodes += R[1]
        oneChild += R[2]
        internalPathLength += R[3]
        externalPathLength += R[4]
    elif node.right:
        oneChild += 1
        externalPathLength += level+1

        R = search(node.right, level+1)
        maxDepth = max(maxDepth,R[0])
        leafNodes += R[1]
        oneChild += R[2]
        internalPathLength += R[3]
        externalPathLength += R[4]
    else:
        leafNodes += 1
        externalPathLength += 2*(level+1)
    return [maxDepth, leafNodes, oneChild, internalPathLength, externalPathLength]
        
        
    
def solve(string):
    string = [ord(x) for x in string]
    root = Node(None, string[0])
    for c in string[1:]:
        t = root
        while True:
            if c <= t.value:
                if not t.left:
                    t.left = Node(t,c)
                    break
                t = t.left
            elif c > t.value:
                if not t.right:
                    t.right = Node(t,c)
                    break
                t = t.right
    return search(root, 0)

for i in range(1,3):
    try:
        raw = input(str(i)+'. ')
        raw = ''.join([x if x in string.ascii_letters else '' for x in raw])
        O = solve(raw)
        for j in range(5):
            print(str((i-1)*5+1+j)+'. '+str(O[j]))
    except:
        print('error')

                
        
            
    
