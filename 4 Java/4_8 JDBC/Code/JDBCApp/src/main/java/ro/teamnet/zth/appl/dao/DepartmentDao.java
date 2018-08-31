package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Department;

import java.sql.SQLException;
import java.util.List;

public class DepartmentDao {
    EntityManager entityManager = new EntityManagerImpl();

    public Department callFindById(Long id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return entityManager.findById(Department.class, id);
    }

    public Long callGetNextIdVal(String tableName, String columnIdName) throws SQLException, ClassNotFoundException {
        return entityManager.getNextIdVal(tableName, columnIdName);
    }

    public Department callInsert(Department dep) throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return (Department) entityManager.insert(dep);
    }

    public List<Department> callFindAll() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return entityManager.findAll(Department.class);
    }

    public Department callUpdate(Department dep) throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException {
        return entityManager.update(dep);
    }

}
