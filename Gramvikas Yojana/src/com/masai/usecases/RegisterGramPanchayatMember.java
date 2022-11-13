package com.masai.usecases;

import java.util.Scanner;

import com.masai.dao.BlockDevelopmentOfficerDao;
import com.masai.dao.BlockDevelopmentOfficerDaoImpl;
import com.masai.demo.Menu;
import com.masai.exceptions.GramPanchayatMemberException;
import com.masai.model.GramPanchayatMember;

public class RegisterGramPanchayatMember {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("Enter Gram Panchayat Member Details");
		
		System.out.println("Enter Name:");
		String name = sc.nextLine();
		
		System.out.println("Enter Address:");
		String address = sc.next();
		
		System.out.println("Enter Email:");
		String email = sc.next();
		
		System.out.println("Enter Password:");
		String password = sc.next();
		
		GramPanchayatMember gpm = new GramPanchayatMember();
		gpm.setName(name);
		gpm.setAddress(address);
		gpm.setEmail(email);
		gpm.setPassword(password);
		
		BlockDevelopmentOfficerDao bdo = new BlockDevelopmentOfficerDaoImpl();
		try {
			String message = bdo.registerGpm(gpm);
			System.out.println("");
			System.out.println("Name: "+name+"\nAddress: "+address+"\nEmail: "+email);
			System.out.println("");
			System.out.println(message);
		} catch (GramPanchayatMemberException e) {
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
