/***
 * Write a Java program to determine if a given integer is a prime number.
Example :
● Input: 21
● Output: "The given number is NOT prime"
● Explanation:
The number 21 is divisible by 3 and 7, hence it is not a prime number.

Constraints:
● The input will be a positive integer between 1 and 10^6.

Owner: Ajay-Jangir

Date of Creation: 03-09-2024
 */

package codeup_assignment;

import java.util.Scanner;

public class  Prime_Number_Checker{

    public static boolean isPrime(int number) {
        if (number < 1) return false;
        if (number <= 3) return true; 
        
        if (number % 2 == 0 || number % 3 == 0) return false;
        
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        string_expander newinput = new string_expander();

        
        while (true) {
            System.out.print(newinput.prompt_prime);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("done")) {
                break;
            }

            try {
                int number = Integer.parseInt(input);
                if (isPrime(number)) {
                    System.out.println(number + " is a prime number.");
                } else {
                    System.out.println(number + " is not a prime number.");
                }
            } catch (NumberFormatException e) {
                System.out.println(newinput.invalid_prime);
            }
        }

        scanner.close();
    }
}