import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

//Mahlon Farbanish

public class Bacon {
	private static HashMap<String, LinkedList<String>> actorMap = new HashMap<String, LinkedList<String>>();
	private static HashMap<String, LinkedList<String>> movieMap = new HashMap<String, LinkedList<String>>();
	private static Scanner s;
	private static String center = "Kevin Bacon (I)";
	
	private static HashMap<String, String> visited = new HashMap<String, String>();
	//private static String center = "Bob";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws MalformedURLException, IOException {
		//System.out.println((args[0].substring(0, 5)));
		if (args.length > 1) {
			center = "";
			int argcount = 0;
			for (String item : args) {
			if (argcount == 1) {
				center += item;
			}
			if (argcount > 1) {
				center += " " + item;
			}
			argcount += 1;
			}
		}
		
		if (args[0].substring(0, 5).equals("http:")) {
			s = new Scanner(new URL(args[0]).openStream());
		}
		else {
			//check if this is right
			s = new Scanner(new File(args[0]));
		}
		
		
		//s = new Scanner(new URL("http://www.cs.oberlin.edu/~jdonalds/151/imdb.cslam.txt").openStream());
		
		//fills the two maps
		System.out.println("loading database");
		while (s.hasNextLine()) {
			String[] temp = s.nextLine().split("\\|");
			//System.out.println(temp[0]);
			if (actorMap.containsKey(temp[0])) {
				actorMap.get(temp[0]).add(temp[1]);
			}
			else {
				LinkedList<String> newlink = new LinkedList<String>();
				newlink.add(temp[1]);
				actorMap.put(temp[0], newlink);
			}
			if (movieMap.containsKey(temp[1])) {
				movieMap.get(temp[1]).add(temp[0]);
			}
			else {
				LinkedList<String> newlink = new LinkedList<String>();
				newlink.add(temp[0]);
				movieMap.put(temp[1], newlink);
			}
		}
		
		if (!actorMap.containsKey("Kevin Bacon (I)") && center.equals("Kevin Bacon (I)")) {
			Set<String> actorset = actorMap.keySet();
			int randint = (int) (Math.random()*actorset.size()-2);
			center = (String) actorset.toArray()[randint];
			System.out.println("center has been set to " + center);
		}
		
		//preforms breadth first search
		Queue<String> q = new LinkedList<String>();
		//HashMap<String, String> visited = new HashMap<String, String>();
		visited.put(center, "root");
		q.add(center);
		
		while (!q.isEmpty()) {
			String current = q.remove();
			//System.out.println(current);
			if (actorMap.containsKey(current)) {
				for (String vertex : actorMap.get(current)) {
					if (!visited.containsKey(vertex)) {
						visited.put(vertex, current);
						q.add(vertex);
					}
				}
			}
			else if (movieMap.containsKey(current)) {
				for (String vertex : movieMap.get(current)) {
					if (!visited.containsKey(vertex)) {
						visited.put(vertex, current);
						q.add(vertex);
					}
				}
			}
		}
		
		
		System.out.println("database loaded");
		Scanner input = new Scanner(System.in);
		
		//runs program
		while (input.hasNextLine()) {
			String templine = input.nextLine();
			
			//String entry = "movies Bob";
			//System.out.println(entry.substring(7));
			try {
				if (templine.equals("quit")) {
					break;
				}
				
				else if (templine.equals("help")) {
					System.out.println("help, quit, find <name>, recenter <name>, avgdist, circles, top center<n> movies <name>, topactors");
				}
				
				else if (templine.equals("avgdist")) {
					avgdist();
					
				}
				
				else if (templine.equals("circles")) {
					circles();
				}
				
				else if (templine.substring(0, 4).equals("find")) {
					System.out.println(find(templine));
				}
				
				else if (templine.substring(0, 8).equals("recenter")) {
					String name = templine.substring(9);
					if (actorMap.containsKey(name)) {
						center = name;
						q = new LinkedList<String>();
						visited = new HashMap<String, String>();
						visited.put(center, "root");
						q.add(center);
						
						while (!q.isEmpty()) {
							String current = q.remove();
							//System.out.println(current);
							if (actorMap.containsKey(current)) {
								for (String vertex : actorMap.get(current)) {
									if (!visited.containsKey(vertex)) {
										visited.put(vertex, current);
										q.add(vertex);
									}
								}
							}
							else if (movieMap.containsKey(current)) {
								for (String vertex : movieMap.get(current)) {
									if (!visited.containsKey(vertex)) {
										visited.put(vertex, current);
										q.add(vertex);
									}
								}
							}
						}
						System.out.println("center has been updated");
					}
					else {
						System.out.println(name + " not in database");
					}
				}
				
				else if (templine.substring(0, 9).equals("topcenter")) {
					topcenter(Integer.parseInt(templine.substring(10)));
				}
				
				else if (templine.substring(0, 6).equals("movies")) {
					movies(templine.substring(7));
				}
				
				else if (templine.substring(0, 9).equals("topactors")) {
					topactors(Integer.parseInt(templine.substring(10)));
				}
				
			}
			
			
			catch (Exception e) {
				System.out.println("not a proper command, try again");
			}
		}
		
		
	}
	//make vertex class
	
	private static String find(String n) {
		
		String entry = n.substring(5);
		
		String path = "";
		String temp = entry;
		if (actorMap.get(temp) == null) {
			return temp + " is not in database";
		}
		
		path += temp; 
		int pathcount = 0;
		while (!temp.equals(center)) {
			temp = visited.get(temp);
			if (visited.get(temp) == null) {
				return entry + " is unreachable";
			}
			path = temp + " -> " + path;
			pathcount += 1;
		}
		
		path += " (" + pathcount/2 + ")";
		
		return path;
		
		
		
	}
	
	public static void avgdist() {
		//System.out.println(visited.toString());
		//LinkedList<Integer> pathlengths = new LinkedList<Integer>();
		int reachable = 0;
		float pathtotal = (float) 0;
		for (String name : visited.keySet()) {
			if (actorMap.containsKey(name)) {
				reachable += 1;
				int pathcount = 0;
				String temp = name;
				while (!temp.equals(center)) {
					pathcount += 1;
					temp = visited.get(temp);
				}
				pathtotal += (float) pathcount;
				//pathlengths.add(pathcount/2);
			}
			
		}
		
		float ret = ((float) (pathtotal/2))/(float) reachable;
		int unreach = actorMap.size() - reachable;
		System.out.println(ret + "   " + center + " " + reachable + " " + unreach);
		
		
	}
	
	public static void circles() {
		//System.out.println("here");
		HashMap<Integer, Integer> circleMap = new HashMap<Integer, Integer>();
		HashMap<Integer, LinkedList<String>> circleNames = new HashMap<Integer, LinkedList<String>>();
		if (actorMap.containsKey(center)) {
			for (String name : visited.keySet()) {
				if (actorMap.containsKey(name)) {
					int pathcount = 0;
					String temp = name;
					while (!temp.equals(center)) {
						pathcount += 1;
						temp = visited.get(temp);
					}
					if (circleMap.containsKey(pathcount/2)) {
						int counter = circleMap.get(pathcount/2) + 1;
						circleMap.put(pathcount/2, counter);
						circleNames.get(pathcount/2).add(name);
					}
					else {
						circleMap.put(pathcount/2, 1);
						LinkedList<String> namelist = new LinkedList<String>();
						namelist.add(name);
						circleNames.put(pathcount/2, namelist);
					}
				}
			}
			for (int i = 0; i < 10; i++) {
				boolean contains = circleNames.containsKey(i);
				if (contains == false) {
					break;
				}
				System.out.printf("%-2d %-7d %s", i, circleMap.get(i), "       [");
				//System.out.print(i + "    " + circleMap.get(i) + "    [");
				int breakcount = 0;
				for (String actor : circleNames.get(i)) {
					if (breakcount > 2) {
						break;
					}
					System.out.print(actor + ", ");
					breakcount += 1;
				}
				
				System.out.println("]");
			}
		}
		else {
			System.out.println("center has no connections in this dataset");
		}
	}
	
	public static void topcenter(int n) {
		//everything above is fine

		HashMap<Float, LinkedList<String>> actorAv = new HashMap<Float, LinkedList<String>>();

		for (String centerActor : visited.keySet()) {
			if (actorMap.containsKey(centerActor)) {
				float tempavg = avgdisthelp(centerActor, visited);
				if (actorAv.containsKey(tempavg)) {
					actorAv.get(tempavg).add(centerActor);
				}
				else {
					LinkedList<String> templist = new LinkedList<String>();
					templist.add(centerActor);
					actorAv.put(tempavg, templist);
				}
			}
		}
		
		for (String centerActor : visited.keySet()) {
			if (actorMap.containsKey(centerActor)) {
				float tempavg = avgdisthelp(centerActor, visited);
				if (actorAv.containsKey(tempavg)) {
					actorAv.get(tempavg).add(centerActor);
				}
				else {
					LinkedList<String> templist = new LinkedList<String>();
					templist.add(centerActor);
					actorAv.put(tempavg, templist);
				}
			}
		}
		
		
		//this prints n top names
		int count = 0;
		//System.out.println(actorAv.keySet().toString());
		while (count < n) {
			String tempact = "";
			//System.out.println(actorMap.toString());
			try {
				Float tempKey = Collections.min(actorAv.keySet());
				for (String actor : actorAv.get(tempKey)) {
					tempact = actor;
					if (count >= n) {
						break;
					}
					//printf("'%-5d'", 10);
					System.out.printf("%-2d %-20s %f \n", count+1, actor, tempKey);
					//System.out.println(count+1 + ". " + actor + "    " + tempKey);
					count += 1;
					break;
				}
				LinkedList<String> tempList = actorAv.get(tempKey);
				while (tempList.contains(tempact)) {
					tempList.remove(tempact);
				}
				if (tempList.isEmpty()) {
					actorAv.remove(tempKey);
				}
			}
			catch (Exception f) {
				break;
			}
			
		}
	}
		//this is a topcenter helper
	public static float avgdisthelp(String cent, HashMap<String, String> priorvis) {
			Queue<String> q = new LinkedList<String>();
			HashMap<String, String> visited = new HashMap<String, String>();
			visited.put(cent, "root");
			q.add(cent);
			
			while (!q.isEmpty()) {
				String current = q.remove();
				//System.out.println(current);
				boolean vistcheck = priorvis.containsKey(current);
				if (actorMap.containsKey(current) && vistcheck) {
					for (String vertex : actorMap.get(current)) {
						if (!visited.containsKey(vertex)) {
							visited.put(vertex, current);
							q.add(vertex);
						}
					}
				}
				else if (movieMap.containsKey(current) && vistcheck) {
					for (String vertex : movieMap.get(current)) {
						if (!visited.containsKey(vertex)) {
							visited.put(vertex, current);
							q.add(vertex);
						}
					}
				}
			}

			int reachable = 0;
			float pathtotal = (float) 0;
			for (String name : visited.keySet()) {
				if (actorMap.containsKey(name)) {
					reachable += 1;
					int pathcount = 0;
					String temp = name;
					while (!temp.equals(cent)) {
						pathcount += 1;
						temp = visited.get(temp);
					}
					pathtotal += (float) pathcount;
				}
				
			}
			
			float ret = ((float) (pathtotal/2))/(float) reachable;
			return ret;	
	}

		
	public static void movies(String name) {
		int count = 0;
		System.out.println(name + " appears in: ");
		for (String movie : actorMap.get(name)) {
			if (count >= 20) {
				break;
			}
			System.out.println(movie);
			count += 1;
		}
	}
	
	public static void topactors(int n) {
		HashMap<String, LinkedList<String>> tempact = actorMap;
		for (int i = 0; i < n; i++) {
			int maxcount = 0;
			String maxname = "";
			for (String name : tempact.keySet()) {
				int listLen = tempact.get(name).size();
				if (listLen >= maxcount) {
					maxname = name;
					maxcount = listLen;
				}
			}
		tempact.remove(maxname);
		System.out.println(maxname);
		}
	}
	

}
