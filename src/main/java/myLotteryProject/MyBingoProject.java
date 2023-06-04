package myLotteryProject;

import java.util.*;

public class MyBingoProject {
    public static void main(String[] args) {
        int[][] bingoCard = generateBingoCard();
        Set<Integer> calledNumbers = new HashSet<>();

        System.out.println("Welcome to Bingo Game!");

        while (true) {
            int calledNumber = callNumber(calledNumbers);
            System.out.println("Number called: " + calledNumber);

            markNumber(bingoCard, calledNumber);
            displayBingoCard(bingoCard);

            if (checkBingo(bingoCard)) {
                System.out.println("BINGO! You won!");
                break;
            }

            System.out.println("Press Enter to continue or 'q' to quit.");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                System.out.println("Thank you for playing!");
                break;
            }
        }
    }

    private static int[][] generateBingoCard() {
        int[][] bingoCard = new int[5][5];
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 75; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                bingoCard[row][col] = numbers.remove(0);
            }
        }

        bingoCard[2][2] = 0; // Free space in the center

        return bingoCard;
    }

    private static int callNumber(Set<Integer> calledNumbers) {
        Random random = new Random();
        int calledNumber;

        do {
            calledNumber = random.nextInt(75) + 1;
        } while (calledNumbers.contains(calledNumber));

        calledNumbers.add(calledNumber);
        return calledNumber;
    }

    private static void markNumber(int[][] bingoCard, int calledNumber) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (bingoCard[row][col] == calledNumber) {
                    bingoCard[row][col] = 0;
                    return;
                }
            }
        }
    }

    private static void displayBingoCard(int[][] bingoCard) {
        System.out.println("BINGO CARD:");
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (bingoCard[row][col] == 0) {
                    System.out.print("   -  ");
                } else {
                    System.out.printf("%5d ", bingoCard[row][col]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean checkBingo(int[][] bingoCard) {
        // Check rows
        for (int row = 0; row < 5; row++) {
            if (bingoCard[row][0] == 0 && bingoCard[row][1] == 0 && bingoCard[row][2] == 0 &&
                    bingoCard[row][3] == 0 && bingoCard[row][4] == 0) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 5; col++) {
            if (bingoCard[0][col] == 0 && bingoCard[1][col] == 0 && bingoCard[2][col] == 0 &&
                    bingoCard[3][col] == 0 && bingoCard[4][col] == 0) {
                return true;
            }
        }

        // Check diagonals
        if ((bingoCard[0][0] == 0 && bingoCard[1][1] == 0 && bingoCard[2][2] == 0 &&
                bingoCard[3][3] == 0 && bingoCard[4][4] == 0) ||
                (bingoCard[0][4] == 0 && bingoCard[1][3] == 0 && bingoCard[2][2] == 0 &&
                        bingoCard[3][1] == 0 && bingoCard[4][0] == 0)) {
            return true;
        }

        return false;
    }}