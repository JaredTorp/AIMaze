package mazePD;

import mazePD.Maze.*;

public class MazeTest {
	public static void MazeTest(int levels, int dim)
	{
		
		
		System.out.println("This is the maze");
		Maze maze = new Maze(dim, levels, MazeMode.NORMAL);
		Droid droid = new Droid();
		droid.setName("Claptrap");
		System.out.println("Maze - "+maze.toString());
		 
		
		for (int z = 0; z < levels; z++)
		{
			System.out.println("Level - "+z);
			String[] mazeArray = maze.toStringLevel(z);
			for (int y = 0; y < dim; y++)
			{
				System.out.println(y+" " + mazeArray[y]);
			}
		}
		
		//now for the fun part
		droid.runMaze(maze);
		
		
		
		
		
	}

}
