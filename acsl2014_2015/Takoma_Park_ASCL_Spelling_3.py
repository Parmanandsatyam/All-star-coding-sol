vowels = ['a','e','i','o','u']
consonants = ['b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t', \
              'v', 'w','x','y','z']
for count in range(5):
    word = input(str(count+1)+". ").strip().lower()
    length = len(word)
    print(str(count+1)+". ", end = "")
    if word[length-2: length] == "ch" or word[length-2: length] == "sh" or \
       word[-1] == "s" or word[-1] == "x" or word [-1] == "z":
        print(word+"es")
    elif word[-1] == "y":
        if word[-2] in consonants:
            print(word[0: -1]+"ies")
        else:
            print(word+"s")
    elif word[-1] == "f":
        print(word[0: -1]+"ves")
    elif word[-2:length] == "fe":
        print(word[0:-2]+"ves")
    elif word[-1] == "o":
        if word[-2] in consonants:
            print(word+"es")
        else:
            print(word+"s")
    else:
        print(word+"s")

for count in range(5):
    inputs = input(str(count+1)+". ").strip().lower().split(",")
    for ele in range(len(inputs)):
        inputs[ele]=inputs[ele].strip()
    word = inputs[0]
    length = len(word)
    ending = inputs[1]
    print(str(count+1)+". ", end="")
    if (word[-1] == "e") and (ending[0] in vowels):
        print(word[0: -1]+ending)
    elif (word[-1] == "y") and not(ending[0] == "i"):
        print(word[0:-1]+"i"+ending)
    elif (word[-1] in consonants) and (word[-2] in vowels) and \
         (word[-3] in consonants):
        print(word+word[-1]+ending)
    else:
        print(word+ending)
input("Press enter to exit the program.")

