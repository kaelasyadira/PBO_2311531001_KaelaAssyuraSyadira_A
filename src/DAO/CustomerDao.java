package DAO;

import java.util.List;

import model.Customer;

public interface CustomerDao {
	void save(Customer customerr);
	public List<Customer> show();
	public void delete(String id);
	public void update(Customer customer);


}
