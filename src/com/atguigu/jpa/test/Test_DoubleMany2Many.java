package com.atguigu.jpa.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.atguigu.jpa.entities.Category;
import com.atguigu.jpa.entities.Item;

public class Test_DoubleMany2Many
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
	public void createTable()
	{
		System.out.println("---------------Test_DoubleMany2Many");
	}
	
	
	@Test
	public void testSave()
	{
		Category c1 = new Category();
		Category c2 = new Category();

		Item i1 = new Item();
		Item i2 = new Item();

		c1.setCategoryName("AA");
		c1.getItems().add(i1);
		c1.getItems().add(i2);

		c2.setCategoryName("BB");
		c2.getItems().add(i1);
		c2.getItems().add(i2);

		i1.setItemName("11");
		i1.getCategorys().add(c1);
		i1.getCategorys().add(c2);

		i2.setItemName("22");
		i2.getCategorys().add(c1);
		i2.getCategorys().add(c2);

		entityManager.persist(c1);
		entityManager.persist(c2);
		entityManager.persist(i1);
		entityManager.persist(i2);
	}
	
}
