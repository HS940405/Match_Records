package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TeamListTest {

    private TeamList teamList1;

    @BeforeEach
    void runBefore() {
        teamList1 = new TeamList();
    }

    @Test
    void testAddOneTeam() {
        Team aTeam = new Team("A");
        teamList1.addTeam(aTeam);

        assertEquals("A", teamList1.getTeamNames().get(0));
        assertEquals("A", teamList1.getTeamList().get(0).getTeamName());
    }

    @Test
    void testAddManyTeam() {
        Team aTeam = new Team("A");
        Team bTeam = new Team("B");
        teamList1.addTeam(aTeam);
        teamList1.addTeam(bTeam);

        assertEquals("A", teamList1.getTeamNames().get(0));
        assertEquals("A", teamList1.getTeamList().get(0).getTeamName());
        assertEquals("B", teamList1.getTeamNames().get(1));
        assertEquals("B", teamList1.getTeamList().get(1).getTeamName());
    }

    @Test
    void testIsEmpty() {
        assertTrue(teamList1.isEmpty());

        Team aTeam = new Team("A");
        teamList1.addTeam(aTeam);

        assertFalse(teamList1.isEmpty());
    }

    /*
    @Test
    void testJSONObject() {
        teamList1.toJson();
        assertNull(teamList1.toJson()); //----?????
    }

     */



}
