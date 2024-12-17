package model;

public class CustomerBuilder {
	private String id,name, address, hp;
	
	public CustomerBuilder() {	
	}
	public CustomerBuilder setId(String id) {
		this.id = id;
		return this;
	}
	public CustomerBuilder setName(String name) {
		this.name = name;
		return this;
	}
	public CustomerBuilder setAddress(String address) {
		this.address = address;
		return this;
	}
	public CustomerBuilder setHp(String hp) {
		this.hp = hp;
		return this;
	}
	public Customer build() {
		return new Customer(id,name, address, hp);
	}
}