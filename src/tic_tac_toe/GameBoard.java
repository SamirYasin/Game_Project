package tic_tac_toe;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class GameBoard {

    public void StartGame() {
        playingGame();
    }

    private String[] board;

    public GameBoard() {
        board = new String[]{"_ ", "_ ", "_ ", "_ ", "_ ", "_ ", "_ ", "_ ", "_ "};
    }

    public void printBoard() {
        for (int i = 1; i <= board.length; i++) {
            if (i % 3 == 0) System.out.println(board[i - 1]);
            else System.out.print(board[i - 1]);
        }
    }

    public void playingGame() {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int count = 0;
        while (true) {
            count++;
            printBoard();
            System.out.println("Hey user, pick a number between 1-9");
            String usersMove = input.nextLine();
            while (!checkIfValidMove(usersMove, board) | !checkIfMoveTaken(usersMove, board)) {
                if (usersMove.isEmpty()) System.out.println("You did not select a move, please enter a number");
                else if(!checkIfNumber(usersMove)) System.out.println("Only numbers can be accepted as valid moves");
                else if(!checkIfValidMove(usersMove, board)) System.out.println("Please enter a valid number between 1-9");
                else if(!checkIfMoveTaken(usersMove, board)) System.out.println("That move is taken, please enter another number");
                usersMove = input.nextLine();
            }
            updateBoard(usersMove, "user");
            System.out.println("\u001B[33mYour Move\u001B[0m");
            if (checkIfSomeoneWon()) {
                printBoard();
                System.out.println("\u001B[32mYOU WIN!\u001B[0m");
                break;
            }
            else if(count == 5){
                System.out.println();
                printBoard();
                System.out.println("\u001B[34m" + "It's a CATS game" + "\u001B[0m");
                break;
            }
            printBoard();
            int computerMove = random.nextInt(9) + 1;

            while (!checkIfMoveTaken(String.valueOf(computerMove), board)) {
                computerMove = random.nextInt(9) + 1;
            }
            updateBoard(String.valueOf(computerMove), "computer");
            if (checkIfSomeoneWon()) {
                System.out.println();
                printBoard();
                System.out.println("\u001B[31mYOU LOSE\u001B[0m");
                break;
            }
            System.out.println("\u001B[33mComputer Move\u001B[0m");
        }
    }

    public Boolean checkIfValidMove(String user, String[] board) {
        if (user.isEmpty()) return false;
        else if (!checkIfNumber(user)) return false;
        else return !(Integer.parseInt(user) > 9 | Integer.parseInt(user) < 1);
    }

    public Boolean checkIfMoveTaken(String user, String[] board){
        if (user.isEmpty()) return false;
        else if (!checkIfNumber(user)) return false;
        else return board[Integer.parseInt(user) - 1].equals("_ ");
    }

    public Boolean checkIfNumber(String user){
        return Character.isDigit(user.charAt(0));
    }

    public void updateBoard(String move, String whoseMove) {

        String whichPlayer = whoseMove.toLowerCase();

        if (!Objects.equals(whichPlayer, "computer") &&
                !Objects.equals(whichPlayer, "user")) {
            //invalid parameter, update nothing
            board[Integer.parseInt(move) - 1] = "_ ";
        } else {
            String letterToAdd = whichPlayer.equals("user") ? "X " : "O ";
            board[Integer.parseInt(move) - 1] = letterToAdd;
        }
    }

    public Boolean checkIfSomeoneWon() {
        if (board[0].equals(board[1]) & board[1].equals(board[2]) & !board[0].equals("_ ")) return true;
        if (board[3].equals(board[4]) & board[4].equals(board[5]) & !board[3].equals("_ ")) return true;
        if (board[6].equals(board[7]) & board[7].equals(board[8]) & !board[7].equals("_ ")) return true;
        if (board[0].equals(board[3]) & board[3].equals(board[6]) & !board[0].equals("_ ")) return true;
        if (board[1].equals(board[4]) & board[4].equals(board[7]) & !board[1].equals("_ ")) return true;
        if (board[2].equals(board[5]) & board[5].equals(board[8]) & !board[2].equals("_ ")) return true;
        if (board[0].equals(board[4]) & board[4].equals(board[8]) & !board[0].equals("_ ")) return true;
        if (board[2].equals(board[4]) & board[4].equals(board[6]) & !board[2].equals("_ ")) return true;
        return false;
    }
}

