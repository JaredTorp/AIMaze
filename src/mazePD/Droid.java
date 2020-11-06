package mazePD;

import mazePD.Maze.Content;
import mazePD.Maze.Direction;

public class Droid implements DroidInterface {
	
	private Cell[][][] cells;
	private Maze botMaze;
	private Coordinates currentLocation;
	private String name;
	private Content[] surroundings;
	private LinkedStack<Coordinates> stack;
	
	public Droid()
	{
		
		stack = new LinkedStack<Coordinates>();
	}
	public Droid(Maze m, Coordinates c, String n )
	{
		setBotMaze(m);
		currentLocation = c;
		setName(n);
		
	}



	public Maze getBotMaze() {
		return botMaze;
	}



	public void setBotMaze(Maze botMaze) {
		this.botMaze = botMaze;
	}



	public Coordinates getCurrentLocation() {
		return currentLocation;
	}



	public void setCurrentLocation(Coordinates currentLocation) {
		this.currentLocation = currentLocation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Cell[][][] getCells() {
		return cells;
	}
	public void setCells(Cell[][][] cells) {
		this.cells = cells;
	}
	public Content[] getSurroundings() {
		return surroundings;
	}
	public void setSurroundings(Content[] surroundings) {
		this.surroundings = surroundings;
	}
	
	
	//THIS is the method that will complete the maze
	public void runMaze(Maze maze) {
		
		this.setBotMaze(maze); //bind the maze to the robot
		int x = maze.getMazeDim();
		int y = maze.getMazeDim();
		int z = maze.getMazeDepth();
		cells = new Cell[x][y][z];
		
		for (int i = 0; i < z; i++)
		{
			for (int j = 0; j < x; j++)
			{
				for (int k = 0; k < y; k++)
				{
					cells[j][k][i] = new Cell(new Coordinates(j,k,i), Content.NA, false);
				}
			}
		}
		
		maze.enterMaze(this); //pass the robot into the maze
		this.setCurrentLocation(maze.getMazeStartCoord()); //set the bots current location
		/*put this in a method*/	
		cells[this.getCurrentLocation().getX()][this.getCurrentLocation().getY()][this.getCurrentLocation().getZ()].setCellContent(maze.scanCurLoc(this));
		setVisited(true);
		stack.push(this.getCurrentLocation());
		
		
		
		//this is the loop
		while (maze.scanCurLoc(this)!=Content.END && !stack.isEmpty())
		{
			
			surroundings = maze.scanAdjLoc(this);
			//check to see if all the surroundings has an empty cell that has not been visited
			//method
			updateCells(surroundings);
			//go through list 
			 Coordinates nextMove = findNextMove();
			 
			 if (nextMove == null)
			 {
				 stack.pop();
				 Coordinates moveTo = stack.top();
				 Direction direction = findDirection(moveTo);
				 this.setCurrentLocation(maze.move(this, direction)); //pops the stack AND sets the current location
				 
				 
			 }
			 else
			 {
				 Direction direction = findDirection(nextMove); //find the direction
				this.setCurrentLocation(maze.move(this, direction)); // move to the location AND sets the current location
				stack.push(this.getCurrentLocation());//push the new location onto the stack
				setVisited(true);
				
			 }
			
			 if(cells[this.getCurrentLocation().getX()][this.getCurrentLocation().getY()][this.getCurrentLocation().getZ()].getCellContent()==Content.PORTAL_DN)
				 {
				 	this.setCurrentLocation(maze.move(this, Direction.DN)); // move to the location AND sets the current location
				 	stack.push(this.getCurrentLocation());//push the new location onto the stack
					setVisited(true);
					
				 }
		}
		
		if(stack.isEmpty())
		{
			System.out.println("There is no path!!");
		}
		
		Coordinates temp;
		System.out.println("This is the answer!");
		while(!stack.isEmpty())
		{
			temp = stack.pop();
			
			System.out.println(temp.toString());
		}
		
		
		
		
		
	}
	
	/**
	* set visited is a method that 
	*
	*/
	private void setVisited(boolean b) {
		cells[this.getCurrentLocation().getX()][this.getCurrentLocation().getY()][this.getCurrentLocation().getZ()].setHasBeenVisited(b);
		
	}
	/**
	* This method finds the direction of the cell to go to
	*
	*/
	private Direction findDirection(Coordinates nextMove) {
		
		if (nextMove.getY() < this.getCurrentLocation().getY())
		{
			return Direction.D00;
		}
		else if (nextMove.getX() > this.getCurrentLocation().getX())
		{
			return Direction.D90;
		}
		else if (nextMove.getY() > this.getCurrentLocation().getY())
		{
			return Direction.D180;
		}
		else if (nextMove.getX() < this.getCurrentLocation().getX())
		{
			return Direction.D270;
		}
		else if (nextMove.getZ() > this.getCurrentLocation().getZ())
		{
			return Direction.DN;
		}
		else if (nextMove.getZ() < this.getCurrentLocation().getZ())
		{
			return Direction.UP;
		}
		
		return null;
		
	}
	
	/**
	* This method helps find where to move next 
	*/
	private Coordinates findNextMove() {
		
		Coordinates nextMove = null;
		
		//Check the Upper cell
		
		
		if (this.getCurrentLocation().getY()-1 >= 0 && cells[this.getCurrentLocation().getX()][this.getCurrentLocation().getY()-1][this.getCurrentLocation().getZ()].getCellContent()!=Content.BLOCK && 
				!cells[this.getCurrentLocation().getX()][this.getCurrentLocation().getY()-1][this.getCurrentLocation().getZ()].isHasBeenVisited()) {
			
				nextMove = new Coordinates(this.getCurrentLocation().getX(), this.getCurrentLocation().getY()-1, this.getCurrentLocation().getZ());
				
				}
		//Check the Right cell
		else if (this.getCurrentLocation().getX()+1 < botMaze.getMazeDim() && cells[this.getCurrentLocation().getX()+1][this.getCurrentLocation().getY()][this.getCurrentLocation().getZ()].getCellContent()!=Content.BLOCK &&
				!cells[this.getCurrentLocation().getX()+1][this.getCurrentLocation().getY()][this.getCurrentLocation().getZ()].isHasBeenVisited()) 
		{
			nextMove = new Coordinates(this.getCurrentLocation().getX()+1, this.getCurrentLocation().getY(), this.getCurrentLocation().getZ());
			
		}
		//Check the Bottom cell
		else if ( this.getCurrentLocation().getY()+1 < botMaze.getMazeDim() && cells[this.getCurrentLocation().getX()][this.getCurrentLocation().getY()+1][this.getCurrentLocation().getZ()].getCellContent()!=Content.BLOCK &&
				!cells[this.getCurrentLocation().getX()][this.getCurrentLocation().getY()+1][this.getCurrentLocation().getZ()].isHasBeenVisited())
		{
			nextMove = new Coordinates(this.getCurrentLocation().getX(), this.getCurrentLocation().getY()+1, this.getCurrentLocation().getZ());
			
		}
		//Check the left cell
		else if ( this.getCurrentLocation().getX()-1 >= 0 && cells[this.getCurrentLocation().getX()-1][this.getCurrentLocation().getY()][this.getCurrentLocation().getZ()].getCellContent()!=Content.BLOCK&&
				!cells[this.getCurrentLocation().getX()-1][this.getCurrentLocation().getY()][this.getCurrentLocation().getZ()].isHasBeenVisited())
		{
			nextMove = new Coordinates(this.getCurrentLocation().getX()-1, this.getCurrentLocation().getY(), this.getCurrentLocation().getZ());
			
		}
		
		return nextMove;
	
	
	
	
	
	}
	
	
	
	/**
	* This method updates the 3d array's  cell contents 
	*/
	public void updateCells(Content[] surroundings)
	{
		if (surroundings[0] != Content.NA)
		{
			cells[this.getCurrentLocation().getX()][this.getCurrentLocation().getY()-1][this.getCurrentLocation().getZ()].setCellContent(surroundings[0]);
		}
		if (surroundings[1] != Content.NA)
		{
			cells[this.getCurrentLocation().getX()+1][this.getCurrentLocation().getY()][this.getCurrentLocation().getZ()].setCellContent(surroundings[1]);
		}
		if (surroundings[2] != Content.NA)
		{
			cells[this.getCurrentLocation().getX()][this.getCurrentLocation().getY()+1][this.getCurrentLocation().getZ()].setCellContent(surroundings[2]);
		}
		if (surroundings[3] != Content.NA)
		{
			cells[this.getCurrentLocation().getX()-1][this.getCurrentLocation().getY()][this.getCurrentLocation().getZ()].setCellContent(surroundings[3]);
			
		}
		
		
		
		
		
		
	}
	

	
	
	
}
