package com.masai.usecases;

import java.sql.SQLException;
import java.util.Scanner;

import com.masai.dao.GramPanchayatMemberDao;
import com.masai.dao.GramPanchayatMemberDaoImpl;
import com.masai.demo.Menu;
import com.masai.exceptions.EmployeeException;
import com.masai.exceptions.ProjectException;

public class AssignEmployeeToProject {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("Assign Employee to Project");
		System.out.println("Enter Employee ID:");
		int eid = sc.nextInt();
		
		System.out.println("Enter Project ID:");
		int pid = sc.nextInt();
		
		try {
			GramPanchayatMemberDao gpm = new GramPanchayatMemberDaoImpl();
			try {
				String message = gpm.assignEmployeeToProject(eid, pid);
				System.out.println("");
				System.out.println(message);
			} catch (ProjectException e) {
				System.out.println(e.getMessage());
			}
			
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
