/***
 * ### Problem 1: Valid Parentheses Combination Generator
 * This problem involves generating all possible valid combinations of
 * characters from a given string. The function
 * `validParenthesesCombinationGenerator` handles this task. It first checks if
 * the input string is empty or contains only one character, in which case it
 * simply returns the input. For longer strings, it begins by storing each
 * character as a valid combination. Then, it iteratively generates all
 * 2-character combinations by pairing characters and ensures that no duplicate
 * combinations are stored. The function ultimately prints these unique
 * combinations, separated by commas. Edge cases include handling empty strings,
 * single-character strings, and strings with repeated characters. For example,
 * the input "abc" would result in the output "a, b, c, ab, ac, bc."
 *
 * ### Problem 2: Digit Sum Loop (String)
 * This problem focuses on reducing a string representation of digits to a
 * single digit by repeatedly summing its digits. The function `digitSumLoop`
 * handles this process. If the input string is empty, it returns 0. If the
 * string contains only one digit, it returns that digit as an integer.
 * Otherwise, the function sums the digits of the string repeatedly until the
 * sum becomes a single digit, which is then returned as the final result. Edge
 * cases include handling empty strings, single-digit strings, and ensuring
 * valid input through input validation. For instance, the input "12345" would
 * return 6, as 1+2+3+4+5 equals 15, and 1+5 equals 6.
 *
 * ### Problem 3: Encoding Challenge with ASCII Conversion
 * This problem would involve encoding a string by converting its characters
 * to their ASCII values and applying a transformation based on a given numeric pattern.
 * The function would take an input string and pattern, convert each character to its ASCII
 * value, manipulate the ASCII values according to the pattern, and return a new
 * string composed of the transformed characters. Edge cases might involve
 * handling non-alphabetic characters, managing pattern lengths greater than the
 * input string, and processing empty inputs or patterns.
 *
 * ### Problem 4: Caesar Cipher with Shift Variability
 * This problem implements a Caesar Cipher where each character in the input
 * string is shifted according to a specified numeric pattern. The function
 * `caesarCipherWithShiftVariability` takes an input string and a pattern of
 * numeric shifts. It processes alphabetic characters and digits, applying the
 * appropriate shifts while wrapping around within the alphabet or digit range.
 * Non-alphabetic characters, such as spaces, remain unchanged. The function
 * continues using the pattern cyclically until the entire input string is
 * processed. Edge cases include handling empty inputs or patterns, managing
 * pattern lengths that differ from the input string, and ensuring
 * non-alphabetic characters are left untouched. For example, with an input of
 * "abc123" and a pattern of "123," the output would be "bdf456."
 *
 * ### Problem 5: Consecutive Number Summer
 * This problem would involve summing consecutive numbers in an input string.
 * The function would generate all consecutive numbers from the string, sum them, and return the result.
 * Edge cases to consider would include handling non-numeric characters,
 * processing empty input strings, and dealing with input strings that contain
 * repeated digits. For example, given an input of "1234," the output would be
 * "10," resulting from the sum of 1+2+3+4.
 */

import java.util.Scanner;
public class Week2_Assignment1 {
    static HardCodedWords hardWords = new HardCodedWords();



//  function 1 Valid Parentheses Combination Generator

        public static void validParenthesesCombinationGenerator(String input) {
            String[] outputArray = new String[(input.length() * (input.length() - 1)) / 2 + input.length()];
            int count = 0;

            if (input.isEmpty() || input.length() == 1) {
                System.out.print(input);
            }
            else {
                for (int i = 0; i < input.length(); i++) {
                    outputArray[count] = String.valueOf(input.charAt(i));
                    count++;
                }

                for (int i = 0; i < input.length(); i++) {
                    for (int j = i + 1; j < input.length(); j++) {
                        String combination = String.valueOf(input.charAt(i)) + String.valueOf(input.charAt(j));

                        boolean exists = false;
                        for (int k = 0; k < count; k++) {
                            if (outputArray[k].equals(combination)) {
                                exists = true;
                                break;
                            }
                        }

                        if (!exists) {
                            outputArray[count] = combination;
                            count++;
                        }
                    }
                }

                for (int i = 0; i < count; i++) {
                    System.out.print(outputArray[i]);
                    if (i < count - 1) {
                        System.out.print(", ");
                    }
                }
            }
            System.out.println();
        }


//  function 2 Digit Sum Loop(String)

    public static int digitSumLoop(String input) {
        int sum = 0;
        if(input.isEmpty()){
            return 0;
        }
        else if (input.length() == 1) {
            return Integer.parseInt(input);
        }
        else {
            while (input.length() > 1) {
                sum = 0;
                for (int i = 0; i < input.length(); i++) {
                    sum += Character.getNumericValue(input.charAt(i));
                }
                input = String.valueOf(sum);
            }
        }
        return Integer.parseInt(input);


    }


//    function 4 Caesar Cipher with Shift Variability
public static String caesarCipherWithShiftVariability(String input, String pattern) {
    if (input == null || pattern == null || pattern.isEmpty()) {
        return "";
    }

    String output = "";
    int patternLength = pattern.length();
    int patternIndex = 0; // To keep track of the current position in the pattern

    for (int i = 0; i < input.length(); i++) {
        char currentCharacter = input.charAt(i);

        if (Character.isLetter(currentCharacter) || Character.isDigit(currentCharacter)) {

            int shift = Character.getNumericValue(pattern.charAt(patternIndex % patternLength));

            if (Character.isLetter(currentCharacter)) {

                char base = Character.isLowerCase(currentCharacter) ? 'a' : 'A';
                char newChar = (char) (((currentCharacter - base + shift) % 26 + 26) % 26 + base);
                output += newChar;
            } else if (Character.isDigit(currentCharacter)) {

                char newChar = (char) (((currentCharacter - '0' + shift) % 10 + 10) % 10 + '0');
                output += newChar;
            }


            patternIndex++;
        } else {

            output += currentCharacter;
        }
    }

    return output;
}




























    public static void main(String[] args) {

        Scanner Input = new Scanner(System.in);
        HardCodedWords.Option();
        int chooseCase = Input.nextInt();
        Input.nextLine();
        switch (chooseCase) {
            case 1:
                do {
                    System.out.print(hardWords.ENTER_STRING);
                    String stringInput = Input.nextLine();
                    validParenthesesCombinationGenerator(stringInput);
                    System.out.print(hardWords.AGAIN_CONTINUE);
                }while (Input.nextLine().equalsIgnoreCase("yes"));
                break;


            case 2:
                while (true) {
                    System.out.print(hardWords.ENTER_STRING_OR_EXIT);
                    String userInput = Input.nextLine();

                    if (userInput.equalsIgnoreCase("exit")) {
                        break;
                    }
                    try {
                        int intInput = Integer.parseInt(userInput);
                        int output = digitSumLoop(Integer.toString(intInput));
                        System.out.println(hardWords.RESULT + output);
                    } catch (NumberFormatException e) {
                        System.out.println(hardWords.INVALID_INPUT);
                    }
                }
                break;



            case 3:
                do {
                    System.out.println(hardWords.ENTER_STRING);
                    String stringInput = Input.nextLine();
                    System.out.println(hardWords.PATTERN);
                    String stringPattern = Input.nextLine();
                    caesarCipherWithShiftVariability(stringInput, stringPattern);
                    String Output = caesarCipherWithShiftVariability(stringInput, stringPattern);
                    System.out.println(hardWords.CODED_STRING+Output);
                    System.out.print(hardWords.AGAIN_CONTINUE);
                }while (Input.nextLine().equalsIgnoreCase("yes"));
                break;


        }
    }
}
