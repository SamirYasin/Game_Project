package Interface;

import tic_tac_toe.GameBoard;

import java.util.Scanner;

public class MainInterface {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome");
        System.out.println("Choose A for: Tic tac toe");
        System.out.println("Choose B for: Bank");
        System.out.println("Choose C for: HandMan");
        System.out.println("Choose D for: Random number memory game");
        System.out.println("Choose E for: Movie API");

        String usersChoice = scanner.next();
        userOptions(usersChoice);
    }

    private void userOptions(String usersChoice) {

        String choice = usersChoice.toUpperCase();

        switch (choice) {
            case "A":
                GameBoard gameBoard = new GameBoard();
                gameBoard.StartGame();
                break;
            default:

                break;
        }
    }
}
