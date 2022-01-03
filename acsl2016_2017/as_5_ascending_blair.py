import itertools

def convert_tuple(a):
    return "".join(a)

for count in range(1,11):
    try:
        inp_list = str(input(str(count)+'. '))

        inp_list = list(inp_list)
        n = int(inp_list.pop(0))
        inp_list.sort()
        scope = n

        fin_list = list()

        while len(inp_list) > 0:
            temp_list = inp_list[:scope]
            temp = list(itertools.permutations(temp_list, n))
            temp = list(map(convert_tuple, temp))
            temp.sort()

            if temp == []:
                break

            smallest = temp[0]

            found = True
            if str(smallest)[0] == "0" or int(smallest) in fin_list:
                found = False
                for possible in temp:
                    if str(possible)[0] != "0" and not(int(possible) in fin_list):
                        found = True
                        smallest = int(possible)
                        break
                    
                if found == False:
                    scope += 1
                    if scope > len(inp_list):
                        break
                        
            if found:
                scope = n
                fin_list.append(int(smallest))
                for i in str(smallest):
                    inp_list.pop(inp_list.index(i))
                inp_list.sort()

        print(str(count)+'. '+" ".join(list(map(lambda x:str(x), fin_list))))
    except:
        print('error')
