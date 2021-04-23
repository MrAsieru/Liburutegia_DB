package Eredua;

import java.sql.SQLException;
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
        //TODO eman beharrezkoa: nan, izena, abizena, jaiotzedata, generoa
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_LISTA_EGUNERATU, SQLManager.getManager().getErabiltzaileak());
    }

    public void getErabiltzaileak(String pNan, String pIzena, String pAbizena) {
        //TODO eman beharrezkoa: nan, izena, abizena, jaiotzedata, generoa
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_LISTA_EGUNERATU, SQLManager.getManager().getErabiltzaileak(pNan, pIzena, pAbizena));
    }

    public void addErabiltzaileArrunta(Erabiltzailea pErabiltzaile, String pPasahitza){
        try{
            SQLManager.getManager().addErabiltzaileArrunta(pErabiltzaile, pPasahitza);
            //TODO eman beharrezkoa: nan, izena, abizena, jaiotzedata, generoa
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_LISTA_EGUNERATU, SQLManager.getManager().getErabiltzaileak());
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_SORTU_ONDO);
        }
        catch (SQLException e){
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_SORTU_TXARTO, e.getMessage());
        }
    }

    public void aldatuPasahitza(String pNan, String pPasahitza) {
        try{
            SQLManager.getManager().aldatuPasahitza(pNan, pPasahitza);
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_PASAHITZA_ONDO);
        }
        catch (SQLException e){
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_PASAHITZA_TXARTO, e.getMessage());
        }
    }

    public void removeErabiltzailea(String pNAN){
        try{
            SQLManager.getManager().removeErabiltzaile(pNAN);
            //TODO eman beharrezkoa: nan, izena, abizena, jaiotzedata, generoa
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_LISTA_EGUNERATU, SQLManager.getManager().getErabiltzaileak());
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_EZA_ONDO);
        }
        catch (SQLException e){
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_EZA_TXARTO, e.getMessage());
        }
    }

    public void getLiburuak() {
        //TODO eman beharrezkoa: isbn, izena, argitaratzedata, lengoaia, erreserbatuta, mailegatuta, erabiltzaileNAN
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_KAT_TAULA_EGUNERATU, SQLManager.getManager().getLiburuak());
    }

    public void getLengoaiak() {
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_KAT_GEH_LENGOAIA_LISTA_EGUNERATU,
                new String[]{"Euskera", "Gaztelera", "Ingelesa"});
    }

    public void addLiburu(Liburua pLiburua){
        try{
            SQLManager.getManager().addLiburu(pLiburua);
            //TODO eman beharrezkoa: isbn, izena, argitaratzedata, lengoaia, erreserbatuta, mailegatuta, erabiltzaileNAN
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_KAT_TAULA_EGUNERATU, SQLManager.getManager().getLiburuak());
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_KAT_GEH_ONDO_SORTUTA);
        }
        catch (SQLException e){
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_KAT_GEH_TXARTO_SORTUTA, e.getMessage());
        }
    }

    public void removeLiburu(long ISBN){
        try{
            SQLManager.getManager().removeLiburu(ISBN);
            //TODO eman beharrezkoa: isbn, izena, argitaratzedata, lengoaia, erreserbatuta, mailegatuta, erabiltzaileNAN
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_KAT_TAULA_EGUNERATU, SQLManager.getManager().getLiburuak());
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_KAT_EZA_ONDO_EZABATUTA);
        }
        catch (SQLException e){
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_KAT_EZA_TXARTO_EZABATUTA, e.getMessage());
        }
    }

    public void getMaileguak() {
        //TODO eman beharrezkoa: isbn, liburuaizena, nan erabiltzaileaizena
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_MAI_TAULA_EGUNERATU, SQLManager.getManager().getMaileguak());
    }

    public void addMailegua(String NAN, long pISBN){
        //
        Liburua lib = SQLManager.getManager().getLiburua(pISBN);
        if (!lib.mailegatuta) {
            if (!lib.erreserbatua) {
                try {
                    SQLManager.getManager().addMailegua(NAN, pISBN);
                    //TODO eman beharrezkoa: isbn, liburuaizena, nan erabiltzaileaizena
                    bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_MAI_TAULA_EGUNERATU, SQLManager.getManager().getMaileguak());
                    bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_MAI_HASI_ONDO);
                } catch (SQLException e) {
                    bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_MAI_BUELTATU_TXARTO, e.getMessage());
                }
            }
            else {
                //TODO eman beharrezkoa: izena, abizena, nan
                bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_MAI_HASI_ERRESERBATUTA);
            }
        }
        else {
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_MAI_HASI_TXARTO, "liburua mailegatuta");
        }
    }

    public void removeMailegua(long pISBN){
        try{
            SQLManager.getManager().removeMailegua(pISBN);
            //TODO eman beharrezkoa: isbn, liburuaizena, nan erabiltzaileaizena
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_MAI_TAULA_EGUNERATU, SQLManager.getManager().getMaileguak());
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_MAI_BUELTATU_ONDO);
        }
        catch (SQLException e){
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_MAI_BUELTATU_TXARTO, e.getMessage());
        }
    }

    public void getIdazleak() {
        //TODO eman beharrezkoa: id, izena, abizena, generoa, herrialdea
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_IDAZLE_LISTA_EGUNERATU, SQLManager.getManager().getIdazleak());
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_IDAZLE_LISTA_EGUNERATU
    }

    public void addIdazlea(Idazlea pIdl) {
        try{
            SQLManager.getManager().addAutorea(pIdl);
            //TODO eman beharrezkoa: id, izena, abizena, generoa, herrialdea
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_IDAZLE_LISTA_EGUNERATU, SQLManager.getManager().getIdazleak());
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_IDL_GEH_ONDO);
        }
        catch (SQLException e){
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_IDL_GEH_TXARTO, e.getMessage());
        }
    }

    public void removeIdazlea(String pIdazleZenbakia){
        try{
            SQLManager.getManager().removeAutorea(pIdazleZenbakia);
            //TODO eman beharrezkoa: id, izena, abizena, generoa, herrialdea
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_IDAZLE_LISTA_EGUNERATU, SQLManager.getManager().getIdazleak());
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_IDL_KEN_ONDO);
        }
        catch (SQLException e){
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_IDL_KEN_TXARTO, e.getMessage());
        }
    }

    public void getArgitaletxeak() {
        //TODO eman beharrezkoa: ifk, izena, helbidea
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ARGITALETXE_LISTA_EGUNERATU, SQLManager.getManager().getArgitaletxeak());
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_ARGITALETXE_LISTA_EGUNERATU
    }

    public void addArgitaletxea(Argitaletxea arg) {
        try{
            SQLManager.getManager().addArgitaletxea(arg);
            //TODO eman beharrezkoa: ifk, izena, helbidea
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ARGITALETXE_LISTA_EGUNERATU, SQLManager.getManager().getArgitaletxeak());
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ARG_GEH_ONDO);
        }
        catch (SQLException e){
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ARG_GEH_TXARTO, e.getMessage());
        }
    }

    public void removeArgitaletzea(String pIFK){
        try{
            SQLManager.getManager().removeArgitaletzea(pIFK);
            //TODO eman beharrezkoa: ifk, izena, helbidea
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ARGITALETXE_LISTA_EGUNERATU, SQLManager.getManager().getArgitaletxeak());
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ARG_KEN_ONDO);
        }
        catch (SQLException e){
            bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ARG_KEN_TXARTO, e.getMessage());
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