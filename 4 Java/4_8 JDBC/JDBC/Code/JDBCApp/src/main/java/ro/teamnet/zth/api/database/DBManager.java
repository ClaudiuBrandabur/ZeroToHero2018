package ro.teamnet.zth.api.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

    private DBManager() {
        throw new UnsupportedOperationException();
    }

    final static String CONNECTION_STRING = "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT + ":XE";

    private static void registerDriver(){

        try {
            Class.forName(DBProperties.DRIVER_CLASS);
        }catch (ClassNotFoundException e){
            System.out.println("Error: unable to load driver class!!");
            System.exit(1);
        }
    }

    public static Connection getConnection(){

        Connection conn = null;
        registerDriver();

        try {
            conn = DriverManager.getConnection(CONNECTION_STRING,DBProperties.USER,DBProperties.PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static boolean checkConnection(Connection connection){

        boolean ok = false;
        try(Statement stm = connection.createStatement()){
           ok = stm.execute("SELECT 1 FROM DUAL");
        }catch (SQLException e){
            e.printStackTrace();
        }

        return ok;

    }
}
