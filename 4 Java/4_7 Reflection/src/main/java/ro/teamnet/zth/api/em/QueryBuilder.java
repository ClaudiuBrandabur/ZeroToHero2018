package ro.teamnet.zth.api.em;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueryBuilder {
    private Object tableName;
    private ArrayList<ColumnInfo> queryColumns = new ArrayList<ColumnInfo>();
    private QueryType queryType;
    private ArrayList<Condition> conditions = new ArrayList<Condition>();

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

    public QueryBuilder addCondition(Condition condition) {
        this.conditions.add(condition);
        return this;
    }

    public QueryBuilder setTableName(Object tableName) {
        this.tableName = tableName;
        return this;
    }

    public QueryBuilder addQueryColumns(ArrayList<ColumnInfo> querycolumns) {
        for(int i = 0; i < querycolumns.size(); i++){
            this.queryColumns.add(querycolumns.get(i));
        }
        return this;
    }

    public QueryBuilder setQueryType(QueryType queryType) {
        this.queryType = queryType;
        return this;
    }

    private String createSelectQuery() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(QueryType.SELECT);
        stringBuilder.append(" ");

        boolean ok = true;
        for(ColumnInfo col : queryColumns) {

            if(!ok) {
                stringBuilder.append(", ");
            }

            stringBuilder.append(tableName + "." + col.getDbColumnName());
            ok = false;

        }
        stringBuilder.append(" FROM ");
        stringBuilder.append(tableName);
        boolean ok2 = true;
        if(conditions.size() > 0) {
            stringBuilder.append(" WHERE ");
        }
        for(Condition condition : conditions){
            if(!ok2){
                stringBuilder.append("AND");
            }
            stringBuilder.append(condition.getColumnName());
            stringBuilder.append(" = ");
            stringBuilder.append(condition.getValue());
        }
        return stringBuilder.toString();
    }

    private String createDeleteQuery() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(QueryType.DELETE);
        stringBuilder.append(" ");
        boolean ok = true;

        for (ColumnInfo col : queryColumns) {

            if (!ok) {
                stringBuilder.append(", ");
            }

            stringBuilder.append(tableName + "." + col.getDbColumnName());
            ok = false;
        }
        stringBuilder.append(" FROM ");
        stringBuilder.append(tableName);
        if (conditions.size() > 0) {
            stringBuilder.append(" WHERE ");
        }
        boolean ok2 = true;
        for(Condition condition : conditions){
            if(!ok2){
                stringBuilder.append(" AND ");
            }
            stringBuilder.append(condition.getColumnName());
            stringBuilder.append(" = ");
            stringBuilder.append(condition.getValue());
            ok2 = false;
        }
        return stringBuilder.toString();
    }
    private String createUpdateQuery () {
        StringBuilder stringBuilder = new StringBuilder();
        boolean ok = true;
        stringBuilder.append(QueryType.UPDATE);
        stringBuilder.append(" ");
        stringBuilder.append(tableName);
        stringBuilder.append(" SET ");
        for(ColumnInfo col : queryColumns){
            if(!ok){
                stringBuilder.append(", ");
            }
            stringBuilder.append(col.getDbColumnName());
            stringBuilder.append(" = ");
            if(col.getColumnType() == String.class){
                stringBuilder.append("'").append(col.getValue()).append("'");
            }
            else
                stringBuilder.append(col.getValue());
            ok = false;
        }
        if (conditions.size() > 0) {
            stringBuilder.append(" WHERE ");
        }
        boolean ok2 = true;
        for(Condition condition : conditions){
            if(!ok2){
                stringBuilder.append(" AND ");
            }
            stringBuilder.append(condition.getColumnName());
            stringBuilder.append(" = ");
            stringBuilder.append(condition.getValue());
            ok2 = false;
        }
        return stringBuilder.toString();
    }

    private String createInsertQuery () {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(QueryType.INSERT);
        stringBuilder.append(" INTO ");
        stringBuilder.append(tableName);
        stringBuilder.append(" (");
        boolean ok = true;
        for (ColumnInfo col : queryColumns) {

            if (!ok) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(col.getDbColumnName());
            ok = false;
        }

        stringBuilder.append(") VALUES(");
        ok = true;
        for (ColumnInfo col : queryColumns) {

            if (!ok) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(col.getValue());
            ok = false;
        }
        stringBuilder.append(")");

        return stringBuilder.toString();
    }
    public String createQuery(){

        String create = new String();
        if(queryType == QueryType.SELECT){
            create = createSelectQuery();
        }
        if(queryType == QueryType.DELETE){
            create = createDeleteQuery();
        }
        if(queryType == QueryType.UPDATE){
            create = createUpdateQuery();
        }
        if(queryType == QueryType.INSERT){
            create = createInsertQuery();
        }
        return create;
    }
}



