package com.alinab.taskOneGOFpatterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class HeadDepartment implements Department {

    private List<Department> childDepartments;

    public HeadDepartment() {
        this.childDepartments = new ArrayList<>();
    }

    @Override
    public void printDepartmentSpecialization() {
        childDepartments.forEach(Department::printDepartmentSpecialization);
    }

    public void addDepartment(Department department) {
        childDepartments.add(department);
    }

    public void removeDepartment(Department department) {
        childDepartments.remove(department);
    }
}
