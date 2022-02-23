package persistence;

import model.MatchList;
import model.TeamList;
import org.json.JSONObject;

import java.io.*;

//Represents a writer that writes JSON representation of team to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs writer to write data to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens writer; throws FileNotFoundException if destination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of team to file
    public void write(TeamList teamList) {
        JSONObject json = teamList.toJson();
        saveToFile(json.toString(TAB));
    }

    public void writeMatchList(MatchList matchList) {
        JSONObject json = matchList.toJson();
        saveToFile(json.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    //MODIFIES: this
    //EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
