package model;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkTeam(String name, Team team) {
        assertEquals(name, team.getTeamName());
    }

    protected void checkMatch(String date, double time, String opp, boolean book, String res, String imp, Match match) {
        assertEquals(date, match.getDate());
        assertEquals(time, match.getTime());
        assertEquals(opp, match.getOpposingTeam());
        assertEquals(book, match.isBooking());
        assertEquals(res, match.getResult());
        assertEquals(imp, match.getImpression());
    }
}
