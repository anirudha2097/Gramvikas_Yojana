package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.exceptions.BDOException;
import com.masai.exceptions.EmployeeException;
import com.masai.exceptions.GramPanchayatMemberException;
import com.masai.exceptions.ProjectException;
import com.masai.model.Employee;
import com.masai.model.GramPanchayatMember;
import com.masai.model.Project;
import com.masai.utility.DButil;

public class BlockDevelopmentOfficerDaoImpl implements BlockDevelopmentOfficerDao{

	@Override
	public String createProject(Project project) throws ProjectException{
		String message = "Project was not created...";
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps1 = conn.prepareStatement("select * from Projects where pid = ?");
			ps1.setInt(1, project.getPid());
			
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next()) {
				throw new ProjectException("Project already present. Duplicate project not allowed.");
			} else {
				PreparedStatement ps2 = conn.prepareStatement("insert into projects values(?,?,?,?,?)");
				
				ps2.setInt(1, project.getPid());
				ps2.setString(2, project.getName());
				ps2.setString(3, project.getAmount());
				ps2.setString(4, project.getLocation());
				ps2.setInt(5, project.getDuration());
				
				int x = ps2.executeUpdate();
				
				if(x>0) {
					message = "Project created successful";
				} else {
					throw new ProjectException("Project not created!");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProjectException(e.getMessage());
		}
		
		return message;
	}

	@Override
	public List<Project> getAllProjectDetails() throws ProjectException {
		
		List<Project> projectList = new ArrayList<>();
		
		try (Connection conn = DButil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from Projects");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Project p = new Project();
				p.setPid(rs.getInt("pid"));
				p.setName(rs.getString("name"));
				p.setAmount(rs.getString("amount"));
				p.setLocation(rs.getString("location"));
				p.setDuration(rs.getInt("duration"));
				
				projectList.add(p);
			}
			
			if(projectList.size()==0) {
				throw new ProjectException("No projects found..");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProjectException(e.getMessage());
		}
		
		return projectList;
	}

	@Override
	public String allocateProjectToGpm(int pid, int gpmid) throws ProjectException, GramPanchayatMemberException {
		String message = "Project was not allocated";
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps1 = conn.prepareStatement("select * from projects where pid=?");
			
			ps1.setInt(1, pid);
			ResultSet rs1 = ps1.executeQuery();
			
			PreparedStatement ps4 = conn.prepareStatement("select * from gpm_projects where pid=?");
			ps4.setInt(1, pid);
			ResultSet rs4 = ps4.executeQuery();
			
			if(rs4.next()) {
				throw new ProjectException("The project is already allocated.");
			} else {
				if(rs1.next()) {
					PreparedStatement ps2 = conn.prepareStatement("select * from gpm where gpmid=?");
					ps2.setInt(1, gpmid);
					
					String pname = rs1.getString("name");
					
					ResultSet rs2 = ps2.executeQuery();
					
					if(rs2.next()) {
						PreparedStatement ps3 = conn.prepareStatement("insert into gpm_projects values(?,?)");
						ps3.setInt(1, pid);
						ps3.setInt(2, gpmid);
						
						String gpmname = rs2.getString("name");
						int x = ps3.executeUpdate();
						if(x>0) {
							message = "Project '" +pname+ "' Allocated to Gram Panchayat Member "+gpmname;
						}
						
					} else {
						throw new GramPanchayatMemberException("Gram Panchayat Member not exists with GPM ID:"+gpmid);
					}
					
				} else {
					throw new ProjectException("Project not exists with Project ID:"+pid);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProjectException(e.getMessage());
		}
		
		return message;
	}

	@Override
	public List<Employee> getDetailsOfEmployeesByProjectName(String projectName) throws ProjectException, EmployeeException {
		List<Employee> empList = new ArrayList<>();
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select e.eid, e.name, e.address, e.age, e.salary, p.name from Employee e INNER JOIN Projects p INNER JOIN emp_projects ep ON ep.eid=e.eid AND ep.pid=p.pid AND p.name=?");
			ps.setString(1, projectName);
			
			ResultSet rs = ps.executeQuery();
			
			PreparedStatement ps2 = conn.prepareStatement("select * from Projects where name=?");
			ps2.setString(1, projectName);
			
			ResultSet rs2 = ps2.executeQuery();
			if(rs2.next()) {
				while(rs.next()) {
					Employee emp = new Employee();
					
					emp.setEid(rs.getInt("eid"));
					emp.setName(rs.getString("name"));
					emp.setAddress(rs.getString("address"));
					emp.setAge(rs.getString("age"));
					emp.setSalary(rs.getInt("salary"));
					
					empList.add(emp);
				}
			} else {
				throw new ProjectException("Project not found");
			}
			if(empList.size()==0) {
				throw new EmployeeException("No one employee was assigned to this project.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProjectException(e.getMessage());
		}
		
		return empList;
	}

	@Override
	public String registerGpm(GramPanchayatMember gpm) throws GramPanchayatMemberException {
		String message = "Not Registerd...";
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("insert into GPM(name,address,email,password) value(?,?,?,?)");
			
			ps.setString(1, gpm.getName());
			ps.setString(2, gpm.getAddress());
			ps.setString(3, gpm.getEmail());
			ps.setString(4, gpm.getPassword());
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				message = "Gram Panchayat Member Registered";
			} else {
				throw new GramPanchayatMemberException("Gram Panchayat Member was not Registered..");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GramPanchayatMemberException(e.getMessage());
		}
		
		return message;
	}

	@Override
	public List<GramPanchayatMember> getAllGpm() throws GramPanchayatMemberException {
		List<GramPanchayatMember> gpmlist = new ArrayList<>();
		
		try (Connection conn = DButil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from gpm");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				GramPanchayatMember gpm = new GramPanchayatMember();
				gpm.setGpmid(rs.getInt("gpmid"));
				gpm.setName(rs.getString("name"));
				gpm.setAddress(rs.getString("address"));
				gpm.setEmail(rs.getString("email"));
				gpm.setPassword(rs.getString("password"));
				
				gpmlist.add(gpm);
			}
			
			if(gpmlist.size()==0) {
				throw new GramPanchayatMemberException("Gram Panchayat Members not found.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new GramPanchayatMemberException(e.getMessage());
		}
		
		
		return gpmlist;
	}

}
