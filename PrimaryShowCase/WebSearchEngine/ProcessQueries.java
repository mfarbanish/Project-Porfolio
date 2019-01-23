import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class ProcessQueries {

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<WebPageIndex> webList = new ArrayList<WebPageIndex>();
		
		System.out.print("Enter a query: ");
		Scanner scanq = new Scanner(System.in);
		
		
		Scanner scanurl = new Scanner(new File(args[0])); //replace with arg[0]
		
		String temp = scanurl.next();
		while (scanurl.hasNext()) {
			WebPageIndex webinput = new WebPageIndex(temp);
			webList.add(webinput);
			temp = scanurl.next();
		}
		
		
		while (scanq.hasNextLine()) {
			String q = scanq.nextLine();
			if (q.equals("")) {
				break;
			}
			
			q.toLowerCase();
			
			String[] qArray = q.split("\\s+");
			List<String> qList = new ArrayList<String>();
			
			for (String item : qArray) {
				qList.add(item);
			}
			URLComparator comp = new URLComparator(qList);
			MyPriorityQueue heap = new MyPriorityQueue(comp);
			//System.out.println(heap);
			for (WebPageIndex page : webList) {
				//System.out.println(page.toString());
				heap.add(page);
			}
			//System.out.println(heap);
			int count = -1;
			if (args.length == 2) {
				count = Integer.parseInt(args[1]);
			}
			
			while (heap.isEmpty() == false) {
				//System.out.print("*******");
				if (count == 0) {
					break;
				}
				
				WebPageIndex temp2 = (WebPageIndex) heap.remove();
				for (String word : qList) {
					if (temp2.contains(word.toLowerCase())) {
						System.out.println("(score: " + comp.score(temp2) + ") " + temp2.getUrl());
						break;
					}
				}

				
				if (count != -1) {
					count -= 1;
				}
			}
			System.out.print("Enter a query: ");
		}
		
		
		
	}

}
