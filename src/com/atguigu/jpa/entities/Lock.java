package com.atguigu.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Table(name="tbl_lock")
@Entity
public class Lock
{
	private Integer id;
	private String  lockName;
	private Key		key;
	
	public Lock(){}
	
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

	public String getLockName()
	{
		return lockName;
	}

	public void setLockName(String lockName)
	{
		this.lockName = lockName;
	}
	@JoinColumn(name="key_id",unique=true)
	@OneToOne(fetch=FetchType.LAZY)
	public Key getKey()
	{
		return key;
	}

	public void setKey(Key key)
	{
		this.key = key;
	}

	@Override
	public String toString()
	{
		return "Lock [id=" + id + ", lockName=" + lockName + ", key=" + key
				+ "]";
	}
	
	
}
