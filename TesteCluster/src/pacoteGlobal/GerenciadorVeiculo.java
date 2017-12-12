/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacoteGlobal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import cluster1.Proprietario;


/**
 * Classe responsável por controlar os objetos do tipo Veiculo (Carro, Moto ou Caminhão).
 * Essa classe possui uma lista de veiculos para representar tais entidades.
 * @author Elena, Christian
 */
public class GerenciadorVeiculo implements Serializable {
    private  ArrayList<Veiculo> listaVeiculos = new ArrayList();
    
    /**
     * 
     */
    public GerenciadorVeiculo() {
        this.listaVeiculos = listaVeiculos;
    }
    /** Método responsável por cadastrar um Veiculo do tipo Carro
     * 
     * @param proprietario     Proprietario do Veiculo (Carro, Caminhao ou Moto)
     * @param placa            Placa do Veiculo (Carro, Caminhao ou Moto) 
     * @param numChassi        Numero do Chassi do Veiculo (Carro, Caminhao ou Moto)
     * @param marca            Marca do Veiculo (Carro, Caminhao ou Moto)
     * @param modelo           Modelo do Veiculo (Carro, Caminhao ou Moto)
     * @param tipoCombustivel  Tipo de Combustivel do Veiculo (Gasolina, Etanol, Gas, Diesel)
     * @param anoFabricacao    Ano de fabricação do Veiculo (Carro, Caminhao ou Moto)
     * @param valorMercado     Valor de mercado atual do Veiculo (Carro, Caminhao ou Moto)
     * @param qtdPortas        Quantidade de portas do Veiculo (Carro)
     * @param numAssentos      Numero de assentos do Veiculo (Carro)
     * @throws Exception       Exeção lançada caso um Carro já tenha sido 
     *                         cadastrado
     */
    public void cadastrarVeiculo(Proprietario proprietario, 
                        String placa,
                        String numChassi,
                        String marca,
                        String modelo,
                        int anoFabricacao,
                        double valorMercado,
                        int qtdPortas, 
                        int numAssentos) throws Exception{
        Carro c = new Carro (proprietario,placa,numChassi,marca,modelo,
                                           anoFabricacao, valorMercado, 
                                            qtdPortas, numAssentos );
        if(!consultarVeiculoRegistrado(placa, numChassi))
            this.listaVeiculos.add(c);
    }
    /**
     * Cadastro de Veiculo do tipo Moto
     * @param proprietario     Proprietario do Veiculo (Moto)
     * @param placa            Placa do Veiculo (Moto) 
     * @param numChassi        Numero do Chassi do Veiculo (Moto)
     * @param marca            Marca do Veiculo (Moto)
     * @param modelo           Modelo do Veiculo (Moto)
     * @param tipoCombustivel  Tipo de Combustivel do Veiculo (Gasolina, Etanol, Gas, Diesel, Alcool)
     * @param anoFabricacao    Ano de fabricação do Veiculo (Moto)
     * @param valorMercado     Valor de mercado atual do Veiculo (Moto)
     * @param cilindradas      Quantidade de cilindradas do Veiculo (Moto)
     * @throws Exception       Exeção lançada caso uma Moto já tenha sido 
     *                         cadastrada
     */
    public void cadastrarVeiculo(Proprietario proprietario, 
                        String placa,
                        String numChassi,
                        String marca,
                        String modelo,
                        int anoFabricacao,
                        double valorMercado,
                        int cilindradas) throws Exception{
        Moto moto = new Moto (proprietario,placa,numChassi,marca,modelo,
                                          anoFabricacao, valorMercado, 
                                           cilindradas);
        if(!consultarVeiculoRegistrado(placa, numChassi))
            this.listaVeiculos.add(moto);
    }
    /**   
     * Cadastro de Veiculo do tipo Caminhão     * 
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
     * @throws Exception       Exeção lançada caso um Caminhão já tenha sido 
     *                         cadastrada
     */
    public void cadastrarVeiculo(int numEixos, Proprietario proprietario, 
                        String placa,
                        String numChassi,
                        String marca,
                        String modelo,
                        int anoFabricacao,
                        double valorMercado,
                        int capacidade) throws Exception{
        Caminhao caminhao = new Caminhao (numEixos, proprietario,placa,numChassi,marca,modelo,
                                           anoFabricacao, valorMercado, 
                                           capacidade);
        if(!consultarVeiculoRegistrado(placa, numChassi))
            this.listaVeiculos.add(caminhao);
    }
    /**
     * Metodo que consulta se um Veiculo foi cadastrado (registrado)
     * @param placa         placa do Veiculo   
     * @param chassi        chassi do Veiculo
     * @return              True caso o Veiculo tenha sido cadastrado anteriormente 
     *                      False caso contrário
     * @throws Exception    Exceção caso um Veiculo tenha sido cadastrado anteriormente
     */
    public boolean consultarVeiculoRegistrado(String placa, String chassi) throws Exception{
        boolean erro = false;
        for(Veiculo v : this.listaVeiculos){
           if(v.getPlaca().equals(placa)){
               erro = true;
              // throw new PlacaIgualException("Nao foi possivel adicionar o veiculo - Placa ja registrada");             
           } 
           if(v.getPlaca().equals(chassi)){
               erro = true;
               throw new Exception("Nao foi possivel adicionar o veiculo - Chassi ja registrado");
           }
        
        }        
        return erro;
    }
    /**
     * Consultar um determinado Veiculo por sua placa
     * @param placa placa do Veiculo
     * @return um Veiculo encontrado
     */
    public Veiculo consultarVeiculoPorPlaca(String placa){
        
        for(Veiculo v: this.listaVeiculos){
            if(v.getPlaca().equals(placa)){
                return v;
            }
        }
        
        return null;        
        
        
    }
  
    /**
     * Método responsável por retornar uma lista de Veiculos
     * @return listaDeVeiculos
     */
    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
    /**
     * Método responsável por atualizar uma lista de Veiculo
     * @param listaVeiculos a ser atualizada
     */
    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
    /**
     * Método responśavel por consultar uma multa dado a placa do Veiculo
     * @param placa do Veiculo
     * @return listaDeMultasDoVeiculo
     */
    
    
    
    /**
     * Método responsável por retornar o valor do IPVA de um dado Veiculo (Carro, Moto, Caminhao).
     * 
     * @param v Veiculo a ser consultado o IPVA
     * @return o valor do IPVA de um Veiculo
     */
    
    
    public void lerDados(){
        try {
            
            File arqVeiculos = new File("veiculos.dat");

            if ( arqVeiculos.exists()) { 

                FileInputStream arquivo1 = new FileInputStream("veiculos.dat");
                ObjectInputStream ler1 = new ObjectInputStream(arquivo1);
                
                if ( arquivo1.available() != 0 ) {
                        listaVeiculos = (ArrayList<Veiculo>) ler1.readObject();
                        ler1.close();
                        arquivo1.close();
                }
            }

        }
        catch( Exception e ) {			
            e.printStackTrace( );
        }
    }
    public void gravarDados(){
        try {
            FileOutputStream arq1 = new FileOutputStream("veiculos.dat");
            
            ObjectOutputStream grv1 = new ObjectOutputStream(arq1);
                   
            grv1.writeObject(listaVeiculos);
            grv1.flush();
            grv1.close();
            arq1.flush();
            arq1.close();
            
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    
    

