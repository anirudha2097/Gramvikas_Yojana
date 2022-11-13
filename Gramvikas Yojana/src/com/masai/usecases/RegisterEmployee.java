package com.masai.usecases;

import java.util.Scanner;

import com.masai.dao.GramPanchayatMemberDao;
import com.masai.dao.GramPanchayatMemberDaoImpl;
import com.masai.demo.Menu;
import com.masai.exceptions.EmployeeException;
import com.masai.model.Employee;

public class RegisterEmployee {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("Enter Details of Employee");
		
		System.out.println("Enter Name:");
		String name = sc.nextLine();
		
		System.out.println("Enter Address:");
		String address = sc.next();
		
		System.out.println("Enter Age:");
		String age = sc.next();
		
		System.out.println("Enter Salary:");
		int salary = sc.nextInt();
		
		Employee emp = new Employee();
		emp.setName(name);
		emp.setAddress(address);
		emp.setAge(age);
		emp.setSalary(salary);
		
		try {
			GramPanchayatMemberDao gpm = new GramPanchayatMemberDaoImpl();
			String result = gpm.createEmployee(emp);
			System.out.println("");
			System.out.println("Name: "+name+"\nAddress: "+address+"\nAge: "+age+"\nSalary: "+salary+"\n");
			System.out.println("");
			System.out.println(result);  
			
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
		}

		finally {
			System.out.println("---------------------------------------------------------------");
			System.out.println("");
			Menu.GPMMenu();
		}
		sc.close();
		
	}

}
