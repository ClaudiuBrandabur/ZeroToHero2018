package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.api.em.EntityUtils;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;
import java.util.Map;

public class DepartmentDao {

    EntityManagerImpl entityManager = new EntityManagerImpl();

    public Department findById(Long id){
        return entityManager.findById(Department.class,id);
    }

    public long getNextIdVal(String columnIdName){
        return entityManager.getNextIdVal(EntityUtils.getTableName(Department.class),columnIdName);
    }

    public Department insert(Department department){
        return (Department) entityManager.insert(department);
    }

    public List <Department> findAll(){
        return entityManager.findAll(Department.class);
    }

    public Department update(Department department){
        return entityManager.update(department);
    }

    public void delete(Department department){
        entityManager.delete(department);
    }
}
