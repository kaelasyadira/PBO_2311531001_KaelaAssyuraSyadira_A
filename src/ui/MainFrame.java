package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 311, 337);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(121, 211, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Laundry Apps");
		lblNewLabel.setFont(new Font("Papyrus", Font.BOLD, 14));
		lblNewLabel.setBounds(99, 11, 102, 29);
		contentPane.add(lblNewLabel);
		
		JButton btnPelanggan = new JButton("Pelanggan");
		btnPelanggan.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnPelanggan.setBackground(new Color(255, 181, 106));
		btnPelanggan.setBounds(43, 175, 89, 50);
		contentPane.add(btnPelanggan);
		
		JButton btnLayanan = new JButton("Layanan");
		btnLayanan.setBackground(new Color(138, 255, 138));
		btnLayanan.setBounds(43, 114, 89, 50);
		contentPane.add(btnLayanan);
		
		JButton btnPesanan = new JButton("Pesanan");
		btnPesanan.setBackground(new Color(255, 255, 147));
		btnPesanan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPesanan.setBounds(43, 53, 89, 50);
		contentPane.add(btnPesanan);
		
		JButton btnPengguna = new JButton("Pengguna");
		btnPengguna.setBackground(new Color(255, 74, 255));
		btnPengguna.setBounds(164, 53, 89, 50);
		contentPane.add(btnPengguna);
		
		JButton btnLaporan = new JButton("Laporan");
		btnLaporan.setBackground(new Color(255, 174, 174));
		btnLaporan.setBounds(164, 114, 89, 50);
		contentPane.add(btnLaporan);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBackground(new Color(193, 132, 132));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHome.setBounds(164, 175, 89, 50);
		contentPane.add(btnHome);
		
		JButton btnNewButton = new JButton("KELUAR");
		btnNewButton.setBounds(102, 252, 89, 23);
		contentPane.add(btnNewButton);
	}
}
