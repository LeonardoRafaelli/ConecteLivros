package br.senai.sc.livros.view;

import br.senai.sc.livros.model.entities.Livro;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DefaultTableModelArrayList extends AbstractTableModel {

    ArrayList<Livro> dados;
    String[] colunas;

    public DefaultTableModelArrayList(ArrayList<Livro> lista){
        this.dados = lista;
        colunas = new String[]{"ISBN", "Título", "Qtd. Páginas", "Autor", "Editora", "Status", "Revisor responsável"};
    }


    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Livro livro = dados.get(rowIndex);
        switch (columnIndex){
            case 0 -> {
                return livro.getISBN();
            }
            case 1 -> {
                return livro.getTitulo();
            }
            case 2 -> {
                return livro.getQntdPaginas();
            }
            case 3 -> {
                return livro.getAutor();
            }
            case 4 -> {
                return livro.getEditora();
            }
            case 5 -> {
                return livro.getStatus();
            }
            case 6 -> {
                return livro.getRevisor();
            }
        }
        return null;
    }

   @Override
    public String getColumnName(int columnIndex){
        return colunas[columnIndex];
   }
}
