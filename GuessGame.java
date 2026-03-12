import java.util.Random;
import java.util.Scanner;

public class GuessGame {
    public static void main(String[] args) {

        Random random = new Random();
        Scanner input = new Scanner(System.in);

        int number = random.nextInt(100) + 1;
        int guess = 0;
        int attempts = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Guess a number between 1 and 100");

        while (guess != number) {

            System.out.print("Enter your guess: ");
            guess = input.nextInt();
            attempts++;

            if (guess < number) {
                System.out.println("Too low! Try again.");
            }
            else if (guess > number) {
                System.out.println("Too high! Try again.");
            }
            else {
                System.out.println("🎉 Correct! You guessed in " + attempts + " attempts.");
            }
        }

        input.close();
    }
}