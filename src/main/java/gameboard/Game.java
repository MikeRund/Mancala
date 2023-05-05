/**
 The Game class represents a Mancala game that can be played between two players.
 It contains methods to create a new game, generate a starting player, make a move, switch players,
 check if the game is over, get the winner, and start the game.
 It also has instance variables to keep track of the game's board, players, current player, and last hole index.
 */
package gameboard;

import leaderboard.Player;

import java.util.Random;
import java.util.Scanner;


public class Game {
    private Board board;
    private int player1;
    private int player2;
    private Player player;
    public int currentPlayer;
    public int lastHoleIndex;

    /**
     * Returns the game's Board.
     *
     * @return the game's Board
     */
    public Game() {
        this.board = new Board();
    }

    /**
     * Returns the game's Board.
     *
     * @return the game's Board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Returns player 1 as int
     * @return player 1
     */
    public int getPlayer1() {
        return player1;
    }

    /**
     * Returns player 2 as int
     * @return player 2
     */
    public int getPlayer2() {
        return player2;
    }

    /**
     * Sets the int of player 1.
     *
     * @param player1 as int
     */
    public void setPlayer1(int player1) {
        this.player1 = player1;
    }

    /**
     * Sets the int of player 1.
     *
     * @param player2 as int
     */
    public void setPlayer2(int player2) {
        this.player2 = player2;
    }

    /**
     * Sets the current player as int.
     *
     * @param currentPlayer the new current player
     */
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Returns the current player as int.
     *
     * @return the current player
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the player object.
     *
     * @return the player object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player object.
     *
     * @param player the new player object
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Returns the index of the next hole.
     *
     * @param holeIndex the index of the current hole
     * @return the index of the next hole
     */
    public int nextHole(int holeIndex) {

        holeIndex++;
        if(holeIndex > 13){
            holeIndex = 0;
        }
        //this.holeIndex = holeIndex;
        return holeIndex;
    }

    /**
     * Switches the current player.
     */
    public void switchPlayer() {
        if(currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    /**
     * Takes all pieces out of a selected hole and redistributes them in the board
     * according to the game rules.
     *
     * @param holeIndex the index of the hole where the move starts.
     */
    public void makeMove(int holeIndex) {

        //Take all pieces out of selected hole
        Hole[] holes = board.getAllHoles();
        int pieces = holes[holeIndex].takePieces();
        holeIndex = nextHole(holeIndex);

        while(pieces > 0) {

            if(holeIndex == 6) {

                if (currentPlayer == player1) {
                    holes[holeIndex].addPiece();
                    pieces--;
                    holeIndex = nextHole(holeIndex);

                } else {
                    holeIndex = nextHole(holeIndex);
                }

            } else if (holeIndex == 13) {

                if (currentPlayer == player2) {
                    holes[holeIndex].addPiece();
                    pieces--;
                    holeIndex = nextHole(holeIndex);

                } else {
                    holeIndex = nextHole(holeIndex);
                }
            } else {
                holes[holeIndex].addPiece();
                pieces--;
                holeIndex = nextHole(holeIndex);
            }
        }
        //Since the method is always looking to the next hole, we reduce the hole index by 1
        int lastHole = holeIndex -1;
        //Perform a fix for finishing on the 13th hole (next hole = 0, then 0 - 1 = -1)
        if (lastHole == -1){
            lastHole = 13;
        }
        this.lastHoleIndex = lastHole;
        System.out.println("Last hole index: " + lastHoleIndex);
    }

    /**
     * Generates a random number (1 or 2) to determine the starting player.
     *
     * @return the number of the starting player.
     */
    public int generateStartingPlayer() {
        Random rand = new Random();
        int playerNumber = rand.nextInt(2) + 1;
        System.out.println("Player " + playerNumber +" is first!");
        return playerNumber;
    }

    /**
     * Checks if the game is over by verifying if any of the rows of holes is empty.
     *
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return board.isRowEmpty(board.getRow1()) || board.isRowEmpty(board.getRow2());
    }

    /**
     * Returns the number of the player who has won the game.
     *
     * @return 1 if player 1 has won, 2 if player 2 has won.
     */
    public int getWinner() {
        int player1Score = board.getStore1().getPieces();
        int player2Score = board.getStore2().getPieces();

        if (player1Score > player2Score) {
            System.out.println("Player 1 wins!");
            return 1;
            //getPlayer1.increaseWins(1);
        } else {
            System.out.println("Player 2 wins!");
            return 2;
            //getPlayer2.increaseWins(1);
        }
    }

//    public void startGame() {
//
//        //Set-up
//        Scanner in = new Scanner(System.in);
//        Game game = new Game();
//        game.player1 = 1;
//        game.player2 = 2;
//        game.currentPlayer = game.generateStartingPlayer();
//
//
//        //Game logic
//        //Will exist the while loop when all the pieces are out of a row
//        while(!game.isGameOver()) {
////            game.board.displayBoardCommandLine();
//            System.out.println("\n" + "PLayer " + game.currentPlayer + " choose a hole");
//            int holeIndex = in.nextInt();
//            game.makeMove(holeIndex);
//            game.switchPlayer();
//        }
//        game.getWinner();
//    }



}
