/***
 * Write a Java program that converts a given integer into its written English form.
Example :
● Input: 51
● Output: "fifty one"
● Explanation:
The number 51 is represented as "fifty" for 50 and "one" for 1.

Constraints:
● The input will be a positive integer between 1 and 1000.

Owner : Ajay-Jangir

Date of Creation : 03-09-2024
 */

package codeup_assignment;

import java.util.Scanner;

public class Number_Words_Converter {

    public static class NumberToWords {

        private static final String[] tensNames = {
            "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
        };

        private static final String[] numberNames = {
            "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"
        };

        private static final String[] specialNames = {
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
        };

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            string_expander newinput = new string_expander();

            System.out.println(newinput.num_prompt);
            
            while (true) {
                System.out.print(newinput.num_word);
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                try {
                    int number = Integer.parseInt(input);
                    if (number < 0 || number > 1000) {
                        System.out.println(newinput.num_out);
                    } else {
                        System.out.println("The number " + number + " in words is: " + numberToWords(number));
                    }
                } catch (NumberFormatException e) {
                    System.out.println(newinput.num_invalid);
                }
            }

            scanner.close();
        }

        public static String numberToWords(int number) {
            if (number == 1000) {
                return "One Thousand";
            }

            if (number == 0) {
                return "Zero";
            }

            return convert(number).trim();
        }

        private static String convert(int number) {
            if (number < 10) {
                return numberNames[number];
            } else if (number < 20) {
                return specialNames[number - 10];
            } else if (number < 100) {
                return tensNames[number / 10] + ((number % 10 != 0) ? " " + numberNames[number % 10] : "");
            } else if (number < 1000) {
                return numberNames[number / 100] + " Hundred" + ((number % 100 != 0) ? " and " + convert(number % 100) : "");
            }

            return ""; 
        }
    }
}