package com.masai.usecases;

import java.util.List;
import java.util.Scanner;

import com.masai.dao.GramPanchayatMemberDao;
import com.masai.dao.GramPanchayatMemberDaoImpl;
import com.masai.demo.Menu;
import com.masai.exceptions.EmployeeException;
import com.masai.exceptions.ProjectException;

public class GetWorkingDaysAndDurationOfEmployee {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("Total wages of employee in a project.");
		System.out.println("Enter Project ID:");
		int pid = sc.nextInt();
		
		try {
			GramPanchayatMemberDao gpm = new GramPanchayatMemberDaoImpl();
			List<String> empDetails;
			try {
				empDetails = gpm.getDaysOfWorkingAndWagesOfEmployeeInAProject(pid);
				System.out.println("");
				empDetails.forEach(emp -> System.out.println(emp));
				
			} catch (EmployeeException e) {
				System.out.println(e.getMessage());
			}
			
			
		} catch (ProjectException p) {
			System.out.println(p.getMessage());
		}

		finally {
			System.out.println("---------------------------------------------------------------");
			System.out.println("");
			Menu.GPMMenu();
		}
		sc.close();
	}

}
