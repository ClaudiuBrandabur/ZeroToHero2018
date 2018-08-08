package ro.teamnet.zth.api.database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

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

    }
}
