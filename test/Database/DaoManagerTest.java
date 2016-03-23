package Database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kaj Suiker on 20-3-2016.
 */
public class DaoManagerTest {
    DaoManager daoManager;

    @Before
    public void setUp() throws Exception {
        daoManager = DaoManager.INSTANCE;
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testOpen() throws Exception {
        daoManager.open();
        daoManager.close();
    }

    @Test
    public void testClose() throws Exception {
        daoManager.open();
        daoManager.close();
    }

    @Test
    public void testGetDao() throws Exception {
        daoManager.open();
        assertNotNull("Database get Personeel object",daoManager.getDao(DbTables.PERSONEEL));
        assertNotNull("Database get materiaal object",daoManager.getDao(DbTables.MATERIAAL));
        daoManager.close();
    }
}