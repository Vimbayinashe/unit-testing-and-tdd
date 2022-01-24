package com.example.bank;

import nl.jqno.equalsverifier.EqualsVerifier;
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

    @Test
    void toStringShouldContainEmployeeIdAndSalary() {
        Employee employee = new Employee("101", 25000);

        var result = employee.toString();

        assertThat(result).contains("id=101").contains("salary=25000");
    }

    @Test
    void verifyEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(Employee.class).verify();
    }

}
