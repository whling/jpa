package com.atguigu.jpa.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQuery(name="testNamedQuery",query="select p from Person p  where p.id=:id")
@Table(name="tbl_person")
@Entity
public class Person
{
	private Integer id;
	private String personName;
	private int age;
	private double salary;
	private Date birth;
	private Date registerTime;
	
	public Person(){}
	
	

	public Person(String personName, int age)
	{
		super();
		this.personName = personName;
		this.age = age;
	}



	/*@TableGenerator(name="ID_GENERATOR",
	       table="tbl_id_generator",
	       pkColumnName="pk_name",
	       pkColumnValue="person_id",
	       valueColumnName="pk_value",
	       allocationSize=1)
	 @Id
	 @GeneratedValue(strategy=GenerationType.TABLE,generator="ID_GENERATOR") */
	@Id
	@GeneratedValue
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}
	@Column(length=40,nullable=false)
	public String getPersonName()
	{
		return personName;
	}

	public void setPersonName(String personName)
	{
		this.personName = personName;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public double getSalary()
	{
		return salary;
	}

	public void setSalary(double salary)
	{
		this.salary = salary;
	}
	@Temporal(TemporalType.DATE)
	public Date getBirth()
	{
		return birth;
	}

	public void setBirth(Date birth)
	{
		this.birth = birth;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRegisterTime()
	{
		return registerTime;
	}

	public void setRegisterTime(Date registerTime)
	{
		this.registerTime = registerTime;
	}

	@Override
	public String toString()
	{
		return "Person [id=" + id + ", personName=" + personName + ", age="
				+ age + ", salary=" + salary + ", birth=" + birth
				+ ", registerTime=" + registerTime + "]";
	}
	
	//@Transient
//	public String qryPersonInfo()
//	{
//		return "jpa hello";
//	}
	
}
