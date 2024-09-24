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

//    allows the unique name of the player in the list
    public static boolean isUnique(String[] playerArray, String playerName, int endIndex) {
        if (playerName == null || playerName.isEmpty()) {
            return false;
        }
        for (int i = 0; i < endIndex; i++) {
            if (playerArray[i] != null && playerArray[i].equals(playerName)) {
                return false;
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
        if (inputName.charAt(0) == ' ' || inputName.charAt(nameLength - 1) == ' ') {
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
        System.out.println(constant.ENTER_TEAM);
        setNameTeam = true;
        while (setNameTeam) {
            String nameOfTeam = scan.nextLine();
            if (constant.EXIT.equalsIgnoreCase(nameOfTeam)) {
                setNameTeam = false;
                return;
            } else {
                if (!isValidName(nameOfTeam)){
                    System.out.println(constant.INVALID_TEAM_NAME);
                    System.out.println(constant.ENTER_TEAM);
                    continue;
                }
                if (isUnique(totalTeamName, nameOfTeam, teamCounter)) {
                    totalTeamName[teamCounter] = nameOfTeam;
                    teamCounter++;
                    System.out.println(constant.TEAM_ADDED + nameOfTeam);
                    break;
                } else {
                    System.out.println(constant.TEAM_EXIST);
                }
            }
        }
    }

    //    set player name in a team
    public void setPlayerName(Scanner scan, int teamIndex) {
        System.out.println(constant.ENTER_PLAYER);
        int countPlayerInTeam = 0;
        int playerStartIndex = playerCounter;
        while (true) {
            System.out.print(constant.PLAYER + (countPlayerInTeam + 1) + ": ");
            String nameOfPlayer = scan.nextLine();
            if (constant.EXIT.equalsIgnoreCase(nameOfPlayer)) {
                if (countPlayerInTeam < 11) {
                    System.out.println(constant.VALID_PLAYER);
                    continue;
                }
                totalPlayerInTeam[teamIndex] = countPlayerInTeam;
                System.out.println(constant.EXITING_PLAYER + totalTeamName[teamIndex]);
                break;
            }
            if (!isValidName(nameOfPlayer)) {
                System.out.println(constant.INVALID_NAME);
                continue;
            }
            if (isUnique(totalPlayerName, nameOfPlayer, playerCounter)) {
                totalPlayerName[playerCounter] = nameOfPlayer;
                playerCounter++;
                countPlayerInTeam++;
            } else {
                System.out.println(constant.PLAYER_EXIST);
            }
        }
    }

//    set the multiple team at a time
    public void teamsSet() {
        while (true) {
            System.out.print(constant.CREATE_TEAM);
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase(constant.EXIT)) {
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
            System.out.println(constant.TEAM + (i + 1) + ": " + totalTeamName[i]);
            for (int j = indexOfPlayer; j < indexOfPlayer + totalPlayerInTeam[i]; j++) {
                System.out.println((j - indexOfPlayer + 1) + " - " + totalPlayerName[j]);
            }
            indexOfPlayer += totalPlayerInTeam[i];
            System.out.println();
        }
    }

//    print name of the team
    public void printTeamNames() {
        System.out.println(constant.AVAILABLE_TEAM);
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
        System.out.println(constant.PLAYER_OF_TEAM + totalTeamName[teamNumber] + ":");
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
            System.out.println(constant.ONE_TEAM);
            System.out.println(constant.CREATE_TEAM);
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase(constant.EXIT)) {
                setTeamName(scanner);
                setPlayerName(scanner, teamCounter - 1);
            } else {
                System.out.println(constant.N0_MATCH_POSSIBLE);
                return;
            }
        }
        printTeamNames();
        int teamAIndex = -1;
        while (teamAIndex == -1) {
            System.out.println(constant.SELECT_TEAM_A);
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.out.println(constant.EMPTY_INPUT);
                    continue;
                }
                int teamA = Integer.parseInt(input);
                if (teamA < 1 || teamA > teamCounter) {
                    System.out.println(constant.INVALID_TEAM_NUMBER);
                } else {
                    teamAIndex = teamA;
                    teamAName = totalTeamName[teamA - 1];
                    printTeamMembers(teamA);
                    choosePlaying11Player(teamA, playing11teamA);
                }
            } catch (NumberFormatException e) {
                System.out.println(constant.INVALID_INPUT);
            }
        }
        while (true) {
            printTeamNames();
            System.out.println(constant.SELECT_TEAM_B);
            try {
                String input = scanner.nextLine();
                if (Integer.parseInt(input) == teamAIndex) {
                    System.out.println(constant.TEAM_SELECTED);
                    continue;
                }
                if (input.isEmpty()) {
                    System.out.println(constant.EMPTY_INPUT);
                    continue;
                }
                int teamB = Integer.parseInt(input);
                if (teamB < 1 || teamB > teamCounter) {
                    System.out.println(constant.INVALID_TEAM_NUMBER);
                    continue;
                }
                teamBName = totalTeamName[teamB - 1];
                printTeamMembers(teamB);
                choosePlaying11Player(teamB, playing11teamB);
                break;
            } catch (NumberFormatException e) {
                System.out.println(constant.INVALID_INPUT);
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
        System.out.println(constant.SELECT_11 + totalTeamName[teamIndex]);
        int selectedPlayers = 0;
        boolean[] isPlayerSelected = new boolean[totalPlayerInTeam[teamIndex]];
        while (selectedPlayers < 11) {
            boolean validInput = false;
            int playerChoice = -1;
            while (!validInput) {
                System.out.print(constant.SERIAL_NUMBER + (selectedPlayers + 1) + ": ");
                String input = scanner.nextLine();
                try {
                    playerChoice = Integer.parseInt(input);
                    if (playerChoice < 1 || playerChoice > totalPlayerInTeam[teamIndex]) {
                        System.out.println(constant.INVALID_CHOICE);
                    } else if (isPlayerSelected[playerChoice - 1]) {
                        System.out.println(constant.PLAYER_SELECTED);
                    } else {
                        validInput = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(constant.INVALID_INPUT_NUMBER);
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
        System.out.println(constant.CHOOSE_PLAYER_ACTIVE);
        for (int i = 0; i < nameOfTeam.length; i++) {
            System.out.println((i + 1) + " - " + nameOfTeam[i]);
        }
        int chooseStriker;
        String striker;
        while (true) {
            System.out.print(constant.CHOOSE_STRIKER);
            try {
                chooseStriker = scanner.nextInt();
                scanner.nextLine();
                if (chooseStriker < 1 || chooseStriker > nameOfTeam.length) {
                    System.out.println(constant.INVALID_CHOICE);
                } else {
                    striker = nameOfTeam[chooseStriker - 1];
                    strikerName = striker;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(constant.INVALID_INPUT);
                scanner.next();
            }
        }
        String nonStriker;
        while (true) {
            System.out.print(constant.CHOOSE_NON_STRIKER);
            try {
                int chooseNonStriker = scanner.nextInt();
                scanner.nextLine();
                if (chooseNonStriker == chooseStriker) {
                    System.out.println(constant.AS_STRIKER);
                } else if (chooseNonStriker < 1 || chooseNonStriker > nameOfTeam.length) {
                    System.out.println(constant.INVALID_INDEX);
                } else {
                    nonStriker = nameOfTeam[chooseNonStriker - 1];
                    nonStrikerName = nonStriker;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(constant.INVALID_INPUT);
                scanner.next();
            }
        }
        System.out.println("\n"+constant.STRIKER + strikerName + "\n"+constant.NON_STRIKER + nonStrikerName + "\n");
    }

//    bowling by the team
    private String lastBowler;
    private int countOver = 0;
    int[] overByBowler = new int[11];
    public void Bowling(String[] bowlingTeam) {
        System.out.println(constant.CHOOSE_BOWLER);
        for (int i = 0; i < bowlingTeam.length; i++) {
            if (!bowlingTeam[i].equals(lastBowler) && overByBowler[i] < 5) {
                System.out.println((i + 1) + " - " + bowlingTeam[i]);
            }
        }
        int chooseBowler;
        String bowler;
        while (true) {
            System.out.print(constant.CHOOSE_BOWLER_TEAM);
            try {
                chooseBowler = scanner.nextInt();
                if (chooseBowler < 1 || chooseBowler > bowlingTeam.length) {
                    System.out.println(constant.INVALID_INDEX);
                } else {
                    int index = chooseBowler - 1;
                    bowler = bowlingTeam[index];
                    if (bowler.equals(lastBowler)) {
                        System.out.println(constant.BOWLER_NOT_SELECTED);
                    } else if (overByBowler[index] >= 5) {
                        System.out.println(constant.DONE_5_OVER);
                    } else {
                        bowlerName = bowler;
                        lastBowler = bowler;
                        overByBowler[index] += 1;
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println(constant.INVALID_INPUT);
                scanner.next();
            }
        }
        System.out.println(constant.SELECTED_BOWLER + bowlerName + "\n\n");
    }

//   bowler stats
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
            System.out.println(constant.BOWLER + bowlers[i] + constant.RUNS_GIVEN + runsGivenBowler[i] + constant.WICKET_TAKEN + wicketsByBowler[i]);
        }
    }

//    set number of over match
    public void setOver() {
        int over;
        while (true) {
            System.out.print(constant.LEAST_OVER);
            try {
                over = scanner.nextInt();
                scanner.nextLine();
                if (over < 2) {
                    System.out.println(constant.INVALID_SELECTION);
                } else {
                    Over = over;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(constant.INVALID_INPUT);
                scanner.next();
            }
        }
    }

//    give the toss result of the team
    public void tossResult(String teamName, String teamChoice) {
        if (teamName.equals(teamAName)) {
            if (teamChoice.equalsIgnoreCase(constant.BATTING)) {
                Bowling(playing11teamB);
            } else {
                Bowling(playing11teamA);
            }
        } else {
            if (teamChoice.equalsIgnoreCase(constant.BATTING)) {
                Bowling(playing11teamA);
            } else {
                Bowling(playing11teamB);
            }
        }
    }

//  toss between the teams
    public void toss() {
        while (true) {
            System.out.print(constant.TEAM_TOSS);
            System.out.println(teamAName + "\t" + teamBName);
            String tossWin = scanner.next().trim();
            if (!tossWin.equalsIgnoreCase(teamAName) && !tossWin.equalsIgnoreCase(teamBName)) {
                System.out.println(constant.INVALID_TEAM);
                continue;
            }
            tossWinner = tossWin.equalsIgnoreCase(teamAName) ? teamAName : teamBName;
            tossLooser = tossWinner.equalsIgnoreCase(teamAName) ? teamBName : teamAName;
            while (true) {
                System.out.print(constant.CHOICE_TEAM);
                String choice = scanner.next().trim().toLowerCase();

                if (!choice.equalsIgnoreCase(constant.BATTING) && !choice.equalsIgnoreCase(constant.BOWLING)) {
                    System.out.println(constant.INVALID_CHOICE_OF_TEAM);
                    continue;
                }
                teamChoice = choice.equalsIgnoreCase(constant.BATTING) ? constant.BATTING : constant.BOWLING;
                System.out.println("\n"+constant.TEAM + tossWinner + constant.WON_THE_TOSS + teamChoice + constant.FIRST);
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

//    wicket of the team is handled here
    String[] batsmanRemains = new String[11];
    String[] outBatsman = new String[11];
    int[] outBatsmanRun = new int[11];
    String[] bowlerTakenWicket = new String[11];
    int outPlayerIndex = 0;
    static private int strikerScore;
    static private int nonStrikerScore;
    public void wicket(String outPlayer, int outPlayerScore, String[] playingTeam) {
        System.out.println(constant.CHOOSE_BATSMAN);
        int remainingPlayerIndex = 0;
        outBatsman[outPlayerIndex] = outPlayer;
        outBatsmanRun[outPlayerIndex] = outPlayerScore;
        bowlerTakenWicket[outPlayerIndex] = bowlerName;
        outPlayerIndex++;
        for (int i = 0; i < playingTeam.length; i++) {
            boolean isOut = false;
            for (String out : outBatsman) {
                if (playingTeam[i] != null && playingTeam[i].equals(out)) {
                    isOut = true;
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
            System.out.print(constant.INDEX_BATSMAN);
            try {
                chooseNewBatsman = Integer.parseInt(scanner.nextLine());
                if (chooseNewBatsman < 1 || chooseNewBatsman > remainingPlayerIndex) {
                    System.out.println(constant.INVALID_CHOICE);
                } else {
                    String newBatsman = batsmanRemains[chooseNewBatsman - 1];
                    System.out.println(constant.BATSMAN_SELECTION + newBatsman);
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
                System.out.println(constant.INVALID_INPUT);
            }
        }
    }

    //  print the batting stats
    public void printBattingStats() {
        for (int i = 0; i < outPlayerIndex; i++) {
            System.out.println(constant.BATSMAN + outBatsman[i] + constant.RUN_SCORED + outBatsmanRun[i] + constant.WICKET_TAKEN_BY + bowlerTakenWicket[i]);
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
                System.out.print(constant.CHOOSE_SCORE);
                int choose = Integer.parseInt(scanner.nextLine());
                switch (choose) {
                    case 0:
                        System.out.println(constant.DOT_BALL);
                        balls += 1;
                        break;
                    case 1:
                        System.out.println(constant.ONE_RUN);
                        totalScore += 1;
                        strikerScore += 1;
                        bowlerRun += 1;
                        balls += 1;
                        swapPlayer(strikerName, nonStrikerName);
                        break;
                    case 2:
                        System.out.println(constant.TWO_RUN);
                        totalScore += 2;
                        strikerScore += 2;
                        bowlerRun += 2;
                        balls += 1;
                        break;
                    case 3:
                        System.out.println(constant.THREE_RUN);
                        totalScore += 3;
                        strikerScore += 3;
                        bowlerRun += 3;
                        balls += 1;
                        swapPlayer(strikerName, nonStrikerName);
                        break;
                    case 4:
                        System.out.println(constant.FOUR_RUN);
                        totalScore += 4;
                        strikerScore += 4;
                        bowlerRun += 4;
                        balls += 1;
                        break;
                    case 6:
                        System.out.println(constant.SIX_RUN);
                        totalScore += 6;
                        strikerScore += 6;
                        bowlerRun += 6;
                        balls += 1;
                        break;
                    case 7:
                        System.out.println(constant.HIT_WICKET);
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
                        System.out.println(constant.RUN_OUT);
                        totalWicket += 1;
                        balls += 1;
                        System.out.print(constant.WHO_GOT_OUT);
                        int whoGotOut = Integer.parseInt(scanner.nextLine());
                        if (whoGotOut == 1) {
                            System.out.println(constant.STRIKER_OUT);
                            if (totalWicket <= 9) {
                                wicket(strikerName, strikerScore, currentPlaying);
                            } else {
                                break;
                            }
                        } else if (whoGotOut == 2) {
                            System.out.println(constant.NON_STRIKER_OUT);
                            if (totalWicket <= 9) {
                                wicket(nonStrikerName, nonStrikerScore, currentPlaying);
                            } else {
                                break;
                            }
                        } else {
                            System.out.println(constant.INVALID_INPUT_BATSMAN);
                        }
                        break;
                    case 9:
                        System.out.println(constant.CATCH_OUT);
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
                        System.out.println(constant.INVALID);
                        continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(constant.INVALID_NAME);
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
            System.out.println(constant.OVER_COMPLETE);
            if (countOver < Over) {
                tossResult(tossWinner, teamChoice);
            }
        }
    }

    public void wide() {
        while (true) {
            try {
                System.out.print(constant.WIDE_BALL_OPTION);
                int choose = Integer.parseInt(scanner.nextLine());
                totalScore += 1;
                extras += 1;
                switch (choose) {
                    case 0:
                        System.out.println(constant.DOT_BALL);
                        break;
                    case 1:
                        System.out.println(constant.ONE_RUN);
                        totalScore += 1;
                        extras += 1;
                        swapPlayer(strikerName, nonStrikerName);
                        break;
                    case 2:
                        System.out.println(constant.TWO_RUN);
                        totalScore += 2;
                        extras += 2;
                        break;
                    case 3:
                        System.out.println(constant.THREE_RUN);
                        totalScore += 3;
                        extras += 3;
                        swapPlayer(strikerName, nonStrikerName);
                        break;
                    case 4:
                        System.out.println(constant.FOUR_RUN);
                        totalScore += 4;
                        extras += 4;
                        break;
                    default:
                        System.out.println(constant.INVALID_INPUT);
                        continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(constant.INVALID);
            }
        }
        totalDelivery += 1;
    }

    public void noBall() {
        while (true) {
            try {
                System.out.println(constant.NO_BALL_OPTION);
                int choose = Integer.parseInt(scanner.nextLine());
                totalScore += 1;
                extras += 1;
                switch (choose) {
                    case 0:
                        System.out.println(constant.DOT_BALL);
                        break;
                    case 1:
                        System.out.println(constant.ONE_RUN);
                        totalScore += 1;
                        extras += 1;
                        swapPlayer(strikerName, nonStrikerName);
                        break;
                    case 2:
                        System.out.println(constant.TWO_RUN);
                        totalScore += 2;
                        extras += 2;
                        break;
                    case 3:
                        System.out.println(constant.THREE_RUN);
                        totalScore += 3;
                        extras += 3;
                        swapPlayer(strikerName, nonStrikerName);
                        break;
                    case 4:
                        System.out.println(constant.FOUR_RUN);
                        totalScore += 4;
                        extras += 4;
                        break;
                    case 6:
                        System.out.println(constant.SIX_RUN);
                        totalScore += 6;
                        extras += 6;
                        break;
                    case 7:
                        System.out.println(constant.RUN_OUT);
                        totalWicket += 1;
                        while (true) {
                            System.out.print(constant.WHO_GOT_OUT);
                            int whoGotOut = Integer.parseInt(scanner.nextLine());
                            if (whoGotOut == 1) {
                                System.out.println(constant.STRIKER_OUT);
                                if (totalWicket <= 9) {
                                    wicket(strikerName, strikerScore, currentPlaying);
                                } else {
                                    break;
                                }
                            } else if (whoGotOut == 2) {
                                System.out.println(constant.NON_STRIKER_OUT);
                                if (totalWicket <= 9) {
                                    wicket(nonStrikerName, nonStrikerScore, currentPlaying);
                                } else {
                                    break;
                                }
                            } else {
                                System.out.println(constant.INVALID_INPUT_BATSMAN);
                            }
                        }
                        break;
                    default:
                        System.out.println(constant.INVALID_INPUT);
                        continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(constant.INVALID);
            }
        }
        totalDelivery += 1;
    }

    public void bye() {
        while (true) {
            try {
                System.out.print(constant.BYE_OPTION);
                int byeRun = Integer.parseInt(scanner.nextLine());
                switch (byeRun) {
                    case 0:
                        System.out.println(constant.DOT_BALL);
                        break;
                    case 1:
                        System.out.println(constant.ONE_RUN);
                        totalScore += 1;
                        extras += 1;
                        swapPlayer(strikerName, nonStrikerName);
                        break;
                    case 2:
                        System.out.println(constant.TWO_RUN);
                        totalScore += 2;
                        extras += 2;
                        break;
                    case 3:
                        System.out.println(constant.THREE_RUN);
                        totalScore += 3;
                        extras += 3;
                        swapPlayer(strikerName, nonStrikerName);
                        break;
                    case 4:
                        System.out.println(constant.FOUR_RUN);
                        totalScore += 4;
                        extras += 4;
                        break;
                    default:
                        System.out.println(constant.INVALID_INPUT);
                        continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(constant.INVALID);
            }
        }
        totalDelivery += 1;
    }

//    count runs in match
    public void countRuns(String player) {
        while (true) {
            try {
                System.out.print(constant.CHOOSE_BALL_OPTION);
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
                        System.out.println(constant.INVALID_INPUT);
                        continue;
                }
                break;
            } catch (Exception e) {
                System.out.println(constant.INVALID);
                scanner.nextLine();
            }
        }
    }

//    gives the next batting team
    boolean played;
    public void nextBatting(boolean toss) {
        if (tossWinner.equalsIgnoreCase(teamAName) && teamChoice.equalsIgnoreCase(constant.BATTING) && toss) {
            played = false;
        }
        if (tossWinner.equalsIgnoreCase(teamAName) && teamChoice.equalsIgnoreCase(constant.BOWLING) && !toss) {
            played = true;
        }
        if (tossWinner.equalsIgnoreCase(teamBName) && teamChoice.equalsIgnoreCase(constant.BATTING) && toss) {
            played = true;
        }
        if (tossWinner.equalsIgnoreCase(teamBName) && teamChoice.equalsIgnoreCase(constant.BOWLING) && !toss) {
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
//   print the playing11 of the teams
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
                    System.out.println(teamAName + constant.BATTING_TEAM);
                    for (int i = 0;i < playing11teamA.length;i++)
                    {
                        System.out.println((i+1)+". "+playing11teamA[i]);
                    }
                    System.out.println(teamBName + constant.BOWLING_TEAM);
                    for (int i = 0;i < playing11teamB.length;i++)
                    {
                        System.out.println((i+1)+". "+playing11teamB[i]);
                    }
                    break;
                case "bowling":
                    System.out.println(teamBName + constant.BATTING_TEAM);
                    for (int i = 0;i <playing11teamB.length;i++)
                    {
                        System.out.println((i+1)+". "+playing11teamB[i]);
                    }
                    System.out.println(teamAName + constant.BOWLING_TEAM);
                    for (int i = 0;i <playing11teamA.length;i++)
                    {
                        System.out.println((i+1)+". "+playing11teamA[i]);
                    }
                    break;
            }
        } else {
            switch (teamChoice.toLowerCase()) {
                case "batting":
                    System.out.println(teamBName + constant.BATTING_TEAM);
                    for (int i = 0;i <playing11teamB.length;i++)
                    {
                        System.out.println((i+1)+". "+playing11teamB[i]);
                    }
                    System.out.println(teamAName + constant.BOWLING_TEAM);
                    for (int i = 0;i <playing11teamA.length;i++)
                    {
                        System.out.println((i+1)+". "+playing11teamA[i]);
                    }
                    break;

                case "bowling":
                    System.out.println(teamAName + constant.BATTING_TEAM);
                    for (int i = 0;i <playing11teamA.length;i++)
                    {
                        System.out.println((i+1)+". "+playing11teamA[i]);
                    }
                    System.out.println(teamBName + constant.BOWLING_TEAM);
                    for (int i = 0;i <playing11teamB.length;i++)
                    {
                        System.out.println((i+1)+". "+playing11teamB[i]);
                    }
                    break;
            }
        }
    }

//  start the match between teams
    public void startMatch(){
        System.out.println(constant.START_MATCH);
        boolean toss;
        if (tossWinner.equals(teamAName)) {
            if (teamChoice.equalsIgnoreCase(constant.BATTING)) {
                System.out.println(constant.TEAM+ tossWinner);
                swapTeam(currentPlaying, playing11teamA);
                Batting(currentPlaying);
                Bowling(playing11teamB);
                teamPlayedTimes += 1;
                toss = true;
            } else {
                System.out.println(constant.TEAM+ tossWinner);
                swapTeam(currentPlaying, playing11teamB);
                Batting(currentPlaying);
                Bowling(playing11teamA);
                toss = false;
                teamPlayedTimes += 1;
            }
        } else {
            if (teamChoice.equalsIgnoreCase(constant.BATTING)) {
                System.out.println(constant.TEAM+ tossLooser);
                swapTeam(currentPlaying, playing11teamB);
                Batting(currentPlaying);
                Bowling(playing11teamA);
                teamPlayedTimes += 1;
                toss = true;
            } else {
                System.out.println(constant.TEAM+ tossLooser);
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
                    outBatsman[outPlayerIndex] = strikerName;
                    outBatsmanRun[outPlayerIndex] = strikerScore;
                    bowlerTakenWicket[outPlayerIndex] = constant.NOT_OUT;
                    outPlayerIndex++;
                    outBatsman[outPlayerIndex] = nonStrikerName;
                    outBatsmanRun[outPlayerIndex] = nonStrikerScore;
                    bowlerTakenWicket[outPlayerIndex] = constant.NOT_OUT;
                    outPlayerIndex++;

                    bowlerStat(bowlerName, bowlerRun, bowlerWicket);
                    totalScoreTeamA = totalScore;
                    System.out.println(tossWinner + constant.SCORED + totalScoreTeamA + "/" + totalWicket);
                    break;
                }
                System.out.println(constant.STRIKER + strikerName + "\t\t|\t\t" + constant.NON_STRIKER + nonStrikerName + "\n" +
                        constant.BOWLER + bowlerName + "\t\t\t" + constant.OVER + countOver + "." + balls + "/" + Over + "\t\t\t" +
                        constant.EXTRAS + extras + "\n" + tossWinner + "  " + totalScore + "/" + totalWicket + "\t\t\t" +
                        strikerName + "  " + strikerScore + "\t\t\t" + nonStrikerName + "  " + nonStrikerScore + "\n");
                if (countOver < Over && totalWicket < 10) {
                    countRuns(strikerName);
                    if (Inning ==1 && runsLeftTeamB <= totalScoreTeamB) {
                        System.out.println(constant.TEAM + tossWinner + " require " + (totalScoreTeamB - totalScore + 1) + " to win");
                        runsLeftTeamB = totalScore;
                    }
                }
                if (countOver == Over || totalWicket == 10) {
                    totalScoreTeamA = totalScore;
                    System.out.println(tossWinner + constant.SCORED + totalScoreTeamA + "/" + totalWicket);
                    outBatsman[outPlayerIndex] = strikerName;
                    outBatsmanRun[outPlayerIndex] = strikerScore;
                    bowlerTakenWicket[outPlayerIndex] = constant.NOT_OUT;
                    outPlayerIndex++;
                    outBatsman[outPlayerIndex] = nonStrikerName;
                    outBatsmanRun[outPlayerIndex] = nonStrikerScore;
                    bowlerTakenWicket[outPlayerIndex] = constant.NOT_OUT;
                    outPlayerIndex++;
                    System.out.println("\n"+constant.INNING_COMPLETE + "\n\n");
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
                            teamChoice = constant.BOWLING;
                        } else {
                            swapTeam(currentPlaying, playing11teamB);
                            Batting(currentPlaying);
                            Bowling(playing11teamA);
                            teamPlayedTimes += 1;
                            teamChoice = constant.BOWLING;
                        }
                        Inning += 1;
                        resetInningVariable();
                    }
                }
            } else {
                if (runsLeftTeamA > totalScoreTeamA & Inning == 1) {
                    outBatsman[outPlayerIndex] = strikerName;
                    outBatsmanRun[outPlayerIndex] = strikerScore;
                    bowlerTakenWicket[outPlayerIndex] = constant.NOT_OUT;
                    outPlayerIndex++;
                    outBatsman[outPlayerIndex] = nonStrikerName;
                    outBatsmanRun[outPlayerIndex] = nonStrikerScore;
                    bowlerTakenWicket[outPlayerIndex] = constant.NOT_OUT;
                    outPlayerIndex++;
                    bowlerStat(bowlerName, bowlerRun, bowlerWicket);
                    totalScoreTeamB = totalScore;
                    System.out.println(tossLooser + constant.SCORED + totalScoreTeamB + "/" + totalWicket);
                    break;
                }
                System.out.println(constant.STRIKER + strikerName + "\t\t|\t\t" + constant.NON_STRIKER + nonStrikerName + "\n" +
                        constant.BOWLER + bowlerName + "\t\t\t" + constant.OVER + countOver + "." + balls + "/" + Over + "\t\t\t" +
                        constant.EXTRAS + extras + "\n" + tossLooser + "  " + totalScore + "/" + totalWicket + "\t\t\t" +
                        strikerName + "  " + strikerScore + "\t\t\t" + nonStrikerName + "  " + nonStrikerScore+"\n");
                if (countOver < Over && totalWicket < 10) {
                    countRuns(strikerName);
                    if(Inning == 1 && runsLeftTeamA <= totalScoreTeamA) {
                        System.out.println(constant.TEAM + tossLooser + " require " + (totalScoreTeamA - totalScore + 1) + " to win" );
                        runsLeftTeamA = totalScore;
                    }
                }
                if (countOver == Over || totalWicket == 10) {
                    totalScoreTeamB = totalScore;
                    System.out.println(tossLooser + constant.SCORED + totalScoreTeamB + "/" + totalWicket);
                    runsLeftTeamB = totalScoreTeamB;
                    outBatsman[outPlayerIndex] = strikerName;
                    outBatsmanRun[outPlayerIndex] = strikerScore;
                    bowlerTakenWicket[outPlayerIndex] = constant.NOT_OUT;
                    outPlayerIndex++;
                    outBatsman[outPlayerIndex] = nonStrikerName;
                    outBatsmanRun[outPlayerIndex] = nonStrikerScore;
                    bowlerTakenWicket[outPlayerIndex] = constant.NOT_OUT;
                    outPlayerIndex++;
                    System.out.println(constant.INNING_COMPLETE + "\n\n\n\n");
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
                            teamChoice = constant.BATTING;
                        } else {
                            swapTeam(currentPlaying, playing11teamB);
                            Batting(currentPlaying);
                            Bowling(playing11teamA);
                            teamPlayedTimes += 1;
                            teamChoice = constant.BATTING;
                        }
                        Inning += 1;
                        resetInningVariable();
                    }
                }
            }
        }
        if (totalScoreTeamA > totalScoreTeamB) {
            System.out.println(constant.WINNER_IS + teamAName + " by " + (totalScoreTeamA - totalScoreTeamB) + " runs.\n");
        } else if (totalScoreTeamA < totalScoreTeamB) {
            System.out.println(constant.WINNER_IS + teamBName + " by " + (totalScoreTeamB - totalScoreTeamA) + " runs.\n");
        } else {
            System.out.println(constant.TIE + totalScoreTeamA + " runs.\n");
        }
    }


    static int teamPlayedTimes = 0;
    public static void main(String[] args) {
        cricketScoreBoard cricket = new cricketScoreBoard();
        boolean isValid = true;
        while (isValid) {
            System.out.print(constant.CHOOSE_CASE);
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
                    case 8:
                        System.out.println(constant.EXITING);
                        isValid = false;
                        break;
                    default:
                        System.out.println(constant.INVALID_OPTION);
                }
            } catch (Exception e) {
                System.out.println(constant.ERROR_OCCURRED + e.getMessage());
                scanner.nextLine();
            }
        }
    }
}
