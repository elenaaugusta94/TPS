package model;

public class Entry implements Comparable<Entry> {
	private String classe1;
	private String classe2;
	private float valor;
	//private Similaridade dadosClasses;

	public Entry(String classe1, String classe2, float valor) {
		this.classe1 = classe1;
		this.classe2 = classe2;
		this.valor = valor;
	}

    public Entry() {}

	@Override
	public int compareTo(Entry other){
		if (this.valor < other.valor) {
			return 1;
		}
		if (this.valor > other.valor) {
			return -1;
		}
	      return 0;
	}

	public String getClasse1() {
		return classe1;
	}

	public void setClasse1(String classe1) {
		this.classe1 = classe1;
	}

	public String getClasse2() {
		return classe2;
	}

	public void setClasse2(String classe2) {
		this.classe2 = classe2;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	
	

	
    
    
    
    
}
