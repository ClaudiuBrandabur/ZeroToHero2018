package ro.teamnet.zth.api.em;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class QueryBuilder {

    private Object tableName;
    private List<ColumnInfo> queryColumns;
    private QueryType queryType;
    private List<Condition> conditions;

    public String getValueForQuery(Object value){
        if(value instanceof String) return "'" + value + "'";
        if(value instanceof Date){
            DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
            return "TO_DATE('"+dateFormat.format((Date)value)+"','mm-dd-YYYY'";
        }

        return value.toString();
    }

    public QueryBuilder addCondition(Condition condition){
        conditions.add(condition);
        return this;
    }

    public QueryBuilder setTableName(Object tableName) {
        this.tableName = tableName;
        return this;
    }

    public QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns){
        this.queryColumns.addAll(queryColumns);
        return this;
    }

    public QueryBuilder setQueryType(QueryType queryType){
        this.queryType = queryType;
        return this;
    }

    private String createSelectQuery(){

        StringBuilder st = new StringBuilder();
        st.append(QueryType.SELECT);

        boolean ok = true;

        for(ColumnInfo c : queryColumns){

            if(!ok){
               st.append(',');
            }

            st.append(tableName + "." + c.getDbColumnName());
            ok = false;
        }

        st.append("FROM").append(tableName);
        ok = true;

        if(conditions != null){
            st.append("WHERE");
            for(Condition condition : conditions){
                if(!ok)
                    st.append("and");
               st.append(condition.getColumnName() + " = " + getValueForQuery(condition.getValue()));
               ok = false;
            }
        }

        st.append(";");

        return st.toString();
    }

    private String createInsertQuery(){
        StringBuilder st = new StringBuilder();
        st.append(QueryType.INSERT).append("INTO").append(tableName);
        boolean ok = true;

        if(queryColumns != null) {
            st.append("(");
            for (ColumnInfo c : queryColumns) {
                if (!ok)
                    st.append(",");
                st.append(c.getDbColumnName());
                ok = false;
            }
            st.append(")");
        }

        st.append("VALUES").append("(");
        ok = true;

        for(Condition condition: conditions){
            if(!ok)
                st.append(",");
            st.append(getValueForQuery(condition.getValue()));
            ok = false;
        }

        st.append(");");
        return st.toString();
    }

    private String createDeleteQuery(){

        StringBuilder st = new StringBuilder();
        st.append(QueryType.DELETE);
        st.append("FROM").append(tableName);

        if(conditions != null) {
            st.append("WHERE");
            boolean ok = true;

            for (Condition condition : conditions) {
                if (!ok)
                    st.append("and");
                st.append(condition.getColumnName()).append("=").append(getValueForQuery(condition.getValue()));
                ok = false;
            }
        }
        st.append(";");
        return st.toString();
    }

    private String createUpdateQuery(){
        StringBuilder st = new StringBuilder();
        st.append(QueryType.UPDATE);
        st.append(tableName).append(" SET");
        boolean ok = true;

        for (ColumnInfo c : queryColumns){
            if (!ok)
                st.append(",");
            st.append(c.getDbColumnName()).append("=").append(getValueForQuery(c.getValue()));
            ok = false;
        }

        if(conditions != null) {
            ok = true;
            st.append("WHERE");

            for (Condition condition : conditions) {
                if (!ok)
                    st.append("and");
                st.append(condition.getColumnName()).append("=").append(getValueForQuery(condition.getValue()));
                ok = false;
            }
        }
        st.append(";");

        return st.toString();
    }

    public String createQuery(){
        if(queryType == QueryType.SELECT)
            return createSelectQuery();
        if(queryType == QueryType.INSERT)
            return createInsertQuery();
        if(queryType == QueryType.DELETE)
            return createDeleteQuery();
        if(queryType == QueryType.UPDATE)
            return createUpdateQuery();

        return null;
    }
}
