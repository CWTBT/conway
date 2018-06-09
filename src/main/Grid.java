package main;

public class Grid {
	public boolean[][] cellMap;
	
	public Grid(int width, int height) {
		cellMap = new boolean[width][height];
	}
	
	private int getWidth() {
		return cellMap.length;
	}
	
	private int getHeight() {
		return cellMap[0].length;
	}
	
	private void switchCell(int x, int y) {
		try {
			cellMap[x][y] = !cellMap[x][y];
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Invalid cell ["+x+","+y+"] (Source: switchCell)");
		}
	}
	
	private int neighborCount(int x, int y, boolean[][] map) {
		int count = 0;

		for (int i = x-1; i <= x+1; i++) {
			for(int j = y-1; j <= y+1; j++) {
				if (i == x && j == y) continue;
				else {
					try {
						if (map[i][j]) count++;
					}
					
					catch(IndexOutOfBoundsException e) {}
				}
			}
		}
		return count;
	}
	
	public void update() {
		boolean[][] tempMap = new boolean[getWidth()][getHeight()];
		
		//Copy the contents of the cellMap into this temporary array.
		for(int i = 0; i < getWidth(); i++) {
			for(int j = 0; j < getHeight(); j++) {
				tempMap[i][j] = cellMap[i][j];
			}
		}
		
		//Based on the generation in cellMap, updates all necessary cells on tempMap.
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				int count = neighborCount(i,j,cellMap);
				if (cellMap[i][j] == false) {
					if (count == 3) {
						tempMap[i][j] = true;
					}
				}
				
				else if (count < 2 || count > 3){
					tempMap[i][j] = false;
				}
			}
		}
		//Replace cellMap with the updated contents of tempMap
		cellMap = tempMap;
	}
	
	public static void main(String[] args) {
		/* For testing
		 * 
		 * Grid grid = new Grid(5, 5);
		System.out.println(grid);
		for (int i = 0; i < 5; i++) {
			grid.update();
			System.out.println(grid);
		}
		*/
	}
	
	public String toString() {
		String output = "";
		for(int i = 0; i < getWidth(); i++) {
			for(int j = 0; j < getHeight(); j++) {
				if(cellMap[j][i]) output+="X ";
				else output+="O ";
			}
			output+="\n";
		}
		return output;
	}
}
