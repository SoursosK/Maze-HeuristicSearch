import java.util.Random;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

// The grid cosists of n x n placeholder Nodes, which contain the 4 possible paths
// to the neighbouring Node. The paths are represented by the placeholder's edges.

public class Maze {
    private LinkedList<Node> dataStructure;
    private HashSet<String> visitedNodes;

    private Placeholder grid[][];

    private int createdNodesCounter;
    private int visitedNodesCounter;

    private int startX, startY, targetX, targetY;

    public Maze(int numberOfLines, int percentageRemoving, int maxWeight) {

        grid = this.initializeGrid(numberOfLines, percentageRemoving, maxWeight);

        do {
            Random rand = new Random(System.currentTimeMillis());
            this.startX = rand.nextInt(numberOfLines);
            this.startY = rand.nextInt(numberOfLines);
            this.targetX = rand.nextInt(numberOfLines);
            this.targetY = rand.nextInt(numberOfLines);
        } while (this.impossibleToFind());

        // Testing purposes ; simulating target being cut off
        // this.targetX = 0;
        // this.targetY = 0;
        // this.grid[0][0].setEdges0();

        this.printGrid(numberOfLines);

        // Testing purposes
        // UCS(2000);
        // BestFirstSearch(2000);
        // Astar(2000);
    }

    private void setParameters() {
        this.dataStructure = new LinkedList<>();
        this.visitedNodes = new HashSet<>();
        this.createdNodesCounter = 0;
        this.visitedNodesCounter = 0;
    }

    private void Astar(int maxVisitedNods) {
        this.setParameters();

        this.dataStructure.add(new Node(this.startX, this.startY, this.grid, null, 0, 0, this));

        while (!dataStructure.isEmpty()) {
            if (this.visitedNodesCounter >= maxVisitedNods) {
                System.out.println("\nVisited Nodes are over " + this.visitedNodesCounter
                        + ".\nMax Nodes visited number exceeded.\nReturning..");
                return;
            }
            this.visitedNodesCounter++;
            // System.out.println("\n" + this.visitedNodesCounter);

            Node minCostNode = null;
            int minCost = Integer.MAX_VALUE;
            for (int i = 0; i < this.dataStructure.size(); i++)

                if (this.dataStructure.get(i).getCost()
                        + this.dataStructure.get(i).manhattanDistance(targetX, targetY) < minCost) {
                    minCost = this.dataStructure.get(i).getCost()
                            + this.dataStructure.get(i).manhattanDistance(targetX, targetY);
                    minCostNode = this.dataStructure.get(i);
                }

            Node node = minCostNode;
            this.visitedNodes.add(node.getLocation());

            // System.out.println("node " + node.getLocation() + " depth: " +
            // node.getDepth() + " manhattan distance: "
            // + node.manhattanDistance(this.targetX, this.targetY));
            this.dataStructure.remove(node);

            if (!node.checkIfTargetNode(this.targetX, this.targetY)) {
                node.createChildren(this);

                for (Node childNode : node.getChildren())
                    if (!this.visitedNodes.contains(childNode.getLocation()))
                        this.dataStructure.addLast(childNode);
            } else {
                this.printOutput(node, "A*");
                return;
            }
        }
    }

    private void BestFirstSearch(int maxVisitedNods) {
        this.setParameters();

        this.dataStructure.add(new Node(this.startX, this.startY, this.grid, null, 0, 0, this));

        while (!dataStructure.isEmpty()) {
            if (this.visitedNodesCounter >= maxVisitedNods) {
                System.out.println("\nVisited Nodes are over " + this.visitedNodesCounter
                        + ".\nMax Nodes visited number exceeded.\nReturning..");
                return;
            }
            this.visitedNodesCounter++;
            // System.out.println("\n" + this.visitedNodesCounter);

            Node minCostNode = null;
            int minCost = Integer.MAX_VALUE;
            for (int i = 0; i < this.dataStructure.size(); i++)

                if (this.dataStructure.get(i).manhattanDistance(targetX, targetY) < minCost) {
                    minCost = this.dataStructure.get(i).manhattanDistance(targetX, targetY);
                    minCostNode = this.dataStructure.get(i);
                }

            Node node = minCostNode;
            this.visitedNodes.add(node.getLocation());

            // System.out.println("node " + node.getLocation() + " depth: " +
            // node.getDepth() + " manhattan distance: " +
            // node.manhattanDistance(this.targetX, this.targetY));
            this.dataStructure.remove(node);

            if (!node.checkIfTargetNode(this.targetX, this.targetY)) {
                node.createChildren(this);

                for (Node childNode : node.getChildren())
                    if (!this.visitedNodes.contains(childNode.getLocation()))
                        this.dataStructure.addLast(childNode);
            } else {
                this.printOutput(node, "BestFirstSearch");
                return;
            }
        }
    }

    // Uncomment the lines below to see the exact path of the algorithm
    private void UCS(int maxVisitedNods) {
        this.setParameters();

        this.dataStructure.add(new Node(this.startX, this.startY, this.grid, null, 0, 0, this));

        while (!dataStructure.isEmpty()) {
            if (this.visitedNodesCounter >= maxVisitedNods) {
                System.out.println("\nVisited Nodes are over " + this.visitedNodesCounter
                        + ".\nMax Nodes visited number exceeded.\nReturning..");
                return;
            }
            this.visitedNodesCounter++;
            // System.out.println("\n" + this.visitedNodesCounter);

            Node minCostNode = null;
            int minCost = Integer.MAX_VALUE;
            for (int i = 0; i < this.dataStructure.size(); i++)

                if (this.dataStructure.get(i).getCost() < minCost) {
                    minCost = this.dataStructure.get(i).getCost();
                    minCostNode = this.dataStructure.get(i);
                }

            Node node = minCostNode;
            this.visitedNodes.add(node.getLocation());

            // System.out.println("node " + node.getLocation() + " depth: " +
            // node.getDepth());
            this.dataStructure.remove(node);

            if (!node.checkIfTargetNode(this.targetX, this.targetY)) {
                node.createChildren(this);

                for (Node childNode : node.getChildren())
                    if (!this.visitedNodes.contains(childNode.getLocation()))
                        this.dataStructure.addLast(childNode);
            } else {
                this.printOutput(node, "UCS");
                return;
            }
        }
    }

    private void printOutput(Node node, String algorithmName) {
        System.out.println("\n" + algorithmName + " found a solution to the problem!");
        System.out.println("The final cost is " + node.getCost() + " minutes.");
        System.out.println("The nodes created were " + this.createdNodesCounter + ", with " + this.visitedNodesCounter
                + " of them being visited.\n");
    }

    private Placeholder[][] initializeGrid(int numberOfLines, int percentageRemoving, int maxWeight) {
        Placeholder grid[][] = new Placeholder[numberOfLines][numberOfLines];

        // Creates the nxn grid and initiazes the edge's weights
        for (int i = 0; i < numberOfLines; i++)
            for (int j = 0; j < numberOfLines; j++)
                grid[i][j] = new Placeholder(grid, i, j, maxWeight, numberOfLines);

        int edgesToRemove = 2 * numberOfLines * (numberOfLines - 1) * percentageRemoving / 100;
        Random rand = new Random(System.currentTimeMillis());

        for (int i = edgesToRemove; i > 0; i--) {
            int x = rand.nextInt(numberOfLines);
            int y = rand.nextInt(numberOfLines);
            if (!grid[x][y].isIsolated())
                grid[x][y].removeEdge();
            else
                i++;
        }

        // Testing pursposes
        // for (int i = 0; i < numberOfLines; i++)
        // for (int j = 0; j < numberOfLines; j++) {
        // System.out.println(grid[i][j].getLocation());
        // grid[i][j].getEdges();
        // }

        return grid;
    }

    private void printGrid1(int numberOfLines) {
        String gridToShow[][] = new String[numberOfLines][numberOfLines];
        System.out.println();

        for (int i = 0; i < numberOfLines; i++)
            for (int j = 0; j < numberOfLines; j++) {
                gridToShow[i][j] = grid[i][j].getLocation();
            }

        gridToShow[this.startX][this.startY] = " S ";
        gridToShow[this.targetX][this.targetY] = " T ";

        for (int i = 0; i < numberOfLines; i++)
            System.out.println(Arrays.toString(gridToShow[i]));
    }

    private void printGrid(int numberOfLines) {
        String gridToShow[][] = new String[numberOfLines*2-1][numberOfLines*2-1];
        System.out.println();
        for (int i = 0; i < numberOfLines*2-1; i++)
            for (int j = 0; j < numberOfLines*2-1; j++) 
                gridToShow[i][j] = "   ";
            

        for (int i = 0; i < numberOfLines*2-1; i+=2)
            for (int j = 0; j < numberOfLines*2-1; j+=2) {
                gridToShow[i][j] = grid[i/2][j/2].getLocation();
                if(!(j == numberOfLines*2-2)){
                    gridToShow[i][j+1] = " " + this.grid[i/2][j/2].getRightEdge() + " ";
                }
                if(!(i == numberOfLines*2-2)){
                    gridToShow[i+1][j] = " " + this.grid[i/2][j/2].getLowerEdge() + " ";
                }
            }

        gridToShow[this.startX*2][this.startY*2] = " S ";
        gridToShow[this.targetX*2][this.targetY*2] = " T ";

        for (int i = 0; i < numberOfLines*2-1; i++)
            System.out.println(Arrays.toString(gridToShow[i]));
    }

    public boolean impossibleToFind() {
        if (this.grid[this.targetX][this.targetY].isIsolated()) {
            System.out.println("\nTargert node's Edges are.. ");
            this.grid[this.targetX][this.targetY].getEdges();
            System.out.println("\nProblem can't be solved.");
            System.out.println("Reallocating..\n");
            return true;
        }

        if (this.grid[this.startX][this.startY].isIsolated()) {
            System.out.println("\nStarting node's Edges are.. ");
            this.grid[this.startX][this.startY].getEdges();
            System.out.println("\nProblem can't be solved.");
            System.out.println("Reallocating..\n");
            return true;
        }

        if (this.startX == this.targetX && this.startY == this.targetY) {
            System.out.println("\nStarting and Target nodes happen to be the same node.");
            System.out.println("Reallocating..\n");
            return true;
        }

        return false;
    }

    public void incrementCreatedNodesCounter() {
        this.createdNodesCounter++;
    }

    public static void main(String[] args) {
        
        int numberOfLines;
        int percentageRemoving;
        int maxWeight;

        Scanner sc = new Scanner(System.in);

        System.out.println("Please insert the number of the grid's lines: ");
        numberOfLines = sc.nextInt();

        System.out.println("Please insert the percentage of the grid's edges you want to remove(ex.80): ");
        percentageRemoving = sc.nextInt();

        System.out.println("Please insert the max weight of a grid's edge: ");
        maxWeight = sc.nextInt();

        Maze maze = new Maze(numberOfLines, percentageRemoving, maxWeight);

        // Testing Purposes
        // Maze maze = new Maze(5, 20, 9);

        System.out.println("\nPlease insert the maximum number of nodes that an algorithm is allowed to visit: ");
        int maxNodes = sc.nextInt();

        // Testing Purposes
        // int maxNodes = 2000;

        while (true) {
            System.out.println("Please choose which algorithm you would like to run: \n1) UCS\n2) Best-First Search\n3) A*\n4) Exit");
            int option = sc.nextInt();

            if (option == 1)
                maze.UCS(maxNodes);
            else if (option == 2)
                maze.BestFirstSearch(maxNodes);
            else if (option == 3)
                maze.Astar(maxNodes);
            else if (option == 4) {
                System.out.println("Terminating the program.");
                sc.close();
                return;
            } else
                System.out.println("Wrong input.\nPlease try again.");
        }

    }

}