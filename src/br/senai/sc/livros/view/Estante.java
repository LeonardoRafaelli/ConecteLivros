package br.senai.sc.livros.view;

import br.senai.sc.livros.controller.LivrosController;
import br.senai.sc.livros.model.entities.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Estante extends JFrame {
    private JPanel estante;
    private JTable tabelaLivros;
    private JButton buttonVoltar;
    private JButton editarButton;

    public Estante(int option){
        criarComponentes(option);
        buttonVoltar.addActionListener(e -> {
            dispose();
            new Menu(Menu.getUsuario());
        });
        editarButton.addActionListener(e -> {
            LivrosController livrosController = new LivrosController();
            Pessoa usuario = Menu.getUsuario();

            if(usuario instanceof Autor){
                int row = tabelaLivros.getSelectedRow();
                int isbn = (int) tabelaLivros.getValueAt(row, 0);
                Livro livro = livrosController.selecionarPorISBN(isbn);
                livrosController.atualizarStatus(livro, Status.AGUARDANDO_REVISAO);
                JOptionPane.showMessageDialog(null, "Livro editado com sucesso!");
                dispose();
                new Estante(option);
            } else if(usuario instanceof Revisor){
                int row = tabelaLivros.getSelectedRow();
                int isbn = (int) tabelaLivros.getValueAt(row, 0);
                Livro livro = livrosController.selecionarPorISBN(isbn);
                new CadastroLivro(usuario, livro);
            }


        });
    }

    private void criarComponentes(int option){
        LivrosController livrosController = new LivrosController();

        if(option == 1){
            editarButton.setVisible(false);
            tabelaLivros.setModel(new DefaultTableModelArrayList(livrosController.getAllLivros()));
        } else {
            tabelaLivros.setModel(new DefaultTableModelArrayList(livrosController.listarAtividades()));
        }
        tabelaLivros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setContentPane(estante);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }



}
