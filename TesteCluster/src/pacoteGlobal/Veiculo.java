/**
 * Teste
 */
package pacoteGlobal;

import java.util.*;

import cluster1.Proprietario;

import java.io.Serializable;

/**Classe responsável por representar um Veiculo*/
public abstract class Veiculo {
    
    protected String numChassi, marca, modelo,  placa;
    protected int anoFabricacao; 
    protected double valorMercado;
    protected Proprietario proprietario;
  //  protected ArrayList<Multa> multas ;

    /**Construtor da classe Veiculo no qual necessita dos seguintes parâmetros
     * 
     * @param proprietario     Proprietario do Veiculo (Carro, Caminhao ou Moto)
     * @param placa            Placa do Veiculo (Carro, Caminhao ou Moto) 
     * @param numChassi        Numero do Chassi do Veiculo (Carro, Caminhao ou Moto)
     * @param marca            Marca do Veiculo (Carro, Caminhao ou Moto)
     * @param modelo           Modelo do Veiculo (Carro, Caminhao ou Moto)
     * @param tipoCombustivel  Tipo de Combustivel do Veiculo (Gasolina, Etanol, Gas, Diesel, Alcool)
     * @param anoFabricacao    Ano de fabricação do Veiculo (Carro, Caminhao ou Moto)
     * @param valorMercado     Valor de mercado atual do Veiculo (Carro, Caminhao ou Moto)
     */  
    public Veiculo(Proprietario proprietario,
                   String placa,
                   String numChassi,
                   String marca,
                   String modelo,
                   int anoFabricacao,
                   double valorMercado){
        
        this.proprietario = proprietario;
        this.placa = placa;
        this.numChassi = numChassi;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.valorMercado = valorMercado;
    }
    
    /**
     * Método abstrato do Padrão de Projeto Visitor que deve ser implementado 
     * pelas classes filhas, a fim de obter informações para o cálculo do IPVA
     * de cada entidade.
     * @param visita representa uma visita às subclasses filhas
     */
 
    
    /**
     * Método que retorna o Proprietario do Veiculo
     * @return proprietarioVeiculo
     */
    public Proprietario getProprietario(){
        return proprietario;
    }
    
    /**
     * Método que retorna a Placa do Veiculo
     * @return placaVeiculo
     */
    public String getPlaca(){
        return placa;
    }
    
    /**
     * Método que retorna o Numero do Chassi do Veiculo
     * @return numeroChassiVeiculo
     */
    public String getNumChassi(){
        return numChassi;
    }
    
    /**
     * Método que retorna a Marca do Veiculo
     * @return marcaVeiculo
     */
    public String getMarca(){
        return marca;
    }
    
    /**
     * Método que retorna o Modelo do Veiculo
     * @return modeloVeiculo
     */
    public String getModelo(){
        return modelo;
    }
    
    /**
     * Método que retorna o Tipo do Combustivel do Veiculo
     * @return tipoDocombustivelDoVeiculo
    /**
     * Método que retorna o Ano de Fabricacao do Veiculo
     * @return anoDeFabricacaoVeiculo
     */
    public int getAnoFabricacao(){
        return anoFabricacao;
    }
    
    /**
     * Método que retorna o Valor de Mercado do Veiculo
     * @return valorDeMercadoVeiculo
     */
    public double getValorMercado(){
        return valorMercado;
    }
    
    /**
     * Método que retorna uma lista de Multas do Veiculo
     * @return multasDoVeiculo
     */
   
    /**
     * Método que atualiza o Proprietario do Veiculo
     * @param proprietario atulização do proprietario
     */
    public void setProprietario(Proprietario proprietario){
        this.proprietario = proprietario;
    }
    
    /**
     * Método que atualiza o valor de mercado do Veiculo
     * @param valorMercado atulização do valor de mercado 
     */
    public void setValorMercado(double valorMercado){
        this.valorMercado = valorMercado;
    }
    
   
  
    
}

