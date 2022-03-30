package model;

import java.util.ArrayList;

//Creates a team containing number of matches
public class Team {

    //fields
    private final String teamName;
    private final MatchList matchList;
    private final ArrayList<String> matchDateList = new ArrayList<>();

    //constructor
    //EFFECTS: create a new team with given name
    public Team(String teamName) {
        this.teamName = teamName;
        matchList = new MatchList();
    }

    //methods
    //REQUIREMENTS: result should be one of 'w', 'l' and 'n'
    //MODIFIES: this
    //EFFECTS: add Match to the selected date
    public void addMatch(String date, double time, String oppTeam, boolean booking, String result, String impression) {

        Match match = new Match(date);

        match.setTime(time);
        match.setOpposingTeam(oppTeam);
        match.setBooking(booking);
        match.setResult(result);
        match.setImpression(impression);

        matchList.addMatch(match);
        matchDateList.add(date);
        EventLog.getInstance().logEvent(new Event("Added a match on " + date + " of Team " + teamName));
    }

    //EFFECTS: bring recorded match with a selected date
    public int recordedMatch(String date) {
        if (matchDateList.contains(date)) {
            for (Match i : matchList.getMatchList()) {
                if (i.getDate().equals(date)) {
                    return matchList.getMatchList().indexOf(i);
                }
            }
        }
        return -1;
    }

    //EFFECTS: find all matches that booking is true, add them to a string and return the string
    public String allBookedMatch() {
        EventLog.getInstance().logEvent(new Event("Checked all booked matches of Team " + teamName));
        StringBuilder string = new StringBuilder();
        for (Match i : matchList.getMatchList()) {
            if (i.isBooking()) {
                string.append(i.getDate()).append("\n");
            }
        }
        return string.toString();
    }

    //REQUIRES: user inputs must be without any spaces
    // EFFECTS: get user input of a data, find match with given data, and display the match's info if exists
    public String checkMatch(String date) {
        EventLog.getInstance().logEvent(new Event("Checked match on " + date + " of Team " + teamName));
        if (recordedMatch(date) == -1) {
            return "There is no match on the selected date";
        } else {
            Match onTheDay = getMatchList().get(recordedMatch(date));
            String info = "\nMatch Info on " + date + "\nTime: " + onTheDay.getTime()
                    + "\nOpposing Team: " + onTheDay.getOpposingTeam() + "\nBooking: " + onTheDay.isBooking()
                    + "\nResult: " + onTheDay.getResult() + "\nImpression: " + onTheDay.getImpression();
            System.out.println(info);
            return info;
        }
    }

    //EFFECTS: count number of booked matches in the team
    public int countBooking() {
        int count = 0;
        for (Match i : matchList.getMatchList()) {
            if (i.isBooking()) {
                count += 1;
            }
        }
        return count;
    }

    //EFFECTS: return name of the team
    public String getTeamName() {
        return teamName;
    }

    //EFFECTS: return the list of matches
    public ArrayList<Match> getMatchList() {
        return matchList.getMatchList();
    }

    //EFFECTS: return the list of match days
    public ArrayList<String> getMatchDateList() {
        return matchDateList;
    }

    //MODIFIES: this
    //EFFECTS: add given date to matchDateList
    public void setMatchDateList(String date) {
        matchDateList.add(date);
    }

    /*
    @Override
    //EFFECTS: returns team as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("teamName", teamName);
        json.put("matchList", matchesToJson());
        return json;
    }

    //EFFECTS: returns things in this team as a JSON array
    private JSONArray matchesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Match match : matchList.getMatchList()) {
            jsonArray.put(match.toJson());
        }

        return jsonArray;
    }

     */
}
