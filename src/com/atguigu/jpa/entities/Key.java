package com.atguigu.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="tbl_key")
@Entity
public class Key
{
	private Integer id;
	private String  keyName;
	private Lock	lock;

	public Key(){}

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

	public String getKeyName()
	{
		return keyName;
	}

	public void setKeyName(String keyName)
	{
		this.keyName = keyName;
	}

	@OneToOne(mappedBy="key",fetch=FetchType.LAZY)
	public Lock getLock()
	{
		return lock;
	}

	public void setLock(Lock lock)
	{
		this.lock = lock;
	}

	@Override
	public String toString()
	{
		return "Key [id=" + id + ", keyName=" + keyName + ", lock=" + lock
				+ "]";
	}
	
	
}
