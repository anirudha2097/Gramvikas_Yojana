package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.exceptions.EmployeeException;
import com.masai.exceptions.GramPanchayatMemberException;
import com.masai.exceptions.ProjectException;
import com.masai.model.Employee;
import com.masai.utility.DButil;

public class GramPanchayatMemberDaoImpl implements GramPanchayatMemberDao{

	@Override
	public boolean login(String username, String password) throws GramPanchayatMemberException {
		boolean flag = false;
		
		try (Connection conn = DButil.provideConnection()){
			PreparedStatement ps = conn.prepareStatement("select * from GPM where email=? AND password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				flag = true;
			} else {
				throw new GramPanchayatMemberException("Invalid username or password");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GramPanchayatMemberException(e.getMessage());
		}
		
		return flag;
	}

	

	@Override
	public Employee getDetailsOfEmployeeByEid(int eid) throws EmployeeException{
		Employee emp = new Employee();
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from Employee where eid=?");
			
			ps.setInt(1, eid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				emp.setEid(rs.getInt("eid"));
				emp.setName(rs.getString("name"));
				emp.setAddress(rs.getString("address"));
				emp.setAge(rs.getString("age"));
				emp.setSalary(rs.getInt("salary"));
			} else {
				throw new EmployeeException("Employee not exist with "+eid);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		}
		
		return emp;
	}

	@Override
	public List<Employee> getAllEmployeeDetails() throws EmployeeException{
		
		List<Employee> emplist = new ArrayList<>();
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from Employee");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setEid(rs.getInt("eid"));
				emp.setName(rs.getString("name"));
				emp.setAddress(rs.getString("address"));
				emp.setAge(rs.getString("age"));
				emp.setSalary(rs.getInt("salary"));
				
				emplist.add(emp);
			}
			
			if(emplist.size()==0) {
				throw new EmployeeException("Employee not found..");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		}
		
		
		return emplist;
	}

	@Override
	public String assignEmployeeToProject(int eid, int pid) throws EmployeeException, ProjectException{
		String message = "Employee not assigned to any project";
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps1 = conn.prepareStatement("select * from Employee where eid = ?");
			
			ps1.setInt(1, eid);
			ResultSet rs1 = ps1.executeQuery();
			
			PreparedStatement ps2 = conn.prepareStatement("select * from emp_projects where eid = ?");
			ps2.setInt(1,eid);
			ResultSet rs2 = ps2.executeQuery();
			if(rs2.next()) {
				throw new EmployeeException("Employee "+eid+" already assigned to another project.");
			} else {
				if(rs1.next()) {
					String ename = rs1.getString("name");
					
					PreparedStatement ps3 = conn.prepareStatement("select * from Projects where pid = ?");
					ps3.setInt(1, pid);
					ResultSet rs3 = ps3.executeQuery();
					if(rs3.next()) {
						String pname = rs3.getString("name");
						int duration = rs3.getInt("duration");
						PreparedStatement ps4 = conn.prepareStatement("insert into emp_projects values(?,?,?)");
						ps4.setInt(1, eid);
						ps4.setInt(2, pid);
						ps4.setInt(3, duration);
						int x = ps4.executeUpdate();
						if(x>0) {
							message = "Employee "+ename+" is assigned to Project "+pname+".";
						}
					} else {
						throw new ProjectException("Projects not found.");
					}
				} else {
					throw new EmployeeException("Employee not found.");
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		}
		
		return message;
	}

	@Override
	public List<String> getDaysOfWorkingAndWagesOfEmployeeInAProject(int pid) throws EmployeeException, ProjectException{
		List<String> empSalaryList = new ArrayList<>();
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps1 = conn.prepareStatement("select * from projects where pid=?");
			ps1.setInt(1, pid);
			
			ResultSet rs1 = ps1.executeQuery();
			
			if(rs1.next()) {
				PreparedStatement ps2 = conn.prepareStatement("select e.eid, e.name, ep.duration, e.salary/30*ep.duration Total from Employee e INNER JOIN emp_projects ep ON ep.pid = ? AND e.eid = ep.eid");
				ps2.setInt(1, pid);
				
				ResultSet rs2 = ps2.executeQuery();
				
				while(rs2.next()) {
					String empDetails= "Employee ID: "+rs2.getInt("eid")+", "+"Employee Name: "+rs2.getString("name")+", "+"Working Days: "+rs2.getInt("duration")+", "+"Total Wages: "+rs2.getInt("total")+".";
					empSalaryList.add(empDetails);
				}
				
				if(empSalaryList.size()==0) {
					throw new EmployeeException("No one employee was assigned to the project.");
				}
			} else {
				throw new ProjectException("Project not found.");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProjectException(e.getMessage());
		}
		
		return empSalaryList;
	}



	@Override
	public String createEmployee(Employee employee) throws EmployeeException {
		String message = "Employee not registered";
		
			try (Connection conn = DButil.provideConnection()){
				
				PreparedStatement ps = conn.prepareStatement("insert into employee(name, address, age, salary) values(?,?,?,?)");
				ps.setString(1, employee.getName());
				ps.setString(2, employee.getAddress());
				ps.setString(3, employee.getAge());
				ps.setInt(4, employee.getSalary());
				
				int x = ps.executeUpdate();
				if(x>0) {
					message = "Employee Registered..";
				} else {
					throw new EmployeeException(message);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new EmployeeException(e.getMessage());
			}
		
		return message;
	}

}
