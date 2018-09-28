package ro.teamnet.zth.api.em;

import org.junit.jupiter.api.Test;
import ro.teamnet.zth.appl.domain.Department;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityUtilsTest {

    @Test
    public void testGetTableNameMethod() {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "departments", tableName);
    }




}
