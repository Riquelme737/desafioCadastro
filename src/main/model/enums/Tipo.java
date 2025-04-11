package model.enums;

public enum Tipo {
    CACHORRO ("Cachorro"),
    GATO ("Gato");

    private final String nome;

    Tipo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
