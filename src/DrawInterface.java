import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DrawInterface {
    ReadFile readFile = new ReadFile();
    UserInput userInput = new UserInput();

    private int stateOfGame = 1;
    private String secretWord = readFile.getWord();
    private int wordsLength = secretWord.length();
    char usersGuess;
    /*String continueOrNot;*/
    String usersProgress;
    List<Character> guessArray = new ArrayList<>();
    int mistakes = 0;

    public DrawInterface() {
        System.out.println("Start by guessing one letter");
        drawUnderscores();

        for (int i = 0; i < wordsLength; i++) {
            guessArray.add('_');
        }
    }

    public void gaming() {
        if (stateOfGame == 1) {
            usersGuess = userInput.charInput();
            isUserInputCorrect(usersGuess);
            System.out.println("=============");
            System.out.println("");
            checkStateOfGame();
        } else {
            System.out.println("would you like to play again?");

            //neponyatno
            Scanner scanner = new Scanner(System.in);
            String b = scanner.nextLine();
            /*continueOrNot = userInput.wordInput();*/

            if (b.equalsIgnoreCase("yes") || b.equalsIgnoreCase("y")) {
                refresh();
                System.out.println("word has been changed!");
                System.out.println("");
                drawUnderscores();
            } else {
                throw new GameOverException("thanks for playing!");
            }
        }
    }

    void refresh() {
        stateOfGame = 1;
        usersProgress = null;
        guessArray.removeAll(guessArray);
        mistakes = 0;
        secretWord = /*"marriage";*/readFile.getWord();
        wordsLength = secretWord.length();
        for (int i = 0; i < wordsLength; i++) {
            guessArray.add('_');
        }
    }

    void checkStateOfGame() {
        if (usersProgress != null) {
            if (usersProgress.equalsIgnoreCase(secretWord)) {
                stateOfGame = 0;
                System.out.println("you won!");
            }
        }

        if (mistakes == wordsLength) {
            stateOfGame = 0;
            System.out.println("you lost!");
            System.out.println("the word was " + secretWord);
        }
    }

    void isUserInputCorrect(char usersGuess) {
        boolean flag = false;
        for (int i = 0; i < secretWord.length(); i++) {
            if (Character.toLowerCase(secretWord.charAt(i)) == usersGuess) {
                checkUsersProgress(i, usersGuess);
                flag = true;
            }
        }

        if (flag) {
        } else {
            mistakes++;
        }

        System.out.println(getUsersProgress(usersProgress));

        System.out.println("you have " + (wordsLength - mistakes) + " tries left");
    }

    void updateUsersProgress(String guessingProgress) {

        for (int i = 0; i < wordsLength; i++) {
            if (guessingProgress.charAt(i) != '_') {
                guessArray.set(i, guessingProgress.charAt(i));
            }
        }

        usersProgress = guessArray.stream().map(String::valueOf).collect(Collectors.joining());
    }

    void checkUsersProgress(int pos, char usersGuess) {
        String guessingProgress = "";
        for (int i = 0; i < pos; i++) {
            guessingProgress += "_";
        }
        guessingProgress += usersGuess;
        for (int i = pos + 1; i < wordsLength; i++) {
            guessingProgress += "_";
        }
        updateUsersProgress(guessingProgress);
    }

    String getUsersProgress(String usersProgress) {
        if (usersProgress == null) {
            drawUnderscores();

            usersProgress = "wrong letter!";
        }
        return usersProgress;
    }

    void drawUnderscores() {
        String underscores = "";
        for (int i = 0; i < wordsLength; i++) {
            underscores += "_";
        }
        System.out.println(underscores);
    }
}
