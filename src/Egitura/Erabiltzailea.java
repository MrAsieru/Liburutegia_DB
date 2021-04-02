package Egitura;

import java.util.Date;

public class Erabiltzailea {
    public String nan;
    public String izena;
    public String abizena;
    public String jaiotzeData;
    public String generoa;
    public boolean liburuzainaDa;
	
    public Erabiltzailea() {}
    
	public Erabiltzailea(String pNan, String pIzena, String pAbizena, String pJaioData, String pGeneroa, boolean pLiburuzainaDa) {
		this.nan = pNan;
		this.izena = pIzena;
		this.abizena = pAbizena;
		this.jaiotzeData = pJaioData;
		this.generoa = pGeneroa;
		this.liburuzainaDa = pLiburuzainaDa;
	}
}
