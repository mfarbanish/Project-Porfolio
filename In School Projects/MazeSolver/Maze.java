import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;

public class Maze {
   private static int MAXSQSIZE = 30;
   private int height;
   private int width;
   private Square[][] grid;
   private Square startSquare;
   private Square exitSquare;

   Maze() {
	   this.height = 0;
	   this.width = 0;
   }
   
   public int getHeight(){
      return height;
   }
   
   public int getWidth(){
      return width;
   }
   
   public Square getStart(){
      return startSquare;
   }
   
   public Square getExit(){
      return exitSquare;
   }
   
   public Square getSquare(int row, int col){
      return grid[row][col];
   }

    /* resets the maze by resetting all of its squares  */   
   public void reset(){
      for(int r=0; r<height; r++)
         for(int c=0; c<width; c++)
         grid[r][c].reset();
   }
   
   public static Maze loadMaze(String fname) throws FileNotFoundException {
       Maze newmaze = new Maze();
       
       Scanner mazescan = new Scanner(new File(fname));
       //put a try catch around this to check for runtime errors
       //System.out.println(mazescan.next().toString());
       Scanner tempscan = new Scanner(mazescan.nextLine());
       //System.out.println(tempscan.next());

       newmaze.height = Integer.parseInt(tempscan.next());
       newmaze.width = Integer.parseInt(tempscan.next());
       newmaze.grid = new Square[newmaze.height][newmaze.width];
       
       int countrow = 0;
       while(mazescan.hasNextLine()) {
    	   tempscan = new Scanner(mazescan.nextLine());
    	   int countcolumn = 0;
    	   while (tempscan.hasNext()) {
    		   String s = tempscan.next();
    		   newmaze.grid[countrow][countcolumn] = 
    				   new Square(Integer.parseInt(s), countrow, countcolumn);
    		   		   if (Integer.parseInt(s) == 2) {
    		   			   newmaze.startSquare = new Square(2, countrow, countcolumn);
    		   		   }
    		   		   else if (Integer.parseInt(s) == 3) {
    		   			   newmaze.exitSquare = new Square(3, countrow, countcolumn);
    		   		   }
    		   countcolumn += 1;
    	   }
    	   countrow += 1;
       }
	   
	   
	   //System.out.print(newmaze.startSquare.getRow());
       return newmaze; // replace this line
   }
   
   public ArrayList<Square> getNeighbors(Square square){
       ArrayList<Square> neighbors = new ArrayList<Square>();
	   int row = square.getRow();
	   int column = square.getColumn();
	   
	   if (row > 0) {
		   neighbors.add(grid[row-1][column]);
		   //System.out.println(Integer.toString(row-1) + Integer.toString(column));
	   }
	   if (column+1 < width) {
		   neighbors.add(grid[row][column+1]);
		   //System.out.println(Integer.toString(row+1) + Integer.toString(column));
		   //System.out.println("bloop");
	   }
	   if (row+1 < height) {
		   neighbors.add(grid[row+1][column]);
		   
	   }
	   if (column > 0) {
		   neighbors.add(grid[row][column-1]);
	   }
	   
       return neighbors; // replace this line
   }
                   
   public String toString(){
      String mstring = "";
      for(int i=0; i<height; i++){
        for(int j=0; j<width; j++){
           mstring += grid[i][j].toString();
        }
        if(i<height-1)
           mstring+="\n";
      }
      return mstring;
   }

    /* draws the maze on the GUI.  DO NOT CHANGE! */   
   public void draw(Graphics g, Dimension d){
      int sqsize = Math.min(Math.min(d.width/width,d.height/height),MAXSQSIZE);
      int top=(d.height-sqsize*height)/2;
      int left=(d.width-sqsize*width)/2;
      for(int i=0; i<height; i++){
         for(int j=0; j<width; j++){
            Square sq = grid[i][j];
            Color c = Color.white;
            switch(sq.getType()){
               case Square.START: c = Color.green; break;
               case Square.EXIT: c = Color.red; break;
               case Square.WALL: c = Color.black; break;
               case Square.SPACE: c = Color.white;
               if(sq.isMarked())
                  c = Color.lightGray;
               if(sq.isOnPath())
                  c = Color.yellow;
               break;
            }
            g.setColor(c);
            g.fillRect(left+j*sqsize,top+i*sqsize,sqsize,sqsize);
         }
      }
      g.setColor(Color.black);
      for(int i=0; i<height; i++){         
         for(int j=0; j<width; j++){
            g.drawRect(left+j*sqsize,top+i*sqsize,sqsize,sqsize);
         }
      }
   }
}

