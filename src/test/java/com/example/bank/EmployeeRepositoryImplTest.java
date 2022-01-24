package com.example.bank;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeRepositoryImplTest {

    @Test
    void findAllShouldInitiallyReturnAnEmptyList() {
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();

        var result = employeeRepository.findAll();

        assertThat(result).isEmpty();
    }

}
