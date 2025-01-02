package ui;

import java.awt.EventQueue;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.OrderRepo;
import model.Order;
import table.TableOrder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OrderFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableOrder;
	
	OrderRepo repo_od = new OrderRepo();
	List<Order> ls_od;
	String order_id="";
	
	public String id_cust;
	public String tanggal;
	public String status;
	public String status_bayar;
	public String total;
	public String tanggal_kembali;
	OrderDetailFrame odf = new OrderDetailFrame();
	
	public void loadTableOrder() {
		ls_od = repo_od.show();
		TableOrder tu = new TableOrder(ls_od);
		tableOrder.setModel(tu);
		tableOrder.getTableHeader().setVisible(true);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderFrame frame = new OrderFrame();
					frame.setVisible(true);
					frame.loadTableOrder();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblJudul = new JLabel("DATA ORDERAN");
		lblJudul.setBounds(10, 11, 131, 29);
		contentPane.add(lblJudul);
		
		JButton btnOrder = new JButton("Buat Orderan");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				odf.setVisible(true);
				odf.loadTableDetail();
				odf.loadTableService();
				dispose();
			}
		});
		btnOrder.setBounds(10, 64, 119, 23);
		contentPane.add(btnOrder);
		
		JButton btnEditDetail = new JButton("Edit/Detail");
		btnEditDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				odf.loadTableDetail();
				odf.loadTableService();
				odf.setOrderID(order_id);
				odf.onDataReceived(id_cust, getCustomerNameById(id_cust));
				
				SimpleDateFormat sdf_tanggal = new SimpleDateFormat("yyyy-MM-dd");
	            try {
	                Date parsedDate_tanggal = sdf_tanggal.parse(tanggal); 
	                odf.setTanggal(parsedDate_tanggal); 
	            } catch (ParseException ex) {
	                ex.printStackTrace();
	            }
	            
	            odf.setStatus(status);
	            odf.setStatusBayar(status_bayar);
	            odf.setTotal(total);
	            odf.setVisible(true);
	            dispose();
			}
		});
		btnEditDetail.setBounds(390, 64, 155, 23);
		contentPane.add(btnEditDetail);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(order_id!="") {
					repo_od.delete(order_id);
					loadTableOrder();
				} else {
					JOptionPane.showMessageDialog(null, "Pilih data yang akan dihapus");
				}
			}
		});
		btnHapus.setBounds(288, 64, 89, 23);
		contentPane.add(btnHapus);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 98, 630, 242);
		contentPane.add(scrollPane);
		
		tableOrder = new JTable();
		tableOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tableOrder.getSelectedRow();
                if (selectedRow != -1) { 
                    order_id = tableOrder.getValueAt(selectedRow, 0).toString(); 
                }
                
                id_cust = tableOrder.getValueAt(tableOrder.getSelectedRow(), 1).toString();
                tanggal = tableOrder.getValueAt(tableOrder.getSelectedRow(), 2).toString();
                status = tableOrder.getValueAt(tableOrder.getSelectedRow(), 3).toString();
                status_bayar = tableOrder.getValueAt(tableOrder.getSelectedRow(), 4).toString();
                total = tableOrder.getValueAt(tableOrder.getSelectedRow(), 5).toString();
			}
		});
		scrollPane.setViewportView(tableOrder);
	}

	private String getCustomerNameById(String customerId) {
	    // Logika untuk mendapatkan nama pelanggan dari database
	    return repo_od.getCustomerName(customerId);
	}

}