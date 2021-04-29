package com.cecile.cyriaque.model;

public class Article {
private int id;
private String name;
private String description;
private double price;
private int id_buyer;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public int getId_buyer() {
	return id_buyer;
}
public void setId_buyer(int id_buyer) {
	this.id_buyer = id_buyer;
}


}
