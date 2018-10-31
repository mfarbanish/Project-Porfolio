import java.util.*;

class MazeSolver {
    public static final int STACK=1,QUEUE=2;
    private Worklist<Square> worklist;
    protected Maze maze;
    protected boolean finished=false;
    protected boolean pathFound=false;
    
    MazeSolver(Maze maze, Worklist<Square> worklist){
	this.maze = maze;
	this.worklist = worklist;
	this.worklist.clear();
	this.worklist.add(maze.getStart());
    }

    public boolean isFinished(){
	return finished;
    }
    
    public boolean pathFound(){
	return pathFound;
    }
    
    /* makes a list of the squares on a path from the start square
       to the exit square */
    public List<Square> getPath(){
    	while (finished == false) {
    		this.step();
    	}
    	
    if (pathFound == false)	{
    	return new ArrayList<Square>();
    }
    else {
    	Square tempsq = worklist.peek();
    	ArrayList<Square> path = new ArrayList<Square>();
    	while (tempsq.getBack() != null) {
    		//maybe add this to index 0
    		path.add(tempsq);
    		tempsq.onpath();
    		tempsq = tempsq.getBack();
    	}
    	
    	return path;
    }
    
    }
    
    /* performs one step of the maze solver algorithm */
    public void step(){
    	if (worklist.isEmpty() == true) {
    		finished = true;
    		pathFound = false;
    	}
    	else if (worklist.peek().getType() == 3) {
			pathFound = true;
			finished = true;
		}
		else {
			Square temp = worklist.remove();
			temp.mark();
			ArrayList<Square> neighbor = maze.getNeighbors(temp);
			for (Square item : neighbor) {
				if (item.getType() != 1 && !item.isMarked()) {
					item.setBack(temp);
					worklist.add(item);
				}
			}
			
		}
    }
}
