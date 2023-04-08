package tic_tac_toe;

public class GameBoard {

    public void StartGame(){
        printBoard();
        playingGame();
    }
    private String[] board;
    private String[] refBoard;

    public GameBoard() {
        board = new String[]{"_ ", "_ ", "_ ", "_ ", "_ ", "_ ","_ ", "_ ", "_ "};
    }

    public void printBoard() {
        for (int i = 1; i <= board.length; i++) {
            if(i % 3 == 0) System.out.println(board[i -1]);
            else System.out.print(board[i -1]);
        }
    }


    public void playingGame(){

    }
}
