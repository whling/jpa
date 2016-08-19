package com.atguigu.jpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Table(name="tbl_customer")
@Entity
public class Customer
{
	private Integer id;
	private String  customerName;
	private Set<Order> orders = new HashSet<Order>();
	
	public Customer(){}
	
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

	public String getCustomerName()
	{
		return customerName;
	}

	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}
	//@JoinColumn(name="customer_id")
	@OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.REMOVE},mappedBy="customer")
	public Set<Order> getOrders()
	{
		return orders;
	}

	public void setOrders(Set<Order> orders)
	{
		this.orders = orders;
	}

	@Override
	public String toString()
	{
		return "Customer [id=" + id + ", customerName=" + customerName
				+ ", orders=" + orders + "]";
	}
	
	
	
}
