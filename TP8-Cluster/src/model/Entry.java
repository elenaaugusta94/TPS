package model;

public class Entry implements Comparable<Similaridade> {
	//private String classes;
	private Similaridade dadosClasses;

	public Entry(Similaridade dadosClasses) {
		this.dadosClasses = dadosClasses;
	}

    public Entry() {}
    @Override
	public int compareTo(Similaridade other) {
		if (this.dadosClasses.getValueSimilarity() < other.getValueSimilarity()) {
            return 1;
        }
        if (this.dadosClasses.getValueSimilarity() > other.getValueSimilarity()) {
            return -1;
        }
        return 0;
	}

	public Similaridade getDadosClasses() {
		return dadosClasses;
	}

	
	

	
    
    
    
    
}
