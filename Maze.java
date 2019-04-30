import java.util.Random;

public class Maze {

    // private LinkedList<Node> dataStructure;

    private Placeholder grid[][];

    private int createdNodesCounter;
    private int visitedNodesCounter;

    public Maze(int numberOfLines, int percentageRemoving, int maxWeight) {

        grid = this.initializeGrid(numberOfLines, percentageRemoving, maxWeight);

        // UCS();
    }

    private Placeholder[][] initializeGrid(int numberOfLines, int percentageRemoving, int maxWeight) {
        Placeholder grid[][] = new Placeholder[numberOfLines][numberOfLines];

        for(int i = 0; i < numberOfLines; i++)
            for (int j = 0; j < numberOfLines; j++){
                grid[i][j] = new Placeholder(grid, i, j, maxWeight, numberOfLines);
            }
        
        int edgesToRemove = 2 * numberOfLines * (numberOfLines - 1) * percentageRemoving/100;

        Random rand = new Random(System.currentTimeMillis());
        for(int i=edgesToRemove; i>0; i--){
            int x = rand.nextInt(numberOfLines);
            int y = rand.nextInt(numberOfLines);
            grid[x][y].removeEdge();
        }

        // Testing pursposes        
        // for (int i = 0; i < numberOfLines; i++)
        //     for (int j = 0; j < numberOfLines; j++) {
        //         grid[i][j].getLocation();
        //         grid[i][j].getEdges();
        //     }

        return grid;
    }

    // private void UCS() {
    // this.dataStructure.add(new Node("startingNode", this, null, this.state, 0,
    // 0));

    // while (!dataStructure.isEmpty()) {
    // this.visitedNodesCounter++;
    // System.out.println("\n" + this.visitedNodesCounter);

    // Node minCostNode = null;
    // int minCost = Integer.MAX_VALUE;
    // for (int i = 0; i < this.dataStructure.size(); i++)

    // if (this.dataStructure.get(i).getCost() < minCost) {
    // minCost = this.dataStructure.get(i).getCost();
    // minCostNode = this.dataStructure.get(i);
    // }

    // Node node = minCostNode;

    // System.out.println("node " + node.getName() + " depth: " + node.getDepth());
    // this.dataStructure.remove(node);

    // if (!node.checkFiniteState()) {
    // node.createChildren();

    // for (Node childNode : node.getChildren())
    // this.dataStructure.addLast(childNode);
    // } else {
    // printOutput(node, "UCS");
    // return;
    // }
    // }

    // }

    public static void main(String[] args) {
        // int numberOfLines;
        // int percentageRemoving;
        // int maxWeight;

        // Scanner sc = new Scanner(System.in);

        // System.out.println("Please insert the number of the grid's lines: ");
        // numberOfLines = sc.nextInt();

        // System.out.println("Please insert the percentage of the grid's edges you want
        // to remove(ex.80): ");
        // percentageRemoving = sc.nextInt();

        // System.out.println("Please insert the max weight of a grid's edge: ");
        // percentageRemoving = sc.nextInt();

        // Maze maze = new Maze(numberOfLines, percentageRemoving, maxWeight);

        // Testing Purposes
        Maze maze = new Maze(5, 20, 10);

        // while (true) {
        // System.out.println("Please choose which algorithm you would like to run: \n1)
        // UCS\n"
        // + "2) Best-First Search\n3) A*\n4) Exit");
        // int option = sc.nextInt();

        // if (option == 1)
        // maze.UCS();
        // else if (option == 2)
        // maze.BestFirstSearch();
        // else if (option == 3)
        // maze.Astar();
        // else if (option == 4) {
        // System.out.println("Terminating the program.");
        // sc.close();
        // return;
        // } else
        // System.out.println("Wrong input.\nPlease try again.");
        // }

    }

}