package com.example.bank;

import java.util.Objects;

public class Employee {

	private String id;
	private double salary;
	private boolean paid;

	public Employee(String id, double salary) {
		this.id = id;
		this.salary = salary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", salary=" + salary + "]";
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Employee employee)) return false;

		if (Double.compare(employee.salary, salary) != 0) return false;
		if (paid != employee.paid) return false;
		return Objects.equals(id, employee.id);
	}

	@Override
	public final int hashCode() {
		int result;
		long temp;
		result = id != null ? id.hashCode() : 0;
		temp = Double.doubleToLongBits(salary);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (paid ? 1 : 0);
		return result;
	}
}
