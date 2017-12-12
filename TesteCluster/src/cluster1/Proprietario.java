package cluster1;

import java.io.Serializable;

import pacoteGlobal.Endereco;
import pacoteGlobal.Pessoa;

/**
 * Classe responsável por representar o Prorpietario de um Veiculo cadastrado no Detran. 
 * Essa classe herda os atributos da classe Pessoa e especifica 
 * características com relação ao numero de sua CNH.
 * 
 * @author Elena, Christian
 */
public class Proprietario extends Pessoa implements Serializable{
    
    private String numCarteira;
 

    /**
     * Construtor da classe Proprietario no qual necessita dos seguintes parâmetros
     * @param numCarteira   numero da carteira (CNH) do Proprietario
     * @param endereco      endereco do Proprietario
     * @param nome          nome do Proprietario
     */
    
    public Proprietario(String numCarteira, Endereco endereco, String nome) {
        super(nome, endereco);
        this.numCarteira = numCarteira;      
        
    }
    
    
    /**Metodo que retorna o numero da CNH do Proprietario
     @return numeroDaCarteira
     */
    public String getNumCarteira(){
        return numCarteira;
    }

    
    
    
}
