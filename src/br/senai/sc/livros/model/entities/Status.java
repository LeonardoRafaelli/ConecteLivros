package br.senai.sc.livros.model.entities;

import br.senai.sc.livros.view.Menu;

import java.util.Arrays;

public enum Status {
    AGUARDANDO_REVISAO("Aguardando revisão"),       //0
    EM_REVISAO("Em revisão"),                       //1
    APROVADO("Aprovado"),                           //2
    AGUARDANDO_EDICAO("Aguardando edição"),         //3
    REPROVADO("Reprovado"),                         //4
    PUBLICADO("Publicado");                         //5

    private String nome;
    private int[] permissao;

    Status(String nome) {
        this.nome = nome;
    }

    public static String[] getAllStatus() {
        if(Menu.getUsuario() instanceof Revisor){
            String[] stringStatus = new String[4];
            stringStatus[0] = Status.EM_REVISAO.getNome();
            stringStatus[1] = Status.AGUARDANDO_EDICAO.getNome();
            stringStatus[2] = Status.APROVADO.getNome();
            stringStatus[3] = Status.REPROVADO.getNome();
            return stringStatus;
        } else {
            String[] stringStatus = new String[3];
            stringStatus[0] = Status.PUBLICADO.getNome();
            stringStatus[1] = Status.AGUARDANDO_REVISAO.getNome();
            stringStatus[2] = Status.REPROVADO.getNome();
            return stringStatus;
        }
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
