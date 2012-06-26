package com.test;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/employee")
public class EmployeeService {

	private List<Employee> employees = new ArrayList<Employee>();

	public EmployeeService() {
		Employee employee1 = new Employee("1", "Rohit", "Ghatol");
		Employee employee2 = new Employee("2", "Amit", "Dixit");
		employees.add(employee1);
		employees.add(employee2);
	}

	@GET()
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Employee> getEmployees() {
		return employees;
	}

	@GET()
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Employee getEmployee(@PathParam("id") String id) {
		Employee matchedEmployee = null;
		for (Employee employee : employees) {
			if (employee.getId().equals(id)) {
				matchedEmployee = employee;
			}
		}
		return matchedEmployee;
	}

	@PUT()
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Employee createEmployee(Employee employee) {
		Employee existingEmployee = getEmployee(employee.getId());
		if (null == existingEmployee) {
			employees.add(employee);
		}
		return employee;
	}

	@POST()
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Employee updateEmployee(@PathParam("id") String id, Employee employee) {
		Employee existingEmployee = getEmployee(id);
		if (null != existingEmployee) {
			existingEmployee.setFirstName(employee.getFirstName());
			existingEmployee.setLastName(employee.getLastName());
		}
		return existingEmployee;
	}

	@DELETE()
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteEmployee(@PathParam("id") String id) {
		Employee employee = getEmployee(id);
		if (null != employee) {
			employees.remove(employee);
		}

	}
}
