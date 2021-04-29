package com.cecile.cyriaque.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Transaction {
	private int id;
	private int id_buyer;
	private String site;
	private String description;
	private double amount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_buyer() {
		return id_buyer;
	}
	public void setId_buyer(int id_buyer) {
		this.id_buyer = id_buyer;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	
	
	

}
