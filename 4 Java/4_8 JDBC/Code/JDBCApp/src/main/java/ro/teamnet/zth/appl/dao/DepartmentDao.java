package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EnityManagerImpl;
import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.appl.domain.Department;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DepartmentDao {

    EnityManagerImpl manager = new EnityManagerImpl();

    public Department findById(Long id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return manager.findById(Department.class, id);
    }

    public Long getNextIdVal(String columnIdName) throws SQLException, ClassNotFoundException {
        return manager.getNextIdVal("DEPARTMENTS", columnIdName);
    }

    public Object insert(Department entity) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return manager.insert(entity);
    }

    public List<Department> findAll() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return manager.findAll(Department.class);
    }

    public Department update(Department entity) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return manager.update(entity);
    }

    public void delete(Object entity) throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException {
        manager.delete(entity);
    }

    public void findByParams(Class<Department> entityClass, Map<String, Object> params) throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException {
        manager.findByParams(entityClass, params);
    }
}
