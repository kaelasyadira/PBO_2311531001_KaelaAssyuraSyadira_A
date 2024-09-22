package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import DAO.UserRepo;
import model.User;
import table.TableUser;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTable tableUsers;

	/**
	 * Launch the application.
	 */
	
	UserRepo usr = new UserRepo();
	List<User> ls;
	String id;
	
	public void reset() {
		txtName.setText("");
		txtUsername.setText("");
		txtPassword.setText("");
	}
	public void loadTable() {
		ls = usr.show();
		TableUser tu = new TableUser(ls);
		tableUsers.setModel(tu);;
		tableUsers.getTableHeader().setVisible(true);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame frame = new UserFrame();
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
	public UserFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 427, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 391, 191);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtName.setBounds(95, 33, 272, 22);
		panel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel.setBounds(20, 33, 65, 22);
		panel.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblUsername.setBounds(20, 75, 65, 22);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblPassword.setBounds(20, 117, 65, 22);
		panel.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtUsername.setColumns(10);
		txtUsername.setBounds(95, 76, 272, 22);
		panel.add(txtUsername);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtPassword.setColumns(10);
		txtPassword.setBounds(95, 118, 272, 22);
		panel.add(txtPassword);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setNama(txtName.getText());
				user.setUsername(txtUsername.getText());
				user.setPassword(txtPassword.getText());
				usr.save(user);
				reset();
				loadTable();
			}
		});
		btnSave.setBackground(new Color(250, 124, 147));
		btnSave.setBounds(20, 157, 65, 23);
		panel.add(btnSave);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setNama(txtName.getText());
				user.setUsername(txtUsername.getText());
				user.setPassword(txtPassword.getText());
				user.setId(id);
				usr.update(user);
				reset();
				loadTable();
			}
		});
		btnUpdate.setBackground(new Color(114, 166, 252));
		btnUpdate.setBounds(95, 157, 85, 23);
		panel.add(btnUpdate);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setBackground(new Color(102, 255, 140));
		btnCancel.setBounds(280, 157, 87, 23);
		panel.add(btnCancel);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id != null) {
					usr.delete(id);
					reset();
					loadTable();
				} else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan di hapus");
				}
			}
		});
		btnDelete.setBackground(new Color(251, 174, 117));
		btnDelete.setBounds(190, 157, 80, 23);
		panel.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 200, 391, 267);
		scrollPane.setViewportView(tableUsers);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane);
		
		tableUsers = new JTable();
		tableUsers.setToolTipText("");
		tableUsers.setFillsViewportHeight(true);
		tableUsers.setBackground(new Color(255, 255, 255));
		tableUsers.setBounds(10, 200, 416, 232);
		scrollPane.setRowHeaderView(tableUsers);
		tableUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = tableUsers.getValueAt(tableUsers.getSelectedRow(),0).toString();
				txtName.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(),1).toString());
				txtUsername.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(),2).toString());
				txtPassword.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(),3).toString());
			}
		});
		tableUsers.setFont(new Font("SansSerif", Font.PLAIN, 12));
	}
}
