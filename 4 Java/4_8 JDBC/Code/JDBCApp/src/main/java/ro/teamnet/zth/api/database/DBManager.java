package ro.teamnet.zth.api.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
    private DBManager() throws UnsupportedOperationException{
    }
    private final static String CONNECTION_STRING = "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT + ":XE";
    static void registerDriver() {//throws ClassNotFoundException{
        try {
            Class.forName(DBProperties.DRIVER_CLASS);
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }

    }

    public static Connection getConnection() {//throws ClassNotFoundException, SQLException{
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

