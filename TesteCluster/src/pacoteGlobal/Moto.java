/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacoteGlobal;

import cluster1.Proprietario;

/**
 * Classe responsável por representar um veículo do tipo Moto.
 * Essa classe herda os atributos da classe Veiculo e especifica 
 * características com relação às suas cilindradas.
 * @author Elena, Christian
 */
public class Moto extends Veiculo {
    
    private int cilindradas;  
    
    /**
     * Construtor da classe Moto no qual necessita dos seguintes parâmetros
     * @param proprietario     Proprietario do Veiculo (Moto)
     * @param placa            Placa do Veiculo (Moto) 
     * @param numChassi        Numero do Chassi do Veiculo (Moto)
     * @param marca            Marca do Veiculo (Moto)
     * @param modelo           Modelo do Veiculo (Moto)
     * @param tipoCombustivel  Tipo de Combustivel do Veiculo (Gasolina, Etanol, Gas, Diesel, Alcool)
     * @param anoFabricacao    Ano de fabricação do Veiculo (Moto)
     * @param valorMercado     Valor de mercado atual do Veiculo (Moto)
     * @param cilindradas       Quantidade de cilindradas do Veiculo (Moto)
     */
    public Moto(Proprietario proprietario, String placa, String numChassi, String marca, String modelo,  int anoFabricacao, double valorMercado, int cilindradas) {
        super(proprietario, placa, numChassi, marca, modelo, anoFabricacao, valorMercado);
        this.cilindradas = cilindradas;
    }
    
    /**Retorna o numero de cilindradas da moto.    
     * @return cilindradas 
     */
    public int getCilindradas() {
        return cilindradas;
    }
    
    
     
}
