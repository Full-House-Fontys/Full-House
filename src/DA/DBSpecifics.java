package DA;

import java.lang.String;
import java.sql.ResultSet;

/**
 * Created by kaj75 on 15-3-2016.
 */
public class DBSpecifics {
    static String queryString(String tableName, String keyValue, QueryType type) {
        StringBuilder firstHalf = new StringBuilder();

        switch (type) {
            case READ:
                firstHalf.append("select * from " + tableName + " where ");
                break;
            case DELETE:
                firstHalf.append("DELETE from " + tableName + " where ");
                break;
            case INSERT:
                firstHalf.append("INSERT INTO " + tableName);
            default:
        }

        switch (tableName) {
            case "PERSONEEL":
                return firstHalf.append("ID=" + keyValue + "").toString();
            default:
                return null;
        }
    }

    static <T> T getPojoFromResultSet(String tableName, ResultSet rs) throws Exception {

        switch (tableName) {
            case "PERSONEEL":
                return (T) SpecObject.getPojo(rs);
            default:
                return null;
        }
    }
}
