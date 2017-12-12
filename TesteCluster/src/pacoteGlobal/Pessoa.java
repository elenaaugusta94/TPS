/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacoteGlobal;

import java.io.Serializable;

/**
 * Classe responsável por representar uma Pessoa.
 * 
 * @author Elena, Christian
 */
public class Pessoa implements Serializable{
    protected String nome;
    protected Endereco endereco;
    
   /**
    * Construtor da classe Pessoa no qual necessita dos seguintes parâmetros
    * @param nome       nome da Pessoa (Funcionario ou Proprietario)
    * @param endereco   endereco da Pessoa (Funcionario ou Proprietario)
    */

    public Pessoa(String nome, Endereco endereco) {
        this.nome = nome;
        this.endereco = endereco;
        
    }
    /**
     * Método responsável por cadastrar o Endereço de uma Pessoa no sistema Detran
     * @param logradouro logradouro da residência da Pessoa (Funcionario ou Proprietario)
     * @param numero     numero da residência da Pessoa (Funcionario ou Proprietario)
     * @param bairro     bairro da residência da Pessoa (Funcionario ou Proprietario)
     * @param cep        cep da residência da Pessoa (Funcionario ou Proprietario)
     */
    public void cadastrarEndereco(String logradouro, int numero, String bairro, String cep){
        Endereco endereco = new Endereco(logradouro, numero, bairro, cep);
    }
    /**
     * Método responsável por retornar o nome de uma Pessoa
     * @return nomePessoa
     */
    public String getNome() {
        return nome;
    }


   

    
}
