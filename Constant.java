public class Constant {

//    main class
    public String PROMPT_CONTINUE = "Continue again? Type 'YES' to CONTINUE else press any key to exit: ";
    public String ENTER_STRING = "Enter the String: ";
    public String EXIT = "Exiting...";

// function 1 Valid Parentheses Combination Generator


//   function 2 Digit Sum Loop(String)

    public String ENTER_STRING_OR_EXIT = "Enter the Integer String (or type 'exit' to quit): ";
    public String RESULT = "Sum of digits: ";
    public String INVALID_INPUT = "Invalid input. Please enter a valid integer.";

//    function 3 Consecutive Number Summer

    public String INTEGER = "Enter a Integer: ";

//    function 4 Caesar Cipher with Shift Variability

    public String PATTERN_LENGTH = "Enter the Pattern Length: ";
    public String PATTERN = "Enter the Pattern Array: ";
    public String CODED_STRING = "Your coded String is: ";

//    function 5 Encoding Challenge with ASCII Conversion

    public String LENGTH_ARRAY = "Enter the length of strings array: ";
    public String ENTER_SERIES = "Enter the series: ";
    public String INVALID_INDEX = "Invalid index in the series.";


    public static void Option(){
        System.out.println(" Press 1 for Valid Parentheses Combination Generator " +
                            "\n Press 2 for Digit Sum Loop(String) " +
                            "\n Press 3 for Consecutive Number Summer " +
                            "\n Press 4 for Caesar Cipher with Shift Variability " +
                            "\n Press 5 for Encoding Challenge with ASCII Conversion " +
                            "\n Press 6 for Exit");
        System.out.print("Choose the option from the above (Enter no. between 1-6): ");
    }
}
