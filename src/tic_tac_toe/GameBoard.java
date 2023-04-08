package tic_tac_toe;

public class GameBoard {

    public void StartGame(){
        drawBoard();
    }
    public GameBoard() {
        int[][] board = new int[3][3];
    }
    public static void drawBoard(){
        for (int i = 0; i < 3; i++) {
            System.out.println("\n");
            for (int j = 0; j < 3; j++) {
                System.out.println(" x ");
            }

        }
        }


}
