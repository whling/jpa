package com.atguigu.jpa.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.atguigu.jpa.entities.Key;
import com.atguigu.jpa.entities.Lock;

public class Test_DoubleOne2One
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
		System.out.println("---------------Test_DoubleOne2One");
	}
	
	@Test
	public void testSave()
	{
		Lock lock = new Lock();
		lock.setLockName("ironLock");
		
		Key key = new Key();
		key.setKeyName("ironkey");
		
		lock.setKey(key);
		key.setLock(lock);
		
		//2条insert+1条update
//		entityManager.persist(lock);
//		entityManager.persist(key);
		
		//推荐
		entityManager.persist(key);	
		entityManager.persist(lock);
	}
	
	@Test
	public void testFind()
	{
		Lock lock = entityManager.find(Lock.class,1);
		System.out.println(lock.getLockName());
		System.out.println(lock.getKey().getClass().getName());
	}
	
	@Test
	public void testFind2()
	{
		Key key = entityManager.find(Key.class,1);
		System.out.println(key.getKeyName());
		System.out.println(key.getLock().getClass().getName());
	}	
}
