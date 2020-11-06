package mazePD;

import mazePD.Maze.Content;

public class Cell {

	private Coordinates coordinates;			// coordinates of this cell
	private Content cellContent;				// contents of this cell 
	private boolean hasBeenVisited;				// has this cell been visited during generation
	
	public Cell()
	{
		coordinates = null;
		cellContent = null;
		hasBeenVisited = false;
	}
	public Cell(Coordinates c, Content v, Boolean t)
	{
		coordinates = c;
		cellContent = v;
		hasBeenVisited = t;
		 
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public Content getCellContent() {
		return cellContent;
	}

	public void setCellContent(Content cellContent) {
		this.cellContent = cellContent;
	}

	public boolean isHasBeenVisited() {
		return hasBeenVisited;
	}

	public void setHasBeenVisited(boolean hasBeenVisited) {
		this.hasBeenVisited = hasBeenVisited;
	}
	
}
