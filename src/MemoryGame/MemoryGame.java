package MemoryGame;

import Helper.Helpers;

import java.util.*;

public class MemoryGame {

    private static int level = 1;
    private static int highScore = 1;
    private static int incorrectTries = 0;
    private static final List<Character> generatedArray = new ArrayList<>();
    private static final List<Character> usersArray = new ArrayList<>();

    public void startGame() {
        gameIntro();
        gameCycle(generatedArray, usersArray);
    }

    private void gameIntro() {
        showUsersLevel();
        countDown();
    }

    private void showUsersLevel() {
        String usersLevel = String.format("Level: " + level);
        Helpers.printAndClearAfterDelay(usersLevel, 1700);
    }

    private void countDown() {

        int count = 3;

        for (int i = 0; i < 3; i++) {
            String countDownNum = String.format("Game starts in %s ", count);
            Helpers.printAndClearAfterDelay(countDownNum, 900);
            count--;
        }
    }

    private void showGeneratedNumber(List<Character> _generatedArray) {
        //show level right here
        showUsersLevel();
        populateGeneratedArray(_generatedArray, level);

        for (char number : _generatedArray) {
            Helpers.printAndClearAfterDelay(String.valueOf(number), 800);
        }
    }

    //TODO REFACTOR THIS METHOD
    private void populateGeneratedArray(List<Character> _generatedArray, int usersCurrentLevel) {

        //based the generated array length off the players current level
        int arrayLength = usersCurrentLevel + 2;

        Random random = new Random();
        StringBuilder generatedAnswer = new StringBuilder();

        if (!_generatedArray.isEmpty()) _generatedArray.clear();

        for (int i = 0; i < arrayLength; i++) {
            generatedAnswer.append((Math.abs(random.nextInt() % 10)));
        }

        for (int i = 0; i < generatedAnswer.length(); i++) {
            _generatedArray.add(generatedAnswer.charAt(i));
        }
    }

    private void promptAndStoreUserAnswer(List<Character> _usersArray) {
        System.out.println("Type the numbers you saw");

        Scanner scanner = new Scanner(System.in);
        String usersAnswer = scanner.nextLine();

        if (!_usersArray.isEmpty()) _usersArray.clear();

        for (int i = 0; i < usersAnswer.length(); i++) {
            _usersArray.add(usersAnswer.charAt(i));
        }
    }

    private boolean isAnswerCorrect(List<Character> _generatedArray, List<Character> _usersArray) {

        if (_generatedArray.size() != _usersArray.size()) return false;

        return Arrays.equals(_generatedArray.toArray(), _usersArray.toArray());
    }

    private void gameCycle(List<Character> _generatedArray, List<Character> _usersArray) {

        showGeneratedNumber(_generatedArray);
        promptAndStoreUserAnswer(_usersArray);
        boolean isUsersAnswerCorrect = isAnswerCorrect(_generatedArray, _usersArray);
        boolean isGameOver = isGameOver();

        if (isUsersAnswerCorrect) {
            increaseLevelAndHighScore();
            gameCycle(generatedArray, usersArray);
        } else {
            incorrectTries++;
            level--;

            if (isGameOver) {
                gameOutro();
            } else {
                gameCycle(generatedArray, usersArray);
            }
        }
    }

    private void increaseLevelAndHighScore() {
        if (level == highScore) {
            level++;
            highScore++;
        } else {
            level++;
        }
    }

    private boolean isGameOver() {
        return level == 1 || incorrectTries == 3;
    }

    private void gameOutro() {
        System.out.println("Game over");
        System.out.println("High passed level: " + (highScore - 1));
        System.out.println();

        boolean doesUserWantToRetry = promptUserToRetry();

        if (doesUserWantToRetry) {
            resetGameStats();
            startGame();
        } else {
            System.out.println("Have a good one!");
        }
    }

    private boolean promptUserToRetry() {

        Scanner scanner = new Scanner(System.in);
        boolean breakOutLoop = false;
        boolean wantToRetry = false;
        String usersAnswer;

        System.out.println("Want to retry?: Y/N?");
        System.out.println();

        do {

            usersAnswer = scanner.nextLine().toLowerCase();

            if (!usersAnswer.equals("y") && !usersAnswer.equals("n")) {
                System.out.println("Huh?...Y or N please");
                continue;
            }

            if (usersAnswer.equals("y")) {

                wantToRetry = true;
            }

            breakOutLoop = true;

        } while (!breakOutLoop);

        return wantToRetry;
    }

    private void resetGameStats() {
        level = 1;
        highScore = 1;
        incorrectTries = 0;
    }
}