/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacoteGlobal;

import java.io.Serializable;

/**
 * Classe responsável por representar o Funcionario do Detran. 
 * Essa classe herda os atributos da classe Pessoa e especifica 
 * características com relação à seu identificador e senha.
 * 
 * @author Elena, Christian
 */
public class Funcionario extends Pessoa implements Serializable{
    
    private int identificador;
    private String senha;
    /**
     * Construtor da classe Funcionario no qual necessita dos seguintes parâmetros
     * @param nome          nome do Funcionario
     * @param endereco      endereco do Funcionario
     * @param senha         senha do Funcionario
     * @param identificador identificador do Funcionario
     */
    public Funcionario(String nome, Endereco endereco, String senha, int identificador) {
        super(nome, endereco);
        this.identificador = identificador;
        this.senha = senha;
    }
    /**
     * Método que retorna o identificador do Funcionario
     * @return identificador 
     */
    public int getIdentificador() {
        return identificador;
    }
    /**
     * Método que retorna a senha do Funcionario
     * @return identificador 
     */
    public String getSenha(){
        return senha;
    }
}
