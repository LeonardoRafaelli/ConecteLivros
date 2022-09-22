package br.senai.sc.livros.model.dao;

import br.senai.sc.livros.model.factory.FactoryConnection;
import br.senai.sc.livros.model.entities.*;
import br.senai.sc.livros.model.factory.GenderFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class PessoaDAO {
    private Connection conn;

    public PessoaDAO() {
        this.conn = new FactoryConnection().connectDB();
    }

    private static final Set<Pessoa> listaPessoas = new HashSet<>();

    static {
        listaPessoas.add(new Autor("12435678", "autor", "Rafaellizin",
                "autor@", Genero.MASCULINO, "123"));
        listaPessoas.add(new Revisor("12435678", "revisor", "Rafaellizin",
                "revisor@", Genero.MASCULINO, "123"));
        listaPessoas.add(new Revisor("12435678", "Revisor2", "Rafaellizin",
                "revisor2@", Genero.MASCULINO, "123"));
        listaPessoas.add(new Diretor("12435678", "diretor", "Rafaellizin",
                "diretor@", Genero.MASCULINO, "123"));
    }

    public Revisor buscarRevisor(String revisorCPF) {
        String sqlQuery = "SELECT * FROM pessoas WHERE cpf = ?;";

        try (PreparedStatement pstm = conn.prepareStatement(sqlQuery)) {
            pstm.setString(1, revisorCPF);
            try {
                ResultSet rs = pstm.executeQuery();

                if(rs.next()){
                    return new Revisor(
                            revisorCPF,
                            rs.getString("nome"),
                            rs.getString("sobrenome"),
                            rs.getString("email"),
                            new GenderFactory().getGeneroByName(rs.getString("genero")),
                            rs.getString("senha")
                    );
                } else {
                    return null;
                }


            } catch (Exception err) {
                throw new RuntimeException("Erro ao executar buscarRevisor");
            }
        } catch (Exception err) {
            throw new RuntimeException("Erro ao preparar buscarRevisor");
        }
    }

    public void inserir(Pessoa pessoa) {
        String sqlCommand = "INSERT INTO PESSOAS (cpf, nome, sobrenome, email, genero, senha, tipoAcesso) values (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement pstm = conn.prepareStatement(sqlCommand)) {
            pstm.setString(1, pessoa.getCPF());
            pstm.setString(2, pessoa.getNome());
            pstm.setString(3, pessoa.getSobrenome());
            pstm.setString(4, pessoa.getEmail());

            pstm.setInt(5, pessoa.getGenero().ordinal() + 1);

            pstm.setString(6, pessoa.getSenha());
            pstm.setInt(7,
                    (pessoa instanceof Autor) ? 1 :
                            (pessoa instanceof Revisor) ? 2 : 3
            );

            try {
                pstm.execute();
            } catch (Exception e) {
                throw new RuntimeException("\nErro ao executar a inserção da pessoa");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro de conexão com bd (inserir()): " + e);
        }
        System.out.println("Cadastro chegou ao fim");
    }

    public void remover(Pessoa pessoa) {
        listaPessoas.remove(pessoa);
    }


    public Pessoa selecionarPorEmail(String email) {
        String query = "select * from pessoas where email = ?";

        try (PreparedStatement pstm = conn.prepareStatement(query)) {
            pstm.setString(1, email);
            try {
                ResultSet rs = pstm.executeQuery();

                if (rs.next()) {
                    int tipoPessoa = rs.getInt("tipoAcesso");
                    if (tipoPessoa == 1) {
                        return new Autor(
                                rs.getString("cpf"),
                                rs.getString("nome"),
                                rs.getString("sobrenome"),
                                email,
                                new GenderFactory().getGeneroByName(rs.getString("genero")),
                                rs.getString("senha")
                        );
                    } else if (tipoPessoa == 2) {
                        return new Revisor(
                                rs.getString("cpf"),
                                rs.getString("nome"),
                                rs.getString("sobrenome"),
                                email,
                                new GenderFactory().getGeneroByName(rs.getString("genero")),
                                rs.getString("senha")
                        );
                    } else if (tipoPessoa == 3) {
                        return new Diretor(
                                rs.getString("cpf"),
                                rs.getString("nome"),
                                rs.getString("sobrenome"),
                                email,
                                new GenderFactory().getGeneroByName(rs.getString("genero")),
                                rs.getString("senha")
                        );
                    }
                } else {
                    throw new RuntimeException("Pessoa não encontrada!");
                }

            } catch (Exception e) {
                throw new RuntimeException("Erro ao executar a seleção por email da pessoa");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro de conexão com bd (selecionarPorEmail()): " + e);
        }
        return null;
    }
}
