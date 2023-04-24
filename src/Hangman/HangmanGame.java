package Hangman;

import java.util.Scanner;

public class HangmanGame {

    private String[] board;

    public HangmanGame(){
        board = new String[]{"  ┌──┐",
                             "  O  │",
                             " /|\\ │",
                             " / \\ │",
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

    public void playGame(){
        Scanner input = new Scanner(System.in);
    }
}
