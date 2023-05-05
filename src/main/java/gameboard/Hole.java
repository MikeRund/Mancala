/**
 * Represents a hole in a game board that can hold pieces.
 */
package gameboard;

public class Hole {

    private int pieces;

    /**
     * Constructs a new Hole with the given number of pieces.
     * @param pieces the number of pieces in the Hole
     */
    public Hole(int pieces) {
        this.pieces = pieces;
    }

    /**
     * Constructs a new Hole with no pieces.
     */
    public Hole() {
        this.pieces = 0;
    }

    /**
     * Returns the number of pieces in this hole.
     * @return the number of pieces in this hole
     */
    public int getPieces() {
        return pieces;
    }

    /**
     * Sets the number of pieces in this hole to the given value.
     * @param pieces the new number of pieces in this hole
     * @throws IllegalArgumentException if the given number of pieces is negative
     */
    public void setPieces(int pieces) {
        if (pieces >= 0) {
            this.pieces = pieces;
        } else {
            throw new IllegalArgumentException("Number of pieces cannot be negative");
        }
    }

    /**
     * Removes all the pieces from this hole and returns the number of pieces removed.
     * @return the number of pieces removed from this hole
     */
    public int takePieces() {
        int pieces = this.pieces;
        this.pieces = 0;
        return pieces;
    }

    /**
     * Adds one piece to this hole.
     */
    public void addPiece() {
        this.pieces++;
    }


}
