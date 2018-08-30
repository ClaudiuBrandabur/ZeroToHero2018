package ro.teamnet.zth.api.database;

import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DBManagerTest {

    @Test
    public void testGetConnection() {
        try (Connection connection = DBManager.getConnection()) {
            assertTrue(connection != null);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCheckConnection() throws SQLException, ClassNotFoundException {
        try (ResultSet rez = DBManager.checkConnection(DBManager.getConnection())) {
            assertTrue(rez != null);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
