import urllib.request

def distance (start, dest):
    # Gives the  distance in meters from
    # start location to dest location
    url = "http://maps.googleapis.com/maps/api/distancematrix/json?origins="+start+"&destinations="+dest+"&mode=driving&sensor=false"
    web_obj = urllib.request.urlopen(url)
    results = web_obj.read()
    web_obj.close()

    D = eval(results)
    F = D["rows"][0]["elements"]
    dist = F[0]["distance"]["value"]
    return dist

def main():
    d = distance( "Oberlin+OH", "New+York+NY")
    print("Oberlin is", d, "meters from New York")

main()
