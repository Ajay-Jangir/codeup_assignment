/**
 * This class provides methods for converting numbers between different bases (binary, octal, decimal, hexadecimal)
 * and performing arithmetic operations (addition, subtraction, multiplication) on them.
 * Owner:- Ajay-Jangir
 * Date of Creation :- 27-09-2024
 */
import java.util.Scanner;

public class numberSystem {
    static Scanner input = new Scanner(System.in);
    static String[] Hexadecimal = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
    static Constant constant = new Constant();
    static errorConstant error = new errorConstant();

    // Validates if the input string is a valid hexadecimal number.
    // Parameters: String input
    // Output: boolean
    public static boolean isValidInput(String input) {
        if (input == null || input.isEmpty() || input.length() > 10) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (Character.isWhitespace(character) || (!Character.isDigit(character) && (character < 'A' || character > 'F'))) {
                return false;
            }
        }
        return true;
    }

    // Validates if the input string is a valid choice (an integer).
    // Parameters: String input
    // Output: boolean
    public static boolean isValidChoice(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Reverses the input string.
    // Parameters: String input
    // Output: String (reversed string)
    public String reverse(String input) {
        String reverseString = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            reverseString += input.charAt(i);
        }
        return reverseString;
    }

    // Computes the power of a base raised to an exponent using recursion.
    // Parameters: int base, int exponent
    // Output: int (result of base^exponent)
    public int power(int base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        return base * power(base, exponent - 1);
    }

    // Checks if the input string represents a binary number.
    // Parameters: String input
    // Output: boolean
    public boolean isBinary(String input) {
        int number = Integer.parseInt(input);
        if (number < 0) {
            return false;
        }
        if (input == null || input.isEmpty()) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            int numericValue = Character.getNumericValue(character);
            if (!Character.isDigit(character) || (numericValue != 0 && numericValue != 1)) {
                return false;
            }
        }
        return true;
    }

    // Checks if the input string represents an octal number.
    // Parameters: String input
    // Output: boolean
    public boolean isOctal(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            int numericValue = Character.getNumericValue(character);
            if (!Character.isDigit(character) || numericValue < 0 || numericValue > 7) {
                return false;
            }
        }
        return true;
    }

    // Checks if the input string represents a hexadecimal number.
    // Parameters: String input
    // Output: boolean
    public boolean isHexadecimal(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (!((character >= '0' && character <= '9') ||
                    (character >= 'A' && character <= 'F'))) {
                return false;
            }
        }
        return true;
    }

    // Checks if the input string represents a decimal number.
    // Parameters: String input
    // Output: boolean
    public boolean isDecimal(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            int numericValue = Character.getNumericValue(character);
            if (numericValue < 0 || numericValue > 9) {
                return false;
            }
        }
        return true;
    }

    // Converts a decimal number (as a string) to its binary representation.
    // Parameters: String number
    // Output: String (binary representation)
    public String toBinary(String number) {
        int numberInput = Integer.parseInt(number);
        String binaryResult = "";
        if (numberInput == 0) {
            return "0";
        }
        while (numberInput > 0) {
            int remainder = numberInput % 2;
            binaryResult += remainder;
            numberInput = numberInput / 2;
        }
        return reverse(binaryResult);
    }

    // Converts a decimal number (as a string) to its octal representation.
    // Parameters: String number
    // Output: String (octal representation)
    public String toOctal(String number) {
        int numberInput = Integer.parseInt(number);
        String octalResult = "";
        if (numberInput == 0) {
            return "0";
        }
        while (numberInput > 0) {
            int remainder = numberInput % 8;
            octalResult += remainder;
            numberInput = numberInput / 8;
        }
        return reverse(octalResult);
    }

    // Converts a decimal number (as a string) to its hexadecimal representation.
    // Parameters: String number
    // Output: String (hexadecimal representation)
    public String toHexadecimal(String number) {
        int numberInput = Integer.parseInt(number);
        String HexadecimalResult = "";
        if (numberInput == 0) {
            return "0";
        }
        while (numberInput > 0) {
            int remainder = numberInput % 16;
            HexadecimalResult += Hexadecimal[remainder];
            numberInput = numberInput / 16;
        }
        return reverse(HexadecimalResult);
    }

    // Finds the index of a character in the provided array.
    // Parameters: String[] array, char value
    // Output: int (index of the character in the array, or -1 if not found)
    public static int getIndexOfElement(String[] array, char value) {
        String character = Character.toString(value);
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(character)) {
                return i;
            }
        }
        return -1;
    }

    // Converts a number from a given base to decimal.
    // Parameters: int base, String number
    // Output: String (decimal representation)
    public String toDecimal(int base, String number) {
        int lengthString = number.length();
        int decimalResult = 0;
        for (int i = 0; i < lengthString; i++) {
            char character = number.charAt(i);
            int index = getIndexOfElement(Hexadecimal, character);
            decimalResult += index * power(base, lengthString - 1 - i);
        }
        return String.valueOf(decimalResult);
    }


    static int userInputNumber;
    // Converts a number from a specified base to decimal.
    // Output: String (decimal representation of the input number)
    public String fromBase() {
        while (true) {
            try {
                System.out.print(constant.CHOOSE_BASE);
                String base = input.nextLine().trim();
                if (!isValidChoice(base)) {
                    System.out.println(error.INVALID_INPUT);
                    continue;
                }
                int baseInteger = Integer.parseInt(base);
                if (baseInteger != 2 && baseInteger != 8 && baseInteger != 10 && baseInteger != 16) {
                    System.out.println(error.CORRECT_BASE);
                    continue;
                }
                while (true) {
                    System.out.print(constant.ENTER_NUMBER);
                    String number = input.nextLine().trim();
                    if (!isValidInput(number)) {
                        System.out.println(error.ENTER_VALID_INPUT);
                        continue;
                    }
                    if (baseInteger == 2 && isBinary(number)) {
                        userInputNumber = Integer.parseInt(number);
                        return toDecimal(baseInteger, number);
                    } else if (baseInteger == 8 && isOctal(number)) {
                        userInputNumber = Integer.parseInt(number);
                        return toDecimal(baseInteger, number);
                    } else if (baseInteger == 10 && isDecimal(number)) {
                        userInputNumber = Integer.parseInt(number);
                        return toDecimal(baseInteger, number);
                    } else if (baseInteger == 16 && isHexadecimal(number)) {
                        userInputNumber = Integer.parseInt(number);
                        return toDecimal(baseInteger, number);
                    } else {
                        System.out.println(error.INVALID_NUMBER);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(error.INVALID_NUMBER);
            }
        }
    }

    // Converts a decimal number (stored as a string) to a specified base.
    // Parameters: String number (decimal number to convert)
    // Output: void (prints the converted number to the console)
    public void toBase(String number) {
        while (true) {
            try {
                System.out.print(constant.CHOOSE_OUTPUT_BASE);
                String base = input.nextLine().trim();
                if (!isValidChoice(base)) {
                    System.out.println(error.INVALID_INPUT);
                    continue;
                }
                int baseInteger = Integer.parseInt(base);
                if (baseInteger != 2 && baseInteger != 8 && baseInteger != 10 && baseInteger != 16) {
                    System.out.println(error.CORRECT_BASE);
                    continue;
                }
                switch (baseInteger) {
                    case 2:
                        System.out.println(constant.NUMBER + userInputNumber + constant.BINARY + toBinary(number));
                        return;
                    case 8:
                        System.out.println(constant.NUMBER + userInputNumber + constant.OCTAL + toOctal(number));
                        return;
                    case 10:
                        System.out.println(constant.NUMBER + userInputNumber + constant.DECIMAL + number);
                        return;
                    case 16:
                        System.out.println(constant.NUMBER + userInputNumber + constant.HEXADECIMAL + toHexadecimal(number));
                        return;
                    default:
                        System.out.println(error.INVALID);
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println(error.VALID_BASE);
            }
        }
    }

    static String firstNumber;
    static String secondNumber;

    // Prompts user for two numbers and stores them for operation.
    // Output: void
    public void operationInput() {
        firstNumber = getNumberFromUser(constant.FIRST);
        secondNumber = getNumberFromUser(constant.SECOND);
    }

    // Gets a number from the user based on specified base.
    // Parameters: String numberPosition (indicates which number is being input)
    // Output: String (decimal representation of the input number)
    private String getNumberFromUser(String numberPosition) {
        String base;
        int baseInteger;
        String number;
        while (true) {
            try {
                System.out.print(constant.CHOOSE_BASE_OF + numberPosition + constant.USER_INPUT_NUMBER);
                base = input.nextLine().trim();
                if (!isValidChoice(base)) {
                    System.out.println(error.INVALID_INPUT);
                    continue;
                }
                baseInteger = Integer.parseInt(base);
                if (baseInteger != 2 && baseInteger != 8 && baseInteger != 10 && baseInteger != 16) {
                    System.out.println(error.CORRECT_BASE);
                    continue;
                }
                while (true) {
                    System.out.print(constant.ENTER_THE + numberPosition + constant.CHOSEN_BASE);
                    number = input.nextLine().trim();
                    if (!isValidInput(number)) {
                        System.out.println(error.ENTER_VALID_INPUT);
                        continue;
                    }

                    if ((baseInteger == 2 && isBinary(number)) ||
                            (baseInteger == 8 && isOctal(number)) ||
                            (baseInteger == 10 && isDecimal(number)) ||
                            (baseInteger == 16 && isHexadecimal(number))) {

                        return toDecimal(baseInteger, number);
                    } else {
                        System.out.println(error.INVALID_NUMBER);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(error.INVALID_INPUT);
            }
        }
    }

    String operationResult;

    // Performs addition on two decimal numbers and returns the result.
    // Parameters: String first (first number), String second (second number)
    // Output: String (result of the addition)
    public String addition(String first, String second) {
        userChoice = "+";
        int firstInput = Integer.parseInt(first);
        int secondInput = Integer.parseInt(second);
        operationResult = String.valueOf(firstInput + secondInput);
        return operationResult;
    }

    // Performs subtraction on two decimal numbers and returns the result.
    // Parameters: String first (first number), String second (second number)
    // Output: String (result of the subtraction)
    public String subtraction(String first, String second) {
        userChoice = "-";
        int firstNumber = Integer.parseInt(first);
        int secondNumber = Integer.parseInt(second);
        int subtractionResult = firstNumber - secondNumber;
        if (subtractionResult < 0) {
            onesCompliment(-subtractionResult);
        }
        operationResult = String.valueOf(subtractionResult);
        return operationResult;
    }

    // Computes the one's complement of a negative number and invokes two's complement.
    // Parameters: int value (negative number to compute one's complement for)
    // Output: void
    public void onesCompliment(int value) {
        String outputValue = "";
        String resultBinary = toBinary(String.valueOf(value));
        for (int i = 0; i < resultBinary.length(); i++) {
            char currentCharacter = resultBinary.charAt(i);
            if (currentCharacter == '1') {
                outputValue += '0';
            } else {
                outputValue += '1';
            }
        }
        twosCompliment(outputValue);
    }

    String twoComplementOutput;

    // Computes the two's complement of a binary string.
    // Parameters: String input (binary string for which to compute two's complement)
    // Output: void
    public void twosCompliment(String input) {
        String twoCompliment = toDecimal(2, input);
        twoComplementOutput = toBinary(String.valueOf(Integer.parseInt(twoCompliment) + 1));
    }

    static String userChoice;

    // Displays the operation result in the chosen base.
    // Parameters: String result (result of the operation)
    // Output: void
    public void operationResult(String result) {
        int baseInteger;
        while (true) {
            try {
                System.out.print(constant.CHOOSE_OUTPUT_BASE);
                String base = input.nextLine().trim();
                if (!isValidChoice(base)) {
                    System.out.println(error.INVALID_INPUT);
                    continue;
                }
                baseInteger = Integer.parseInt(base);
                if (baseInteger != 2 && baseInteger != 8 && baseInteger != 10 && baseInteger != 16) {
                    System.out.println(error.CORRECT_BASE);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(error.VALID_BASE);
            }
        }
        switch (baseInteger) {
            case 2:
                if (Integer.parseInt(result) >= 0) {
                    System.out.println(constant.RESULT_OF + userOperationChoice + constant.BINARY + toBinary(result));
                } else {
                    System.out.println(constant.RESULT_OF + userOperationChoice + constant.BINARY + twoComplementOutput);
                }
                break;
            case 8:
                if (Integer.parseInt(result) >= 0) {
                    System.out.println(constant.RESULT_OF + userOperationChoice + constant.OCTAL + toOctal(result));
                } else {
                    System.out.println(constant.RESULT_OF + userOperationChoice + constant.OCTAL+"-" + toOctal(String.valueOf(-Integer.parseInt(result))));
                }
                break;
            case 10:
                System.out.println(constant.RESULT_OF + userOperationChoice + constant.DECIMAL + result);
                break;
            case 16:
                if (Integer.parseInt(result) >= 0) {
                    System.out.println(constant.RESULT_OF + userOperationChoice + constant.HEXADECIMAL + toHexadecimal(result));
                } else {
                    System.out.println(constant.RESULT_OF + userOperationChoice + constant.HEXADECIMAL+"-" + toHexadecimal(String.valueOf(-Integer.parseInt(result))));
                }
                break;
            default:
                System.out.println(error.INVALID);
                break;
        }
    }

    String userOperationChoice;

    // Manages the arithmetic operation (addition or subtraction) based on user input.
    // Output: void
    public void operation() {
        operationInput();
        System.out.print(constant.ENTER_OPERATION);
        String operation = input.nextLine().trim();

        try {
            isValidChoice(operation);
            int operationChoice = Integer.parseInt(operation);
            switch (operationChoice) {
                case 1:
                    userOperationChoice = constant.ADDITION;
                    String resultAddition = addition(firstNumber, secondNumber);
                    operationResult(resultAddition);
                    break;
                case 2:
                    userOperationChoice = constant.SUBTRACTION;
                    String resultSubtraction = subtraction(firstNumber, secondNumber);
                    operationResult(resultSubtraction);
                    break;
                default:
                    System.out.println(error.INVALID_OPTION);
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println(error.INVALID_NUMERIC_VALUE);
        } catch (Exception e) {
            System.out.println(error.ERROR_OCCURED + e.getMessage());
        }
    }

    // Main method to run the number system conversion and operations.
    // Output: void
    public static void main(String[] args) {
        boolean condition = true;
        while (condition) {
            try {
                numberSystem numbers = new numberSystem();
                System.out.print(constant.CONVERT);
                String chooseOption = input.nextLine();
                int option = Integer.parseInt(chooseOption);
                switch (option) {
                    case 1:
                        numbers.toBase(numbers.fromBase());
                        break;
                    case 2:
                        numbers.operation();
                        break;
                    case 3:
                        System.out.println(constant.EXIT);
                        condition = false;
                        break;
                    default:
                        System.out.println(error.INVALID_CHOICE);
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println(error.INVALID_CHOICE);
            } catch (Exception e) {
                System.out.println(error.ERROR_OCCURED + e.getMessage());
            }
        }
    }
}