package model;

import model.enums.Sexo;
import model.enums.Tipo;
import util.ValidacoesUtils;

public class Pet {

    private String nome;
    private Tipo tipo;
    private Sexo sexo;
    private EnderecoPet enderecoPet;
    private Integer idade;
    private Double peso;
    private String raca;

    public Pet() {
    }

    public String getNAO_INFORMADO() {
        return "Não informado";
    }

    public String getNome() {
        return nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Integer getIdade() {
        return idade;
    }

    public EnderecoPet getEnderecoPet() {
        return enderecoPet;
    }

    public Double getPeso() {
        return peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setNome(String nome) {
        ValidacoesUtils.validarNomeCompleto(nome);
        this.nome = nome;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public void setEnderecoPet(EnderecoPet enderecoPet) {
        this.enderecoPet = enderecoPet;
    }

    public void setIdade(Integer idade) {
        if (idade != null) {
            ValidacoesUtils.validarIdade(idade);
        }
        this.idade = idade;
    }

    public void setPeso(Double peso) {
        if (peso != null) {
            ValidacoesUtils.validarPeso(peso);
        }
        this.peso = peso;
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
