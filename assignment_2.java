/***
 * Case 1: Append String Operation
 * In this case, the program allows the user to append a new string to an
 * existing string. The process continues iteratively until the user chooses to
 * stop. The program takes user input for the initial string, then appends
 * additional strings, updating and displaying the final result after each
 * append operation.
 *
 *
 * Case 2: Count Word Operation
 * This case counts the number of words in a given string input. The program
 * trims any leading or trailing spaces, counts the words based on spaces
 * between them, and outputs the total word count. The user can continue
 * counting words for different strings until they choose to stop.
 *
 *
 * Case 3: Replace String Operation
 * In this case, the program allows the user to replace a specified substring
 * with a new substring within a given string. The program takes the original
 * string, the substring to be replaced, and the new substring as inputs, and
 * performs the replacement operation. The user can perform multiple replacement
 * operations as needed.
 *
 *
 * Case 4: Palindrome Verification Operation
 * This case checks whether a given string is a palindrome. The program reverses
 * the string and compares it with the original. If both are equal, the string
 * is a palindrome; otherwise, it is not. The user can continue verifying
 * palindromes for multiple strings.
 *
 *
 * Case 5: Splice String Operation
 * In this case, the program allows the user to splice (remove) a portion of a
 * string based on the provided start index and length. The program returns the
 * modified string with the specified portion removed. The user can perform
 * multiple splicing operations on different strings.
 *
 *
 * Case 6: Split String Operation
 * This case splits a given string into individual words based on spaces and
 * stores them in an array. The program then prints each word, separated by
 * commas. The user can split multiple strings and observe the output.
 *
 *
 * Case 7: Max Repeat Operation
 * In this case, the program identifies the character that appears the most
 * frequently in a given string. It counts the occurrences of each character and
 * outputs the one with the highest count. If there is a tie, multiple
 * characters are shown. The user can perform this operation on multiple
 * strings.
 *
 *
 * Case 8: Sorting Operation
 * This case sorts the characters in a given string in ascending order, starting
 * with uppercase letters, followed by lowercase letters, and finally digits.
 * The program outputs the sorted string, and the user can sort multiple strings
 * as desired.
 *
 *
 * Case 9: Shift Operation
 * In this case, the program shifts the characters in a string by a specified
 * number of positions. The user provides the string and the shift value, and
 * the program shifts the characters accordingly, returning the modified string.
 * The user can perform this operation on multiple strings.
 *
 *
 * Case 10: Reverse String Operation
 * This case reverses the characters in a given string. The program takes the
 * user's input, reverses it, and displays the reversed string. The user can
 * reverse multiple strings in this case.
 */


import java.util.Scanner;

public class assignment_2 {
    static HardCoded_words code_word = new HardCoded_words();



//    function 1 to append the string;

public static String AppendString(String CurrentStr, String GetString) {
    if (CurrentStr == null) {
        CurrentStr = "";
    }
    if (GetString == null) {
        GetString = "";
    }
    return CurrentStr + " " + GetString;
}



//    function 2 to count words

    public static int CountWords(String Words) {
        String word = Words.trim();
        if (word.isEmpty()) {
            return 0;
        }

        int count = 0;
        boolean inWord = false;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != ' ') {
                if (!inWord) {
                    count++;
                    inWord = true;
                }
            } else {
                inWord = false;
            }
        }
        return count;
    }



//    function 3 to replace string

    public static String ReplaceChar(String original,String oldStr,String newStr)
    {
                String result = "";
                int length = original.length();
                int oldLength = oldStr.length();

                for (int i = 0; i < length; i++) {
                    if (i <= length - oldLength && original.substring(i, i + oldLength).equals(oldStr)) {
                        result += newStr;
                        i += oldLength - 1;
                    } else {
                        result += original.charAt(i);
                    }
                }
                return result;
        }



//    function 4 for palindrome

    public static boolean CheckPalindrome(String Words){
        String CheckPalindrome = Reverse(Words);
        return Words.equals(CheckPalindrome);
    }



//    function 5 to splice the string

    public static String SpliceString(String Words, int Index, int Length) {
        if (Words == null || Index < 0 || Length < 0) {
            return Words;
        }

        int LengthOfWords = Words.length();
        if (Index >= LengthOfWords) {
            return Words;
        }

        String NewString = "";

        for (int i = 0; i < LengthOfWords; i++) {
            if (i >= Index && i < Index + Length) {
                continue;
            } else {
                NewString += Words.charAt(i);
            }
        }
        return NewString;
    }



//    function 6 for the split String

    public static void SplitString(String words) {
        words = words.trim();
        int length = words.length(), count = 0;
        String splitString = "";
        String[] storeString = new String[length];

        for (int i = 0; i < length; i++) {
            if (words.charAt(i) != ' ') {
                splitString += words.charAt(i);
            } else {
                if (!splitString.isEmpty()) {
                    storeString[count] = splitString;
                    splitString = "";
                    count++;
                }
            }
        }

        if (!splitString.isEmpty()) {
            storeString[count] = splitString;
            count++;
        }

        for (int i = 0; i < count; i++) {
            System.out.print(storeString[i]);
            if (i < count - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }



//    function 7 for maxRepeat

    public static void MaxRepeat(String Words){
        int length = Words.length();
        int count,MaxCount = 0;
        String Repeated = "";
        for (int i = 0; i < length; i++) {
            count =0;
            for (int j = 0; j < length; j++) {
                if (Words.charAt(i) == Words.charAt(j)) {
                    count++;
                }
            }
        if(MaxCount < count)
        {
            MaxCount = count;
            Repeated = String.valueOf(Words.charAt(i));
        }
        else if(MaxCount == count && !Repeated.contains(String.valueOf(Words.charAt(i))))
        {
            Repeated += Words.charAt(i);
        }
        }
        System.out.println(Repeated + " is the max repeated word " + MaxCount + " times");
    }



//    Function 8 to sort the string

    public static String SortedString(String words) {
        char[] chars = words.toCharArray();
        int n = chars.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (chars[j] > chars[j + 1]) {
                    char temp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = temp;
                }
            }
        }
        return new String(chars);
    }



// function 9 for the shifting is

    public static String ShiftedWord(String Words, int Value) {
        if (Words == null || Words.isEmpty() || Value < 0) {
            return Words;
        }

        int Length = Words.length();
        if (Value >= Length) {
            return Words;
        }

        String ShiftedWord = "";
        String NewString = "";

        for (int i = 0; i <= Value; i++) {
            ShiftedWord += Words.charAt(i);
        }

        for (int i = Value + 1; i < Length; i++) {
            NewString += Words.charAt(i);
        }

        NewString += ShiftedWord;
        return NewString;
    }



//  function 10 to reverse

    public static String Reverse(String Words){
        int length = Words.length();

        String NewWord = "";
        for (int i = length - 1; i >= 0; i--) {
           NewWord+=Words.charAt(i);
        }
        return NewWord;
    }



//    MAIN FUNCION

    public static void main(String[] args) {
        Scanner Input = new Scanner(System.in);
        do {
            try {
                code_word.option();
                int Num = -1;
                boolean validInput = false;
                while (!validInput) {
                    try {
                        Num = Input.nextInt();
                        Input.nextLine();
                        validInput = true;
                    } catch (Exception e) {
                        System.out.println(code_word.INVALID_OPTION);
                        Input.next();
                    }
                }

                switch (Num) {
                    case 1:
                        do {
                            System.out.print(code_word.Append_ENTER_STRING);
                            String CurrentString = Input.next();
                            do {
                                System.out.print(code_word.Append_ENTER_SUBSTRING);
                                String NewString = Input.next();
                                CurrentString = AppendString(CurrentString, NewString);
                                System.out.println(code_word.Append_UPDATED_STRING + CurrentString);
                                System.out.println(code_word.Append_CONTINUE_YES_NO);
                            } while (Input.nextLine().equals("YES".toLowerCase()));
                            System.out.println(code_word.Append_AGAIN_YES_NO);
                        } while (Input.nextLine().equals("YES".toLowerCase().toUpperCase()));
                        break;

                    case 2:
                        do {
                            System.out.print(code_word.CountWord_ENTER_STRING);
                            String String = Input.nextLine();
                            System.out.println(code_word.CountWord_WORD_COUNT + CountWords(String));
                            System.out.print(code_word.CountWord_CONTINUE_YES_NO);
                        } while (Input.nextLine().equals("YES".toLowerCase().toUpperCase()));
                        break;

                    case 3:
                        do {
                            System.out.println(code_word.Replace_ENTER_STRING);
                            String FromUser = Input.nextLine();
                            System.out.println(code_word.Replace_ENTER_NEW_STRING);
                            String OldString = Input.nextLine();
                            System.out.println(code_word.Replace_ENTER_NEW_STRING_TO_REPLACE);
                            String NewString = Input.nextLine();
                            String Replace = ReplaceChar(FromUser, OldString, NewString);
                            System.out.println(Replace);
                            System.out.print(code_word.Replace_CONTINUE_YES_NO);
                        } while (Input.nextLine().equals("YES".toLowerCase().toUpperCase()));
                        break;

                    case 4:
                        do {
                            System.out.print(code_word.Palindrome_ENTER_STRING);
                            String PalindromeString = Input.nextLine();
                            boolean Check = CheckPalindrome(PalindromeString);
                            System.out.println(Check);
                            System.out.print(code_word.Palindrome_CONTINUE_YES_NO);
                        } while (Input.nextLine().equals("YES".toLowerCase().toUpperCase()));
                        break;

                    case 5:
                        do {
                            System.out.print(code_word.Splice_ENTER_STRING);
                            String SplicedString = Input.nextLine();
                            while (true) {
                                if (!SplicedString.contains(" ") && !SplicedString.isEmpty()) {
                                    break;
                                } else {
                                    System.out.println(code_word.INVALID_INPUT);
                                    System.out.println(code_word.Splice_ENTER_STRING);
                                    SplicedString = Input.nextLine();
                                }
                            }
                            System.out.print(code_word.Splice_ENTER_INDEX_START);
                            int StartFrom = Input.nextInt();
                            System.out.print(code_word.Splice_ENTER_LENGTH);
                            int LengthTo = Input.nextInt();
                            Input.nextLine();
                            String SpliceString = SpliceString(SplicedString, StartFrom, LengthTo);
                            System.out.println(SpliceString);
                            System.out.print(code_word.Splice_CONTINUE_YES_NO);
                        } while (Input.nextLine().equals("YES".toLowerCase().toUpperCase()));
                        break;

                    case 6:
                        do {
                            System.out.print(code_word.Split_ENTER_STRING);
                            String StringSplit = Input.nextLine();
                            SplitString(StringSplit);
                            System.out.print(code_word.Split_CONTINUE_YES_NO);
                        } while (Input.nextLine().equals("YES".toLowerCase().toUpperCase()));
                        break;

                    case 7:
                        do {
                            System.out.println(code_word.RepeatedWord_ENTER_STRING);
                            String UserString = Input.nextLine();
                            MaxRepeat(UserString);
                            System.out.print(code_word.RepeatedWord_CONTINUE_YES_NO);
                        } while (Input.nextLine().equals("YES".toLowerCase().toUpperCase()));
                        break;

                    case 8:
                        do {
                            System.out.print(code_word.Sort_ENTER_STRING);
                            String SortString = Input.nextLine();
                            String SortedText = SortedString(SortString);
                            System.out.println(code_word.Sort_SORTED_STRING + SortedText);
                            System.out.print(code_word.Sort_CONTINUE_YES_NO);
                        } while (Input.nextLine().equals("YES".toLowerCase().toUpperCase()));
                        break;

                    case 9:
                        do {
                            System.out.print(code_word.Shift_ENTER_STRING);
                            String ShiftString = Input.nextLine();
                            while (true) {

                                if (!ShiftString.contains(" ") && !ShiftString.isEmpty()) {
                                    break;
                                } else {
                                    System.out.println(code_word.INVALID_INPUT);
                                    System.out.println(code_word.Shift_ENTER_STRING);
                                    ShiftString = Input.nextLine();
                                }
                            }
                            System.out.print(code_word.Shift_ENTER_INDEX);
                            int ShiftValue = Input.nextInt();
                            Input.nextLine();
                            String ShiftedWords = ShiftedWord(ShiftString, ShiftValue);
                            System.out.println(ShiftedWords);
                            System.out.print(code_word.Shift_CONTINUE_YES_NO);
                        } while (Input.nextLine().equals("YES".toLowerCase().toUpperCase()));
                        break;

                    case 10:
                        do {
                            System.out.print(code_word.Reverse_ENTER_STRING);
                            String ReverseString = Input.nextLine();
                            String ReversedString = Reverse(ReverseString);
                            System.out.println(code_word.Reverse_REVERSED_STRING + ReversedString);
                            System.out.print(code_word.Reverse_CONTINUE_YES_NO);
                        } while (Input.nextLine().equals("YES".toLowerCase().toUpperCase()));
                        break;

                    default:
                        System.out.println(code_word.INVALID_OPTION);
                }
                System.out.println(code_word.CONTINUE_YES_NO);
            } catch (Exception e) {
                System.out.println(code_word.ERROR_OCCURRED + e.getMessage());
                Input.nextLine();
            }
        } while (Input.next().equals("YES".toLowerCase().toUpperCase()));
        Input.close();
    }
}
