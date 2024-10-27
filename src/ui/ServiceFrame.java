package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.CustomerRepo;
import DAO.ServiceRepo;
import model.Customer;
import model.Service;
import table.TableCustomer;
import table.TableService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class ServiceFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtJenis;
	private JTextField txtHarga;
	private JTextField txtStatus;
	private JTable tableServices;
	
	ServiceRepo srvc = new ServiceRepo();
	List<Service> ls;
	String id;
	
	
	public void reset() {
		txtJenis.setText("");
		txtHarga.setText("");
		txtStatus.setText("");
	}
	public void loadTable() {
		ls = srvc.show();
		TableService tu = new TableService(ls);
		tableServices.setModel(tu);;
		tableServices.getTableHeader().setVisible(true);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiceFrame frame = new ServiceFrame();
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
	public ServiceFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 402, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 11, 366, 165);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Jenis");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblHarga = new JLabel("Harga");
		lblHarga.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblHarga.setBounds(10, 39, 46, 14);
		panel.add(lblHarga);
		
		JLabel lblNewLabel_3 = new JLabel("Status");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(10, 70, 46, 14);
		panel.add(lblNewLabel_3);
		
		txtJenis = new JTextField();
		txtJenis.setFont(new Font("Calibri", Font.PLAIN, 13));
		txtJenis.setColumns(10);
		txtJenis.setBounds(81, 6, 245, 20);
		panel.add(txtJenis);
		
		txtHarga = new JTextField();
		txtHarga.setFont(new Font("Calibri", Font.PLAIN, 13));
		txtHarga.setColumns(10);
		txtHarga.setBounds(81, 36, 245, 20);
		panel.add(txtHarga);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Service service = new Service();
				service.setJenis(txtJenis.getText());
				service.setHarga(txtHarga.getText());
				service.setStatus(txtStatus.getText());
				srvc.save(service);
				reset();
				loadTable();
				
			}
		});
		btnSave.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnSave.setBounds(0, 131, 73, 23);
		panel.add(btnSave);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Service service = new Service();
				service.setJenis(txtJenis.getText());
				service.setHarga(txtHarga.getText());
				service.setStatus(txtStatus.getText());
				service.setId(id);
				srvc.update(service);
				reset();
				loadTable();
				
			}
		});
		btnUpdate.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnUpdate.setBounds(81, 131, 89, 23);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id != null) {
					srvc.delete(id);
					reset();
					loadTable();
				} else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan di hapus");
				}
			}
		});
		btnDelete.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnDelete.setBounds(180, 131, 89, 23);
		panel.add(btnDelete);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnCancel.setBounds(279, 129, 77, 25);
		panel.add(btnCancel);
		
		txtStatus = new JTextField();
		txtStatus.setFont(new Font("Calibri", Font.PLAIN, 13));
		txtStatus.setBounds(81, 67, 245, 20);
		panel.add(txtStatus);
		txtStatus.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 187, 366, 146);
		contentPane.add(panel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 346, 124);
		panel_1.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = tableServices.getValueAt(tableServices.getSelectedRow(),0).toString();
				txtJenis.setText(tableServices.getValueAt(tableServices.getSelectedRow(),1).toString());
				txtHarga.setText(tableServices.getValueAt(tableServices.getSelectedRow(),2).toString());
				txtStatus.setText(tableServices.getValueAt(tableServices.getSelectedRow(),3).toString());
				
			}
		});
		
		tableServices = new JTable();
		scrollPane.setViewportView(tableServices);
	}
}
