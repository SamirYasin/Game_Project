package Project.Hangman;

import WebService.APIServices;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {

    private String[] board;

    private static String word;

    private static int lengthOfWord;

    private static String[] lettersToGuess;

    private static int incorrectGuesses = 0;

    private static int correctGuesses = 0;
    private static HashSet<String> lettersGuessed = new HashSet<String>();


    public HangmanGame() {
        //lettersGuessed;
    }

    public void printBeginningBoard() {
        System.out.println("  ┌──┐");
        System.out.println("  O  │      Start game? (y/n)");
        System.out.println(" /|\\ │");
        System.out.println(" / \\ │");
        System.out.println("   ──┴──");
    }

    public void printEndBoard() {
        System.out.println("  ┌──┐");
        System.out.println("  O  │      Thank you for playing!");
        System.out.println(" /|\\ │");
        System.out.println(" / \\ │");
        System.out.println("   ──┴──");
    }

    public void startGame() {
        printBeginningBoard();
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        if (choice.toLowerCase().equals("n")) printEndBoard();
        else if (choice.toLowerCase().equals("y")) playGame();
        else {
            System.out.println("You must pick either Y or N!");
            startGame();
        }
    }

    public void printBoard() {
        for (String line : board) {
            System.out.println(line);
        }
    }

    public void printLetters() {
        for (String toGuess : lettersToGuess) {
            System.out.print(toGuess);
        }
        messageToPlayer();
    }

    public void playGame() {

        Scanner input = new Scanner(System.in);
        //TODOS CALL SERVICE HERE

        word = APIServices.fetchRandomWordAPI();

        System.out.println("Press 1 to play the game or Press 2 for testing purposes");
        String playOrTest = input.next();

        switch (playOrTest) {
            case "1":
                System.out.println("Enjoy the game!");
                break;
            case "2":
                System.out.println("The word is: " + word);
                break;
            default:
                System.out.println("Input not recognized... oh well the games starting anyway");
                break;
        }

        lengthOfWord = word.length();
        lettersToGuess = new String[lengthOfWord];
        for (int i = 0; i < lengthOfWord; i++) {
            lettersToGuess[i] = " - ";
        }
        board = new String[]{"  ┌──┐",
                "     │",
                "     │",
                "     │",
                "   ──┴──"};

        while (incorrectGuesses < 6 & correctGuesses < lengthOfWord) {
            printBoard();
            printLetters();
            String guess = input.next();
            while (!verifySingleLetterGuess(guess)) {
                System.out.println("Your guess can only be one letter! Please enter another letter: ");
                guess = input.next();
            }
            updateBoard(board, lettersToGuess, guess, word);
        }
        printBoard();
        printLetters();

        input.nextLine();
        System.out.println("Would you like to play again? (Y/N)");
        String choice = input.nextLine();
        while (!choice.toLowerCase().equals("y") & !choice.toLowerCase().equals("n")) {
            System.out.println("Please only select Y or N");
            choice = input.next();
        }
        if (choice.toLowerCase().equals("y")) {
            resetStats();
            startGame();

        } else printEndBoard();

    }

    public void messageToPlayer() {
        System.out.println("Letters Guessed: " + lettersGuessed);
        if (incorrectGuesses == 6) System.out.println("Sorry you LOSE! The correct word was: " + word);
        else if (correctGuesses == lengthOfWord) System.out.println("Congratulations you WON!");
        else System.out.println("Please Guess a Letter: ");
    }

    public void updateBoard(String[] board, String[] lettersToGuess, String guess, String word) {
        if (!word.contains(guess) & !lettersGuessed.contains(guess)) {
            lettersGuessed.add(guess);
            incorrectGuesses++;
            switch (String.valueOf(incorrectGuesses)) {
                case "1":
                    board[1] = "  O  │";
                    break;
                case "2":
                    board[2] = " /   │";
                    break;
                case "3":
                    board[2] = " /|  │";
                    break;
                case "4":
                    board[2] = " /|\\ │";
                    break;
                case "5":
                    board[3] = " /   │";
                    break;
                case "6":
                    board[3] = " / \\ │";
                    break;
                default:
                    System.out.println("Game Over");
            }
        } else {
            if (!lettersGuessed.contains(guess)) {
                for (int i = 0; i < word.length(); i++) {
                    if (guess.equals("" + word.charAt(i))) {
                        lettersToGuess[i] = " " + guess + " ";
                        lettersGuessed.add(guess);
                        correctGuesses++;
                    }
                }
            }
        }

    }

    public void resetStats() {
        incorrectGuesses = 0;
        correctGuesses = 0;
        lettersGuessed.clear();
    }

    public static Boolean verifySingleLetterGuess(String guess) {
        return guess.length() == 1 & Character.isLetter(guess.charAt(0));
    }

    public String getRandomWord(String[] words) {
        Random random = new Random();
        int index = random.nextInt(words.length);
        return words[index];
    }

    private static String[] wordList = {"apple", "banana", "orange", "grape", "pineapple", "watermelon", "kiwi", "strawberry",
            "peach", "apricot", "plum", "pear", "blueberry", "raspberry", "mango", "papaya", "lemon", "lime", "cherry", "coconut",
            "melon", "fig", "guava", "kiwifruit", "nectarine", "pomegranate", "prune", "tangerine", "cranberry",
            "blackberry", "elderberry", "grapefruit", "mandarin", "cantaloupe", "honeydew", "dragonfruit", "starfruit",
            "passionfruit", "lychee", "durian", "rhubarb", "tomato", "eggplant", "cucumber", "zucchini", "pumpkin", "potato", "carrot",
            "kale", "broccoli", "cauliflower", "cabbage", "asparagus", "peas", "beans", "lentils", "chickpeas", "soybeans", "cashews",
            "almonds", "walnuts", "peanuts", "hazelnuts", "macadamia", "pistachio", "sesame", "flaxseed", "chia", "sunflower", "pumpkinseed",
            "oatmeal", "rice", "quinoa", "barley", "wheat", "amaranth", "oats", "flour", "yeast", "sugar", "honey", "maple", "syrup",
            "molasses", "agave", "nectar", "corn syrup", "rice", "corn", "pepper", "onion", "garlic",
            "ginger", "spinach", "lettuce"};
}
