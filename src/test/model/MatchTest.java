package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    private Match match1;

    @BeforeEach
    public void runBefore() {
        match1 = new Match("2021-04-28");
    }

    @Test
    public void testConstructor() {
        Match match2 = new Match("2022-01-31");
        assertEquals("2022-01-31", match2.getDate());
        assertEquals(0, match2.getTime());
        assertEquals("", match2.getOpposingTeam());
        assertFalse(match2.isBooking());
        assertEquals("", match2.getResult());
        assertEquals("", match2.getImpression());
    }

    @Test
    public void testSet() {
        match1.setTime(2000);
        assertEquals(2000, match1.getTime());
        match1.setTime(1900);
        assertEquals(1900, match1.getTime());
        match1.setOpposingTeam("Lakers");
        assertEquals("Lakers", match1.getOpposingTeam());
        match1.setOpposingTeam("Thunders");
        assertEquals("Thunders", match1.getOpposingTeam());
        match1.setBooking(true);
        assertTrue(match1.isBooking());
        match1.setBooking(false);
        assertFalse(match1.isBooking());
        match1.setResult("w");
        assertEquals("w", match1.getResult());
        match1.setResult("l");
        assertEquals("l", match1.getResult());
        match1.setImpression("n");
        assertEquals("n", match1.getImpression());
        match1.setImpression("It was so fun!");
        assertEquals("It was so fun!", match1.getImpression());
    }
}