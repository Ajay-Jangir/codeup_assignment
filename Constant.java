public class Constant {
    public String totalConstant = "Total number of consonants: ";
    public String binaryDecimal = "Binary to Decimal is: ";
    public String EXIT = "Exiting...";
    public String enterString = "Enter the String to check number of palindrome: ";
    public String totalPalindrome = "Number of palindromes in ";
    public String exitCode = "To 'Exit' enter 'quit' else press any key to continue: ";
    public String invalidInput = "Invalid Input! Please enter a valid string.";
    public String enterFibonacci = "Enter the Fibonacci Integer value: ";
    public String indexFibonacci = "Fibonacci value at ";
    public String enterSnakeToCamel = "Enter the String to change from SnakeToCamel case: ";
    public String convertedCase = "Converted to camelCase: ";
    public String countConstant = "Enter the String to Count consonants: ";
    public String enterBinary = "Enter the binary value (0 and 1 only): ";
    public String invalidBinary = "Invalid Input! Enter numbers containing only 0 and 1.";
    public String invalidChoice = "Invalid choice! Please enter a number between 1 and 6.";





    public static void Option() {
        System.out.println(" Press 1 for CountPalindromes " +
                "\n Press 2 for NthFibonacci " +
                "\n Press 3 for SnakeToCamel " +
                "\n Press 4 for CountConsonants " +
                "\n Press 5 for BinaryToDecimal " +
                "\n Press 6 for Exit");
        System.out.print("Choose the option from the above (Enter no. between 1-6): ");
    }

}