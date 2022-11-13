package com.masai.usecases;

import java.util.List;

import com.masai.dao.BlockDevelopmentOfficerDao;
import com.masai.dao.BlockDevelopmentOfficerDaoImpl;
import com.masai.demo.Menu;
import com.masai.exceptions.ProjectException;
import com.masai.model.Project;

public class GetAllProjectDetails {

	public static void main(String[] args) {
		
		BlockDevelopmentOfficerDao bdo = new BlockDevelopmentOfficerDaoImpl();
		try {
			List<Project> projectList = bdo.getAllProjectDetails();
			
			if(projectList.size()!=0) {
				System.out.println("");
				projectList.forEach(project -> System.out.println("Project Name: "+project.getName()+"\nProject ID: "+project.getPid()+"\nAmount: "+project.getAmount()+"\nLocation: "+project.getLocation()+"\nDuration: "+project.getDuration()+"\n"));
			} else {
				throw new ProjectException();
			}
		} catch (ProjectException e) {
			System.out.println(e.getMessage());
		} 
		
		finally {
			System.out.println("---------------------------------------------------------------");
			System.out.println("");
			Menu.BDOMenu();
		}
	}

}
