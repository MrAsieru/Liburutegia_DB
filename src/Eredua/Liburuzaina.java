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
        SQLManager.getManager().getErabiltzaileak();
    }
    
    public void getErabiltzaileak(String pNan, String pIzena, String pAbizena) {
		//TODO erabiltzaileak filtroekin bilatu eta bueltatu	
	}
    
    public void getLiburuak() {
        SQLManager.getManager().getLiburuak();
    }
    
    public void getMaileguak() {
        SQLManager.getManager().getMaileguak();
    }
    
    public void getIdazleak() {
        SQLManager.getManager().getIdazleak();
    }
    
    public void getArgitaletxeak() {
       SQLManager.getManager().getArgitaletxeak();
    }

    public void IzenaAldatu(String pIzena){
        SQLManager.getManager().IzenaAldatu(this.nan, pIzena);
    }

    public void AbizenaAldatu(String pAbizena){
        SQLManager.getManager().AbizenaAldatu(this.nan, pAbizena);
    }

    public void JaiotzeDataAldatu(Date pDate){
        SQLManager.getManager().JaiotzeDataAldatu(this.nan, pDate);
    }

    public void GeneroaAldatu(String pGeneroa){
        SQLManager.getManager().GeneroaAldatu(this.nan, pGeneroa);
    }

    public void addLiburu(Liburua pLiburua){
        SQLManager.getManager().addLiburu(pLiburua);
    }

    public void removeLiburu(long ISBN){
        SQLManager.getManager().removeLiburu(ISBN);
    }

    public void addErabiltzaileArrunta(Erabiltzailea pErabiltzaile, String pPasahitza){
        SQLManager.getManager().addErabiltzaileArrunta(pErabiltzaile, pPasahitza);
    }

    public void removeErabiltzaile(String pNAN){
        SQLManager.getManager().removeErabiltzaile(pNAN);
    }
    
    public void addIdazlea(Idazlea pIdl) {
    	//TODO
    }

    public void removeAutorea(String pIdazleZenbakia){
        SQLManager.getManager().removeAutorea(pIdazleZenbakia);
    }

    public void updateAutoreIzena(String pIdazleZenbakia, String pIzena){
        SQLManager.getManager().updateAutoreIzena(pIdazleZenbakia, pIzena);
    }

    public void updateAutoreAbizena(String pIdazleZenbakia, String pAbizena){
        SQLManager.getManager().updateAutoreAbizena(pIdazleZenbakia, pAbizena);
    }

    public void updateAutoreGenero(String pIdazleZenbakia, String pGenero){
        SQLManager.getManager().updateAutoreGenero(pIdazleZenbakia, pGenero);
    }

    public void updateAutoreHerrialdea(String pIdazleZenbakia, String pHerrialdea){
        SQLManager.getManager().updateAutoreHerrialdea(pIdazleZenbakia, pHerrialdea);
    }
    
    public void addArgitaletxea(Argitaletxea arg) {
    	//TODO
    }

    public void removeArgitaletzea(String pIFK){
        SQLManager.getManager().removeArgitaletzea(pIFK);
    }

    public void updateArgitaletzeIzena(String pIFK, String pIzena){
        SQLManager.getManager().updateArgitaletzeIzena(pIFK, pIzena);
    }

    public void updateArgitaletzeHelbidea(String pIFK, String pHelbidea){
        SQLManager.getManager().updateArgitaletzeHelbidea(pIFK, pHelbidea);
    }

    public void addMailegua(String NAN, long pISBN){
        SQLManager.getManager().addMailegua(NAN, pISBN);
    }

    public void removeMailegua(long pISBN){
        SQLManager.getManager().removeMailegua(pISBN);
    }
	
	public void aldatuPasahitza(String pNan, String pPasahitza) {
		//TODO erabiltzailearen pasahitza aldatu eta bueltatu
	}
}
