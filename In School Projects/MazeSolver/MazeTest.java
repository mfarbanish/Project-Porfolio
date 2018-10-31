import static org.junit.Assert.*;

import java.io.FileNotFoundException;


import org.junit.Test;

public class MazeTest {

	@Test
	public void testGetHeight() throws FileNotFoundException {
		Maze maze = Maze.loadMaze("maze-2");
		assertEquals("check height", 7, maze.getHeight());
	}

	@Test
	public void testGetWidth() throws FileNotFoundException {
		Maze maze = Maze.loadMaze("maze-2");
		assertEquals("check height", 13, maze.getWidth());
	}

	@Test
	public void testGetStart() throws FileNotFoundException {
		Maze maze = Maze.loadMaze("maze-2");
		//System.out.println(maze.getStart());
		assertEquals("check height", "2", maze.getStart().toString());
	}

	@Test
	public void testGetExit() throws FileNotFoundException {
		Maze maze = Maze.loadMaze("maze-2");
		//System.out.println(maze.getStart());
		assertEquals("check height", "3", maze.getExit().toString());
	}

	@Test
	public void testGetSquare() throws FileNotFoundException {
		Maze maze = Maze.loadMaze("maze-2");
		
		assertEquals("test", maze.getSquare(6, 4).toString(), "2");
	}


	@Test
	public void testLoadMaze() throws FileNotFoundException {
		Maze maze = Maze.loadMaze("maze-2");
	}

	@Test
	public void testGetNeighbors() throws FileNotFoundException {
		Maze maze = Maze.loadMaze("maze-2");
		
		//System.out.print(maze.getNeighbors(maze.getSquare(6, 4)).toString());
		
	}

	@Test
	public void testToString() throws FileNotFoundException {
		Maze maze = Maze.loadMaze("maze-2");
		//System.out.print(maze.toString());
		//this creates a maze identical to the one in the inputed file
		
	}

}
