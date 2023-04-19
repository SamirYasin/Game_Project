package Helper;

import java.util.Arrays;

public class Helpers {
    public static void printAndClearAfterDelay(String message, long delayInMillis) {
        System.out.println(message);
        try {
            Thread.sleep(delayInMillis);
        } catch (InterruptedException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
