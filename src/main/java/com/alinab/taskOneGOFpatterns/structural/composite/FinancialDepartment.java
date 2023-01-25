package com.alinab.taskOneGOFpatterns.structural.composite;

public class FinancialDepartment implements Department {

    private String name;

    public FinancialDepartment(String name) {
        this.name = name;
    }

    @Override
    public void printDepartmentSpecialization() {
        System.out.println(name + " specializes in finance");
    }
}
