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
        bistaNotifikatu(NotifikazioMotak.ERABARR_KAT_TAULA_EGUNERATU, SQLManager.getManager().getKatalogoa());
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KAT_TAULA_EGUNERATU
    }
    
    public void getKatalogoa(String pIzena, String pDataBehe, String pDataGoi, String pLengoaia, boolean pEskuragarri) {
        bistaNotifikatu(NotifikazioMotak.ERABARR_KAT_TAULA_EGUNERATU, SQLManager.getManager().getKatalogoa(pIzena, pDataBehe, pDataGoi, pLengoaia, pEskuragarri));
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KAT_TAULA_EGUNERATU
    }
    
    public void liburuaErreserbatu(long pIsbn) {
        SQLManager.getManager().liburuaErreserbatu(this.nan, pIsbn);
        bistaNotifikatu(NotifikazioMotak.ERABARR_KAT_TAULA_EGUNERATU, SQLManager.getManager().getKatalogoa());
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KAT_ERRESERBA_ONDO
        // ERABARR_KAT_ERRESERBA_TXARTO
    }
    
    public void getKolekzioak() {
        bistaNotifikatu(NotifikazioMotak.ERABARR_KAT_TAULA_EGUNERATU, SQLManager.getManager().getKolekzioak(this.nan));
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KOL_KOLEKZIOAK_EGUNERATU
    }

    public void getKolekziokoLiburuak(String pKolekzioa) {
        bistaNotifikatu(NotifikazioMotak.ERABARR_KAT_TAULA_EGUNERATU, SQLManager.getManager().getKolekziokoLiburuak(this.nan, pKolekzioa));
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KOL_LIBURUAK_EGUNERATU
    }

    public void sortuKolekzioa(String pIzena){
        SQLManager.getManager().sortuKolekzioa(this.nan, pIzena);
        bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_KOLEKZIOAK_EGUNERATU, SQLManager.getManager().getKolekzioak(this.nan));
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KOL_KOLEKZIOA_SORTU_ONDO
        // ERABARR_KOL_KOLEKZIOA_SORTU_TXARTO
    }

    public void removeKolekzioa(String pKolekzioIzena){
        SQLManager.getManager().removeKolekzioa(this.nan, pKolekzioIzena);
        bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_KOLEKZIOAK_EGUNERATU, SQLManager.getManager().getKolekzioak(this.nan));
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KOL_KOLEKZIOA_EZABATU_ONDO
        // ERABARR_KOL_KOLEKZIOA_EZABATU_TXARTO
    }

    public void addLiburuaKolekziora(String pKolekzioIzena, long pISBN){
        SQLManager.getManager().addLiburuakKolekziora(this.nan, pKolekzioIzena, pISBN);
        bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_LIBURUAK_EGUNERATU, SQLManager.getManager().getKolekzioak(this.nan));
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KOL_LIBURUA_GEHITU_ONDO
        // ERABARR_KOL_LIBURUA_GEHITU_TXARTO
    }

    public void removeLiburuaKolekzioan(String pKolekzioIzena, long pISBN){
        SQLManager.getManager().removeLiburuakKolekziora(this.nan, pKolekzioIzena, pISBN);
        bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_LIBURUAK_EGUNERATU, SQLManager.getManager().getKolekzioak(this.nan));
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KOL_LIBURUA_KENDU_ONDO
        // ERABARR_KOL_LIBURUA_KENDU_TXARTO
    }

    public void getErabiltzaileInformazioa(){
        bistaNotifikatu(NotifikazioMotak.ERABARR_KON_INFORMAZIOA_EGUNERATU, SQLManager.getManager().getErabiltzaileInformazioa(this.nan));
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KON_INFORMAZIOA_EGUNERATU
    }

    public void erabiltzaileInformazioaEguneratu(String pIzena, String pAbizena, String pPasahitza, String pGeneroa, String pJaioData){
        SQLManager.getManager().erabiltzaileInformazioaEguneratu(this.nan, pIzena, pAbizena, pPasahitza, pGeneroa, pJaioData);
        bistaNotifikatu(NotifikazioMotak.ERABARR_KON_INFORMAZIOA_EGUNERATU, SQLManager.getManager().getErabiltzaileInformazioa(this.nan));
        //TODO (null elementua ez bada aldatu)
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KON_ALDAKETA_ONDO
        // ERABARR_KON_ALDAKETA_TXARTO
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
