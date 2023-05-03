package gameboard;

public class Board {

    private Hole[] row1;
    private Hole[] row2;
    private Hole store1;
    private Hole store2;

    private Hole[] allHoles;

    public Board() {

        //Initialize rows with 6 holes
        row1 = new Hole[6];
        row2 = new Hole[6];

        //Places 4 pieces in each hole
        for(int i = 0; i < row1.length; i++) {
            row1[i] = new Hole(4);
            row2[i] = new Hole(4);
        }

        //Initialize the stores as a hole
        store1 = new Hole();
        store2 = new Hole();

        //Initialize allHoles as a single array
        allHoles = getAllHoles();
    }

    public Hole[] getRow1() {
        return row1;
    }

    public Hole[] getRow2() {
        return row2;
    }

    public Hole getStore1() {
        return store1;
    }

    public Hole getStore2() {
        return store2;
    }

    //Method to create single list off all holes and stores
    public Hole[] getAllHoles() {
        Hole[] allHoles = new Hole[14];
        int index = 0;

        // Add row1 at index 0 - 5
        for (int i = row1.length - 1; i >= 0; i--) {
            allHoles[index++] = row1[i];
        }

        // Add store1 at index 6
        allHoles[index++] = store1;

        // Add row2 at index 7 - 12
        for (Hole hole : row2) {
            allHoles[index++] = hole;
        }

        // Add store2 at index 13
        allHoles[index++] = store2;

        return allHoles;
    }

    //Method to check if a row is empty
    public boolean isRowEmpty(Hole[] row) {
        for(Hole hole : row) {
            if(hole.getPieces() != 0) {
                return false;
            }
        }
        return true;
    }
    public void displayBoardCommandLine() {
        System.out.print("\t"); // Add a tab before the first row of holes

        // Display row1 in original order
        for (Hole hole : row1) {
            System.out.print(hole.getPieces());
        }
        System.out.println();

        // Display store1
        System.out.print(store1.getPieces());
        System.out.print("\t\t\t"); // Add multiple tabs to align with the second store

        // Display store2
        System.out.println(" " + store2.getPieces());

        System.out.print("\t"); // Add a tab before the second row of holes

        // Display row2 in original order
        for (Hole hole : row2) {
            System.out.print(hole.getPieces());
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Board board = new Board();
        Hole[] holeList = board.getAllHoles();
        holeList[6].setPieces(9);
        board.displayBoardCommandLine();

    }


}
