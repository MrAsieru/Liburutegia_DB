package Eredua;

import Egitura.Erabiltzailea;
import Egitura.Liburua;

public class SQLManager {

    private static SQLManager SQLManager;

    public static SQLManager getManager(){
        if(SQLManager==null){
            SQLManager = new SQLManager();
        }
        return SQLManager;
    }

    /**Erabiltzaile Guztiak**/

    public void updatePasahitza(String NAN, String pPasahitza){

    }

    public void IzenaAldatu(){

    }

    public void AbizenaAldatu(String pNAN){

    }

    public void JaiotzeDataAldatu(String pNAN){

    }

    public void GeneroaAldatu(String pNAN){

    }

    /**Liburuzaina**/

    public void addLiburu(Liburua pLiburua){

    }

    public void removeLiburu(long ISBN){

    }

    public void addErabiltzaileArrunta(Erabiltzailea pErabiltzaile){

    }

    public void removeErabiltzaile(String pNAN){

    }

    public void removeAutorea(String pIdazleZenbakia){

    }

    public void updateAutoreIzena(String pIdazleZenbakia, String pIzena){

    }

    public void updateAutoreAbizena(String pIdazleZenbakia, String pAbizena){

    }

    public void updateAutoreGenero(String pIdazleZenbakia, String pGenero){

    }

    public void updateAutoreHerrialdea(String pIdazleZenbakia, String pHerrialdea){

    }

    public void removeArgitaletzea(String pIFK){

    }

    public void updateArgitaletzeIzena(String pIFK, String pIzena){

    }

    public void updateArgitaletzeHelbidea(String pIFK, String pHelbidea){

    }

    public void addMailegua(String NAN, long pISBN){

    }

    public void removeMailegua(long pISBN){

    }

    /**Erabiltzaile Arrunta**/

    public void sortuKolekzioa(String pIzena){

    }

    public void removeKolekzioa(String pKolekzioIzena){

    }

    public void addLiburuakKolekziora(String pKolekzioIzena, long pISBN){

    }

    public void removeLiburuakKolekziora(String pKolekzioIzena, long pISBN){

    }
}
