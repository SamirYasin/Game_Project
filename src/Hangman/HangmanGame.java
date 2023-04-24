package Hangman;

import java.util.Scanner;

public class HangmanGame {

    private String[] board;

    private static String word;

    private static int lengthOfWord;

    private static String[] lettersToGuess;

    private static int incorrectGuesses = 0;



    public HangmanGame(){
        board = new String[]{"  ┌──┐",
                             "  O  │",
                             "     │",
                             "     │",
                             "   ──┴──"};
    }

    public void printBeginningBoard(){
        System.out.println("  ┌──┐");
        System.out.println("  O  │      Start game? (y/n)");
        System.out.println(" /|\\ │");
        System.out.println(" / \\ │");
        System.out.println("   ──┴──");
    }
    public void printEndBoard(){
        System.out.println("  ┌──┐");
        System.out.println("  O  │      Thank you for playing!");
        System.out.println(" /|\\ │");
        System.out.println(" / \\ │");
        System.out.println("   ──┴──");
    }

    public void startGame(){
        printBeginningBoard();
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        if(choice.toLowerCase().equals("n")) printEndBoard();
        else if (choice.toLowerCase().equals("y")) playGame();
        else {
            System.out.println("You must pick either Y or N!");
            startGame();
        }
    }

    public void printBoard(){
        for (String line : board) {
            System.out.println(line);
        }
    }

    public void printLetters(){
        for (String toGuess : lettersToGuess) {
            System.out.print(toGuess);
        }
    }

    public void playGame(){
        word = "hello";
        lengthOfWord = word.length();
        lettersToGuess = new String[lengthOfWord];
        for (int i = 0; i < lengthOfWord; i++) {
            lettersToGuess[i] = " - ";
        }
        Scanner input = new Scanner(System.in);
        printBoard();
        printLetters();
        messageToPlayer();

    }

    public void messageToPlayer(){
        if(incorrectGuesses < 6) System.out.println("Please guess a letter");
    }

    public void updateBoard(String[] board, String[] lettersToGuess, int incorrectGuesses, String guess, String word){
        if(!word.contains(guess)){
            incorrectGuesses++;
            switch (incorrectGuesses) {
                case 1:
                    board[1] = "  O  │";
                    break;
                case 2:
                    board[2] = " /   │";
                    break;
                case 3:
                    board[3] = " /|  │";
                    break;
                case 4:
                    board[4] = " /|\\ │";
                    break;
                case 5:
                    board[5] = " /   │";
                    break;
                case 6:
                    board[6] = " / \\ │";
                    break;
                default:
                    System.out.println("Game Over");
            }
        }
        else {
            for (int i = 0; i < word.length(); i++) {
                if(guess.equals("" + word.charAt(i))) lettersToGuess[i] = " " + guess + " ";
            }
        }

    }
}
