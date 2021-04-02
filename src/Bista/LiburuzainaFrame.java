package Bista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Egitura.Argitaletxea;
import Egitura.Idazlea;
import Egitura.Erabiltzailea;
import Egitura.Liburua;
import Eredua.Liburuzaina;
import Eredua.NotifikazioMotak;

import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.JComboBox;

public class LiburuzainaFrame extends JFrame implements Observer {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel pnlTabMaileguak;
	private JPanel pnlTabIdazleak;
	private JPanel pnlTabKatalogoa;
	private JPanel pnlTabErabiltzaile;
	private JPanel pnlErabBotoi;
	private JButton btnErabSortu;
	private JButton btnErabPasahitza;
	private JScrollPane scpErabTaula;
	private JTable tblErab;
	private DefaultTableModel dtmErab;
	private JPanel pnlErabBilaketa;
	private JLabel lblErabNANBilatu;
	private JTextField txfErabNANBilatu;
	private JSeparator sepErab1;
	private JLabel lblErabIzenaBilatu;
	private JTextField txfErabIzenaBilatu;
	private JTextField txfErabAbizenaBilatu;
	private JSeparator sepErab2;
	private JButton btnErabBilaketaBilatu;
	private JButton btnErabBilaketaGarbitu;
	private JLabel lblErabAbizenaBilatu;
	private JLabel lblKatGehISBN;
	private JLabel lblKatEzaISBN;
	private JTextField txfKatGehISBN;
	private JLabel lblKatGehIzena;
	private JTextField txfKatGehIzena;
	private JLabel lblKatGehLeng;
	private JComboBox<String> cbxKatGehLeng;
	private JLabel lblKatGehArgit;
	private JLabel lblKatGehIdazle;
	private JButton btnKatGehSortu;
	private JPanel pnlKatEzaForm;
	private JTextField txfKatEzaISBN;
	private JButton btnKatEzaEzabatu;
	private JComboBox<ComboItem<String>> cbxKatGehArgit;
	private JComboBox<ComboItem<Integer>> cbxKatGehIdazle;
	private JPanel pnlMaiHasi;
	private JPanel pnlMaiBueltatu;
	private JLabel lblMaiHasISBN;
	private JTextField txfMaiHasISBN;
	private JLabel lblMaiHasNAN;
	private JTextField txfMaiHasNAN;
	private JButton btnMaiHasiMailegatu;
	private JLabel lblMaiBueISBN;
	private JTextField txfMaiBueISBN;
	private JButton btnMaiBueBueltatu;
	private JPanel pnlTabArgitaletxeak;
	private JPanel pnlKatBotoi;
	private JScrollPane scpKatTaula;
	private JTable tblKat;
	private DefaultTableModel dtmKat;
	private JButton btnKatGehitu;
	private JButton btnKatKendu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LiburuzainaFrame frame = new LiburuzainaFrame("main");
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
	public LiburuzainaFrame(String pErabiltzailea) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 300);
		Liburuzaina.getInstantzia().erabiltzaileEzarri(pErabiltzailea);
		Liburuzaina.getInstantzia().addObserver(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getTabbedPane(), BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	//Logika
	@Override
	public void update(Observable o, Object arg) {
		// arg -> Object[]: 
		//			arg[0] -> NotifikazioMotak,
		//			arg[1,2,3,...] -> Datuak
		// Datu egiturak:
		// LIBURUZAIN_ERAB_LISTA_EGUNERATU: 				Erabiltzaile[]
		// LIBURUZAIN_ERAB_SORTU_ONDO:						ezer
		// LIBURUZAIN_ERAB_SORTU_TXARTO:					String
		// LIBURUZAIN_ERAB_PASAHITZA_ONDO:					ezer
		// LIBURUZAIN_ERAB_PASAHITZA_TXARTO:				String
		// LIBURUZAIN_KAT_TAULA_EGUNERATUA					Liburua[]
		// LIBURUZAIN_KAT_GEH_LENGOAIA_LISTA_EGUNERATU: 	String[]
		// LIBURUZAIN_KAT_GEH_ARGITALETXE_LISTA_EGUNERATU: 	String[]
		// LIBURUZAIN_KAT_GEH_IDAZLE_LISTA_EGUNERATU: 		String[]
		// LIBURUZAIN_KAT_GEH_ONDO_SORTUTA: 				ezer
		// LIBURUZAIN_KAT_GEH_TXARTO_SORTUTA: 				String
		// LIBURUZAIN_KAT_EZA_ONDO_EZABATUTA: 				ezer
		// LIBURUZAIN_KAT_EZA_TXARTO_EZABATUTA:				String
		if (o instanceof Liburuzaina && arg instanceof Object[] && ((Object[])arg).length > 0 && ((Object[])arg)[0] instanceof NotifikazioMotak) {
			switch ((NotifikazioMotak)((Object[])arg)[0]) {
			case LIBURUZAIN_ERAB_LISTA_EGUNERATU:
				if (((Object[])arg)[1] instanceof Erabiltzailea[]){
					System.out.println("[Bista.Liburuzaina]: Erabiltzaileen taula eguneratu da");
					erabiltzaileListaEguneratu((Erabiltzailea[]) ((Object[])arg)[1]);
				} else System.out.println("[Bista.Liburuzaina]: LIBURUZAIN_ERAB_LISTA_EGUNERATU ez du eskatutakoa jaso");
				break;
			case LIBURUZAIN_ERAB_SORTU_ONDO:
				JOptionPane.showMessageDialog(contentPane, "Erabiltzailea ondo sortu da", "Erabiltzailea sortuta", JOptionPane.PLAIN_MESSAGE);
				break;
			case LIBURUZAIN_ERAB_SORTU_TXARTO:
				if (((Object[])arg)[1] instanceof String) {
					JOptionPane.showMessageDialog(contentPane, "Ezin izan da erabiltzailea sortu:\n"+(String)((Object[])arg)[1], "Errorea", JOptionPane.ERROR_MESSAGE);
				} else System.out.println("[Bista.Liburuzaina]: LIBURUZAIN_ERAB_SORTU_TXARTO ez du eskatutakoa jaso");
				break;
			case LIBURUZAIN_ERAB_PASAHITZA_ONDO:
				JOptionPane.showMessageDialog(contentPane, "Pasahitza ondo aldatu egin da", "Pasahitza aldatuta", JOptionPane.PLAIN_MESSAGE);
				break;
			case LIBURUZAIN_ERAB_PASAHITZA_TXARTO:
				if (((Object[])arg)[1] instanceof String) {
					JOptionPane.showMessageDialog(contentPane, "Ezin izan da pasahitza aldatu:\n"+(String)((Object[])arg)[1], "Errorea", JOptionPane.ERROR_MESSAGE);
				} else System.out.println("[Bista.Liburuzaina]: LIBURUZAIN_ERAB_PASAHITZA_TXARTO ez du eskatutakoa jaso");
				break;
			case LIBURUZAIN_KAT_TAULA_EGUNERATUA:
				if (((Object[])arg)[1] instanceof Liburua[]) {
					System.out.println("[Bista.Liburuzaina]: Katalogoaren taula eguneratu da");
					katalogoaListaEguneratu((Liburua[]) ((Object[])arg)[1]);
				} else System.out.println("[Bista.Liburuzaina]: LIBURUZAIN_KAT_TAULA_EGUNERATUA ez du eskatutakoa jaso");
				break;
			case LIBURUZAIN_KAT_GEH_LENGOAIA_LISTA_EGUNERATU:

				break;
			case LIBURUZAIN_KAT_GEH_ARGITALETXE_LISTA_EGUNERATU:
				
				break;
			case LIBURUZAIN_KAT_GEH_IDAZLE_LISTA_EGUNERATU:
				
				break;
			case LIBURUZAIN_KAT_GEH_ONDO_SORTUTA:
				
				break;
			case LIBURUZAIN_KAT_GEH_TXARTO_SORTUTA:
				
				break;
			case LIBURUZAIN_KAT_EZA_ONDO_EZABATUTA:
				
				break;
			case LIBURUZAIN_KAT_EZA_TXARTO_EZABATUTA:
				
				break;

			default:
				break;
			}
		}
		
	}
	
	private void erabiltzaileListaEguneratu(Erabiltzailea[] pLista) {
		dtmErab.setRowCount(0);
		for (Erabiltzailea erab:pLista) {
			dtmErab.addRow(new Object[] {erab.nan, erab.izena, erab.abizena, erab.jaiotzeData, erab.generoa});
		}
	}
	
	private void katalogoaListaEguneratu(Liburua[] pLista) {
		dtmKat.setRowCount(0);
		for (Liburua lib:pLista) {
			dtmKat.addRow(new Object[] {lib.isbn, lib.izena, lib.argitaratzeData, lib.lengoaia, (lib.erreserbatua)?"Erreserbatuta":((lib.mailegatuta)?"Mailegatuta":"Eskuragarri"), lib.erabiltzaileaNAN});
		}
	}
	
	private void katalogoaLengoaiakEguneratu(String[] pLista) {
		cbxKatGehLeng.removeAll();
		for (String leng:pLista) {
			cbxKatGehLeng.addItem(leng);
		}
	}
	
	private void katalogoaArgitaletxeakEguneratu(Argitaletxea[] pLista) {
		cbxKatGehArgit.removeAll();
		for(Argitaletxea arg:pLista) {
			cbxKatGehArgit.addItem(new ComboItem<String>(arg.Izena, arg.IFK));
		}
	}
	
	private void katalogoaIdazleakEguneratu(Idazlea[] pLista) {
		cbxKatGehIdazle.removeAll();
		for(Idazlea idl:pLista) {
			cbxKatGehIdazle.addItem(new ComboItem<Integer>(idl.Izena, idl.IdaleZenbakia));
		}
	}
	
	private class ComboItem<T> {
		private String key;
		private T value;
		
		public ComboItem(String pKey, T pValue) {
			this.key = pKey;
			this.value = pValue;
		}
		
		@Override
		public String toString() {
			return key;
		}
		
		public String getKey() {
			return key;
		}
		
		public T getValue() {
			return value;
		}
	}
	
	//GUI elementuak
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
			tabbedPane.addTab("Erabiltzaileak", null, getPnlTabErabiltzaile(), null);
			tabbedPane.addTab("Katalogoa", null, getPnlTabKatalogoa(), null);
			tabbedPane.addTab("Maileguak", null, getPnlTabMaileguak(), null);
			tabbedPane.addTab("Idazleak", null, getPnlTabIdazleak(), null);
			tabbedPane.addTab("Argitaletxeak", null, getPnlTabArgitaletxeak(), null);
		}
		return tabbedPane;
	}
	private JPanel getPnlTabMaileguak() {
		if (pnlTabMaileguak == null) {
			pnlTabMaileguak = new JPanel();
			GridBagLayout gbl_pnlTabMaileguak = new GridBagLayout();
			gbl_pnlTabMaileguak.columnWidths = new int[] {0};
			gbl_pnlTabMaileguak.rowHeights = new int[] {106, 0};
			gbl_pnlTabMaileguak.columnWeights = new double[]{1.0};
			gbl_pnlTabMaileguak.rowWeights = new double[]{0.0, 0.0};
			pnlTabMaileguak.setLayout(gbl_pnlTabMaileguak);
			GridBagConstraints gbc_pnlMaiHasi = new GridBagConstraints();
			gbc_pnlMaiHasi.anchor = GridBagConstraints.NORTHWEST;
			gbc_pnlMaiHasi.insets = new Insets(0, 0, 5, 5);
			gbc_pnlMaiHasi.gridx = 0;
			gbc_pnlMaiHasi.gridy = 0;
			pnlTabMaileguak.add(getPnlMaiHasi(), gbc_pnlMaiHasi);
			GridBagConstraints gbc_pnlMaiBueltatu = new GridBagConstraints();
			gbc_pnlMaiBueltatu.anchor = GridBagConstraints.NORTHWEST;
			gbc_pnlMaiBueltatu.insets = new Insets(0, 0, 0, 5);
			gbc_pnlMaiBueltatu.gridx = 0;
			gbc_pnlMaiBueltatu.gridy = 1;
			pnlTabMaileguak.add(getPnlMaiBueltatu(), gbc_pnlMaiBueltatu);
		}
		return pnlTabMaileguak;
	}
	private JPanel getPnlTabIdazleak() {
		if (pnlTabIdazleak == null) {
			pnlTabIdazleak = new JPanel();
		}
		return pnlTabIdazleak;
	}
	private JPanel getPnlTabKatalogoa() {
		if (pnlTabKatalogoa == null) {
			pnlTabKatalogoa = new JPanel();
			pnlTabKatalogoa.setLayout(new BorderLayout(0, 0));
			pnlTabKatalogoa.add(getPnlKatBotoi(), BorderLayout.NORTH);
			pnlTabKatalogoa.add(getScpKatTaula(), BorderLayout.CENTER);
		}
		return pnlTabKatalogoa;
	}
	private JPanel getPnlTabErabiltzaile() {
		if (pnlTabErabiltzaile == null) {
			pnlTabErabiltzaile = new JPanel();
			pnlTabErabiltzaile.setLayout(new BorderLayout(0, 0));
			pnlTabErabiltzaile.add(getPnlErabBotoi(), BorderLayout.NORTH);
			pnlTabErabiltzaile.add(getScpErabTaula(), BorderLayout.CENTER);
			pnlTabErabiltzaile.add(getPnlErabBilaketa(), BorderLayout.EAST);
			
		}
		return pnlTabErabiltzaile;
	}
	private JPanel getPnlErabBotoi() {
		if (pnlErabBotoi == null) {
			pnlErabBotoi = new JPanel();
			pnlErabBotoi.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			pnlErabBotoi.add(getBtnErabSortu());
			pnlErabBotoi.add(getBtnErabPasahitza());
		}
		return pnlErabBotoi;
	}
	private JButton getBtnErabSortu() {
		if (btnErabSortu == null) {
			btnErabSortu = new JButton("Sortu");
			btnErabSortu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("[Kontrolatzailea]: (Erabiltzaile panela) btnErabSortu klikatuta");
					popUpErabiltzaileaSortu();
				}
			});
		}
		return btnErabSortu;
	}
	
	private void popUpErabiltzaileaSortu() {
		JPanel panel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[] {0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gridBagLayout);
		GridBagConstraints gbc_lblNan = new GridBagConstraints();
		gbc_lblNan.anchor = GridBagConstraints.WEST;
		gbc_lblNan.insets = new Insets(0, 0, 5, 0);
		gbc_lblNan.gridx = 0;
		gbc_lblNan.gridy = 0;
		JLabel lblNan = new JLabel("NAN:");
		panel.add(lblNan, gbc_lblNan);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 20);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		JTextField txfPopNAN = new JTextField();
		txfPopNAN.setColumns(10);
		panel.add(txfPopNAN, gbc_textField);
		GridBagConstraints gbc_lblIzena = new GridBagConstraints();
		gbc_lblIzena.anchor = GridBagConstraints.WEST;
		gbc_lblIzena.insets = new Insets(0, 0, 5, 0);
		gbc_lblIzena.gridx = 0;
		gbc_lblIzena.gridy = 2;
		JLabel lblIzena = new JLabel("Izena:");
		panel.add(lblIzena, gbc_lblIzena);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 3;
		JTextField txfPopIzena = new JTextField();
		txfPopIzena.setColumns(10);
		panel.add(txfPopIzena, gbc_textField_1);
		GridBagConstraints gbc_lblGeneroa = new GridBagConstraints();
		gbc_lblGeneroa.anchor = GridBagConstraints.WEST;
		gbc_lblGeneroa.insets = new Insets(0, 0, 5, 0);
		gbc_lblGeneroa.gridx = 0;
		gbc_lblGeneroa.gridy = 4;
		JLabel lblGeneroa = new JLabel("Generoa:");
		panel.add(lblGeneroa, gbc_lblGeneroa);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.anchor = GridBagConstraints.WEST;
		gbc_textField_3.gridx = 0;
		gbc_textField_3.gridy = 5;
		JTextField txfPopGeneroa = new JTextField();
		txfPopGeneroa.setColumns(10);
		panel.add(txfPopGeneroa, gbc_textField_3);
		GridBagConstraints gbc_lblPasahitza = new GridBagConstraints();
		gbc_lblPasahitza.anchor = GridBagConstraints.WEST;
		gbc_lblPasahitza.insets = new Insets(0, 0, 5, 0);
		gbc_lblPasahitza.gridx = 1;
		gbc_lblPasahitza.gridy = 0;
		JLabel lblPasahitza = new JLabel("Pasahitza:");
		panel.add(lblPasahitza, gbc_lblPasahitza);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.WEST;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		JPasswordField pwfPopPasahitza = new JPasswordField();
		pwfPopPasahitza.setColumns(10);
		panel.add(pwfPopPasahitza, gbc_passwordField);
		GridBagConstraints gbc_lblAbizena = new GridBagConstraints();
		gbc_lblAbizena.anchor = GridBagConstraints.WEST;
		gbc_lblAbizena.insets = new Insets(0, 0, 5, 0);
		gbc_lblAbizena.gridx = 1;
		gbc_lblAbizena.gridy = 2;
		JLabel lblAbizena = new JLabel("Abizena:");
		panel.add(lblAbizena, gbc_lblAbizena);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 3;
		JTextField txfPopAbizena = new JTextField();
		txfPopAbizena.setColumns(10);
		GridBagConstraints gbc_jaio = new GridBagConstraints();
		gbc_jaio.anchor = GridBagConstraints.WEST;
		gbc_jaio.insets = new Insets(0, 0, 5, 0);
		gbc_jaio.gridx = 1;
		gbc_jaio.gridy = 4;
		JLabel lblPopJaioD = new JLabel("Jaiotze data:");
		panel.add(lblPopJaioD, gbc_jaio);
		GridBagConstraints gbc_txfJaiotzeData = new GridBagConstraints();
		gbc_txfJaiotzeData.anchor = GridBagConstraints.WEST;
		gbc_txfJaiotzeData.gridx = 1;
		gbc_txfJaiotzeData.gridy = 5;
		JTextField txfJaiotzeData = new HintTextField("YYYY-MM-DD");
		txfJaiotzeData.setColumns(10);
		panel.add(txfJaiotzeData, gbc_txfJaiotzeData);
		
		panel.add(txfPopAbizena, gbc_textField_2);
		
		int aukera = JOptionPane.showConfirmDialog(this, panel, "Sortu erabiltzailea", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (aukera == 0) {
			Erabiltzailea erab = new Erabiltzailea();
			erab.nan = txfPopNAN.getText();
			erab.izena = txfPopIzena.getText();
			erab.abizena = txfPopAbizena.getText();
			erab.jaiotzeData = txfJaiotzeData.getText();
			erab.generoa = txfPopGeneroa.getText();
			System.out.println(String.format("[Kontrolatzailea]: (Erabiltzailea sortu Pop-Up) Erabiltzailea sortzeko klikatuta. Nan:%s, Izena:%s, Abizena:%s, JaiotzeData:%s, Generoa:%s, Pasahitza:%s", erab.nan, erab.izena, erab.abizena, erab.jaiotzeData, erab.generoa, new String(pwfPopPasahitza.getPassword())));
			Liburuzaina.getInstantzia().addErabiltzaileArrunta(erab, new String(pwfPopPasahitza.getPassword()));
		} else {
			System.out.println("[Kontrolatzailea]: (Erabiltzailea sortu Pop-Up) Ateratzeko klikatuta");
		}
		
	}
	
	private JButton getBtnErabPasahitza() {
		if (btnErabPasahitza == null) {
			btnErabPasahitza = new JButton("Pasahitza eguneratu");
			btnErabPasahitza.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("[Kontrolatzailea]: (Erabiltzaile panela) btnErabPasahitza klikatuta");
					if (tblErab.getSelectedRow() >= 0) {
						String pasahitza = JOptionPane.showInputDialog(contentPane, String.format("Pasahitza berria sortu %s erabiltzailearentzat:", (String) tblErab.getValueAt(tblErab.getSelectedRow(), 0)), "Pasahitza berria", JOptionPane.PLAIN_MESSAGE);
						if (pasahitza != null) {
							System.out.println(String.format("[Kontrolatzailea]: (Pasahitza aldatu Pop-Up) pasahitza aldatzeko klikatu. NAN:%s, Pasahitza:%s", (String) tblErab.getValueAt(tblErab.getSelectedRow(), 0), pasahitza));
							Liburuzaina.getInstantzia().aldatuPasahitza((String) tblErab.getValueAt(tblErab.getSelectedRow(), 0), pasahitza);
						} else System.out.println("[Kontrolatzailea]: (Pasahitza aldatu Pop-Up) ateratzeko klikatu");
					} else {
						JOptionPane.showMessageDialog(contentPane, "Pasahitza aldatzeko lehenengo erabiltzaile bat aukeratu.", "Errorea", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return btnErabPasahitza;
	}
	
	private JScrollPane getScpErabTaula() {
		if (scpErabTaula == null) {
			scpErabTaula = new JScrollPane();
			scpErabTaula.setViewportView(getTblErab());
		}
		return scpErabTaula;
	}
	private JTable getTblErab() {
		if (tblErab == null) {
			dtmErab = new DefaultTableModel(new Object[][] {}, 
					 						new String[] {"NAN", "Izena","Abizena","JaiotzeD","Generoa"}) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tblErab = new JTable(dtmErab);
			Liburuzaina.getInstantzia().getErabiltzaileak();
		}
		return tblErab;
	}
	private JPanel getPnlErabBilaketa() {
		if (pnlErabBilaketa == null) {
			pnlErabBilaketa = new JPanel();
			GridBagLayout gbl_pnlErabBilaketa = new GridBagLayout();
			gbl_pnlErabBilaketa.columnWidths = new int[] {0};
			gbl_pnlErabBilaketa.rowHeights = new int[]{19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_pnlErabBilaketa.columnWeights = new double[]{1.0};
			gbl_pnlErabBilaketa.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			pnlErabBilaketa.setLayout(gbl_pnlErabBilaketa);
			GridBagConstraints gbc_lblErabNANBilatu = new GridBagConstraints();
			gbc_lblErabNANBilatu.insets = new Insets(0, 0, 5, 0);
			gbc_lblErabNANBilatu.anchor = GridBagConstraints.WEST;
			gbc_lblErabNANBilatu.gridx = 0;
			gbc_lblErabNANBilatu.gridy = 0;
			pnlErabBilaketa.add(getLblErabNANBilatu(), gbc_lblErabNANBilatu);
			GridBagConstraints gbc_txfErabNANBilatu = new GridBagConstraints();
			gbc_txfErabNANBilatu.insets = new Insets(0, 0, 5, 0);
			gbc_txfErabNANBilatu.fill = GridBagConstraints.HORIZONTAL;
			gbc_txfErabNANBilatu.gridx = 0;
			gbc_txfErabNANBilatu.gridy = 1;
			pnlErabBilaketa.add(getTxfErabNANBilatu(), gbc_txfErabNANBilatu);
			GridBagConstraints gbc_sepErab1 = new GridBagConstraints();
			gbc_sepErab1.fill = GridBagConstraints.HORIZONTAL;
			gbc_sepErab1.insets = new Insets(0, 0, 5, 0);
			gbc_sepErab1.gridx = 0;
			gbc_sepErab1.gridy = 2;
			pnlErabBilaketa.add(getSepErab1(), gbc_sepErab1);
			GridBagConstraints gbc_lblErabIzenaBilatu = new GridBagConstraints();
			gbc_lblErabIzenaBilatu.insets = new Insets(0, 0, 5, 0);
			gbc_lblErabIzenaBilatu.gridx = 0;
			gbc_lblErabIzenaBilatu.gridy = 3;
			pnlErabBilaketa.add(getLblErabIzenaBilatu(), gbc_lblErabIzenaBilatu);
			GridBagConstraints gbc_txfErabIzenaBilatu = new GridBagConstraints();
			gbc_txfErabIzenaBilatu.insets = new Insets(0, 0, 5, 0);
			gbc_txfErabIzenaBilatu.fill = GridBagConstraints.HORIZONTAL;
			gbc_txfErabIzenaBilatu.gridx = 0;
			gbc_txfErabIzenaBilatu.gridy = 5;
			pnlErabBilaketa.add(getTxfErabIzenaBilatu(), gbc_txfErabIzenaBilatu);
			GridBagConstraints gbc_txfErabAbizenaBilatu = new GridBagConstraints();
			gbc_txfErabAbizenaBilatu.insets = new Insets(0, 0, 5, 0);
			gbc_txfErabAbizenaBilatu.fill = GridBagConstraints.HORIZONTAL;
			gbc_txfErabAbizenaBilatu.gridx = 0;
			gbc_txfErabAbizenaBilatu.gridy = 6;
			pnlErabBilaketa.add(getTxfErabAbizenaBilatu(), gbc_txfErabAbizenaBilatu);
			GridBagConstraints gbc_sepErab2 = new GridBagConstraints();
			gbc_sepErab2.insets = new Insets(0, 0, 5, 0);
			gbc_sepErab2.gridx = 0;
			gbc_sepErab2.gridy = 7;
			pnlErabBilaketa.add(getSepErab2(), gbc_sepErab2);
			GridBagConstraints gbc_btnErabBilaketaBilatu = new GridBagConstraints();
			gbc_btnErabBilaketaBilatu.insets = new Insets(0, 0, 5, 0);
			gbc_btnErabBilaketaBilatu.gridx = 0;
			gbc_btnErabBilaketaBilatu.gridy = 8;
			pnlErabBilaketa.add(getBtnErabBilaketaBilatu(), gbc_btnErabBilaketaBilatu);
			GridBagConstraints gbc_btnErabBilaketaGarbitu = new GridBagConstraints();
			gbc_btnErabBilaketaGarbitu.insets = new Insets(0, 0, 5, 0);
			gbc_btnErabBilaketaGarbitu.gridx = 0;
			gbc_btnErabBilaketaGarbitu.gridy = 9;
			pnlErabBilaketa.add(getBtnErabBilaketaGarbitu(), gbc_btnErabBilaketaGarbitu);
			GridBagConstraints gbc_lblErabAbizenaBilatu = new GridBagConstraints();
			gbc_lblErabAbizenaBilatu.gridx = 0;
			gbc_lblErabAbizenaBilatu.gridy = 4;
			pnlErabBilaketa.add(getLblErabAbizenaBilatu(), gbc_lblErabAbizenaBilatu);
			pnlErabBilaketa.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.RIGHT));
		}
		return pnlErabBilaketa;
	}
	private JLabel getLblErabNANBilatu() {
		if (lblErabNANBilatu == null) {
			lblErabNANBilatu = new JLabel("NAN bilaketa:");
		}
		return lblErabNANBilatu;
	}
	private JTextField getTxfErabNANBilatu() {
		if (txfErabNANBilatu == null) {
			txfErabNANBilatu = new HintTextField("NAN");
			txfErabNANBilatu.setColumns(9);
		}
		return txfErabNANBilatu;
	}
	private JSeparator getSepErab1() {
		if (sepErab1 == null) {
			sepErab1 = new JSeparator();
		}
		return sepErab1;
	}
	private JLabel getLblErabIzenaBilatu() {
		if (lblErabIzenaBilatu == null) {
			lblErabIzenaBilatu = new JLabel("Izen-abizen");
		}
		return lblErabIzenaBilatu;
	}
	private JTextField getTxfErabIzenaBilatu() {
		if (txfErabIzenaBilatu == null) {
			txfErabIzenaBilatu = new HintTextField("Izena");
			txfErabIzenaBilatu.setToolTipText("Izena");
			txfErabIzenaBilatu.setColumns(9);
		}
		return txfErabIzenaBilatu;
	}
	private JTextField getTxfErabAbizenaBilatu() {
		if (txfErabAbizenaBilatu == null) {
			txfErabAbizenaBilatu = new HintTextField("Abizena");
			txfErabAbizenaBilatu.setToolTipText("Abizena");
			txfErabAbizenaBilatu.setColumns(9);
		}
		return txfErabAbizenaBilatu;
	}
	private JSeparator getSepErab2() {
		if (sepErab2 == null) {
			sepErab2 = new JSeparator();
		}
		return sepErab2;
	}
	private JButton getBtnErabBilaketaBilatu() {
		if (btnErabBilaketaBilatu == null) {
			btnErabBilaketaBilatu = new JButton("Bilatu");
			btnErabBilaketaBilatu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(String.format("[Kontrolatzailea]: (Erabiltzaile panela) btnErabBilaketaBilatu klikatuta. NAN:%s, Izena:%s, Abizena:%s", txfErabNANBilatu.getText(), txfErabIzenaBilatu.getText(), txfErabAbizenaBilatu.getText()));
					Liburuzaina.getInstantzia().getErabiltzaileak(txfErabNANBilatu.getText(), txfErabIzenaBilatu.getText(), txfErabAbizenaBilatu.getText());
				}
			});
		}
		return btnErabBilaketaBilatu;
	}
	private JButton getBtnErabBilaketaGarbitu() {
		if (btnErabBilaketaGarbitu == null) {
			btnErabBilaketaGarbitu = new JButton("Garbitu");
			btnErabBilaketaGarbitu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("[Kontrolatzailea]: (Erabiltzaile panela) btnErabBilaketaGarbitu klikatuta");
					getTxfErabNANBilatu().setText("");
					getTxfErabIzenaBilatu().setText("");
					getTxfErabAbizenaBilatu().setText("");
					Liburuzaina.getInstantzia().getErabiltzaileak();				
				}
			});
		}
		return btnErabBilaketaGarbitu;
	}
	private JLabel getLblErabAbizenaBilatu() {
		if (lblErabAbizenaBilatu == null) {
			lblErabAbizenaBilatu = new JLabel("bilaketa:");
		}
		return lblErabAbizenaBilatu;
	}
	private void popUpKatalogoaLiburuaGehitu() {
		JPanel panel = new JPanel();
		GridBagLayout gbl_pnlKatalogoaGehitu = new GridBagLayout();
		gbl_pnlKatalogoaGehitu.columnWidths = new int[] {0, 0};
		gbl_pnlKatalogoaGehitu.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
		gbl_pnlKatalogoaGehitu.columnWeights = new double[]{0.0, 1.0};
		gbl_pnlKatalogoaGehitu.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_pnlKatalogoaGehitu);
		GridBagConstraints gbc_lblKatGehISBN = new GridBagConstraints();
		gbc_lblKatGehISBN.insets = new Insets(0, 0, 5, 5);
		gbc_lblKatGehISBN.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblKatGehISBN.gridx = 0;
		gbc_lblKatGehISBN.gridy = 0;
		JLabel lblKatGehISBN = new JLabel("ISBN:");
		panel.add(lblKatGehISBN, gbc_lblKatGehISBN);
		GridBagConstraints gbc_lblKatGehArgit = new GridBagConstraints();
		gbc_lblKatGehArgit.anchor = GridBagConstraints.WEST;
		gbc_lblKatGehArgit.insets = new Insets(0, 0, 5, 0);
		gbc_lblKatGehArgit.gridx = 1;
		gbc_lblKatGehArgit.gridy = 0;
		JLabel lblKatGehArgit = new JLabel("Argitaletxea:");
		panel.add(lblKatGehArgit, gbc_lblKatGehArgit);
		GridBagConstraints gbc_lblKatGehIdazle = new GridBagConstraints();
		gbc_lblKatGehIdazle.anchor = GridBagConstraints.WEST;
		gbc_lblKatGehIdazle.insets = new Insets(0, 0, 5, 0);
		gbc_lblKatGehIdazle.gridx = 1;
		gbc_lblKatGehIdazle.gridy = 2;
		JLabel lblKatGehIdazle = new JLabel("Idazlea:");
		panel.add(lblKatGehIdazle, gbc_lblKatGehIdazle);
		GridBagConstraints gbc_cbxKatGehArgit = new GridBagConstraints();
		gbc_cbxKatGehArgit.weightx = 0.5;
		gbc_cbxKatGehArgit.insets = new Insets(0, 0, 5, 0);
		gbc_cbxKatGehArgit.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbxKatGehArgit.gridx = 1;
		gbc_cbxKatGehArgit.gridy = 1;
		cbxKatGehArgit = new JComboBox();
		panel.add(cbxKatGehArgit, gbc_cbxKatGehArgit);
		GridBagConstraints gbc_cbxKatGehIdazle = new GridBagConstraints();
		gbc_cbxKatGehIdazle.insets = new Insets(0, 0, 5, 0);
		gbc_cbxKatGehIdazle.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbxKatGehIdazle.gridx = 1;
		gbc_cbxKatGehIdazle.gridy = 3;
		cbxKatGehIdazle = new JComboBox();
		panel.add(cbxKatGehIdazle, gbc_cbxKatGehIdazle);
		GridBagConstraints gbc_cbxKatGehLeng = new GridBagConstraints();
		gbc_cbxKatGehLeng.insets = new Insets(0, 0, 0, 5);
		gbc_cbxKatGehLeng.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbxKatGehLeng.gridx = 0;
		gbc_cbxKatGehLeng.gridy = 5;
		cbxKatGehLeng = new JComboBox();
		panel.add(cbxKatGehLeng, gbc_cbxKatGehLeng);
		GridBagConstraints gbc_lblKatGehLeng = new GridBagConstraints();
		gbc_lblKatGehLeng.anchor = GridBagConstraints.WEST;
		gbc_lblKatGehLeng.insets = new Insets(0, 0, 5, 5);
		gbc_lblKatGehLeng.gridx = 0;
		gbc_lblKatGehLeng.gridy = 4;
		JLabel lblKatGehLeng = new JLabel("Lengoaia:");
		panel.add(lblKatGehLeng, gbc_lblKatGehLeng);
		GridBagConstraints gbc_txfKatGehIzena = new GridBagConstraints();
		gbc_txfKatGehIzena.insets = new Insets(0, 0, 5, 5);
		gbc_txfKatGehIzena.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfKatGehIzena.gridx = 0;
		gbc_txfKatGehIzena.gridy = 3;
		JTextField txfKatGehIzena = new JTextField();
		txfKatGehIzena.setColumns(10);
		panel.add(txfKatGehIzena, gbc_txfKatGehIzena);
		GridBagConstraints gbc_lblKatGehIzena = new GridBagConstraints();
		gbc_lblKatGehIzena.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblKatGehIzena.insets = new Insets(0, 0, 5, 5);
		gbc_lblKatGehIzena.gridx = 0;
		gbc_lblKatGehIzena.gridy = 2;
		JLabel lblKatGehIzena = new JLabel("Izena:");
		panel.add(lblKatGehIzena, gbc_lblKatGehIzena);
		GridBagConstraints gbc_txfKatGehISBN = new GridBagConstraints();
		gbc_txfKatGehISBN.weightx = 0.5;
		gbc_txfKatGehISBN.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfKatGehISBN.anchor = GridBagConstraints.WEST;
		gbc_txfKatGehISBN.insets = new Insets(0, 0, 5, 5);
		gbc_txfKatGehISBN.gridx = 0;
		gbc_txfKatGehISBN.gridy = 1;
		JTextField txfKatGehISBN = new JTextField();
		txfKatGehISBN.setColumns(13);
		panel.add(txfKatGehISBN, gbc_txfKatGehISBN);
		
		int aukera = JOptionPane.showConfirmDialog(this, panel, "Liburua gehitu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (aukera == 0) {
			try {
				Liburua lib = new Liburua();
				lib.isbn = Long.parseLong(txfKatGehISBN.getText());
				lib.izena = txfKatGehIzena.getText();
				lib.lengoaia = (String) cbxKatGehLeng.getSelectedItem();
				lib.argitaletxeaIFK = ((ComboItem<String>)cbxKatGehArgit.getSelectedItem()).getValue();
				lib.idazleaZnb = ((ComboItem<Integer>)cbxKatGehIdazle.getSelectedItem()).getValue();
				System.out.println(String.format("[Kontrolatzailea]: (Liburua gehitu Pop-Up) datuak bidali. ISBN:%d, Izena:%s, Lengoaia:%s, Argitaletxea:%s, Idazlea:%d", lib.isbn, lib.izena, lib.lengoaia, lib.argitaletxeaIFK, lib.idazleaZnb));
				Liburuzaina.getInstantzia().addLiburu(lib);
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(contentPane, "ISBN kode balioduna sartu", "Errorea", JOptionPane.ERROR_MESSAGE);
			} catch (NullPointerException npe) {
				JOptionPane.showMessageDialog(contentPane, "Eremu guztiak bete behar dira", "Errorea", JOptionPane.ERROR_MESSAGE);
			}
		} else System.out.println("[Kontrolatzailea]: (Liburua gehitu Pop-Up) lehioa itxi");
	}
	private void popUpKatalogoaLiburuaKendu() {
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		JPanel pnlKatEzaForm = new JPanel();
		pnlKatEzaForm.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel lblKatEzaISBN = new JLabel("ISBN:");
		pnlKatEzaForm.add(lblKatEzaISBN);
		JTextField txfKatEzaISBN = new JTextField();
		txfKatEzaISBN.setColumns(13);
		pnlKatEzaForm.add(txfKatEzaISBN);
		panel.add(pnlKatEzaForm);
		
		int aukera = JOptionPane.showConfirmDialog(this, panel, "Liburua kendu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (aukera == 0) {
			try {
				long isbn = Long.parseLong(txfKatEzaISBN.getText());
				System.out.println(String.format("[Kontrolatzailea]: (Liburua kendu Pop-Up) datuak bidali. ISBN:%d", isbn));
				Liburuzaina.getInstantzia().removeLiburu(isbn);
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(contentPane, "ISBN kode balioduna sartu", "Errorea", JOptionPane.ERROR_MESSAGE);
			}			
		} else System.out.println("[Kontrolatzailea]: (Liburua kendu Pop-Up) lehioa itxi");
	}
	private JPanel getPnlMaiHasi() {
		if (pnlMaiHasi == null) {
			pnlMaiHasi = new JPanel();
			GridBagLayout gbl_pnlMaiHasi = new GridBagLayout();
			gbl_pnlMaiHasi.columnWidths = new int[] {0, 0};
			gbl_pnlMaiHasi.rowHeights = new int[] {0, 0, 0};
			gbl_pnlMaiHasi.columnWeights = new double[]{0.0, 0.0};
			gbl_pnlMaiHasi.rowWeights = new double[]{0.0, 0.0, 0.0};
			pnlMaiHasi.setLayout(gbl_pnlMaiHasi);
			GridBagConstraints gbc_lblMaiHasISBN = new GridBagConstraints();
			gbc_lblMaiHasISBN.anchor = GridBagConstraints.WEST;
			gbc_lblMaiHasISBN.insets = new Insets(0, 0, 5, 5);
			gbc_lblMaiHasISBN.gridx = 0;
			gbc_lblMaiHasISBN.gridy = 0;
			pnlMaiHasi.add(getLblMaiHasISBN(), gbc_lblMaiHasISBN);
			GridBagConstraints gbc_txfMaiHasISBN = new GridBagConstraints();
			gbc_txfMaiHasISBN.insets = new Insets(0, 0, 5, 10);
			gbc_txfMaiHasISBN.anchor = GridBagConstraints.WEST;
			gbc_txfMaiHasISBN.gridx = 0;
			gbc_txfMaiHasISBN.gridy = 1;
			pnlMaiHasi.add(getTxfMaiHasISBN(), gbc_txfMaiHasISBN);
			GridBagConstraints gbc_lblMaiHasNAN = new GridBagConstraints();
			gbc_lblMaiHasNAN.anchor = GridBagConstraints.WEST;
			gbc_lblMaiHasNAN.insets = new Insets(0, 0, 5, 0);
			gbc_lblMaiHasNAN.gridx = 1;
			gbc_lblMaiHasNAN.gridy = 0;
			pnlMaiHasi.add(getLblMaiHasNAN(), gbc_lblMaiHasNAN);
			GridBagConstraints gbc_txfMaiHasNAN = new GridBagConstraints();
			gbc_txfMaiHasNAN.insets = new Insets(0, 0, 5, 0);
			gbc_txfMaiHasNAN.fill = GridBagConstraints.HORIZONTAL;
			gbc_txfMaiHasNAN.gridx = 1;
			gbc_txfMaiHasNAN.gridy = 1;
			pnlMaiHasi.add(getTxfMaiHasNAN(), gbc_txfMaiHasNAN);
			GridBagConstraints gbc_btnMaiHasiMailegatu = new GridBagConstraints();
			gbc_btnMaiHasiMailegatu.anchor = GridBagConstraints.WEST;
			gbc_btnMaiHasiMailegatu.insets = new Insets(0, 0, 0, 5);
			gbc_btnMaiHasiMailegatu.gridx = 0;
			gbc_btnMaiHasiMailegatu.gridy = 2;
			pnlMaiHasi.add(getBtnMaiHasiMailegatu(), gbc_btnMaiHasiMailegatu);
			pnlMaiHasi.setBorder(BorderFactory.createTitledBorder("Mailegua hasi:"));
		}
		return pnlMaiHasi;
	}
	private JPanel getPnlMaiBueltatu() {
		if (pnlMaiBueltatu == null) {
			pnlMaiBueltatu = new JPanel();
			GridBagLayout gbl_pnlMaiBueltatu = new GridBagLayout();
			gbl_pnlMaiBueltatu.columnWidths = new int[]{0, 0, 0};
			gbl_pnlMaiBueltatu.rowHeights = new int[]{0, 0, 0, 0};
			gbl_pnlMaiBueltatu.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
			gbl_pnlMaiBueltatu.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			pnlMaiBueltatu.setLayout(gbl_pnlMaiBueltatu);
			pnlMaiBueltatu.setBorder(BorderFactory.createTitledBorder("Mailegua bueltatu:"));
			GridBagConstraints gbc_lblMaiBueISBN = new GridBagConstraints();
			gbc_lblMaiBueISBN.anchor = GridBagConstraints.WEST;
			gbc_lblMaiBueISBN.insets = new Insets(0, 0, 5, 0);
			gbc_lblMaiBueISBN.gridx = 0;
			gbc_lblMaiBueISBN.gridy = 0;
			pnlMaiBueltatu.add(getLblMaiBueISBN(), gbc_lblMaiBueISBN);
			GridBagConstraints gbc_txfMaiBueISBN = new GridBagConstraints();
			gbc_txfMaiBueISBN.insets = new Insets(0, 0, 5, 0);
			gbc_txfMaiBueISBN.fill = GridBagConstraints.HORIZONTAL;
			gbc_txfMaiBueISBN.gridx = 0;
			gbc_txfMaiBueISBN.gridy = 1;
			pnlMaiBueltatu.add(getTxfMaiBueISBN(), gbc_txfMaiBueISBN);
			GridBagConstraints gbc_btnMaiBueBueltatu = new GridBagConstraints();
			gbc_btnMaiBueBueltatu.anchor = GridBagConstraints.WEST;
			gbc_btnMaiBueBueltatu.gridx = 0;
			gbc_btnMaiBueBueltatu.gridy = 2;
			pnlMaiBueltatu.add(getBtnMaiBueBueltatu(), gbc_btnMaiBueBueltatu);
		}
		return pnlMaiBueltatu;
	}
	private JLabel getLblMaiHasISBN() {
		if (lblMaiHasISBN == null) {
			lblMaiHasISBN = new JLabel("ISBN:");
		}
		return lblMaiHasISBN;
	}
	private JTextField getTxfMaiHasISBN() {
		if (txfMaiHasISBN == null) {
			txfMaiHasISBN = new JTextField();
			txfMaiHasISBN.setColumns(10);
		}
		return txfMaiHasISBN;
	}
	private JLabel getLblMaiHasNAN() {
		if (lblMaiHasNAN == null) {
			lblMaiHasNAN = new JLabel("NAN:");
		}
		return lblMaiHasNAN;
	}
	private JTextField getTxfMaiHasNAN() {
		if (txfMaiHasNAN == null) {
			txfMaiHasNAN = new JTextField();
			txfMaiHasNAN.setColumns(10);
		}
		return txfMaiHasNAN;
	}
	private JButton getBtnMaiHasiMailegatu() {
		if (btnMaiHasiMailegatu == null) {
			btnMaiHasiMailegatu = new JButton("Mailegatu");
		}
		return btnMaiHasiMailegatu;
	}
	private JLabel getLblMaiBueISBN() {
		if (lblMaiBueISBN == null) {
			lblMaiBueISBN = new JLabel("ISBN:");
		}
		return lblMaiBueISBN;
	}
	private JTextField getTxfMaiBueISBN() {
		if (txfMaiBueISBN == null) {
			txfMaiBueISBN = new JTextField();
			txfMaiBueISBN.setColumns(13);
		}
		return txfMaiBueISBN;
	}
	private JButton getBtnMaiBueBueltatu() {
		if (btnMaiBueBueltatu == null) {
			btnMaiBueBueltatu = new JButton("Bueltatu");
		}
		return btnMaiBueBueltatu;
	}
	private JPanel getPnlTabArgitaletxeak() {
		if (pnlTabArgitaletxeak == null) {
			pnlTabArgitaletxeak = new JPanel();
		}
		return pnlTabArgitaletxeak;
	}
	private JPanel getPnlKatBotoi() {
		if (pnlKatBotoi == null) {
			pnlKatBotoi = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlKatBotoi.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlKatBotoi.add(getBtnKatGehitu());
			pnlKatBotoi.add(getBtnKatKendu());
		}
		return pnlKatBotoi;
	}
	private JScrollPane getScpKatTaula() {
		if (scpKatTaula == null) {
			scpKatTaula = new JScrollPane();
			scpKatTaula.setViewportView(getTblKat());
		}
		return scpKatTaula;
	}
	private JTable getTblKat() {
		if (tblKat == null) {
			
			dtmKat = new DefaultTableModel(new Object[][] {}, 
										   new String[] {"ISBN", "Izena", "Arg. data", "Lengoaia", "Egoera", "NAN"}) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tblKat = new JTable(dtmKat);
			Liburuzaina.getInstantzia().getLiburuak();
		}
		return tblKat;
	}
	private JButton getBtnKatGehitu() {
		if (btnKatGehitu == null) {
			btnKatGehitu = new JButton("LiburuaGehitu");
			btnKatGehitu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("[Kontrolatzailea]: (Katalogoa panela) Liburua gehitzeko pop-up irekitzeko klikatuta");
					popUpKatalogoaLiburuaGehitu();
				}
			});
		}
		return btnKatGehitu;
	}
	private JButton getBtnKatKendu() {
		if (btnKatKendu == null) {
			btnKatKendu = new JButton("Liburua kendu");
			btnKatKendu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("[Kontrolatzailea]: (Katalogoa panela) Liburua kentzeko pop-up irekitzeko klikatuta");
					popUpKatalogoaLiburuaKendu();
				}
			});
		}
		return btnKatKendu;
	}
}
