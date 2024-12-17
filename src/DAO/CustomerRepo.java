package DAO;

import java.sql.Statement;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import model.Customer;
import confg.DatabaseHelper;
import model.CustomerBuilder;

public class CustomerRepo implements CustomerDao{
	private Connection connection;
	final String insert = "INSERT INTO Customer (name, address, hp) VALUES (?,?,?);";
	final String select = "SELECT * FROM Customer;";
	final String delete = "DELETE FROM Customer WHERE id=?;";
	final String update = "UPDATE Customer SET name=?, address=?, hp=? WHERE id=?;";
	
	public CustomerRepo() {
		connection = DatabaseHelper.getConnection();
	}
	
	@Override
	public void save (Customer customer) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(insert);
			st.setString(1, customer.getName());
			st.setString(2, customer.getAddress());
			st.setString(3, customer.getHp());
			st.executeUpdate();
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				st.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public List<Customer> show() {
	    // TODO Auto-generated method stub
	    List<Customer> ls = null;
	    try {
	        ls = new ArrayList<Customer>();
	        Statement st = connection.createStatement();
	        ResultSet rs = st.executeQuery(select);
	        while (rs.next()) {
	            Customer csmtr = new CustomerBuilder()
	                    .setId(rs.getString("id"))
	                    .setName(rs.getString("name"))
	                    .setAddress(rs.getString("address"))
	                    .setHp(rs.getString("hp"))
	                    .build();
	            		ls.add(csmtr);
	        }
	    } catch (SQLException e) {
	        // TODO: handle exception
	        Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, e);
	    }
	    return ls;
	}

	
	public void update (Customer customers) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(update);
			st.setString(1, customers.getName());  
			st.setString(2, customers.getAddress()); 
			st.setString(3, customers.getHp());  
			st.setString(4, customers.getId()); 
			st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void delete(String id) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(delete);
					st.setString(1,id);
			st.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				st.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}