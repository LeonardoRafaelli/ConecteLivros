package br.senai.sc.livros.model.dao;

import br.senai.sc.livros.model.factory.FactoryConnection;
import br.senai.sc.livros.model.entities.*;
import br.senai.sc.livros.model.factory.GenderFactory;
import br.senai.sc.livros.model.factory.PessoaFactory;
import br.senai.sc.livros.model.factory.StatusFactory;
import br.senai.sc.livros.view.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class LivroDAO {

    private static Collection<Livro> listaLivros = new HashSet<>();

    private Connection conn;

    public LivroDAO() {
        this.conn = new FactoryConnection().connectDB();
    }

    public boolean inserir(Livro livro) throws SQLException {
        String sqlQuery = "select * from livros";

        try (PreparedStatement pstm = conn.prepareStatement(sqlQuery);) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                if (rs.getInt("isbn") == livro.getISBN()) {
                    return false;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro na preparação em inserir pessoa: " + e);
        }

        String sqlCommand = "insert into livros (isbn, titulo, status, qtdPaginas, AUTOR_cpf) values (?,?,?,?,?);";
        try (PreparedStatement pstm = conn.prepareStatement(sqlCommand);) {
            pstm.setInt(1, livro.getISBN());
            pstm.setString(2, livro.getTitulo());
            pstm.setInt(3, livro.getStatus().ordinal() + 1);
            pstm.setInt(4, livro.getQntdPaginas());
            pstm.setString(5, livro.getAutor().getCPF());
            pstm.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro na preparação em inserir pessoa: " + e);
        }

        return true;
    }

    public void remover(Livro livro) {
        listaLivros.remove(livro);
    }

    public Livro selecionar(int isbn) {
        for (Livro livro : listaLivros) {
            if (livro.getISBN() == isbn) {
                return livro;
            }
        }
        ;
        return null;
    }

    public void atualizarStatus(Livro livroAtualizado) {
    String sqlCommand = "update livros set status = ? where isbn = ?;";

    try(PreparedStatement pstm = conn.prepareStatement(sqlCommand)){
        pstm.setInt(1, livroAtualizado.getStatus().ordinal()+1);
        pstm.setInt(2, livroAtualizado.getISBN());
        try {
            pstm.execute();
        }catch (Exception err){
            throw new RuntimeException("Erro na execução de atualização de status: " + err.getMessage());
        }
    }catch (Exception err){
        throw new RuntimeException("Erro na preparação de atualização de status: " + err.getMessage());
    }

    }

    public void adicionarRevisor(Livro livroAtualizado) {
        String sqlCommand = "update livros set REVISOR_cpf = ? where isbn = ?;";

        try(PreparedStatement pstm = conn.prepareStatement(sqlCommand)){
            pstm.setString(1, livroAtualizado.getRevisor().getCPF());
            pstm.setInt(2, livroAtualizado.getISBN());
            try{
                pstm.execute();
            } catch (Exception err){
                throw new RuntimeException("Erro na execução de adicionar revisor");
            }

        } catch (Exception err){
            throw new RuntimeException("Erro na preparação adicionar revisor.   - "+ err.getMessage());
        }
    }

    public Collection<Livro> getAllLivros() {
        String sqlQuery = "select * from viewLivroAutor";
        try (PreparedStatement pstm = conn.prepareStatement(sqlQuery)) {
            ResultSet rs = pstm.executeQuery();

            ArrayList<Livro> listaLivros = new ArrayList<>();
            while (rs.next()) {
                Livro livroBuscado = new Livro(
                        new Autor(rs.getString("cpf"),
                                rs.getString("nome"),
                                rs.getString("sobrenome"),
                                rs.getString("email"),
                                new GenderFactory().getGeneroByName(rs.getString("genero")),
                                rs.getString("senha")
                        ),
                        rs.getString("titulo"),
                        new StatusFactory().buscarStatusCorreto(rs.getString("status")),
                        rs.getInt("qtdPaginas"),
                        rs.getInt("isbn")
                );
                String revisorCPF = rs.getString("REVISOR_cpf");
                if(revisorCPF != null){
                    Revisor revisor = new PessoaDAO().buscarRevisor(revisorCPF);
                    livroBuscado.setRevisor(revisor);
                }
                listaLivros.add(livroBuscado);
            }
            return Collections.unmodifiableCollection(listaLivros);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todos os livros: " + e.getMessage());
        }
    }



    ;

    public Collection<Livro> selecionarPorAutor(Pessoa pessoa) throws SQLException {
        String sqlCommand = "select * from livros";
        try (PreparedStatement pstm = conn.prepareStatement(sqlCommand)) {
            ResultSet rs = pstm.executeQuery();
            Collection<Livro> livrosAutor = new ArrayList<>();
            while (rs.next()) {
                String autorCPF = rs.getString("AUTOR_cpf");
                if (autorCPF.equals(Menu.getUsuario().getCPF())) {
                    livrosAutor.add(
                            new Livro(
                                    (Autor) Menu.getUsuario(),
                                    rs.getString("titulo"),
                                    new StatusFactory().buscarStatusCorreto(rs.getString("status")),
                                    rs.getInt("qtdPaginas"),
                                    rs.getInt("isbn")
                            )
                    );
                }
            }
            return livrosAutor;
        } catch (Exception e) {
            throw new RuntimeException("Erro na preparação em selecionar por autor" + e);
        }

    }


    public Collection<Livro> selecionarPorStatus(Status status) {

        Collection<Livro> livrosStatus = new ArrayList<>();
        for (Livro livro : getAllLivros()) {
            if (livro.getStatus().equals(status)) {
                livrosStatus.add(livro);
            }
        }
        return livrosStatus;
    }

    public Collection<Livro> selecionarAtividadesAutor(Pessoa pessoa) {
        Collection<Livro> livrosAutor = new ArrayList<>();
        for (Livro livro : getAllLivros()) {
            if (livro.getAutor().getCPF().equals(pessoa.getCPF()) && livro.getStatus().equals(Status.AGUARDANDO_EDICAO)) {
                livrosAutor.add(livro);
            }
            ;
        }
        return livrosAutor;
    }

}
