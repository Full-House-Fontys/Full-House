

//import static javafx.application.Application.*;

import javafx.stage.Stage;

import java.sql.*;

/**
 * Created by jvdwi on 9-3-2016.
 */
public class main extends javafx.application.Application {
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            String userName = "dbi329146";
            String passWord = "Fullhouseaapje";
            String url = "jdbc:sqlserver://mssql.fhict.local";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(url, userName, passWord);

            System.out.println("connected");

            Statement st = conn.createStatement();
            String queryString = "SELECT * FROM FULLHOUSE";
            ResultSet rs = st.executeQuery(queryString);
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            conn.close();
        } catch (SQLException ex){
            System.out.println("Connectie is niet gemaakt");
        }
        finally{
            System.exit(0);
        }
    }
}
