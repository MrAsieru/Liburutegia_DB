package Eredua;

import Egitura.Erabiltzaile;
import Egitura.Liburua;

public class Liburuzaina {

    private String NAN;

    public Liburuzaina(String pNAN){
        this.NAN = pNAN;
    }

    public void updatePasahitza(String NAN, String pPasahitza){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }

    public void IzenaAldatu(String pIzena){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }

    public void AbizenaAldatu(String pNAN){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }

    public void JaiotzeDataAldatu(String pNAN){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }

    public void GeneroaAldatu(String pNAN){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }

    public void addLiburu(Liburua pLiburua){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }

    public void removeLiburu(long ISBN){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }

    public void addErabiltzaileArrunta(Erabiltzaile pErabiltzaile){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }

    public void removeErabiltzaile(String pNAN){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }

    public void removeAutorea(String pIdazleZenbakia){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }

    public void updateAutoreIzena(String pIdazleZenbakia, String pIzena){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }

    public void updateAutoreAbizena(String pIdazleZenbakia, String pAbizena){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }

    public void updateAutoreGenero(String pIdazleZenbakia, String pGenero){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }

    public void updateAutoreHerrialdea(String pIdazleZenbakia, String pHerrialdea){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }

    public void removeArgitaletzea(String pIFK){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }

    public void updateArgitaletzeIzena(String pIFK, String pIzena){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }

    public void updateArgitaletzeHelbidea(String pIFK, String pHelbidea){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }

    public void addMailegua(String NAN, long pISBN){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }

    public void removeMailegua(long pISBN){
        SQLManager.getManager().updatePasahitza(this.NAN, pPasahitza);
    }
}
