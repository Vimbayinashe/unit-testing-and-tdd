package com.example.bank;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeRepositoryImplTest {

    @Test
    void findAllShouldInitiallyReturnAnEmptyList() {
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();

        var result = employeeRepository.findAll();

        assertThat(result).isEmpty();
    }

    @Test
    void findAllShouldAllEmployees() {
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl(List.of(
                new Employee("200", 25750.00),
                new Employee("201", 26500.00)
        ));

        var result = employeeRepository.findAll();

        assertThat(result).hasSize(2);
    }

    @Test
    void saveShouldAddAnEmployeeToTheRepository() {
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
        employeeRepository.save(new Employee("1", 23000.00));

        var result = employeeRepository.findAll();

        assertThat(result).contains(new Employee("1", 23000.00)).hasSize(1);
    }

    @Test
    void saveShouldUpdateAnExistingEmployeeId() {
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl(List.of(
                new Employee("1", 25000.0)
        ));
        employeeRepository.save(new Employee("1", 35000));

        var result = employeeRepository.findAll();

        assertThat(result)
                .contains(new Employee("1", 35000.00))
                .doesNotContain(new Employee("1", 25000.00));
    }

}
