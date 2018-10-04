package java.ro.teamnet.zth.api.em;

import org.testng.annotations.Test;
import java.ro.teamnet.zth.api.annotations.Column;
import java.ro.teamnet.zth.appl.domain.Department;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityUtilsTest {
    @Test
    public void testGetTableNameMethod() {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "departments", tableName);
    }

    @Test
    public void testGetColumnsMethod() {
        List<ColumnInfo> columns = EntityUtils.getColumns(Department.class);
        assertEquals(3, columns.size());
    }

    @Test
    public void testGetFieldsByAnnotations() throws NoSuchFieldException {
        assertEquals(2, EntityUtils.getFieldsByAnnotations(Department.class, Column.class).size());
    }
}
