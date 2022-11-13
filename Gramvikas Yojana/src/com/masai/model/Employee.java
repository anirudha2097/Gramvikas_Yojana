package com.masai.model;

public class Employee {

	private int eid;
	private String name;
	private String address;
	private String age;
	private int salary;
	
	

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int eid, String name, String address, String age, int salary) {
		super();
		this.eid = eid;
		this.name = name;
		this.address = address;
		this.age = age;
		this.salary = salary;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", name=" + name + ", address=" + address + ", age=" + age + ", salary="
				+ salary + "]";
	}

	
	
	
}
