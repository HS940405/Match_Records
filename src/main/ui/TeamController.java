package ui;

import model.Team;
import model.TeamList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

//control the team in UI
public class TeamController {

    //fields
    protected static final String JSON_STORE = "./data/team.json";
    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;
    protected TeamList teamList;

    //constructor
    //EFFECTS: create TeamController
    public TeamController() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        loadTeamList();
    }

    //EFFECTS: load the teams from the stored data
    public void loadTeamList() {
        try {
            teamList = jsonReader.readTeams();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: (data in) JSON_STORE
    //EFFECTS: save the current teams and matches to the JSON_STORE
    public void saveTeamList() {
        try {
            jsonWriter.open();
            jsonWriter.write(teamList);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS: return teamList
    public TeamList getTeamList() {
        return teamList;
    }

    //EFFECTS: find team matches a given team name
    public Team findTeam(String teamName) {
        if (teamList != null) {
            for (Team team : teamList.getTeamList()) {
                if (teamName.equals(team.getTeamName())) {
                    return team;
                }
            }
        }
        return null;
    }

    //EFFECTS: add team to the current teamList
    public void addTeam(Team team) {
        if (team != null) {
            teamList.addTeam(team);
        }
    }
}
