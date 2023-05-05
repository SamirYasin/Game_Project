package Project.Interface;

import Project.Hangman.HangmanGame;
import Project.MemoryGame.MemoryGame;
import Project.tic_tac_toe.GameBoard;

import java.util.Objects;
import java.util.Scanner;

public class MainInterface {

    private static final String[] availableServices = {"A", "B", "C"};

    public static void run() {
        System.out.println("Welcome");
        takeUserToGame(promptUserForService());
    }

    private static void takeUserToGame(String usersChoice) {
        switch (usersChoice.toUpperCase()) {
            case "A":
                GameBoard gameBoard = new GameBoard();
                System.out.println("Welcome to tic-tac-toe!");
                gameBoard.StartGame();
                break;
            case "B":
                MemoryGame memoryGame = new MemoryGame();
                System.out.println("Welcome to number memory game!");
                memoryGame.startGame();
                break;
            case "C":
                HangmanGame hangmanGame = new HangmanGame();
                System.out.println("Welcome to Hang Man Game");
                hangmanGame.startGame();
                break;

            default:
                System.out.println("Users choice is not mapped to a service");
                break;
        }
    }

    private static String promptUserForService() {
        Scanner scanner = new Scanner(System.in);
        boolean breakOutLoop = false;
        String usersChoice;

        do {
            System.out.println("Choose A for: Tic tac toe");
            System.out.println("Choose B for: Number memory game");
            System.out.println("Choose C for: HangMan (Service currently not available)");
            System.out.println("Choose D for: Bank (Service currently not available)");
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

    private static boolean isServiceAvailable(String usersChoice) {
        for (String service : availableServices) {
            if (Objects.equals(usersChoice.toUpperCase(), service)) return true;
        }
        return false;
    }

    private static boolean isUsersChoiceALetter(String usersChoice) {
        if (usersChoice.length() != 1) return false;
        return Character.isLetter(usersChoice.charAt(0));
    }
}

