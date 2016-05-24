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
        assertEquals("foute naam", "BRAND", TR.getName());
    }

    @Test
    public void testSetName() throws Exception {
        TR.setName("overval");
        assertEquals("foute naam", "overval", TR.getName());
    }

    @Test
    public void testGetDescription() throws Exception {
        assertEquals("foute descriptie", "veel brand", TR.getDescription());
    }

    @Test
    public void testSetDescription() throws Exception {
        TR.setDescription("grote overval");
        assertEquals("foute descriptie", "grote overval", TR.getDescription());
    }

    @Test
    public void testGetEHBO() throws Exception {
        assertEquals("foute aantal EHBO", 2, TR.getMedic());
    }

    @Test
    public void testSetEHBO() throws Exception {
        TR.setMedic(3);
        assertEquals("foute aantal EHBO", 3, TR.getMedic());
    }

    @Test
    public void testGetPolitie() throws Exception {
        assertEquals("foute aantal Politie", 2, TR.getPolice());
    }

    @Test
    public void testSetPolitie() throws Exception {
        TR.setPolice(6);
        assertEquals("foute aantal Politie", 6, TR.getPolice());
    }

    @Test
    public void testGetBrandweer() throws Exception {
        assertEquals("foute aantal brandweer", 6, TR.getFireman());
    }

    @Test
    public void testSetBrandweer() throws Exception {
        TR.setFireman(3);
        assertEquals("foute aantal brandweer", 3, TR.getFireman());
    }

    @Test
    public void testGetMarachause() throws Exception {
        assertEquals("foute aantal Marachause", 1, TR.getMilitarypolice());
    }

    @Test
    public void testSetMarachause() throws Exception {
        TR.setMilitarypolice(2);
        assertEquals("foute aantal Marachause", 2, TR.getMilitarypolice());
    }

    @Test
    public void testGetmissie() throws Exception {
        assertEquals("foute missie", mission, TR.getMission());
    }

    @Test
    public void testSetmissie() throws Exception {
        Mission missie2 = new Mission(2, null, null, null, null, null, 0.0, 0.0);
        TR.setMission(missie2);
        assertEquals("foute missie", missie2, TR.getMission());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("foute missie", "BRAND: veel brand", TR.toString());
    }
}