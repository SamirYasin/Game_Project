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
                             "     │",
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

    public void startGame(){

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
    }
}
