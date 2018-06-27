import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//select a random cell to start
		Cell random = maze.getCell(randGen.nextInt(width), randGen.nextInt(height));
		selectNextPath(random);
		
		//call selectNextPath method with the randomly selected cell
		
		return maze;
	}

	private static void selectNextPath(Cell currentCell) {
		// mark current cell as visited
		currentCell.setBeenVisited(true);

		// check for unvisited neighbors
		if(currentCell.getX() == 0) {
			currentCell.setNorthWall(false);
		}
		if(currentCell.getY() == 0) {
			currentCell.setWestWall(false);
		}
		if(currentCell.getX() == 4) {
			currentCell.setSouthWall(false);
		}
		if(currentCell.getY() == 4) {
			currentCell.setEastWall(false);
		}
		ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();
		for (int i = 0; i < 4; i++) {
			if(i == 0) {
				if(currentCell.hasNorthWall() == true) {
					unvisitedNeighbors.add(maze.getCell(currentCell.getX(), currentCell.getY()-1));
				}
				if(currentCell.hasEastWall() == true) {
					unvisitedNeighbors.add(maze.getCell(currentCell.getX()+1, currentCell.getY()));
				}
				if(currentCell.hasSouthWall() == true) {
					unvisitedNeighbors.add(maze.getCell(currentCell.getX(), currentCell.getY()+1));
				}
				if(currentCell.hasWestWall() == true) {
					unvisitedNeighbors.add(maze.getCell(currentCell.getX()-1, currentCell.getY()));
				}
			}
		}
		Cell chosenCell = unvisitedNeighbors.get(randGen.nextInt(unvisitedNeighbors.size()));
		uncheckedCells.push(chosenCell);
		//NOTE TO SELF: FIGURE OUT HOW TO DETERMINE WHICH WALL IS BETWEEN THE TWO CELLS
		
		// if has unvisited neighbors,
			// select one at random.
			// push it to the stack
			// remove the wall between the two cells
			// make the new cell the current cell and mark it as visited
		
			//call the selectNextPath method with the current cell
			
		// if all neighbors are visited
			//if the stack is not empty
				// pop a cell from the stack
				// make that the current cell
		
				//call the selectNextPath method with the current cell
	}

	private static void removeWalls(Cell c1, Cell c2) {
		
	}

	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		return null;
	}
}