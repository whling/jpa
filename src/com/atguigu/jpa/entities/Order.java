package com.atguigu.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="tbl_order")
@Entity
public class Order
{
	private Integer id;
	private String  orderName;
	private Customer customer;
	
	public Order(){}

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

	public String getOrderName()
	{
		return orderName;
	}

	public void setOrderName(String orderName)
	{
		this.orderName = orderName;
	}
	@JoinColumn(name="customer_id")
	@ManyToOne(fetch=FetchType.LAZY)
	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	@Override
	public String toString()
	{
		return "Order [id=" + id + ", orderName=" + orderName + ", customer="+"]";
	}
	
	
}
