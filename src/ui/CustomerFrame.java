package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.CustomerRepo;
import DAO.UserRepo;
import model.Customer;
import model.CustomerBuilder;
import model.User;
import table.TableCustomer;
import table.TableUser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class CustomerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	private JTextField txtHp;
	private JTextField txtAddress;
	private JTextField txtNama;
	private JTable tableCustomers;
	
	CustomerRepo csmtr = new CustomerRepo();
	List<Customer> ls;
	String id;
	
	public void reset() {
		txtNama.setText("");
		txtAddress.setText("");
		txtHp.setText("");
	}
	
	public void loadTable() {
		ls = csmtr.show();
		TableCustomer tu = new TableCustomer(ls);
		tableCustomers.setModel(tu);;
		tableCustomers.getTableHeader().setVisible(true);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerFrame frame = new CustomerFrame();
					frame.setVisible(true);
					frame.loadTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 354, 162);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nama");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Alamat");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 39, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nomor HP");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 68, 75, 14);
		panel.add(lblNewLabel_2);
		
		txtHp = new JTextField();
		txtHp.setFont(new Font("Calibri", Font.PLAIN, 13));
		txtHp.setBounds(95, 63, 249, 20);
		panel.add(txtHp);
		txtHp.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Calibri", Font.PLAIN, 13));
		txtAddress.setColumns(10);
		txtAddress.setBounds(95, 34, 249, 20);
		panel.add(txtAddress);
		
		txtNama = new JTextField();
		txtNama.setFont(new Font("Calibri", Font.PLAIN, 13));
		txtNama.setColumns(10);
		txtNama.setBounds(95, 6, 249, 20);
		panel.add(txtNama);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer customer = new CustomerBuilder()
				.setName(txtNama.getText())
				.setAddress(txtAddress.getText())
				.setHp(txtHp.getText())
				.build();
				csmtr.save(customer);
				reset();
				loadTable();
			}
		});
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnNewButton.setBounds(0, 126, 75, 23);
		panel.add(btnNewButton);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    Customer customer = new CustomerBuilder()
			            .setName(txtNama.getText())
			            .setAddress(txtAddress.getText())
			            .setHp(txtHp.getText())
			            .build();
			    csmtr.save(customer);
			    reset();
			    loadTable();
			}
		});
		btnUpdate.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnUpdate.setBounds(85, 126, 89, 23);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id != null) {
					csmtr.delete(id);
					reset();
					loadTable();
				} else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan di hapus");
				}
			}
		});
		btnDelete.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnDelete.setBounds(177, 126, 89, 23);
		panel.add(btnDelete);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnCancel.setBounds(276, 126, 78, 23);
		panel.add(btnCancel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 184, 354, 192);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 334, 170);
		panel_1.add(scrollPane);
		
		tableCustomers = new JTable();
		scrollPane.setViewportView(tableCustomers);
		tableCustomers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = tableCustomers.getValueAt(tableCustomers.getSelectedRow(),0).toString();
				txtNama.setText(tableCustomers.getValueAt(tableCustomers.getSelectedRow(),1).toString());
				txtAddress.setText(tableCustomers.getValueAt(tableCustomers.getSelectedRow(),2).toString());
				txtHp.setText(tableCustomers.getValueAt(tableCustomers.getSelectedRow(),3).toString());
			}
		});
	}
}