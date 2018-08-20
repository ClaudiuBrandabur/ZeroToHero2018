package ro.teamnet.zth.api.em;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class QueryBuilder {
    Object tableName;
    List<ColumnInfo> queryColumns;
    QueryType queryType;
    List<Condition> conditions;

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
        this.setTableName(tableName);
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

    private String createSelectQuery() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(QueryType.SELECT);

        boolean ok = true;
        for(ColumnInfo col : queryColumns) {

            if(!ok) {
                stringBuilder.append(',');
            }

            stringBuilder.append(tableName + "." + col.getDbColumnName());
            ok = false;

        }
        stringBuilder.append("FROM ");
        stringBuilder.append(tableName);
        boolean ok2 = true;
        if(conditions.size() > 0) {
            stringBuilder.append("WHERE ");
        }
        for(Condition condition : conditions){
            if(!ok2){
                stringBuilder.append("AND");
            }
//            stringBuilder.append(condition)
        }
        return stringBuilder.toString();
    }

    private String createDeleteQuery() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(QueryType.DELETE);

        boolean ok = true;
        for (ColumnInfo col : queryColumns) {

            if (!ok) {
                stringBuilder.append(',');
            }

            stringBuilder.append(tableName + "." + col.getDbColumnName());
            ok = false;
        }
        stringBuilder.append("FROM ");
        stringBuilder.append(tableName);
        if (conditions.size() > 0) {
            stringBuilder.append("WHERE ");
        }
        return stringBuilder.toString();
    }
    private String createUpdateQuery () {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(QueryType.UPDATE);

        boolean ok = true;
        for (ColumnInfo col : queryColumns) {
            if (!ok) {
                stringBuilder.append(',');
            }
            stringBuilder.append(tableName + "." + col.getDbColumnName());
            ok = false;
        }
        stringBuilder.append("FROM ");
        stringBuilder.append(tableName);
        if (conditions.size() > 0) {
            stringBuilder.append("WHERE ");
        }
        for (Condition condition : conditions) {

        }
        return stringBuilder.toString();
    }

    private String createInsertQuery () {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(QueryType.INSERT);

        boolean ok = true;
        for (ColumnInfo col : queryColumns) {

            if (!ok) {
                stringBuilder.append(',');
            }
            stringBuilder.append(tableName + "." + col.getDbColumnName());
            ok = false;
        }

        stringBuilder.append("FROM ");
        stringBuilder.append(tableName);
        if (conditions.size() > 0) {
            stringBuilder.append("WHERE ");
        }
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



