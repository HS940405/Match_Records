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

    public void createTeam() {
        System.out.println("Team name: ");
        String teamName = input.next();
        Team team = new Team(teamName);
        teamList.add(team);
        String string = String.format("Team %s is created!", teamName);
        System.out.println(string);
        start();
    }

    public void selectTeam() {
        if (teamList.isEmpty()) {
            System.out.println("There's no team created. Please create a team first.");
            start();
        } else {
            System.out.println("Please input team name.");
            String selectedTeam = input.next();
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

    public void addMatch(Team team) {
        System.out.println("Please input a date in a form of 'year-month-day'");
        selectedDate = input.next();
        System.out.println("Please input the match time in 4 numbers. ex) 5pm -> 1700");
        int time = Integer.parseInt(input.next());
        System.out.println("Please input the opposing team name.");
        String oppTeam = input.next();
        System.out.println("Please input 'y' if you booked ticket for the match. Otherwise, input 'n'");
        boolean booking = input.next().equals("y");
        System.out.println("Please input the result.\n win -> w\n lose -> l\n not yet decided -> n");
        String result = input.next();
        System.out.println("Please input the impression if you have. If not, please press 'n'");
        String impression = input.next();
        team.addMatch(selectedDate, time, oppTeam, booking, result, impression);
        team.matchToList();
        System.out.println("The match added!");
        input = new Scanner(System.in);

        runTeam(team);
    }

    public void checkMatch(Team team) {
        System.out.println("Please input a date in a form of 'year-month-day'");
        selectedDate = input.next();
        if (team.recordedMatch(selectedDate).getDate().equals("null")) {
            System.out.println("There is no match on the selected date");
        } else {
            Match onTheDay = team.recordedMatch(selectedDate);
            String info = "Match Info on " + selectedDate + "\nTime: " + onTheDay.getTime()
                    + "\nOpposing Team: " + onTheDay.getOpposingTeam() + "\nBooking: " + onTheDay.isBooking()
                    + "\nResult: " + onTheDay.getResult() + "\nImpression: " + onTheDay.getImpression();
            System.out.println(info);
        }
        runTeam(team);
    }

    public void checkBooked(Team team) {
        System.out.println(team.allBookedMatch());

        runTeam(team);
    }

}
