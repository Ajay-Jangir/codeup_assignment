
public class HardCoded_words {

//   MAIN BRANCH REPEAT

    public String CONTINUE_YES_NO = "If you want to do more operation then press 'yes' else any key to exit: ";
    public String ERROR_OCCURRED = "An error occurred: ";
    public String INVALID_OPTION = "Invalid option. Please enter a number between 1-10.";
    public String INVALID_INPUT = "Invalid input. Please enter only a single word with no spaces.";

//    Append Method 1:

    public String Append_ENTER_STRING = "Enter a string: ";
    public String Append_ENTER_SUBSTRING = "Enter the new String to append: ";
    public String Append_UPDATED_STRING = "Updated String is: ";
    public String Append_CONTINUE_YES_NO = "If you want to append more then press 'yes' else any key to exit: ";
    public String Append_AGAIN_YES_NO = "To again Start Append press 'yes' else any key to exit: ";

//    CountWord 2:

    public String CountWord_ENTER_STRING = "Enter the String for counting words: ";
    public String CountWord_WORD_COUNT = "Word Count is: ";
    public String CountWord_CONTINUE_YES_NO = "To again Start CountWord press 'yes' else any key to exit: ";

//    Replace 3:

    public String Replace_ENTER_STRING = "Enter a string to replace: ";
    public String Replace_ENTER_NEW_STRING = "Enter the String or Char you want to replace: ";
    public String Replace_ENTER_NEW_STRING_TO_REPLACE = "Enter the String or Char you want to replace with: ";
    public String Replace_CONTINUE_YES_NO = "To again Start Replace press 'yes' else any key to exit: ";

//  Palindrome 4:

    public String Palindrome_ENTER_STRING = "Enter the String to check Palindrome: ";
    public String Palindrome_CONTINUE_YES_NO = "To again check isPalindrome press 'yes' else any key to exit: ";

//  Splice 5:

    public String Splice_ENTER_STRING = "Enter a string to splice: ";
    public String Splice_ENTER_INDEX_START = "Enter the index to start: ";
    public String Splice_ENTER_LENGTH = "Enter the length to splice: ";
    public String Splice_CONTINUE_YES_NO = "To again Start Splice press 'yes' else any key to exit: ";

//    Split 6:

    public String Split_ENTER_STRING = "Enter the String to Split: ";
    public String Split_CONTINUE_YES_NO = "To again Start Split press 'yes' else any key to exit: ";

//    RepeatedWord 7:

    public String RepeatedWord_ENTER_STRING = "Enter the Word for counting the maximum repeated word: ";
    public String RepeatedWord_CONTINUE_YES_NO = "To again Start RepeatedWord press 'yes' else any key to exit: ";

//    Sort 8:

    public String Sort_ENTER_STRING = "Enter the String to sort: ";
    public String Sort_SORTED_STRING = "Sorted_Text is: ";
    public String Sort_CONTINUE_YES_NO = "To again Start Sort press 'yes' else any key to exit: ";

//  shift 9:

    public String Shift_ENTER_STRING = "Enter the String for shifting: ";
    public String Shift_ENTER_INDEX = "Enter the index to shift the string from: ";
    public String Shift_CONTINUE_YES_NO = "To again Start Shift press 'yes' else any key to exit: ";

//    reverse 10:

    public String Reverse_ENTER_STRING = "Enter the String to reverse: ";
    public String Reverse_REVERSED_STRING = "Reversed String is: ";
    public String Reverse_CONTINUE_YES_NO = "To again Start Reverse press 'yes' else any key to exit: ";


    public static void option()
    {
        System.out.print("Press 1  to  Append                        ");
        System.out.print("Press 2  to  countWord                         ");
        System.out.println("Press 3  to  Replace  ");
        System.out.print("Press 4  to  verify isPalindrome           ");
        System.out.print("Press 5  for Splice                            ");
        System.out.println("Press 6  for Split  ");
        System.out.print("Press 7  for maxRepeat                     ");
        System.out.print("Press 8  for Sort                              ");
        System.out.println("Press 9  for Shift  ");
        System.out.println("Press 10 for Reverse");
        System.out.print("Enter an number according to choice: ");
    }
}



