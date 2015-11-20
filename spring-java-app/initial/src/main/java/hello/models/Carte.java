package hello;

public class Carte {

    private long id;
    private String titlu;
    private String autor;
	public Carte(long id, String titlu, String autor) {
		super();
		this.id = id;
		this.titlu = titlu;
		this.autor = autor;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitlu() {
		return titlu;
	}
	public void setTitlu(String titlu) {
		this.titlu = titlu;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}


}
