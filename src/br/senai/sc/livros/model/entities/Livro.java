package br.senai.sc.livros.model.entities;

import java.util.ArrayList;

public class Livro {
    private Autor autor;
    private Editora editora;
    private String titulo;
    private Status status;
    //    1 - Aprovado
//    2 - Em revisão
//    3 - Aguardando revisão
//    4 - Aguardando edição
//    5 - Reprovado
//    6 - Publicado
    private int qntdPaginas;
    private double paginasRevisadas = 0;
    private int ISBN;
    private Pessoa revisor;

    static ArrayList<Livro> listaLivros = new ArrayList<Livro>();

    public Livro() {
    }

    ;

    public Livro(Autor autor, String titulo, Status status, int qntdPaginas, int ISBN) {
        this.autor = autor;
        this.titulo = titulo;
        this.status = status;
        this.qntdPaginas = qntdPaginas;
        this.ISBN = ISBN;
    }

    public static Livro cadastrar(String titulo, int isbn, int qtdPag, Autor autor) {
        return new Livro(autor, titulo, Status.AGUARDANDO_REVISAO, qtdPag, isbn);
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getQntdPaginas() {
        return qntdPaginas;
    }

    public void setQntdPaginas(int qntdPaginas) {
        this.qntdPaginas = qntdPaginas;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public double getPaginasRevisadas() {
        return paginasRevisadas;
    }

    public void setPaginasRevisadas(double paginasRevisadas) {
        this.paginasRevisadas = paginasRevisadas;
    }

    public Pessoa getRevisor() {
        return revisor;
    }

    public void setRevisor(Revisor revisor) {
        this.revisor = revisor;
    }

    @Override
    public String toString() {
        String editoraNome;
        String revisorNome;

        if (getEditora() == null) {
            editoraNome = "Livro não publicado";
        } else {
            editoraNome = editora.getNome();
        }
        if(getRevisor() == null){
            revisorNome = "Sem revisor responsável";
        } else {
            revisorNome = getRevisor().getNome();
        }

        return "Livro{" +
                "autor=" + autor.getNome() +
                ", editora=" + editoraNome +
                ", titulo='" + titulo + '\'' +
                ", status=" + status.getNome() +
                ", qntdPaginas=" + qntdPaginas +
                ", ISBN=" + ISBN +
                ", RevisorResponsável: " + revisorNome +
                ", % Páginas revisadas: " + paginasRevisadas +
                '}';
    }
}
