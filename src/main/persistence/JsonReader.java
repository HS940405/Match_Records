package persistence;

import model.Team;
import model.TeamList;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads teams from JSON data stored in file
public class JsonReader {
    private final String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads team from file and returns it, or throws IOException if an error occurs
    public TeamList readTeams() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTeam(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    private java.lang.String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses team from JSON object and returns it
    private TeamList parseTeam(JSONObject jsonObject) {
        TeamList teamList = new TeamList();
        addTeams(teamList, jsonObject);
        return teamList;
    }

    //MODIFIES: teamList
    //EFFECTS: parses teams from JSON object and add them to teamList
    private void addTeams(TeamList teamList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("teamList");
        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            addTeam(teamList, nextTeam);
        }
    }

    //MODIFIES: teamList
    //EFFECTS: parses a team from JSON object and add it to teamList
    private void addTeam(TeamList teamList, JSONObject jsonObject) {
        String name = jsonObject.getString("teamName");
        Team team = new Team(name);
        addMatches(team, jsonObject);
        teamList.addTeam(team);
    }


    //MODIFIES: team
    //EFFECTS: parses matches from JSON object and add them to team
    private void addMatches(Team team, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("matchList");
        for (Object json : jsonArray) {
            JSONObject nextMatch = (JSONObject) json;
            addMatch(team, nextMatch);
        }
    }

    // MODIFIES: team
    // EFFECTS: parses a match from JSON object and adds it to team
    private void addMatch(Team team, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        double time = jsonObject.getDouble("time");
        String oppTeam = jsonObject.getString("opposingTeam");
        boolean booking = jsonObject.getBoolean("booking");
        String result = jsonObject.getString("result");
        String impression = jsonObject.getString("impression");
        team.addMatch(date, time, oppTeam, booking, result, impression);
    }


}
