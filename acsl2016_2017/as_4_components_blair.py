import copy

def matrix_print(a):
    for row in a:
        print(row)

def matrix_multiply(a, b):
    # Get dimensions
    dim = len(a)
    
    # New matrix
    prod = []
    for i in range(0, dim):
        prod.append([0] * dim)

    # Do the multiplication
    for i in range(0, dim):
        for j in range(0, dim):
            # Pairwise-multiply A's row i by B's col j
            s = 0
            for k in range(0, dim):
                s += a[i][k] * b[k][j]

            # Put it in the new matrix
            prod[i][j] = s

    # Return result
    return prod

def matrix_add(a, b):
    # Get dimensions
    dim = len(a)

    # Do the sum
    for i in range(0, dim):
        for j in range(0, dim):
            a[i][j] = a[i][j] + b[i][j]

for i in range(0, 10):
    try:
        # Get the input
        data = input().split(", ")

        # Number of vertices
        nverts = int(data[0])

        # Bitstring
        hexstr = data[1]
        bitstr = ""
        for digit in hexstr:
            binary = bin(int(digit,16)).replace("0b","")
            binary = ("0" * (4 - len(binary))) + binary

            bitstr += binary

        #print(nverts, bitstr)

        # Make an empty adjacency matrix
        graph = []
        for j in range(0, nverts):
            graph.append([0] * nverts)

        # Build the adjacency matrix
        for j in range(1, nverts+1):
            # Number of adjacency entries to look at
            nadj = nverts - j

            # Get the adjacency entries, and cut them out of the list
            adj = bitstr[0:nadj]
            bitstr = bitstr[nadj:]

            # Build the adjacency matrix
            for k in range(0, nadj):
                # Adjacent
                if adj[k] == "1":
                    graph[j-1][j + k] = 1
                    graph[j + k][j-1] = 1

        #matrix_print(graph)

        # Find paths
        allpaths = copy.deepcopy(graph)

        pwr = copy.deepcopy(graph)
        for j in range(0, nverts-1):
            pwr = matrix_multiply(pwr, graph)
            matrix_add(allpaths, pwr)

        #matrix_print(allpaths)

        # Get the components
        nodes = list(range(0, nverts))
        comps = []
        a_comp = None
        while len(nodes) > 0:
            # Pick unclassified start node
            start = nodes[0]

            # Find all paths from start to other nodes
            comp = [start]
            for j in range(0, nverts):
                if allpaths[start][j] != 0:
                    comp.append(j)
            comp = list(set(comp))

            # Take out everything in comp
            for x in comp:
                nodes.remove(x)

            # Add to overall list of components
            comps.append(comp)

            # If A is in this, save it
            if 0 in comp: a_comp = comp

        # Print output
        a_comp.sort()
        a_comp = "".join([chr(ord('A') + x) for x in a_comp])

        print(len(comps), a_comp)
    except:
        print("Error")
