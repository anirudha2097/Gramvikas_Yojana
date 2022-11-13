package com.masai.demo;

import java.util.Scanner;

import com.masai.usecases.AllocateProjectToGPM;
import com.masai.usecases.AssignEmployeeToProject;
import com.masai.usecases.CreateProject;
import com.masai.usecases.GetAllDetailsOfGPM;
import com.masai.usecases.GetAllEmployeeDetails;
import com.masai.usecases.GetAllProjectDetails;
import com.masai.usecases.GetEmployeeDetailsByProjectName;
import com.masai.usecases.GetEmpolyeeDetailsByEid;
import com.masai.usecases.GetWorkingDaysAndDurationOfEmployee;
import com.masai.usecases.LoginBDO;
import com.masai.usecases.LoginToGPM;
import com.masai.usecases.ExitApplication;
import com.masai.usecases.RegisterEmployee;
import com.masai.usecases.RegisterGramPanchayatMember;

public class Menu {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Select from following option:");
		System.out.println("1. BDO Login:");
		System.out.println("2. GPM Login:");
		System.out.println("3. Exit Application");
		int opt = sc.nextInt();
		
		if(opt == 1 | opt==2 | opt==3) {
			switch(opt) {
				case 1: LoginBDO.main(args);
				break;
				case 2: LoginToGPM.main(args);
				break;
				case 3: System.out.println("Thank You for visiting.");
				break;
			}
		} else {
			System.out.println("");
			System.out.println("Please select from the option");
			System.out.println("------------------------------");
			Menu.main(args);
		}
		sc.close();
	}
	
	public static void BDOMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("Select from following options:");
		System.out.println("------------------------------");
		System.out.println("1. Register Gram Panchayat Member.");
		System.out.println("2. Get All Details of Gram Panchayat Members.");
		System.out.println("3. Create Project.");
		System.out.println("4. Get All Project Details.");
		System.out.println("5. Allocate Project To Gram Panchayat Member.");
		System.out.println("6. Get Employee details by Project name.");
		System.out.println("0. Logout.");
		System.out.println("99. Exit Application.");
		int i = sc.nextInt();
		switch(i) {
			case 1: RegisterGramPanchayatMember.main(null);
					break;
			case 2: GetAllDetailsOfGPM.main(null);
					BDOMenu();
					break;
			case 3: CreateProject.main(null);
					BDOMenu();
					break;
			case 4: GetAllProjectDetails.main(null);
					BDOMenu();
					break;
			case 5: AllocateProjectToGPM.main(null);
					BDOMenu();
					break;
			case 6: GetEmployeeDetailsByProjectName.main(null);
					BDOMenu();
					break;
			case 0: System.out.println("Logout Successful.\n");
					Menu.main(null);
					break;
			case 99: ExitApplication.main(null);
					break;
			default :System.out.println("Option not found."); 
					BDOMenu();
					break;
		}
		
		sc.close();
	}
	public static void GPMMenu() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("Select from following options:");
		System.out.println("------------------------------");
		System.out.println("1. Register Employee.");
		System.out.println("2. Get All Employee Details.");
		System.out.println("3. Get Employee Details by Employee ID.");
		System.out.println("4. Assign Employee to Project.");
		System.out.println("5. Get working days of Employee in a Project.");
		System.out.println("0. Logout");
		System.out.println("99. Exit Application.");
		int i = sc.nextInt();
		switch(i) {
			case 1: RegisterEmployee.main(null);
					GPMMenu();
					break;
			case 2: GetAllEmployeeDetails.main(null);
					GPMMenu();
					break;
			case 3: GetEmpolyeeDetailsByEid.main(null);
					GPMMenu();
					break;
			case 4: AssignEmployeeToProject.main(null);
					GPMMenu();
					break;
			case 5: GetWorkingDaysAndDurationOfEmployee.main(null);
					GPMMenu();
					break;
			case 0: System.out.println("Logout Successful.\n");
					Menu.main(null);
					break;
			case 99: ExitApplication.main(null);
					break;
			default : System.out.println("Option not found.");
					GPMMenu();
					break;
					
		}
		
	}
}
