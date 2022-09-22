package br.senai.sc.livros.model.service;

import br.senai.sc.livros.model.dao.PessoaDAO;
import br.senai.sc.livros.model.entities.Pessoa;

import javax.swing.*;
import java.sql.SQLException;

public class PessoaService {
    PessoaDAO bdPessoa = new PessoaDAO();

    public void inserir(Pessoa pessoa) {
        new PessoaDAO().inserir(pessoa);
    }

    public void remover(Pessoa pessoa) {
        bdPessoa.remover(pessoa);
    }

    public Pessoa selecionarPorEmail(String email){

        return new PessoaDAO().selecionarPorEmail(email);

    }
}
