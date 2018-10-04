package ro.teamnet.zth.api.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DBManager {

    static final String CONNECTION_STRING = "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT;

   private static void registerDriver() throws ClassNotFoundException {
        try {
            Class.forName(DBProperties.DRIVER_CLASS);
        }
        catch (ClassNotFoundException ex){
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }
//       Driver myDriver = new oracle.jdbc.driver.OracleDriver();
   }

    private DBManager() throws UnsupportedOperationException {
    }
   public static Object getConnection() throws ClassNotFoundException {
        Connection connection = null;
        registerDriver();
       try {
           connection = DriverManager.getConnection(CONNECTION_STRING, DBProperties.USER, DBProperties.PASS);
       }
       catch(SQLException se){
           se.printStackTrace();
       }
       return connection;
   }

    public static boolean checkConnection(Connection connection) throws SQLException {
        Statement statement = null;
        statement = connection.createStatement();
        try {
            statement.executeQuery("SELECT 1 FROM DUAL");
            return true;
        }
        catch (SQLException se){
            se.printStackTrace();
            return false;
        }
    }
}
