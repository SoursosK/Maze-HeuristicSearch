import java.util.Random;

public class Placeholder {
    private Placeholder[][] grid;

    private int indexX;
    private int indexY;

    // if != 0, they exist and the integer represents the edge's weight
    private int upperEdge;
    private int lowerEdge;
    private int rightEdge;
    private int leftEdge;

    public Placeholder(Placeholder[][] grid, int indexX, int indexY, int maxWeight, int tableAxesLength) {
        this.grid = grid;

        this.indexX = indexX;
        this.indexY = indexY;

        int x = indexX, y = indexY;
        Random rand = new Random(System.currentTimeMillis());

        if (x == 0 && y == 0) {
            this.upperEdge = 0;
            this.lowerEdge = rand.nextInt(maxWeight) + 1;
            this.rightEdge = rand.nextInt(maxWeight) + 1;
            this.leftEdge = 0;
        } else if (x == 0 && y == tableAxesLength - 1) {
            this.upperEdge = 0;
            this.lowerEdge = rand.nextInt(maxWeight) + 1;
            this.rightEdge = 0;
            this.leftEdge = grid[x][y - 1].getRightEdge();
        } else if (x == tableAxesLength - 1 && y == 0) {
            this.upperEdge = grid[x - 1][y].getLowerEdge();
            this.lowerEdge = 0;
            this.rightEdge = rand.nextInt(maxWeight) + 1;
            this.leftEdge = 0;
        } else if (x == tableAxesLength - 1 && y == tableAxesLength - 1) {
            this.upperEdge = grid[x - 1][y].getLowerEdge();
            this.lowerEdge = 0;
            this.rightEdge = 0;
            this.leftEdge = grid[x][y - 1].getRightEdge();
        } else if (x == 0) {
            this.upperEdge = 0;
            this.lowerEdge = rand.nextInt(maxWeight) + 1;
            this.rightEdge = rand.nextInt(maxWeight) + 1;
            this.leftEdge = grid[x][y - 1].getRightEdge();
        } else if (y == 0) {
            this.upperEdge = grid[x - 1][y].getLowerEdge();
            this.lowerEdge = rand.nextInt(maxWeight) + 1;
            this.rightEdge = rand.nextInt(maxWeight) + 1;
            this.leftEdge = 0;
        } else if (x == tableAxesLength - 1) {
            this.upperEdge = grid[x - 1][y].getLowerEdge();
            this.lowerEdge = 0;
            this.rightEdge = rand.nextInt(maxWeight) + 1;
            this.leftEdge = grid[x][y - 1].getRightEdge();
        } else if (y == tableAxesLength - 1) {
            this.upperEdge = grid[x - 1][y].getLowerEdge();
            this.lowerEdge = rand.nextInt(maxWeight) + 1;
            this.rightEdge = 0;
            this.leftEdge = grid[x][y - 1].getRightEdge();
        } else {
            this.upperEdge = grid[x - 1][y].getLowerEdge();
            this.lowerEdge = rand.nextInt(maxWeight) + 1;
            this.rightEdge = rand.nextInt(maxWeight) + 1;
            this.leftEdge = grid[x][y - 1].getRightEdge();
        }

    }

    public int getUpperEdge() {
        return this.upperEdge;
    }

    public int getLowerEdge() {
        return this.lowerEdge;
    }

    public int getRightEdge() {
        return this.rightEdge;
    }

    public int getLeftEdge() {
        return this.leftEdge;
    }

    public void deleteUpperEdge() {
        this.upperEdge = 0;
    }

    public void deleteLowerEdge() {
        this.lowerEdge = 0;
    }

    public void deleteRightEdge() {
        this.rightEdge = 0;
    }

    public void deleteLeftEdge() {
        this.leftEdge = 0;
    }

    public void removeEdge() {
        Random rand = new Random(System.currentTimeMillis());

        while (true) {
            int side = rand.nextInt(4);

            if (side == 0) {
                if (this.upperEdge == 0)
                    continue;

                this.upperEdge = 0;
                this.grid[this.indexX - 1][this.indexY].deleteLowerEdge();
            } else if (side == 1) {
                if (this.lowerEdge == 0)
                    continue;

                this.lowerEdge = 0;
                this.grid[this.indexX + 1][this.indexY].deleteUpperEdge();
            } else if (side == 2) {
                if (this.rightEdge == 0)
                    continue;

                this.rightEdge = 0;
                this.grid[this.indexX][this.indexY + 1].deleteLeftEdge();
            } else if (side == 3) {
                if (this.leftEdge == 0)
                    continue;

                this.leftEdge = 0;
                this.grid[this.indexX][this.indexY - 1].deleteRightEdge();
            }
            return;
        }
    }

    public boolean isIsolated() {
        if (this.upperEdge == 0 && this.lowerEdge == 0 && 
            this.rightEdge == 0 && this.leftEdge == 0)
            return true;
        return false;
    }

    // testing purposes
    public String getLocation() {
        return this.indexX + "," + this.indexY;
    }

    // testing purposes
    public void getEdges() {
        System.out.println("Up: " + this.upperEdge);
        System.out.println("Down: " + this.lowerEdge);
        System.out.println("Right: " + this.rightEdge);
        System.out.println("Left: " + this.leftEdge);
    }

    // testing purposes ; helps in simulating target being cut off
    public void setEdges0() {
        this.rightEdge = 0;
        this.lowerEdge = 0;
    }
}