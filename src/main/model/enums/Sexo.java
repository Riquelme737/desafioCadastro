package model.enums;

public enum Sexo {
    MACHO("Macho"),
    FEMEA("Femea");

    private String nome;

    Sexo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
