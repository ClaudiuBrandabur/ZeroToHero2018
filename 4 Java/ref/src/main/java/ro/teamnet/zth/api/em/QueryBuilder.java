package ro.teamnet.zth.api.em;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class QueryBuilder {

    private Object tableName;
    private List<ColumnInfo> querryColumns;
    private QueryType queryType;
    private List<Condition> conditions;

    public String getValueForQuery(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof Date) {
            DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
            return "TO_DATE('" + dateFormat.format((Date) value) + "','mm-dd-YYYY'";
        } else
            return value.toString();
    }

    public QueryBuilder addCondition (Condition condition) {
        this.conditions.add(condition);
        return this;
    }
}

