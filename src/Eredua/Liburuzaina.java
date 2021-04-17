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
        //TODO erabiltzaileak filtroekin bilatu eta bueltatu
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_ERAB_LISTA_EGUNERATU
    }

    public void getErabiltzaileak(String pNan, String pIzena, String pAbizena) {
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_LISTA_EGUNERATU, SQLManager.getManager().getErabiltzaileak(pNan, pIzena, pAbizena));
        //TODO erabiltzaileak filtroekin bilatu eta bueltatu
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_ERAB_LISTA_EGUNERATU
    }

    public void addErabiltzaileArrunta(Erabiltzailea pErabiltzaile, String pPasahitza){
        SQLManager.getManager().addErabiltzaileArrunta(pErabiltzaile, pPasahitza);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_LISTA_EGUNERATU, SQLManager.getManager().getErabiltzaileak());
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_ERAB_SORTU_ONDO,
        // LIBURUZAIN_ERAB_SORTU_TXARTO
        // LIBURUZAIN_ERAB_LISTA_EGUNERATU
    }

    public void aldatuPasahitza(String pNan, String pPasahitza) {
        SQLManager.getManager().aldatuPasahitza(pNan, pPasahitza);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_PASAHITZA_TXARTO, "Pasahitzaren logika ez dago sortuta.");
        //TODO erabiltzailearen pasahitza aldatu eta bueltatu
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_ERAB_PASAHITZA_ONDO
        // LIBURUZAIN_ERAB_PASAHITZA_TXARTO
    }

    public void removeErabiltzailea(String pNAN){
        SQLManager.getManager().removeErabiltzaile(pNAN);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ERAB_LISTA_EGUNERATU, SQLManager.getManager().getErabiltzaileak());
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_ERAB_EZA_ONDO
        // LIBURUZAIN_ERAB_EZA_TXARTO
        // LIBURUZAIN_ERAB_LISTA_EGUNERATU
    }

    public void getLiburuak() {
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_KAT_TAULA_EGUNERATU, SQLManager.getManager().getLiburuak());
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_KAT_TAULA_EGUNERATU
    }

    public void getLengoaiak() {
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_KAT_GEH_LENGOAIA_LISTA_EGUNERATU, SQLManager.getManager().getLengoaiak());
        // Lista hemen sortu, ez da behar DB-n egotea
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_KAT_GEH_LENGOAIA_LISTA_EGUNERATU
    }

    public void addLiburu(Liburua pLiburua){
        SQLManager.getManager().addLiburu(pLiburua);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_KAT_TAULA_EGUNERATU, SQLManager.getManager().getLiburuak());
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_KAT_GEH_ONDO_SORTUTA
        // LIBURUZAIN_KAT_GEH_TXARTO_SORTUTA
        // LIBURUZAIN_KAT_TAULA_EGUNERATU
    }

    public void removeLiburu(long ISBN){
        SQLManager.getManager().removeLiburu(ISBN);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_KAT_TAULA_EGUNERATU, SQLManager.getManager().getLiburuak());
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_KAT_EZA_ONDO_EZABATUTA
        // LIBURUZAIN_KAT_EZA_TXARTO_EZABATUTA
        // LIBURUZAIN_KAT_TAULA_EGUNERATU
    }

    public void getMaileguak() {
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_MAI_TAULA_EGUNERATU, SQLManager.getManager().getMaileguak());
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_MAI_TAULA_EGUNERATU
    }

    public void addMailegua(String NAN, long pISBN){
        SQLManager.getManager().addMailegua(NAN, pISBN);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_MAI_TAULA_EGUNERATU, SQLManager.getManager().getMaileguak());
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_MAI_HASI_ONDO
        // LIBURUZAIN_MAI_HASI_ERRESERBATUTA
        // LIBURUZAIN_MAI_HASI_TXARTO
        // LIBURUZAIN_MAI_TAULA_EGUNERATU
    }

    public void removeMailegua(long pISBN){
        SQLManager.getManager().removeMailegua(pISBN);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_MAI_TAULA_EGUNERATU, SQLManager.getManager().getMaileguak());
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_MAI_BUELTATU_ONDO
        // LIBURUZAIN_MAI_BUELTATU_TXARTO
        // LIBURUZAIN_MAI_TAULA_EGUNERATU
    }

    public void getIdazleak() {
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_IDL_TAULA_EGUNERATU, SQLManager.getManager().getIdazleak());
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_KAT_GEH_IDAZLE_LISTA_EGUNERATU
        // LIBURUZAIN_IDL_TAULA_EGUNERATU
    }

    public void addIdazlea(Idazlea pIdl) {
        SQLManager.getManager().addAutorea(pIdl);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_IDL_TAULA_EGUNERATU, SQLManager.getManager().getIdazleak());
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_IDL_GEH_ONDO
        // LIBURUZAIN_IDL_GEH_TXARTO
        // LIBURUZAIN_IDL_TAULA_EGUNERATU
    }

    public void removeIdazlea(String pIdazleZenbakia){
        SQLManager.getManager().removeAutorea(pIdazleZenbakia);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_IDL_TAULA_EGUNERATU, SQLManager.getManager().getIdazleak());
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_IDL_KEN_ONDO
        // LIBURUZAIN_IDL_KEN_TXARTO
        // LIBURUZAIN_IDL_TAULA_EGUNERATU
    }

    public void getArgitaletxeak() {
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ARG_TAULA_EGUNERATU, SQLManager.getManager().getArgitaletxeak());
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_KAT_GEH_ARGITALETXE_LISTA_EGUNERATU
        // LIBURUZAIN_ARG_TAULA_EGUNERATU
    }

    public void addArgitaletxea(Argitaletxea arg) {
        SQLManager.getManager().addArgitaletxea(arg);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ARG_TAULA_EGUNERATU, SQLManager.getManager().getArgitaletxeak());
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_ARG_GEH_ONDO
        // LIBURUZAIN_ARG_GEH_TXARTO
        // LIBURUZAIN_ARG_TAULA_EGUNERATU
    }

    public void removeArgitaletzea(String pIFK){
        SQLManager.getManager().removeArgitaletzea(pIFK);
        bistaNotifikatu(NotifikazioMotak.LIBURUZAIN_ARG_TAULA_EGUNERATU, SQLManager.getManager().getArgitaletxeak());
        //TODO erabili behar diren notifikazio motak:
        // LIBURUZAIN_ARG_KEN_ONDO
        // LIBURUZAIN_ARG_KEN_TXARTO
        // LIBURUZAIN_ARG_TAULA_EGUNERATU
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