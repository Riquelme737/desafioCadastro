package model.enums;

public enum Sexo {
    MACHO("Macho"),
    FEMEA("Femea");

    private final String nome;

    Sexo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
