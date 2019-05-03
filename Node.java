import java.util.ArrayList;
import java.util.Arrays;
import java.lang.*;

public class Node {
    private int indexX;
    private int indexY;

    private Placeholder[][] grid;

    private Node parentNode;
    private ArrayList<Node> children;

    private int depth;
    private int cost;

    public Node(int indexX, int indexY, Placeholder[][] grid, Node parentNode, int depth, int cost, Maze maze) {
        this.indexX = indexX;
        this.indexY = indexY;

        this.grid = grid;

        this.parentNode = parentNode;
        this.children = null;

        this.depth = depth;
        this.cost = cost;

        maze.incrementCreatedNodesCounter();
    }

    public boolean checkIfTargetNode(int indexX, int indexY){
        if(this.indexX == indexX && this.indexY == indexY)
            return true;
        return false;
    }

    public void createChildren(Maze maze) {
        this.children = new ArrayList<>();
        int x = this.indexX, y = this.indexY;

        if(this.grid[x][y].getUpperEdge() != 0)
            this.children.add( new Node(x-1, y, this.grid, this, this.depth+1, this.cost + this.grid[x][y].getUpperEdge(), maze) );
        
        if (this.grid[x][y].getLowerEdge() != 0)
            this.children.add( new Node(x+1, y, this.grid, this, this.depth+1, this.cost + this.grid[x][y].getLowerEdge(), maze) );

        if (this.grid[x][y].getRightEdge() != 0)
            this.children.add( new Node(x, y+1, this.grid, this, this.depth + 1, this.cost + this.grid[x][y].getRightEdge(), maze) );

        if (this.grid[x][y].getLeftEdge() != 0)
            this.children.add( new Node(x, y-1, this.grid, this, this.depth + 1, this.cost + this.grid[x][y].getLeftEdge(), maze) );
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

    public int manhattanDistance(int targetX, int targetY) {
        return Math.abs(targetX - this.indexX) + Math.abs(targetY - this.indexY);
    } 

}