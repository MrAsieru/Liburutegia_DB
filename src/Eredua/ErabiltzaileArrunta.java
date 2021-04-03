package Eredua;

import java.util.Date;

public class ErabiltzaileArrunta {

    private String nan;
    private static ErabiltzaileArrunta instantzia;

    private ErabiltzaileArrunta(){
    }

    public static ErabiltzaileArrunta getInstantzia() {
        if (instantzia == null) {
            instantzia = new ErabiltzaileArrunta();
        }
        return instantzia;
    }

    public void PasahitzaAldatu(String NAN, String pPasahitza){

    }

    public void IzenaAldatu(String pIzena){
        SQLManager.getManager().IzenaAldatu(this.nan, pIzena);
    }

    public void AbizenaAldatu(String pAbizena){
        SQLManager.getManager().AbizenaAldatu(this.nan, pAbizena);
    }

    public void JaiotzeDataAldatu(Date pDate){
        SQLManager.getManager().JaiotzeDataAldatu(this.nan, pDate);
    }

    public void GeneroaAldatu(String pGeneroa){
        SQLManager.getManager().GeneroaAldatu(this.nan, pGeneroa);
    }

    public void sortuKolekzioa(String pIzena){
        SQLManager.getManager().sortuKolekzioa(this.nan, pIzena);
    }

    public void removeKolekzioa(String pKolekzioIzena){
        SQLManager.getManager().removeKolekzioa(this.nan, pKolekzioIzena);
    }

    public void addLiburuakKolekziora(String pKolekzioIzena, long pISBN){
        SQLManager.getManager().addLiburuakKolekziora(this.nan, pKolekzioIzena, pISBN);
    }

    public void removeLiburuakKolekziora(String pKolekzioIzena, long pISBN){
        SQLManager.getManager().removeLiburuakKolekziora(this.nan, pKolekzioIzena, pISBN);
    }

}