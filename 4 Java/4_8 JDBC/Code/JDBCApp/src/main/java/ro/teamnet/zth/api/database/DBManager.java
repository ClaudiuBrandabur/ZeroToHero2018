package ro.teamnet.zth.api.database;

import java.sql.*;

public class DBManager {
    private static String CONNECTION_STRING = "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT + ":XE";

    private DBManager() throws UnsupportedOperationException {

    }

    private static void registerDriver() throws ClassNotFoundException {
        Class.forName(DBProperties.DRIVER_CLASS);
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        registerDriver();
        Connection connection = DriverManager.getConnection(CONNECTION_STRING, DBProperties.USER, DBProperties.PASS);
        return connection;
    }

    public static ResultSet checkConnection(Connection connection) throws  ClassNotFoundException {
        ResultSet rezSet = null;
        try (Statement statement = getConnection().createStatement()){
            String select = "SELECT 1 FROM DUAL";
            rezSet = statement.executeQuery(select);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezSet;
    }
}
