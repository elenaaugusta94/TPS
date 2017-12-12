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
 * Classe responsável por controlar os objetos do tipo Pessoa (Funcionario ou Proprietario).
 * Essa classe possui uma lista de pessoas para representar tais entidades.
 * @author Elena Christian
 */
public class GerenciadorPessoa implements Serializable{
    
    private ArrayList<Pessoa> listaPessoas = new ArrayList();

     
    public GerenciadorPessoa() {
    }
    
    /**
     * Método responsável por cadastrar uma Pessoa do tipo Funcionario.
     * Compreende à uma sobrecarga de método.
     * 
     * @param nome          nome do Funcionario
     * @param endereco      endereco do Funcionario
     * @param senha         senha do Funcionario
     * @param identificador identificador do Funcionario
     * @throws Exception    Exeção caso um Funcionario já tenha sido cadastrado no sistema
     */
    public void cadastrarPessoa(String nome, Endereco endereco, String senha, int identificador) throws Exception{
        
        Funcionario f = new Funcionario(nome, endereco, senha, identificador);
        
        if(!verificarPessoa(identificador))
            listaPessoas.add(f);
        
    }
    /**
     * Método responsável por cadastrar uma Pessoa do tipo Proprietario
     * @param numCarteira   numero da carteira (CNH) do Proprietario
     * @param endereco      endereco do Proprietario
     * @param nome          nome do Proprietario
     * @throws Exception    Exeção caso um Proprietario já tenha sido cadastrado no sistema
     */
    public void cadastrarPessoa(String numCarteira, Endereco endereco, String nome) throws Exception{
        
        Proprietario p = new Proprietario(numCarteira, endereco, nome);
        
        if(!verificarPessoa(numCarteira)) // verifica se uma Pessoa não foi cadastrada anteriormente
            listaPessoas.add(p);
        
    }
    /**
     * Método responsável por verificar se um Funcionario já foi cadastrado anteriormente
     * @param identificador identificador do Funcionario
     * @return retorna True se o Funcionario já foi cadastrado e False caso contrário.
     * @throws Exception Exeção lançada caso um Funcionario tenha sido cadastrado
     */
    public boolean verificarPessoa(int identificador) throws Exception{
        
        return true;
    }
    /**
     * Método responsável por verificar se um Proprietario já foi cadastrado anteriormente
     * @param numCarteira numero da carteira de habilitação do Proprietario
     * @return retorna True se o Proprietario já foi cadastrado e False caso contrário.
     * @throws Exception Exeção lançada caso um Proprietario tenha sido cadastrado 
     */
    public boolean verificarPessoa(String numCarteira) throws Exception{
        boolean erro = false;
        for(Pessoa p : listaPessoas){
            
            if(p instanceof Proprietario ){    // verifica se contém uma instância de proprietario na lista de pessoas            
                if(((Proprietario) p).getNumCarteira().equals(numCarteira)){ //verifica se o numero da carteira de tal 
                                                                              //intância de Proprieta compreende à mesma 
                                                                              //passada por parâmetro
                    erro = true;
                    //throw new CNHIgualException("Nao foi possivel adicionar o Proprietario - CNH ja registrado");
                }
           } 
        }
        return erro;
   }
    /**
     * Método que retorna uma Pessoa (Proprietario) com base no numero de sua 
     * carteira
     * @param numCarteira   numero da carteira do Proprietario
     * @return              Pessoa (Proprietario) correspondente ao numero de 
     * carteira passado como parâmetro
     *   
     */
    public Pessoa getPessoa(String numCarteira) {
       
        for(Pessoa p : listaPessoas){
            
            if(p instanceof Proprietario ){                
                if(((Proprietario) p).getNumCarteira().equals(numCarteira)){
                    return p;
                }
            } 
        }
        return null;
   }
    /**
     * Método responsável por autenticar um Funcionario com base em seu
     * identificador e sua senha
     * @param identificador identificador do Funcionario
     * @param senha         senha do Funcionario
     * @return              True caso o identificador e senha 
     *                      corresponde ao um Funcionario cadastrado no sistema
     */
    public boolean autentificar(int identificador, String senha){
        
        for(Pessoa p:listaPessoas){
            if(p instanceof Funcionario){
                if((((Funcionario) p).getIdentificador() == identificador) && (((Funcionario) p).getSenha().equals(senha))){
                    return true;
                }
            }
        }
        
        return false;
    }
    /**
     * Método que retorna a lista de Pessosas
     * @return listaDEePessoas
     */
    public ArrayList<Pessoa> getListaPessoas() {
        return listaPessoas;
    }
    /**
     * Método que atualiza a lista de Pessoas
     * @param listaPessoas a ser atualizada
     */
    public void setListaPessoas(ArrayList<Pessoa> listaPessoas) {
        this.listaPessoas = listaPessoas;
    }
    
    public void lerDados(){
        try {
            
            File arqVeiculos = new File("pessoas.dat");

            if ( arqVeiculos.exists()) { 

                FileInputStream arquivo1 = new FileInputStream("pessoas.dat");
                ObjectInputStream ler1 = new ObjectInputStream(arquivo1);
                
                if ( arquivo1.available() != 0 ) {
                        listaPessoas = (ArrayList<Pessoa>)ler1.readObject();
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
            FileOutputStream arq1 = new FileOutputStream("pessoas.dat");
            
            ObjectOutputStream grv1 = new ObjectOutputStream(arq1);
                   
            grv1.writeObject(listaPessoas);
            grv1.flush();
            grv1.close();
            arq1.flush();
            arq1.close();
            
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
