package java.ro.teamnet.zth.api.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    static final String CONNECTION_STRING = "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT;

    private DBManager() throws UnsupportedOperationException {
    }
   private static void registerDriver() throws ClassNotFoundException {
        Class.forName(DBProperties.DRIVER_CLASS);
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
}
