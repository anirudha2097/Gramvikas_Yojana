package com.masai.usecases;

import java.util.Scanner;

import com.masai.dao.GramPanchayatMemberDao;
import com.masai.dao.GramPanchayatMemberDaoImpl;
import com.masai.demo.Menu;
import com.masai.exceptions.EmployeeException;
import com.masai.model.Employee;

public class GetEmpolyeeDetailsByEid {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("Enter Employee ID:");
		int eid = sc.nextInt();
	
		GramPanchayatMemberDao gpm = new GramPanchayatMemberDaoImpl();
		
		Employee emp;
			
		try {
			emp = gpm.getDetailsOfEmployeeByEid(eid);
			System.out.println("");
			System.out.println("Name: "+emp.getName()+"\nEmployee ID: "+emp.getEid()+"\nAddress: "+emp.getAddress()+"\nAge: "+emp.getAge()+"\nSalary: "+emp.getSalary());
			
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
