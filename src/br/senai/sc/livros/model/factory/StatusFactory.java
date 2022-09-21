package br.senai.sc.livros.model.factory;

import br.senai.sc.livros.model.entities.Status;

public class StatusFactory {
    public Status buscarStatusCorreto(String stringStatus) {
        for (Status status : Status.values()) {
            if (status.getNome().equals(stringStatus)) {
                return status;
            }
        }
        return null;
    }
}
