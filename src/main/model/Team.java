package model;

import java.util.ArrayList;

public class Team {

    //field
    private final String teamName;
    private final ArrayList<Match> matchList = new ArrayList<>();
    private final ArrayList<String> matchDateList = new ArrayList<>();
    private Match match;

    //constructor
    public Team(String teamName) {
        this.teamName = teamName;
        match = null;
    }

    //methods
    //REQUIREMENTS: result should be one of 'w', 'l' and 'n'
    //MODIFIES: this
    //EFFECTS: add Match to the selected date
    public void addMatch(String date, double time, String oppTeam, boolean booking, String result, String impression) {

        match = new Match(date);

        match.setTime(time);
        match.setOpposingTeam(oppTeam);
        match.setBooking(booking);
        match.setResult(result);
        match.setImpression(impression);
    }

    //MODIFIES: this
    //EFFECTS: add match to matchList and matchDataList
    public void matchToList() {
        matchList.add(match);
        matchDateList.add(match.getDate());
    }

    //EFFECTS: bring recorded match with a selected date
    public int recordedMatch(String date) {
        if (matchDateList.contains(date)) {
            for (Match i : matchList) {
                if (i.getDate().equals(date)) {
                    return matchList.indexOf(i);
                }
            }
        }

        return -1;
    }

    //EFFECTS: find all matches that booking is true, add them to a string and return the string
    public String allBookedMatch() {
        StringBuilder string = new StringBuilder();
        for (Match i : matchList) {
            if (i.isBooking()) {
                string.append(i.getDate()).append("\n");
            }
        }
        return string.toString();
    }

    //EFFECTS: return name of the team
    public String getTeamName() {
        return teamName;
    }

    //EFFECTS: return the list of matches
    public ArrayList<Match> getMatchList() {
        return matchList;
    }

    //EFFECTS: return the list of match days
    public ArrayList<String> getMatchDateList() {
        return matchDateList;
    }

    //EFFECTS: return the match
    public Match getMatch() {
        return match;
    }
}
