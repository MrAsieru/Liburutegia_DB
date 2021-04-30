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
            String kontsulta = """
            SELECT liburuzaina_da
            FROM Erabiltzailea
            WHERE nan=? AND 
                pasahitza=?;
            """;
            PreparedStatement statement = konexioa.prepareStatement(kontsulta);
            statement.setString(1, pNan);
            statement.setString(2, pPasahitza);
            ResultSet results = statement.executeQuery();
            System.out.println(statement);

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
            String kontsulta = """
                UPDATE Erabiltzailea
                SET pasahitza=?
                WHERE nan=?;
                """;
            PreparedStatement statement = konexioa.prepareStatement(kontsulta);
            statement.setString(1, pPasahitza);
            statement.setString(2, pNAN);
            statement.executeUpdate();
            System.out.println(statement);
        }
        catch (SQLException e){
            throw e;
        }
    }

    public ArrayList<Liburua> getKatalogoa(String pNan) {
        ArrayList<Liburua> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            Statement statement = konexioa.createStatement();
            ResultSet results = statement.executeQuery("""
                SELECT isbn, L.izena, argitaratze_data, lengoaia, A.izena, mailegatuta, erreserbatuta, erab_nan
                FROM Liburua L JOIN Argitaletxea A ON L.arg_ifk = A.ifk
                UNION
                SELECT isbn, L.izena, argitaratze_data, lengoaia, NULL, mailegatuta, erreserbatuta, erab_nan
                FROM Liburua L
                WHERE arg_ifk IS NULL
                ORDER BY isbn;
                """);

            while (results.next()) {
                Liburua liburua = new Liburua();
                liburua.isbn = results.getLong(1);
                liburua.izena = results.getString(2);
                liburua.argitaratzeData = results.getString(3);
                liburua.lengoaia = results.getString(4);
                liburua.argitaletxeaIzena = results.getString(5);
                liburua.mailegatuta = results.getBoolean(6);
                liburua.erreserbatua = results.getBoolean(7);
                liburua.erabiltzaileaNAN = results.getString(8);
                if (pNan.equals(liburua.erabiltzaileaNAN)) liburua.erabiltzaileBera = true;
                lista.add(liburua);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Liburua> getKatalogoa(String pNan, String pIzena, String pDataBehe, String pDataGoi, String pLengoaia, boolean pEskuragarri) {
        ArrayList<Liburua> lista = new ArrayList<>();
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            boolean and = false;
            String esaldia = """
                SELECT L.isbn, L.izena, L.argitaratze_data, L.lengoaia, A.izena, L.mailegatuta, L.erreserbatuta, L.erab_nan
                FROM Liburua L JOIN Argitaletxea A ON L.arg_ifk = A.ifk
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
                liburua.isbn = results.getLong(1);
                liburua.izena = results.getString(2);
                liburua.argitaratzeData = results.getString(3);
                liburua.lengoaia = results.getString(4);
                liburua.argitaletxeaIzena = results.getString(5);
                liburua.mailegatuta = results.getBoolean(6);
                liburua.erreserbatua = results.getBoolean(7);
                if (pNan.equals(liburua.erabiltzaileaNAN)) liburua.erabiltzaileBera = true;
                liburua.erabiltzaileaNAN = results.getString(8);
                lista.add(liburua);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public Erabiltzailea getErabiltzailea(String pNan) {
        Erabiltzailea erab = null;
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            String kontsulta = """
                SELECT izena, abizena
                FROM Erabiltzailea
                WHERE nan = ?
                """;
            PreparedStatement statement = konexioa.prepareStatement(kontsulta);
            statement.setString(1, pNan);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                erab = new Erabiltzailea();
                erab.nan = pNan;
                erab.izena = results.getString("izena");
                erab.abizena = results.getString("abizena");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return erab;
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
            String kontsulta = """
                SELECT nan, izena, abizena, jaiotze_data, generoa
                FROM Erabiltzailea
                WHERE nan LIKE ? AND
                    izena LIKE ? AND
                    abizena LIKE ?;
                """;
            PreparedStatement statement = konexioa.prepareStatement(kontsulta);
            statement.setString(1, "%"+pNan+"%");
            statement.setString(2, "%"+pIzena+"%");
            statement.setString(3, "%"+pAbizena+"%");
            ResultSet results = statement.executeQuery();
            System.out.println(statement);

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
                liburua.isbn = results.getLong("isbn");
                liburua.izena = results.getString("izena");
                liburua.argitaratzeData = results.getString("argitaratze_data");
                liburua.lengoaia = results.getString("lengoaia");
                liburua.mailegatuta = results.getBoolean("mailegatuta");
                liburua.erreserbatua = results.getBoolean("erreserbatuta");
                liburua.erabiltzaileaNAN = results.getString("erab_nan");
                lista.add(liburua);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public Liburua getLiburuaMailegatzeko(long pISBN) {
        Liburua liburua = null;
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            String kontsulta = """
                SELECT mailegatuta, erreserbatuta, erab_nan
                FROM Liburua
                WHERE isbn=?
                """;
            PreparedStatement statement = konexioa.prepareStatement(kontsulta);
            statement.setLong(1, pISBN);
            ResultSet results = statement.executeQuery();
            System.out.println(statement);

            if (results.next()){
                liburua = new Liburua();
                //Lehenengo taula (eta bakarra) lortzeko
                liburua.mailegatuta = results.getBoolean("mailegatuta");
                liburua.erreserbatua = results.getBoolean("erreserbatuta");
                liburua.erabiltzaileaNAN = results.getString("erab_nan");
            }
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
                """);

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
                idazlea.id = results.getInt("znb");
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
                FROM Argitaletxea;
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
            String esaldia = """
                INSERT INTO Liburua
                VALUES (?, ?, ?, ?, 0, 0, NULL, ?, ?);
                """;
            PreparedStatement statement = konexioa.prepareStatement(esaldia);
            statement.setLong(1, pLiburua.isbn);
            statement.setString(2, pLiburua.izena);
            statement.setString(3, pLiburua.argitaratzeData);
            statement.setString(4, pLiburua.lengoaia);
            statement.setInt(5, pLiburua.idazleaZnb);
            statement.setString(6, pLiburua.argitaletxeaIFK);
            statement.execute();
            System.out.println(statement);
        }
        catch (SQLException e){
            throw e;
        }

    }

    public void removeLiburu(long ISBN) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            String esaldia = """
            DELETE FROM Liburua
            WHERE isbn=?
            """;
            PreparedStatement statement = konexioa.prepareStatement(esaldia);
            statement.setLong(1, ISBN);
            int ezabatuta = statement.executeUpdate();
            System.out.println(statement);

            if (ezabatuta == 0) throw new SQLException("liburua ez da existitzen");
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void addErabiltzaileArrunta(Erabiltzailea pErabiltzaile, String pPasahitza) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            String kontsulta = """
            INSERT INTO Erabiltzailea (nan, izena, abizena, jaiotze_data, generoa, liburuzaina_da, pasahitza)
            VALUES (?, ?, ?, ?, ?, 0, ?)
            """;
            PreparedStatement statement = konexioa.prepareStatement(kontsulta);
            statement.setString(1, pErabiltzaile.nan);
            statement.setString(2, pErabiltzaile.izena);
            statement.setString(3, pErabiltzaile.abizena);
            statement.setString(4, pErabiltzaile.jaiotzeData);
            statement.setString(5, pErabiltzaile.generoa);
            statement.setString(6, pPasahitza);
            statement.executeUpdate();
            System.out.println(statement);
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void removeErabiltzaile(String pNAN) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            String esaldia = """
            DELETE FROM Erabiltzailea
            WHERE nan=?
            """;
            PreparedStatement statement = konexioa.prepareStatement(esaldia);
            statement.setString(1, pNAN);
            statement.executeUpdate();
            System.out.println(statement);
        }
        catch (SQLException e){
            throw e;
        }
    }
    
    public void addAutorea(Idazlea pIdz) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            String kontsulta = """
            INSERT INTO Idazlea 
            VALUES(?, ?, ?, ?, ?)
            """;
            PreparedStatement statement = konexioa.prepareStatement(kontsulta);
            statement.setInt(1, pIdz.id);
            statement.setString(2, pIdz.izena);
            statement.setString(3, pIdz.abizena);
            statement.setString(4, pIdz.generoa);
            statement.setString(5, pIdz.herrialdea);
            statement.executeUpdate();
            System.out.println(statement);
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void removeAutorea(String pIdazleZenbakia) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            String esaldia = """
            DELETE FROM Idazlea 
            WHERE (znb=?)
            """;
            PreparedStatement statement = konexioa.prepareStatement(esaldia);
            statement.setString(1, pIdazleZenbakia);
            statement.executeUpdate();
            System.out.println(statement);
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void addArgitaletxea(Argitaletxea pArgitaletxea) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        if (pArgitaletxea.ifk.equals("")) throw new SQLException("IFK ez da balioduna");
        try{
            String esaldia = """
            INSERT INTO Argitaletxea 
            VALUES(?, ?, ?)
            """;
            PreparedStatement statement = konexioa.prepareStatement(esaldia);
            statement.setString(1, pArgitaletxea.ifk);
            statement.setString(2, pArgitaletxea.izena);
            statement.setString(3, pArgitaletxea.helbidea);
            statement.executeUpdate();
            System.out.println(statement);
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void removeArgitaletxea(String pIFK) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            String esaldia = """
            DELETE FROM Argitaletxea 
            WHERE ifk=?
            """;
            PreparedStatement statement = konexioa.prepareStatement(esaldia);
            statement.setString(1, pIFK);
            statement.executeUpdate();
            System.out.println(statement);
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void addMailegua(String pNAN, long pISBN) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            String esaldia = """
                    UPDATE Liburua 
                    SET mailegatuta=1, erreserbatuta=0, erab_nan=?
                    WHERE isbn=? AND 
                        mailegatuta=0 AND
                        (erreserbatuta=0 OR
                            (erreserbatuta=1 AND
                            erab_nan=?))
                    """;
            PreparedStatement statement = konexioa.prepareStatement(esaldia);
            statement.setString(1, pNAN);
            statement.setLong(2, pISBN);
            statement.setString(3, pNAN);
            statement.executeUpdate();
            System.out.println(statement);
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void removeMailegua(long pISBN) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
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

    public void removeKolekzioa(String pNAN, String pKolekzioIzena) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
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
                SELECT L.isbn, L.izena, L.erreserbatuta, L.mailegatuta, L.erab_nan
                FROM Kolekzio_Liburua K JOIN Liburua L ON K.lib_isbn = L.isbn
                WHERE K.erab_nan=\'%s\' AND
                    K.kol_izena=\'%s\';
                """.formatted(pNAN, pKolekzioa));

            while (results.next()) {
                Liburua liburua = new Liburua();
                liburua.isbn = results.getLong(1);
                liburua.izena = results.getString(2);
                liburua.mailegatuta = results.getBoolean(3);
                liburua.erreserbatua = results.getBoolean(4);
                liburua.erabiltzaileaNAN = results.getString(5);
                if (pNAN.equals(liburua.erabiltzaileaNAN)) liburua.erabiltzaileBera = true;
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
        try{
            Statement statement = konexioa.createStatement();
            statement.executeUpdate("""
                        INSERT INTO Kolekzio_Liburua 
                        VALUES(\'%s\', \'%s\', \'%s\')
                        """.formatted(pNAN, pKolekzioIzena, pISBN));
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void removeLiburuakKolekziora(String pNAN, String pKolekzioIzena, long pISBN) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            Statement statement = konexioa.createStatement();
            statement.executeUpdate("""
            DELETE FROM Kolekzio_Liburua 
            WHERE(erab_nan=\'%s\' and kol_izena =\'%s\'  and lib_isbn =\'%s\')
            """.formatted(pNAN, pKolekzioIzena, pISBN));
        }
        catch (SQLException e){
            throw e;
        }
    }

    public void liburuaErreserbatu(String pNAN, long pIsbn) throws SQLException {
        System.out.printf("[Modeloa.SQLManager] Metodo hau ejekutatuko dugu: " + getMetodoIzena(Thread.currentThread().getStackTrace()) + "\n");
        try{
            Statement statement = konexioa.createStatement();
            statement.executeUpdate("""
                UPDATE Liburua 
                SET erreserbatuta=1, erab_nan=\'%s\'
                WHERE (isbn=\'%s\' AND 
                    mailegatuta=0 AND 
                    erreserbatuta=0)
                """.formatted(pNAN, pIsbn, pNAN));
        }
        catch (SQLException e){
            throw e;
        }
    }

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
        try{
            Statement statement = konexioa.createStatement();
            if (pIzena != null) statement.executeUpdate("""
                                UPDATE Erabiltzailea
                                SET izena=\'%s\'
                                WHERE nan=\'%s\';
                                """.formatted(pIzena, pNAN));
            if (pAbizena != null) statement.executeUpdate("""
                                UPDATE Erabiltzailea
                                SET abizena=\'%s\'
                                WHERE nan=\'%s\';
                                """.formatted(pAbizena, pNAN));
            if (pJaioData != null) statement.executeUpdate("""
                                UPDATE Erabiltzailea
                                SET jaiotze_data=\'%s\'
                                WHERE nan=\'%s\';
                                """.formatted(pJaioData, pNAN));
            if (pGeneroa != null) statement.executeUpdate("""
                                UPDATE Erabiltzailea
                                SET generoa=\'%s\'
                                WHERE nan=\'%s\';
                                """.formatted(pGeneroa, pNAN));
            if (pPasahitza != null) statement.executeUpdate("""
                                UPDATE Erabiltzailea
                                SET pasahitza=\'%s\'
                                WHERE nan=\'%s\';
                                """.formatted(pPasahitza, pNAN));
        }
        catch (SQLException e){
            throw e;
        }
    }

    private String getMetodoIzena(StackTraceElement[] trace){
        return trace[1].getMethodName();
    }
}
