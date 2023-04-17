package board;

public class Board {

    Hole[] row1;
    Hole[] row2;
    Hole store1;
    Hole store2;

    Hole[] allHoles;

    public Board() {

        //Initialize rows with 6 holes
        row1 = new Hole[6];
        row2 = new Hole[6];

        //Initialize the stores as a hole
        store1 = new Hole();
        store2 = new Hole();

        //Initialize allHoles as a single array
        allHoles = getAllHolesClockwise();
    }

    //Method to create single list off all holes and stores
    public Hole[] getAllHolesClockwise() {
        Hole[] allHoles = new Hole[14];
        int index = 0;

        //Add row1
        for (Hole hole : row1) {
            allHoles[index++] = hole;
        }

        //Add store1
        allHoles[index++] = store1;

        //Add row2
        for (Hole hole : row2) {
            allHoles[index++] = hole;
        }

        //Add store2
        allHoles[index++] = store2;

        return allHoles;

    }
}
