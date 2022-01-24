package com.example.bank;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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

    @Test
    void payEmployeesShouldOnlyPayValidEmployees() {
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.findAll()).thenReturn(List.of(
                new Employee("200", 25750.00),
                new Employee("201", 26500.00)
        ));
        BankService bankService = mock(BankService.class);
        doThrow(RuntimeException.class).when(bankService).pay("201", 26500.00);

        EmployeeManager employeeManager = new EmployeeManager(employeeRepository, bankService);


        var result = employeeManager.payEmployees();

        assertThat(result).isEqualTo(1);
    }

    @Test
    void payEmployeesShouldPayUpdatedSalaries() {
        Employee employee1 = new Employee("201", 25_000.00);
        Employee employee2 = new Employee("201", 250_000.00);
        employee2.setSalary(25_000.00);

        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.findAll()).thenReturn(List.of( employee1, employee2));

        BankService bankService = mock(BankService.class);
        doThrow(RuntimeException.class).when(bankService).pay(employee2.getId(), 250_000.00);

        EmployeeManager employeeManager = new EmployeeManager(employeeRepository, bankService);


        var result = employeeManager.payEmployees();

        assertThat(result).isEqualTo(2);
        assertTrue((employee2.isPaid()));
    }

}
