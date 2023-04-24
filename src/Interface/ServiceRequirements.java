package Interface;

public class ServiceRequirements {
    public static void showRequirements(String service) {

        String header = String.format("Here's how %s works", service);
        String requirements = "";

        switch (service) {
            case "tic-tac-toe":
                requirements = String.format(
                        "\n" +
                                "            %s\n" +
                                "\n" +
                                "   Tic Tac Toe is a two-player game played on a 3x3 grid. \n" +
                                "The players take turns marking X or O in one of the nine \n" +
                                "spaces until one player succeeds in placing three of their \n" +
                                "marks in a horizontal, vertical, or diagonal row, or all \n" +
                                "spaces are filled without a winner, resulting in a tie. \n" +
                                "The game starts with an empty board, and each player takes\n" +
                                "turns placing their symbol in an empty cell of the grid.\n" +
                                "The first player to get three of their symbols in a row,\n" +
                                "either horizontally, vertically, or diagonally, wins the game.\n" +
                                "If all spaces are filled without a winner, the game is a draw." +
                                "\n" +
                                "\n"
                        , header
                );
                break;
            case "memory game":
                requirements = String.format(
                        "\n" +
                                "            %s\n" +
                                "\n" +
                                "This going to be a game that tests the users' memory. The game will start at three numbers that will\n" +
                                "randomly be flashed one at a time to the user. After the numbers are show, the user will have to pass those three numbers in the\n" +
                                "order the number was shown. If they get it correct, they will go up a level and be shown another set of random numbers with an\n" +
                                "additional number added at the end (four numbers in this case). If they get it incorrect, they will drop a level and be shown\n" +
                                "a new random set of numbers just one less than they were shown before. If the user fails three times (doesn't have to be in a row),\n" +
                                "they will lose the game." +
                                "\n" +
                                "\n"
                        , header
                );
                break;
            default:
                System.out.println("Cannot find a service description");
        }
        System.out.println(requirements);
    }
}
