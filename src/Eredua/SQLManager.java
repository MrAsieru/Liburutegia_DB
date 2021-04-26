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
        try{
            Statement statement = konexioa.createStatement();
            statement.executeUpdate("""
                UPDATE Erabiltzailea
                SET pasahitza=\'%s\'
                WHERE nan=\'%s\';
                """.formatted(pPasahitza, pNAN));
        }
        catch (SQLException e){
            throw e;
        }
    }

    public ArrayList<Liburua> getKatalogoa() {
        ArrayList<Liburua> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("""
                SELECT L.isbn, L.izena, L.argitaratze_data, L.lengoaia, A.izena, L.mailegatuta, L.erreserbatuta
                FROM Liburua L JOIN Argitaletxe A ON L.arg_ifk = A.ifk;
                """);

            while (results.next()) {
                Liburua liburua = new Liburua();
                liburua.isbn = Long.parseLong(results.getString(1));
                liburua.izena = results.getString(2);
                liburua.argitaratzeData = results.getString(3);
                liburua.lengoaia = results.getString(4);
                liburua.argitaletxeaIzena = results.getString(5);
                liburua.mailegatuta = results.getBoolean(6);
                liburua.erreserbatua = results.getBoolean(7);
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
                and = true;
            }
            if (pDataGoi != null) {
                if (and){
                    and = false;
                    esaldia += " AND ";
                }
                esaldia += "L.argitaratze_data <= \'%s\'".formatted(pDataGoi);
                and = true;
            }
            if (pLengoaia != null) {
                if (and){
                    and = false;
                    esaldia += " AND ";
                }
                esaldia += "L.lengoaia=\'%s\'".formatted(pLengoaia);
                and = true;
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
                liburua.isbn = Long.parseLong(results.getString(1));
                liburua.izena = results.getString(2);
                liburua.argitaratzeData = results.getString(3);
                liburua.lengoaia = results.getString(4);
                liburua.argitaletxeaIzena = results.getString(5);
                liburua.mailegatuta = results.getBoolean(6);
                liburua.erreserbatua = results.getBoolean(7);
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

    public ArrayList<Erabiltzailea> getErabiltzaileak(String pNan, String pIzena, String pAbizena) {
        ArrayList<Erabiltzailea> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
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

    public ArrayList<Liburua> getLiburuak() {
        ArrayList<Liburua> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
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
        //TODO konprobatu erabilera
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
    public ArrayList<Mailegua> getMaileguak() {
        ArrayList<Mailegua> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("""
                SELECT L.isbn, L.izena, E.nan, E.izena
                FROM Liburua L JOIN Erabiltzailea E on E.nan = L.erab_nan
                WHERE L.mailegatuta=1;
                """); //CUIDADO!!!!!!! Teneis que conseguir las tablas y cambiarselas respectivamente como estan abajo

            while (results.next()) {
                Mailegua mailegua = new Mailegua();
                mailegua.isbn = results.getLong(1);
                mailegua.liburuaIzena = results.getString(2);
                mailegua.nan = results.getString(3);
                mailegua.erabiltzaileaIzena = results.getString(4);
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
    public ArrayList<Argitaletxea> getArgitaletxeak() {
        ArrayList<Argitaletxea> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("""
                SELECT *
                FROM Argitaletxe;
                """);

            while (results.next()) {
                Argitaletxea argitaletxea = new Argitaletxea();
                argitaletxea.ifk= results.getString("ifk");
                argitaletxea.helbidea = results.getString("helbidea");
                argitaletxea.izena = results.getString("izena");
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
        try{
            Statement statement = konexioa.createStatement();
            statement.execute("""
                INSERT INTO Liburua
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
        //TODO probatu
        try{
            Statement statement = konexioa.createStatement();
            statement.executeUpdate("""
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
        //TODO ez doa
        try{
            Statement statement = konexioa.createStatement();
            statement.executeUpdate("""
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
        //TODO ez doa
        try{
            Statement statement = konexioa.createStatement();
            statement.executeUpdate("""
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
        //TODO egin
        try{
            Statement statement = konexioa.createStatement();
            statement.executeUpdate("""
                    INSERT INTO Idazlea 
                    VALUES(\'%s\', \'%s\', \'%s\', \'%s\', \'%s\')
                    """.formatted(pIdz.id, pIdz.izena, pIdz.abizena, pIdz.generoa, pIdz.herrialdea));
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void removeAutorea(String pIdazleZenbakia) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO egin
        try{
            Statement statement = konexioa.createStatement();
            statement.executeUpdate("""
                    DELETE * FROM Idazlea 
                    WHERE (znb=\'%s\')
                    """.formatted(pIdazleZenbakia));
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void addArgitaletxea(Argitaletxea pArgitaletxea) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO egin
        try{
            Statement statement = konexioa.createStatement();
            statement.executeUpdate("""
                    INSERT INTO Argitaletxea 
                    VALUES(\'%s\', \'%s\', \'%s\')
                    """.formatted(pArgitaletxea.ifk, pArgitaletxea.izena, pArgitaletxea.helbidea));
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void removeArgitaletzea(String pIFK) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO egin
        try{
            Statement statement = konexioa.createStatement();
            statement.executeUpdate("""
                        DELETE * FROM 
                        Argitaletxea WHERE(ifk=\'%s\')
                        """.formatted(pIFK));
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void addMailegua(String pNAN, long pISBN) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO egin
        try{
            Statement statement = konexioa.createStatement();
            statement.executeUpdate("""
                    UPDATE Liburua 
                    SET (mailegatuta=1 AND erab_nan=\'%s\') WHERE isbn=\'%s\'
                    """.formatted(pNAN, pISBN));
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void removeMailegua(long pISBN) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO probatu
        try{
            Statement statement = konexioa.createStatement();
            statement.executeUpdate("""
                UPDATE Liburua 
                SET mailegatuta=0, erab_nan=NULL 
                WHERE isbn=\'%s\'
            """.formatted(pISBN));
        }
        catch (SQLException e){
            throw e;
        }
    }

    /**Erabiltzaile Arrunta**/

    public void sortuKolekzioa(String pNAN, String pIzena) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO egin
        try{
            Statement statement = konexioa.createStatement();
            statement.executeUpdate("""
            INSERT INTO Kolekzioa 
            VALUES(\'%s\',\'%s\')
            """.formatted(pNAN,pIzena));
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void removeKolekzioa(String pKolekzioIzena, String pNAN) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO egin
        try{
            Statement statement = konexioa.createStatement();
            statement.executeUpdate("""
                DELETE FROM Kolekzioa 
                WHERE erab_nan=\'%s\' AND izena=\'%s\'
            """.formatted(pNAN, pKolekzioIzena));
        }
        catch (SQLException e){
            throw e;
        }
    }
    public ArrayList<LiburuKolekzio> getKolekzioak(String pNAN) {
        return getKolekzioak(pNAN, -1, -1);
    }
    public ArrayList<LiburuKolekzio> getKolekzioak(String pNAN, int pBehe, int pGoi) {
        ArrayList<LiburuKolekzio> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            String esaldia = """
                SELECT x.kol_izena, SUM(x.guztira)
                FROM (
                    SELECT kol_izena, COUNT(*) AS guztira
                    FROM Kolekzio_Liburua
                    WHERE erab_nan=\'%s\'
                    GROUP BY kol_izena
                    UNION
                    SELECT izena, 0
                    FROM Kolekzioa
                    WHERE erab_nan=\'%s\') AS x
                GROUP BY x.kol_izena
                """.formatted(pNAN, pNAN);
            if (pBehe != -1 && pGoi == -1) {
                esaldia += " HAVING %s <= SUM(x.guztira)".formatted(pBehe);
            } else if (pBehe == -1 && pGoi != -1) {
                esaldia += " HAVING SUM(x.guztira) <= %s".formatted(pGoi);
            } else if (pBehe != -1 && pGoi != -1) {
                esaldia += " HAVING SUM(x.guztira) BETWEEN %s AND %s".formatted(pBehe, pGoi);
            }
            esaldia += " ORDER BY x.kol_izena";
            esaldia += ";";
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery(esaldia);

            while (results.next()) {
                LiburuKolekzio kolekzio = new LiburuKolekzio();
                kolekzio.izena = results.getString(1);
                kolekzio.liburuKantitatea = results.getInt(2);
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
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("""
                SELECT L.isbn, L.izena, L.erreserbatuta, L.mailegatuta
                FROM Kolekzio_Liburua K JOIN Liburua L ON K.lib_isbn = L.isbn
                WHERE K.erab_nan=\'%s\' AND
                    K.kol_izena=\'%s\';
                """.formatted(pNAN, pKolekzioa));

            while (results.next()) {
                Liburua liburua = new Liburua();
                liburua.isbn = results.getLong(1);
                liburua.izena = results.getString(2);
                liburua.erreserbatua = results.getBoolean(3);
                liburua.mailegatuta = results.getBoolean(4);
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
        //TODO egin gabe
        try{
            Statement statement = konexioa.createStatement();
            statement.executeUpdate("""
                        INSERT INTO Kolekzioa_Liburua 
                        VALUES(\'%s\', \'%s\', \'%s\')
                        """.formatted(pNAN, pKolekzioIzena, pISBN));
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void removeLiburuakKolekziora(String pNAN, String pKolekzioIzena, long pISBN) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO egin gabe
        try{
            Statement statement = konexioa.createStatement();
            statement.executeUpdate("""
            DELETE FROM Kolekzioa_Liburua 
            WHERE(erab_nan=\'%s\' and kol_izena =\'%s\'  and lib_isbn =\'%s\')
            """.formatted(pNAN, pKolekzioIzena, pKolekzioIzena));
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void liburuaErreserbatu(String pNAN, long pIsbn) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO egin gabe
        try{
            Statement statement = konexioa.createStatement();
            statement.executeUpdate("""

""");
        }
        catch (SQLException e){
            throw e;
        }
    }
    //TODO eman beharrezkoa: nan, izena, abizena, generoa, jaiotze data
    public ArrayList<String> getErabiltzaileInformazioa(String pNAN) throws SQLException {
        ArrayList<String> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("""
                SELECT nan, izena, abizena, generoa, jaiotze_data
                FROM Erabiltzailea
                WHERE nan=\'%s\'
            """.formatted(pNAN));

            results.next();
            lista.add(results.getString(1));
            lista.add(results.getString(2));
            lista.add(results.getString(3));
            lista.add(results.getString(4));
            lista.add(results.getString(5));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public void erabiltzaileInformazioaEguneratu(String pNAN, String pIzena, String pAbizena, String pPasahitza, String pGeneroa, String pJaioData) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        //TODO probatu
        try{
            Statement statement = konexioa.createStatement();
            if (pIzena != null) statement.executeUpdate("""
                                UPDATE Erabiltzailea
                                SET izena=\'%s\'
                                WHERE nan=\'%s\';
                                """.formatted(pNAN, pIzena));
            else if (pAbizena != null) statement.executeUpdate("""
                                UPDATE Erabiltzailea
                                SET abizena=\'%s\'
                                WHERE nan=\'%s\';
                                """.formatted(pNAN, pAbizena));
            else if (pJaioData != null) statement.executeUpdate("""
                                UPDATE Erabiltzailea
                                SET jaiotze_data=\'%s\'
                                WHERE nan=\'%s\';
                                """.formatted(pNAN, pJaioData));
            else if (pGeneroa != null) statement.executeUpdate("""
                                UPDATE Erabiltzailea
                                SET generoa=\'%s\'
                                WHERE nan=\'%s\';
                                """.formatted(pNAN, pGeneroa));
            else if (pPasahitza != null) statement.executeUpdate("""
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
