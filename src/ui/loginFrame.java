package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class loginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginFrame frame = new loginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public loginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 311, 338);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(147, 220, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBackground(new Color(255, 255, 255));
		txtUsername.setBounds(51, 97, 195, 23);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(51, 83, 70, 14);
		contentPane.add(lblNewLabel);
		
		txtPassword = new JTextField();
		txtPassword.setBackground(new Color(255, 255, 255));
		txtPassword.setColumns(10);
		txtPassword.setBounds(51, 147, 195, 23);
		contentPane.add(txtPassword);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(51, 133, 70, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(0, 174, 174));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (User.loggin(txtUsername.getText(), txtPassword.getText())) {
					JOptionPane.showMessageDialog(null, "Berhasil Login");
			//      cara 1 : pindah dari login ke mainframe
					MainFrame mf = new MainFrame();
					mf.setVisible(true);
					dispose();		
			//      cara 2 :
					new MainFrame().setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Gagal Login");
				}
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnLogin.setBounds(161, 203, 85, 33);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel_2 = new JLabel("Laundry Apps");
		lblNewLabel_2.setFont(new Font("Papyrus", Font.BOLD, 14));
		lblNewLabel_2.setBounds(96, 25, 105, 23);
		contentPane.add(lblNewLabel_2);
	}
}
