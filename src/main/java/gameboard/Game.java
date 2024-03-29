package gameboard;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private Board board;
    private int player1;
    private int player2;
    public int currentPlayer;
    public int lastHoleIndex;

    public Game() {
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public int getPlayer1() {
        return player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public void setPlayer1(int player1) {
        this.player1 = player1;
    }

    public void setPlayer2(int player2) {
        this.player2 = player2;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int nextHole(int holeIndex) {

        holeIndex++;
        if(holeIndex > 13){
            holeIndex = 0;
        }
        //this.holeIndex = holeIndex;
        return holeIndex;
    }

    public void switchPlayer() {
        if(currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }


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

    public int generateStartingPlayer() {
        Random rand = new Random();
        int playerNumber = rand.nextInt(2) + 1;
        System.out.println("Player " + playerNumber +" is first!");
        return playerNumber;
    }

    public boolean isGameOver() {
        return board.isRowEmpty(board.getRow1()) || board.isRowEmpty(board.getRow2());
    }

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

    public void startGame() {

        //Set-up
        Scanner in = new Scanner(System.in);
        Game game = new Game();
        game.player1 = 1;
        game.player2 = 2;
        game.currentPlayer = game.generateStartingPlayer();


        //Game logic
        //Will exist the while loop when all the pieces are out of a row
        while(!game.isGameOver()) {
            game.board.displayBoardCommandLine();
            System.out.println("\n" + "PLayer " + game.currentPlayer + " choose a hole");
            int holeIndex = in.nextInt();
            game.makeMove(holeIndex);
            game.switchPlayer();
        }
        game.getWinner();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
//        game.player1 = 1;
//        game.player2 = 2;
//        game.currentPlayer = 1;
//        game.board.displayBoardCommandLine();
//        game.makeMove(11);
//        game.board.displayBoardCommandLine();
//        game.makeMove(8);
//        game.board.displayBoardCommandLine();

    }

}
