package Eredua;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class ErabiltzaileArrunta extends Observable {

    private String nan;
    private static ErabiltzaileArrunta instantzia;

    private ErabiltzaileArrunta(){
    }

    public static ErabiltzaileArrunta getInstantzia() {
        if (instantzia == null) {
            instantzia = new ErabiltzaileArrunta();
        }
        return instantzia;
    }
    
    public void erabiltzaileEzarri(String pNan) {
    	if (nan == null) {
    		nan = pNan;
    	}
    }
    
    public void getKatalogoa() {
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KAT_TAULA_EGUNERATU
    }
    
    public void getKatalogoa(String pIzena, String pDataBehe, String pDataGoi, String pLengoaia, boolean pEskuragarri) {
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KAT_TAULA_EGUNERATU
    }
    
    public void liburuaErreserbatu(long pIsbn) {
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KAT_ERRESERBA_ONDO
        // ERABARR_KAT_ERRESERBA_TXARTO
    }
    
    public void getKolekzioak() {
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KOL_KOLEKZIOAK_EGUNERATU
    }
    
    public void getKolekziokoLiburuak(String pKolekzioa) {
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KOL_LIBURUAK_EGUNERATU
    }

    public void sortuKolekzioa(String pIzena){
        SQLManager.getManager().sortuKolekzioa(this.nan, pIzena);
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KOL_KOLEKZIOA_SORTU_ONDO
        // ERABARR_KOL_KOLEKZIOA_SORTU_TXARTO
    }

    public void removeKolekzioa(String pKolekzioIzena){
        SQLManager.getManager().removeKolekzioa(this.nan, pKolekzioIzena);
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KOL_KOLEKZIOA_EZABATU_ONDO
        // ERABARR_KOL_KOLEKZIOA_EZABATU_TXARTO
    }

    public void addLiburuaKolekziora(String pKolekzioIzena, long pISBN){
        SQLManager.getManager().addLiburuakKolekziora(this.nan, pKolekzioIzena, pISBN);
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KOL_LIBURUA_GEHITU_ONDO
        // ERABARR_KOL_LIBURUA_GEHITU_TXARTO
    }

    public void removeLiburuaKolekzioan(String pKolekzioIzena, long pISBN){
        SQLManager.getManager().removeLiburuakKolekziora(this.nan, pKolekzioIzena, pISBN);
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KOL_LIBURUA_KENDU_ONDO
        // ERABARR_KOL_LIBURUA_KENDU_TXARTO
    }

    public void getErabiltzaileInformazioa(){
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KON_INFORMAZIOA_EGUNERATU
    }

    public void erabiltzaileInformazioaEguneratu(String pIzena, String pAbizena, String pPasahitza, String pGeneroa, String pJaioData){
        //TODO (null elementua ez bada aldatu)
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KON_ALDAKETA_ONDO
        // ERABARR_KON_ALDAKETA_TXARTO
    }
}
