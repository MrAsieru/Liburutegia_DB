package Eredua;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import Egitura.*;

public class Liburuzaina extends Observable{

    private String nan;
    private static Liburuzaina instantzia;

    private Liburuzaina(){
    }
    
    public static Liburuzaina getInstantzia() {
    	if (instantzia == null) {
    		instantzia = new Liburuzaina();
    	}
    	return instantzia;
    }
    
    public void erabiltzaileEzarri(String pNan) {
    	if (nan == null) {
    		nan = pNan;
    	}
    }
    
    public void getErabiltzaileak() {
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_LISTA_EGUNERATU, SQLManager.getManager().getErabiltzaileak());
        
        
        
    }
    
    public void getErabiltzaileak(String pNan, String pIzena, String pAbizena) {
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_LISTA_EGUNERATU, SQLManager.getManager().getErabiltzaileak(pNan, pIzena, pAbizena));
		
        
        
	}

    public void addErabiltzaileArrunta(Erabiltzailea pErabiltzaile, String pPasahitza){
        SQLManager.getManager().addErabiltzaileArrunta(pErabiltzaile, pPasahitza);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_LISTA_EGUNERATU, SQLManager.getManager().getErabiltzaileak());
        
        
        
        
    }

    public void aldatuPasahitza(String pNan, String pPasahitza) {
        SQLManager.getManager().aldatuPasahitza(pNan, pPasahitza);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_PASAHITZA_TXARTO, "Pasahitzaren logika ez dago sortuta.");
        
        
        
        
    }

    public void removeErabiltzailea(String pNAN){
        SQLManager.getManager().removeErabiltzaile(pNAN);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_LISTA_EGUNERATU, SQLManager.getManager().getErabiltzaileak());
        
        
        
        
    }
    
    public void getLiburuak() {
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_KAT_TAULA_EGUNERATU, SQLManager.getManager().getLiburuak());
        
        
    }

    public void getLengoaiak() {
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_KAT_GEH_LENGOAIA_LISTA_EGUNERATU, SQLManager.getManager().getLengoaiak());
        
        
        
    }

    public void addLiburu(Liburua pLiburua){
        SQLManager.getManager().addLiburu(pLiburua);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_KAT_TAULA_EGUNERATU, SQLManager.getManager().getLiburuak());
        
        
        
        
    }

    public void removeLiburu(long ISBN){
        SQLManager.getManager().removeLiburu(ISBN);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_KAT_TAULA_EGUNERATU, SQLManager.getManager().getLiburuak());
        
        
        
        
    }
    
    public void getMaileguak() {
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_MAI_TAULA_EGUNERATU, SQLManager.getManager().getMaileguak());
        
        
    }

    public void addMailegua(String NAN, long pISBN){
        SQLManager.getManager().addMailegua(NAN, pISBN);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_MAI_TAULA_EGUNERATU, SQLManager.getManager().getMaileguak());
        
        
        
        
        
    }

    public void removeMailegua(long pISBN){
        SQLManager.getManager().removeMailegua(pISBN);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_MAI_TAULA_EGUNERATU, SQLManager.getManager().getMaileguak());
        
        
        
        
    }
    
    public void getIdazleak() {
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_IDL_TAULA_EGUNERATU, SQLManager.getManager().getIdazleak());
        
        
        
    }

    public void addIdazlea(Idazlea pIdl) {
        SQLManager.getManager().addAutorea(pIdl);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_IDL_TAULA_EGUNERATU, SQLManager.getManager().getIdazleak());
        
        
        
        
    }

    public void removeIdazlea(String pIdazleZenbakia){
        SQLManager.getManager().removeAutorea(pIdazleZenbakia);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_IDL_TAULA_EGUNERATU, SQLManager.getManager().getIdazleak());
        
        
        
        
    }

    public void getArgitaletxeak() {
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ARG_TAULA_EGUNERATU, SQLManager.getManager().getArgitaletxeak());
        
        
        
    }
    
    public void addArgitaletxea(Argitaletxea arg) {
        SQLManager.getManager().addArgitaletxea(arg);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ARG_TAULA_EGUNERATU, SQLManager.getManager().getArgitaletxeak());
        
        
        
        
    }

    public void removeArgitaletzea(String pIFK){
        SQLManager.getManager().removeArgitaletzea(pIFK);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ARG_TAULA_EGUNERATU, SQLManager.getManager().getArgitaletxeak());
        
        
        
        
    }

    private void bistaNotifikatu(NotifikazioMotak pMota, Object ... pArg){
        Object[] argumentuak = new Object[pArg.length + 1];
        argumentuak[0] = pMota;
        for (int i = 0; i < pArg.length; i++){
            argumentuak[i+1] = pArg[i];
        }
        setChanged();
        notifyObservers(argumentuak);
    }
}
