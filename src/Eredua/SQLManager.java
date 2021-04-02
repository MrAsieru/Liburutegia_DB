package Eredua;

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

        /**Erabiltzaile guztiek erabiltzen duten metodoak**/


    public void IzenaAldatu(String pNAN, String pIzena){
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
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    /**Liburuzaina**/

    public ArrayList<Erabiltzailea> getErabiltzaileak() {
        ArrayList<Erabiltzailea> listaErabiltzaileak = new ArrayList<>();
        //TODO
        return listaErabiltzaileak;
    }

    public ArrayList<Liburua> getLiburuak() {
        ArrayList<Liburua> listaLiburuak = new ArrayList<>();
        //TODO
        return listaLiburuak;
    }

    public ArrayList<Mailegua> getMaileguak() {
        ArrayList<Mailegua> listaMaileguak = new ArrayList<>();
        //TODO
        return listaMaileguak;
    }

    public ArrayList<Idazlea> getIdazleak() {
        ArrayList<Idazlea> listaIdazleak = new ArrayList<>();
        //TODO
        return listaIdazleak;
    }

    public ArrayList<Argitaletxea> getArgitaletxeak() {
        ArrayList<Argitaletxea> listaIdazleak = new ArrayList<>();
        //TODO
        return listaIdazleak;

    }

    public void addLiburu(Liburua pLiburua){
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
        try{
            String komandoa = String.format("");
            konexioa.prepareStatement(komandoa);
        }
        catch (SQLException e){
            System.out.println("Errore bat egon da komandoan.");
            e.printStackTrace();
        }
    }

    public void addLiburuakKolekziora(String pNAN, String pKolekzioIzena, long pISBN){
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
