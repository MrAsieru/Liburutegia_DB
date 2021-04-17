package Eredua;

/*
 * datubasea sortzeko komandoak:
 *  create database liburutegia;
 *  use liburutegia;
 *  create table Liburua (
 *      ISBN integer(13),
 *      Izena varchar(200),
 *
 *  )
 */

import Egitura.*;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLManager {

    private Connection konexioa;

    private static SQLManager SQLManager;

    private SQLManager(){
        konektatu();
    }

    public static SQLManager getManager(){
        if(SQLManager==null){
            SQLManager = new SQLManager();
        }
        return SQLManager;
    }

    private void konektatu() {
        try {
            // TODO - datu-basera konektatzeko kodea
            String zerbitzaria = "jdbc:mysql://localhost:3306/liburutegia";
            String erabiltzailea = "root";
            String pasahitza = "";
            konexioa = DriverManager.getConnection(zerbitzaria, erabiltzailea, pasahitza);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        /**Login**/

    public ErabiltzaileMota checkLogin(String pNan, String pPasahitza){
        //TODO
        return null;
    }

        /**Erabiltzaile guztiek erabiltzen duten metodoak**/


    public void IzenaAldatu(String pNAN, String pIzena){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void AbizenaAldatu(String pNAN, String pAbizena){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void JaiotzeDataAldatu(String pNAN, Date pJaiotzeData){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void GeneroaAldatu(String pNAN, String pGeneroa){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void aldatuPasahitza(String pNAN, String pPasahitza){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public ArrayList<Liburua> getKatalogoa(){
        ArrayList<Liburua> listaLiburuak = new ArrayList<>();
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
        return listaLiburuak;
    }

    public ArrayList<Liburua> getKatalogoa(String pIzena, String pDataBehe, String pDataGoi, String pLengoaia, boolean pEskuragarri) {
        ArrayList<Liburua> listaErabiltzaileak = new ArrayList<>();
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
        return listaErabiltzaileak;
    }

    /**Liburuzaina**/

    public ArrayList<Erabiltzailea> getErabiltzaileak() {
        ArrayList<Erabiltzailea> listaErabiltzaileak = new ArrayList<>();
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
        return listaErabiltzaileak;
    }

    public ArrayList<Erabiltzailea> getErabiltzaileak(String pNan, String pIzena, String pAbizena){
        ArrayList<Erabiltzailea> listaErabiltzaileak = new ArrayList<>();
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
        return listaErabiltzaileak;
    }

    public ArrayList<Liburua> getLiburuak() {
        ArrayList<Liburua> listaLiburuak = new ArrayList<>();
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
        return listaLiburuak;
    }

    public ArrayList<Mailegua> getMaileguak() {
        ArrayList<Mailegua> listaMaileguak = new ArrayList<>();
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
        return listaMaileguak;
    }

    public ArrayList<Idazlea> getIdazleak() {
        ArrayList<Idazlea> listaIdazleak = new ArrayList<>();
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
        return listaIdazleak;
    }

    public ArrayList<Argitaletxea> getArgitaletxeak() {
        ArrayList<Argitaletxea> listaIdazleak = new ArrayList<>();
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
        return listaIdazleak;

    }
    
    public ArrayList<String> getLengoaiak(){
        ArrayList<String> listaIdazleak = new ArrayList<>();
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
        return listaIdazleak;
    }

    public void addLiburu(Liburua pLiburua){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }

    }

    public void removeLiburu(long ISBN){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void addErabiltzaileArrunta(Erabiltzailea pErabiltzaile, String pPasahitza){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void removeErabiltzaile(String pNAN){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }
    
    public void addAutorea(Idazlea pIdz){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void removeAutorea(String pIdazleZenbakia){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void updateAutoreIzena(String pIdazleZenbakia, String pIzena){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void updateAutoreAbizena(String pIdazleZenbakia, String pAbizena){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void updateAutoreGenero(String pIdazleZenbakia, String pGenero){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void updateAutoreHerrialdea(String pIdazleZenbakia, String pHerrialdea){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void addArgitaletxea(Argitaletxea argitaletxea){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void removeArgitaletzea(String pIFK){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void updateArgitaletzeIzena(String pIFK, String pIzena){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void updateArgitaletzeHelbidea(String pIFK, String pHelbidea){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void addMailegua(String NAN, long pISBN){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void removeMailegua(long pISBN){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    /**Erabiltzaile Arrunta**/

    public void sortuKolekzioa(String pNAN, String pIzena){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void removeKolekzioa(String pKolekzioIzena, String pNAN){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public ArrayList<LiburuKolekzio> getKolekzioak(String pNAN) {
        ArrayList<LiburuKolekzio> listaIdazleak = new ArrayList<>();
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
        return listaIdazleak;
    }

    public ArrayList<Liburua> getKolekziokoLiburuak(String pNAN, String pKolekzioa) {
        ArrayList<Liburua> listaIdazleak = new ArrayList<>();
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
        return listaIdazleak;
    }

    public void addLiburuakKolekziora(String pNAN, String pKolekzioIzena, long pISBN){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void removeLiburuakKolekziora(String pNAN, String pKolekzioIzena, long pISBN){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void liburuaErreserbatu(String pNAN, long pIsbn) {
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public String[] getErabiltzaileInformazioa(String pNAN){
        //String[] non nan, izena, abizena, generoa, jaiotze data
        String[] listaIdazleak = new String[4];
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
        return listaIdazleak;
    }

    public void erabiltzaileInformazioaEguneratu(String pNAN, String pIzena, String pAbizena, String pPasahitza, String pGeneroa, String pJaioData){
        //TODO
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }
}
