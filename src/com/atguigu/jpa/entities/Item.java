package com.atguigu.jpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name="tbl_item")
@Entity
public class Item
{
	private Integer id;
	private String  itemName;
	private Set<Category> categorys = new HashSet<Category>();
	
	public Item(){}
	
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
	public String getItemName()
	{
		return itemName;
	}
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	@ManyToMany(mappedBy="items")	
	public Set<Category> getCategorys()
	{
		return categorys;
	}
	public void setCategorys(Set<Category> categorys)
	{
		this.categorys = categorys;
	}
	
	
}
