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
        bistaNotifikatu(NotifikazioMotak.ERABARR_KAT_TAULA_EGUNERATU, SQLManager.getManager().getKatalogoa());
    }

    public void getKatalogoa(String pIzena, String pDataBehe, String pDataGoi, String pLengoaia, boolean pEskuragarri) {
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
        bistaNotifikatu(NotifikazioMotak.ERABARR_KAT_TAULA_EGUNERATU, SQLManager.getManager().getKolekzioak(this.nan));
    }

    public void getKolekziokoLiburuak(String pKolekzioa) {
        bistaNotifikatu(NotifikazioMotak.ERABARR_KAT_TAULA_EGUNERATU, SQLManager.getManager().getKolekziokoLiburuak(this.nan, pKolekzioa));
    }

    public void sortuKolekzioa(String pIzena){
        try{
            SQLManager.getManager().sortuKolekzioa(this.nan, pIzena);
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
            bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_LIBURUAK_EGUNERATU, SQLManager.getManager().getKolekzioak(this.nan));
            bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_LIBURUA_GEHITU_ONDO);
        }
        catch (SQLException e){
            bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_LIBURUA_GEHITU_TXARTO, e.getMessage());
        }
    }

    public void removeLiburuaKolekzioan(String pKolekzioIzena, long pISBN){
        try{
            SQLManager.getManager().removeLiburuakKolekziora(this.nan, pKolekzioIzena, pISBN);
            bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_LIBURUAK_EGUNERATU, SQLManager.getManager().getKolekzioak(this.nan));
            bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_LIBURUA_KENDU_ONDO);
        }
        catch (SQLException e){
            bistaNotifikatu(NotifikazioMotak.ERABARR_KOL_LIBURUA_KENDU_TXARTO, e.getMessage());
        }
    }

    public void getErabiltzaileInformazioa(){
        bistaNotifikatu(NotifikazioMotak.ERABARR_KON_INFORMAZIOA_EGUNERATU, SQLManager.getManager().getErabiltzaileInformazioa(this.nan));
    }

    public void erabiltzaileInformazioaEguneratu(String pIzena, String pAbizena, String pPasahitza, String pGeneroa, String pJaioData){
        try{
            SQLManager.getManager().erabiltzaileInformazioaEguneratu(this.nan, pIzena, pAbizena, pPasahitza, pGeneroa, pJaioData);
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