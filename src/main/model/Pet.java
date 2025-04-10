package model;

import model.enums.Sexo;
import model.enums.Tipo;
import util.ValidacoesUtils;

public class Pet {

    private String nome;
    private Tipo tipo;
    private Sexo sexo;
    private EnderecoPet enderecoPet;
    private int idade;
    private double peso;
    private String raca;
    private final String NAO_INFORMADO = "Não informado";

    public Pet() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        ValidacoesUtils.validacaoNomeCompleto(nome);
        this.nome = nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public EnderecoPet getEnderecoPet() {
        return enderecoPet;
    }

    public void setEnderecoPet(EnderecoPet enderecoPet) {
        this.enderecoPet = enderecoPet;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        ValidacoesUtils.validacaoIdade(idade);
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        ValidacoesUtils.validacaoPeso(peso);
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        ValidacoesUtils.validarUltimate(raca);
        this.raca = raca;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "nome='" + nome + '\'' +
                ", tipo=" + tipo +
                ", sexo=" + sexo +
                ", enderecoPet=" + enderecoPet +
                ", idade=" + idade +
                ", peso=" + peso +
                ", raca='" + raca + '\'' +
                '}';
    }
}
