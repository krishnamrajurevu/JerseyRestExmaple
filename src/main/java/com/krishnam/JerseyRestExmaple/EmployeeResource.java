package com.krishnam.JerseyRestExmaple;

import java.util.List;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;



@Path("employees")
public class EmployeeResource {
	

	EmployeeRepository repo = new EmployeeRepository();
	
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Employee> getAllEmployees(){
		
		return repo.getAllEmployees();
	}
	
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("employee")
	public Employee createEmployee(Employee emp) {
		repo.createEmployee(emp);
		return emp;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("employee/{id}")
	public Employee getEmployee(@PathParam("id") int id){
		return repo.getEmployee(id);
	}
	
	@PUT
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("employee")
	public Employee updateEmployee(Employee emp) {
		repo.updateEmployee(emp);
		return emp;
	}
	
	@DELETE
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("employee/{id}")
	public Employee deleteEmployee(@PathParam("id") int id) {
		
		Employee emp = repo.getEmployee(id);
		if(emp.getId() != 0 )
			repo.deleteEmployee(id);
		return emp;
	}
	
	
	

}
