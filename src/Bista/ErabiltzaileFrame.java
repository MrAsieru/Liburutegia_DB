package Bista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;

public class ErabiltzaileFrame extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel pnlKatalogoa;
	private JPanel pnlKolekzioak;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErabiltzaileFrame frame = new ErabiltzaileFrame("main");
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
	public ErabiltzaileFrame(String pErabiltzailea) {
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
			tabbedPane.addTab("Katalogoa", null, getPnlKatalogoa(), null);
			tabbedPane.addTab("Kolekzioak", null, getPnlKolekzioak(), null);
		}
		return tabbedPane;
	}
	private JPanel getPnlKatalogoa() {
		if (pnlKatalogoa == null) {
			pnlKatalogoa = new JPanel();
		}
		return pnlKatalogoa;
	}
	private JPanel getPnlKolekzioak() {
		if (pnlKolekzioak == null) {
			pnlKolekzioak = new JPanel();
		}
		return pnlKolekzioak;
	}
}
