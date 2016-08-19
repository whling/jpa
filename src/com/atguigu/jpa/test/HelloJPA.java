package com.atguigu.jpa.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.atguigu.jpa.entities.Person;

public class HelloJPA
{
	public static void main(String[] args)
	{
		String persistenceUnitName = "jpa001";
		//1	获得EntityManagerFactory的会话
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("javax.persistence.jdbc.password","cxxxxsdf");
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName,map);
		//2 获取EntityManager的连接
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//3 开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		//4 持久化操作
		for (int i = 0; i < 1; i++)
		{
			Person person = new Person();
			person.setAge(27);
			person.setBirth(new Date());
			person.setPersonName("lisi"+UUID.randomUUID().toString().substring(1,8));
			person.setRegisterTime(new Date());
			person.setSalary(4533.55);
			
			entityManager.persist(person);			
		}
		
		//5 提交事务
		transaction.commit();
		//6 关闭EntityManager
		entityManager.close();
		//7 关闭EntityManagerFactory
		entityManagerFactory.close();
	}
}
