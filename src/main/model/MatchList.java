package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Records matches in each team
public class MatchList {

    //fields
    private final ArrayList<Match> matchList;

    //constructor
    //EFFECTS: constructs a teamList
    public MatchList() {
        matchList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add given match to the match list
    public void addMatch(Match match) {
        matchList.add(match);
    }

    //EFFECTS: return matchList
    public ArrayList<Match> getMatchList() {
        return matchList;
    }

    /*
    @Override
    //EFFECTS: returns match list as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("matchList", matchList);
        return json;
    }

     */
}
