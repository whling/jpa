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

public class Test_Merge
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
	public void testMerge1()
	{
		Person person = new Person();
		person.setAge(27);
		person.setBirth(new Date());
		person.setPersonName("testMerge1"+UUID.randomUUID().toString().substring(1,8));
		person.setRegisterTime(new Date());
		person.setSalary(6500.55);
		
		Person person2 = entityManager.merge(person);
		
		System.out.println(person == person2);
		System.out.println(person.getId()+"\t"+person2.getId());
		
	}
	@Test
	public void testMerge2()
	{
		Person person = new Person();
		person.setAge(27);
		person.setBirth(new Date());
		person.setPersonName("testMerge2"+UUID.randomUUID().toString().substring(1,8));
		person.setRegisterTime(new Date());
		person.setSalary(6500.55);
		
		person.setId(777);
		
		Person person2 = entityManager.merge(person);
		
		System.out.println(person == person2);
		System.out.println(person.getId()+"\t"+person2.getId());
	}	
	
	@Test
	public void testMerge3()
	{
		Person person = new Person();
		person.setAge(26);
		person.setBirth(new Date());
		person.setPersonName("testMerge3"+UUID.randomUUID().toString().substring(1,8));
		person.setRegisterTime(new Date());
		person.setSalary(6666.55);
		
		person.setId(6);
		
		Person person2 = entityManager.merge(person);
		
		System.out.println(person == person2);
		System.out.println(person.getId()+"\t"+person2.getId());
	}	
	
	@Test
	public void testMerge4()
	{
		Person person = new Person();
		person.setAge(26);
		person.setBirth(new Date());
		person.setPersonName("testMerge4"+UUID.randomUUID().toString().substring(1,8));
		person.setRegisterTime(new Date());
		person.setSalary(6666.55);
		
		person.setId(6);
		
		Person person3 = entityManager.find(Person.class,6);
		
		Person person2 = entityManager.merge(person);
		
		System.out.println(person == person2);
		System.out.println(person.getId()+"\t"+person2.getId()+"\t"+person3.getId());
		System.out.println(person.hashCode());
		System.out.println(person2.hashCode());
		System.out.println(person3.hashCode());
	}	
	
}
