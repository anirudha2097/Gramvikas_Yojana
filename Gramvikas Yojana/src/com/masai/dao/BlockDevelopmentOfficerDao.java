package com.masai.dao;

import java.util.List;

import com.masai.exceptions.BDOException;
import com.masai.exceptions.EmployeeException;
import com.masai.exceptions.GramPanchayatMemberException;
import com.masai.exceptions.ProjectException;
import com.masai.model.Employee;
import com.masai.model.GramPanchayatMember;
import com.masai.model.Project;

public interface BlockDevelopmentOfficerDao {
	
	public String createProject(Project project)throws ProjectException;
	
	public List<Project> getAllProjectDetails()throws ProjectException;
	
	public String allocateProjectToGpm(int pid, int gpmid)throws ProjectException,GramPanchayatMemberException;
	
	public List<Employee> getDetailsOfEmployeesByProjectName(String projectName)throws ProjectException,EmployeeException;
	
	public String registerGpm(GramPanchayatMember gpm)throws GramPanchayatMemberException;
	
	public List<GramPanchayatMember> getAllGpm()throws GramPanchayatMemberException;
}
