#Trips.py

#Dizzy Farbanish

import urllib.request

class Trip:
    def __init__(self, source = "", *destinations):
        self.source = source
        self.destinations = destinations
        self.distlist = []
        
    def stored(self):
        self.distlist = []
        oldtemp = self.source
        for i in self.destinations:
            #print(oldtemp)
            #add meters to miles conversion
            url = "http://maps.googleapis.com/maps/api/distancematrix/json?origins="+oldtemp+"&destinations="+i+"&mode=driving&sensor=false"
            web_obj = urllib.request.urlopen(url)
            results_str = str(web_obj.read())
            web_obj.close()
       
            D = eval(eval(results_str))
            F = D["rows"][0]["elements"]
            dist = F[0]["distance"]["value"]
            self.distlist.append(dist*0.000621371)
            oldtemp = i
        #sumtotal = 0
        
    def Length(self):
        self.stored()
        for i in self.distlist:
            sumtotal = sumtotal + i
        return sumtotal
    
    def SetSource(self, city):
        self.source = city
        self.stored()
    
        
    def InsertDestination(self, city, n):
        #check this one over
        temp = []
        for i in range(len(self.destinations)+1):
            if n == i:
                temp.append(city)
            if i == len(self.destinations) and city not in temp:
                temp.append(city)
                break
            elif i != len(self.destinations):
                temp.append(self.destinations[i])
        self.destinations = tuple(temp)
        self.stored()
        
    def AddDestination(self, city):
        temp = []
        for i in self.destinations:
            temp.append(i)
        temp.append(city)
        self.destinations = tuple(temp)
        self.stored()
        
    def RemoveDestination(self, city):
        self.Length
        temp = []
        for i in self.destinations:
            temp.append(i)
        if city not in temp:
            #maybe change this to a return
            print("No such city in destination list.")
            pass
        while city in temp:
            temp.remove(city)
        self.destinations = temp
        self.stored()
        
    def GetSource(self):
        return self.source
        
    def GetDestination(self):
        temp = []
        for i in self.destinations:
            temp.append(i)
        return temp
    
    def Print(self):
        num = 0.00
        print(self.source, " ", num)
        #print(self.distlist)
        for i in range(len(self.destinations)):
            num = num + self.distlist[i]
            print(self.destinations[i], " ", round(num,2))
        
      
    
class RoundTrip(Trip):
    def __init__(self, source = "", *destinations):
        self.source = source
        self.destinations = destinations
        self.distlist = []
        
    def stored(self):
        self.distlist = []
        oldtemp = self.source
        for i in self.destinations:
            #print(oldtemp)
            #add meters to miles conversion
            url = "http://maps.googleapis.com/maps/api/distancematrix/json?origins="+oldtemp+"&destinations="+i+"&mode=driving&sensor=false"
            web_obj = urllib.request.urlopen(url)
            results_str = str(web_obj.read())
            web_obj.close()
       
            D = eval(eval(results_str))
            F = D["rows"][0]["elements"]
            dist = F[0]["distance"]["value"]
            self.distlist.append(dist*0.000621371)
            oldtemp = i
        url = "http://maps.googleapis.com/maps/api/distancematrix/json?origins="+oldtemp+"&destinations="+self.source+"&mode=driving&sensor=false"
        web_obj = urllib.request.urlopen(url)
        results_str = str(web_obj.read())
        web_obj.close()
       
        D = eval(eval(results_str))
        F = D["rows"][0]["elements"]
        dist = F[0]["distance"]["value"]
        self.distlist.append(dist*0.000621371)
       
    def RemoveDestination(self, city):
        self.Length
        temp = []
        for i in range(len(self.destinations)-1):
            temp.append(self.destinations[i])
        if city not in temp:
            #maybe change this to a return
            print("No such city in destination list.")
            pass
        while city in temp:
            temp.remove(city)
        self.destinations = temp
        self.stored()
    
    
    def InsertDestination(self, city, n):
        #check this one over
        temp = []
        for i in range(len(self.destinations)):
            if n == i:
                temp.append(city)
            if i == len(self.destinations)-1 and city not in temp:
                temp.append(city)
                break
            elif i != len(self.destinations)-1:
                temp.append(self.destinations[i])
        temp.append(self.source)
        self.destinations = tuple(temp)
        self.stored()   
    
    def GetDestination(self):
        temp = []
        for i in range(len(self.destinations)-1):
            temp.append(self.destinations[i])
        return temp

    def SetSource(self, city):
        temp = []
        for i in self.destinations:
            if i != self.source:
                temp.append(i)
        temp.append(city)
        self.destinations = tuple(temp)
        self.source = city
        self.stored()
        
    def AddDestination(self, city):
        temp = []
        for i in self.destinations:
            if i != self.source:
                temp.append(i)
        temp.append(city)
        temp.append(self.source)
        self.destinations = tuple(temp)
        self.stored()
        
            