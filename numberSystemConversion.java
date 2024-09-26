import java.util.Scanner;
public class numberSystemConversion {
    static Scanner scan = new Scanner(System.in);
    static String[] octal = {"000", "001" , "010" , "011" , "100" , "101" , "110" ,"111"};
    static String[] Hexadecimal = {"0000", "0001" , "0010" , "0011" , "0100" , "0101" , "0110" , "0111" , "1000" , "1001" , "A", "B" , "C" , "D" , "E" , "F"};

//    check for valid value
public boolean isValidInput(String input) {
    if (input == null || input.isEmpty()) {
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


    //    reverse the input string
    public String reverse(String input) {
        String reverseString = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            reverseString += input.charAt(i);
        }
        return reverseString;
    }

    //    math power function
    public int power(int base , int exponent) {
        if(exponent == 0){
            return 1;
        }
        return base * power(base, exponent-1);
    }

//    check for binary number
public boolean isBinary(String input) {
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


    //    check for octal number
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

//    check for Hexadecimal number
public boolean isHexadecimal(String input) {
    if (input == null || input.isEmpty()) {
        return false;
    }

    // Loop through each character of the input
    for (int i = 0; i < input.length(); i++) {
        char character = input.charAt(i);

        // Check if the character is not a valid hexadecimal digit
        if (!((character >= '0' && character <= '9') ||
                (character >= 'A' && character <= 'F'))) {
            return false;
        }
    }
    return true;
}


    //    check for Decimal number
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


//      Decimal to Binary conversion
    public int toBinary(String number) {
        int numberInput = toDecimal(number);
        String binaryResult = "";

        if (numberInput == 0) {
            return 0;
        }
        while (numberInput > 0) {
            int remainder = numberInput % 2;
            binaryResult += remainder;
            numberInput = numberInput / 2;
        }
        binaryResult = reverse(binaryResult);
        return Integer.parseInt(binaryResult);
    }

//        decimal to octal conversion
    public int toOctal(String number) {
        int numberInput = toDecimal(number);
        String octalResult = "";

        if (numberInput == 0) {
            return 0;
        }
        while (numberInput > 0) {
            int remainder = numberInput % 8;
            octalResult += remainder;
            numberInput = numberInput / 8;
        }
        octalResult = reverse(octalResult);
        return Integer.parseInt(octalResult);
    }

//    decimal to Hexadecimal conversion
    public String toHexadecimal(String number) {
        int numberInput = toDecimal(number);
        String HexadecimalResult = "";

        if (numberInput == 0) {
            return "0";
        }
        while (numberInput > 0) {
            int remainder = numberInput % 16;
            if (remainder < 9) {
                HexadecimalResult += remainder;
            } else {
                HexadecimalResult += Hexadecimal[remainder];
            }
            numberInput = numberInput / 16;
        }
        HexadecimalResult = reverse(HexadecimalResult);
        return HexadecimalResult;
    }

    public static int getIndexOfElement(String[] array, char value) {
        String character = Character.toString(value);
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(character)) {
                return i;
            }
        }
        return -1;
    }


    //  decimal to decimal conversion
    public int toDecimal(String number) {
        int lengthString = number.length();
        int decimalResult = 0;

        for (int i = 0; i < lengthString; i++) {
            char character = number.charAt(i);
            if (!Character.isDigit(character)) {
                int index = getIndexOfElement(Hexadecimal,character);
                decimalResult += index * power(base, lengthString - 1 - i);
            } else {
                int numericValue = Character.getNumericValue(character);
                decimalResult += numericValue * power(base, lengthString - 1 - i);
            }
        }
        return decimalResult;
    }

//    ask user to change from the base
    int base;
    public void fromBase(String number) {
        while (true) {
            try {
                System.out.print("Choose from base:\n1: Binary\n2: Octal\n3: Decimal\n4: Hexadecimal\nChoice: ");
                String Choice = scan.nextLine();
                isValidChoice(Choice);
                int fromChoice = Integer.parseInt(Choice);
                switch (fromChoice) {
                    case 1:
                        if (!isBinary(number)) {
                            System.out.println("The number is not a valid binary number");
                        } else {
                            System.out.println("The number is a valid binary number");
                            base = 2;
                            return;
                        }
                        break;

                    case 2:
                        if (!isOctal(number)) {
                            System.out.println("The number is not a valid octal number");
                        } else {
                            System.out.println("The number is a valid octal number");
                            base = 8;
                            return;
                        }
                        break;

                    case 3:
                        if (!isDecimal(number)) {
                            System.out.println("The number is not a valid decimal number");
                        } else {
                            System.out.println("The number is a valid decimal number");
                            base = 10;
                            return;
                        }
                        break;

                    case 4:
                        if (!isHexadecimal(number)) {
                            System.out.println("The number is not a valid hexadecimal number");
                        } else {
                            System.out.println("The number is a valid hexadecimal number");
                            base = 16;
                            return;
                        }
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

//    ask the user in which base user want to change
//    argument String number, toChoice,
//    return the desired base value to the user from the another base
    public void toBase(String number) {
        System.out.print("Choose to base:\n1:- Binary\n2:- Octal\n3:- Decimal\n4:- Hexadecimal\nChoice: ");
        int toChoice = scan.nextInt();
        switch (toChoice) {
            case 1:
                System.out.println("The number " + number + " in binary is: " + toBinary(number));
                return;
            case 2:
                System.out.println("The number " + number + " in Octal is: " +toOctal(number));
                return;
            case 3:
                System.out.println("The number " + number + " in Decimal is: " + toDecimal(number));
                return;
            case 4:
                System.out.println("The number " + number + " in Hexadecimal is: " + toHexadecimal(number));
                return;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public int addition(String first , String second) {
        base = 10;
        int firstNumber = toDecimal(first);
        int secondNumber = toDecimal(second);
        return firstNumber + secondNumber;
    }

    public int onesCompliment(int value){
        String stringValue = String.valueOf(value);
        base = 10;
        String resultDecimal = String.valueOf(toDecimal(stringValue));
        String outputValue = "";
        base = 2;
        int resultBinary = toBinary(resultDecimal);
        String stringBinary = String.valueOf(resultBinary);
        for (int i = 0;i<stringBinary.length();i++){
            char currentCharacter = stringBinary.charAt(i);
            if ( currentCharacter == '1'){
                outputValue += '0';
            } else {
                outputValue += '1';
            }
        }
        return twosCompliment(Integer.parseInt(outputValue));
    }

    public int twosCompliment(int input){
        base = 10;
        String compliment = Integer.toString(input);
        return toDecimal(compliment)+1;
    }


    public int subtraction(String first , String second) {
        base = 10;
        int firstNumber = toDecimal(first);
        int secondNumber = toDecimal(second);
        int subtractionResult =  firstNumber - secondNumber;
        if (subtractionResult >= 0) {
            return subtractionResult;
        } else {
            subtractionResult = -subtractionResult;
            return onesCompliment(subtractionResult);
        }
    }



    public void convert(Scanner scanner) {
        while (true) {
            System.out.print("Enter number: ");
            String inputNumber = scanner.nextLine();
            isValidInput(inputNumber);
            if (!isValidInput(inputNumber)) {
                System.out.println("That is not a valid number");
            } else {
                fromBase(inputNumber);
                toBase(inputNumber);
                 break;
            }
        }
    }

    public void operation(Scanner scanner) {
        String firstNumber = "";
        String secondNumber = "";
        int operationChoice = -1;
        int choiceUser = -1;

        while (true) {
            System.out.print("Enter first number: ");
            firstNumber = scanner.nextLine();
            if (isValidInput(firstNumber)) {
                break; // Exit loop if input is valid
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        while (true) {
            System.out.print("Enter second number: ");
            secondNumber = scanner.nextLine();
            if (isValidInput(secondNumber)) {
                break; // Exit loop if input is valid
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        if (isBinary(firstNumber) || isBinary(secondNumber)) {
            firstNumber = String.valueOf(toDecimal(firstNumber));
            secondNumber = String.valueOf(toDecimal(secondNumber));
        }

        while (operationChoice < 1 || operationChoice > 2) {
            System.out.print("Enter operation you want to perform: \n1: Addition\n2: Subtraction\nChoice: ");
            String operation = scanner.nextLine();
            try {
                isValidChoice(operation);
                operationChoice = Integer.parseInt(operation);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }

        int result = 0;
        String operationString = "";

        switch (operationChoice) {
            case 1:
                result = addition(firstNumber, secondNumber);
                operationString = "Addition";
                break;
            case 2:
                result = subtraction(firstNumber, secondNumber);
                operationString = "Subtraction";
                break;
            default:
                System.out.println("Invalid choice!!");
                return;
        }

        while (choiceUser < 1 || choiceUser > 4) {
            System.out.print("Choose output base:\n1: Binary\n2: Octal\n3: Decimal\n4: Hexadecimal\nChoice: ");
            String resultChoice = scanner.nextLine();
            try {
                isValidChoice(resultChoice);
                choiceUser = Integer.parseInt(resultChoice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }

        String resultString = String.valueOf(result);
        switch (choiceUser) {
            case 1:
                System.out.println("The result of " + operationString + " in binary is: " + toBinary(resultString));
                break;
            case 2:
                System.out.println("The result of " + operationString + " in Octal is: " + toOctal(resultString));
                break;
            case 3:
                System.out.println("The result of " + operationString + " in Decimal is: " + resultString);
                break;
            case 4:
                System.out.println("The result of " + operationString + " in Hexadecimal is: " + toHexadecimal(resultString));
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }






    public static void main(String[] args) {
        numberSystemConversion conversion = new numberSystemConversion();
        String userChoice;
        do {
            System.out.println("1:- If you want to perform operation (Addition,Subtraction)\n2:- If you want to do the conversion from one number System to other\nChoice: ");
            String chooseOption = scan.nextLine();
            isValidChoice(chooseOption);
            int Option = Integer.parseInt(chooseOption);
            switch (Option) {
                case 1:
                    conversion.operation(scan);
                    break;
                case 2:
                    conversion.convert(scan);
                    break;
            }
            System.out.print("To continue press any key (Press 'exit' to exit): ");
            userChoice = scan.nextLine();
        } while (!userChoice.equalsIgnoreCase("exit"));
    }
}
