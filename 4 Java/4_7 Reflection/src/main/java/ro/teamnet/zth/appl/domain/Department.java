package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

@Table(name="DEPARTMENTS")
public class Department {
    @Id(name="DEPARTMENT_ID")
    private long id;

    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;

    @Column(name="LOCATION_ID")
    private Location location;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Department)){
            return false;
        }
        Department department = new Department();
        if(id != department.id || departmentName != department.departmentName || location != department.location){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String dep = new String();
        dep = "ID: " + this.id + "\n Department Name: " + this.departmentName + "\n Location" + this.location;
        return dep;
    }
}
