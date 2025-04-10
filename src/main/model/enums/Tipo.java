package model.enums;

public enum Tipo {
    CACHORRO ("Cachorro"),
    GATO ("Gato");

    private String nome;

    Tipo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
