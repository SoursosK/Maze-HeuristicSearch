import java.util.ArrayList;
import java.util.Arrays;


public class Node {
    private int indexX;
    private int indexY;

    private Placeholder[][] grid;

    private Node parentNode;
    private ArrayList<Node> children;

    private int depth;
    private int cost;

    public Node(int indexX, int indexY, Placeholder[][] grid, Node parentNode, int depth, int cost) {
        this.indexX = indexX;
        this.indexY = indexY;

        this.grid = grid;

        this.parentNode = parentNode;
        this.children = null;

        this.depth = depth;
        this.cost = cost;

        this.bt.incrementCreatedNodesCounter();
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
            this.children.add( new Node(x-1, y, this.grid, this, this.depth+1, this.grid[x][y].getUpperEdge()) );
        
        if (this.grid[x][y].getLowerEdge() != 0)
            this.children.add(new Node(x+1, y, this.grid, this, this.depth+1, this.grid[x][y].getLowerEdge()));

        if (this.grid[x][y].getRightEdge() != 0)
            this.children.add(new Node(x, y+1, this.grid, this, this.depth + 1, this.grid[x][y].getRightEdge()));

        if (this.grid[x][y].getLeftEdge() != 0)
            this.children.add(new Node(x, y-1, this.grid, this, this.depth + 1, this.grid[x][y].getLeftEdge()));
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

}