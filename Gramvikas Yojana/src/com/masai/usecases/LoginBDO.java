package com.masai.usecases;

import java.util.Scanner;

import com.masai.dao.BlockDevelopmentOfficerDao;
import com.masai.dao.BlockDevelopmentOfficerDaoImpl;
import com.masai.demo.Menu;
import com.masai.exceptions.BDOException;

public class LoginBDO {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("Login To BDO");
		System.out.println("--------------");
		System.out.println("Enter Username:");
		String username = sc.next();

		System.out.println("Enter Password:");
		String password = sc.next();
		
		if(username.equals("admin")) {
			if(password.equals("admin123")) {
				System.out.println("");
				System.out.println("Welcom "+username);
				System.out.println("--------------------------------");
				System.out.println("");
				Menu.BDOMenu();
			} else {
				System.out.println("");
				System.out.println("Incorrect password please try again.");
				LoginBDO.main(args);
			}
		} else {
			System.out.println("");
			System.out.println("Wrong credentials please try again");
			LoginBDO.main(args);
		}
		
		
		sc.close();
	}

}
