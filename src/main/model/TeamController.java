package model;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TeamController {

    protected static final String JSON_STORE = "./data/team.json";
    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;
    protected TeamList teamList;

    public TeamController() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        loadTeamList();
    }

    public void loadTeamList() {
        try {
            teamList = jsonReader.readTeams();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public void saveTeamList() {
        try {
            jsonWriter.open();
            jsonWriter.write(teamList);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    public TeamList getTeamList() {
        return teamList;
    }

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

    public void addTeam(Team team) {
        if (team != null) {
            teamList.addTeam(team);
        }
    }
}
