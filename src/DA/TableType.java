package DA;

import java.util.ArrayList;

/**
 * Created by kaj75 on 16-3-2016.
 */
public enum  TableType {
    STAFF("PERSONEEL", (ArrayList<String>)DBRead.getColums("PERSONEEL"));

    private final String tableName;
    private ArrayList<String> column;

    TableType(String tableName, ArrayList<String> column) {
        this.tableName = tableName;
        this.column = column;
    }

    public String toString() {
        return tableName;
    }

}
