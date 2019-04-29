import java.util.Random;

public class Placeholder {

    private int indexX;
    private int indexY;

    // if <> 0, they exist and the integer represents the edge's weight
    private int upperEdge;
    private int lowerEdge;
    private int rightEdge;
    private int leftEdge;

    public Placeholder(Placeholder[][] grid, int indexX, int indexY, int maxWeight, int tableAxesLength) {
        this.indexX = indexX;
        this.indexY = indexY;

        int x = indexX, y = indexY;
        Random rand = new Random(System.currentTimeMillis());

        if(x == 0 && y == 0){
            this.upperEdge = 0;
            this.lowerEdge = rand.nextInt(maxWeight) + 1;
            this.rightEdge = rand.nextInt(maxWeight) + 1;
            this.leftEdge = 0;
        } else if(x == 0 && y == tableAxesLength-1){
            this.upperEdge = 0;
            this.lowerEdge = rand.nextInt(maxWeight) + 1;
            this.rightEdge = 0;
            this.leftEdge = grid[i][j-1].getRightEdge();
        } else if(x == tableAxesLength-1 && y == 0){
            this.upperEdge = grid[i-1][j].getLowerEdge();
            this.lowerEdge = 0;
            this.rightEdge = rand.nextInt(maxWeight) + 1;
            this.leftEdge = 0;
        } else if(x == tableAxesLength-1 && y == tableAxesLength-1){
            this.upperEdge = grid[i-1][j].getLowerEdge();
            this.lowerEdge = 0;
            this.rightEdge = 0;
            this.leftEdge = grid[i][j-1].getRightEdge();
        } else if(x == 0){
            this.upperEdge = 0;
            this.lowerEdge = rand.nextInt(maxWeight) + 1;
            this.rightEdge = rand.nextInt(maxWeight) + 1;
            this.leftEdge = grid[i][j-1].getRightEdge();
        } else if(y == 0){
            this.upperEdge = grid[i-1][j].getLowerEdge();
            this.lowerEdge = rand.nextInt(maxWeight) + 1;
            this.leftEdge = 0;
        } else if(x == tableAxesLength-1){
            this.upperEdge = grid[i-1][j].getLowerEdge();
            this.lowerEdge = 0;
            this.rightEdge = rand.nextInt(maxWeight) + 1;
            this.leftEdge = grid[i][j-1].getRightEdge();
        } else if(y == tableAxesLength-1){
            this.upperEdge = grid[i-1][j].getLowerEdge();
            this.lowerEdge = rand.nextInt(maxWeight) + 1;
            this.rightEdge = 0;
            this.leftEdge = grid[i][j-1].getRightEdge();
        } else {
            this.upperEdge = grid[i-1][j].getLowerEdge();
            this.lowerEdge = rand.nextInt(maxWeight) + 1;
            this.rightEdge = rand.nextInt(maxWeight) + 1;
            this.leftEdge = grid[i][j-1].getRightEdge();
        }


    }

    public int getRightEdge() {
        return this.rightEdge;
    }

    public int getLowerEdge() {
        return this.lowerEdge;
    }

}