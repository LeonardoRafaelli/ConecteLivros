package br.senai.sc.livros.model.factory;

import br.senai.sc.livros.model.entities.Genero;

public class GenderFactory {

    public Genero getGeneroByName(String generoNome){
        switch (generoNome){
            case "Masculino" -> {return Genero.MASCULINO;}
            case "Feminino" -> {return Genero.FEMININO;}
            case "Outro" -> {return Genero.OUTRO;}
        }
        throw new RuntimeException("Genero não encontrado");
    }

}
