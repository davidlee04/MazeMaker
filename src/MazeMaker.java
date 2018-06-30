import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// select a random cell to start
		Cell random = maze.getCell(randGen.nextInt(width), randGen.nextInt(height));
		selectNextPath(random);

		// call selectNextPath method with the randomly selected cell

		return maze;
	}

	private static void selectNextPath(Cell currentCell) {
		// mark current cell as visited
		currentCell.setBeenVisited(true);

		// check for unvisited neighbors
		ArrayList<Cell> unvisitedNeighbors = getUnvisitedNeighbors(currentCell);

		// if has unvisited neighbors,
		// select one at random.
		// push it to the stack
		// remove the wall between the two cells
		// make the new cell the current cell and mark it as visited
		if (unvisitedNeighbors.size() != 0) {
			Cell chosenCell = unvisitedNeighbors.get(randGen.nextInt(unvisitedNeighbors.size()));
			uncheckedCells.push(chosenCell);
			removeWalls(currentCell, chosenCell);
			currentCell = chosenCell;
			currentCell.setBeenVisited(true);
			selectNextPath(currentCell);
		}
		// call the selectNextPath method with the current cell

		// if all neighbors are visited
		// if the stack is not empty
		// pop a cell from the stack
		// make that the current cell
		if (unvisitedNeighbors.size() == 0 && uncheckedCells.size() != 0) {
			currentCell = uncheckedCells.pop();
			selectNextPath(currentCell);
		}
		// call the selectNextPath method with the current cell
	}

	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX() == c2.getX()) {
			if (c1.getY() > c2.getY()) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}
			if (c1.getY() < c2.getY()) {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
		}
		if (c1.getY() == c2.getY()) {
			if (c1.getX() > c2.getX()) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			}
			if (c1.getX() < c2.getX()) {
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
		}
	}

	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		boolean northWall = true;
		boolean southWall = true;
		boolean eastWall = true;
		boolean westWall = true;
		if (c.getX() == 0) {
			westWall = false;
		}
		if (c.getY() == 0) {
			northWall = false;
		}
		if (c.getX() == 4) {
			eastWall = false;
		}
		if (c.getY() == 4) {
			southWall = false;
		}
		ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();

		if (northWall) {
			Cell temp = maze.getCell(c.getX(), c.getY() - 1);
			if (temp.hasBeenVisited() == false) {
				unvisitedNeighbors.add(temp);
			}
		}
		if (eastWall) {
			Cell temp = maze.getCell(c.getX() + 1, c.getY());
			if (temp.hasBeenVisited() == false) {
				unvisitedNeighbors.add(temp);
			}
		}
		if (southWall) {
			Cell temp = maze.getCell(c.getX(), c.getY() + 1);
			if (temp.hasBeenVisited() == false) {
				unvisitedNeighbors.add(temp);
			}
		}
		if (westWall) {
			Cell temp = maze.getCell(c.getX() - 1, c.getY());
			if (temp.hasBeenVisited() == false) {
				unvisitedNeighbors.add(temp);
			}
		}
		return unvisitedNeighbors;
	}
}