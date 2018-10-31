/*
  Square

  A class representing a square in a maze
*/
public class Square {
   public static final int SPACE=0, WALL=1, START=2, EXIT=3;
   private int type;
   private int row;
   private int column;
   private boolean marked;
   private boolean onpath;
   private Square back; // refers to the prior Square on the path from start to exit
   
   public Square(int type, int row, int column){
      this.type = type;
      this.row = row;
      this.column = column;
      marked = false;
      onpath = false;
      back = null;
   }

   // call this method if this Square is on the path from start to exit
   public void onpath(){
      onpath = true;
   }
 
   // call this method to mark the Square as having been visited
   public void mark(){
      marked = true;
   }

    // restore the square to its initial state   
   public void reset(){
      marked = false;
      onpath = false;
      back = null;
   }
   
    // save a reference to the previous square on the path
   public void setBack(Square sq){
      back = sq;
   }
   
   public Square getBack(){
      return back;
   }
   
   public boolean isMarked(){
      return marked;
   }
   
   public boolean isOnPath(){
      return onpath;
   }

    /* getter methods for row, column, and type */
   public int getRow(){
      return row;
   }
   
   public int getColumn(){
      return column;
   }
   
   public int getType(){
      return type;
   }
   
  public String toString() {
	  return Integer.toString(type);// + Integer.toString(row) + Integer.toString(column);
  }
   
}