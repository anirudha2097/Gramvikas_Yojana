package com.masai.usecases;

import java.util.List;

import com.masai.dao.GramPanchayatMemberDao;
import com.masai.dao.GramPanchayatMemberDaoImpl;
import com.masai.demo.Menu;
import com.masai.exceptions.EmployeeException;
import com.masai.model.Employee;

public class GetAllEmployeeDetails {

	public static void main(String[] args) {
		
		GramPanchayatMemberDao gpm = new GramPanchayatMemberDaoImpl();

		try {
			List<Employee> emplist = gpm.getAllEmployeeDetails();
			System.out.println("");
			emplist.forEach(emp -> System.out.println("Name: "+emp.getName()+"\nEmployee ID: "+emp.getEid()+"\nAddress: "+emp.getAddress()+"\nAge: "+emp.getAge()+"\nSalary: "+emp.getSalary()+"\n"));
			
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
		}
		
		finally {
			System.out.println("---------------------------------------------------------------");
			System.out.println("");
			Menu.GPMMenu();
		}
	}

}
