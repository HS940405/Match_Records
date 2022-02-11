package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {

    private Team team1;
    private Team team2;

    @BeforeEach
    public void runBefore() {
        team1 = new Team("Hawks");
        team2 = new Team("Tigers");
    }

    @Test
    public void testConstructor() {
        Team team3 = new Team("Bulls");
        assertEquals("Bulls", team3.getTeamName());
        assertEquals(0, team3.getMatchList().size());
        assertEquals(0, team3.getMatchDateList().size());
    }

    @Test
    public void testAddMatch() {
        team1.addMatch("2022-01-31", 1800, "Tigers", false, "w", null);

        assertEquals(1800, team1.getMatch().getTime());
        assertEquals("Tigers", team1.getMatch().getOpposingTeam());
        assertFalse(team1.getMatch().isBooking());
        assertEquals("w", team1.getMatch().getResult());
        assertNull(team1.getMatch().getImpression());
    }

    @Test
    public void testMatchToList() {
        team1.addMatch("2022-01-31", 1800, "Tigers", false, "w", null);
        team1.matchToList();

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
        team1.matchToList();
        team1.addMatch("2006-05-07", 1300, "A H", true, "l", "");
        team1.matchToList();

        assertEquals(0, team1.recordedMatch("2022-02-07"));
        assertEquals(-1, team1.recordedMatch("2021-05-05"));
        assertEquals(1, team1.recordedMatch("2006-05-07"));
        assertEquals(-1, team2.recordedMatch("2022-09-05"));

    }

    @Test
    public void testAllBookedMatch() {

        team1.addMatch("2022-01-05", 1900, "Bulls", true, "w", null);
        team1.matchToList();
        team1.addMatch("2022-03-02", 1600, "Phoenix", false, "l", "n");
        team1.matchToList();

        assertEquals("2022-01-05\n", team1.allBookedMatch());
        assertEquals("", team2.allBookedMatch());
    }

}
