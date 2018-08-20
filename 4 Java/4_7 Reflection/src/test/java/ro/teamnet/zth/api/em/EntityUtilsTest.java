package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.domain.Location;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class EntityUtilsTest {
    @Test
    public void testGetTableNameMethod(){
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments", "DEPARTMENTS", tableName);

        String tablename2 = EntityUtils.getTableName(Location.class);
        assertEquals("Table name should be locations!", "LOCATIONS", tablename2);
    }


    @Test
    public void testGetColumnMethod(){
        ArrayList<ColumnInfo> columnList  = EntityUtils.getColumns(Department.class);
        assertEquals(3, columnList.size());

        ArrayList<ColumnInfo> columnList2 = EntityUtils.getColumns(Job.class);
        assertEquals(4, columnList2.size());
    }
}
