package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Location;
import ro.teamnet.zth.appl.service.LocationService;
import ro.teamnet.zth.appl.domain.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao = new DepartmentDao();
    private final LocationService locationService =  new LocationServiceImpl();

    @Override
    public List<Department> findAllDepartments() {
        List<Department> departments = departmentDao.getAllDepartments();
        List<Long> locationIds = new ArrayList<>();
        for (Department department : departments){
            locationIds.add(department.getLocation());
        }

    List<Location> locations = locationService.findAllById(locationIds);
    for (Department department : departments){
        for(Location location : locations){
            if(department.getLocation().equals(location.getLocationId())) {
                department.setLocationObj(location);
            }
        }
    }
    return departments;
    }

    @Override
    public Department findOne(Long departmentId) {

        Department department =  departmentDao.findDepartmentById(departmentId);
        Location location = locationService.findOne(department.getLocation());
        department.setLocationObj(location);
        return department;
    }

    @Override
    public Department save(Department department) {
        return null;
    }

    @Override
    public Department update(Department department) {
        return null;
    }

    @Override
    public Department delete(Department department) {
        return null;
    }
}
