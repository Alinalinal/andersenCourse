package com.alinab.taskOneGOFpatterns.structural.composite;

public class Main {

    public static void main(String[] args) {
        Department salesDepartment = new SalesDepartment("Sales Department");
        Department financialDepartment = new FinancialDepartment("Financial Department");

        HeadDepartment headDepartment = new HeadDepartment();
        headDepartment.addDepartment(salesDepartment);
        headDepartment.addDepartment(financialDepartment);

        headDepartment.printDepartmentSpecialization();
    }
}
