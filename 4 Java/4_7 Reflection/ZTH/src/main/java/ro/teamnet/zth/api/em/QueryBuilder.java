package ro.teamnet.zth.api.em;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {

    public String getValueForQuery(Object value) {
        if (value instanceof String)
            return "'value'";
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
        if (condition != null)
            conditions.add(condition);
        return this;
    }

    public QueryBuilder setTableName(Object tableName) {
        if (tableName != null)
            this.tableName = tableName;
        return this;
    }

    public QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns) {
        if (queryColumns != null)
            this.queryColumns.addAll(queryColumns);
        return this;
    }

    public QueryBuilder setQueryType(QueryType queryType) {
        if (queryType != null)
            this.queryType = queryType;
        return this;
    }

    private String createSelectQuery() {
        String ans = "SELECT ";
        if (queryColumns == null)
            ans += "*";
        else
        {
            for (int i = 0; i < queryColumns.size() - 1; i++)
                ans += queryColumns.get(i).getColumnName() + ", ";
            ans += queryColumns.get(queryColumns.size() - 1).getColumnName();
        }
        ans += "\n FROM " + tableName.toString();
        return ans;
    }

    private String createDeleteQuery() {
        String ans = "DELETE FROM " + tableName.toString() + "\nWHERE ";
        for (int i = 0; i < conditions.size() - 1; i++)
            ans += conditions.get(i).getColumnName() + " = " +
                    getValueForQuery(conditions.get(i).getValue() + " && ");
        ans += conditions.get(conditions.size() - 1).getColumnName() + " && "
                + getValueForQuery(conditions.get(conditions.size() - 1).getValue());
        return ans;
    }

    private String createUpdateQuery() {
        String ans = "UPDATE " + tableName.toString() + "\nSET ";
        for (int i = 0; i < queryColumns.size() - 1; i++)
            ans += queryColumns.get(i).getColumnName() + " = " + queryColumns.get(i).getValue() + ", ";
        ans += queryColumns.get(queryColumns.size() - 1).getColumnName() + " = "
                + queryColumns.get(queryColumns.size() - 1).getValue();
        ans += "\nWHERE ";
        for (int i = 0; i < conditions.size() - 1; i++)
            ans += conditions.get(i).getColumnName() + " = " +
                    getValueForQuery(conditions.get(i).getValue() + " && ");
        ans += conditions.get(conditions.size() - 1).getColumnName() + " && "
                + getValueForQuery(conditions.get(conditions.size() - 1).getValue());
        return ans;
    }

    private String createInsertQuery() {
        String ans = "INSERT INTO " + tableName.toString() + " (";
        for (int i = 0; i < queryColumns.size() - 1; i++)
            ans += queryColumns.get(i).getColumnName() + ", ";
        ans += queryColumns.get(queryColumns.size() - 1).getColumnName() + ")\nVALUES (";
        for (int i = 0; i < queryColumns.size() - 1; i++)
            ans += queryColumns.get(i).getValue() + ", ";
        ans += queryColumns.get(queryColumns.size() - 1).getValue() + ")";
        return ans;
    }

    public String createQuery() {
        switch (queryType) {
            case SELECT: return createSelectQuery();
            case DELETE: return createDeleteQuery();
            case UPDATE: return createUpdateQuery();
            case INSERT: return createInsertQuery();
        }
        return "";
    }
}
