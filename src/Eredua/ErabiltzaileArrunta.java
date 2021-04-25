package Eredua;

import java.sql.SQLException;
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
        //TODO eman beharrezkoa: isbn, izena, argData, lengoaia, argitaletxeaIzena, mailegatuta, erreserbatuta
        bistaNotifikatu(NotifikazioMotak.ERABARR_KAT_TAULA_EGUNERATU, SQLManager.getManager().getKatalogoa());
    }

    public void getKatalogoa(String pIzena, String pDataBehe, String pDataGoi, String pLengoaia, boolean pEskuragarri) {
        //TODO eman beharrezkoa: isbn, izena, argData, lengoaia, argitaletxeaIzena, mailegatuta, erreserbatuta
        bistaNotifikatu(NotifikazioMotak.ERABARR_KAT_TAULA_EGUNERATU, SQLManager.getManager().getKatalogoa(pIzena, pDataBehe, pDataGoi, pLengoaia, pEskuragarri));
    }

    public void liburuaErreserbatu(long pIsbn) {
        try{
            SQLManager.getManager().liburuaErreserbatu(this.nan, pIsbn);
            bistaNotifikatu(NotifikazioMotak.ERABARR_KAT_ERRESERBA_ONDO, SQLManager.getManager().getKatalogoa());
        }
        catch (SQLException e){
            bistaNotifikatu(NotifikazioMotak.ERABARR_KAT_ERRESERBA_TXARTO, e.getMessage());
        }
    }

    public void getKolekzioak() {
        //TODO eman beharrezkoa: String[]: izenak, int[]: liburu kantitateak
        bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_KOLEKZIOAK_EGUNERATU, SQLManager.getManager().getKolekzioak(this.nan));
    }

    public void getKolekzioak(int pBehe, int pGoi){ // TODO balioa -1 bada tratatu
        //TODO eman beharrezkoa: String[]: izenak, int[]: liburu kantitateak
    }

    public void getKolekziokoLiburuak(String pKolekzioa) {
        //TODO eman beharrezkoa: isbn, izena, erreserbatuta, mailegatuta
        bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_LIBURUAK_EGUNERATU, SQLManager.getManager().getKolekziokoLiburuak(this.nan, pKolekzioa));
    }

    public void sortuKolekzioa(String pIzena){
        try{
            SQLManager.getManager().sortuKolekzioa(this.nan, pIzena);
            //TODO eman beharrezkoa: String[]: izenak, int[]: liburu kantitateak
            bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_KOLEKZIOAK_EGUNERATU, SQLManager.getManager().getKolekzioak(this.nan));
            bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_KOLEKZIOA_SORTU_ONDO);
        }
        catch (SQLException e){
            bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_KOLEKZIOA_SORTU_TXARTO, e.getMessage());
        }
    }

    public void removeKolekzioa(String pKolekzioIzena){
        try{
            SQLManager.getManager().removeKolekzioa(this.nan, pKolekzioIzena);
            //TODO eman beharrezkoa: String[]: izenak, int[]: liburu kantitateak
            bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_KOLEKZIOAK_EGUNERATU, SQLManager.getManager().getKolekzioak(this.nan));
            bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_KOLEKZIOA_EZABATU_ONDO);
        }
        catch (SQLException e){
            bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_KOLEKZIOA_EZABATU_TXARTO, e.getMessage());
        }
    }

    public void addLiburuaKolekziora(String pKolekzioIzena, long pISBN){
        try{
            SQLManager.getManager().addLiburuakKolekziora(this.nan, pKolekzioIzena, pISBN);
            //TODO eman beharrezkoa: isbn, izena, erreserbatuta, mailegatuta
            bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_LIBURUAK_EGUNERATU, SQLManager.getManager().getKolekziokoLiburuak(this.nan, pKolekzioIzena));
            bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_LIBURUA_GEHITU_ONDO);
        }
        catch (SQLException e){
            bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_LIBURUA_GEHITU_TXARTO, e.getMessage());
        }
    }

    public void removeLiburuaKolekzioan(String pKolekzioIzena, long pISBN){
        try{
            SQLManager.getManager().removeLiburuakKolekziora(this.nan, pKolekzioIzena, pISBN);
            //TODO eman beharrezkoa: isbn, izena, erreserbatuta, mailegatuta
            bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_LIBURUAK_EGUNERATU, SQLManager.getManager().getKolekziokoLiburuak(this.nan, pKolekzioIzena));
            bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_LIBURUA_KENDU_ONDO);
        }
        catch (SQLException e){
            bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_LIBURUA_KENDU_TXARTO, e.getMessage());
        }
    }

    public void getErabiltzaileInformazioa(){
        try {
            //TODO eman beharrezkoa: nan, izena, abizena, generoa, jaiotze data
            bistaNotifikatu(NotifikazioMotak.ERABARR_KON_INFORMAZIOA_EGUNERATU, SQLManager.getManager().getErabiltzaileInformazioa(this.nan));
        }
        catch (SQLException e){
            System.out.printf("Errore bat getErabiltzaileInformazioa metodojo SQL-an");
            e.printStackTrace();
        }
    }

    public void erabiltzaileInformazioaEguneratu(String pIzena, String pAbizena, String pPasahitza, String pGeneroa, String pJaioData){
        try{
            SQLManager.getManager().erabiltzaileInformazioaEguneratu(this.nan, pIzena, pAbizena, pPasahitza, pGeneroa, pJaioData);
            //TODO eman beharrezkoa: nan, izena, abizena, generoa, jaiotze data
            bistaNotifikatu(NotifikazioMotak.ERABARR_KON_INFORMAZIOA_EGUNERATU, SQLManager.getManager().getErabiltzaileInformazioa(this.nan));
            bistaNotifikatu(NotifikazioMotak.ERABARR_KON_ALDAKETA_ONDO);
        }
        catch (SQLException e){
            bistaNotifikatu(NotifikazioMotak.ERABARR_KON_ALDAKETA_TXARTO, e.getMessage());
        }
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