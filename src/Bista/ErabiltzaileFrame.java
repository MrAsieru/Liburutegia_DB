package Bista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.util.stream.Collectors;
import javax.swing.JPasswordField;
import javax.swing.text.JTextComponent;

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
	private DefaultTableModel dtmKol1;
	private DefaultTableModel dtmKol2;
	private JButton btnKatErreserba;
	private JPanel pnlKontua;
	private JPanel pnlKolKolekzioakBotoi;
	private JPanel pnlKolLiburuakBotoi;
	private JButton btnKolKolekzioaSortu;
	private JButton btnKolKolekzioaEzabatu;
	private JButton btnKolLiburuaGehitu;
	private JButton btnKolLiburuaKendu;
	private JScrollPane scpKolKolekzioak;
	private JScrollPane scpKolLiburuak;
	private JTable tblKolKolekzioak;
	private DefaultTableModel dtmKolKolekzioak;
	private JTable tblKolLiburuak;
	private DefaultTableModel dtmKolLiburuak;
	private JPanel pnlKonInfo;
	private JPanel pnlKonBotoi;
	private JLabel lblKonNan;
	private JTextField txfKonNan;
	private JLabel lblKonPasahitza;
	private JPasswordField psfKonPasahitza;
	private JLabel lblKonIzena;
	private JTextField txfKonIzena;
	private JLabel lblKonAbizena;
	private JTextField txfKonAbizena;
	private JLabel lblKonGeneroa;
	private JTextField txfKonGeneroa;
	private JLabel lblKonJaiotzeData;
	private JTextField txfKonJaioData;
	private JButton btnKolEguneratu;

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
		setIconImage(new ImageIcon("res/icon.png").getImage());
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
		// ERABARR_KAT_TAULA_EGUNERATU: 				Liburua[]: isbn, izena, argData, lengoaia, argitaletxeaIzena, mailegatuta, erreserbatuta
		// ERABARR_KAT_ERRESERBA_ONDO:					ezer
		// ERABARR_KAT_ERRESERBA_TXARTO:				String
		// ERABARR_KOL_KOLEKZIOAK_EGUNERATU:			String[]: izenak, int[]: liburu kantitatea
		// ERABARR_KOL_KOLEKZIOA_SORTU_ONDO:			ezer
		// ERABARR_KOL_KOLEKZIOA_SORTU_TXARTO:			String
		// ERABARR_KOL_KOLEKZIOA_EZABATU_ONDO:			ezer
		// ERABARR_KOL_KOLEKZIOA_EZABATU_TXARTO:		String
		// ERABARR_KOL_LIBURUAK_EGUNERATU:				Liburua[]: isbn, izena, erreserbatuta, mailegatuta
		// ERABARR_KOL_LIBURUA_GEHITU_ONDO:				ezer
		// ERABARR_KOL_LIBURUA_GEHITU_TXARTO:			String
		// ERABARR_KOL_LIBURUA_KENDU_ONDO:				ezer
		// ERABARR_KOL_LIBURUA_KENDU_TXARTO:			String
		// ERABARR_KON_INFORMAZIOA_EGUNERATU:			String[] non nan, izena, abizena, generoa, jaiotze data
		// ERABARR_KON_ALDAKETA_ONDO:					ezer
		// ERABARR_KON_ALDAKETA_TXARTO:					String

		if (o instanceof ErabiltzaileArrunta && arg instanceof Object[] && ((Object[])arg).length > 0 && ((Object[])arg)[0] instanceof NotifikazioMotak) {
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
					if (((Object[])arg)[1] instanceof String) {
						JOptionPane.showMessageDialog(contentPane, "Ezin izan da liburua erreserbatu:\n"+((Object[])arg)[1], "Errorea", JOptionPane.ERROR_MESSAGE);
					} else System.out.println("[Bista.Erabiltzailea]: ERABARR_KAT_ERRESERBA_TXARTO ez du eskatutakoa jaso");
					break;
				case ERABARR_KOL_KOLEKZIOAK_EGUNERATU:
					if (((Object[])arg)[1] instanceof String[] && ((Object[])arg)[2] instanceof int[]){
						System.out.println("[Bista.Erabiltzailea]: Kolekzio lista eguneratu da");
						kolekzioaKolekzioListaEguneratu((String[]) ((Object[])arg)[1], (int[]) ((Object[])arg)[2]);
					} else System.out.println("[Bista.Erabiltzailea]: ERABARR_KOL_KOLEKZIOAK_EGUNERATU ez du eskatutakoa jaso");
					break;
				case ERABARR_KOL_KOLEKZIOA_SORTU_ONDO:
					JOptionPane.showMessageDialog(contentPane, "Kolekzioa ondo sortu da", "Kolekzioa ondo sortuta", JOptionPane.PLAIN_MESSAGE);
					break;
				case ERABARR_KOL_KOLEKZIOA_SORTU_TXARTO:
					if (((Object[])arg)[1] instanceof String) {
						JOptionPane.showMessageDialog(contentPane, "Ezin izan da kolekzioa gorde:\n"+((Object[])arg)[1], "Errorea", JOptionPane.ERROR_MESSAGE);
					} else System.out.println("[Bista.Erabiltzailea]: ERABARR_KOL_KOLEKZIOA_SORTU_TXARTO ez du eskatutakoa jaso");
					break;
				case ERABARR_KOL_KOLEKZIOA_EZABATU_ONDO:
					JOptionPane.showMessageDialog(contentPane, "Kolekzioa ondo ezabatu da", "Kolekzioa ondo ezabatuta", JOptionPane.PLAIN_MESSAGE);
					break;
				case ERABARR_KOL_KOLEKZIOA_EZABATU_TXARTO:
					if (((Object[])arg)[1] instanceof String) {
						JOptionPane.showMessageDialog(contentPane, "Ezin izan da kolekzioa ezabatu:\n"+((Object[])arg)[1], "Errorea", JOptionPane.ERROR_MESSAGE);
					} else System.out.println("[Bista.Erabiltzailea]: ERABARR_KOL_KOLEKZIOA_EZABATU_TXARTO ez du eskatutakoa jaso");
					break;
				case ERABARR_KOL_LIBURUAK_EGUNERATU:
					if (((Object[])arg)[1] instanceof Liburua[]){
						System.out.println("[Bista.Erabiltzailea]: Kolekzioaren liburu lista eguneratu da");
						kolekzioaLiburuListaEguneratu((Liburua[]) ((Object[])arg)[1]);
					} else System.out.println("[Bista.Erabiltzailea]: ERABARR_KOL_KOLEKZIOAK_EGUNERATU ez du eskatutakoa jaso");
					break;
				case ERABARR_KOL_LIBURUA_GEHITU_ONDO:
					JOptionPane.showMessageDialog(contentPane, "Liburua ondo gehitu da", "Liburua ondo gehituta", JOptionPane.PLAIN_MESSAGE);
					break;
				case ERABARR_KOL_LIBURUA_GEHITU_TXARTO:
					if (((Object[])arg)[1] instanceof String) {
						JOptionPane.showMessageDialog(contentPane, "Ezin izan da liburua gehitu:\n"+((Object[])arg)[1], "Errorea", JOptionPane.ERROR_MESSAGE);
					} else System.out.println("[Bista.Erabiltzailea]: ERABARR_KOL_LIBURUA_GEHITU_TXARTO ez du eskatutakoa jaso");
					break;
				case ERABARR_KOL_LIBURUA_KENDU_ONDO:
					JOptionPane.showMessageDialog(contentPane, "Liburua ondo kendu da", "Liburua ondo kenduta", JOptionPane.PLAIN_MESSAGE);
					break;
				case ERABARR_KOL_LIBURUA_KENDU_TXARTO:
					if (((Object[])arg)[1] instanceof String) {
						JOptionPane.showMessageDialog(contentPane, "Ezin izan da liburua kendu:\n"+((Object[])arg)[1], "Errorea", JOptionPane.ERROR_MESSAGE);
					} else System.out.println("[Bista.Erabiltzailea]: ERABARR_KOL_LIBURUA_KENDU_TXARTO ez du eskatutakoa jaso");
					break;
				case ERABARR_KON_INFORMAZIOA_EGUNERATU:
					if (((Object[])arg)[1] instanceof String[]){
						System.out.println("[Bista.Erabiltzailea]: Kontua informazioa eguneratu da");
						kontuaInformazioaKargatu((String[]) ((Object[])arg)[1]);
					} else System.out.println("[Bista.Erabiltzailea]: ERABARR_KON_INFORMAZIOA_EGUNERATU ez du eskatutakoa jaso");
					break;
				case ERABARR_KON_ALDAKETA_ONDO:
					JOptionPane.showMessageDialog(contentPane, "Datuak ondo eguneratu egin dira.", "Ondo eguneratuta", JOptionPane.PLAIN_MESSAGE);
					break;
				case ERABARR_KON_ALDAKETA_TXARTO:
					if (((Object[])arg)[1] instanceof String) {
						JOptionPane.showMessageDialog(contentPane, "Ezin izan dira datuak eguneratu:\n"+((Object[])arg)[1], "Errorea", JOptionPane.ERROR_MESSAGE);
					} else System.out.println("[Bista.Erabiltzailea]: ERABARR_KON_ALDAKETA_TXARTO ez du eskatutakoa jaso");
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

	private void kolekzioaKolekzioListaEguneratu(String[] pListaIze, int[] pListaKant) {
		dtmKol1.setRowCount(0);
		if (pListaIze.length == pListaKant.length) {
			for (int i = 0; i < pListaIze.length; i++) {
				dtmKol1.addRow(new Object[] {pListaIze[i], pListaKant[i]});
			}
		}
	}

	private void kolekzioaLiburuListaEguneratu(Liburua[] pLista) {
		dtmKol2.setRowCount(0);
		for (Liburua lib:pLista) {
			dtmKol1.addRow(new Object[] {lib.isbn, lib.izena, (!lib.erreserbatua && !lib.mailegatuta)});
		}
	}

	private void kontuaInformazioaKargatu(String[] pLista) {
		txfKonNan.setText(pLista[0]);
		HintTextField[] eremuak =  new HintTextField[] {(HintTextField) txfKonIzena, (HintTextField)txfKonAbizena, (HintTextField)txfKonGeneroa, (HintTextField)txfKonJaioData};
		for (int i = 0; i < eremuak.length; i++) {
			eremuak[i].setHint(pLista[i+1]);
		}
	}

	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addTab("Katalogoa", null, getPnlKatalogoa(), null);
			tabbedPane.addTab("Kolekzioak", null, getPnlKolekzioak(), null);
			tabbedPane.addTab("Nire kontua", null, getPnlKontua(), null);
			tabbedPane.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					if (tabbedPane.getSelectedIndex() == 2) {
						System.out.println("[Kontrolatzailea]: Nire kontua panela irekita, informazioa eskatu da.");
						ErabiltzaileArrunta.getInstantzia().getErabiltzaileInformazioa();
					}
				}
			});
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
			pnlKolekzioak.setLayout(new BorderLayout(0, 0));
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
			GridBagLayout gbl_pnlKolTaulak = new GridBagLayout();
			gbl_pnlKolTaulak.columnWidths = new int[] {0, 0};
			gbl_pnlKolTaulak.rowHeights = new int[] {0, 0};
			gbl_pnlKolTaulak.columnWeights = new double[]{0.0, 0.0};
			gbl_pnlKolTaulak.rowWeights = new double[]{0.0, 1.0};
			pnlKolTaulak.setLayout(gbl_pnlKolTaulak);
			GridBagConstraints gbc_pnlKolKolekzioakBotoi = new GridBagConstraints();
			gbc_pnlKolKolekzioakBotoi.anchor = GridBagConstraints.WEST;
			gbc_pnlKolKolekzioakBotoi.insets = new Insets(0, 0, 5, 5);
			gbc_pnlKolKolekzioakBotoi.gridx = 0;
			gbc_pnlKolKolekzioakBotoi.gridy = 0;
			pnlKolTaulak.add(getPnlKolKolekzioakBotoi(), gbc_pnlKolKolekzioakBotoi);
			GridBagConstraints gbc_pnlKolLiburuakBotoi = new GridBagConstraints();
			gbc_pnlKolLiburuakBotoi.anchor = GridBagConstraints.WEST;
			gbc_pnlKolLiburuakBotoi.insets = new Insets(0, 0, 5, 0);
			gbc_pnlKolLiburuakBotoi.gridx = 1;
			gbc_pnlKolLiburuakBotoi.gridy = 0;
			pnlKolTaulak.add(getPnlKolLiburuakBotoi(), gbc_pnlKolLiburuakBotoi);
			GridBagConstraints gbc_scpKolKolekzioak = new GridBagConstraints();
			gbc_scpKolKolekzioak.weightx = 0.33;
			gbc_scpKolKolekzioak.insets = new Insets(0, 0, 5, 5);
			gbc_scpKolKolekzioak.fill = GridBagConstraints.BOTH;
			gbc_scpKolKolekzioak.gridx = 0;
			gbc_scpKolKolekzioak.gridy = 1;
			pnlKolTaulak.add(getScpKolKolekzioak(), gbc_scpKolKolekzioak);
			GridBagConstraints gbc_scpKolLiburuak = new GridBagConstraints();
			gbc_scpKolLiburuak.weightx = 0.66;
			gbc_scpKolLiburuak.anchor = GridBagConstraints.WEST;
			gbc_scpKolLiburuak.insets = new Insets(0, 0, 5, 0);
			gbc_scpKolLiburuak.fill = GridBagConstraints.BOTH;
			gbc_scpKolLiburuak.gridx = 1;
			gbc_scpKolLiburuak.gridy = 1;
			pnlKolTaulak.add(getScpKolLiburuak(), gbc_scpKolLiburuak);
		}
		return pnlKolTaulak;
	}
	private JPanel getPnlKontua() {
		if (pnlKontua == null) {
			pnlKontua = new JPanel();
			pnlKontua.setLayout(new BorderLayout(0, 0));
			pnlKontua.add(getPnlKonInfo(), BorderLayout.CENTER);
			pnlKontua.add(getPnlKonBotoi(), BorderLayout.SOUTH);
		}
		return pnlKontua;
	}
	private JPanel getPnlKolKolekzioakBotoi() {
		if (pnlKolKolekzioakBotoi == null) {
			pnlKolKolekzioakBotoi = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlKolKolekzioakBotoi.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlKolKolekzioakBotoi.add(getBtnKolKolekzioaSortu());
			pnlKolKolekzioakBotoi.add(getBtnKolKolekzioaEzabatu());
		}
		return pnlKolKolekzioakBotoi;
	}
	private JPanel getPnlKolLiburuakBotoi() {
		if (pnlKolLiburuakBotoi == null) {
			pnlKolLiburuakBotoi = new JPanel();
			pnlKolLiburuakBotoi.add(getBtnKolLiburuaGehitu());
			pnlKolLiburuakBotoi.add(getBtnKolLiburuaKendu());
		}
		return pnlKolLiburuakBotoi;
	}
	private JButton getBtnKolKolekzioaSortu() {
		if (btnKolKolekzioaSortu == null) {
			btnKolKolekzioaSortu = new JButton("Sortu");
			btnKolKolekzioaSortu.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("[Kontrolatzailea]: (Kolekzioak panela) btnKolKolekzioaSortu klikatuta");
					String izena = JOptionPane.showInputDialog(contentPane, "Kolekzio berriaren izena sartu:", "Kolekzio berria", JOptionPane.PLAIN_MESSAGE);
					if (izena != null) {
						System.out.println(String.format("[Kontrolatzailea]: (Kolekzioa gehitu Pop-Up) kolekzioa sortzeko klikatu. Izena:%s", izena));
						ErabiltzaileArrunta.getInstantzia().sortuKolekzioa(izena);
					} else System.out.println("[Kontrolatzailea]: (Kolekzioa gehitu Pop-Up) ateratzeko klikatu");
				}
			});
		}
		return btnKolKolekzioaSortu;
	}
	private JButton getBtnKolKolekzioaEzabatu() {
		if (btnKolKolekzioaEzabatu == null) {
			btnKolKolekzioaEzabatu = new JButton("Ezabatu");
			btnKolKolekzioaEzabatu.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("[Kontrolatzailea]: (Kolekzioak panela) btnKolKolekzioaEzabatu klikatuta");
					if (tblKolKolekzioak.getSelectedRow() >= 0) {
						int aukera = JOptionPane.showConfirmDialog(contentPane, String.format("%s kolekzioa ezabatu nahi duzu?", (String) tblKolKolekzioak.getValueAt(tblKolKolekzioak.getSelectedRow(), 0)), "Kolekzioa ezabatu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
						if (aukera == 0) {
							System.out.println(String.format("[Kontrolatzailea]: (Kolekzioa ezabatu Pop-Up) kolekzioa kentzeko klikatu. Izena:%s", (String) tblKolKolekzioak.getValueAt(tblKolKolekzioak.getSelectedRow(), 0)));
							ErabiltzaileArrunta.getInstantzia().removeKolekzioa((String) tblKolKolekzioak.getValueAt(tblKolKolekzioak.getSelectedRow(), 0));
						} else System.out.println("[Kontrolatzailea]: (Kolekzioa ezabatu Pop-Up) ateratzeko klikatu");
					} else {
						JOptionPane.showMessageDialog(contentPane, "Kolekzio bat ezabatu ahal izateko lehenengo kolekzio bat aukeratu.", "Errorea", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return btnKolKolekzioaEzabatu;
	}
	private JButton getBtnKolLiburuaGehitu() {
		if (btnKolLiburuaGehitu == null) {
			btnKolLiburuaGehitu = new JButton("Gehitu");
			btnKolLiburuaGehitu.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("[Kontrolatzailea]: (Kolekzioak panela) btnKolLiburuaGehitu klikatuta");
					String isbn = JOptionPane.showInputDialog(contentPane, "Liburuaren ISBN-a sartu:", "Liburua gehitu", JOptionPane.PLAIN_MESSAGE);
					if (isbn != null) {
						try {
							long isbn_l = Long.parseLong(isbn);
							System.out.println(String.format("[Kontrolatzailea]: (Liburua gehitu Pop-Up) liburua gehitzeko klikatu. ISBN:%d", isbn_l));
							ErabiltzaileArrunta.getInstantzia().addLiburuaKolekziora((String) tblKolKolekzioak.getValueAt(tblKolKolekzioak.getSelectedRow(), 0), isbn_l);
						} catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(contentPane, "ISBN kode balioduna sartu", "Errorea", JOptionPane.ERROR_MESSAGE);
						} catch (ArrayIndexOutOfBoundsException aioobe) {
							JOptionPane.showMessageDialog(contentPane, "Lehenengoz kolekzio bat aukeratu", "Errorea", JOptionPane.ERROR_MESSAGE);
						}

					} else System.out.println("[Kontrolatzailea]: (Liburua gehitu Pop-Up) ateratzeko klikatu");
				}
			});
		}
		return btnKolLiburuaGehitu;
	}
	private JButton getBtnKolLiburuaKendu() {
		if (btnKolLiburuaKendu == null) {
			btnKolLiburuaKendu = new JButton("Kendu");
			btnKolLiburuaKendu.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("[Kontrolatzailea]: (Kolekzioak panela) btnKolLiburuaKendu klikatuta");
					if (tblKolKolekzioak.getSelectedRow() >= 0 && tblKolLiburuak.getSelectedRow() >= 0) {
						int aukera = JOptionPane.showConfirmDialog(contentPane, String.format("%s liburua %s kolekziotik ezabatu nahi duzu?",(String) tblKolLiburuak.getValueAt(tblKolLiburuak.getSelectedRow(), 1) ,(String) tblKolKolekzioak.getValueAt(tblKolKolekzioak.getSelectedRow(), 0)), "Liburua ezabatu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
						if (aukera == 0) {
							System.out.println(String.format("[Kontrolatzailea]: (Liburua ezabatu Pop-Up) liburua kentzeko klikatu. ISBN:%S, Izena:%s", (long) tblKolLiburuak.getValueAt(tblKolLiburuak.getSelectedRow(), 0), (String) tblKolLiburuak.getValueAt(tblKolLiburuak.getSelectedRow(), 1)));
							ErabiltzaileArrunta.getInstantzia().removeLiburuaKolekzioan((String) tblKolKolekzioak.getValueAt(tblKolKolekzioak.getSelectedRow(), 0), (long) tblKolLiburuak.getValueAt(tblKolLiburuak.getSelectedRow(), 0));
						} else System.out.println("[Kontrolatzailea]: (Liburua ezabatu Pop-Up) ateratzeko klikatu");
					} else {
						JOptionPane.showMessageDialog(contentPane, "Liburu bat ezabatu ahal izateko lehenengo kolekzio eta liburu bat aukeratu.", "Errorea", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return btnKolLiburuaKendu;
	}
	private JScrollPane getScpKolKolekzioak() {
		if (scpKolKolekzioak == null) {
			scpKolKolekzioak = new JScrollPane();
			scpKolKolekzioak.setViewportView(getTblKolKolekzioak());
		}
		return scpKolKolekzioak;
	}
	private JScrollPane getScpKolLiburuak() {
		if (scpKolLiburuak == null) {
			scpKolLiburuak = new JScrollPane();
			scpKolLiburuak.setViewportView(getTblKolLiburuak());
		}
		return scpKolLiburuak;
	}
	private JTable getTblKolKolekzioak() {
		if (tblKolKolekzioak == null) {
			dtmKolKolekzioak = new DefaultTableModel(new Object[][] {},
					new String[] {"Izena", "Kantitatea"}) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tblKolKolekzioak = new JTable(dtmKolKolekzioak);
			tblKolKolekzioak.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					ErabiltzaileArrunta.getInstantzia().getKolekziokoLiburuak((String) tblKolKolekzioak.getValueAt(tblKolKolekzioak.getSelectedRow(), 0));
				}
			});
			ErabiltzaileArrunta.getInstantzia().getKolekzioak();
		}
		return tblKolKolekzioak;
	}
	private JTable getTblKolLiburuak() {
		if (tblKolLiburuak == null) {
			dtmKolLiburuak = new DefaultTableModel(new Object[][] {},
					new String[] {"ISBN", "Izena", "Eskuragarri"}) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tblKolLiburuak = new JTable(dtmKolLiburuak);
		}
		return tblKolLiburuak;
	}
	private JPanel getPnlKonInfo() {
		if (pnlKonInfo == null) {
			pnlKonInfo = new JPanel();
			GridBagLayout gbl_pnlKonInfo = new GridBagLayout();
			gbl_pnlKonInfo.columnWidths = new int[] {0, 0};
			gbl_pnlKonInfo.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
			gbl_pnlKonInfo.columnWeights = new double[]{0.0, 0.0};
			gbl_pnlKonInfo.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			pnlKonInfo.setLayout(gbl_pnlKonInfo);
			GridBagConstraints gbc_lblKonNan = new GridBagConstraints();
			gbc_lblKonNan.anchor = GridBagConstraints.WEST;
			gbc_lblKonNan.insets = new Insets(0, 0, 5, 5);
			gbc_lblKonNan.gridx = 0;
			gbc_lblKonNan.gridy = 0;
			pnlKonInfo.add(getLblKonNan(), gbc_lblKonNan);
			GridBagConstraints gbc_lblKonPasahitza = new GridBagConstraints();
			gbc_lblKonPasahitza.anchor = GridBagConstraints.WEST;
			gbc_lblKonPasahitza.insets = new Insets(0, 0, 5, 0);
			gbc_lblKonPasahitza.gridx = 1;
			gbc_lblKonPasahitza.gridy = 0;
			pnlKonInfo.add(getLblKonPasahitza(), gbc_lblKonPasahitza);
			GridBagConstraints gbc_txfKonNan = new GridBagConstraints();
			gbc_txfKonNan.insets = new Insets(0, 0, 5, 5);
			gbc_txfKonNan.anchor = GridBagConstraints.WEST;
			gbc_txfKonNan.gridx = 0;
			gbc_txfKonNan.gridy = 1;
			pnlKonInfo.add(getTxfKonNan(), gbc_txfKonNan);
			GridBagConstraints gbc_psfKonPasahitza = new GridBagConstraints();
			gbc_psfKonPasahitza.insets = new Insets(0, 0, 5, 0);
			gbc_psfKonPasahitza.fill = GridBagConstraints.HORIZONTAL;
			gbc_psfKonPasahitza.gridx = 1;
			gbc_psfKonPasahitza.gridy = 1;
			pnlKonInfo.add(getPsfKonPasahitza(), gbc_psfKonPasahitza);
			GridBagConstraints gbc_lblKonIzena = new GridBagConstraints();
			gbc_lblKonIzena.anchor = GridBagConstraints.WEST;
			gbc_lblKonIzena.insets = new Insets(0, 0, 5, 5);
			gbc_lblKonIzena.gridx = 0;
			gbc_lblKonIzena.gridy = 2;
			pnlKonInfo.add(getLblKonIzena(), gbc_lblKonIzena);
			GridBagConstraints gbc_lblKonAbizena = new GridBagConstraints();
			gbc_lblKonAbizena.anchor = GridBagConstraints.WEST;
			gbc_lblKonAbizena.insets = new Insets(0, 0, 5, 0);
			gbc_lblKonAbizena.gridx = 1;
			gbc_lblKonAbizena.gridy = 2;
			pnlKonInfo.add(getLblKonAbizena(), gbc_lblKonAbizena);
			GridBagConstraints gbc_txfKonIzena = new GridBagConstraints();
			gbc_txfKonIzena.insets = new Insets(0, 0, 5, 5);
			gbc_txfKonIzena.fill = GridBagConstraints.HORIZONTAL;
			gbc_txfKonIzena.gridx = 0;
			gbc_txfKonIzena.gridy = 3;
			pnlKonInfo.add(getTxfKonIzena(), gbc_txfKonIzena);
			GridBagConstraints gbc_txfKonAbizena = new GridBagConstraints();
			gbc_txfKonAbizena.insets = new Insets(0, 0, 5, 0);
			gbc_txfKonAbizena.fill = GridBagConstraints.HORIZONTAL;
			gbc_txfKonAbizena.gridx = 1;
			gbc_txfKonAbizena.gridy = 3;
			pnlKonInfo.add(getTxfKonAbizena(), gbc_txfKonAbizena);
			GridBagConstraints gbc_lblKonGeneroa = new GridBagConstraints();
			gbc_lblKonGeneroa.anchor = GridBagConstraints.WEST;
			gbc_lblKonGeneroa.insets = new Insets(0, 0, 5, 5);
			gbc_lblKonGeneroa.gridx = 0;
			gbc_lblKonGeneroa.gridy = 4;
			pnlKonInfo.add(getLblKonGeneroa(), gbc_lblKonGeneroa);
			GridBagConstraints gbc_lblKonJaiotzeData = new GridBagConstraints();
			gbc_lblKonJaiotzeData.anchor = GridBagConstraints.WEST;
			gbc_lblKonJaiotzeData.insets = new Insets(0, 0, 5, 0);
			gbc_lblKonJaiotzeData.gridx = 1;
			gbc_lblKonJaiotzeData.gridy = 4;
			pnlKonInfo.add(getLblKonJaiotzeData(), gbc_lblKonJaiotzeData);
			GridBagConstraints gbc_txfKonGeneroa = new GridBagConstraints();
			gbc_txfKonGeneroa.insets = new Insets(0, 0, 0, 5);
			gbc_txfKonGeneroa.fill = GridBagConstraints.HORIZONTAL;
			gbc_txfKonGeneroa.gridx = 0;
			gbc_txfKonGeneroa.gridy = 5;
			pnlKonInfo.add(getTxfKonGeneroa(), gbc_txfKonGeneroa);
			GridBagConstraints gbc_txfKonJaioData = new GridBagConstraints();
			gbc_txfKonJaioData.fill = GridBagConstraints.HORIZONTAL;
			gbc_txfKonJaioData.gridx = 1;
			gbc_txfKonJaioData.gridy = 5;
			pnlKonInfo.add(getTxfKonJaioData(), gbc_txfKonJaioData);
		}
		return pnlKonInfo;
	}
	private JPanel getPnlKonBotoi() {
		if (pnlKonBotoi == null) {
			pnlKonBotoi = new JPanel();
			pnlKonBotoi.add(getBtnKolEguneratu());
		}
		return pnlKonBotoi;
	}
	private JLabel getLblKonNan() {
		if (lblKonNan == null) {
			lblKonNan = new JLabel("NAN:");
		}
		return lblKonNan;
	}
	private JTextField getTxfKonNan() {
		if (txfKonNan == null) {
			txfKonNan = new JTextField();
			txfKonNan.setEditable(false);
			txfKonNan.setColumns(10);
			txfKonNan.setName("NAN");
		}
		return txfKonNan;
	}
	private JLabel getLblKonPasahitza() {
		if (lblKonPasahitza == null) {
			lblKonPasahitza = new JLabel("Pasahitza:");
		}
		return lblKonPasahitza;
	}
	private JPasswordField getPsfKonPasahitza() {
		if (psfKonPasahitza == null) {
			psfKonPasahitza = new JPasswordField();
		}
		return psfKonPasahitza;
	}
	private JLabel getLblKonIzena() {
		if (lblKonIzena == null) {
			lblKonIzena = new JLabel("Izena:");
		}
		return lblKonIzena;
	}
	private JTextField getTxfKonIzena() {
		if (txfKonIzena == null) {
			txfKonIzena = new HintTextField("");
			txfKonIzena.setColumns(10);
			txfKonIzena.setName("Izena");
		}
		return txfKonIzena;
	}
	private JLabel getLblKonAbizena() {
		if (lblKonAbizena == null) {
			lblKonAbizena = new JLabel("Abizena:");
		}
		return lblKonAbizena;
	}
	private JTextField getTxfKonAbizena() {
		if (txfKonAbizena == null) {
			txfKonAbizena = new HintTextField("");
			txfKonAbizena.setColumns(10);
			txfKonAbizena.setName("Abizena");
		}
		return txfKonAbizena;
	}
	private JLabel getLblKonGeneroa() {
		if (lblKonGeneroa == null) {
			lblKonGeneroa = new JLabel("Generoa:");
		}
		return lblKonGeneroa;
	}
	private JTextField getTxfKonGeneroa() {
		if (txfKonGeneroa == null) {
			txfKonGeneroa = new HintTextField("");
			txfKonGeneroa.setColumns(10);
			txfKonGeneroa.setName("Generoa");
		}
		return txfKonGeneroa;
	}
	private JLabel getLblKonJaiotzeData() {
		if (lblKonJaiotzeData == null) {
			lblKonJaiotzeData = new JLabel("Jaiotze data:");
		}
		return lblKonJaiotzeData;
	}
	private JTextField getTxfKonJaioData() {
		if (txfKonJaioData == null) {
			txfKonJaioData = new HintTextField("");
			txfKonJaioData.setColumns(10);
			txfKonJaioData.setName("Jaiotze data");
		}
		return txfKonJaioData;
	}
	private JButton getBtnKolEguneratu() {
		if (btnKolEguneratu == null) {
			btnKolEguneratu = new JButton("Datuak eguneratu");
			btnKolEguneratu.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("[Kontrolatzailea]: (Kontua panela) btnKolEguneratu klikatuta");
					JTextComponent[] elementuak = new JTextComponent[] {psfKonPasahitza, txfKonIzena, txfKonAbizena, txfKonGeneroa, txfKonJaioData};
					List<JTextComponent> aldaketak = Arrays.stream(elementuak).filter(p -> (p.getText() != null && !p.getText().equals(""))).collect(Collectors.toList());
					if (aldaketak.size() > 0) {
						String mezua = "Egingo diren aldaketak:\n";
						for (JTextComponent com: aldaketak){
							if (!(com instanceof JPasswordField)) mezua += com.getName()+": "+com.getText()+"\n";
							else mezua += "Pasahitza\n";
						}
						int aukera = JOptionPane.showConfirmDialog(contentPane, mezua, "Datuak eguneratu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
						if (aukera == 0) {
							String pasahitza = (new String(psfKonPasahitza.getPassword()).length() > 0) ? new String(psfKonPasahitza.getPassword()) : null;
							String izena = (txfKonIzena.getText().length() > 0) ? txfKonIzena.getText() :null;
							String abizena = (txfKonAbizena.getText().length() > 0) ? txfKonAbizena.getText() :null;
							String generoa = (txfKonGeneroa.getText().length() > 0) ? txfKonGeneroa.getText() :null;
							String jaiotzeData = (txfKonJaioData.getText().length() > 0) ? txfKonJaioData.getText() :null;
							System.out.println(String.format("[Kontrolatzailea]: (Datuak eguneratu Pop-Up) datuak eguneratzeko klikatu. Izena:%s, Abizena:%s, Pasahitza:%s, Generoa:%s, JaiotzeData:%s", izena, abizena, pasahitza, generoa, jaiotzeData));
							ErabiltzaileArrunta.getInstantzia().erabiltzaileInformazioaEguneratu(izena, abizena, pasahitza, generoa, jaiotzeData);
						} else System.out.println("[Kontrolatzailea]: Datuak eguneratu Pop-Up) ateratzeko klikatu");
					} else {
						JOptionPane.showMessageDialog(contentPane, "Ez dira aldaketak egin", "Errorea", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return btnKolEguneratu;
	}
}