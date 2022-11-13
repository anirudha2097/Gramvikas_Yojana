package com.masai.dao;

import java.util.List;

import com.masai.exceptions.EmployeeException;
import com.masai.exceptions.GramPanchayatMemberException;
import com.masai.exceptions.ProjectException;
import com.masai.model.Employee;

public interface GramPanchayatMemberDao {

	public boolean login(String username , String password) throws GramPanchayatMemberException;
	
	public String createEmployee(Employee employee) throws EmployeeException;
	
	public Employee getDetailsOfEmployeeByEid(int eid) throws EmployeeException;
	
	public List<Employee> getAllEmployeeDetails() throws EmployeeException;
	
	public String assignEmployeeToProject(int eid, int pid) throws EmployeeException, ProjectException;
	
	public List<String> getDaysOfWorkingAndWagesOfEmployeeInAProject(int pid) throws EmployeeException, ProjectException;
}
