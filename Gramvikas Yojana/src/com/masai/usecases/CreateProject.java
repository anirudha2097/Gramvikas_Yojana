package com.masai.usecases;

import java.util.Scanner;

import com.masai.dao.BlockDevelopmentOfficerDao;
import com.masai.dao.BlockDevelopmentOfficerDaoImpl;
import com.masai.demo.Menu;
import com.masai.exceptions.ProjectException;
import com.masai.model.Project;

public class CreateProject {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Project p1 = new Project();
		System.out.println("");
		System.out.println("Enter Project ID:");
		int pid = sc.nextInt();
		
		System.out.println("Enter Project Name:");
		sc.nextLine();
		String name = sc.nextLine();

		System.out.println("Enter Project Amount:");
		String amount = sc.next();
		
		System.out.println("Enter Project Location:");
		String location = sc.next();
		
		System.out.println("Enter Project Duration(in days):");
		int duration = sc.nextInt();


		p1.setPid(pid);
		p1.setName(name);
		p1.setAmount(amount);
		p1.setLocation(location);
		p1.setDuration(duration);
		

		BlockDevelopmentOfficerDao bdo = new BlockDevelopmentOfficerDaoImpl();
		try {
			String m = bdo.createProject(p1);
			System.out.println("");
			System.out.println("Project Name: "+name+"\nAmount: "+amount+"\nLocation: "+location+"\nDuration: "+duration+"\n");
			System.out.println(m);
		} catch (ProjectException e) {
			System.out.println(e.getMessage());
		}
		finally {
			System.out.println("---------------------------------------------------------------");
			System.out.println("");
			Menu.BDOMenu();
		}
		sc.close();
	}

}
