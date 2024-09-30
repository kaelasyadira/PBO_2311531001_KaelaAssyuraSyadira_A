package DAO;

import java.util.logging.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import DAO.CustomerDao;
import confg.Database;
import model.Customer;
import java.util.*;

public class CustomerRepo  implements CustomerDao {
		private Connection connection;
		final String insert = "INSERT INTO customer (name, address, hp) VALUES (?, ?, ?);";
		final String select = "SELECT * FROM customer;" ;
		final String delete = "DELETE FROM customer WHERE id=?;";
		final String update = "UPDATE customer SET name=?, address=?, hp=? WHERE id = ?;";
		
		public CustomerRepo () {
			connection = Database.koneksi();
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
			List<Customer>ls = null;
			try {
				ls = new ArrayList<Customer>();
				Statement  st = connection.createStatement();
				ResultSet rs = st.executeQuery(select);
				while(rs.next()) {
					Customer customer = new Customer();
					customer.setId(rs.getString("id"));
					customer.setName(rs.getString("name"));
					customer.setAddress(rs.getString("address"));
					customer.setHp(rs.getString("hp"));
					ls.add(customer);
				}
			} 
			catch(SQLException e) {
				Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, e);
			}
			return ls;
		}

		@Override
		public void delete(String id) {
			PreparedStatement st = null;
			try {
				st = connection.prepareStatement(delete);
				st.setString(1, id);
				st.executeUpdate();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					st.close();
				} 
			catch(SQLException e) {
				e.printStackTrace();
				}
			}
		
			
		}

		@Override
		public void update(Customer customer) {
			PreparedStatement st = null;
			try {
				st = connection.prepareStatement(update);
				st.setString(1, customer.getName());
				st.setString(2, customer.getAddress());
				st.setString(3, customer.getHp());
				st.setString(4, customer.getId());
				st.executeUpdate();
			}
			catch(SQLException e){
				e.printStackTrace();
				
			}
			finally {
				try {
					st.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			
		}}

		
