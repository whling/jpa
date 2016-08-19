package com.atguigu.jpa.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.ejb.QueryHints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.atguigu.jpa.entities.Customer;
import com.atguigu.jpa.entities.Order;
import com.atguigu.jpa.entities.Person;

public class Test_JPQL
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
	public void testHelloWorld()
	{
		String jpql = "select p from Person p where p.id=?";
		Person person = (Person)entityManager.createQuery(jpql).setParameter(1,1).getSingleResult();
		System.out.println(person.getPersonName());
	}
	@Test
	public void testHelloWorld2()
	{
		String jpql = "select p from Person p where p.id=:id";
		Person person = (Person)entityManager.createQuery(jpql).setParameter("id",1).getSingleResult();
		System.out.println(person.getPersonName());
	}
	@Test
	public void testHelloWorld3()
	{
		String jpql = "select p from Person p where p.id>?";
		List<Person> list = entityManager.createQuery(jpql).setParameter(1,10).getResultList();
		System.out.println(list.size());
	}	
//	@Test
//	public void testSQLInject()
//	{
//		String name = "z3";
//		String sql  ="select password from student where name='"+name+"'";
//		
//		name = "\'\' or 1 or \'\'";
//		sql = "select password from student where name="+name+"";
//		System.out.println(sql);
//	}
	
	@Test
	public void testPartorField()
	{
		String jpql = "select new Person(p.personName,p.age) from Person p where p.id=?";
		Person person = (Person)entityManager.createQuery(jpql).setParameter(1,10).getSingleResult();
		System.out.println(person.getAge());		
	}
	
	@Test
	public void testPartorField2()
	{
		String jpql = "select p.personName,p.age from Person p where p.id=?";
		Object[] obj = (Object[]) entityManager.createQuery(jpql).setParameter(1,10).getSingleResult();
		System.out.println(obj[0]+"\t"+obj[1]);
	}
	@Test
	public void testPartorField3()
	{
		String jpql = "select p.id,p.personName,p.age from Person p where p.id>?";
		List<Object[]> list = entityManager.createQuery(jpql).setParameter(1,10).getResultList();
		for (Object[] element : list)
		{
			for (int i = 0; i < element.length; i++)
			{
				System.out.print(element[i]+" ");
			}
			System.out.println();
		}
	}	
	//@NamedQuery
	@Test
	public void testNamedQuery()
	{
		Person person = (Person)entityManager.createNamedQuery("testNamedQuery").setParameter("id",10).getSingleResult();
		System.out.println(person.getPersonName());
	}
	//createNativeQuery 
	@Test
	public void testcreateNativeQuery()
	{
		String jpql = "select * from tbl_person where id=?";
		Object[] obj = (Object[]) entityManager.createNativeQuery(jpql).setParameter(1,10).getSingleResult();
		System.out.println(obj[0]+"\t"+obj[1]);
	}	
	
	@Test
	public void testQueryCache()
	{
		String jpql = "select p from Person p where p.id=?";
		Person person1 = (Person)entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE,true).setParameter(1,10).getSingleResult();
		Person person2 = (Person)entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE,true).setParameter(1,10).getSingleResult();
//		System.out.println("***************************");
//		entityManager.find(Person.class,12);
//		entityManager.find(Person.class,12);
	}	
	
	@Test
	public void testGroupBy()
	{
		String jpql = "select o.customer from Order o group by o.customer having count(o.id)>?";
		List<Customer> list = entityManager.createQuery(jpql).setParameter(1,2L).getResultList();
		System.out.println(list.size());
	}
	
	@Test
	public void testJoin()
	{
		String jpql = "select c from Customer c left outer join c.orders where c.id=?";		
		List<Customer> list = entityManager.createQuery(jpql).setParameter(1,1).getResultList();
		System.out.println(list.size());
	}
	@Test
	public void testSubQuery()
	{
		String jpql = "select o from Order o where o.customer=(select c from Customer c where c.id=?)";
		List<Order> list = entityManager.createQuery(jpql).setParameter(1,1).getResultList();
		System.out.println(list.size());
	}
	
	@Test
	 public void testInnerFunction()
	 {
	   String jpql = "select concat(p.birth,p.age) from Person p where p.id=1";
	   String ret = (String) entityManager.createQuery(jpql).getSingleResult();
	   System.out.println(ret);

	 }	
}
