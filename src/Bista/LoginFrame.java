package Bista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Eredua.ErabiltzaileMota;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class LoginFrame extends JFrame implements Observer{

	private JPanel contentPane;
	private JPanel pnlBotoiak;
	private JButton btnLoginSartu;
	private JPanel pnlLogin;
	private JLabel lblLoginErab;
	private JTextField txfLoginErab;
	private JLabel lblLoginPasahitza;
	private JPasswordField psfLoginPasahitza;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 150);
		setTitle("Liburutegia: login");
		setIconImage(new ImageIcon("res/icon.png").getImage()); //Icono sudoku by Jeremiah / CC BY
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(getPnlLogin());
		contentPane.add(getPnlBotoiak());
		setLocationRelativeTo(null);
		setVisible(true);
	}
	private JPanel getPnlLogin() {
		if (pnlLogin == null) {
			pnlLogin = new JPanel();
			GridBagLayout gbl_pnlLogin = new GridBagLayout();
			gbl_pnlLogin.columnWidths = new int[] {0, 0};
			gbl_pnlLogin.rowHeights = new int[] {0, 0};
			gbl_pnlLogin.columnWeights = new double[]{0.0, 0.0};
			gbl_pnlLogin.rowWeights = new double[]{0.0, 0.0};
			pnlLogin.setLayout(gbl_pnlLogin);
			GridBagConstraints gbc_lblLoginErab = new GridBagConstraints();
			gbc_lblLoginErab.anchor = GridBagConstraints.EAST;
			gbc_lblLoginErab.insets = new Insets(0, 0, 5, 0);
			gbc_lblLoginErab.gridx = 0;
			gbc_lblLoginErab.gridy = 0;
			pnlLogin.add(getLblLoginErab(), gbc_lblLoginErab);
			GridBagConstraints gbc_txfLoginErab = new GridBagConstraints();
			gbc_txfLoginErab.insets = new Insets(0, 20, 0, 0);
			gbc_txfLoginErab.fill = GridBagConstraints.HORIZONTAL;
			gbc_txfLoginErab.gridx = 1;
			gbc_txfLoginErab.gridy = 0;
			pnlLogin.add(getTxfLoginErab(), gbc_txfLoginErab);
			GridBagConstraints gbc_lblLoginPasahitza = new GridBagConstraints();
			gbc_lblLoginPasahitza.anchor = GridBagConstraints.EAST;
			gbc_lblLoginPasahitza.insets = new Insets(0, 0, 5, 0);
			gbc_lblLoginPasahitza.gridx = 0;
			gbc_lblLoginPasahitza.gridy = 1;
			pnlLogin.add(getLblLoginPasahitza(), gbc_lblLoginPasahitza);
			GridBagConstraints gbc_psfLoginPasahitza = new GridBagConstraints();
			gbc_psfLoginPasahitza.insets = new Insets(0, 20, 0, 0);
			gbc_psfLoginPasahitza.fill = GridBagConstraints.HORIZONTAL;
			gbc_psfLoginPasahitza.gridx = 1;
			gbc_psfLoginPasahitza.gridy = 1;
			pnlLogin.add(getPsfLoginPasahitza(), gbc_psfLoginPasahitza);
		}
		return pnlLogin;
	}
	private JLabel getLblLoginErab() {
		if (lblLoginErab == null) {
			lblLoginErab = new JLabel("NAN:");
		}
		return lblLoginErab;
	}
	private JTextField getTxfLoginErab() {
		if (txfLoginErab == null) {
			txfLoginErab = new JTextField();
			txfLoginErab.setColumns(10);
		}
		return txfLoginErab;
	}
	private JLabel getLblLoginPasahitza() {
		if (lblLoginPasahitza == null) {
			lblLoginPasahitza = new JLabel("Pasahitza:");
		}
		return lblLoginPasahitza;
	}
	private JPasswordField getPsfLoginPasahitza() {
		if (psfLoginPasahitza == null) {
			psfLoginPasahitza = new JPasswordField();
			psfLoginPasahitza.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					logeatu();
				}
			});
		}
		return psfLoginPasahitza;
	}
	private JPanel getPnlBotoiak() {
		if (pnlBotoiak == null) {
			pnlBotoiak = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlBotoiak.getLayout();
			flowLayout.setVgap(0);
			flowLayout.setHgap(0);
			pnlBotoiak.add(getBtnLoginSartu());
		}
		return pnlBotoiak;
	}
	private JButton getBtnLoginSartu() {
		if (btnLoginSartu == null) {
			btnLoginSartu = new JButton("Sartu");
			btnLoginSartu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					logeatu();
				}
			});
		}
		return btnLoginSartu;
	}
	
	private void logeatu() {
		System.out.println("[Bista.Login]: Sartu botoia sakatuta");
		Eredua.Login saioa = new Eredua.Login(getTxfLoginErab().getText(), new String(getPsfLoginPasahitza().getPassword()), this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Eredua.Login && arg instanceof Object[] && 
			((Object[]) arg).length == 2 && 
			((Object[]) arg)[0] instanceof ErabiltzaileMota && 
			((Object[]) arg)[1] instanceof String) {
			if (((Object[]) arg)[0] == ErabiltzaileMota.LIBURUZAIN) {
				int aukera = JOptionPane.showOptionDialog(this, 
														  "Liburuzain edo erabiltzaile arrunt bat bezala sartu nahi zara?", 
														  "Saio mota aukeratu", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,  
														  new String[] {"Liburuzain", "Arrunta"}, "Liburuzain");
				if (aukera == 0) {
					System.out.println("[Bista.Login]: Liburuzain bezala logeatuta, liburuzain bista irekiko da");
					setVisible(false);
					new Bista.LiburuzainaFrame((String)((Object[]) arg)[1]);
				} else {
					System.out.println("[Bista.Login]: Liburuzain bezala logeatuta, bista arrunta irekiko da");
					setVisible(false);
					new Bista.ErabiltzaileFrame((String)((Object[]) arg)[1]);
				}
				
			} else if (((Object[]) arg)[0] == ErabiltzaileMota.ARRUNTA) {
				System.out.println("[Bista.Login]: Erabiltzaile arrunt bezala logeatuta, bista arrunta irekiko da");
				setVisible(false);
				new Bista.ErabiltzaileFrame((String)((Object[]) arg)[1]);
			} else if (((Object[]) arg)[0] == ErabiltzaileMota.OKERRA) {
				System.out.println("[Bista.Login]: Erabiltzailea edo pasahitza oker daude");
				getPsfLoginPasahitza().setText("");
				JOptionPane.showMessageDialog(contentPane, "Ezin izan da logeatu, mezedez erabiltzailea eta pasahitza konprobatu", "Errorea", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
