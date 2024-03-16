package dataHandlers_Model;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class dbRetrieveHandler {
    public static ArrayList<String> retrieveData(String dbPassword) throws SQLException { //Retrieves current data in database (using JDBC API).
        String retrieveStatement = "select * from studentinfo";
        ArrayList<String> resultList = new ArrayList<>(); //ArrayList for holding retrieved db data.

        //Establishing credentials/properties for db connection.
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", dbPassword);
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", props);

        PreparedStatement pstatement = conn.prepareStatement(retrieveStatement); //Creating prepared statement to avoid SQL injections.
        ResultSet rs = pstatement.executeQuery(); //Storing result of query into rs for parsing.

        //Pulling data, sequentially, from each row in database and inserting into resultList.
        while (rs.next()) {
            String sfname = rs.getString("studentfname");
            String slname = rs.getString("studentlname");
            String ssubject = rs.getString("studentsubject");
            String stimein = rs.getString("studenttimein");
            String stimeout = rs.getString("studenttimeout");

            resultList.add("\nStudent First Name: " + sfname + "\nStudent Last Name: " + slname
            + "\nStudent Subject: " + ssubject + "\nStudent Time In: " + stimein + "\nStudent Time Out: " + stimeout + "\n");
        }

        return resultList;
    }
}
