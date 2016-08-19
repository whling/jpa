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

public class Test_SingleMany2One
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
		System.out.println("---------------Test_SingleMany2One");
	}
	
	
	@Test
	public void testSave()
	{
		Customer c1 = new Customer();
		c1.setCustomerName("li5");
		
		Order o1 = new Order();
		o1.setOrderName("JD003");
		//o1.setCustomer(c1);
		
		Order o2 = new Order();
		o2.setOrderName("JD004");
		//o2.setCustomer(c1);
		
		
		/*先N后1，三条insert+2条update，性能不好
		entityManager.persist(o1);
		entityManager.persist(o2);
		entityManager.persist(c1);*/
		
		entityManager.persist(c1);
		entityManager.persist(o1);
		entityManager.persist(o2);
	}
	
	@Test
	public void testFind()
	{
		Order o1 = entityManager.find(Order.class,1);
		System.out.println(o1.getOrderName());
		//System.out.println(o1.getCustomer().getCustomerName());
	}
	

	@Test
	public void testRemove()
	{
		//常规删除
//		Order o1 = entityManager.find(Order.class,4);
//		entityManager.remove(o1);
		
		Customer c1 = entityManager.find(Customer.class,1);
		entityManager.remove(c1);
	}
	
	@Test
	 public void test_Update()
	 {
	   Order o = entityManager.find(Order.class,3);
	   //o.getCustomer().setCustomerName("AAA");
	   entityManager.merge(o);
	 }	
	
}
