package Eredua;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class ErabiltzaileArrunta extends Observable {

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
    
    public void erabiltzaileEzarri(String pNan) {
    	if (nan == null) {
    		nan = pNan;
    	}
    }
    
    public void getKatalogoa() {
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KAT_TAULA_EGUNERATU
    }
    
    public void getKatalogoa(String pIzena, String pDataBehe, String pDataGoi, String pLengoaia, boolean pEskuragarri) {
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KAT_TAULA_EGUNERATU
    }
    
    public void liburuaErreserbatu(long pIsbn) {
        //TODO erabili behar diren notifikazio motak:
        // ERABARR_KAT_ERRESERBA_ONDO
        // ERABARR_KAT_ERRESERBA_TXARTO
    }
    
    public void getKolekzioak() {
    	//TODO
    }
    
    public void getKolekziokoLiburuak(String pKolekzioa) {
    	//TODO
    }

    public void sortuKolekzioa(String pIzena){
        SQLManager.getManager().sortuKolekzioa(this.nan, pIzena);
    }

    public void removeKolekzioa(String pKolekzioIzena){
        SQLManager.getManager().removeKolekzioa(this.nan, pKolekzioIzena);
    }

    public void addLiburuaKolekziora(String pKolekzioIzena, long pISBN){
        SQLManager.getManager().addLiburuakKolekziora(this.nan, pKolekzioIzena, pISBN);
    }

    public void removeLiburuaKolekzioan(String pKolekzioIzena, long pISBN){
        SQLManager.getManager().removeLiburuakKolekziora(this.nan, pKolekzioIzena, pISBN);
    }

    public void izenaAldatu(String pIzena){
        SQLManager.getManager().IzenaAldatu(this.nan, pIzena);
    }

    public void abizenaAldatu(String pAbizena){
        SQLManager.getManager().AbizenaAldatu(this.nan, pAbizena);
    }

    public void jaiotzeDataAldatu(Date pDate){
        SQLManager.getManager().JaiotzeDataAldatu(this.nan, pDate);
    }

    public void generoaAldatu(String pGeneroa){
        SQLManager.getManager().GeneroaAldatu(this.nan, pGeneroa);
    }

    public void pasahitzaAldatu(String NAN, String pPasahitza){
        //TODO
    }
}
