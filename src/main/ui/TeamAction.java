package ui;

import model.Match;
import model.Team;
import model.TeamList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Make the console works for team
public class TeamAction {

    //fields
    private static final String JSON_STORE = "./data/team.json";
    private String selectedDate;
    private TeamList teamList;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private boolean keepGoing;
    private boolean teamGoing;

    //constructor
    //EFFECTS: call start function to start the console program
    public TeamAction() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        teamList = new TeamList();
        loadTeam();
        start();
    }

    //method
    //REQUIRES: user input must be either "s" or "c"
    //EFFECTS: display menu, get user input and call corresponding methods(selectTeam for "s", createTeam for "c")
    public void start() {
        keepGoing = true;
        String command;

        while (keepGoing) {
            System.out.println("Select Team or Create Team by typing 's' or 'c'. Otherwise, type 'q' for quit.");
            Scanner input = new Scanner(System.in);
            command = input.next();
            System.out.println(command);

            if (command.equals("c")) {
                createTeam();
            } else if (command.equals("s")) {
                loadTeam();
                selectTeam();
            } else if (command.equals("q")) {
                keepGoing = false;
                System.out.println(teamList);
                saveTeam();
            } else {
                System.out.println("Not valid Command. Please select 's' or 'c'");
            }
        }

        System.out.println("\nGoodbye!");
    }

    //REQUIRES: teamName must be in one word
    //MODIFIES: this
    //EFFECTS: instantiate Team and add it to teamList
    public void createTeam() {
        System.out.println("Team name(Team name should be in one word without spacing) : ");
        Scanner teamInput = new Scanner(System.in);
        String teamName = teamInput.next();
        Team team = new Team(teamName);
        teamList.addTeam(team);
        saveTeam();
        String string = String.format("Team %s is created!", teamName);
        System.out.println(string);
        start();
    }

    //REQUIRES: user input must be in one word
    //EFFECTS: get user input, find Team with given name and call runTeam() if in list; otherwise call selectTeam again
    public void selectTeam() {
        if (teamList.isEmpty()) {
            System.out.println("There's no team created. Please create a team first.");
            start();
        } else {
            System.out.println("Please input team name.");
            Scanner selectTeamInput = new Scanner(System.in);
            String selectedTeam = selectTeamInput.next();
            if (teamList.getTeamNames().contains(selectedTeam)) {
                for (Team i : teamList.getTeamList()) {
                    if (i.getTeamName().equals(selectedTeam)) {
                        runTeam(i);
                        break;
                    }
                }
            } else {
                String string = String.format("There's no team called %s. Please choose another team.", selectedTeam);
                System.out.println(string);
                selectTeam();
            }
        }
    }

    //REQUIRES: user input must be one of "a", "c", "b" and "q"
    //EFFECTS: display menu, get user input and call corresponding methods for the input
    public void runTeam(Team team) {
        teamGoing = true;
        System.out.println("Select from:\n a -> add match\n c -> check match\n b -> check booked tickets\n q -> quit");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();
        System.out.println(command);

        while (teamGoing) {
            if (command.equals("a")) {
                addMatch(team);
            } else if (command.equals("c")) {
                checkMatch(team);
            } else if (command.equals("b")) {
                checkBooked(team);
            } else if (command.equals("q")) {
                teamGoing = false;
                start();
            } else {
                System.out.println("Not valid Command.");
                runTeam(team);
            }
        }
    }

    //REQUIRES: user inputs must be without any spaces
    //MODIFIES: Team
    //EFFECTS: get information of a match from user and create a match with given info.
    public void addMatch(Team team) {
        Scanner matchInput = new Scanner(System.in);
        System.out.println("Please input a date in a form of 'year-month-day'");
        selectedDate = matchInput.next();
        System.out.println("Please input the match time in 4 numbers. ex) 5pm -> 1700");
        double time = Double.parseDouble(matchInput.next());
        System.out.println("Please input the opposing team name without any spaces.");
        String oppTeam = matchInput.next();
        System.out.println("Please input 'y' if you booked ticket for the match. Otherwise, input 'n'");
        boolean booking = matchInput.next().equals("y");
        System.out.println("Please input the result.\n win -> w\n lose -> l\n not yet decided -> n");
        String result = matchInput.next();
        System.out.println("Please input the impression if you have without any spaces. If not, please press 'n'");
        String impression = matchInput.next();
        team.addMatch(selectedDate, time, oppTeam, booking, result, impression);
        System.out.println("The match added!");

        runTeam(team);
    }

    //REQUIRES: user inputs must be without any spaces
    //EFFECTS: get user input of a data, find match with given data, and display the match's info if exists
    public void checkMatch(Team team) {
        System.out.println("Please input a date in a form of 'year-month-day'");
        Scanner selectDateInput = new Scanner(System.in);
        selectedDate = selectDateInput.next();
        if (team.recordedMatch(selectedDate) == -1) {
            System.out.println("There is no match on the selected date");
        } else {
            Match onTheDay = team.getMatchList().get(team.recordedMatch(selectedDate));
            String info = "\nMatch Info on " + selectedDate + "\nTime: " + onTheDay.getTime()
                    + "\nOpposing Team: " + onTheDay.getOpposingTeam() + "\nBooking: " + onTheDay.isBooking()
                    + "\nResult: " + onTheDay.getResult() + "\nImpression: " + onTheDay.getImpression();
            System.out.println(info);
        }
        runTeam(team);
    }

    //EFFECTS: print all matches that booking is true
    public void checkBooked(Team team) {
        System.out.println(team.allBookedMatch());

        runTeam(team);
    }

    //MODIFIES: JSON_STORE
    //EFFECTS: saves the team to file
    private void saveTeam() {
        try {
            jsonWriter.open();
            jsonWriter.write(teamList);
            jsonWriter.close();
            System.out.println("Saved data");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads team from file
    private void loadTeam() {
        try {
            teamList = jsonReader.readTeams();
            System.out.println("Loaded Team List " + " from" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
