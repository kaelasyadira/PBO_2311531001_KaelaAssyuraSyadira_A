package model;

public class Customer {
	private String id;
	private String name;
	private String address;
	private String hp;
	
	public Customer(String id, String name, String address, String hp) {
	this.id = id;
	this.name = name;
	this.address = address;
	this.hp = hp;
	}

	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getHp() {
		return hp;
	}
}