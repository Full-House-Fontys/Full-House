import Database.DaoManager;

/**
 * Created by Kaj Suiker on 20-3-2016.
 */
public class ShutdownHook extends Thread {

    /**
     * Closes database connection after app closes.
     */
    public void run(){
        DaoManager daoManager = DaoManager.INSTANCE;
        daoManager.close();
        System.out.println("Database connection closed");
    }
}
