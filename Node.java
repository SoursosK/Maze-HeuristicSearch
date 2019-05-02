import java.util.ArrayList;
import java.util.Arrays;

public class Node {
    private Maze maze;

    private int indexX;
    private int indexY;

    private Placeholder[][] grid;

    private Node parentNode;
    private ArrayList<Node> children;

    private int depth;
    private int cost;

    public Node(int indexX, int indexY, Placeholder[][] grid, Node parentNode, int depth, int cost, Maze maze) {
        this.maze = maze;
        
        this.indexX = indexX;
        this.indexY = indexY;

        this.grid = grid;

        this.parentNode = parentNode;
        this.children = null;

        this.depth = depth;
        this.cost = cost;

        this.maze.incrementCreatedNodesCounter();
    }

    public boolean checkIfTargetNode(int indexX, int indexY){
        if(this.indexX == indexX && this.indexY == indexY)
            return true;
        return false;
    }

    public void createChildren() {
        this.children = new ArrayList<>();
        int x = this.indexX, y = this.indexY;

        if(this.grid[x][y].getUpperEdge() != 0)
            this.children.add( new Node(x-1, y, this.grid, this, this.depth+1, this.cost + this.grid[x][y].getUpperEdge(), this.maze) );
        
        if (this.grid[x][y].getLowerEdge() != 0)
            this.children.add( new Node(x+1, y, this.grid, this, this.depth+1, this.cost + this.grid[x][y].getLowerEdge(), this.maze) );

        if (this.grid[x][y].getRightEdge() != 0)
            this.children.add( new Node(x, y+1, this.grid, this, this.depth + 1, this.cost + this.grid[x][y].getRightEdge(), this.maze) );

        if (this.grid[x][y].getLeftEdge() != 0)
            this.children.add( new Node(x, y-1, this.grid, this, this.depth + 1, this.cost + this.grid[x][y].getLeftEdge(), this.maze) );
    }

    public Node getParentNode() {
        return parentNode;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public int getDepth() {
        return depth;
    }

    public int getCost() {
        return cost;
    }

    public String getLocation() {
        return this.indexX + "," + this.indexY;
    }

}