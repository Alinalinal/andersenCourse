package com.alinab.taskOneGOFpatterns.structural.composite;

public class SalesDepartment implements Department {

    private String name;

    public SalesDepartment(String name) {
        this.name = name;
    }

    @Override
    public void printDepartmentSpecialization() {
        System.out.println(name + " specializes in sales");
    }
}
