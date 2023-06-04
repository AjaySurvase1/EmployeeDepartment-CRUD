package org.jsp.employeeApp.controller;

import java.util.Scanner;

import org.jsp.employeeApp.dao.Departmentdao;
import org.jsp.employeeApp.dao.Employeedao;
import org.jsp.employeeApp.dto.Department;
import org.jsp.employeeApp.dto.Employee;

public class EmployeeApplication {
	static Scanner sc=new Scanner(System.in);
	static Employeedao edao=new Employeedao();
	static Departmentdao ddao=new Departmentdao();
	public static void main(String[] args) {
		boolean check=true;
		while(check) {
			System.out.println("1.save the department");
			System.out.println("2.add the employees to the department");
			System.out.println("3.update the department");
			System.out.println("4.update employee in the department");
			System.out.println("5.view employees by department id");
			System.out.println("6.view employees by department name");
			System.out.println("7.delete the employee in the department");
			
			int choice=sc.nextInt();
			switch(choice) {
			case 1:
				addD();
			break;	
			case 2:
				addE();
			break;
			case 3:
				uDept();
			break;
			case 4:
				uEmp();
			break;
			case 5:
				viewEmp();
			break;
			case 6:
				viewEByDeptN();
			break;
			case 7:
				dEmployee();
			}
		}
		
	}
	
	public static void addD() {
		System.out.println("enter name and location");
		String name=sc.next();
		String loc=sc.next();

		Department dept=new Department();
		dept.setName(name);
		dept.setLocation(loc);
		dept=ddao.saveDept(dept);
		System.out.println("employee department saved with id"+dept.getId());
	}
	
	public static void addE() {
//		Department dept=ddao.fetchDeptBID(id);
		System.out.println("enter name ,desg,salary,phone and password");
		String name=sc.next();
		String desg=sc.next();
		double salary=sc.nextDouble();
		long phone=sc.nextLong();
		String password=sc.next();
		
		Employee emp=new Employee();
		emp.setName(name);
		emp.setDesg(desg);
		emp.setSalary(salary);
		emp.setPhone(phone);
		emp.setPassword(password);
		int dept_id=sc.nextInt();
		emp=edao.saveEmp(emp, dept_id);
		
	}
	public static void uDept() {
		System.out.println("enter name and location");
		int id=sc.nextInt();
		String name=sc.next();
		String loc=sc.next();
		
		Department dept=new Department();
		dept.setId(id); 
		dept.setName(name);
		dept.setLocation(loc);
		dept=ddao.UpdateDept(dept);
	}

	public static void uEmp() {
		System.out.println("enter id, name ,desg,salary,phone and password");
		int id=sc.nextInt();
		String name=sc.next();
		String desg=sc.next();
		double salary=sc.nextDouble();
		long phone=sc.nextLong();
		String password=sc.next();
		
		Employee emp=new Employee();
		emp.setId(id);
		emp.setName(name);
		emp.setDesg(desg);
		emp.setSalary(salary);
		emp.setPhone(phone);
		emp.setPassword(password);
		int dept_id=sc.nextInt();
		emp=edao.updateEmp(emp, dept_id);
	}
	
	public static void viewEmp() {
		System.out.println("enter dept id");
		int id=sc.nextInt();
		for(Employee e:edao.viewEmpByDid(id)) {
			System.out.println(e.getName());
			System.out.println(e.getDesg());
			System.out.println(e.getSalary());
			System.out.println(e.getPhone());
		}
	}
	public static void viewEByDeptN() {
		System.out.println("enter dept name");
		String name=sc.next();
		for(Employee e:edao.viewEmpByDName(name)) {
			System.out.println(e.getName());
			System.out.println(e.getDesg());
			System.out.println(e.getSalary());
			System.out.println(e.getPhone());
		}
	}
	public static void dEmployee() {
		System.out.println("enter dept id to delelte the employees");
		int did=sc.nextInt();
		boolean delete=edao.deleteEmp(did);
		if(delete) {
			System.out.println("employees deleted having dept id " +did);
		}
	}
}
