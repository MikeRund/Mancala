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
        this.pieces = pieces;
    }

}
