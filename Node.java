import java.util.ArrayList;
import java.util.Arrays;


public class Node {
    // private String name;

    // private BridgeAndTorch bt;

    // private Node parentNode;
    // private ArrayList<Node> children;
    // private int state[];

    // private int depth;
    // private int cost;

    // public Node(String name, BridgeAndTorch bt, Node parentNode, int state[], int depth, int cost) {
    //     this.name = name;
        
    //     this.bt = bt;

    //     this.parentNode = parentNode;
    //     this.children = null;
    //     this.state = state;

    //     this.depth = depth;
    //     this.cost = cost;

    //     this.bt.incrementCreatedNodesCounter();
    // }

    // public boolean checkFiniteState(){
    //     if(Arrays.equals(this.state, this.bt.getFiniteState()))
    //         return true;
    //     else
    //         return false;
    // }

    // public void createChildren() {
    //     this.children = new ArrayList<>();

    //     // if the torch is on the left side
    //     if (state[state.length - 1] == 0)
            
    //         // create all the possible pairs of indexes
    //         for (int i = 0; i < state.length - 1; i++)
    //             for (int j = i + 1; j < state.length - 1; j++){

    //                 if (this.state[i] == 0 && this.state[j] == 0) {
    //                     int childState[] = this.state.clone();
    //                     childState[i] = 1;
    //                     childState[j] = 1;
    //                     childState[childState.length - 1] = 1; // torch goes right

    //                     int crossingTime = bt.calculateCrossingTime(i, j);

    //                     children.add(new Node(i+""+j, bt, this, childState, this.depth + 1, this.cost + crossingTime));
                        
    //                     System.out.println("child created left side");
    //                 }
    //             }

    //     // if the torch is on the right side
    //     if (state[state.length - 1] == 1)

    //         for (int i = 0; i < state.length - 1; i++)

    //             if (this.state[i] == 1) {
    //                 int childState[] = this.state.clone();
    //                 childState[i] = 0;
    //                 childState[state.length - 1] = 0; // torch goes left

    //                 int crossingTime = bt.getCrossingTime(i);

    //                 this.children.add(new Node(i+"", bt, this, childState, this.depth + 1, this.cost + crossingTime));
                    
    //                 System.out.println("child created right side");
    //             }

    // }

    // public Node getParentNode() {
    //     return parentNode;
    // }

    // public ArrayList<Node> getChildren() {
    //     return children;
    // }

    // public int getDepth() {
    //     return depth;
    // }

    // public int getCost() {
    //     return cost;
    // }

    // public String getName() {
    //     return name;
    // }


}