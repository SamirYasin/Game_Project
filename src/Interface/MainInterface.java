package Interface;

import tic_tac_toe.GameBoard;

import java.util.Objects;
import java.util.Scanner;

public class MainInterface {

    private static final String[] availableServices = {"A"};

    public void run() {
        System.out.println("Welcome");
        takeUserToGame(promptUserForService());
    }

    private void takeUserToGame(String usersChoice) {
        switch (usersChoice.toUpperCase()) {
            case "A":
                GameBoard gameBoard = new GameBoard();
                System.out.println("Welcome to tic-tac-toe!");
                gameBoard.StartGame();
                break;
            default:
                System.out.println("Users choice is not mapped to a service");
                break;
        }
    }

    private String promptUserForService() {
        Scanner scanner = new Scanner(System.in);
        boolean breakOutLoop = false;
        String usersChoice;

        do {
            System.out.println("Choose A for: Tic tac toe");
            System.out.println("Choose B for: Bank (Service currently not available)");
            System.out.println("Choose C for: HandMan (Service currently not available)");
            System.out.println("Choose D for: Random number memory game (Service currently not available)");
            System.out.println("Choose E for: Movie API (Service currently not available)");

            usersChoice = scanner.next();
            boolean isChoiceASingleLetter = isUsersChoiceALetter(usersChoice);
            boolean isChoiceAService = isServiceAvailable(usersChoice);

            if (!isChoiceASingleLetter) {
                System.out.println("Please choose either A, B, C, D, or E");
                continue;
            }

            if (!isChoiceAService) {
                System.out.println("Sorry this service is currently unavailable");
                continue;
            }

            breakOutLoop = true;

        } while (!breakOutLoop);

        return usersChoice;
    }

    private boolean isServiceAvailable(String usersChoice) {
        for (String service : availableServices) {
            if (Objects.equals(usersChoice.toUpperCase(), service)) return true;
        }
        return false;
    }

    private boolean isUsersChoiceALetter(String usersChoice) {
        if (usersChoice.length() != 1) return false;
        return Character.isLetter(usersChoice.charAt(0));
    }
}

