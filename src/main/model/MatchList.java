package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Records matches in each team
public class MatchList implements Writable {

    //fields
    private String name;
    private ArrayList<Match> matchList = new ArrayList<>();

    //constructor
    //EFFECTS: constructs a teamList
    public MatchList(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: add given match to the match list
    public void addMatch(Match match) {
        matchList.add(match);
    }

    //EFFECTS: return boolean showing whether the matchList is empty
    public boolean isEmpty() {
        return matchList.isEmpty();
    }

    //EFFECTS: return matchList
    public ArrayList<Match> getMatchList() {
        return matchList;
    }

    @Override
    //EFFECTS: returns match list as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("matchList", matchList);
        return json;
    }
}
