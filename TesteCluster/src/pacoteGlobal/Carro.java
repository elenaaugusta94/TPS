package pacoteGlobal;

import java.io.Serializable;

import cluster1.Proprietario;

/**
 * Classe Carro extende as características de Veiculo e implementa sua 
 * característica particular quantidade de portas e numero de assentos
 */
public class Carro extends Veiculo implements Serializable{
    
    private int qtdPortas, numAssentos; 
    
   /**Construtor da classe Carro no qual necessita dos seguintes parâmetros
     * 
     * @param proprietario     Proprietario do Veiculo (Carro)
     * @param placa            Placa do Veiculo (Carro) 
     * @param numChassi        Numero do Chassi do Veiculo (Carro)
     * @param marca            Marca do Veiculo (Carro)
     * @param modelo           Modelo do Veiculo (Carro)
     * @param tipoCombustivel  Tipo de Combustivel do Veiculo (Gasolina, Etanol, Gas, Diesel, Alcool)
     * @param anoFabricacao    Ano de fabricação do Veiculo (Carro)
     * @param valorMercado     Valor de mercado atual do Veiculo (Carro)
     * @param qtdPortas        Quantidade de portas do Veiculo (Carro)
     * @param numAssentos      Numero de assentos do Veiculo (Carro)
     * 
     * */
    public Carro(Proprietario proprietario,
                        String placa,
                        String numChassi,
                        String marca,
                        String modelo,
                        int anoFabricacao,
                        double valorMercado,
                        int qtdPortas, 
                        int numAssentos){
        super(proprietario,placa,numChassi,marca,modelo,anoFabricacao,valorMercado);
        this.qtdPortas = qtdPortas;
        this.numAssentos = numAssentos;
    }
    
    /**Metodo que retorna o numero de portas do carro
     * @return numeroDePortas*/
    public int getQtdPortas(){
        return qtdPortas;
    }
    
    /**Metodo que retorna o numero de asssentos do carro
     * @return NumeroDeAssentos*/
    public int getNumAssentos(){
        return numAssentos;
    }
    
    /**Metodo que aceita a visita do Padrão Visitor para conhecer informações de 
     * como calcular o IPVA de Carro     
     * @param visita Estabelece um aceite de visita para obter informações Carro
     */
}
