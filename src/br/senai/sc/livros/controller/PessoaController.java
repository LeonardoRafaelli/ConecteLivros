package br.senai.sc.livros.controller;

import br.senai.sc.livros.model.entities.Genero;
import br.senai.sc.livros.model.entities.Pessoa;
import br.senai.sc.livros.model.service.PessoaService;

public class PessoaController {
    Pessoa pessoa;

    public Pessoa validaLogin(String email, String senha) {
        PessoaService service = new PessoaService();
        pessoa = service.selecionarPorEmail(email);
        return pessoa.validaLogin(senha);
    }

    public void cadastrar(String nome, String sobrenome, String email, Object genero, String senha, String cpf, String confSenha, int tipo) {
        PessoaService pessoaService = new PessoaService();
        Pessoa pessoa = Pessoa.cadastrar(nome, sobrenome, email, (Genero)genero, senha, cpf, confSenha);
        pessoaService.inserir(pessoa, tipo);

    }
}
