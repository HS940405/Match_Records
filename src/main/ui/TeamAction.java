package ui;

import model.Match;
import model.Team;

import java.util.ArrayList;
import java.util.Scanner;

public class TeamAction {

    //fields
    private String selectedDate;
    private final ArrayList<Team> teamList = new ArrayList<>();
    private Scanner input = new Scanner(System.in);


    //constructor
    public TeamAction() {
        start();
    }

    //methods
    //REQUIRES: user input must be either "s" or "c"
    //EFFECTS: display menu, get user input and call corresponding methods(selectTeam for "s", createTeam for "c")
    public void start() {
        System.out.println("Select Team or Create Team by typing 's' or 'c'");
        String command = input.next();
        System.out.println(command);

        if (command.equals("c")) {
            createTeam();
        } else if (command.equals("s")) {
            selectTeam();
        } else {
            System.out.println("Not valid Command. Please select 's' or 'c'");
        }
    }

    //REQUIRES: teamName must be in one word
    //MODIFIES: this
    //EFFECTS: instantiate Team and add it to teamList
    public void createTeam() {
        System.out.println("Team name(Team name should be in one word without spacing) : ");
        String teamName = input.next();
        Team team = new Team(teamName);
        teamList.add(team);
        String string = String.format("Team %s is created!", teamName);
        System.out.println(string);
        input = new Scanner(System.in);
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
            String selectedTeam = input.next();
            input = new Scanner(System.in);
            for (Team i : teamList) {
                if (i.getTeamName().equals(selectedTeam)) {
                    runTeam(i);
                    break;
                }
            }

            String string = String.format("There's no team called %s. Please choose another team.", selectedTeam);
            System.out.println(string);
            selectTeam();
        }
    }

    //REQUIRES: user input must be one of "a", "c" and "b"
    //EFFECTS: display menu, get user input and call corresponding methods for the input
    public void runTeam(Team team) {
        System.out.println("Select from:\n a -> add match\n c -> check match\n b -> check date that booked tickets");
        String command = input.next();
        switch (command) {
            case "a":
                addMatch(team);
                break;
            case "c":
                checkMatch(team);
                break;
            case "b":
                checkBooked(team);
                break;
            default:
                System.out.println("Not valid Command. Please select 's' or 'c'");
                runTeam(team);
                break;
        }
    }

    //REQUIRES: user inputs must be without any spaces
    //MODIFIES: Team
    //EFFECTS: get information of a match from user and create a match with given info.
    public void addMatch(Team team) {
        System.out.println("Please input a date in a form of 'year-month-day'");
        selectedDate = input.next();
        System.out.println("Please input the match time in 4 numbers. ex) 5pm -> 1700");
        int time = Integer.parseInt(input.next());
        System.out.println("Please input the opposing team name without any spaces.");
        String oppTeam = input.next();
        input = new Scanner(System.in);
        System.out.println("Please input 'y' if you booked ticket for the match. Otherwise, input 'n'");
        boolean booking = input.next().equals("y");
        System.out.println("Please input the result.\n win -> w\n lose -> l\n not yet decided -> n");
        String result = input.next();
        System.out.println("Please input the impression if you have without any spaces. If not, please press 'n'");
        String impression = input.next();
        team.addMatch(selectedDate, time, oppTeam, booking, result, impression);
        team.matchToList();
        System.out.println("The match added!");
        input = new Scanner(System.in);

        runTeam(team);
    }

    //REQUIRES: user inputs must be without any spaces
    //EFFECTS: get user input of a data, find match with given data, and display the match's info if exists
    public void checkMatch(Team team) {
        System.out.println("Please input a date in a form of 'year-month-day'");
        selectedDate = input.next();
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

}
