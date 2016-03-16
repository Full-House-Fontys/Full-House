package DA;

import java.lang.String;
import java.sql.ResultSet;

/**
 * Created by kaj75 on 15-3-2016.
 */
public class DBSpecifics {
    static String queryString(String tableName, QueryType type, String keyValue) {
        StringBuilder firstHalf = new StringBuilder();

        switch (type) {
            case READ:
                firstHalf.append("select * from " + tableName);
                break;
            case DELETE:
                firstHalf.append("DELETE from " + tableName);
                break;
            case INSERT:
                firstHalf.append("INSERT INTO " + tableName);
                break;
            case COLUMN:
                firstHalf.append("SELECT COLUMN_NAME FROM dbi329146.INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?");
            default:
        }

        if (!keyValue.equals(""))
            switch (tableName) {
                case "PERSONEEL":
                    firstHalf.append("ID = ?");
                default:
                    return null;
            }
        return firstHalf.toString();
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
