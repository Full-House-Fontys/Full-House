package DA;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by kaj75 on 16-3-2016.
 */
public enum  TableType {
    STAFF("PERSONEEL", (Map<String,String>)DBRead.getColums("PERSONEEL"));

    private final String tableName;
    private final Map<String, String> columns;

    TableType(String tableName, Map<String, String> column) {
        this.tableName = tableName;
        this.columns = column;
    }

    public String getTableName(){
        return tableName;
    }

    public Map<String, String> getColumns(){
        return columns;
    }

    public String toString() {
        return tableName;
    }
}
