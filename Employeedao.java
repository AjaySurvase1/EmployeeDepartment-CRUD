package org.jsp.employeeApp.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.employeeApp.dto.Department;
import org.jsp.employeeApp.dto.Employee;

public class Employeedao {

	Departmentdao dao=new Departmentdao();
	EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction t=manager.getTransaction();
	Scanner sc=new Scanner(System.in);
	
	public Employee saveEmp(Employee e,int dept_id) {
		
		Department dept=manager.find(Department.class, dept_id);
		if(dept!=null) {
			dept.getEmp().add(e);
			e.setDept(dept);
			manager.persist(e);
			t.begin();
			t.commit();
			return e;
		}
		return null;
	}
	
       public Employee updateEmp(Employee e,int dept_id) {
		
		Department dept=manager.find(Department.class, dept_id);
		if(dept!=null) {
			dept.getEmp().add(e);
			e.setDept(dept);
			manager.merge(e);
			t.begin();
			t.commit();
			return e;
		}
		return null;
	}
       public List<Employee> viewEmpByDid(int dept_id){
    	   Query q=manager.createQuery("select d.emp from Department d where d.id=?1");
    	   q.setParameter(1, dept_id);
    	   return q.getResultList();
    			   
       }
       public List<Employee> viewEmpByDName(String name){
    	   Query q=manager.createQuery("select d.emp from Department d where d.name=?1");
    	   q.setParameter(1, name);
    	   return q.getResultList();
    			   
       }
       public boolean deleteEmp(int id) {
    	   Query q=manager.createQuery("select d.emp from Department d where d.id=?1");
    	   q.setParameter(1,id );
    	  
    	 
    	   Employee em=(Employee) q.getSingleResult();
    	   if(em!=null) {
    		   manager.remove(em);
    		   t.begin();
    		   t.commit();
    		   return true;
    	   }
    	   return false;
       }
}
