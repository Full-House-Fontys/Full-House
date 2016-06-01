package HulpDienst;

import CentralPoint.Mission;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mark on 26-4-2016.
 */
public class TeamRequestTest {

    TeamRequest TR;
    Mission mission;

    @Before
    public void setUp() throws Exception {
        mission = new Mission(1, null, null, null, null, null, 0.0, 0.0);
        TR = new TeamRequest("BRAND", "veel brand", 2, 2, 6, 1, mission);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("wrong name", "BRAND", TR.getName());
    }

    @Test
    public void testSetName() throws Exception {
        TR.setName("overval");
        assertEquals("wrong name", "overval", TR.getName());
    }

    @Test
    public void testGetDescription() throws Exception {
        assertEquals("wrong description", "veel brand", TR.getDescription());
    }

    @Test
    public void testSetDescription() throws Exception {
        TR.setDescription("grote overval");
        assertEquals("wrong description", "grote overval", TR.getDescription());
    }

    @Test
    public void testGetEHBO() throws Exception {
        assertEquals("Wrong amount of Medic", 2, TR.getMedic());
    }

    @Test
    public void testSetEHBO() throws Exception {
        TR.setMedic(3);
        assertEquals("Wrong amount of Medic", 3, TR.getMedic());
    }

    @Test
    public void testGetPolitie() throws Exception {
        assertEquals("Wrong amount of Police", 2, TR.getPolice());
    }

    @Test
    public void testSetPolitie() throws Exception {
        TR.setPolice(6);
        assertEquals("Wrong amount of Police", 6, TR.getPolice());
    }

    @Test
    public void testGetBrandweer() throws Exception {
        assertEquals("Wrong amount of fireman", 6, TR.getFireman());
    }

    @Test
    public void testSetBrandweer() throws Exception {
        TR.setFireman(3);
        assertEquals("Wrong amount of fireman", 3, TR.getFireman());
    }

    @Test
    public void testGetMarachause() throws Exception {
        assertEquals("Wrong amount of military police", 1, TR.getMilitarypolice());
    }

    @Test
    public void testSetMarachause() throws Exception {
        TR.setMilitarypolice(2);
        assertEquals("Wrong amount of military police", 2, TR.getMilitarypolice());
    }

    @Test
    public void testGetmissie() throws Exception {
        assertEquals("wrong mission", mission, TR.getMission());
    }

    @Test
    public void testSetmissie() throws Exception {
        Mission missie2 = new Mission(2, null, null, null, null, null, 0.0, 0.0);
        TR.setMission(missie2);
        assertEquals("wrong mission", missie2, TR.getMission());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("wrong mission", "BRAND: veel brand", TR.toString());
    }
}