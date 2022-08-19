package br.senai.sc.livros.controller;

import br.senai.sc.livros.model.entities.*;
import br.senai.sc.livros.model.service.LivroService;
import br.senai.sc.livros.view.Menu;

import java.util.ArrayList;
//Objeto que vai intermediar a view com a classe livros;
//Livro livro = new Livro(new Autor("123","Leo","Rafaelli","leonardo@gmail.com","masc","123"), "Fourteen Ways To Do It", 3, 7000, 1243578);
//LivrosView livroView = new LivroView();

public class LivrosController {

    Livro model = new Livro();

    public Autor getAutor() {
        return model.getAutor();
    }

    public void setAutor(Autor autor) {
        model.setAutor(autor);
    }

    public Editora getEditora() {
        return model.getEditora();
    }

    public void setEditora(Editora editora) {
        model.setEditora(editora);
    }

    public String getTitulo() {
        return model.getTitulo();
    }

    public void setTitulo(String titulo) {
        model.setTitulo(titulo);
    }

    public Status getStatus() {
        return model.getStatus();
    }

    public void setStatus(Status status) {
        model.setStatus(status);
    }

    public int getQntdPaginas() {
        return model.getQntdPaginas();
    }

    public void setQntdPaginas(int qntdPaginas) {
        model.setQntdPaginas(qntdPaginas);
    }

    public int getISBN() {
        return model.getISBN();
    }

    public void setISBN(int ISBN) {
        model.setISBN(ISBN);
    }

    public double getPaginasRevisadas() {
        return model.getPaginasRevisadas();
    }

    public void setPaginasRevisadas(double paginasRevisadas) {
        model.setPaginasRevisadas(paginasRevisadas);
    }

    public void cadastrar(String titulo, String isbn, String qtdPag, Pessoa autor){
       new LivroService().inserir(Livro.cadastrar(titulo, Integer.parseInt(isbn), Integer.parseInt(qtdPag), (Autor)autor));
    }

    public ArrayList<Livro> getAllLivros(){
        return new LivroService().getAllLivros();
    };

    public ArrayList<Livro> selecionarPorAutor(){
        return new LivroService().selecionarPorAutor(Menu.getUsuario());
    }

    public ArrayList<Livro> listarAtividades(){
        return new LivroService().listarAtividades(Menu.getUsuario());
    }

    public Livro selecionarPorISBN(int isbn){
        return new LivroService().selecionarPorISBN(isbn);
    }

    public void atualizarStatus(Livro livro, Status status){
        new LivroService().atualizarStatus(livro, status);
    }

    public void adicionarRevisor(Livro livro, Pessoa usuario){
        new LivroService().adicionarRevisor(livro, (Revisor) usuario);
    }

//    public ArrayList<Livro> selecionarPorStatus(){
//        return new LivroService().selecionarPorStatus();
//    }

}
