package com.atguigu.jpa.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.atguigu.jpa.entities.Customer;
import com.atguigu.jpa.entities.Order;

public class Test_SingleOne2Many
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
		System.out.println("---------------Test_SingleOne2Many");
	}
	
	
	@Test
	public void testSave()
	{
		Customer c1 = new Customer();
		c1.setCustomerName("li7");
		
		Order o1 = new Order();
		o1.setOrderName("JD005");
		
		Order o2 = new Order();
		o2.setOrderName("JD006");
		
		c1.getOrders().add(o1);
		c1.getOrders().add(o2);
		
		/*先N后1，三条insert+2条update
		entityManager.persist(o1);
		entityManager.persist(o2);
		entityManager.persist(c1);*/
		
		/*先1后N,三条insert+2条update*/
		entityManager.persist(c1);
		entityManager.persist(o1);
		entityManager.persist(o2);
	}
	
	@Test
	public void testFind()
	{
		Customer c1 = entityManager.find(Customer.class,1);
		System.out.println(c1.getCustomerName());
		System.out.println(c1.getOrders().size());
	}
	
	@Test
	public void testRemove()
	{
		Customer c1 = entityManager.find(Customer.class,2);
		entityManager.remove(c1);
	}

	@Test
	public void test_Update()
	{
		Customer c = entityManager.find(Customer.class, 3);
		c.getOrders().iterator().next().setOrderName("taobao002");
	}
}
