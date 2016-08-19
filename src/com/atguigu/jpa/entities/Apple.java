package com.atguigu.jpa.entities;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Cacheable(false)
@Table(name="tbl_apple")
@Entity
public class Apple
{
	private Integer id;
	private String  appleName;
	
	public Apple(){}

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

	public String getAppleName()
	{
		return appleName;
	}

	public void setAppleName(String appleName)
	{
		this.appleName = appleName;
	}
	
	
}
