package com.example.bank;

import java.util.List;

public class EmployeeRepositoryStub implements EmployeeRepository {
    @Override
    public List<Employee> findAll() {
        return List.of(
                new Employee("1", 27000.00),
                new Employee("2", 35000.00),
                new Employee("3", 23500.00)
        );
    }

    @Override
    public Employee save(Employee e) {
        return null;
    }
}
