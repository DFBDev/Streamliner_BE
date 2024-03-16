package dataHandlers_Model;
import java.sql.*;
import java.util.Properties;

public class dbInputHandler {
    public static void inputData(String fname, String lname, String subject, String timein, String timeout, String dbPassword) throws SQLException { //Creates insertion query for postgreSQL using payload/user input data (using JDBC API).
        String insertStatement = "insert into studentinfo (studentfname, studentlname, studentsubject, studenttimein, studenttimeout) values (?, ?, ?, ?, ?)";

        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", dbPassword); //Password received from user input.
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", props);

        PreparedStatement pstatement = conn.prepareStatement(insertStatement); //Creating prepared statement to avoid SQL injections.
        pstatement.setString(1, fname);
        pstatement.setString(2, lname);
        pstatement.setString(3, subject);
        pstatement.setString(4, timein);
        pstatement.setString(5, timeout);

        pstatement.executeUpdate();
        conn.close();
    }
}
