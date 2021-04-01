package Eredua;

import java.util.Observable;
import java.util.Observer;

public class Login extends Observable{
	private ErabiltzaileMota erab;
	public Login(String pErabiltzailea, String pPasahitza, Observer pO) {
		this.addObserver(pO);
		System.out.println("[Eredua.Login]: Saioa hasteko eskatuta, erab: %s, pasahitza: %s".format(pErabiltzailea, pPasahitza));
		if (pErabiltzailea.equals("admin") && pPasahitza.equals("1234")) {
			erab = ErabiltzaileMota.ADMIN;
		} else if (pErabiltzailea.equals("erab") && pPasahitza.equals("1234")) {
			erab = ErabiltzaileMota.ARRUNTA;
		} else {
			erab = ErabiltzaileMota.OKERRA;
		}
		System.out.println("[Eredua.Login]: Saioaren erabiltzaile mota: %s".format(erab.name()));
		setChanged();
		notifyObservers(new Object[] {erab, pErabiltzailea});
	}	
}
