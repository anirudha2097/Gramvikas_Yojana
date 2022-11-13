package com.masai.usecases;

import java.util.List;
import java.util.Scanner;

import com.masai.dao.BlockDevelopmentOfficerDao;
import com.masai.dao.BlockDevelopmentOfficerDaoImpl;
import com.masai.demo.Menu;
import com.masai.exceptions.EmployeeException;
import com.masai.exceptions.ProjectException;
import com.masai.model.Employee;

public class GetEmployeeDetailsByProjectName {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("Get all details of employee of the project");
		System.out.println("Enter Project Name:");
		String projectName = sc.nextLine();
		
		try {
			BlockDevelopmentOfficerDao bdo = new BlockDevelopmentOfficerDaoImpl();
			List<Employee> emplist;
			try {
				emplist = bdo.getDetailsOfEmployeesByProjectName(projectName);
				System.out.println("");
				emplist.forEach(emp -> System.out.println("Name: "+emp.getName()+"\nEmployee ID: "+emp.getEid()+"\nAddress: "+emp.getAddress()+"\nSalary: "+emp.getSalary()+"\n"));
			} catch (EmployeeException e) {
				System.out.println(e.getMessage());
			}
			
		} catch (ProjectException p) {
			System.out.println(p.getMessage());
		}
		
		finally {
			System.out.println("---------------------------------------------------------------");
			System.out.println("");
			Menu.BDOMenu();
		}
		sc.close();
	}

}
