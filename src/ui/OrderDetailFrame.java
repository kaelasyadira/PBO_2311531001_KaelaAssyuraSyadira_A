package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import DAO.OrderDetailRepo;
import DAO.OrderRepo;
import DAO.ServiceRepo;
import listener.DataListener;
import model.Order;
import model.OrderDetail;
import model.Service;
import table.TableOrderDetail;
import table.TableService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class OrderDetailFrame extends JFrame implements DataListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtOrderID;
	private JTextField txtHarga;
	private JTextField txtJumlah;
	private JTextField txtTotal;
	private JTable tableOrderDetail;
	JTable tableLayanan;
	JTextField txtPelanggan;
	JDateChooser CalTanggal;
	JDateChooser CalTanggalKembali;
	JComboBox cbStatus;
	JComboBox cbPembayaran;
	JComboBox cbStatusPembayaran;
	JLabel lblTotal;
	
	ServiceRepo sr = new ServiceRepo();
	List<Service> ls_service;
	public String id_service;
	public static String id_pelanggan="";
	
	public void loadTableService() {
		ls_service = sr.show();
		TableService tu = new TableService(ls_service);
		tableLayanan.setModel(tu);
		tableLayanan.getTableHeader().setVisible(true);
	}
	
	public double total(String jumlah) {
		double result = 0;
		if(jumlah.isEmpty()) {
			result = 0;
		} else {
			result = Double.parseDouble(jumlah) * Double.parseDouble(txtHarga.getText());
		}
		return result;
	}
	
	OrderDetailRepo repo_od = new OrderDetailRepo();
	List<OrderDetail> ls_od;
	public String id_order_detail;
	
	public void reset() {
		txtHarga.setText("");
		txtJumlah.setText("");
		txtTotal.setText("");
		id_service=null;
		id_order_detail=null;
	}
	
	public void loadTableDetail() {
		ls_od = repo_od.show(id_order_detail);
		TableOrderDetail tu = new TableOrderDetail(ls_od);
		tableOrderDetail.setModel(tu);
		tableOrderDetail.getTableHeader().setVisible(true);
	}
	
	public String tgl;
	public String tgl_kbl;
	
	public void setOrderID(String id) {
		txtOrderID.setText(id);
	}
	public void setCustID (String id) {
		id_pelanggan=id;
	}
	public void setPelanggan (String pelanggan) {
		txtPelanggan.setText(pelanggan);
	}
	public void setTanggal(Date date) {
		CalTanggal.setDate(date);
	}
	public void setTanggalKembali(Date date) {
		CalTanggalKembali.setDate(date);
	}
	public void setStatus (String proses) {
		cbStatus.setSelectedItem(proses);
	}
	public void setTotal (String total) {
		lblTotal.setText(total);
	}
	public void setStatusBayar (String status) {
		cbStatusPembayaran.setSelectedItem(status);
	}
	
	OrderRepo order_repo = new OrderRepo();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderDetailFrame frame = new OrderDetailFrame();
					frame.setVisible(true);
					frame.loadTableService();
					frame.loadTableDetail();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderDetailFrame() {
		
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 682);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel order = new JPanel();
		order.setBackground(new Color(192, 192, 192));
		order.setBounds(10, 11, 277, 623);
		contentPane.add(order);
		order.setLayout(null);
		
		JLabel lblOrderID = new JLabel("Order ID");
		lblOrderID.setFont(new Font("Montserrat", Font.PLAIN, 13));
		lblOrderID.setBounds(10, 11, 108, 27);
		order.add(lblOrderID);
		
		txtOrderID = new JTextField();
		txtOrderID.setEditable(false);
		txtOrderID.setFont(new Font("Montserrat", Font.PLAIN, 12));
		txtOrderID.setBounds(10, 38, 255, 27);
		order.add(txtOrderID);
		txtOrderID.setColumns(10);
		
		String generatedOrderId = order_repo.generateOrderId();
	    txtOrderID.setText("TRX-001");
		
		JLabel lblPelanggan = new JLabel("Pelanggan");
		lblPelanggan.setFont(new Font("Montserrat", Font.PLAIN, 13));
		lblPelanggan.setBounds(10, 76, 108, 27);
		order.add(lblPelanggan);
		
		JLabel lblTanggal = new JLabel("Tanggal");
		lblTanggal.setFont(new Font("Montserrat", Font.PLAIN, 13));
		lblTanggal.setBounds(10, 142, 108, 27);
		order.add(lblTanggal);
		
		JLabel lblTanggalPengambilan = new JLabel("Tanggal Pengambilan");
		lblTanggalPengambilan.setFont(new Font("Montserrat", Font.PLAIN, 13));
		lblTanggalPengambilan.setBounds(10, 208, 197, 27);
		order.add(lblTanggalPengambilan);
		
		cbStatus = new JComboBox();
		cbStatus.setModel(new DefaultComboBoxModel(new String[] {"Diproses", "Selesai"}));
		cbStatus.setFont(new Font("Montserrat", Font.PLAIN, 12));
		cbStatus.setBounds(10, 302, 257, 27);
		order.add(cbStatus);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Montserrat", Font.PLAIN, 13));
		lblStatus.setBounds(10, 274, 108, 27);
		order.add(lblStatus);
		
		JLabel lbltulisanTotal = new JLabel("Total");
		lbltulisanTotal.setFont(new Font("Montserrat", Font.PLAIN, 13));
		lbltulisanTotal.setBounds(12, 340, 108, 27);
		order.add(lbltulisanTotal);
		
		lblTotal = new JLabel("");
		lblTotal.setFont(new Font("Montserrat", Font.PLAIN, 16));
		lblTotal.setBounds(12, 364, 108, 27);
		order.add(lblTotal);
		
		JLabel lblPembayaran = new JLabel("Pembayaran");
		lblPembayaran.setFont(new Font("Montserrat", Font.PLAIN, 13));
		lblPembayaran.setBounds(12, 397, 108, 27);
		order.add(lblPembayaran);
		
		cbPembayaran = new JComboBox();
		cbPembayaran.setModel(new DefaultComboBoxModel(new String[] {"Tunai", "Transfer", "QRIS"}));
		cbPembayaran.setFont(new Font("Montserrat", Font.PLAIN, 12));
		cbPembayaran.setBounds(12, 425, 257, 27);
		order.add(cbPembayaran);
		
		JLabel lblStatusPembayaran = new JLabel("Status Pembayaran");
		lblStatusPembayaran.setFont(new Font("Montserrat", Font.PLAIN, 13));
		lblStatusPembayaran.setBounds(10, 463, 164, 27);
		order.add(lblStatusPembayaran);
		
		cbStatusPembayaran = new JComboBox();
		cbStatusPembayaran.setModel(new DefaultComboBoxModel(new String[] {"Belum Lunas", "Lunas"}));
		cbStatusPembayaran.setFont(new Font("Montserrat", Font.PLAIN, 12));
		cbStatusPembayaran.setBounds(10, 491, 257, 27);
		order.add(cbStatusPembayaran);
		
		JButton btnSimpanOrder = new JButton("Simpan");
		btnSimpanOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  
			        if (!id_pelanggan.isEmpty()) {
			            Order order = new Order();
			            order.setId(txtOrderID.getText());
			            order.setId_pelanggan(id_pelanggan);
			            order.setTanggal(tgl);
			            order.setTanggal_pengambilan(tgl_kbl);
			            order.setStatus(cbStatus.getSelectedItem().toString());
			            order.setStatus_pembayaran(cbStatusPembayaran.getSelectedItem().toString());
			            order.setPembayaran(cbPembayaran.getSelectedItem().toString());
			            order.setTotal(lblTotal.getText());

			            // Periksa apakah Order ID sudah ada
			            boolean isExistingOrder = order_repo.checkOrderExists(txtOrderID.getText());

			            if (isExistingOrder) {
			                // Jika Order ID sudah ada, lakukan update
			                order_repo.update(order);
			                JOptionPane.showMessageDialog(null, "Order berhasil diperbarui");
			            } else {
			                // Jika Order ID belum ada, lakukan save
			                order_repo.save(order);
			                JOptionPane.showMessageDialog(null, "Order berhasil disimpan");
			            }

			            // Tutup frame dan buka OrderFrame
			            OrderFrame orderFrame = new OrderFrame();
			            orderFrame.setVisible(true);
			            orderFrame.loadTableOrder();
			            dispose();

			        } else {
			            JOptionPane.showMessageDialog(null, "Silahkan pilih Pelanggan terlebih dahulu");
			        }
				
			}
		});
		btnSimpanOrder.setFont(new Font("Montserrat", Font.PLAIN, 11));
		btnSimpanOrder.setBounds(29, 556, 89, 23);
		order.add(btnSimpanOrder);
		
		JButton btnBatalOrder = new JButton("Batal");
		btnBatalOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderFrame orderFrame = new OrderFrame();
	            orderFrame.setVisible(true);
	            orderFrame.loadTableOrder();
	            dispose();
			}
		});
		btnBatalOrder.setFont(new Font("Montserrat", Font.PLAIN, 11));
		btnBatalOrder.setBounds(156, 556, 89, 23);
		order.add(btnBatalOrder);
		
		txtPelanggan = new JTextField();
		txtPelanggan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DialogPelanggan dialog = new DialogPelanggan(OrderDetailFrame.this);
				dialog.setVisible(true);
			}
		});
		txtPelanggan.setFont(new Font("Montserrat", Font.PLAIN, 12));
		txtPelanggan.setColumns(10);
		txtPelanggan.setBounds(10, 114, 255, 27);
		order.add(txtPelanggan);
		
		CalTanggal = new JDateChooser();
		CalTanggal.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		CalTanggal.setBounds(10, 168, 255, 27);
		order.add(CalTanggal);
		CalTanggal.getDateEditor().addPropertyChangeListener("date", evt -> {
		    if (CalTanggal.getDate() != null) {
		    	SimpleDateFormat sdf_tanggal = new SimpleDateFormat("yyyy-MM-dd");
		        tgl = sdf_tanggal.format(CalTanggal.getDate());
		    }
		});
		
		CalTanggalKembali = new JDateChooser();
		CalTanggalKembali.setBounds(10, 236, 257, 27);
		order.add(CalTanggalKembali);
		CalTanggalKembali.getDateEditor().addPropertyChangeListener("date", evt -> {
		    if (CalTanggalKembali.getDate() != null) {
		    	SimpleDateFormat sdf_tanggalkembali = new SimpleDateFormat("yyyy-MM-dd");
		        tgl_kbl = sdf_tanggalkembali.format(CalTanggalKembali.getDate());
		    }
		});
		
		JPanel layanan = new JPanel();
		layanan.setBackground(new Color(192, 192, 192));
		layanan.setBounds(297, 11, 583, 378);
		contentPane.add(layanan);
		layanan.setLayout(null);
		
		JLabel lblLayanan = new JLabel("Layanan");
		lblLayanan.setFont(new Font("Montserrat", Font.PLAIN, 13));
		lblLayanan.setBounds(10, 0, 108, 27);
		layanan.add(lblLayanan);
		
		JLabel lblHargakg = new JLabel("Harga/Kg");
		lblHargakg.setFont(new Font("Montserrat", Font.PLAIN, 13));
		lblHargakg.setBounds(10, 209, 197, 27);
		layanan.add(lblHargakg);
		
		txtHarga = new JTextField();
		txtHarga.setFont(new Font("Montserrat", Font.PLAIN, 12));
		txtHarga.setColumns(10);
		txtHarga.setBounds(10, 237, 255, 27);
		layanan.add(txtHarga);
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setFont(new Font("Montserrat", Font.PLAIN, 13));
		lblJumlah.setBounds(10, 270, 197, 27);
		layanan.add(lblJumlah);
		
		txtJumlah = new JTextField();
		txtJumlah.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String value_jumlah = txtJumlah.getText().toString();
				txtTotal.setText(""+total(value_jumlah));
			}
		});
		txtJumlah.setFont(new Font("Montserrat", Font.PLAIN, 12));
		txtJumlah.setColumns(10);
		txtJumlah.setBounds(10, 298, 255, 27);
		layanan.add(txtJumlah);
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setFont(new Font("Montserrat", Font.PLAIN, 13));
		lblTotal_1.setBounds(318, 270, 197, 27);
		layanan.add(lblTotal_1);
		
		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Montserrat", Font.PLAIN, 12));
		txtTotal.setColumns(10);
		txtTotal.setBounds(318, 298, 255, 27);
		layanan.add(txtTotal);
		
		JButton btnSimpanDetail = new JButton("Simpan");
		btnSimpanDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_order_detail == null) {
					OrderDetail od = new OrderDetail();
					od.setOrder_id(txtOrderID.getText());
					od.setService_id(id_service);
					od.setHarga(txtHarga.getText());
					od.setJumlah(txtJumlah.getText());
					od.setTotal(txtTotal.getText());
					repo_od.save(od);
					JOptionPane.showMessageDialog(null, "berhasil disimpan");
					loadTableDetail();
					reset();
					lblTotal.setText(""+repo_od.total(txtOrderID.getText()));	
				}
			}
		});
		btnSimpanDetail.setFont(new Font("Montserrat", Font.PLAIN, 11));
		btnSimpanDetail.setBounds(78, 336, 89, 23);
		layanan.add(btnSimpanDetail);
		
		JButton btnUbahDetail = new JButton("Ubah");
		btnUbahDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_order_detail != null) {
					OrderDetail od = new OrderDetail();
					od.setOrder_id(txtOrderID.getText());
					od.setService_id(id_service);
					od.setHarga(txtHarga.getText());
					od.setJumlah(txtJumlah.getText());
					od.setTotal(txtTotal.getText());
					od.setId(id_order_detail);
					repo_od.update(od);
					loadTableDetail();
					reset();
					lblTotal.setText(""+repo_od.total(txtOrderID.getText()));
				} else {
					JOptionPane.showMessageDialog(null, "silahkan pilih order terlebih dahulu");
				}
			}
		});
		btnUbahDetail.setFont(new Font("Montserrat", Font.PLAIN, 11));
		btnUbahDetail.setBounds(177, 336, 89, 23);
		layanan.add(btnUbahDetail);
		
		JButton btnHapusDetail = new JButton("Hapus");
		btnHapusDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_order_detail != null) {
					repo_od.delete(id_order_detail);
					reset();
					loadTableDetail();
				} else {
					JOptionPane.showMessageDialog(null, 
							"SIlahkan pilih data yang akan dihapus");
				}
			}
		});
		btnHapusDetail.setFont(new Font("Montserrat", Font.PLAIN, 11));
		btnHapusDetail.setBounds(276, 336, 89, 23);
		layanan.add(btnHapusDetail);
		
		JButton btnBatalDetail = new JButton("Batal");
		btnBatalDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame mf = new MainFrame();
				mf.setVisible(true);
				dispose();
			}
		});
		btnBatalDetail.setFont(new Font("Montserrat", Font.PLAIN, 11));
		btnBatalDetail.setBounds(375, 336, 89, 23);
		layanan.add(btnBatalDetail);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 563, 183);
		layanan.add(scrollPane);
		
		tableLayanan = new JTable();
		scrollPane.setViewportView(tableLayanan);
		tableLayanan.setFont(new Font("Montserrat", Font.PLAIN, 11));
		tableLayanan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id_service = tableLayanan.getValueAt(tableLayanan.getSelectedRow(), 0).toString();
				txtHarga.setText(tableLayanan.getValueAt(tableLayanan.getSelectedRow(), 2).toString());
				
				if(!txtJumlah.getText().isEmpty()) {
					txtTotal.setText(""+total(txtJumlah.getText()));
				}
			}
		});
		
		JPanel tabelOrder = new JPanel();
		tabelOrder.setBounds(297, 400, 583, 234);
		contentPane.add(tabelOrder);
		tabelOrder.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 583, 234);
		tabelOrder.add(scrollPane_1);
		
		tableOrderDetail = new JTable();
		scrollPane_1.setViewportView(tableOrderDetail);
		tableOrderDetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id_order_detail = tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 0).toString();
				txtOrderID.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 1).toString());
				id_service = tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 2).toString();
				txtHarga.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 3).toString());
				txtJumlah.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 4).toString());
				txtTotal.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 5).toString());
				lblTotal.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 5).toString());
			}
		});
		tableOrderDetail.setFont(new Font("Montserrat", Font.PLAIN, 11));
	}

	@Override
	public void onDataReceived(String id, String nama) {
		// TODO Auto-generated method stub
		txtPelanggan.setText(nama);
		id_pelanggan=id;
		
	}
}