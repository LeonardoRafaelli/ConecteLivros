package br.senai.sc.livros.model.entities;

import java.util.ArrayList;

public class Pessoa {
    private String CPF, nome, sobrenome, email, senha;
    private Genero genero;

    static ArrayList<Pessoa> listaPessoas = new ArrayList<>();

    public Pessoa(String CPF, String nome, String sobrenome, String email, Genero genero, String senha) {
        this.CPF = CPF;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.genero = genero;
        this.senha = senha;
    }


    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Pessoa validaLogin(String senha){
        if(this.getSenha().equals(senha)){
            return this;
        };
        throw new RuntimeException("Senha incorreta!");
    }


    @Override
    public String toString() {
        return nome + " " + sobrenome;
    }

    public static Pessoa cadastrar(String nome, String sobrenome, String email, Genero genero, String senha, String cpf, String confSenha){
        if(senha.equals(confSenha)){
            if(email.contains("@")){
                return new Autor(cpf,nome,sobrenome,email,genero,senha);
            } else {
                throw new RuntimeException("Email inválido!");
            }
        } else {
            throw new RuntimeException("Senhas não conferem!");
        }
    };
}
