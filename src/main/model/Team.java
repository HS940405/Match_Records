package model;

import java.util.ArrayList;

public class Team {

    //field
    private final String teamName;
    private final ArrayList<Match> matchList = new ArrayList<>();
    private final ArrayList<String> matchDateList = new ArrayList<>();
    private Match match;

    //constructor
    //EFFECTS: create a Team instance
    public Team(String teamName) {
        this.teamName = teamName;
        match = null;
    }

    //methods
    //REQUIREMENTS: result should be one of 'w', 'l' and 'n'
    //MODIFIES: match
    //EFFECTS: Add Match to the selected date
    public void addMatch(String date, int time, String oppTeam, boolean booking, String result, String impression) {

        match = new Match(date);

        match.setTime(time);
        match.setOpposingTeam(oppTeam);
        match.setBooking(booking);
        match.setResult(result);
        match.setImpression(impression);
    }

    public void matchToList() {
        matchList.add(match);
        matchDateList.add(match.getDate());
    }

    //EFFECTS: Bring recorded match to the selected date
    public Match recordedMatch(String date) {
        if (matchDateList.contains(date)) {
            for (Match i : matchList) {
                if (i.getDate().equals(date)) {
                    return i;
                }
            }
        }

        return new Match("null");
    }

    //EFFECTS: check all booked match
    public String allBookedMatch() {
        StringBuilder string = new StringBuilder();
        for (Match i : matchList) {
            if (i.isBooking()) {
                string.append(i.getDate()).append("\n");
            }
        }
        return string.toString();
    }

    //EFFECTS: Return name of the team
    public String getTeamName() {
        return teamName;
    }

    //EFFECTS: Return the list of matches
    public ArrayList<Match> getMatchList() {
        return matchList;
    }

    //EFFECTS: Return the list of match days
    public ArrayList<String> getMatchDateList() {
        return matchDateList;
    }

    //EFFECTS: Return the match
    public Match getMatch() {
        return match;
    }
}
