package org.jsp.employeeApp.dao;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.jsp.employeeApp.dto.Department;

public class Departmentdao {

	EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction t=manager.getTransaction();
	Scanner sc=new Scanner(System.in);
	
	public Department saveDept(Department d) {
		manager.persist(d);
		t.begin();
		t.commit();
		return d;
		
	}
	
	public Department UpdateDept(Department d) {
		manager.merge(d);
		t.begin();
		t.commit();
		return d;
		
	}
	public Department fetchDeptBID(int id) {
		return manager.find(Department.class, id);
	}
}
