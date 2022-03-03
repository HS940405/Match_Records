package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatchListTest {

    MatchList matchList;

    @BeforeEach
    void runBefore() {
        matchList = new MatchList();
    }

    @Test
    void testConstructor() {
        MatchList matchList2 = new MatchList();
        assertEquals(0, matchList2.getMatchList().size());
    }

    @Test
    void testAddMatch() {
        Match match1 = new Match("2022-01-03");
        Match match2 = new Match("2003-01-05");
        matchList.addMatch(match1);
        matchList.addMatch(match2);
        assertEquals(2, matchList.getMatchList().size());
        assertEquals("2022-01-03", matchList.getMatchList().get(0).getDate());
        assertEquals("2003-01-05", matchList.getMatchList().get(1).getDate());
    }
}
