package Eredua;

import java.util.Observable;
import java.util.Observer;

public class Login extends Observable{
	private ErabiltzaileMota erab;

	public Login(String pErabiltzailea, String pPasahitza, Observer pO) {
		this.addObserver(pO);
		System.out.println("[Eredua.Login]: Saioa hasteko eskatuta, erab: %s, pasahitza: %s".formatted(pErabiltzailea, pPasahitza));

		erab = SQLManager.getManager().checkLogin(pErabiltzailea, pPasahitza);
		System.out.println("[Eredua.Login]: Saioaren erabiltzaile mota: %s".formatted(erab.name()));

		setChanged();
		notifyObservers(new Object[] {erab, pErabiltzailea});
	}	
}
