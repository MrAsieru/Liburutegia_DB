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
        ErabiltzaileMota erab = null;
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("""
            SELECT liburuzaina_da
            FROM Erabiltzailea
            WHERE nan=\'%s\' AND 
                pasahitza=\'%s\';
            """.formatted(pNan, pPasahitza));

            if (!results.next()){
                erab = ErabiltzaileMota.OKERRA;
            } else {
                if (results.getBoolean("liburuzaina_da")) erab = ErabiltzaileMota.LIBURUZAIN;
                else erab = ErabiltzaileMota.ARRUNTA;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return erab;
    }

    public void aldatuPasahitza(String pNAN, String pPasahitza) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            statement.executeQuery("""
                UPDATE Erabiltzailea
                SET pasahitza=\'%s\'
                WHERE nan=\'%s\';
                """.formatted(pPasahitza, pNAN));
        }
        catch (SQLException e){
            throw e;
        }
    }
    //TODO eman beharrezkoa: isbn, izena, argData, lengoaia, argitaletxeaIzena, mailegatuta, erreserbatuta
    public ArrayList<Liburua> getKatalogoa() {
        ArrayList<Liburua> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("""
                SELECT L.isbn, L.izena, L.argitaratze_data, L.lengoaia, A.izena, L.mailegatuta, L.erreserbatuta
                FROM Liburua L JOIN Argitaletxe A ON L.arg_ifk = A.ifk;
                """);

            while (results.next()) {
                Liburua liburua = new Liburua();
                liburua.isbn = Long.parseLong(results.getString(0));
                liburua.izena = results.getString(1);
                liburua.argitaratzeData = results.getString(2);
                liburua.lengoaia = results.getString(3);
                liburua.argitaletxeaIzena = results.getString(4);
                liburua.mailegatuta = Boolean.parseBoolean(results.getString(5));
                liburua.erreserbatua = Boolean.parseBoolean(results.getString(6));
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
            boolean and = false;
            String esaldia = """
                SELECT L.isbn, L.izena, L.argitaratze_data, L.lengoaia, A.izena, L.mailegatuta, L.erreserbatuta
                FROM Liburua L JOIN Argitaletxe A ON L.arg_ifk = A.ifk
                """;
            if (pIzena != null || pDataBehe != null || pDataGoi != null || pLengoaia != null || pEskuragarri) {
                esaldia += " WHERE ";
            }
            if (pIzena != null){
                esaldia += "L.izena LIKE \'%%%s%%\'".formatted(pIzena);
                and = true;
            }
            if (pDataBehe != null) {
                if (and){
                    and = false;
                    esaldia += " AND ";
                }
                esaldia += "\'%s\' <= L.argitaratze_data".formatted(pDataBehe);
            }
            if (pDataGoi != null) {
                if (and){
                    and = false;
                    esaldia += " AND ";
                }
                esaldia += "L.argitaratze_data <= \'%s\'".formatted(pDataGoi);
            }
            if (pLengoaia != null) {
                if (and){
                    and = false;
                    esaldia += " AND ";
                }
                esaldia += "L.lengoaia=\'%s\'".formatted(pLengoaia);
            }
            if (pEskuragarri) {
                if (and){
                    and = false;
                    esaldia += " AND ";
                }
                esaldia += "L.mailegatuta=0 AND L.erreserbatuta=0";
            }
            esaldia+=";";

            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery(esaldia);

            while (results.next()) {
                Liburua liburua = new Liburua();
                liburua.isbn = Long.parseLong(results.getString(0));
                liburua.izena = results.getString(1);
                liburua.argitaratzeData = results.getString(2);
                liburua.lengoaia = results.getString(3);
                liburua.argitaletxeaIzena = results.getString(4);
                liburua.mailegatuta = Boolean.parseBoolean(results.getString(5));
                liburua.erreserbatua = Boolean.parseBoolean(results.getString(6));
                lista.add(liburua);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    /**Liburuzaina**/
//TODO eman beharrezkoa: nan, izena, abizena, jaiotzedata, generoa
    public ArrayList<Erabiltzailea> getErabiltzaileak() {
        ArrayList<Erabiltzailea> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("""
                SELECT nan, izena, abizena, jaiotze_data, generoa
                FROM Erabiltzailea;
                """);

            while (results.next()) {
                Erabiltzailea erab = new Erabiltzailea();
                erab.nan = results.getString("nan");
                erab.izena = results.getString("izena");
                erab.abizena = results.getString("abizena");
                erab.jaiotzeData = results.getString("jaiotze_data");
                erab.generoa = results.getString("generoa");
                lista.add(erab);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    //TODO eman beharrezkoa: nan, izena, abizena, jaiotzedata, generoa
    public ArrayList<Erabiltzailea> getErabiltzaileak(String pNan, String pIzena, String pAbizena) {
        ArrayList<Erabiltzailea> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("""
                SELECT nan, izena, abizena, jaiotze_data, generoa
                FROM Erabiltzailea
                WHERE nan LIKE \'%%%s%%\' AND
                    izena LIKE \'%%%s%%\' AND
                    abizena LIKE \'%%%s%%\';
                """.formatted(pNan, pIzena, pAbizena));

            while (results.next()) {
                Erabiltzailea erab = new Erabiltzailea();
                erab.nan = results.getString("nan");
                erab.izena = results.getString("izena");
                erab.abizena = results.getString("abizena");
                erab.jaiotzeData = results.getString("jaiotze_data");
                erab.generoa = results.getString("generoa");
                lista.add(erab);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    //TODO eman beharrezkoa: isbn, izena, argitaratzedata, lengoaia, erreserbatuta, mailegatuta, erabiltzaileNAN
    public ArrayList<Liburua> getLiburuak() {
        ArrayList<Liburua> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("""
                SELECT isbn, izena, argitaratze_data, lengoaia, erreserbatuta, mailegatuta, erab_nan
                FROM Liburua;
                """);

            while (results.next()) {
                Liburua liburua = new Liburua();
                liburua.isbn = Long.parseLong(results.getString("isbn"));
                liburua.izena = results.getString("izena");
                liburua.argitaratzeData = results.getString("argitaratze_data");
                liburua.lengoaia = results.getString("lengoaia");
                liburua.mailegatuta = Boolean.parseBoolean(results.getString("mailegatuta"));
                liburua.erreserbatua = Boolean.parseBoolean(results.getString("erreserbatuta"));
                liburua.erabiltzaileaNAN = results.getString("erab_nan");
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
            ResultSet results = statement.executeQuery("""
                SELECT *
                FROM Liburua
                WHERE isbn=\'%d\'
                """.formatted(pISBN));
            results.next(); //Lehenengo taula (eta bakarra) lortzeko

            liburua.isbn = Long.parseLong(results.getString("isbn"));
            liburua.izena = results.getString("izena");
            liburua.argitaratzeData = results.getString("argitaratze_data");
            liburua.lengoaia = results.getString("hizkuntza");
            liburua.mailegatuta = Boolean.parseBoolean(results.getString("mailegatuta"));
            liburua.erreserbatua = Boolean.parseBoolean(results.getString("erreserbatuta"));
            liburua.erabiltzaileaNAN = results.getString("erab_nan");
            liburua.idazleaZnb = Integer.parseInt(results.getString("idl_znb"));
            liburua.argitaletxeaIFK = results.getString("arg_ifk");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return liburua;
    }
    //TODO eman beharrezkoa: isbn, liburuaizena, nan, erabiltzaileaizena
    public ArrayList<Mailegua> getMaileguak() {
        ArrayList<Mailegua> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("""
                SELECT L.isbn, L.izena, E.nan, E.izena
                FROM Liburua L JOIN Erabiltzailea E on E.nan = L.erab_nan
                WHERE L.mailegatuta=1;
"""); //CUIDADO!!!!!!! Teneis que conseguir las tablas y cambiarselas respectivamente como estan abajo

            while (results.next()) {
                Mailegua mailegua = new Mailegua();
                mailegua.isbn = Long.parseLong(results.getString(0));
                mailegua.liburuaIzena = results.getString(1);
                mailegua.nan = results.getString(2);
                mailegua.erabiltzaileaIzena = results.getString(3);
                lista.add(mailegua);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    //TODO eman beharrezkoa: id, izena, abizena, generoa, herrialdea
    public ArrayList<Idazlea> getIdazleak() {
        ArrayList<Idazlea> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("""
                SELECT znb, izena, abizenak, generoa, herrialdea
                FROM Idazlea;
                """);

            while (results.next()) {
                Idazlea idazlea = new Idazlea();
                idazlea.id = Integer.parseInt(results.getString("znb"));
                idazlea.izena = results.getString("izena");
                idazlea.abizena = results.getString("abizenak");
                idazlea.generoa = results.getString("generoa");
                idazlea.herrialdea = results.getString("herrialdea");
                lista.add(idazlea);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    //TODO eman beharrezkoa: ifk, izena, helbidea
    public ArrayList<Argitaletxea> getArgitaletxeak() {
        ArrayList<Argitaletxea> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("""
                SELECT *
                FROM Argitaletxe;
                """);

            while (results.next()) {
                Argitaletxea argitaletxea = new Argitaletxea();
                argitaletxea.IFK = results.getString("ifk");
                argitaletxea.Helbidea = results.getString("helbidea");
                argitaletxea.Izena = results.getString("izena");
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
            statement.execute("""
                INSERT INTO
                VALUES (%s, \'%s\', \'%s\', \'%s\', 0, 0, NULL, %s, \'%s\');
                """.formatted(pLiburua.isbn, pLiburua.izena,
                    pLiburua.argitaratzeData, pLiburua.lengoaia,
                    pLiburua.idazleaZnb, pLiburua.argitaletxeaIFK));
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
            statement.executeQuery("""
                DELETE FROM Liburua
                WHERE isbn=\'%s\'
                """.formatted(ISBN));
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
            statement.executeQuery("""
                INSERT INTO Erabiltzailea
                VALUES (\'%s\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\');
                """.formatted(pErabiltzaile.nan, pErabiltzaile.izena,
                    pErabiltzaile.abizena, pErabiltzaile.jaiotzeData,
                    pErabiltzaile.generoa, 0, pPasahitza));
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
            statement.executeQuery("""
                DELETE FROM Erabiltzailea
                WHERE nan=\'%s\';
                """.formatted(pNAN));
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
    //TODO eman beharrezkoa: String[]: izenak, int[]: liburu kantitateak
    public ArrayList<LiburuKolekzio> getKolekzioak(String pNAN) {
        ArrayList<LiburuKolekzio> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("".formatted());

            while (results.next()) {
                LiburuKolekzio kolekzio = new LiburuKolekzio();
                kolekzio.izena = results.getString("Izena");
                kolekzio.erabNAN = results.getString("ErabiltzaileaNAN");
                kolekzio.liburuLista = this.getKolekziokoLiburuak(pNAN, kolekzio.izena); //TODO liburuak ez dira behar
                lista.add(kolekzio);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    public ArrayList<LiburuKolekzio> getKolekzioak(String pNAN, int pBehe, int pGoi) { //TODO -1 bada ez hartu kontuan
        ArrayList<LiburuKolekzio> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("".formatted());

            while (results.next()) {
                LiburuKolekzio kolekzio = new LiburuKolekzio();
                kolekzio.izena = results.getString("Izena");
                kolekzio.erabNAN = results.getString("ErabiltzaileaNAN");
                kolekzio.liburuLista = this.getKolekziokoLiburuak(pNAN, kolekzio.izena);
                lista.add(kolekzio);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    //TODO eman beharrezkoa: isbn, izena, erreserbatuta, mailegatuta
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
    //TODO eman beharrezkoa: nan, izena, abizena, generoa, jaiotze data
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
        try{
            Statement statement = konexioa.createStatement();
            if (pIzena != null) statement.executeQuery("""
                                UPDATE Erabiltzailea
                                SET izena=\'%s\'
                                WHERE nan=\'%s\';
                                """.formatted(pNAN, pIzena));
            else if (pAbizena != null) statement.executeQuery("""
                                UPDATE Erabiltzailea
                                SET abizena=\'%s\'
                                WHERE nan=\'%s\';
                                """.formatted(pNAN, pAbizena));
            else if (pJaioData != null) statement.executeQuery("""
                                UPDATE Erabiltzailea
                                SET jaiotze_data=\'%s\'
                                WHERE nan=\'%s\';
                                """.formatted(pNAN, pJaioData));
            else if (pGeneroa != null) statement.executeQuery("""
                                UPDATE Erabiltzailea
                                SET generoa=\'%s\'
                                WHERE nan=\'%s\';
                                """.formatted(pNAN, pGeneroa));
            else if (pPasahitza != null) statement.executeQuery("""
                                UPDATE Erabiltzailea
                                SET pasahitza=\'%s\'
                                WHERE nan=\'%s\';
                                """.formatted(pNAN, pJaioData));
        }
        catch (SQLException e){
            throw e;
        }
    }

    private String getMetodoIzena(StackTraceElement[] trace){
        return trace[1].getMethodName();
    }
}
