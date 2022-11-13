package com.masai.usecases;

import java.util.Scanner;

import com.masai.dao.BlockDevelopmentOfficerDao;
import com.masai.dao.BlockDevelopmentOfficerDaoImpl;
import com.masai.demo.Menu;
import com.masai.exceptions.GramPanchayatMemberException;
import com.masai.exceptions.ProjectException;

public class AllocateProjectToGPM {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("Enter Project ID:");
		int pid = sc.nextInt();
		
		System.out.println("Enter Gram Panchayat Member ID;");
		int gpmid = sc.nextInt();
		
		BlockDevelopmentOfficerDao bdo = new BlockDevelopmentOfficerDaoImpl();
		
		try {
			try {
				String message = bdo.allocateProjectToGpm(pid, gpmid);
				System.out.println("");
				System.out.println(message);
				System.out.println("");
			} catch (GramPanchayatMemberException e) {
				System.out.println(e.getMessage());
			}
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
