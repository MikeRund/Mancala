package board;

public class Hole {

    int pieces;

    public Hole(int pieces) {
        this.pieces = pieces;
    }

    public Hole() {
        pieces = 0;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        if (pieces >= 0) {
            this.pieces = pieces;
        } else {
            throw new IllegalArgumentException("Number of pieces cannot be negative");
        }
    }


}
