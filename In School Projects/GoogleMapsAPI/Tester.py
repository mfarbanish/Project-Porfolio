import Trips

def main():
    t = Trips.Trip("Oberlin+OH", "Boston+MA", "Akron+OH")
    t.SetSource("Detroit+MI")
    t.InsertDestination("Berkeley+CA", 1)
    t.RemoveDestination("Boston+MA")
    t.SetSource("Oberlin+OH")
    t.Print()
    print()

    r = Trips.RoundTrip("Oberlin+OH", "Washington+DC", "New+York+NY", "Boston+MA" )
    r.AddDestination("Buffalo+NY")
    r.InsertDestination("Berkeley+CA", 4)
    #r.RemoveDestination("Washington+DC")
    r.SetSource("Akron+OH")
    #print(r.GetDestination())
    r.Print()


main()
