package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noFile.json");
        try {
            TeamList teamList = reader.readTeams();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testReaderEmptyTeamList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTeamList.json");
        try {
            TeamList teamList = reader.readTeams();
            assertEquals(0, teamList.getTeamList().size());
            assertEquals(0, teamList.getTeamNames().size());
        } catch (IOException e) {
            fail("No TeamList Found");
        }
    }

    @Test
    void testReaderGeneralTeamList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTeamList.json");
        try {
            TeamList teamList = reader.readTeams();
            assertEquals("Hawks", teamList.getTeamList().get(0).getTeamName());
            assertEquals("2022-01-03", teamList.getTeamList().get(0).getMatchDateList().get(0));
        } catch (IOException e) {
            fail("No Teams found");
        }
    }

    @Test
    void testReaderGeneralTeam() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTeamList.json");
        try {
            TeamList teamList = reader.readTeams();
            checkMatch("2022-01-03", 1800, "A", true, "l", "n",
                       teamList.getTeamList().get(0).getMatchList().get(0));
        } catch (IOException e) {
            fail("No Teams found");
        }
    }


}
