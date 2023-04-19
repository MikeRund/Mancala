package gameboard;

public class Hole {

    private int pieces;

    public Hole(int pieces) {
        this.pieces = pieces;
    }

    public Hole() {
        this.pieces = 0;
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

    public int takePieces() {
        int pieces = this.pieces;
        this.pieces = 0;
        return pieces;
    }

    public void addPiece() {
        this.pieces++;
    }


}
