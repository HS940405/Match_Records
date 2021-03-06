package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {

    private Team team1;
    private Team team2;
    private Team team3;

    @BeforeEach
    public void runBefore() {
        team1 = new Team("Hawks");
        team2 = new Team("Tigers");
        team3 = new Team("Ants");
    }

    @Test
    public void testConstructor() {
        Team team4 = new Team("Bulls");
        assertEquals("Bulls", team4.getTeamName());
        assertEquals(0, team4.getMatchList().size());
        assertEquals(0, team4.getMatchDateList().size());
    }

    @Test
    public void testAddMatch() {
        team1.addMatch("2022-01-31", 1800, "Tigers", false, "w", null);

        assertEquals(1800, team1.getMatchList().get(0).getTime());
        assertEquals("Tigers", team1.getMatchList().get(0).getOpposingTeam());
        assertFalse(team1.getMatchList().get(0).isBooking());
        assertEquals("w", team1.getMatchList().get(0).getResult());
        assertNull(team1.getMatchList().get(0).getImpression());
    }

    @Test
    public void testMatchToList() {
        team1.addMatch("2022-01-31", 1800, "Tigers", false, "w", null);

        assertEquals("2022-01-31", team1.getMatchList().get(0).getDate());
        assertEquals("Tigers", team1.getMatchList().get(0).getOpposingTeam());
        assertFalse(team1.getMatchList().get(0).isBooking());
        assertEquals("w", team1.getMatchList().get(0).getResult());
        assertNull(team1.getMatchList().get(0).getImpression());
        assertEquals("2022-01-31", team1.getMatchDateList().get(0));
    }

    @Test
    public void testRecordedMatch() {
        team1.addMatch("2022-02-07", 1800, "Tigers", false, "w", "");
        team1.addMatch("2006-05-07", 1300, "AM", true, "l", "");

        assertEquals(0, team1.recordedMatch("2022-02-07"));
        assertEquals(-1, team1.recordedMatch("2021-05-05"));
        assertEquals(1, team1.recordedMatch("2006-05-07"));
        assertEquals(-1, team2.recordedMatch("2022-09-05"));
        assertEquals(-1, team2.recordedMatch("2006-05-07"));
        assertEquals(-1, team1.recordedMatch("hello"));

        team2.addMatch("2022-09-05", 700, "Peanuts", false, "n", "Hello");

        assertEquals(0, team2.recordedMatch("2022-09-05"));
        assertEquals(-1, team2.recordedMatch("2021-09-05"));

        team1.addMatch("2021-08-22", 1730, "Java", true, "w", "Buddy");

        assertEquals(2, team1.recordedMatch("2021-08-22"));

        team3.setMatchDateList("2022-02-03");
        assertEquals(-1, team3.recordedMatch("2022-02-03"));

    }

    @Test
    public void testAllBookedMatch() {

        team1.addMatch("2022-01-05", 1900, "Bulls", true, "w", null);
        team1.addMatch("2022-03-02", 1600, "Phoenix", false, "l", "n");

        assertEquals("2022-01-05\n", team1.allBookedMatch());
        assertEquals("", team2.allBookedMatch());
    }

    @Test
    public void testCheckMatch() {

        team1.addMatch("2022-01-05", 1900, "Bulls", true, "w", null);
        team1.addMatch("2022-03-02", 1600, "Phoenix", false, "l", "n");

        assertEquals("There is no match on the selected date", team1.checkMatch("2022-01-04"));
        assertEquals("\nMatch Info on 2022-01-05"+"\nTime: 1900.0" + "\nOpposing Team: Bulls" +
                        "\nBooking: true" + "\nResult: w" + "\nImpression: null\n", team1.checkMatch("2022-01-05"));

    }

    @Test
    public void testCountBooking() {
        assertEquals(0, team1.countBooking());
        team1.addMatch("2022-01-05", 1900, "Bulls", true, "w", null);
        assertEquals(1, team1.countBooking());
        team1.addMatch("2022-03-02", 1600, "Phoenix", false, "l", "n");
        assertEquals(1, team1.countBooking());
        team1.addMatch("2022-03-09", 1600, "Phoenix", true, "l", "n");
        assertEquals(2, team1.countBooking());
    }

}
