import javax.xml.transform.Result;
import java.util.Scanner;
import java.util.StringJoiner;

public class assignment_2 {
    static HardCoded_words code_word = new HardCoded_words();

//    function in the switch case
    public static void option()
    {
        System.out.println("Press 1  to  append string operation");
        System.out.println("Press 2  to  countWord operation");
        System.out.println("Press 3  to  replace string operation");
        System.out.println("Press 4  to  verify isPalindrome operation");
        System.out.println("Press 5  for splice the string operation");
        System.out.println("Press 6  for split operation");
        System.out.println("Press 7  for maxRepeat operation");
        System.out.println("Press 8  for sorting operation");
        System.out.println("Press 9  for shift operation");
        System.out.println("Press 10 for reverse string operation");
        System.out.print("Enter an option: ");
    }


//    function 1 to append the string;
    public static String append_string(String Current_str,String get_string)
    {
        return Current_str+" "+get_string;
    }

//    function 2 to count words
    public static int count_words(String Words){
        String word = Words.trim();

        int count = 1;
        if (word.isEmpty()) {
            return 0;
        }

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ' ') {
                count++;
            }
        }
        return count;
    }

//    function 3 to replace string
    public static void Replace_char(String Input_user,String Old_input,String New_input)
    {
        int length = Old_input.length();

    }


//  function 4 to reverse
    public static String Reverse(String Words){
        int length = Words.length();

        String New_word = "";
        for (int i = length - 1; i >= 0; i--) {
           New_word+=Words.charAt(i);
        }
        return New_word;
    }

//    function 5 for pallindrome
    public static String CheckPalindrome(String Words){
        String CheckPalindrome = Reverse(Words);
        if(Words.equals(CheckPalindrome))
            return "Yes the given input is Pallindrome.";
        else
            return  "No the given input is not Pallindrome.";
    }

//    function 6 for maxRepeat
    public static void MaxRepeat(String Words){
        int length = Words.length();
        int count = 0,MaxCount = 0;
        String Repeated = "";
        for (int i = 0; i < length; i++) {
            count =0;
            for (int j = i; j < length; j++) {
                if (Words.charAt(i) == Words.charAt(j)) {
                    count++;
                }
                    Repeated = String.valueOf(Words.charAt(i));
            }
        if(MaxCount<=count)
        {
            MaxCount=count;
            Repeated += String.valueOf(Words.charAt(i));
        }
        }
        System.out.print(Repeated + " is the max repeated word " + MaxCount + " times");
    }







    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        option();
        int num = input.nextInt();
        input.nextLine();
        switch (num) {
            case 1:
                System.out.print(code_word.Append_ENTER_STRING);
                String current_string = input.next();
                do {
                    System.out.print(code_word.Append_ENTER_SUBSTRING);
                    String new_string = input.next();
                    current_string = append_string(current_string,new_string);
                    System.out.println(code_word.Append_UPDATED_STRING + current_string);
                    System.out.println(code_word.Append_PROMPT_YES_NO);
                } while (input.next().equals("Y".toLowerCase()));
                break;


            case 2:
                System.out.print("Enter the String for counting words: ");
                String string = input.nextLine();
                System.out.print("Word Count is: " + count_words(string));
                break;


            case 3:
                System.out.print("Enter the String: ");
                String From_user = input.nextLine();
                System.out.print("Enter the String or Char you want to replace: ");
                String Old_string = input.nextLine();
                System.out.print("Enter the String or Char you want to replace: ");
                String New_string = input.nextLine();
                Replace_char(From_user,Old_string,New_string);
                break;


            case 4:
                System.out.print("Enter the String: ");
                String Reverse_string = input.next();
                String Reversed_string = Reverse(Reverse_string);
                System.out.print("Reversed String is: " + Reversed_string);
                break;


            case 5:
                System.out.print("Enter the String to check Palindrome: ");
                String Palindrome_string = input.next();
                String check = CheckPalindrome(Palindrome_string);
                System.out.print(check);
                break;


            case 6:
                System.out.print("Enter the String: ");
                String User_string = input.next();
                MaxRepeat(User_string);
                break;




        }
    }
}
