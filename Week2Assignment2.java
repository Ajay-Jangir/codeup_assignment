import java.util.Scanner;

public class Week2Assignment2 {
    static int consonantCounter = 0;

    //    Option
public static void Option() {
    System.out.println(" Press 1 for CountPalindromes " +
            "\n Press 2 for NthFibonacci " +
            "\n Press 3 for SnakeToCamel " +
            "\n Press 4 for CountConsonants " +
            "\n Press 5 for BinaryToDecimal " +
            "\n Press 6 for Exit");
    System.out.print("Choose the option from the above (Enter no. between 1-6): ");
    }

//    function 4 count consonants
    public static void countConsonants(String input){
    countConsonants(input,0);
    }

    public static void countConsonants(String input, int index) {
        if (index >= input.length()) {
            System.out.println("Total number of consonants: " + consonantCounter);
            return;
        }
        char currentCharacter = Character.toLowerCase(input.charAt(index));
        if (currentCharacter >= 'a' && currentCharacter <= 'z' && currentCharacter != 'a' && currentCharacter != 'e' && currentCharacter != 'i' && currentCharacter != 'o' && currentCharacter != 'u' ) {
            consonantCounter +=1;
        }
            countConsonants(input, index + 1);
    }

//    function 5 binary to decimal
//    public static void binaryToDecimal(long binary){
//        binaryToDecimal(long binary , 0l);
//    }
//
//    public static void binaryToDecimal(long binary, long index) {
//        if (index>= binary.length)


    }




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Option();
        int Choice = scanner.nextInt();
        scanner.nextLine();
        switch (Choice){
            case 4:
                do {
                    System.out.print("Enter the String to Count consonants: ");
                    String input = scanner.nextLine();
                    countConsonants(input, 0);
                    System.out.print("To 'Exit' enter 'quit' else press any key to continue: ");
                }while (!(scanner.nextLine()).equals("quit"));
                break;
        }

    }
}
