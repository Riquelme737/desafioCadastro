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
    private  int id;
    private static int proximoId = 1;

    public Pet() {
    }

    public String NAO_INFORMADO() {
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

    public int getId() { return id;}

    public void setNome(String nome){
        nome = ValidacoesUtils.validarNomeCompleto(nome);
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
        if (idade != -1) idade = ValidacoesUtils.validarIdade(idade);
        this.idade = idade;
    }

    public void setPeso(Double peso) {
        if (peso != -1) peso = ValidacoesUtils.validarPeso(peso);
        this.peso = peso;
    }

    public void setRaca(String raca) {
        raca = ValidacoesUtils.validarRaca(raca);
        this.raca = raca;
    }

    public void atribuirProximoId() {
        this.id = proximoId++;
    }

    public static void resetProximoId() {
        proximoId = 1;
    }

    public String toFileFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("1 - ").append(getNome()).append("\n");

        sb.append("2 - ").append(getTipo().getNome()).append("\n");

        sb.append("3 - ").append(getSexo().getNome()).append("\n");

        sb.append("4 - ");
        sb.append(getEnderecoPet().getRua()).append(", ");
        if (getEnderecoPet().getNumeroCasa() == -1) {
            sb.append(NAO_INFORMADO());
        } else {
            sb.append(getEnderecoPet().getNumeroCasa());
        }
        sb.append(", ");
        sb.append(getEnderecoPet().getCidade()).append("\n");

        sb.append("5 - ");
        if (getIdade() == -1) sb.append(NAO_INFORMADO()).append("\n");
        else sb.append(getIdade()).append(" anos").append("\n");

        sb.append("6 - ");
        if (getPeso() == -1) sb.append(NAO_INFORMADO()).append("\n");
        else sb.append(getPeso()).append("kg").append("\n");

        sb.append("7 - ").append(getRaca()).append("\n");
        return sb.toString();
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();

       sb.append(id).append(". ");
       sb.append(nome).append(" - ");
       sb.append(tipo.getNome()).append(" - ");
       sb.append(sexo.getNome()).append(" - ");

       sb.append(enderecoPet.getRua()).append(", ");

       if (enderecoPet.getNumeroCasa() == -1) sb.append(NAO_INFORMADO()).append(" - ");
       else sb.append(enderecoPet.getNumeroCasa()).append(" - ");

       sb.append(enderecoPet.getCidade()).append(" - ");

       if (idade == -1) sb.append(NAO_INFORMADO()).append(" - ");
       else sb.append(idade).append(" anos - ");
       if (peso == -1) sb.append(NAO_INFORMADO()).append(" - ");
       else sb.append(peso).append("kg - ");
       sb.append(raca);

       return sb.toString();
    }

    public static class Builder {
        private String nome;
        private Tipo tipo;
        private Sexo sexo;
        private EnderecoPet enderecoPet;
        private Integer idade;
        private Double peso;
        private String raca;

        public Builder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder setTipo(Tipo tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder setSexo(Sexo sexo) {
            this.sexo = sexo;
            return this;
        }

        public Builder setEnderecoPet(EnderecoPet enderecoPet) {
            this.enderecoPet = enderecoPet;
            return this;
        }

        public Builder setIdade(Integer idade) {
            this.idade = idade;
            return this;
        }

        public Builder setPeso(Double peso) {
            this.peso = peso;
            return this;
        }

        public Builder setRaca(String raca) {
            this.raca = raca;
            return this;
        }

        public Pet build() {
            Pet pet = new Pet();
            pet.nome = this.nome;
            pet.tipo= this.tipo;
            pet.sexo = this.sexo;
            pet.enderecoPet = this.enderecoPet;
            pet.idade = this.idade;
            pet.peso = this.peso;
            pet.raca = this.raca;
            pet.atribuirProximoId();
            return pet;
        }
    }
}
