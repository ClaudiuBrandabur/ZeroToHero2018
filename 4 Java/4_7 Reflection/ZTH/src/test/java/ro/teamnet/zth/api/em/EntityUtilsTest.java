package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class EntityUtilsTest {

    @Test
    public void testGetTableNameMethod() {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "DEPARTMENTS", tableName);
    }


    @Test
    public void testGetColumnsMethod() {
        List<String> columns = EntityUtils.getColumns(Department.class);
        assertEquals(3, columns.size());
    }

}
