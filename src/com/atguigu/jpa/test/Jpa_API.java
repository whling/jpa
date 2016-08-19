package com.atguigu.jpa.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.atguigu.jpa.entities.Person;

public class Jpa_API
{
	
	private EntityManagerFactory entityManagerFactory = null;
	private EntityManager entityManager = null;
	private	EntityTransaction transaction = null;
	
	@Before
	public void init()
	{
		String persistenceUnitName = "jpa001";
		entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();
	}
	
	@After
	public void release()
	{
		transaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}
	
	
	@Test
	public void testFind()
	{
		Person person = entityManager.find(Person.class,1);
		//System.out.println(person.getClass().getName());
		//System.out.println(person.getPersonName());
	}
	
	/**
	 * org.hibernate.LazyInitializationException: could not initialize proxy - no Session
	 */
	@Test
	public void testGetReference()
	{
		Person person = entityManager.getReference(Person.class,1);
//		transaction.commit();
//		entityManager.close();
		
		//System.out.println(person.getClass().getName());	
		System.out.println(person.getPersonName());
	}
	
	@Test
	public void testPersist()
	{
		Person person = new Person();
		person.setAge(28);
		person.setBirth(new Date());
		person.setPersonName("z3"+UUID.randomUUID().toString().substring(1,8));
		person.setRegisterTime(new Date());
		person.setSalary(4533.55);
		
		person.setId(135);
		
		entityManager.persist(person);
	}
	
	@Test
	public void testRemove()
	{
		//常规删除
//		Person person = entityManager.getReference(Person.class,11);
//		entityManager.remove(person);
		
		Person person = new Person();
		person.setAge(28);
		person.setBirth(new Date());
		person.setPersonName("z3"+UUID.randomUUID().toString().substring(1,8));
		person.setRegisterTime(new Date());
		person.setSalary(4533.55);
		
		//java.lang.IllegalArgumentException: 
		//Removing a detached instance com.atguigu.jpa.entities.Person#12
		//person.setId(12);		
		entityManager.remove(person);	
		
	}
	
	//====Merge api的使用见Test_Merge.java
	
	@Test
	public void testFlush()
	{
		Person person = entityManager.find(Person.class,3);
		person.setPersonName("xxx3");
		entityManager.flush();
		System.out.println("----------------------------");
	}
	
	@Test
	public void testRefresh()
	{
		Person person = entityManager.find(Person.class,3);
		       person = entityManager.find(Person.class,3);
		       entityManager.refresh(person);
		       entityManager.refresh(person);
		       entityManager.refresh(person);
		
	}
}
