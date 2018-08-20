package ro.teamnet.zth.api.em;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {
    public String getValueForQuery(Object value) {
        if (value instanceof String)
            return "value";
        if (value instanceof Date)
        {
            DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
            return ("TO_DATE(" + dateFormat.format((Date)value) +"'mm-dd-yyyy'");
        }
        return value.toString();
    }

    private Object tableName;
    private List<ColumnInfo> queryColumns = new ArrayList<>();
    private QueryType queryType;
    private List<Condition> conditions = new ArrayList<>();

    public QueryBuilder addCondition(Condition condition) {
        conditions.add(condition);
        return this;
    }

    public QueryBuilder setTableName(Object tableName) {
        this.tableName = tableName;
        return this;
    }

    public QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns) {
        this.queryColumns.addAll(queryColumns);
        return this;
    }

    public QueryBuilder setQueryType(QueryType queryType) {
        this.queryType = queryType;
        return this;
    }



}
