package br.senai.sc.livros.model.dao;

import br.senai.sc.livros.model.entities.*;

import java.util.ArrayList;

public class LivroDAO {
    private static ArrayList<Livro> listaLivros = new ArrayList<>();

//    Autor autor, String titulo, Status status, int qntdPaginas, int ISBN


    static{

        Pessoa pessoa = new PessoaDAO().selecionarPorEmail("autor@");

        listaLivros.add(new Livro(new Autor("123", "Bernadete", "#@!", "123@321", Genero.FEMININO, "123"),
                "O fogo", Status.AGUARDANDO_REVISAO, 568, 1234));
        listaLivros.add(new Livro((Autor)pessoa,
                "A água", Status.AGUARDANDO_EDICAO, 348, 2345));
        listaLivros.add(new Livro(new Autor("123", "Bernadete", "#@!", "autor@", Genero.FEMININO, "123"),
                "A pedra", Status.APROVADO, 346, 2542));
        listaLivros.add(new Livro(new Autor("123", "Bernadete", "#@!", "123@321", Genero.FEMININO, "123"),
                "O Henrique", Status.EM_REVISAO, 467, 4367));
        listaLivros.add(new Livro(new Autor("123", "Bernadete", "#@!", "123@321", Genero.FEMININO, "123"),
                "A Camilly", Status.REPROVADO, 346, 2542));
        listaLivros.add(new Livro(new Autor("123", "Bernadete", "#@!", "123@321", Genero.FEMININO, "123"),
                "O Impiedoso Romárinho", Status.PUBLICADO, 467, 4367));
    }


    public void inserir(Livro livro){
        listaLivros.add(livro);
    }

    public void remover(Livro livro){
        listaLivros.remove(livro);
    }

    public Livro selecionar(int isbn){
        for(Livro livro : listaLivros){
            if(livro.getISBN() == isbn){
                return livro;
            }
        };
        return null;
    }

    public void atualizar(int isbn, Livro livroAtualizado){
        int i = listaLivros.indexOf(selecionar(isbn));
        listaLivros.set(i, livroAtualizado);
    }

    public ArrayList<Livro> getAllLivros(){
      return listaLivros;
    };

    public ArrayList<Livro> selecionarPorAutor(Pessoa pessoa){
        ArrayList<Livro> livrosAutor = new ArrayList<>();
        for(Livro livro : getAllLivros()){
            if(livro.getAutor().equals(pessoa)){
                livrosAutor.add(livro);
            }
        }
        return livrosAutor;
    }

    public ArrayList<Livro> selecionarPorStatus(Status status){
        ArrayList<Livro> livrosStatus = new ArrayList<>();
        for(Livro livro : getAllLivros()){
            if(livro.getStatus().equals(status)){
                livrosStatus.add(livro);
            }
        }
        return livrosStatus;
    }
    public ArrayList<Livro> selecionarAtividadesAutor(Pessoa pessoa){
        ArrayList<Livro> livrosAutor = new ArrayList<>();
        for(Livro livro : getAllLivros()){
            if(livro.getAutor() == pessoa && livro.getStatus().equals(Status.AGUARDANDO_EDICAO)){
                livrosAutor.add(livro);
            };
        }
        return livrosAutor;
    }




}
