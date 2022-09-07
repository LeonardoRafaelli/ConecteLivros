package br.senai.sc.livros.model.dao;

import br.senai.sc.livros.model.entities.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PessoaDAO {
    private static final Set<Pessoa> listaPessoas = new HashSet<>();

    static{
        listaPessoas.add(new Autor("12435678", "autor", "Rafaellizin",
                "autor@", Genero.MASCULINO, "123"));
        listaPessoas.add(new Revisor("12435678", "revisor", "Rafaellizin",
                "revisor@", Genero.MASCULINO, "123"));
        listaPessoas.add(new Revisor("12435678", "Revisor2", "Rafaellizin",
                "revisor2@", Genero.MASCULINO, "123"));
        listaPessoas.add(new Diretor("12435678", "diretor", "Rafaellizin",
                "diretor@", Genero.MASCULINO, "123"));
    }

    public void inserir(Pessoa pessoa){
        listaPessoas.add(pessoa);
    }

    public void remover(Pessoa pessoa){
        listaPessoas.remove(pessoa);
    }

    public Pessoa selecionarPorCPF(String CPF){
        for(Pessoa pessoa : listaPessoas){
            if(pessoa.getCPF().equals(CPF)) return pessoa;
        }
        throw new RuntimeException("CPF não encontrado!");
    }
    public Pessoa selecionarPorEmail(String email){
        for(Pessoa pessoa : listaPessoas){
            if(pessoa.getEmail().equals(email)){
                return pessoa;
            }
        }
        throw new RuntimeException("Email não encontrado!");
    }
}
