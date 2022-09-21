package br.senai.sc.livros.model.service;

import br.senai.sc.livros.model.dao.LivroDAO;
import br.senai.sc.livros.model.entities.*;
import br.senai.sc.livros.view.Menu;

import java.sql.SQLException;
import java.util.Collection;

public class LivroService {

    public boolean inserir(Livro livro) {
        try {
            return new LivroDAO().inserir(livro);
        } catch (SQLException err) {
            System.out.println("Erro ao inserir livro: " + err.getMessage());
            return false;
        }

    }

    public void remover(Livro livro) {
        new LivroDAO().remover(livro);
    }

    public Livro selecionar(int isbn) {
        return new LivroDAO().selecionar(isbn);
    }

    public Collection<Livro> getAllLivros() {
        Pessoa usuario = Menu.getUsuario();

        if (usuario instanceof Autor) {
            return selecionarPorAutor(usuario);
        } else if (usuario instanceof Revisor) {
            Collection<Livro> livros = new LivroDAO().selecionarPorStatus(Status.AGUARDANDO_REVISAO);
            livros.addAll(new LivroDAO().selecionarPorStatus(Status.EM_REVISAO));
            return livros;
        } else {
            return new LivroDAO().getAllLivros();
        }

    }

    public Collection<Livro> selecionarPorAutor(Pessoa pessoa) {
        try {
            return new LivroDAO().selecionarPorAutor(pessoa);
        } catch (SQLException err) {
            System.out.println("Erro no livroService(selecionarPorAutor): " + err.getMessage());
            return null;
        }
    }

    public Collection<Livro> selecionarPorStatus(Status status) {
        return new LivroDAO().selecionarPorStatus(status);
    }

    public Collection<Livro> selecionarAtividadesAutor(Pessoa pessoa) {
        return new LivroDAO().selecionarAtividadesAutor(pessoa);
    }

    public Collection<Livro> listarAtividades(Pessoa pessoa) {
        if (pessoa instanceof Autor) {
            return selecionarAtividadesAutor(pessoa);
        } else if (pessoa instanceof Revisor) {
            Collection<Livro> livros = new LivroDAO().selecionarPorStatus(Status.AGUARDANDO_REVISAO);
            for (Livro livro : new LivroDAO().selecionarPorStatus(Status.EM_REVISAO)) {
                if (livro.getRevisor() == pessoa) {
                    livros.add(livro);
                }
            }
            return livros;
        } else {
            return selecionarPorStatus(Status.APROVADO);
        }
    }

    public Livro selecionarPorISBN(int isbn) {
        for (Livro livro : getAllLivros()) {
            if (livro.getISBN() == isbn) {
                return livro;
            }
        }
        throw new RuntimeException("ISBN não encontrado!");
    }

    public void atualizarStatus(Livro livro, Status status, int tipoAtualizacao) {
        livro.setStatus(status);
        new LivroDAO().atualizarLivro(livro.getISBN(), livro, tipoAtualizacao);
    }

    public void adicionarRevisor(Livro livro, Revisor revisor, int tipoAtualizacao) {
        livro.setRevisor(revisor);
        new LivroDAO().atualizarLivro(livro.getISBN(), livro, tipoAtualizacao);
    }
}
