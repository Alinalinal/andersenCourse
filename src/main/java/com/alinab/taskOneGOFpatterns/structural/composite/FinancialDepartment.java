package com.alinab.taskOneGOFpatterns.structural.composite;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FinancialDepartment implements Department {

    private String name;

    @Override
    public void printDepartmentSpecialization() {
        System.out.println(name + " specializes in finance");
    }
}
