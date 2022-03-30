package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Records teams
public class TeamList implements Writable {

    //fields
    private final ArrayList<Team> teamList;
    private final ArrayList<String> teamNames;

    //constructor
    //EFFECTS: constructs a teamList
    public TeamList() {
        teamList = new ArrayList<>();
        teamNames = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add given team to teamList and teamNames
    public void addTeam(Team team) {
        teamList.add(team);
        teamNames.add(team.getTeamName());
        EventLog.getInstance().logEvent(new Event("Added a Team " + team.getTeamName()));
    }

    //EFFECTS: return true if the teamList is empty; false otherwise
    public boolean isEmpty() {
        return teamList.isEmpty();
    }

    //EFFECTS: return teamList
    public ArrayList<Team> getTeamList() {
        return teamList;
    }

    //EFFECTS: return teamNames
    public ArrayList<String> getTeamNames() {
        return teamNames;
    }

    @Override
    //EFFECTS: returns team list as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("teamList", teamList);
        json.put("teamNames", teamNames);
        return json;
    }

}
