package br.senai.sc.livros.model.factory;

import br.senai.sc.livros.model.dao.PessoaDAO;
import br.senai.sc.livros.model.entities.*;

public class PessoaFactory {


    public Pessoa getPessoa(String cpf, String nome, String sobrenome, String email, String senha, String genero, Integer tipoAcesso){
        switch (tipoAcesso){
            case 1 -> {
                return new Autor(cpf, nome, sobrenome,email, new GenderFactory().getGeneroByName(genero), senha);
            }
            case 2 -> {
                return new Revisor(cpf, nome, sobrenome,email, new GenderFactory().getGeneroByName(genero), senha);
            }
            case 3 -> {
                return new Diretor(cpf, nome, sobrenome,email, new GenderFactory().getGeneroByName(genero), senha);
            }
        }
        return null;
    }

}
