import java.util.Scanner;

public class week3Assignment {
    static int teamCounter = 0;
    static int playerCounter = 0;
    static String[] totalTeamName = new String[100];
    static String[] totalPlayerName = new String[1000];
    static int[] totalPlayerInTeam = new int[100];
    static String[] playing11teamA = new String[11];
    static String[] playing11teamB = new String[11];

    static Scanner scanner = new Scanner(System.in);

    public static boolean isUnique(String[] array, String value, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            if (array[i] != null && array[i].equalsIgnoreCase(value)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        int length = name.length();
        int index = 0;
        while (index < length) {
            char c = name.charAt(index);
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
            index++;
        }
        return true;
    }

    public void setTeamName(Scanner scan) {
        System.out.println("Enter Team name (or type 'exit' to stop adding team): ");
        while (true) {
            String nameOfTeam = scan.nextLine();
            if ("exit".equalsIgnoreCase(nameOfTeam)) {
                break;
            }
            if (isUnique(totalTeamName, nameOfTeam, 0, teamCounter)) {
                totalTeamName[teamCounter] = nameOfTeam;
                teamCounter++;
                break;
            } else {
                System.out.println("Team name already exists!\nEnter new name of team: ");
            }
        }
    }

    public void setPlayerName(Scanner scan, int teamIndex) {
        System.out.println("Enter the player name (or type 'exit' to stop adding players to the team): ");
        int countPlayerInTeam = 0;
        int playerStartIndex = playerCounter;

        while (true) {
            System.out.print("Player " + (countPlayerInTeam + 1) + ": ");
            String nameOfPlayer = scan.nextLine();
            if ("exit".equalsIgnoreCase(nameOfPlayer)) {
                if (countPlayerInTeam < 11) {
                    System.out.println("Player count is less than 11. Please add more players.");
                    continue;
                }
                totalPlayerInTeam[teamIndex] = countPlayerInTeam;
                System.out.println("Exiting player input for team " + totalTeamName[teamIndex]);
                break;
            }
            if (!isValidName(nameOfPlayer)) {
                System.out.println("Invalid name! Please enter a valid player name (alphanumeric only):");
                continue;
            }
            if (isUnique(totalPlayerName, nameOfPlayer, playerStartIndex, playerCounter)) {
                totalPlayerName[playerCounter] = nameOfPlayer;
                playerCounter++;
                countPlayerInTeam++;
            } else {
                System.out.println("Player name already exists in this team!\nEnter new name of player: ");
            }
        }
    }

    public void teamsSet() {
        while (true) {
            System.out.print("To create team press any key (or type 'exit' to stop adding teams): ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("exit")) {
                setTeamName(scanner);
                if (teamCounter > 0) {
                    setPlayerName(scanner, teamCounter - 1);
                }
            } else {
                break;
            }
        }
    }

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

    public void printTeamNames() {
        System.out.println("Available Teams:");
        for (int i = 0; i < teamCounter; i++) {
            System.out.println((i + 1) + ": " + totalTeamName[i]);
        }
        System.out.println();
    }

    public void printTeamMembers(int teamIndexNumber) {
        int startIdx = getStartIndexForTeam(teamIndexNumber);
        int endIdx = startIdx + totalPlayerInTeam[teamIndexNumber];

        System.out.println("Player of Team " + totalTeamName[teamIndexNumber] + ":");
        for (int i = startIdx; i < endIdx; i++) {
            System.out.println((i - startIdx + 1) + ". " + totalPlayerName[i]);
        }
    }

    private int getStartIndexForTeam(int teamIndex) {
        int startIdx = 0;
        for (int i = 0; i < teamIndex; i++) {
            startIdx += totalPlayerInTeam[i];
        }
        return startIdx;
    }

    public void selectPlaying11Team() {
        printTeamNames();
        int teamAIndex = -1;

        while (teamAIndex == -1) {
            System.out.println("Select the team for Team A (enter the team number): ");
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Input cannot be empty. Please enter a valid number.");
                    continue;
                }
                int teamA = Integer.parseInt(input);
                if (teamA < 1 || teamA > teamCounter) {
                    System.out.println("Invalid team number for Team A. Please try again.");
                } else {
                    teamAIndex = teamA - 1;
                    printTeamMembers(teamAIndex);
                    choosePlaying11Player(teamAIndex, playing11teamA);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        while (true) {
            printTeamNames();
            System.out.println("Select the team for Team B (enter the team number): ");
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Input cannot be empty. Please enter a valid number.");
                    continue;
                }
                int teamB = Integer.parseInt(input);
                if (teamB < 1 || teamB > teamCounter) {
                    System.out.println("Invalid team number for Team B. Please try again.");
                    continue;
                }
                if (teamB - 1 == teamAIndex) {
                    System.out.println("The team selected for Team B is the same as Team A. Please select a different team for Team B.");
                    continue;
                }
                int teamBIndex = teamB - 1;
                printTeamMembers(teamBIndex);
                choosePlaying11Player(teamBIndex, playing11teamB);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public void choosePlaying11Player(int teamIndex, String[] playing11) {
        int indexOfPlayer = 0;
        for (int i = 0; i < teamIndex; i++) {
            indexOfPlayer += totalPlayerInTeam[i];
        }

        System.out.println("Select 11 players for " + totalTeamName[teamIndex]);
        int selectedPlayers = 0;
        boolean[] isPlayerSelected = new boolean[totalPlayerInTeam[teamIndex]];
        while (selectedPlayers < 11) {
            boolean validInput = false;
            int playerChoice = -1;
            while (!validInput) {
                System.out.print("Enter the serial number of player " + (selectedPlayers + 1) + ": ");
                String input = scanner.nextLine();
                try {
                    playerChoice = Integer.parseInt(input);
                    if (playerChoice < 1 || playerChoice > totalPlayerInTeam[teamIndex]) {
                        System.out.println("Invalid choice. Please choose a valid player.");
                    } else if (isPlayerSelected[playerChoice - 1]) {
                        System.out.println("Player already selected! Please choose another player.");
                    } else {
                        validInput = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number only.");
                }
            }
            int playerIndex = indexOfPlayer + playerChoice - 1;
            playing11[selectedPlayers] = totalPlayerName[playerIndex];
            isPlayerSelected[playerChoice - 1] = true;
            selectedPlayers++;
        }
    }

    public static void main(String[] args) {
        teams obj = new teams();
        obj.teamsSet();
        obj.viewTotalTeam();
        obj.selectPlaying11Team();
    }
}
