package com.masai.usecases;

import java.util.Scanner;

import com.masai.dao.GramPanchayatMemberDao;
import com.masai.dao.GramPanchayatMemberDaoImpl;
import com.masai.demo.Menu;
import com.masai.exceptions.GramPanchayatMemberException;
import com.masai.model.GramPanchayatMember;

public class LoginToGPM {

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("Login to GPM account");
		
		System.out.println("Enter Username:");
		String username = sc.next();
		
		System.out.println("Enter Password:");
		String password = sc.next();
		
		try {
			GramPanchayatMemberDao gpm = new GramPanchayatMemberDaoImpl();
			
			GramPanchayatMember g = gpm.login(username, password);
			
			if(g.getEmail().equals(username) & g.getPassword().equals(password)) {
				System.out.println("");
				System.out.println("Welcome, "+g.getName().toUpperCase()+"!");
				Menu.GPMMenu();
			} 
			
		} catch(GramPanchayatMemberException e) {
			System.out.println(e.getMessage());
			LoginToGPM.main(args);
		}
		finally {
			System.out.println("---------------------------");
			System.out.println("");
		}
			sc.close();
			
	}

}
