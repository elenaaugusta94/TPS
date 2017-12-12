package pacoteGlobal;

import java.io.Serializable;

import cluster1.Proprietario;

/**
 * Classe responsável por representar um veículo do tipo Caminhão.
 * Essa classe herda os atributos da classe Veiculo e especifica 
 * características com relação à sua capacidade e numero de eixos.
 * @author Elena, Christian
 */
public class Caminhao extends Veiculo implements Serializable{
    
    private int capacidade, numEixos;
    
    /**
     * Construtor da classe Caminhao no qual necessita dos seguintes parâmetros
     * 
     * @param proprietario     Proprietario do Veiculo (Caminhao)
     * @param placa            Placa do Veiculo (Caminhao) 
     * @param numChassi        Numero do Chassi do Veiculo (Caminhao)
     * @param marca            Marca do Veiculo (Caminhao)
     * @param modelo           Modelo do Veiculo (Caminhao)
     * @param tipoCombustivel  Tipo de Combustivel do Veiculo (Gasolina, Etanol, Gas, Diesel, Alcool)
     * @param anoFabricacao    Ano de fabricação do Veiculo (Caminhao)
     * @param valorMercado     Valor de mercado atual do Veiculo (Caminhao)
     * @param capacidade       Capacidade em toneladas do Veiculo (Caminhao)
     * @param numEixos         Numero de eixos do Caminhao
     */
    public Caminhao(int numEixos, Proprietario proprietario,
                        String placa,
                        String numChassi,
                        String marca,
                        String modelo,
                        int anoFabricacao,
                        double valorMercado,
                        int capacidade){
        super(proprietario,placa,numChassi,marca,modelo,anoFabricacao,valorMercado);
        this.capacidade = capacidade;
        this.numEixos = numEixos;
    }
    
    /**Metodo que retorna a capacidade, em toneladas, do caminhao
     * @return capacidadoDoCaminhao*/
    public int getCapacidade(){
    	GerenciadorVeiculo gerenciadorVeiculo = new GerenciadorVeiculo();
        return capacidade;
    }
    
    /**Metodo que retorna o numero de eixos do caminhao
     * @return numeroDeEixos*/
    public int getNumEixos(){
        return numEixos;
    }
    
    /**Metodo que aceita a visita do Padrão Visitor para conhecer informações de 
     * como calcular o IPVA de Caminhao.
     * @param visita Estabelece um aceite de visita para obter informações Caminhao
     */
 }
