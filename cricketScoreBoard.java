/***
 * this program is counting the score and the team player all data like runsGivenBowler ,totalWicket and which team is playing and who win.
 *  methods used in the code:-
 * isUnique :- give the unique value in the team like player name can't be repeated.
 * isValidName :- check the name is valid or verify the edge cases.
 * setTeamName :- set the name of the new team.
 * setPlayerName :- set the name of the player in the team.
 * teamsSet :- allow the user to add more than one team.
 * viewTotalTeam :- print all the team in the program.
 * printTeamNames :- print all the name of the team.
 * printTeamMembers :- print the name of the player in the individual team.
 * getStartIndexForTeam :- this gives the starting index of the new team.
 * selectPlaying11Team :- choose the team from the listed team to play.
 * choosePlaying11Player :- select the player in the playing 11 of the respected team.
 * Batting :- set the batting team
 * Bowling :- set the bowling player of the team
 * printBowlerStats :- print the bowler stat in the match
 * tossResult :- get result of toss
 * wicket :- get wicket next batsman
 * printBattingStats :- print the batting stat in the match
 * Owner :- Ajay-Jangir
 * Date of Creation :- 18-9-2024
 */


import java.util.InputMismatchException;
import java.util.Scanner;


public class cricketScoreBoard {
    static int teamCounter = 0;
    static int playerCounter = 0;
    static String[] totalTeamName = new String[100];
    static String[] totalPlayerName = new String[1000];
    static int[] totalPlayerInTeam = new int[100];
    static String[] playing11teamA = new String[11];
    static String[] playing11teamB = new String[11];
    static  String teamAName;
    static String teamBName;
    static String strikerName;
    static String nonStrikerName;
    static String bowlerName;
    static String tossWinner;
    static String tossLooser;
    static private String teamChoice;
    static int Over = 0;
    final static Constant constant = new Constant();

    static Scanner scanner = new Scanner(System.in);

//    it allows the unique name of the player in the list
    public static boolean isUnique(String[] playerArray, String playerName, int startIndex, int endIndex) {
        if (playerName == null || playerName.isEmpty()) {
            return false;
        }
        String firstName;
        String lastName = "";
        int spaceIndex = playerName.indexOf(' ');

        if (spaceIndex == -1) {
            firstName = playerName;
        } else {
            firstName = playerName.substring(0, spaceIndex);
            lastName = playerName.substring(spaceIndex + 1);
        }
        for (int i = startIndex; i < endIndex; i++) {
            if (playerArray[i] != null) {
                String existingPlayerName = playerArray[i];
                String existingFirstName;
                String existingLastName = "";
                int existingSpaceIndex = existingPlayerName.indexOf(' ');
                if (existingSpaceIndex == -1) {
                    existingFirstName = existingPlayerName;
                } else {
                    existingFirstName = existingPlayerName.substring(0, existingSpaceIndex);
                    existingLastName = existingPlayerName.substring(existingSpaceIndex + 1);
                }
                if (firstName.equalsIgnoreCase(existingFirstName) && lastName.equalsIgnoreCase(existingLastName)) {
                    return false;
                }
            }
        }

        return true;
    }

//    Allow the valid name in the player list
    public static boolean isValidName(String inputName) {
        if (inputName == null || inputName.isEmpty()) {
            return false;
        }
        int nameLength = inputName.length();
        if (inputName.charAt(0) == ' ') {
            return false;
        }

        boolean lastSpace = false;
        for (int i = 0; i < nameLength; i++) {
            char currentCharacter = inputName.charAt(i);
            if (!Character.isLetterOrDigit(currentCharacter) && currentCharacter != ' ') {
                return false;
            }
            if (currentCharacter == ' ') {
                if (lastSpace) {
                    return false;
                }
                lastSpace = true;
            } else {
                lastSpace = false;
            }
        }

        return true;
    }


    //    set multiple team name
    boolean setNameTeam;
    public void setTeamName(Scanner scan) {
        System.out.println(constant.enterTeam);
        setNameTeam = true;
        while (setNameTeam) {
            String nameOfTeam = scan.nextLine();
            if ("exit".equalsIgnoreCase(nameOfTeam)) {
                setNameTeam = false;
                return;
            } else {
                if (isUnique(totalTeamName, nameOfTeam, 0, teamCounter)) {
                    totalTeamName[teamCounter] = nameOfTeam;
                    teamCounter++;
                    System.out.println(constant.teamAdded + nameOfTeam);
                    break;
                } else {
                    System.out.println(constant.teamExist);
                }
            }
        }
    }

//    set player name in a team
    public void setPlayerName(Scanner scan, int teamIndex) {
        System.out.println(constant.enterTeam);
        int countPlayerInTeam = 0;
        int playerStartIndex = playerCounter;
        while (true) {
            System.out.print("Player " + (countPlayerInTeam + 1) + ": ");
            String nameOfPlayer = scan.nextLine();
            if ("exit".equalsIgnoreCase(nameOfPlayer)) {
                if (countPlayerInTeam < 11) {
                    System.out.println(constant.validPlayer);
                    continue;
                }
                totalPlayerInTeam[teamIndex] = countPlayerInTeam;
                System.out.println(constant.existingPlayer + totalTeamName[teamIndex]);
                break;
            }
            if (!isValidName(nameOfPlayer)) {
                System.out.println(constant.invalidName);
                continue;
            }
            if (isUnique(totalPlayerName, nameOfPlayer, playerStartIndex, playerCounter)) {
                totalPlayerName[playerCounter] = nameOfPlayer;
                playerCounter++;
                countPlayerInTeam++;
            } else {
                System.out.println(constant.playerExist);
            }
        }
    }

//    set the multiple team at a time
    public void teamsSet() {
        while (true) {
            System.out.print(constant.createTeam);
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("exit")) {
                setTeamName(scanner);
                if (teamCounter > 0 && setNameTeam) {
                    setPlayerName(scanner, teamCounter - 1);
                }
            } else {
                break;
            }
        }
    }

//    view total team in list
    public void viewTotalTeam() {
        int indexOfPlayer = 0;
        for (int i = 0; i < teamCounter; i++) {
            System.out.println("Team " + (i + 1) + ": " + totalTeamName[i]);
            for (int j = indexOfPlayer; j < indexOfPlayer + totalPlayerInTeam[i]; j++) {
                System.out.println((j - indexOfPlayer + 1) + " - " + totalPlayerName[j]);
            }
            indexOfPlayer += totalPlayerInTeam[i];
            System.out.println();
        }
    }

//    print name of the team
    public void printTeamNames() {
        System.out.println(constant.availableTeam);
        for (int i = 0; i < teamCounter; i++) {
            System.out.println((i + 1) + ": " + totalTeamName[i]);
        }
        System.out.println();
    }

//    print the player in the team
    public void printTeamMembers(int teamNumber) {
        teamNumber = teamNumber - 1;
        int startIdx = getStartIndexForTeam(teamNumber);
        int endIdx = startIdx + totalPlayerInTeam[teamNumber];

        System.out.println("Player of Team " + totalTeamName[teamNumber] + ":");
        for (int i = startIdx; i < endIdx; i++) {
            System.out.println((i - startIdx + 1) + ". " + totalPlayerName[i]);
        }
    }

//    get index of the player
    private int getStartIndexForTeam(int teamIndex) {
        int startIdx = 0;
        for (int i = 0; i < teamIndex; i++) {
            startIdx += totalPlayerInTeam[i];
        }
        return startIdx;
    }

//    select the playing11 team from team list
    public void selectPlaying11Team() {
        if (teamCounter < 2) {
            System.out.println(constant.oneTeam);
            System.out.println(constant.createTeam);
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("exit")) {
                setTeamName(scanner);
                setPlayerName(scanner, teamCounter - 1);
            } else {
                System.out.println(constant.noMatchPossible);
                return;
            }
        }
        printTeamNames();
        int teamAIndex = -1;
        while (teamAIndex == -1) {
            System.out.println(constant.selectTeamA);
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.out.println(constant.emptyInput);
                    continue;
                }
                int teamA = Integer.parseInt(input);
                if (teamA < 1 || teamA > teamCounter) {
                    System.out.println(constant.invalidTeamNumber);
                } else {
                    teamAIndex = teamA;
                    teamAName = totalTeamName[teamA - 1];
                    printTeamMembers(teamA);
                    choosePlaying11Player(teamA, playing11teamA);
                }
            } catch (NumberFormatException e) {
                System.out.println(constant.invalidInput);
            }
        }
        while (true) {
            printTeamNames();
            System.out.println(constant.selectTeamB);
            try {
                String input = scanner.nextLine();
                if (Integer.parseInt(input) == teamAIndex) {
                    System.out.println(constant.teamSelected);
                    continue;
                }
                if (input.isEmpty()) {
                    System.out.println(constant.emptyInput);
                    continue;
                }
                int teamB = Integer.parseInt(input);
                if (teamB < 1 || teamB > teamCounter) {
                    System.out.println(constant.invalidTeamNumber);
                    continue;
                }
                teamBName = totalTeamName[teamB - 1];
                printTeamMembers(teamB);
                choosePlaying11Player(teamB, playing11teamB);
                break;
            } catch (NumberFormatException e) {
                System.out.println(constant.invalidInput);
            }
        }
    }

//    select the playing11 of the team
    public void choosePlaying11Player(int teamIndex, String[] playing11) {
        teamIndex = teamIndex - 1;
        int indexOfPlayer = 0;
        for (int i = 0; i < teamIndex; i++) {
            indexOfPlayer += totalPlayerInTeam[i];
        }
        System.out.println(constant.select11 + totalTeamName[teamIndex]);
        int selectedPlayers = 0;
        boolean[] isPlayerSelected = new boolean[totalPlayerInTeam[teamIndex]];
        while (selectedPlayers < 11) {
            boolean validInput = false;
            int playerChoice = -1;
            while (!validInput) {
                System.out.print(constant.serialNumber + (selectedPlayers + 1) + ": ");
                String input = scanner.nextLine();
                try {
                    playerChoice = Integer.parseInt(input);
                    if (playerChoice < 1 || playerChoice > totalPlayerInTeam[teamIndex]) {
                        System.out.println(constant.invalidChoice);
                    } else if (isPlayerSelected[playerChoice - 1]) {
                        System.out.println(constant.playerSelected);
                    } else {
                        validInput = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(constant.invalidInputNumber);
                }
            }
            int playerIndex = indexOfPlayer + playerChoice - 1;
            playing11[selectedPlayers] = totalPlayerName[playerIndex];
            isPlayerSelected[playerChoice - 1] = true;
            selectedPlayers++;
        }
    }

//    batting of the team
    public void Batting(String[] nameOfTeam) {
        System.out.println(constant.choosePlayerActive);
        for (int i = 0; i < nameOfTeam.length; i++) {
            System.out.println((i + 1) + " - " + nameOfTeam[i]);
        }
        int chooseStriker;
        String striker;
        while (true) {
            System.out.print(constant.chooseStriker);
            try {
                chooseStriker = scanner.nextInt();
                scanner.nextLine();
                if (chooseStriker < 1 || chooseStriker > nameOfTeam.length) {
                    System.out.println(constant.invalidChoice);
                } else {
                    striker = nameOfTeam[chooseStriker - 1];
                    strikerName = striker;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(constant.invalidInput);
                scanner.next();
            }
        }
        String nonStriker;
        while (true) {
            System.out.print(constant.nonStriker);
            try {
                int chooseNonStriker = scanner.nextInt();
                scanner.nextLine();
                if (chooseNonStriker == chooseStriker) {
                    System.out.println(constant.asStriker);
                } else if (chooseNonStriker < 1 || chooseNonStriker > nameOfTeam.length) {
                    System.out.println(constant.invalidIndex);
                } else {
                    nonStriker = nameOfTeam[chooseNonStriker - 1];
                    nonStrikerName = nonStriker;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(constant.invalidInput);
                scanner.next();
            }
        }
        System.out.println("\nStriker: " + strikerName + "\nNon-Striker: " + nonStrikerName + "\n");
    }


//    bowling hy the team
    private String lastBowler;
    private int countOver = 0;
    int[] overByBowler = new int[11];
    public void Bowling(String[] bowlingTeam) {
        System.out.println(constant.chooseBowler);
        for (int i = 0; i < bowlingTeam.length; i++) {
            if (!bowlingTeam[i].equals(lastBowler) && overByBowler[i] < 5) {
                System.out.println((i + 1) + " - " + bowlingTeam[i]);
            }
        }
        int chooseBowler;
        String bowler;
        while (true) {
            System.out.print(constant.chooseBowlerTeam);
            try {
                chooseBowler = scanner.nextInt();
                if (chooseBowler < 1 || chooseBowler > bowlingTeam.length) {
                    System.out.println(constant.invalidIndex);
                } else {
                    int index = chooseBowler - 1;
                    bowler = bowlingTeam[index];
                    if (bowler.equals(lastBowler)) {
                        System.out.println(constant.bowlerNotSelected);
                    } else if (overByBowler[index] >= 5) {
                        System.out.println(constant.done5Over);
                    } else {
                        bowlerName = bowler;
                        lastBowler = bowler;
                        overByBowler[index] += 1;
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println(constant.invalidInput);
                scanner.next();
            }
        }
        System.out.println("Selected Bowler: " + bowlerName + "\n\n");
    }

//     bowler stats
    private final String[] bowlers = new String[100];
    private final int[] runsGivenBowler = new int[100];
    private final int[] wicketsByBowler = new int[100];
    private int uniqueBowler = 0;
    public void bowlerStat(String bowlerName, int run, int wicket) {
        int index = -1;
        for (int i = 0; i < uniqueBowler; i++) {
            if (bowlers[i].equals(bowlerName)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            index = uniqueBowler;
            if (uniqueBowler < bowlers.length) {
                bowlers[index] = bowlerName;
                runsGivenBowler[index] = 0;
                wicketsByBowler[index] = 0;
                uniqueBowler++;
            }
        }
        runsGivenBowler[index] += run;
        wicketsByBowler[index] += wicket;
    }

//    print the bowler stat
    public void printBowlerStats() {
        for (int i = 0; i < uniqueBowler; i++) {
            System.out.println("Bowler: " + bowlers[i] + ", Runs Given: " + runsGivenBowler[i] + ", Wickets Taken: " + wicketsByBowler[i]);
        }
    }

//    set number of over match
    public void setOver() {
        int over;
        while (true) {
            System.out.print(constant.leastOver);
            try {
                over = scanner.nextInt();
                scanner.nextLine();
                if (over < 2) {
                    System.out.println(constant.invalidSelection);
                } else {
                    Over = over;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(constant.invalidInput);
                scanner.next();
            }
        }
    }

//    give the toss result of the team
    public void tossResult(String teamName, String teamChoice) {
        if (teamName.equals(teamAName)) {
            if (teamChoice.equalsIgnoreCase(constant.batting)) {
                Bowling(playing11teamB);
            } else {
                Bowling(playing11teamA);
            }
        } else {
            if (teamChoice.equalsIgnoreCase(constant.batting)) {
                Bowling(playing11teamA);
            } else {
                Bowling(playing11teamB);
            }
        }
    }

//  toss between the teams
    public void toss() {
        while (true) {
            System.out.print(constant.teamToss);
            System.out.println(teamAName + "\t" + teamBName);
            String tossWin = scanner.next().trim();

            if (!tossWin.equalsIgnoreCase(teamAName) && !tossWin.equalsIgnoreCase(teamBName)) {
                System.out.println(constant.invalidTeam);
                continue;
            }

            tossWinner = tossWin.equalsIgnoreCase(teamAName) ? teamAName : teamBName;
            tossLooser = tossWinner.equalsIgnoreCase(teamAName) ? teamBName : teamAName;

            while (true) {
                System.out.print(constant.choiceTeam);
                String choice = scanner.next().trim().toLowerCase();

                if (!choice.equalsIgnoreCase(constant.batting) && !choice.equalsIgnoreCase("bowling")) {
                    System.out.println(constant.invalidChoiceOfTeam);
                    continue;
                }

                teamChoice = choice.equalsIgnoreCase("batting") ? "batting" : "bowling";
                System.out.println("\nTeam " + tossWinner + " won the toss and chose " + teamChoice + " first.\n");
                break;
            }
            break;
        }
    }

//swap the position of the player
    public void swapPlayer(String Striker, String nonStriker) {
        String temp = Striker;
        strikerName = nonStriker;
        nonStrikerName = temp;
        swapScore(strikerScore, nonStrikerScore);
    }

//    swap the score of the players
    public void swapScore(int scoreStriker, int scoreNonStriker) {
        int temp = scoreStriker;
        strikerScore = scoreNonStriker;
        nonStrikerScore = temp;
    }

//    swap the team in current array
    String[] currentPlaying = new String[11];
    public void swapTeam(String[] current, String[] playing11) {
        for (int i = 0; i < playing11.length; i++) {
            current[i] = playing11[i];
        }
    }
    String[] batsmanRemains = new String[11];
    String[] outBatsman = new String[11];
    int[] outBatsmanRun = new int[11];
    String[] bowlerTakenWicket = new String[11];
    int outPlayerIndex = 0;
    static private int strikerScore;
    static private int nonStrikerScore;

//    wicket of the team is handled here
    public void wicket(String outPlayer, int outPlayerScore, String[] playingTeam) {
        System.out.println(constant.chooseBatsman);
        int remainingPlayerIndex = 0;
        for (int i = 0; i < playingTeam.length; i++) {
            boolean isOut = false;
            for (String out : outBatsman) {
                if (playingTeam[i] != null && playingTeam[i].equals(out)) {
                    isOut = true;
                    outBatsman[outPlayerIndex] = outPlayer;
                    outBatsmanRun[outPlayerIndex] = outPlayerScore;
                    bowlerTakenWicket[outPlayerIndex] = bowlerName;
                    outPlayerIndex++;
                    break;
                }
            }
            if (playingTeam[i] != null && !playingTeam[i].equals(outPlayer) && !playingTeam[i].equals(nonStrikerName) && !playingTeam[i].equals(strikerName) && !isOut) {
                batsmanRemains[remainingPlayerIndex] = playingTeam[i];
                remainingPlayerIndex++;
            }
        }

        for (int i = 0; i < playingTeam.length; i++) {
            if (playingTeam[i] != null && playingTeam[i].equals(outPlayer)) {
                playingTeam[i] = null;
                break;
            }
        }

        for (int j = 0; j < remainingPlayerIndex; j++) {
            System.out.println((j + 1) + " - " + batsmanRemains[j]);
        }

        int chooseNewBatsman;
        while (true) {
            System.out.print(constant.indexBatsman);
            try {
                chooseNewBatsman = Integer.parseInt(scanner.nextLine());
                if (chooseNewBatsman < 1 || chooseNewBatsman > remainingPlayerIndex) {
                    System.out.println(constant.invalidChoice);
                } else {
                    String newBatsman = batsmanRemains[chooseNewBatsman - 1];
                    System.out.println(constant.batsmanSelection + newBatsman);
                    if (outPlayer.equals(strikerName)) {
                        strikerName = newBatsman;
                        strikerScore = 0;
                    } else if (outPlayer.equals(nonStrikerName)) {
                        nonStrikerName = newBatsman;
                        nonStrikerScore = 0;
                    }
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println(constant.invalidInput);
            }
        }
    }


    //  print the batting stats
    public void printBattingStats() {
        for (int i = 0; i < outPlayerIndex; i++) {
            System.out.println("Batsman: " + outBatsman[i] + ", Runs Scored: " + outBatsmanRun[i] + ", Wicket Taken By: " + bowlerTakenWicket[i]);
        }
    }

//  types of balls in the match
    static int totalScore = 0;
    static int totalScoreTeamA = 0;
    static int totalScoreTeamB = 0;
    static int totalWicket = 0;
    static int bowlerWicket = 0;
    static int bowlerRun = 0;
    static int extras = 0;
    int balls = 0;
    int totalDelivery = 0;
    public void regularBall() {
        while (true) {
            try {
                System.out.print(constant.chooseScore);
                int choose = Integer.parseInt(scanner.nextLine());
                switch (choose) {
                    case 0:
                        System.out.println(constant.dotBall);
                        balls += 1;
                        break;
                    case 1:
                        System.out.println(constant.oneRun);
                        totalScore += 1;
                        strikerScore += 1;
                        bowlerRun += 1;
                        balls += 1;
                        swapPlayer(strikerName, nonStrikerName);
                        break;
                    case 2:
                        System.out.println(constant.twoRun);
                        totalScore += 2;
                        strikerScore += 2;
                        bowlerRun += 2;
                        balls += 1;
                        break;
                    case 3:
                        System.out.println(constant.threeRun);
                        totalScore += 3;
                        strikerScore += 3;
                        bowlerRun += 3;
                        balls += 1;
                        swapPlayer(strikerName, nonStrikerName);
                        break;
                    case 4:
                        System.out.println(constant.fourRun);
                        totalScore += 4;
                        strikerScore += 4;
                        bowlerRun += 4;
                        balls += 1;
                        break;
                    case 6:
                        System.out.println(constant.sixRun);
                        totalScore += 6;
                        strikerScore += 6;
                        bowlerRun += 6;
                        balls += 1;
                        break;
                    case 7:
                        System.out.println(constant.hitWicket);
                        totalWicket += 1;
                        bowlerWicket += 1;
                        balls += 1;
                        if (totalWicket <= 9) {
                            wicket(strikerName, strikerScore, currentPlaying);
                        } else {
                            break;
                        }
                        break;
                    case 8:
                        System.out.println(constant.runOut);
                        totalWicket += 1;
                        balls += 1;
                        System.out.print(constant.whoGotOut);
                        int whoGotOut = Integer.parseInt(scanner.nextLine());
                        if (whoGotOut == 1) {
                            System.out.println(constant.strikerOut);
                            if (totalWicket <= 9) {
                                wicket(strikerName, strikerScore, currentPlaying);
                            } else {
                                break;
                            }
                        } else if (whoGotOut == 2) {
                            System.out.println(constant.nonStrikerOut);
                            if (totalWicket <= 9) {
                                wicket(nonStrikerName, nonStrikerScore, currentPlaying);
                            } else {
                                break;
                            }
                        } else {
                            System.out.println(constant.invalidInputBatsman);
                        }
                        break;
                    case 9:
                        System.out.println(constant.catchOut);
                        totalWicket += 1;
                        bowlerWicket += 1;
                        balls += 1;
                        if (totalWicket <= 9) {
                            wicket(strikerName, strikerScore, currentPlaying);
                        } else {
                            break;
                        }
                        break;
                    default:
                        System.out.println(constant.invalid);
                        continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(constant.invalidName);
            }
        }

        totalDelivery += 1;
        if (balls == 6) {
            bowlerStat(bowlerName, bowlerRun, bowlerWicket);
            swapPlayer(strikerName, nonStrikerName);
            countOver += 1;
            balls = 0;
            bowlerRun = 0;
            bowlerWicket = 0;
            totalDelivery = 0;
            System.out.println(constant.overComplete);
            if (countOver < Over) {
                tossResult(tossWinner, teamChoice);
            }
        }
    }

    public void wide() {
        while (true) {
            try {
                System.out.print(constant.wideBallOption);
                int choose = Integer.parseInt(scanner.nextLine());
                totalScore += 1;
                extras += 1;
                switch (choose) {
                    case 0:
                        System.out.println(constant.dotBall);
                        break;
                    case 1:
                        System.out.println(constant.oneRun);
                        totalScore += 1;
                        extras += 1;
                        swapPlayer(strikerName, nonStrikerName);
                        break;
                    case 2:
                        System.out.println(constant.twoRun);
                        totalScore += 2;
                        extras += 2;
                        break;
                    case 3:
                        System.out.println(constant.threeRun);
                        totalScore += 3;
                        extras += 3;
                        swapPlayer(strikerName, nonStrikerName);
                        break;
                    case 4:
                        System.out.println(constant.fourRun);
                        totalScore += 4;
                        extras += 4;
                        break;
                    default:
                        System.out.println(constant.invalidInput);
                        continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(constant.invalid);
            }
        }

        totalDelivery += 1;
    }

    public void noBall() {
        while (true) {
            try {
                System.out.println(constant.noBallOption);
                int choose = Integer.parseInt(scanner.nextLine());
                totalScore += 1;
                extras += 1;
                switch (choose) {
                    case 0:
                        System.out.println(constant.dotBall);
                        break;
                    case 1:
                        System.out.println(constant.oneRun);
                        totalScore += 1;
                        extras += 1;
                        swapPlayer(strikerName, nonStrikerName);
                        break;
                    case 2:
                        System.out.println(constant.twoRun);
                        totalScore += 2;
                        extras += 2;
                        break;
                    case 3:
                        System.out.println(constant.threeRun);
                        totalScore += 3;
                        extras += 3;
                        swapPlayer(strikerName, nonStrikerName);
                        break;
                    case 4:
                        System.out.println(constant.fourRun);
                        totalScore += 4;
                        extras += 4;
                        break;
                    case 6:
                        System.out.println(constant.sixRun);
                        totalScore += 6;
                        extras += 6;
                        break;
                    case 7:
                        System.out.println(constant.runOut);
                        totalWicket += 1;
                        while (true) {
                            System.out.print(constant.whoGotOut);
                            int whoGotOut = Integer.parseInt(scanner.nextLine());
                            if (whoGotOut == 1) {
                                System.out.println(constant.strikerOut);
                                if (totalWicket <= 9) {
                                    wicket(strikerName, strikerScore, currentPlaying);
                                } else {
                                    break;
                                }
                            } else if (whoGotOut == 2) {
                                System.out.println(constant.nonStrikerOut);
                                if (totalWicket <= 9) {
                                    wicket(nonStrikerName, nonStrikerScore, currentPlaying);
                                } else {
                                    break;
                                }
                            } else {
                                System.out.println(constant.invalidInputBatsman);
                                continue;
                            }
                        }
                        break;
                    default:
                        System.out.println(constant.invalidInput);
                        continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(constant.invalid);
            }
        }

        totalDelivery += 1;
    }

    public void bye() {
        while (true) {
            try {
                System.out.print(constant.byeOption);
                int byeRun = Integer.parseInt(scanner.nextLine());
                switch (byeRun) {
                    case 0:
                        System.out.println(constant.dotBall);
                        break;
                    case 1:
                        System.out.println(constant.oneRun);
                        totalScore += 1;
                        extras += 1;
                        swapPlayer(strikerName, nonStrikerName);
                        break;
                    case 2:
                        System.out.println(constant.twoRun);
                        totalScore += 2;
                        extras += 2;
                        break;
                    case 3:
                        System.out.println(constant.threeRun);
                        totalScore += 3;
                        extras += 3;
                        swapPlayer(strikerName, nonStrikerName);
                        break;
                    case 4:
                        System.out.println(constant.fourRun);
                        totalScore += 4;
                        extras += 4;
                        break;
                    default:
                        System.out.println(constant.invalidInput);
                        continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(constant.invalid);
            }
        }

        totalDelivery += 1;
    }

//    count runs in match
    public void countRuns(String player) {
        while (true) {
            try {
                System.out.print(constant.chooseBallOption);
                int ballType = scanner.nextInt();
                scanner.nextLine();

                switch (ballType) {
                    case 1:
                        regularBall();
                        break;
                    case 2:
                        wide();
                        break;
                    case 3:
                        noBall();
                        break;
                    case 4:
                    case 5:
                        bye();
                        break;
                    default:
                        System.out.println(constant.invalidInput);
                        continue;
                }
                break;
            } catch (Exception e) {
                System.out.println(constant.invalid);
                scanner.nextLine();
            }
        }
    }

//    gives the next batting team
    boolean played;
    public void nextBatting(boolean toss) {
        if (tossWinner.equalsIgnoreCase(teamAName) && teamChoice.equalsIgnoreCase(constant.batting) && toss) {
            played = false;
        }
        if (tossWinner.equalsIgnoreCase(teamAName) && teamChoice.equalsIgnoreCase(constant.bowling) && !toss) {
            played = true;
        }
        if (tossWinner.equalsIgnoreCase(teamBName) && teamChoice.equalsIgnoreCase(constant.batting) && toss) {
            played = true;
        }
        if (tossWinner.equalsIgnoreCase(teamBName) && teamChoice.equalsIgnoreCase(constant.bowling) && !toss) {
            played = false;
        }
    }

//    reset the inning variables
    public void resetInningVariable() {
        totalScore = 0;
        totalWicket = 0;
        strikerScore = 0;
        nonStrikerScore = 0;
        countOver = 0;
        balls = 0;
        bowlerWicket = 0;
        bowlerRun = 0;
        extras = 0;
    }

    int indexA = 0;
    int indexB = 0;
    public void printPlaying11(){
        if (tossWinner.equals(teamAName)) {
            for (int i = 0; i < totalTeamName.length; i++) {
                if (teamAName.equals(totalTeamName[i])) {
                    indexA = i+1;
                }
                if(teamBName.equals(totalTeamName[i])) {
                    indexB = i+1;
                }
            }
            switch (teamChoice.toLowerCase()) {
                case "batting":
                    System.out.println(teamAName + " batting team is:-");
                    printTeamMembers(indexA);
                    System.out.println(teamBName + " bowling team is:-");
                    printTeamMembers(indexB);
                    break;
                case "bowling":
                    System.out.println(teamBName + " batting team is:-");
                    printTeamMembers(indexB);
                    System.out.println(teamAName + " bowling team is:-");
                    printTeamMembers(indexA);
                    break;
            }
        } else {
            switch (teamChoice.toLowerCase()) {
                case "batting":
                    System.out.println(teamBName + " batting team is:-");
                    printTeamMembers(indexB);
                    System.out.println(teamAName + " bowling team is:-");
                    printTeamMembers(indexA);
                    break;

                case "bowling":
                    System.out.println(teamAName + " batting team is:-");
                    printTeamMembers(indexA);
                    System.out.println(teamBName + " bowling team is:-");
                    printTeamMembers(indexB);
                    break;
            }
        }
    }

    public void startMatch(){
        System.out.println(constant.startMatch);
        boolean toss;
        if (tossWinner.equals(teamAName)) {
            if (teamChoice.equalsIgnoreCase(constant.batting)) {
                swapTeam(currentPlaying, playing11teamA);
                Batting(currentPlaying);
                Bowling(playing11teamB);
                teamPlayedTimes += 1;
                toss = true;
            } else {
                swapTeam(currentPlaying, playing11teamB);
                Batting(currentPlaying);
                Bowling(playing11teamA);
                toss = false;
                teamPlayedTimes += 1;
            }
        } else {
            if (teamChoice.equalsIgnoreCase(constant.batting)) {
                swapTeam(currentPlaying, playing11teamB);
                Batting(currentPlaying);
                Bowling(playing11teamA);
                teamPlayedTimes += 1;
                toss = true;
            } else {
                swapTeam(currentPlaying, playing11teamA);
                Batting(currentPlaying);
                Bowling(playing11teamB);
                toss = false;
                teamPlayedTimes += 1;
            }
        }
        int runsLeftTeamA = 0;
        int runsLeftTeamB = 0;
        resetInningVariable();
        int Inning = 0;
        while (Inning < 2) {
            if (toss) {
                if (runsLeftTeamB > totalScoreTeamB && Inning == 1) {
                    bowlerStat(bowlerName, bowlerRun, bowlerWicket);
                    totalScoreTeamA = totalScore;
                    System.out.println(tossWinner + " Scored " + totalScoreTeamA + "/" + totalWicket);
                    break;
                }
                System.out.println("Striker:  " + strikerName + "\t\t|\t\t" + "nonStriker:  " + nonStrikerName + "\n" +
                        "Bowler:  " + bowlerName + "\t\t\t" + "Over: " + countOver + "." + balls + "/" + Over + "\t\t\t" +
                        "Extras: " + extras + "\n" + tossWinner + "  " + totalScore + "/" + totalWicket + "\t\t\t" +
                        strikerName + "  " + strikerScore + "\t\t\t" + nonStrikerName + "  " + nonStrikerScore + "\n");
                if (countOver < Over && totalWicket < 10) {
                    countRuns(strikerName);
                    if (Inning ==1 && runsLeftTeamB <= totalScoreTeamB) {
                        System.out.println("Team " + tossWinner + " require " + (totalScoreTeamB - totalScore + 1) + " to win");
                        runsLeftTeamB = totalScore;
                    }
                }
                if (countOver == Over || totalWicket == 10) {
                    totalScoreTeamA = totalScore;
                    System.out.println(tossWinner + " Scored " + totalScoreTeamA + "/" + totalWicket);
                    System.out.println("\nInning Complete" + "\n\n");
                    if (teamPlayedTimes == 2) {
                        break;
                    } else {
                        nextBatting(toss);
                        toss = false;
                        if (played) {
                            swapTeam(currentPlaying, playing11teamA);
                            Batting(currentPlaying);
                            Bowling(playing11teamB);
                            teamPlayedTimes += 1;
                            teamChoice = "bowling";
                        } else {
                            swapTeam(currentPlaying, playing11teamB);
                            Batting(currentPlaying);
                            Bowling(playing11teamA);
                            teamPlayedTimes += 1;
                            teamChoice = "bowling";
                        }
                        Inning += 1;
                        resetInningVariable();
                    }
                }
            } else {
                if (runsLeftTeamA > totalScoreTeamA & Inning == 1) {
                    bowlerStat(bowlerName, bowlerRun, bowlerWicket);
                    totalScoreTeamB = totalScore;
                    System.out.println(tossLooser + " Scored " + totalScoreTeamB + "/" + totalWicket);
                    break;
                }
                System.out.println("Striker:  " + strikerName + "\t\t|\t\t" + "nonStriker:  " + nonStrikerName + "\n" +
                        "Bowler:  " + bowlerName + "\t\t\t" + "Over: " + countOver + "." + balls + "/" + Over + "\t\t\t" +
                        "Extras: " + extras + "\n" + tossLooser + "  " + totalScore + "/" + totalWicket + "\t\t\t" +
                        strikerName + "  " + strikerScore + "\t\t\t" + nonStrikerName + "  " + nonStrikerScore+"\n");
                if (countOver < Over && totalWicket < 10) {
                    countRuns(strikerName);
                    if(Inning == 1 && runsLeftTeamA <= totalScoreTeamA) {
                        System.out.println("Team " + tossLooser + " require " + (totalScoreTeamA - totalScore + 1) + " to win" );
                        runsLeftTeamA = totalScore;
                    }
                }
                if (countOver == Over || totalWicket == 10) {
                    totalScoreTeamB = totalScore;
                    System.out.println(tossLooser + " Scored " + totalScoreTeamB + "/" + totalWicket);
                    runsLeftTeamB = totalScoreTeamB;
                    System.out.println("Inning Complete" + "\n\n\n\n");

                    if (teamPlayedTimes == 2) {
                        break;
                    } else {
                        nextBatting(toss);
                        toss = true;
                        if (played) {
                            swapTeam(currentPlaying, playing11teamA);
                            Batting(currentPlaying);
                            Bowling(playing11teamB);
                            teamPlayedTimes += 1;
                            teamChoice = "batting";
                        } else {
                            swapTeam(currentPlaying, playing11teamB);
                            Batting(currentPlaying);
                            Bowling(playing11teamA);
                            teamPlayedTimes += 1;
                            teamChoice = "batting";
                        }
                        Inning += 1;
                        resetInningVariable();
                    }
                }
            }
        }
        if (totalScoreTeamA > totalScoreTeamB) {
            System.out.println("Winner is " + teamAName + " by " + (totalScoreTeamA - totalScoreTeamB) + " runs.\n");
        } else if (totalScoreTeamA < totalScoreTeamB) {
            System.out.println("Winner is " + teamBName + " by " + (totalScoreTeamB - totalScoreTeamA) + " runs.\n");
        } else {
            System.out.println("It's a Tie! Both teams scored " + totalScoreTeamA + " runs.\n");
        }
    }


    static int teamPlayedTimes = 0;
    public static void main(String[] args) {
        cricketScoreBoard cricket = new cricketScoreBoard();
        while (true) {
            System.out.print(constant.chooseCase);
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        cricket.teamsSet();
                        cricket.selectPlaying11Team();
                        break;
                    case 2:
                        cricket.viewTotalTeam();
                        break;

                    case 3:
                        cricket.setOver();
                        cricket.toss();
                        break;
                    case 4:
                        cricket.printPlaying11();
                        break;
                    case 5:
                        cricket.startMatch();
                        break;
                    case 6:
                        cricket.printBowlerStats();
                        break;
                    case 7:
                        cricket.printBattingStats();
                        break;
                    default:
                        System.out.println(constant.invalidOption);
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }
}
