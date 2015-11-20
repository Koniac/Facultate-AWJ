package hello;

public class Ziar {
	public int id;
	public String nume;
	public int an;
	public int luna;
	public int zi;
	public Ziar(int id, String nume, int an, int luna, int zi) {
		super();
		this.id = id;
		this.nume = nume;
		this.an = an;
		this.luna = luna;
		this.zi = zi;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public int getAn() {
		return an;
	}
	public void setAn(int an) {
		this.an = an;
	}
	public int getLuna() {
		return luna;
	}
	public void setLuna(int luna) {
		this.luna = luna;
	}
	public int getZi() {
		return zi;
	}
	public void setZi(int zi) {
		this.zi = zi;
	}


}
