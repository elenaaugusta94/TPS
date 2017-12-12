/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacoteGlobal;

import java.io.Serializable;

/**
 * Classe responsável por representar o Endereco de uma Pessoa (Funcionario ou Proprietario)
 * 
 * @author Elena, Christian
 */
public class Endereco implements Serializable {
    
    private String logradouro;
    private int numero;
    private String bairro;
    private String cep;

    /**
     * Construtor da classe Endereco no qual necessita dos seguintes parâmetros
     * @param logradouro logradouro da residência da Pessoa (Funcionario ou Proprietario)
     * @param numero     numero da residência da Pessoa (Funcionario ou Proprietario)
     * @param bairro     bairro da residência da Pessoa (Funcionario ou Proprietario)
     * @param cep        cep da residência da Pessoa (Funcionario ou Proprietario)
     */
    public Endereco(String logradouro, int numero, String bairro, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
    }
    /**Metodo que retorna o logradouro da residência da Pessoa (Funcionario ou Proprietario)
     * @return logradouro
     */
    
    public String getLogradouro() {
        return logradouro;
    }
    /**Metodo que retorna o numero da residência da Pessoa (Funcionario ou Proprietario)
     * @return numero
     */
    public int getNumero() {
        return numero;
    }
    /**Metodo que retorna o bairro da residência da Pessoa (Funcionario ou Proprietario)
     * @return bairro
     */
    public String getBairro() {
        return bairro;
    }
    /**Metodo que retorna o cep da residência da Pessoa (Funcionario ou Proprietario)
     * @return cep
     */
    public String getCep() {
        return cep;
    }
    
    
    
}
