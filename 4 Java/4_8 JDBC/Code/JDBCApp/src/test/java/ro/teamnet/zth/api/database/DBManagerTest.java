package ro.teamnet.zth.api.database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.TestCase.*;

public class DBManagerTest {

    @Test
    public void testGetConnection(){
        try{
            Connection con = DBManager.getConnection();
            assertNotNull(con);
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testCheckConnection(){
        Connection connection = null;
        connection = DBManager.getConnection();
        boolean ok = false;
        try {
            ok = DBManager.checkConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertTrue(ok);
    }
}
