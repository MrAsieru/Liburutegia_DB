package Eredua;

import Egitura.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

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

            String zerbitzaria = "jdbc:mysql://localhost:3306/Liburutegia";
            String erabiltzailea = "root";
            String pasahitza = "";
            System.out.printf("[Modeloa.SQLManager] Konexioa sortzen... \n");
            konexioa = DriverManager.getConnection(zerbitzaria, erabiltzailea, pasahitza);
            System.out.printf("[Modeloa.SQLManager] Konektatu egin gara! \n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        /**Login**/

    public ErabiltzaileMota checkLogin(String pNan, String pPasahitza) {
        ErabiltzaileMota erab = ErabiltzaileMota.ARRUNTA;
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("select * from Erabiltzailea".formatted());

            results.next(); //Lehenengo taula lortzeko
            if (results.getString("Admin")=="0") {
                erab = ErabiltzaileMota.LIBURUZAIN;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return erab;
    }

        /**Erabiltzaile guztiek erabiltzen duten metodoak**/


    public void IzenaAldatu(String pNAN, String pIzena) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void AbizenaAldatu(String pNAN, String pAbizena) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void JaiotzeDataAldatu(String pNAN, Date pJaiotzeData) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void GeneroaAldatu(String pNAN, String pGeneroa) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void aldatuPasahitza(String pNAN, String pPasahitza) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public ArrayList<Liburua> getKatalogoa() {
        ArrayList<Liburua> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("select * from Liburua".formatted());

            while (results.next()) {
                Liburua liburua = new Liburua();
                liburua.isbn = Long.parseLong(results.getString("ISBN"));
                liburua.izena = results.getString("Izena");
                liburua.argitaratzeData = results.getString("ArgitaratzeEguna");
                liburua.lengoaia = results.getString("Hizkuntza");
                liburua.mailegatuta = Boolean.parseBoolean(results.getString("Mailegatu"));
                liburua.erreserbatua = Boolean.parseBoolean(results.getString("Erreserbatua"));
                liburua.erabiltzaileaNAN = results.getString("ErabiltzaileaNAN");
                liburua.idazleaZnb = 12;//Integer.parseInt(results.getString("IdazleZenbakia"));
                liburua.argitaletxeaIFK = results.getString("ArgitaletxeIFK");
                lista.add(liburua);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Liburua> getKatalogoa(String pIzena, String pDataBehe, String pDataGoi, String pLengoaia, boolean pEskuragarri) {
        ArrayList<Liburua> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("".formatted());

            while (results.next()) {
                Liburua liburua = new Liburua();
                liburua.isbn = Long.parseLong(results.getString("ISBN"));
                liburua.izena = results.getString("Izena");
                liburua.argitaratzeData = results.getString("ArgitaratzeEguna");
                liburua.lengoaia = results.getString("Hizkuntza");
                liburua.mailegatuta = Boolean.parseBoolean(results.getString("Mailegatu"));
                liburua.erreserbatua = Boolean.parseBoolean(results.getString("Erreserbatua"));
                liburua.erabiltzaileaNAN = results.getString("ErabiltzaileaNAN");
                liburua.idazleaZnb = Integer.parseInt(results.getString("IdazleZenbakia"));
                liburua.argitaletxeaIFK = results.getString("ArgitaletxeIFK");
                lista.add(liburua);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    /**Liburuzaina**/

    public ArrayList<Erabiltzailea> getErabiltzaileak() {
        ArrayList<Erabiltzailea> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("".formatted());

            while (results.next()) {
                Erabiltzailea erab = new Erabiltzailea();
                erab.nan = results.getString("NAN");
                erab.izena = results.getString("Izena");
                erab.abizena = results.getString("Abizena");
                erab.jaiotzeData = results.getString("JaiotzeData");
                erab.generoa = results.getString("Genero");
                erab.liburuzainaDa = false;
                if(Integer.parseInt(results.getString("Admin"))==0){
                    erab.liburuzainaDa = true;
                }
                lista.add(erab);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Erabiltzailea> getErabiltzaileak(String pNan, String pIzena, String pAbizena) {
        ArrayList<Erabiltzailea> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("".formatted());

            while (results.next()) {
                Erabiltzailea erab = new Erabiltzailea();
                erab.nan = results.getString("NAN");
                erab.izena = results.getString("Izena");
                erab.abizena = results.getString("Abizena");
                erab.jaiotzeData = results.getString("JaiotzeData");
                erab.generoa = results.getString("Genero");
                erab.liburuzainaDa = false;
                if(Integer.parseInt(results.getString("Admin"))==0){
                    erab.liburuzainaDa = true;
                }
                lista.add(erab);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Liburua> getLiburuak() {
        ArrayList<Liburua> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("".formatted());

            while (results.next()) {
                Liburua liburua = new Liburua();
                liburua.isbn = Long.parseLong(results.getString("ISBN"));
                liburua.izena = results.getString("Izena");
                liburua.argitaratzeData = results.getString("ArgitaratzeEguna");
                liburua.lengoaia = results.getString("Hizkuntza");
                liburua.mailegatuta = Boolean.parseBoolean(results.getString("Mailegatu"));
                liburua.erreserbatua = Boolean.parseBoolean(results.getString("Erreserbatua"));
                liburua.erabiltzaileaNAN = results.getString("ErabiltzaileaNAN");
                liburua.idazleaZnb = Integer.parseInt(results.getString("IdazleZenbakia"));
                liburua.argitaletxeaIFK = results.getString("ArgitaletxeIFK");
                lista.add(liburua);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public Liburua getLiburua(long pISBN) {
        Liburua liburua = new Liburua();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("".formatted());
            results.next(); //Lehenengo taula (eta bakarra) lortzeko

            liburua.isbn = Long.parseLong(results.getString("ISBN"));
            liburua.izena = results.getString("Izena");
            liburua.argitaratzeData = results.getString("ArgitaratzeEguna");
            liburua.lengoaia = results.getString("Hizkuntza");
            liburua.mailegatuta = Boolean.parseBoolean(results.getString("Mailegatu"));
            liburua.erreserbatua = Boolean.parseBoolean(results.getString("Erreserbatua"));
            liburua.erabiltzaileaNAN = results.getString("ErabiltzaileaNAN");
            liburua.idazleaZnb = Integer.parseInt(results.getString("IdazleZenbakia"));
            liburua.argitaletxeaIFK = results.getString("ArgitaletxeIFK");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return liburua;
    }

    public ArrayList<Mailegua> getMaileguak() {
        ArrayList<Mailegua> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("".formatted()); //CUIDADO!!!!!!! Teneis que conseguir las tablas y cambiarselas respectivamente como estan abajo

            while (results.next()) {
                Mailegua mailegua = new Mailegua();
                mailegua.nan = results.getString("ErabiltzaileaNAN");
                mailegua.isbn = Long.parseLong(results.getString("ISBN"));
                mailegua.erabiltzaileaIzena = results.getString("ErabiltzaileIzena");
                mailegua.liburuaIzena = results.getString("LiburuIzena");
                lista.add(mailegua);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Idazlea> getIdazleak() {
        ArrayList<Idazlea> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("".formatted());

            while (results.next()) {
                Idazlea idazlea = new Idazlea();
                idazlea.id = Integer.parseInt(results.getString("IdazleZenbakia"));
                idazlea.izena = results.getString("Izena");
                idazlea.abizena = results.getString("Abizenak");
                idazlea.generoa = results.getString("Genero");
                idazlea.herrialdea = results.getString("Herrialdea");
                lista.add(idazlea);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Argitaletxea> getArgitaletxeak() {
        ArrayList<Argitaletxea> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("".formatted());

            while (results.next()) {
                Argitaletxea argitaletxea = new Argitaletxea();
                argitaletxea.IFK = results.getString("IFK");
                argitaletxea.Helbidea = results.getString("Helbidea");
                argitaletxea.Izena = results.getString("Izena");
                lista.add(argitaletxea);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;

    }

    public void addLiburu(Liburua pLiburua) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }

    }

    public void removeLiburu(long ISBN) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void addErabiltzaileArrunta(Erabiltzailea pErabiltzaile, String pPasahitza) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void removeErabiltzaile(String pNAN) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }
    
    public void addAutorea(Idazlea pIdz) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void removeAutorea(String pIdazleZenbakia) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void updateAutoreIzena(String pIdazleZenbakia, String pIzena) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void updateAutoreAbizena(String pIdazleZenbakia, String pAbizena) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void updateAutoreGenero(String pIdazleZenbakia, String pGenero) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void updateAutoreHerrialdea(String pIdazleZenbakia, String pHerrialdea) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void addArgitaletxea(Argitaletxea argitaletxea) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void removeArgitaletzea(String pIFK) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void updateArgitaletzeIzena(String pIFK, String pIzena) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void updateArgitaletzeHelbidea(String pIFK, String pHelbidea) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void addMailegua(String NAN, long pISBN) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void removeMailegua(long pISBN) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    /**Erabiltzaile Arrunta**/

    public void sortuKolekzioa(String pNAN, String pIzena) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void removeKolekzioa(String pKolekzioIzena, String pNAN) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public ArrayList<LiburuKolekzio> getKolekzioak(String pNAN) {
        ArrayList<LiburuKolekzio> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("".formatted());

            while (results.next()) {
                LiburuKolekzio kolekzio = new LiburuKolekzio();
                kolekzio.Izena = results.getString("Izena");
                kolekzio.ErabiltzaileaNAN = results.getString("ErabiltzaileaNAN");
                kolekzio.LiburuLista = this.getKolekziokoLiburuak(pNAN, kolekzio.Izena);
                lista.add(kolekzio);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Liburua> getKolekziokoLiburuak(String pNAN, String pKolekzioa) {
        ArrayList<Liburua> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("".formatted()); //Lortu erabiltzaile baten kolekzioa liburuaren bidez

            while (results.next()) {
                Liburua liburua = new Liburua();
                liburua.isbn = Long.parseLong(results.getString("ISBN"));
                liburua.izena = results.getString("Izena");
                liburua.argitaratzeData = results.getString("ArgitaratzeEguna");
                liburua.lengoaia = results.getString("Hizkuntza");
                liburua.mailegatuta = Boolean.parseBoolean(results.getString("Mailegatu"));
                liburua.erreserbatua = Boolean.parseBoolean(results.getString("Erreserbatua"));
                liburua.erabiltzaileaNAN = results.getString("ErabiltzaileaNAN");
                liburua.idazleaZnb = Integer.parseInt(results.getString("IdazleZenbakia"));
                liburua.argitaletxeaIFK = results.getString("ArgitaletxeIFK");
                lista.add(liburua);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public void addLiburuakKolekziora(String pNAN, String pKolekzioIzena, long pISBN) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void removeLiburuakKolekziora(String pNAN, String pKolekzioIzena, long pISBN) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void liburuaErreserbatu(String pNAN, long pIsbn) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    public String[] getErabiltzaileInformazioa(String pNAN) throws SQLException {
        //String[] non nan, izena, abizena, generoa, jaiotze data
        String[] lista = new String[4];
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("".formatted()); //Erabiltzaile bakarra lortu!!!

            String[] erabiltzaileInfo = new String[5];
            results.next();
            erabiltzaileInfo[0] = results.getString("NAN");
            erabiltzaileInfo[1] = results.getString("Izena");
            erabiltzaileInfo[2] = results.getString("Abizena");
            erabiltzaileInfo[3] = results.getString("Generoa");
            erabiltzaileInfo[4] = results.getString("JaiotzeData");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public void erabiltzaileInformazioaEguneratu(String pNAN, String pIzena, String pAbizena, String pPasahitza, String pGeneroa, String pJaioData) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("".formatted());
        }
        catch (SQLException e){
            throw e;
        }
    }

    private String getMetodoIzena(StackTraceElement[] trace){
        return trace[1].getMethodName();
    }
}
