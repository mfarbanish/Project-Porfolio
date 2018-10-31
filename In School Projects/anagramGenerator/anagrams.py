#anagrams.py

#Dizzy Farbanish
#04/16/2018

import sys


'''
text = input("input a text file: ")
inpword = input(str("give me a word: "))
length = int(input("what minimum length would you like: "))
maxwords = int(input("what max amount of words would you like in an anagram: "))
'''

text = sys.argv[2]

try:
    inpword = str(sys.argv[1])
    length = int(sys.argv[3])
    maxwords = int(sys.argv[4])
    file = open(text, "r")
    file2 = open(text, "r")
    #switch the test file with the text variable
    s = file.readlines()
    
    def contains(word, inputstr):
        letterstr = ""
        inpstrset = []
        bool = True
        for letter in inputstr:
            if letter !=" ":
                inpstrset.append(letter)
        for ltr in word:
            for ltr2 in inpstrset:
                if ltr2 == ltr:
                    inpstrset.remove(ltr)
                    break
            #this is the problem with this chunk
            #have it loop through so it doesnt take out all letters ask lab helper about this
            else:
                bool = False
        for i in inpstrset:
            letterstr = letterstr + i
            #makes string to return this part is working fine
        if bool == False:
            letterstr = ""
        return (bool, letterstr)
                   
    def grams(s, words, sofar):
        #print("recurse")
        basebool = False
        for word in words:
            #print(word, s)
            boolgram, v = contains(word, s)
            if  boolgram == True:
                basebool = True
                newsofar = list(sofar)
                if len(word) >= length:
                    newsofar.append(word)
                grams(v, words, newsofar)
                #print(sofar)
                #break
        if basebool == False:
            spacecount = 0
            sofar2 = " ".join(sofar)
            for let in sofar2:
                if let == " ":
                    spacecount = spacecount + 1
            if len(s) == 0 and len(sofar2.replace(" ", "")) == len(inpword) and spacecount < maxwords:
                #this part determines if the anagram fits the users parameters and then prints it if it does
                print(sofar2)
            return sofar
        #grams(v, words, newsofar)
       
    
    def main():
        words = set()
        newword = ""
        sofar = []
        for i in s:
            newword = ""
            for j in range(0, len(i)-1):
                newword = newword + i[j]
            words.add(newword)
        words2 = set()
        for p in words:
            words2.add(p)
        #print(words)
        #preprocessing
        for word in words:
            if contains(word, inpword)[0] == False:
                words2.remove(word)
        words = words2
        #print(words)
        grams(inpword, words, sofar)
            
        
    main()
    
except FileNotFoundError:
        print(text, "is missing from your current directory.")
except ValueError:
        print("Make sure you input strings and integers in the right places :-)")
