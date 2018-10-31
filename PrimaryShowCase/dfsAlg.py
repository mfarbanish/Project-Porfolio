#HW #4
#Due Date: 07/27/2018, 11:59PM EST

#Name:Mahlon

class Node:
    def __init__(self, value):
        self.value = value  
        self.next = None 
    
    def __str__(self):
        return "Node({})".format(self.value) 

    __repr__ = __str__
                        
class Stack:
    def __init__(self):
        self.top = None
        self.count = 0
        
    def __str__(self):
        temp=self.top
        out=''
        while temp:
            out+=str(temp.value)+ '\n'
            temp=temp.next
        return ('Top:{}\nStack:\n{}'.format(self.top,out))

    __repr__=__str__


    def isEmpty(self):
        return self.top == None

    def size(self):
        return self.count
    
    def peek(self):
        if self.isEmpty() == False:
            return self.top.value
        else:
            return "Stack is Empty"

    def push(self,value):
        newitem = Node(value)
        if self.top == None:
            self.top = newitem
        else:
            newitem.next = self.top
            self.top = newitem
        self.count += 1
        

    def pop(self):
        temp = self.top
        if self.isEmpty() == False:
            self.top = temp.next
            self.count -= 1
            return temp.value
        else:
            return "Stack is Empty"


class Vertex:
    def __init__(self,value):
        self.value = value
        self.connectedTo = {}

    def addNeighbor(self,node,weight=1):
        self.connectedTo[node] = weight

    def __str__(self):
        return str(self.value) + ': ' + str([x.value for x in self.connectedTo])
        
class Graph:
    def __init__(self):
        self.vertList = {}
        #self.dfsList = []

    def __iter__(self):
        return iter(self.vertList.values())
        
    def getVertex(self,key):
        if key in self.vertList:
            return self.vertList[key]
        else:
            return None

    def addVertex(self,key):
        new_node = Vertex(key)
        self.vertList[key] = new_node
        return new_node

    def addEdge(self,frm,to,weight=1):
        if frm not in self.vertList:
            new_node = self.addVertex(frm)
        if to not in self.vertList:
            new_node = self.addVertex(to)
        self.vertList[frm].addNeighbor(self.vertList[to], weight)


    def dfs(self, start):
        self.dfsList = []
        dfsStack = Stack()
        dfsStack.push(self.vertList[start])
        #self.dfsList.append(start)
        #print(dfsStack)
        while dfsStack.top != None:
            print(dfsStack)
            val = dfsStack.peek()
            end = False
            #print(val)
            for i in self.vertList[val.value].connectedTo:
                if i.value not in self.dfsList:
                    end =True
                    if val.value not in self.dfsList:
                        self.dfsList.append(val.value)
                    dfsStack.push(i)
                    break
                
            if val.value not in self.dfsList:
                self.dfsList.append(val.value)
                    
                    
            elif end == False:
                dfsStack.pop()
            
            
        return (self.dfsList)
        
