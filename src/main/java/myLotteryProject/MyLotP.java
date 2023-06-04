package myLotteryProject;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MyLotP {
    public static void main(String[] args) {
        int[] lotteryNumbers = generateLotNum();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Lotto 6/49!");

        System.out.print("Enter your 6 numbers (1-49), separated by spaces: ");
        int[] userNumbers = readUserNumbers(scanner);

        System.out.println("Lottery Numbers: " + Arrays.toString(lotteryNumbers));
        System.out.println("Your Numbers: " + Arrays.toString(userNumbers));

        int matchedNumbers = countMatchingNumbers(lotteryNumbers, userNumbers);

        if (matchedNumbers == 6) {
            System.out.println("Congratulations! You won the jackpot!");
        } else {
            System.out.println("You matched " + matchedNumbers + " number(s). Better luck next time!");
        }

        scanner.close();
    }

    private static int[] generateLotNum() {
        int[] lotteryNumbers = new int[6];
        Random random = new Random();

        for (int i = 0; i < lotteryNumbers.length; i++) {
            lotteryNumbers[i] = random.nextInt(49) + 1;
        }

        return lotteryNumbers;
    }

    private static int[] readUserNumbers(Scanner scanner) {
        int[] userNumbers = new int[6];

        for (int i = 0; i < userNumbers.length; i++) {
            userNumbers[i] = scanner.nextInt();
        }

        return userNumbers;
    }

    private static int countMatchingNumbers(int[] lotteryNum, int[] userNumbers) {
        int count = 0;

        for (int i = 0; i < userNumbers.length; i++) {
            if (Arrays.binarySearch(lotteryNum, userNumbers[i]) >= 0) {
                count++;
            }
        }

        return count;
    }
}
