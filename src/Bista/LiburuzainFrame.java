package Bista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.JComboBox;

public class LiburuzainFrame extends JFrame implements Observer {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel pnlTabMaileguak;
	private JPanel pnlTabAutore;
	private JPanel pnlTabKatalogoa;
	private JPanel pnlTabErabiltzaile;
	private JPanel pnlErabBotoi;
	private JButton btnErabSortu;
	private JButton btnErabPasahitza;
	private JScrollPane scpErabTaula;
	private JTable tblErab;
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
	private JPanel pnlKatalogoaGehitu;
	private JPanel pnlKatalogoaEzabatu;
	private JLabel lblKatGehISBN;
	private JLabel lblKatEzaISBN;
	private JTextField txfKatGehISBN;
	private JLabel lblKatGehIzena;
	private JTextField txfKatGehIzena;
	private JLabel lblKatGehLeng;
	private JComboBox cbxKatGehLeng;
	private JLabel lblKatGehArgit;
	private JLabel lblKatGehIdazle;
	private JButton btnKatGehSortu;
	private JPanel panel;
	private JTextField txfKatEzaISBN;
	private JButton btnKatEzaEzabatu;
	private JComboBox cbxKatGehArgit;
	private JComboBox cbxKatGehIdazle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LiburuzainFrame frame = new LiburuzainFrame("main");
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
	public LiburuzainFrame(String pErabiltzailea) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		// LIBURUZAIN_KAT_GEH_LENGOAIA_LISTA_EGUNERATU: 	String[]
		// LIBURUZAIN_KAT_GEH_ARGITALETXE_LISTA_EGUNERATU: 	String[]
		// LIBURUZAIN_KAT_GEH_IDAZLE_LISTA_EGUNERATU: 		String[]
		// LIBURUZAIN_KAT_GEH_ONDO_SORTUTA: 				ezer
		// LIBURUZAIN_KAT_GEH_TXARTO_SORTUTA: 				String
		// LIBURUZAIN_KAT_EZA_ONDO_EZABATUTA: 				ezer
		// LIBURUZAIN_KAT_EZA_TXARTO_EZABATUTA:				String
		//TODO: Klasea jarri
		if (o instanceof Object && arg instanceof Object[] && ((Object[])arg).length > 0 && ((Object[])arg)[0] instanceof NotifikazioMotak) {
			switch ((NotifikazioMotak)((Object[])arg)[0]) {
			case LIBURUZAIN_ERAB_LISTA_EGUNERATU:
				
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
	
	//GUI elementuak
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addTab("Erabiltzaileak", null, getPnlTabErabiltzaile(), null);
			tabbedPane.addTab("Katalogoa", null, getPnlTabKatalogoa(), null);
			tabbedPane.addTab("Maileguak", null, getPnlTabMaileguak(), null);
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
			GridBagLayout gbl_pnlTabKatalogoa = new GridBagLayout();
			gbl_pnlTabKatalogoa.columnWidths = new int[] {0};
			gbl_pnlTabKatalogoa.rowHeights = new int[] {10, 0};
			gbl_pnlTabKatalogoa.columnWeights = new double[]{1.0};
			gbl_pnlTabKatalogoa.rowWeights = new double[]{1.0, 1.0};
			pnlTabKatalogoa.setLayout(gbl_pnlTabKatalogoa);
			GridBagConstraints gbc_pnlKatalogoaGehitu = new GridBagConstraints();
			gbc_pnlKatalogoaGehitu.fill = GridBagConstraints.HORIZONTAL;
			gbc_pnlKatalogoaGehitu.anchor = GridBagConstraints.NORTH;
			gbc_pnlKatalogoaGehitu.insets = new Insets(0, 0, 0, 5);
			gbc_pnlKatalogoaGehitu.gridx = 0;
			gbc_pnlKatalogoaGehitu.gridy = 0;
			pnlTabKatalogoa.add(getPnlKatalogoaGehitu(), gbc_pnlKatalogoaGehitu);
			GridBagConstraints gbc_pnlKatalogoaEzabatu = new GridBagConstraints();
			gbc_pnlKatalogoaEzabatu.fill = GridBagConstraints.HORIZONTAL;
			gbc_pnlKatalogoaEzabatu.anchor = GridBagConstraints.NORTH;
			gbc_pnlKatalogoaEzabatu.gridx = 0;
			gbc_pnlKatalogoaEzabatu.gridy = 1;
			pnlTabKatalogoa.add(getPnlKatalogoaEzabatu(), gbc_pnlKatalogoaEzabatu);
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
					//TODO pop-up bat ireki erabiltzaile bat sortzeko					
				}
			});
		}
		return btnErabSortu;
	}
	private JButton getBtnErabPasahitza() {
		if (btnErabPasahitza == null) {
			btnErabPasahitza = new JButton("Pasahitza eguneratu");
			btnErabPasahitza.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//TODO erabiltzaile aukeraketa logika					
				}
			});
		}
		return btnErabPasahitza;
	}
	private JScrollPane getScpErabTaula() {
		if (scpErabTaula == null) {
			scpErabTaula = new JScrollPane();
			scpErabTaula.setViewportView(getTable_1());
		}
		return scpErabTaula;
	}
	private JTable getTable_1() {
		if (tblErab == null) {
			tblErab = new JTable(new Object[][] {}, 
					new String[] {"NAN", "Izena","Abizena","JaiotzeD","Generoa"});
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
					//TODO nan edo izen-abizen bilaketa gauzatu					
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
					// TODO bilaketa garbitu					
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
	private JPanel getPnlKatalogoaGehitu() {
		if (pnlKatalogoaGehitu == null) {
			pnlKatalogoaGehitu = new JPanel();
			GridBagLayout gbl_pnlKatalogoaGehitu = new GridBagLayout();
			gbl_pnlKatalogoaGehitu.columnWidths = new int[]{180, 70, 0};
			gbl_pnlKatalogoaGehitu.rowHeights = new int[]{15, 0, 0, 0, 0, 0, 0};
			gbl_pnlKatalogoaGehitu.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_pnlKatalogoaGehitu.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			pnlKatalogoaGehitu.setLayout(gbl_pnlKatalogoaGehitu);
			pnlKatalogoaGehitu.setBorder(BorderFactory.createTitledBorder("Liburu bat gehitu:"));
			GridBagConstraints gbc_lblKatGehISBN = new GridBagConstraints();
			gbc_lblKatGehISBN.insets = new Insets(0, 0, 5, 5);
			gbc_lblKatGehISBN.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblKatGehISBN.gridx = 0;
			gbc_lblKatGehISBN.gridy = 0;
			pnlKatalogoaGehitu.add(getLblKatGehISBN(), gbc_lblKatGehISBN);
			GridBagConstraints gbc_lblKatGehArgit = new GridBagConstraints();
			gbc_lblKatGehArgit.anchor = GridBagConstraints.WEST;
			gbc_lblKatGehArgit.insets = new Insets(0, 0, 5, 0);
			gbc_lblKatGehArgit.gridx = 1;
			gbc_lblKatGehArgit.gridy = 0;
			pnlKatalogoaGehitu.add(getLblKatGehArgit(), gbc_lblKatGehArgit);
			GridBagConstraints gbc_lblKatGehIdazle = new GridBagConstraints();
			gbc_lblKatGehIdazle.anchor = GridBagConstraints.WEST;
			gbc_lblKatGehIdazle.insets = new Insets(0, 0, 5, 0);
			gbc_lblKatGehIdazle.gridx = 1;
			gbc_lblKatGehIdazle.gridy = 2;
			pnlKatalogoaGehitu.add(getLblKatGehIdazle(), gbc_lblKatGehIdazle);
			GridBagConstraints gbc_cbxKatGehArgit = new GridBagConstraints();
			gbc_cbxKatGehArgit.insets = new Insets(0, 0, 5, 0);
			gbc_cbxKatGehArgit.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxKatGehArgit.gridx = 1;
			gbc_cbxKatGehArgit.gridy = 1;
			pnlKatalogoaGehitu.add(getCbxKatGehArgit(), gbc_cbxKatGehArgit);
			GridBagConstraints gbc_cbxKatGehIdazle = new GridBagConstraints();
			gbc_cbxKatGehIdazle.insets = new Insets(0, 0, 5, 0);
			gbc_cbxKatGehIdazle.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxKatGehIdazle.gridx = 1;
			gbc_cbxKatGehIdazle.gridy = 3;
			pnlKatalogoaGehitu.add(getCbxKatGehIdazle(), gbc_cbxKatGehIdazle);
			GridBagConstraints gbc_btnKatGehSortu = new GridBagConstraints();
			gbc_btnKatGehSortu.gridx = 1;
			gbc_btnKatGehSortu.gridy = 5;
			pnlKatalogoaGehitu.add(getBtnKatGehSortu(), gbc_btnKatGehSortu);
			GridBagConstraints gbc_cbxKatGehLeng = new GridBagConstraints();
			gbc_cbxKatGehLeng.insets = new Insets(0, 0, 0, 5);
			gbc_cbxKatGehLeng.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxKatGehLeng.gridx = 0;
			gbc_cbxKatGehLeng.gridy = 5;
			pnlKatalogoaGehitu.add(getCbxKatGehLeng(), gbc_cbxKatGehLeng);
			GridBagConstraints gbc_lblKatGehLeng = new GridBagConstraints();
			gbc_lblKatGehLeng.anchor = GridBagConstraints.WEST;
			gbc_lblKatGehLeng.insets = new Insets(0, 0, 5, 5);
			gbc_lblKatGehLeng.gridx = 0;
			gbc_lblKatGehLeng.gridy = 4;
			pnlKatalogoaGehitu.add(getLblKatGehLeng(), gbc_lblKatGehLeng);
			GridBagConstraints gbc_txfKatGehIzena = new GridBagConstraints();
			gbc_txfKatGehIzena.insets = new Insets(0, 0, 5, 5);
			gbc_txfKatGehIzena.fill = GridBagConstraints.HORIZONTAL;
			gbc_txfKatGehIzena.gridx = 0;
			gbc_txfKatGehIzena.gridy = 3;
			pnlKatalogoaGehitu.add(getTxfKatGehIzena(), gbc_txfKatGehIzena);
			GridBagConstraints gbc_lblKatGehIzena = new GridBagConstraints();
			gbc_lblKatGehIzena.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblKatGehIzena.insets = new Insets(0, 0, 5, 5);
			gbc_lblKatGehIzena.gridx = 0;
			gbc_lblKatGehIzena.gridy = 2;
			pnlKatalogoaGehitu.add(getLblKatGehIzena(), gbc_lblKatGehIzena);
			GridBagConstraints gbc_txfKatGehISBN = new GridBagConstraints();
			gbc_txfKatGehISBN.fill = GridBagConstraints.HORIZONTAL;
			gbc_txfKatGehISBN.anchor = GridBagConstraints.WEST;
			gbc_txfKatGehISBN.insets = new Insets(0, 0, 5, 5);
			gbc_txfKatGehISBN.gridx = 0;
			gbc_txfKatGehISBN.gridy = 1;
			pnlKatalogoaGehitu.add(getTxfKatGehISBN(), gbc_txfKatGehISBN);
		}
		return pnlKatalogoaGehitu;
	}
	private JPanel getPnlKatalogoaEzabatu() {
		if (pnlKatalogoaEzabatu == null) {
			pnlKatalogoaEzabatu = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlKatalogoaEzabatu.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlKatalogoaEzabatu.setBorder(BorderFactory.createTitledBorder("Liburu bat ezabatu:"));
			pnlKatalogoaEzabatu.add(getPanel());
		}
		return pnlKatalogoaEzabatu;
	}
	private JLabel getLblKatGehISBN() {
		if (lblKatGehISBN == null) {
			lblKatGehISBN = new JLabel("ISBN:");
		}
		return lblKatGehISBN;
	}
	private JTextField getTxfKatGehISBN() {
		if (txfKatGehISBN == null) {
			txfKatGehISBN = new JTextField();
			txfKatGehISBN.setColumns(13);
		}
		return txfKatGehISBN;
	}
	private JLabel getLblKatGehIzena() {
		if (lblKatGehIzena == null) {
			lblKatGehIzena = new JLabel("Izena:");
		}
		return lblKatGehIzena;
	}
	private JTextField getTxfKatGehIzena() {
		if (txfKatGehIzena == null) {
			txfKatGehIzena = new JTextField();
			txfKatGehIzena.setColumns(10);
		}
		return txfKatGehIzena;
	}
	private JLabel getLblKatGehLeng() {
		if (lblKatGehLeng == null) {
			lblKatGehLeng = new JLabel("Lengoaia:");
		}
		return lblKatGehLeng;
	}
	private JComboBox getCbxKatGehLeng() {
		if (cbxKatGehLeng == null) {
			cbxKatGehLeng = new JComboBox();
		}
		return cbxKatGehLeng;
	}
	private JLabel getLblKatGehArgit() {
		if (lblKatGehArgit == null) {
			lblKatGehArgit = new JLabel("Argitaletxea:");
		}
		return lblKatGehArgit;
	}
	private JLabel getLblKatGehIdazle() {
		if (lblKatGehIdazle == null) {
			lblKatGehIdazle = new JLabel("Idazlea:");
		}
		return lblKatGehIdazle;
	}
	private JButton getBtnKatGehSortu() {
		if (btnKatGehSortu == null) {
			btnKatGehSortu = new JButton("Sortu");
		}
		return btnKatGehSortu;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel.add(getLblKatEzaISBN());
			panel.add(getTxfKatEzaISBN());
			panel.add(getBtnKatEzaEzabatu());
		}
		return panel;
	}
	private JLabel getLblKatEzaISBN() {
		if (lblKatEzaISBN == null) {
			lblKatEzaISBN = new JLabel("ISBN:");
		}
		return lblKatEzaISBN;
	}
	private JTextField getTxfKatEzaISBN() {
		if (txfKatEzaISBN == null) {
			txfKatEzaISBN = new JTextField();
			txfKatEzaISBN.setColumns(13);
		}
		return txfKatEzaISBN;
	}
	private JButton getBtnKatEzaEzabatu() {
		if (btnKatEzaEzabatu == null) {
			btnKatEzaEzabatu = new JButton("Ezabatu");
		}
		return btnKatEzaEzabatu;
	}
	private JComboBox getCbxKatGehArgit() {
		if (cbxKatGehArgit == null) {
			cbxKatGehArgit = new JComboBox();
		}
		return cbxKatGehArgit;
	}
	private JComboBox getCbxKatGehIdazle() {
		if (cbxKatGehIdazle == null) {
			cbxKatGehIdazle = new JComboBox();
		}
		return cbxKatGehIdazle;
	}
}
