package com.example.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final List<Employee> employees;

    public EmployeeRepositoryImpl() {
        employees = new ArrayList<>();
    }

    @Override
    public List<Employee> findAll() {
        return Collections.unmodifiableList(employees);
    }

    @Override
    public Employee save(Employee e) {
        employees.add(e);
        return e;
    }

}
