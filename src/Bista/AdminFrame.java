package Bista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class AdminFrame extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel pnlTabMaileguak;
	private JPanel pnlTabAutore;
	private JPanel pnlTabKatalogoa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame("main");
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
	public AdminFrame(String pErabiltzailea) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(getTabbedPane());
		setLocationRelativeTo(null);
		setVisible(true);
	}
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addTab("Maileguak", null, getPnlTabMaileguak(), null);
			tabbedPane.addTab("Katalogoa", null, getPnlTabKatalogoa(), null);
			tabbedPane.addTab("Autore", null, getPnlTabAutore(), null);
		}
		return tabbedPane;
	}
	private JPanel getPnlTabMaileguak() {
		if (pnlTabMaileguak == null) {
			pnlTabMaileguak = new JPanel();
		}
		return pnlTabMaileguak;
	}
	private JPanel getPnlTabAutore() {
		if (pnlTabAutore == null) {
			pnlTabAutore = new JPanel();
		}
		return pnlTabAutore;
	}
	private JPanel getPnlTabKatalogoa() {
		if (pnlTabKatalogoa == null) {
			pnlTabKatalogoa = new JPanel();
		}
		return pnlTabKatalogoa;
	}
}
