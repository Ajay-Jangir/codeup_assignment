/***
 * Below program performs the following 5 tasks:-

 * Task 1 Valid Parentheses Combination Generator
 * Task 2 Digit Sum Loop(String)
 * Task 3 Consecutive Number Summer
 * Task 4 Caesar Cipher with Shift Variability
 * Task 5 Encoding Challenge with ASCII Conversion

 Date of Creation :- 10-9-2024
 Owner :- Ajay Kumar

 */

import java.util.InputMismatchException;
import java.util.Scanner;
public class Week2_Assignment1 {
    static Constant constant = new Constant();


//  function 1 Valid Parentheses Combination Generator

    public static void CombinationGenerator(String input) {
        String[] outputArray = new String[100];
        int count = 0;

        if (input.isEmpty() || input.length() == 1 || input.equals("''") ) {
            System.out.print(input);
        } else {
            for (int i = 0; i < input.length(); i++) {
                outputArray[count] = String.valueOf(input.charAt(i));
                count++;
            }

            for (int i = 0; i < input.length(); i++) {
                for (int j = 0; j < input.length(); j++) {

                    if (i != j) {
                        String combination = String.valueOf(input.charAt(i)) + String.valueOf(input.charAt(j));
                        outputArray[count] = combination;
                        count++;
                    }

                }
            }
            boolean found = false;
            for (int k = 0; k < count; k++) {
                if (outputArray[k].equals(input)) {
                    found = true;
                    break;
                }
            }
            if (!found){
            outputArray[count] = String.valueOf(input);
            count++;
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

    public static int digitSum(String input) {
        int sum = 0;
        if (input.isEmpty()) {
            return 0;
        } else if (input.length() == 1 ) {
            return Integer.parseInt(input);
        } else {
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

//    function 3 Consecutive Number Summer

    public static void NumberSummer(Scanner Input)
    {
        try{
            System.out.print(constant.INTEGER);
            int input = Input.nextInt();
            for (int i = 1; i < input; i++) {
                int Sum = 0;
                int current = i;
                while (Sum < input) {
                    Sum += current;
                    current++;
                    if (Sum == input) {
                        for (int j = i; j < current; j++) {
                            System.out.print(j);

                            if (j < current - 1) {
                                System.out.print(" + ");
                            }
                        }
                        System.out.println();
                    }

                }
            }

        }catch (InputMismatchException e) {
            System.out.println(constant.INVALID_INPUT);
        }
    }

    //    function 4 Caesar Cipher with Shift Variability

    public static String Encryption(String input, int[] newPattern) {
        if (input == null || newPattern == null || newPattern.length == 0) {
            return "";
        }

        String output = "";
        int patternLength = newPattern.length;
        int patternIndex = 0;

        for (int i = 0; i < input.length(); i++) {
            char currentCharacter = input.charAt(i);

            if (Character.isLetter(currentCharacter) || Character.isDigit(currentCharacter)) {

                int shift = newPattern[patternIndex % patternLength];

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


//    function 5 Encoding Challenge with ASCII Conversion

public static void encodingWithASCII(Scanner Input) {
    try {
            System.out.print(constant.LENGTH_ARRAY);
            int length = Input.nextInt();
            Input.nextLine();

            String[] arr = new String[length];
            System.out.println(constant.ENTER_STRING);
            for (int i = 0; i < length; i++) {
                arr[i] = Input.nextLine();
            }

            System.out.println(constant.ENTER_SERIES);
            String Series = Input.nextLine();


            for (int i = 0; i < length - 1; i++) {
                for (int j = 0; j < length - i - 1; j++) {
                    if (arr[j].compareTo(arr[j + 1]) > 0) {
                        String temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
            String[] temp = new String[Series.length()];
            for (int j = 0; j < Series.length(); j++) {
                int index = Character.getNumericValue(Series.charAt(j));
                if (index >= 0 && index < arr.length) {
                    temp[j] = arr[index];
                } else {
                    System.out.println(constant.INVALID_INDEX);
                    return;
                }
            }
            for (int i = temp.length - 1; i >= 0; i--) {

                char currentChar = temp[i].charAt(0);
                System.out.print((int) currentChar + " ");

            }
            System.out.println();
        }catch (InputMismatchException e) {
        System.out.println(constant.INVALID_INPUT);
    }
}


    public static void main(String[] args) {

        while (true) {
            Scanner Input = new Scanner(System.in);
            Constant.Option();
            int chooseCase = Input.nextInt();
            Input.nextLine();
            switch (chooseCase) {
                case 1:
                    do {
                        System.out.print(constant.ENTER_STRING);
                        String stringInput = Input.nextLine();
                        CombinationGenerator(stringInput);
                        System.out.print(constant.PROMPT_CONTINUE);
                    } while (Input.nextLine().equalsIgnoreCase("yes"));
                    break;


                case 2:
                    while (true) {
                        System.out.print(constant.ENTER_STRING_OR_EXIT);
                        String userInput = Input.nextLine();

                        if (userInput.equalsIgnoreCase("exit")) {
                            break;
                        }
                        try {
                            int intInput = Integer.parseInt(userInput);
                            if(intInput < 0)
                            {
                                System.out.println(constant.INVALID_INPUT);
                                continue;
                            }
                            int output = digitSum(Integer.toString(intInput));
                            System.out.println(constant.RESULT + output);
                        } catch (NumberFormatException e) {
                            System.out.println(constant.INVALID_INPUT);
                        }
                    }
                    break;

                case 3:
                    do {
                        NumberSummer(Input);
                        Input.nextLine();
                        System.out.print(constant.PROMPT_CONTINUE);
                    } while (Input.nextLine().equalsIgnoreCase("yes"));
                    break;


                case 4:
                    do {
                        System.out.print(constant.ENTER_STRING);
                        String stringInput = Input.nextLine();
                        System.out.print(constant.PATTERN_LENGTH);
                        int patternLength = Input.nextInt();
                        System.out.println(constant.PATTERN);
                        int[] pattern = new int[patternLength];
                        Input.nextLine();
                        for (int i = 0; i < patternLength; i++) {
                            pattern[i] = Input.nextInt();
                        }
                        String Output = Encryption(stringInput, pattern);
                        System.out.println(constant.CODED_STRING + Output);
                        Input.nextLine();
                        System.out.print(constant.PROMPT_CONTINUE);
                    } while (Input.nextLine().equalsIgnoreCase("yes"));
                    break;


                case 5:
                    do {
                        encodingWithASCII(Input);
                    System.out.print(constant.PROMPT_CONTINUE);
                    } while (Input.nextLine().equalsIgnoreCase("yes"));
                    break;

                default:
                    break;
            }
        if (chooseCase == 6)
        {
            System.out.println(constant.EXIT);
            break;
        }

        }
    }
}
