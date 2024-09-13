/***
 * Below program performs the following functions:
 * 1. Counts unique substring palindromes in a string.
 * 2. print Nth fibonacci number.
 * 3. To convert snake case to camel case.
 * 4. Count consonant in a string.
 * 5. Convert Binary number to Decimal number.
 *
 * This Program is implemented using recursion only. No inbuild fuction or loop is used in implementation of logic.
 *
 * Owner: Ajay-Jangir
 *
 * Date of creation: 13/09/2024
 */

import java.util.Scanner;
import java.math.BigInteger;
public class Week2Assignment2 {
    static Constant constant = new Constant();

//  function 1 CountPalindromes
    private static String[] substrings;
    private static int index = 0;
    static int countPalindrome = 0;
    private static void storeUniqueSubstrings(String str, int start, int end) {
        if (start >= str.length()) {
            return;
        }
        if (end > str.length()) {
            storeUniqueSubstrings(str, start + 1, start + 2);
            return;
        }
        String currentSubstring = substring(str, start, end);
        if (!currentSubstring.trim().isEmpty() && !isSubstringRepeated(currentSubstring, 0, index)) {
            if (index < substrings.length) {
                substrings[index] = currentSubstring;
                index++;
            }
        }
        storeUniqueSubstrings(str, start, end + 1);
    }
    private static String substring(String str, int start, int end) {
        if (start >= end) {
            return "";
        }
        return str.charAt(start) + substring(str, start + 1, end);
    }
    
    private static boolean isSubstringRepeated(String sub, int start, int end) {
        if (start >= end) {
            return false;
        }

        if (substrings[start].equals(sub)) {
            return true;
        }

        return isSubstringRepeated(sub, start + 1, end);
    }

    // Recursive method to check if a string is a palindrome
    public static boolean isPalindrome(String str, int start, int end) {

        if (start >= end) {
            return true;
        }

        if (str.charAt(start) != str.charAt(end)) {
            return false;
        }

        return isPalindrome(str, start + 1, end - 1);
    }

    private static void checkPalindrome(String[] array, int index) {
        if (index >= array.length) {
            return;
        }

        if (array[index] != null && !array[index].trim().isEmpty() && isPalindrome(array[index], 0, array[index].length() - 1)) {
            countPalindrome++;
        }

        checkPalindrome(array, index + 1);
    }

    // function 2  nth NthFibonacci
    private static BigInteger[] memory;

    public static BigInteger NthFibonacci(int input) {
        if (input == 0) {
            return BigInteger.ZERO;
        } else if (input == 1) {
            return BigInteger.ONE;
        }

        if (!memory[input].equals(BigInteger.ZERO)) {
            return memory[input];
        }
        memory[input] = NthFibonacci(input - 1).add(NthFibonacci(input - 2));
        return memory[input];

    }

    private static void initializeMemory(int index) {
        if (index >= 0) {
            memory[index] = BigInteger.ZERO;
            initializeMemory(index - 1);
        }
    }

//    function 4 SnakeToCamel
    private static boolean isSnakeCase(String str, int index) {
        if (index >= str.length()) {
            return true;
        }
        char ch = str.charAt(index);
        if (ch == '_' || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')) {
            return isSnakeCase(str, index + 1);
        }
        return false;
    }

    private static String toSnakeCase(String str, int index) {
        if (index >= str.length()) {
            return "";
        }

        char ch = str.charAt(index);
        
        if (index == 0 && !Character.isAlphabetic(ch) && ch != '_') {
            return toSnakeCase(str, index + 1);
        }

        if (ch >= 'A' && ch <= 'Z') {
            return (index == 0 ? "" : "_") + Character.toLowerCase(ch) + toSnakeCase(str, index + 1);
        } else if (ch == '-' || ch == ' ') {
            return "_" + toSnakeCase(str, index + 1);
        } else if (ch == '_') {
            return "_" + toSnakeCase(str, index + 1);
        } else if (!((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9'))) {
            return toSnakeCase(str, index + 1);
        }
        return ch + toSnakeCase(str, index + 1);
    }
    
    private static String toCamelCase(String str, int index, boolean capitalizeNext) {
        if (index >= str.length()) {
            return "";
        }
        char ch = str.charAt(index);
        if (ch == '_') {
            return toCamelCase(str, index + 1, true);
        }
        if (capitalizeNext) {
            return Character.toUpperCase(ch) + toCamelCase(str, index + 1, false);
        }
        return ch + toCamelCase(str, index + 1, false);
    }
    
    static int consonantCounter = 0;

    public static void countConsonants(String input) {
        countConsonant(input, 0);
    }

    public static void countConsonant(String input, int index) {
        if (index >= input.length()) {
            System.out.println(constant.totalConstant + consonantCounter);
            return;
        }
        char currentCharacter = Character.toLowerCase(input.charAt(index));
        if (currentCharacter >= 'a' && currentCharacter <= 'z' && currentCharacter != 'a' && currentCharacter != 'e' && currentCharacter != 'i' && currentCharacter != 'o' && currentCharacter != 'u') {
            consonantCounter += 1;
        }
        countConsonant(input, index + 1);
    }
    
//    function 5 binary to decimal
    public static boolean isBinaryLong(String input) {
        long newLong = Long.parseLong(input);
        while (newLong > 0) {
            long digit = newLong % 10;
            if (digit != 0 && digit != 1) {
                return false;
            }
            newLong /= 10;
        }
        return true;
    }

    static long power = 1;
    static long remainder = 0;
    static long decimalOutput = 0;

    public static void binaryToDecimal(String binaryInput) {
        long newBinaryInput = Long.parseLong(binaryInput);
        binaryToDecimalHelper(newBinaryInput);
    }

    public static void binaryToDecimalHelper(long newBinaryInput) {
        if (newBinaryInput == 0) {
            System.out.println(constant.binaryDecimal + decimalOutput);
            return;
        }
        remainder = newBinaryInput % 10;
        newBinaryInput = newBinaryInput / 10;
        decimalOutput += power * remainder;
        power *= 2;
        binaryToDecimalHelper(newBinaryInput);
    }
    
    //main function
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int choice = 0;
            try {
                Constant.Option();
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 6) {
                    System.out.println(constant.EXIT);
                    break;
                }

                switch (choice) {
                    case 1:
                        while (true) {
                            try {
                                System.out.print(constant.enterString);
                                String inputString = scanner.nextLine().trim();
                                if (inputString.isEmpty() || inputString.trim().isEmpty() || inputString.equals(" ") || inputString.equals("''")) {
                                    System.out.println(constant.totalPalindrome + inputString + " is: 0");
                                } else {
                                    int maxSubstrings = (inputString.length() * (inputString.length() + 1)) / 2;
                                    substrings = new String[maxSubstrings];
                                    index = 0;
                                    storeUniqueSubstrings(inputString, 0, 1);
                                    countPalindrome = 0;
                                    checkPalindrome(substrings, 0);
                                    System.out.println(constant.totalPalindrome + inputString + " is: " + countPalindrome);
                                }
                                System.out.print(constant.exitCode);
                                String exitChoice = scanner.nextLine().trim();
                                if (exitChoice.equalsIgnoreCase("quit")) {
                                    break;
                                }
                            }
                            catch (Exception e) {
                                System.out.println(constant.invalidInput);
                            } catch (StackOverflowError e){
                                System.out.println(constant.invalidInput);
                            }
                        }
                        break;

                    case 2:
                        do {
                            try {
                                System.out.print(constant.enterFibonacci);
                                int fibonacciInteger = scanner.nextInt();
                                scanner.nextLine();
                                memory = new BigInteger[fibonacciInteger + 1];
                                initializeMemory(fibonacciInteger);
                                BigInteger result =  NthFibonacci(fibonacciInteger);
                                String resultString = result.toString();
                                if (resultString.length() > 1000) {
                                    resultString = resultString.substring(0, 1000);
                                    System.out.println("Fibonacci value at index " + fibonacciInteger + " (truncated to 12 digits): " + resultString);
                                } else {
                                    System.out.println("Fibonacci value at index " + fibonacciInteger + ": " + resultString);
                                }
                                System.out.print(constant.exitCode);
                            } catch (Exception e) {
                                System.out.println(constant.invalidInput);
                            } catch (StackOverflowError e){
                                System.out.println(constant.invalidInput);
                            }
                        } while (!(scanner.nextLine().trim()).equalsIgnoreCase("quit"));
                        break;

                    case 3:
                        while (true) {
                            try {
                                System.out.print(constant.enterSnakeToCamel);
                                String input = scanner.nextLine().trim();
                                if (isSnakeCase(input, 0)) {
                                    String camelCase = toCamelCase(input, 0, false);
                                    System.out.println(constant.convertedCase + camelCase);
                                } else {
                                    String snakeCase = toSnakeCase(input, 0);
                                    String camelCase = toCamelCase(snakeCase, 0, false);
                                    System.out.println(constant.convertedCase + camelCase);
                                }
                                System.out.print(constant.exitCode);
                                String exitChoice = scanner.nextLine().trim();
                                if (exitChoice.equalsIgnoreCase("quit")) {
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println(constant.invalidInput);
                            }
                        }
                        break;

                    case 4:
                        do {
                            try {
                                System.out.print(constant.countConstant);
                                String inputString = scanner.nextLine();
                                countConsonants(inputString);
                                consonantCounter = 0;
                                System.out.print(constant.exitCode);
                            } catch (Exception e) {
                                System.out.println(constant.invalidInput);
                            }
                        } while (!(scanner.nextLine().trim()).equalsIgnoreCase("quit"));
                        break;

                    case 5:
                        while (true) {
                            try {
                                System.out.print(constant.enterBinary);
                                String inputBinary = scanner.nextLine();
                                if (isBinaryLong(inputBinary)) {
                                    binaryToDecimal(inputBinary);
                                    power = 1;
                                    remainder = 0;
                                    decimalOutput = 0;
                                } else {
                                    System.out.println(constant.invalidInput);
                                    continue;
                                }
                                System.out.print(constant.exitCode);
                                String exitChoice = scanner.nextLine().trim();
                                if (exitChoice.equalsIgnoreCase("quit")) {
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(constant.invalidBinary);
                            }
                        }
                        break;

                    default:
                        System.out.println(constant.invalidChoice);
                }
            } catch (Exception e) {
                System.out.println(constant.invalidInput);
                scanner.nextLine();
            }
        }
    }
}
