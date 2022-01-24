package com.example.bank;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class EmployeeManagerTest {

    @Test
    void payEmployeeShouldShouldPayAllEmployees() {
        EmployeeRepository employeeRepository = new EmployeeRepositoryStub();
        BankService bankService = new BankServiceStub();
        EmployeeManager employeeManager = new EmployeeManager(employeeRepository, bankService);

        var result = employeeManager.payEmployees();
        var expected = employeeRepository.findAll().size();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void payEmployeesShouldOnlyPayValidEmployees() {
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.findAll()).thenReturn(List.of(new Employee("1", 26500.00)));

        BankService bankService = mock(BankService.class);
        doThrow(RuntimeException.class).when(bankService).pay("1", 26500.00);

        EmployeeManager employeeManager = new EmployeeManager(employeeRepository, bankService);


        var result = employeeManager.payEmployees();

        assertThat(result).isZero();
    }

    @Test
    void payEmployeesShouldPayUpdatedSalaries() {
        Employee employee1 = new Employee("1", 25_000.00);
        Employee employee2 = new Employee("2", 250_000.00);
        employee2.setSalary(25_000.00);

        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        when(employeeRepository.findAll()).thenReturn(List.of(employee1, employee2));

        BankService bankService = mock(BankService.class);
        doThrow(RuntimeException.class).when(bankService).pay(employee2.getId(), 250_000.00);

        EmployeeManager employeeManager = new EmployeeManager(employeeRepository, bankService);


        var result = employeeManager.payEmployees();

        assertThat(result).isEqualTo(2);
        assertTrue((employee1.isPaid()));
        assertTrue((employee2.isPaid()));
    }

}
