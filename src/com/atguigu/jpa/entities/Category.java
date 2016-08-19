package com.atguigu.jpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name="tbl_category")
@Entity
public class Category
{
	private Integer id;
	private String  categoryName;
	private Set<Item> items = new HashSet<Item>();
	
	public Category(){}

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

	public String getCategoryName()
	{
		return categoryName;
	}

	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}
	@JoinTable(name="mid_item_category",
		      joinColumns={@JoinColumn(name="category_id",referencedColumnName="id")},
		      inverseJoinColumns={@JoinColumn(name="item_id",referencedColumnName="id")})
	@ManyToMany
	public Set<Item> getItems()
	{
		return items;
	}

	public void setItems(Set<Item> items)
	{
		this.items = items;
	}
	
}
