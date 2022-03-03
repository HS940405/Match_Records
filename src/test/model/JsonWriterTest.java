package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            TeamList teamList = new TeamList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptyTeamList() {
        try {
            TeamList teamList = new TeamList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTeamList.json");
            writer.open();
            writer.write(teamList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTeamList.json");
            teamList = reader.readTeams();
            assertTrue(teamList.getTeamList().isEmpty());
            assertTrue(teamList.getTeamNames().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTeamList() {
        try {
            TeamList teamList = new TeamList();
            Team team1 = new Team("A");
            Team team2 = new Team("B");
            teamList.addTeam(team1);
            teamList.addTeam(team2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTeamList.json");
            writer.open();
            writer.write(teamList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTeamList.json");
            teamList = reader.readTeams();
            ArrayList<Team> teams = teamList.getTeamList();
            assertEquals(2, teams.size());
            checkTeam("A", teams.get(0));
            checkTeam("B", teams.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
