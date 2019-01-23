import java.util.*;

class MyPriorityQueue<E> implements PriorityQueueADT<E> {
   
    // you can use either of the following declarations for your heap
    ArrayList<E> heap = new ArrayList<E>();
    //E[] heap;

   Comparator<E> comparator;
      
   MyPriorityQueue(Comparator<E> comparator){
      this.comparator = comparator;
      this.heap = new ArrayList<E>();
   }
   
   public boolean add(E item){
      heap.add(item);
      this.siftUp();
      return true;
   }
   
   public E remove(){
	   E temp = heap.remove(0);
	   if (heap.size() == 0) {
		   return temp;
	   }
	   this.siftDown();
	   return temp;
   }
   
   public boolean isEmpty(){
	   return heap.isEmpty();
   }
   
   public int size(){
	   return heap.size();
   }

   public void clear(){
       heap.clear();
   }

   public String toString(){
      //TODO:  Write this method
      return heap.toString();
   }
   
   private void siftUp(){
	   
      E temp = heap.get(heap.size()-1);
      while (true) {

    	  if (!temp.equals(heap.get(0))) {
	    	  int tempind = heap.indexOf(temp);
		      E par = heap.get(parent(tempind));

		      if (comparator.compare(par, temp) > 0){
		    	  heap.set(heap.indexOf(par), temp);
		    	  //System.out.println(temp);
		    	  heap.set(tempind, par);
		    	  //System.out.println(heap.toString());
		    	  //temp = par;
		      }

		      
		      else {
		    	  break;
		      }
	      }
      else {
    	  break;
      }
      }
   }
         
   private void siftDown(){
      E temp = heap.get(0);
      while (true) {
    	  if (heap.size() == 0) {
    		  break;
    	  }
    	  int indextemp = heap.indexOf(temp);
    	  //check if temp has children
    	  //does this by checking if heap size is smaller than the index of the children
    	  if (!(leftChild(indextemp) > heap.size()-1)) {
    		  E childl = heap.get(leftChild(indextemp));
    		  if (!(rightChild(indextemp) > heap.size()-1)) {
	    		  E childr = heap.get(rightChild(indextemp));
	    		  
	    		  int larger = comparator.compare(childl, childr);
		    		  if (larger > 0) {
		    			  if (comparator.compare(temp, childr) > 0) {
		    				  heap.set(heap.indexOf(childr), temp);
		    				  heap.set(indextemp, childr);
		    				  
		    				  temp = childr;
		    			  }
		    		  } 
    		  	}
    		  if (comparator.compare(temp, childl) > 0) {
    			  heap.set(heap.indexOf(childl), temp);
				  heap.set(indextemp, childl);
				  
				  temp = childl;
    		  }
    		  else {
    			  break;
    		  }
    	  }
    	  else {
    		  break;
    	  }
      }
   }   

   private int parent(int x){
	   return (x-1)/2;
   }
   
   private int leftChild(int x){
	   return 2*x+1;
   }
   
   private int rightChild(int x){
       //TODO:  Write this method
       return 2*x+2;
   }
   
}