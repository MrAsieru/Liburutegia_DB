package Egitura;

public class Mailegua {
	public long isbn;
	public String liburuaIzena;
	public String nan;
	public String erabiltzaileaIzena;
	
	public Mailegua() {}
	
	public Mailegua(long pIsbn, String pLiburuaIzena, String pNan, String pErabiltzaileaIzena) {
		this.isbn = pIsbn;
		this.liburuaIzena = pLiburuaIzena;
		this.nan = pNan;
		this.erabiltzaileaIzena = pErabiltzaileaIzena;
	}
}
