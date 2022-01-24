package com.example.bank;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeTest {

    @Test
    void setIdShouldChangeEmployeeId() {
        Employee employee = new Employee("1", 25000);
        employee.setId("101");

        var id = employee.getId();

        assertThat(id).isEqualTo("101");
    }

}
