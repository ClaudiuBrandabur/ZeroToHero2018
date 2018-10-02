package java.ro.teamnet.zth.api.em;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class QueryBuilder2 {
    private Object tableName;
    private List<ColumnInfo> queryColumns;
    private java.ro.teamnet.zth.api.em.QueryType queryType;
    private List<Condition> conditions;

    public String getValueForQuery(Object value){
        if (value instanceof String){
            return "'" + value + "'";
        } else if (value instanceof java.sql.Date){
            DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
            return "TO_DATE('"+dateFormat.format((Date)value)+"','mm-dd-YYYY'";
        } else {
            return value.toString();
        }
    }

    public QueryBuilder2 addCondition(Condition condition){
        if (this.conditions == null){
            this.conditions = new ArrayList<>();
        }
        this.conditions.add(condition);
        return this;
    }

    public QueryBuilder2 setTableName(Object tableName){
        this.tableName = tableName;

        return this;
    }

    public QueryBuilder2 addQueryColumns(List<ColumnInfo> queryColumns){
        if (this.queryColumns == null){
            this.queryColumns = new ArrayList<>();
        }
        this.queryColumns.addAll(queryColumns);

        return this;
    }

    public QueryBuilder2 setQueryType(java.ro.teamnet.zth.api.em.QueryType queryType){
        this.queryType = queryType;
        return this;
    }

    //    private String createSelectQuery(){
//        StringBuilder sql = new StringBuilder();
//        sql.append("select ");
//        boolean isFirst = true;
//        for(ColumnInfo columnInfo : queryColumns) {
//            if(!isFirst) {
//                sql.append(",");
//            }
//            sql.append(tableName + "." + columnInfo.getDbColumnName());
//            isFirst = false;
//        }
//        sql.append(" from " + tableName);
//
//        boolean whereAdded = false;
//        if(conditions != null && !conditions.isEmpty()) {
//            for(Condition condition : conditions) {
//                sql.append(whereAdded ? " and" : " where ").append(condition.getColumnName()).append("=")
//                        .append(getValueForQuery(condition.getValue()));
//                whereAdded = true;
//            }
//        }
//        return sql.toString();
//    }
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

    private String createDeleteQuery(){
        StringBuilder sql = new StringBuilder();
        sql.append("delete from ").append(tableName);
        boolean whereAdded = false;
        if (conditions != null  && !conditions.isEmpty()){
            for (Condition condition : conditions) {
                sql.append(whereAdded ? " and" : " where ").append(condition.getColumnName()).append("=").append(getValueForQuery(condition.getValue()));
                whereAdded = true;
            }
        }
        return sql.toString();
    }

    private String createUpdateQuery(){
        StringBuilder sql = new StringBuilder();
        sql.append("update ").append(tableName).append(" set ");
        boolean first = true;
        for (ColumnInfo column : queryColumns) {

            if (!first) {
                sql.append(",");
            } else {
                first = false;
            }
            sql.append(column.getDbColumnName()).append("=").append(getValueForQuery(column.getValue()));

        }

        boolean whereAdded = false;
        if (conditions != null  && !conditions.isEmpty()){
            for (Condition condition : conditions) {
                sql.append(whereAdded ? " and" : " where ").append(condition.getColumnName()).append("=").append(getValueForQuery(condition.getValue()));
                whereAdded = true;
            }
        }
        return sql.toString();
    }

    private String createInsertQuery(){
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ").append(tableName).append(" (");
        StringBuilder sqlValues = new StringBuilder(" values (");
        boolean first = true;
        for (ColumnInfo columnInfo : queryColumns) {

            if (!first) {
                sql.append(",");
                sqlValues.append(",");
            } else {
                first = false;
            }
            sql.append(columnInfo.getDbColumnName());
            sqlValues.append(getValueForQuery(columnInfo.getValue()));
        }

        sql.append(") ");
        sqlValues.append(")");
        sql.append(sqlValues);

        return sql.toString();
    }

    public String createQuery(){
        if (QueryType.SELECT.equals(this.queryType)){
            return createSelectQuery();
        } else if (QueryType.INSERT.equals(this.queryType)) {
            return createInsertQuery();
        } else if (QueryType.UPDATE.equals(this.queryType)) {
            return createUpdateQuery();
        } else if (QueryType.DELETE.equals(this.queryType)) {
            return createDeleteQuery();
        }
        return null;
    }
}