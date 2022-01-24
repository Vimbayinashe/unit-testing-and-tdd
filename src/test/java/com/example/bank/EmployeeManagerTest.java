package com.example.bank;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeManagerTest {

    @Test
    void payEmployeeShouldShouldPayAllEmployees() {
        EmployeeRepository employeeRepositoryStub = new EmployeeRepositoryStub();
        BankService bankServiceStub = new BankServiceStub();
        EmployeeManager employeeManager = new EmployeeManager(employeeRepositoryStub, bankServiceStub);

        var result = employeeManager.payEmployees();
        var expected = employeeRepositoryStub.findAll().size();

        assertThat(result).isEqualTo(expected);
    }

}