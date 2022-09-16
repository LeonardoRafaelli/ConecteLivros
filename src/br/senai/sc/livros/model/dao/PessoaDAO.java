package br.senai.sc.livros.model.dao;

import br.senai.sc.livros.model.dao.conectaBD.ConexaoBD;
import br.senai.sc.livros.model.entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PessoaDAO {
    static Connection conn;
    static PreparedStatement pstm;


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

    public void inserir(Pessoa pessoa, int tipo) throws SQLException {
        conn = ConexaoBD.connectDB();
        String sqlCommand = "INSERT INTO PESSOAS (cpf, nome, sobrenome, email, genero, senha, tipo) values (?, ?, ?, ?, ?, ?, ?);";
        pstm = conn.prepareStatement(sqlCommand);
        pstm.setString(1, pessoa.getCPF());
        pstm.setString(2, pessoa.getNome());
        pstm.setString(3, pessoa.getSobrenome());
        pstm.setString(4, pessoa.getEmail());
        pstm.setObject(5, pessoa.getGenero().ordinal());
        pstm.setString(6, pessoa.getSenha());
        pstm.setInt(7, tipo);
        pstm.execute();
        conn.close();
        System.out.println("Cadastro chegou ao fim");
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
    public Pessoa selecionarPorEmail(String email) throws SQLException {
        String query = "select * from pessoas where email = ?";
        conn = ConexaoBD.connectDB();
        pstm = conn.prepareStatement(query);
        pstm.setString(1, email);
        ResultSet rs = pstm.executeQuery();

        Pessoa pessoaSelecionada = null;
        if(rs.next()){

            Genero genero = null;
            for(Genero g : Genero.values()){
                if(g.ordinal() == rs.getInt("genero")){
                    genero = g;
                }
            }
            int tipoPessoa = rs.getInt("tipo");
            if(tipoPessoa == 1){
                pessoaSelecionada = new Autor(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        email,
                        genero,
                        rs.getString("senha")
                );
            } else if(tipoPessoa == 2){
                pessoaSelecionada = new Revisor(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        email,
                        genero,
                        rs.getString("senha")
                );
            } else if(tipoPessoa == 3){
                pessoaSelecionada = new Diretor(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        email,
                        genero,
                        rs.getString("senha")
                );
            }
        }

//        System.out.println(pessoaSelecionada.getCPF());

        if(pessoaSelecionada != null){
                conn.close();
                return pessoaSelecionada;
        } else {
            throw new RuntimeException("Pessoa não encontrada!");
        }
    }
}
