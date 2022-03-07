package com.krishnam.JerseyRestExmaple;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
	
	Connection con= null;
	
	public EmployeeRepository() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");  
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase","root","Krishna@143");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}
	
	public List<Employee> getAllEmployees(){
		List<Employee> empList = new ArrayList<Employee>();
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from employees");
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setSalary(rs.getInt(3));
				empList.add(emp);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return empList;
	}
	
	public void createEmployee(Employee emp) {
		
		try {
			PreparedStatement ps = con.prepareStatement("insert into employees values(?,?,?)");
			ps.setInt(1, emp.getId());
			ps.setString(2, emp.getName());
			ps.setInt(3, emp.getSalary());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Employee getEmployee(int id) {
		Employee emp = new Employee();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from employees where id="+id);
			if(rs.next()) {
				emp.setId(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setSalary(rs.getInt(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	public void updateEmployee(Employee emp) {
		try {
			PreparedStatement ps = con.prepareStatement("update employees set name = ?, salary = ? where id = ?");
			ps.setString(1, emp.getName());
			ps.setInt(2, emp.getSalary());
			ps.setInt(3, emp.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void deleteEmployee(int id) {
		try {
			PreparedStatement ps = con.prepareStatement("delete from employees where id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
