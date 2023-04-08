package tic_tac_toe;

public class GameBoard {

    public void StartGame(){
        printBoard();
    }
    private String[][] board;

    public GameBoard() {
        board = new String[][]{{"_", "_", "_"}, {"_", "_", "_"}, {"_", "_", "_"}};
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
