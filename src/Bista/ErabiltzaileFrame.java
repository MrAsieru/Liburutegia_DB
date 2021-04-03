package Bista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Egitura.Erabiltzailea;
import Egitura.Liburua;
import Eredua.ErabiltzaileArrunta;
import Eredua.Liburuzaina;
import Eredua.NotifikazioMotak;

import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class ErabiltzaileFrame extends JFrame implements Observer {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel pnlKatalogoa;
	private JPanel pnlKolekzioak;
	private JScrollPane scpKat;
	private JTable tblKat;
	private DefaultTableModel dtmKat;
	private JPanel pnlKatFiltroak;
	private JButton btnKatFiltratu;
	private JButton btnKatGarbitu;
	private JPanel pnlKatPopFiltratu;
	private JSeparator separator;
	private JLabel lblKatPopArg;
	private JTextField txfKatPopArgBehe;
	private JLabel lblKatPopArgBehe;
	private JTextField txfPopKatArgGoi;
	private JLabel lblKatPopArgGoi;
	private JSeparator separator_1;
	private JLabel lblLengoaia;
	private JComboBox cbxKatPopLeng;
	private JCheckBox chbKatPopEsku;
	private JPanel pnlKatPopIzena;
	private JLabel lblKatPopIzena;
	private JTextField txfKatPopIzena;
	private JPanel pnlKatPopArg;
	private JPanel pnlKatPopLeng;
	private JSeparator separator_2;
	private JPanel pnlKolTaulak;
	private JScrollPane scpKol1;
	private JScrollPane scpKol2;
	private JTable tblKol1;
	private DefaultTableModel dtmKol1;
	private JTable tblKol2;
	private DefaultTableModel dtmKol2;
	private JButton btnKatErreserba;

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
		setTitle("Erabiltzaile saioa: "+pErabiltzailea);
		ErabiltzaileArrunta.getInstantzia().erabiltzaileEzarri(pErabiltzailea);
		ErabiltzaileArrunta.getInstantzia().addObserver(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(getTabbedPane());
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// arg -> Object[]:
		//			arg[0] -> NotifikazioMotak,
		//			arg[1,2,3,...] -> Datuak
		// Datu egiturak:
		// ERABARR_KAT_TAULA_EGUNERATU: 				Erabiltzailea[]
		//ERABARR_KAT_ERRESERBA_ONDO:					ezer
		// ERABARR_KAT_ERRESERBA_TXARTO:				String
		if (o instanceof Liburuzaina && arg instanceof Object[] && ((Object[])arg).length > 0 && ((Object[])arg)[0] instanceof NotifikazioMotak) {
			switch ((NotifikazioMotak)((Object[])arg)[0]) {
				case ERABARR_KAT_TAULA_EGUNERATU:
					if (((Object[])arg)[1] instanceof Liburua[]){
						System.out.println("[Bista.Erabiltzailea]: Katalogoa taula eguneratu da");
						katalogoaListaEguneratu((Liburua[]) ((Object[])arg)[1]);
					} else System.out.println("[Bista.Erabiltzailea]: ERABARR_KAT_TAULA_EGUNERATU ez du eskatutakoa jaso");
					break;
				case ERABARR_KAT_ERRESERBA_ONDO:
					JOptionPane.showMessageDialog(contentPane, "Erreserba ondo gorde da", "Erreserba ondo gordeta", JOptionPane.PLAIN_MESSAGE);
					break;
				case ERABARR_KAT_ERRESERBA_TXARTO:
					if (((Object[])arg)[1] instanceof Erabiltzailea) {
						Erabiltzailea erab = (Erabiltzailea) ((Object[])arg)[1];
						JOptionPane.showMessageDialog(contentPane, "Ezin izan da liburua erreserbatu:\n"+((Object[])arg)[1], "Errorea", JOptionPane.ERROR_MESSAGE);
					} else System.out.println("[Bista.Erabiltzailea]: ERABARR_KAT_ERRESERBA_TXARTO ez du eskatutakoa jaso");
					break;
				default:
					System.out.println(String.format("[Bista.Erabiltzailea]: Ez da ezagutzen notifikazio mota: %s", ((NotifikazioMotak) ((Object[])arg)[0]).name()));
					break;
			}
		}
	}

	private void katalogoaListaEguneratu(Liburua[] pLista){
		dtmKat.setRowCount(0);
		for (Liburua lib: pLista){
			dtmKat.addRow(new Object[]{lib.isbn, lib.izena, lib.argitaratzeData, lib.lengoaia, lib.argitaletxeaIzena, (!lib.mailegatuta && !lib.erreserbatua)});
		}
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
			pnlKatalogoa.setLayout(new BorderLayout(0, 0));
			pnlKatalogoa.add(getScpKat());
			pnlKatalogoa.add(getPnlKatFiltroak(), BorderLayout.NORTH);
		}
		return pnlKatalogoa;
	}
	private JPanel getPnlKolekzioak() {
		if (pnlKolekzioak == null) {
			pnlKolekzioak = new JPanel();
			pnlKolekzioak.add(getPnlKolTaulak());
		}
		return pnlKolekzioak;
	}
	private JScrollPane getScpKat() {
		if (scpKat == null) {
			scpKat = new JScrollPane();
			scpKat.setViewportView(getTblKat());
		}
		return scpKat;
	}
	private JTable getTblKat() {
		if (tblKat == null) {			
			dtmKat = new DefaultTableModel(new Object[][] {}, 
					   new String[] {"ISBN", "Izena", "Arg. data", "Lengoaia", "Argitaletxea", "Eskuragarri"}) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tblKat = new JTable(dtmKat);
			ErabiltzaileArrunta.getInstantzia().getKatalogoa();
		}
		return tblKat;
	}
	private JPanel getPnlKatFiltroak() {
		if (pnlKatFiltroak == null) {
			pnlKatFiltroak = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlKatFiltroak.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlKatFiltroak.add(getBtnKatFiltratu());
			pnlKatFiltroak.add(getBtnKatGarbitu());
			pnlKatFiltroak.add(getBtnKatErreserba());
		}
		return pnlKatFiltroak;
	}
	private JButton getBtnKatFiltratu() {
		if (btnKatFiltratu == null) {
			btnKatFiltratu = new JButton("Filtratu");
			btnKatFiltratu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("[Kontrolatzailea]: (Katalogoa panela) btnKatFiltratu klikatuta");
					popUpKatalogoaFiltratu();					
				}
			});
		}
		return btnKatFiltratu;
	}
	
	private void popUpKatalogoaFiltratu() {
		if (pnlKatPopFiltratu == null) {
			pnlKatPopFiltratu = new JPanel();
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[] {0};
			gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
			gridBagLayout.columnWeights = new double[]{1.0};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			pnlKatPopFiltratu.setLayout(gridBagLayout);
			GridBagConstraints gbc_pnlKatPopIzena = new GridBagConstraints();
			gbc_pnlKatPopIzena.fill = GridBagConstraints.VERTICAL;
			gbc_pnlKatPopIzena.anchor = GridBagConstraints.WEST;
			gbc_pnlKatPopIzena.insets = new Insets(0, 0, 5, 0);
			gbc_pnlKatPopIzena.gridx = 0;
			gbc_pnlKatPopIzena.gridy = 0;
			pnlKatPopFiltratu.add(getPnlKatPopIzena(), gbc_pnlKatPopIzena);
			GridBagConstraints gbc_separator = new GridBagConstraints();
			gbc_separator.fill = GridBagConstraints.HORIZONTAL;
			gbc_separator.insets = new Insets(0, 0, 5, 0);
			gbc_separator.gridx = 0;
			gbc_separator.gridy = 1;
			pnlKatPopFiltratu.add(getSeparator(), gbc_separator);
			GridBagConstraints gbc_pnlKatPopArg = new GridBagConstraints();
			gbc_pnlKatPopArg.fill = GridBagConstraints.VERTICAL;
			gbc_pnlKatPopArg.anchor = GridBagConstraints.WEST;
			gbc_pnlKatPopArg.insets = new Insets(0, 0, 5, 0);
			gbc_pnlKatPopArg.gridx = 0;
			gbc_pnlKatPopArg.gridy = 2;
			pnlKatPopFiltratu.add(getPnlKatPopArg(), gbc_pnlKatPopArg);
			GridBagConstraints gbc_separator_1 = new GridBagConstraints();
			gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_separator_1.insets = new Insets(0, 0, 5, 0);
			gbc_separator_1.gridx = 0;
			gbc_separator_1.gridy = 3;
			pnlKatPopFiltratu.add(getSeparator_1(), gbc_separator_1);
			GridBagConstraints gbc_pnlKatPopLeng = new GridBagConstraints();
			gbc_pnlKatPopLeng.insets = new Insets(0, 0, 5, 0);
			gbc_pnlKatPopLeng.fill = GridBagConstraints.BOTH;
			gbc_pnlKatPopLeng.gridx = 0;
			gbc_pnlKatPopLeng.gridy = 4;
			pnlKatPopFiltratu.add(getPnlKatPopLeng(), gbc_pnlKatPopLeng);
			GridBagConstraints gbc_separator_2 = new GridBagConstraints();
			gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_separator_2.insets = new Insets(0, 0, 5, 0);
			gbc_separator_2.gridx = 0;
			gbc_separator_2.gridy = 5;
			pnlKatPopFiltratu.add(getSeparator_2_1(), gbc_separator_2);
			GridBagConstraints gbc_chbKatPopEsku = new GridBagConstraints();
			gbc_chbKatPopEsku.insets = new Insets(0, 0, 5, 0);
			gbc_chbKatPopEsku.fill = GridBagConstraints.BOTH;
			gbc_chbKatPopEsku.gridx = 0;
			gbc_chbKatPopEsku.gridy = 6;
			pnlKatPopFiltratu.add(getChbKatPopEsku(), gbc_chbKatPopEsku);
		}
		int aukera = JOptionPane.showConfirmDialog(this, pnlKatPopFiltratu, "Katalogoa filtratu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (aukera == 0) {
			System.out.println(String.format("[Kontrolatzailea]: (Filtroa Pop-Up) datuak bidali. Izena:%s, DataBehe:%s, DataGoi:%s, Lengoaia:%s, Eskuragarri:%s", txfKatPopIzena.getText(), txfKatPopArgBehe.getText(), txfPopKatArgGoi.getText(), (String) cbxKatPopLeng.getSelectedItem(), chbKatPopEsku.isSelected()));
			ErabiltzaileArrunta.getInstantzia().getKatalogoa(txfKatPopIzena.getText(), txfKatPopArgBehe.getText(), txfPopKatArgGoi.getText(), (String) cbxKatPopLeng.getSelectedItem(), chbKatPopEsku.isSelected());		
		} else System.out.println("[Kontrolatzailea]: (Filtroa Pop-Up) lehioa itxi");
	}
	
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JLabel getLblKatPopArg() {
		if (lblKatPopArg == null) {
			lblKatPopArg = new JLabel("Argitaratze tartea:");
		}
		return lblKatPopArg;
	}
	private JTextField getTxfKatPopArgBehe() {
		if (txfKatPopArgBehe == null) {
			txfKatPopArgBehe = new JTextField();
			txfKatPopArgBehe.setColumns(10);
		}
		return txfKatPopArgBehe;
	}
	private JLabel getLblKatPopArgBehe() {
		if (lblKatPopArgBehe == null) {
			lblKatPopArgBehe = new JLabel("-tik");
		}
		return lblKatPopArgBehe;
	}
	private JTextField getTxfPopKatArgGoi() {
		if (txfPopKatArgGoi == null) {
			txfPopKatArgGoi = new JTextField();
			txfPopKatArgGoi.setColumns(10);
		}
		return txfPopKatArgGoi;
	}
	private JLabel getLblKatPopArgGoi() {
		if (lblKatPopArgGoi == null) {
			lblKatPopArgGoi = new JLabel("-arte");
		}
		return lblKatPopArgGoi;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	private JLabel getLblLengoaia() {
		if (lblLengoaia == null) {
			lblLengoaia = new JLabel("Lengoaia:");
		}
		return lblLengoaia;
	}
	private JComboBox getCbxKatPopLeng() {
		if (cbxKatPopLeng == null) {
			cbxKatPopLeng = new JComboBox();
		}
		return cbxKatPopLeng;
	}
	private JCheckBox getChbKatPopEsku() {
		if (chbKatPopEsku == null) {
			chbKatPopEsku = new JCheckBox("Eskuragarriak bakarrik");
		}
		return chbKatPopEsku;
	}
	private JPanel getPnlKatPopIzena() {
		if (pnlKatPopIzena == null) {
			pnlKatPopIzena = new JPanel();
			GridBagLayout gbl_pnlKatPopIzena = new GridBagLayout();
			gbl_pnlKatPopIzena.columnWidths = new int[] {100};
			gbl_pnlKatPopIzena.rowHeights = new int[] {0, 0};
			gbl_pnlKatPopIzena.columnWeights = new double[]{1.0};
			gbl_pnlKatPopIzena.rowWeights = new double[]{0.0, 0.0};
			pnlKatPopIzena.setLayout(gbl_pnlKatPopIzena);
			GridBagConstraints gbc_lblKatPopIzena = new GridBagConstraints();
			gbc_lblKatPopIzena.anchor = GridBagConstraints.WEST;
			gbc_lblKatPopIzena.insets = new Insets(0, 0, 5, 0);
			gbc_lblKatPopIzena.gridx = 0;
			gbc_lblKatPopIzena.gridy = 0;
			pnlKatPopIzena.add(getLblKatPopIzena(), gbc_lblKatPopIzena);
			GridBagConstraints gbc_txfKatPopIzena = new GridBagConstraints();
			gbc_txfKatPopIzena.fill = GridBagConstraints.BOTH;
			gbc_txfKatPopIzena.gridx = 0;
			gbc_txfKatPopIzena.gridy = 1;
			pnlKatPopIzena.add(getTxfKatPopIzena(), gbc_txfKatPopIzena);
		}
		return pnlKatPopIzena;
	}
	private JLabel getLblKatPopIzena() {
		if (lblKatPopIzena == null) {
			lblKatPopIzena = new JLabel("Izena:");
		}
		return lblKatPopIzena;
	}
	private JTextField getTxfKatPopIzena() {
		if (txfKatPopIzena == null) {
			txfKatPopIzena = new JTextField();
			txfKatPopIzena.setColumns(10);
		}
		return txfKatPopIzena;
	}
	private JPanel getPnlKatPopArg() {
		if (pnlKatPopArg == null) {
			pnlKatPopArg = new JPanel();
			GridBagLayout gbl_pnlKatPopArg = new GridBagLayout();
			gbl_pnlKatPopArg.columnWidths = new int[] {0, 0, 100, 0};
			gbl_pnlKatPopArg.rowHeights = new int[] {0, 0};
			gbl_pnlKatPopArg.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
			gbl_pnlKatPopArg.rowWeights = new double[]{0.0, 0.0};
			pnlKatPopArg.setLayout(gbl_pnlKatPopArg);
			GridBagConstraints gbc_lblKatPopArg = new GridBagConstraints();
			gbc_lblKatPopArg.anchor = GridBagConstraints.WEST;
			gbc_lblKatPopArg.insets = new Insets(0, 0, 5, 5);
			gbc_lblKatPopArg.gridx = 0;
			gbc_lblKatPopArg.gridy = 0;
			pnlKatPopArg.add(getLblKatPopArg(), gbc_lblKatPopArg);
			GridBagConstraints gbc_txfKatPopArgBehe = new GridBagConstraints();
			gbc_txfKatPopArgBehe.fill = GridBagConstraints.HORIZONTAL;
			gbc_txfKatPopArgBehe.anchor = GridBagConstraints.NORTH;
			gbc_txfKatPopArgBehe.insets = new Insets(0, 0, 5, 5);
			gbc_txfKatPopArgBehe.gridx = 0;
			gbc_txfKatPopArgBehe.gridy = 1;
			pnlKatPopArg.add(getTxfKatPopArgBehe(), gbc_txfKatPopArgBehe);
			GridBagConstraints gbc_lblKatPopArgBehe = new GridBagConstraints();
			gbc_lblKatPopArgBehe.anchor = GridBagConstraints.WEST;
			gbc_lblKatPopArgBehe.insets = new Insets(0, 0, 5, 5);
			gbc_lblKatPopArgBehe.gridx = 1;
			gbc_lblKatPopArgBehe.gridy = 1;
			pnlKatPopArg.add(getLblKatPopArgBehe(), gbc_lblKatPopArgBehe);
			GridBagConstraints gbc_txfPopKatArgGoi = new GridBagConstraints();
			gbc_txfPopKatArgGoi.fill = GridBagConstraints.HORIZONTAL;
			gbc_txfPopKatArgGoi.anchor = GridBagConstraints.NORTH;
			gbc_txfPopKatArgGoi.insets = new Insets(0, 0, 5, 5);
			gbc_txfPopKatArgGoi.gridx = 2;
			gbc_txfPopKatArgGoi.gridy = 1;
			pnlKatPopArg.add(getTxfPopKatArgGoi(), gbc_txfPopKatArgGoi);
			GridBagConstraints gbc_lblKatPopArgGoi = new GridBagConstraints();
			gbc_lblKatPopArgGoi.insets = new Insets(0, 0, 5, 0);
			gbc_lblKatPopArgGoi.anchor = GridBagConstraints.NORTH;
			gbc_lblKatPopArgGoi.gridx = 3;
			gbc_lblKatPopArgGoi.gridy = 1;
			pnlKatPopArg.add(getLblKatPopArgGoi(), gbc_lblKatPopArgGoi);
		}
		return pnlKatPopArg;
	}
	private JPanel getPnlKatPopLeng() {
		if (pnlKatPopLeng == null) {
			pnlKatPopLeng = new JPanel();
			GridBagLayout gbl_pnlKatPopLeng = new GridBagLayout();
			gbl_pnlKatPopLeng.columnWidths = new int[]{171, 71, 32, 0};
			gbl_pnlKatPopLeng.rowHeights = new int[]{24, 0, 0};
			gbl_pnlKatPopLeng.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_pnlKatPopLeng.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			pnlKatPopLeng.setLayout(gbl_pnlKatPopLeng);
			GridBagConstraints gbc_lblLengoaia = new GridBagConstraints();
			gbc_lblLengoaia.anchor = GridBagConstraints.WEST;
			gbc_lblLengoaia.insets = new Insets(0, 0, 0, 5);
			gbc_lblLengoaia.gridx = 0;
			gbc_lblLengoaia.gridy = 0;
			pnlKatPopLeng.add(getLblLengoaia(), gbc_lblLengoaia);
			GridBagConstraints gbc_cbxKatPopLeng = new GridBagConstraints();
			gbc_cbxKatPopLeng.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxKatPopLeng.anchor = GridBagConstraints.NORTH;
			gbc_cbxKatPopLeng.gridx = 0;
			gbc_cbxKatPopLeng.gridy = 1;
			pnlKatPopLeng.add(getCbxKatPopLeng(), gbc_cbxKatPopLeng);
		}
		return pnlKatPopLeng;
	}
	private JSeparator getSeparator_2_1() {
		if (separator_2 == null) {
			separator_2 = new JSeparator();
		}
		return separator_2;
	}
	
	private JButton getBtnKatGarbitu() {
		if (btnKatGarbitu == null) {
			btnKatGarbitu = new JButton("Garbitu");
			btnKatGarbitu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("[Kontrolatzailea]: (Katalogoa panela) btnKatGarbitu klikatuta");
					ErabiltzaileArrunta.getInstantzia().getKatalogoa();			
				}
			});
		}
		return btnKatGarbitu;
	}
	private JButton getBtnKatErreserba() {
		if (btnKatErreserba == null) {
			btnKatErreserba = new JButton("Erreserbatu");
			btnKatErreserba.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (tblKat.getSelectedRow() >= 0) {
						int aukera = JOptionPane.showConfirmDialog(contentPane, String.format("ISBN: %d duen %s liburua erreserbatu nahi duzu?", (long) tblKat.getValueAt(tblKat.getSelectedRow(), 0), (String) tblKat.getValueAt(tblKat.getSelectedRow(), 1)), "Idazlea ezabatu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
						if (aukera == 0) {
							System.out.println(String.format("[Kontrolatzailea]: (Liburua erreserbatu Pop-Up) erreserbatzeko klikatu. ISBN:%d, Izena:%s", (long) tblKat.getValueAt(tblKat.getSelectedRow(), 0), (String) tblKat.getValueAt(tblKat.getSelectedRow(), 1)));
							ErabiltzaileArrunta.getInstantzia().liburuaErreserbatu((long) tblKat.getValueAt(tblKat.getSelectedRow(), 0));
						} else System.out.println("[Kontrolatzailea]: (Liburua erreserbatu Pop-Up) ateratzeko klikatu");
					} else {
						JOptionPane.showMessageDialog(contentPane, "Erreserbatzeko liburu bat aukeratu.", "Errorea", JOptionPane.ERROR_MESSAGE);
					}				
				}
			});
		}
		return btnKatErreserba;
	}
	private JPanel getPnlKolTaulak() {
		if (pnlKolTaulak == null) {
			pnlKolTaulak = new JPanel();
			pnlKolTaulak.add(getScpKol1());
			pnlKolTaulak.add(getScpKol2());
		}
		return pnlKolTaulak;
	}
	private JScrollPane getScpKol1() {
		if (scpKol1 == null) {
			scpKol1 = new JScrollPane();
			scpKol1.setViewportView(getTblKol1());
		}
		return scpKol1;
	}
	private JScrollPane getScpKol2() {
		if (scpKol2 == null) {
			scpKol2 = new JScrollPane();
			scpKol2.setViewportView(getTblKol2());
		}
		return scpKol2;
	}
	private JTable getTblKol1() {
		if (tblKol1 == null) {		
			dtmKol1 = new DefaultTableModel(new Object[][] {}, 
					   new String[] {}) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tblKol1 = new JTable(dtmKol1);
			ErabiltzaileArrunta.getInstantzia().getKolekzioak();
		}
		return tblKol1;
	}
	private JTable getTblKol2() {
		if (tblKol2 == null) {
			dtmKol2 = new DefaultTableModel(new Object[][] {}, 
					   new String[] {"ISBN", "Izena", "Eskuragarri"}) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tblKol2 = new JTable(dtmKol2);
			ErabiltzaileArrunta.getInstantzia().getKatalogoa();
		}
		return tblKol2;
	}
}
