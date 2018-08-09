package ro.teamnet.zth.api.database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DBManagerTest {

    @Test
    public void testGetConnection() {

        try{
            Connection con = DBManager.getConnection();
            assertNotNull(con);
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testCheckConnection() {

        Connection con = DBManager.getConnection();
        boolean res = DBManager.checkConnection(con);
        assertTrue(res);
    }
}
