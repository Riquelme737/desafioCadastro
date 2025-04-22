package model;

import model.enums.Sexo;
import model.enums.Tipo;
import service.PetService;
import util.ValidacoesUtils;

public class Pet {

    private String nome;
    private Tipo tipo;
    private Sexo sexo;
    private EnderecoPet enderecoPet;
    private Integer idade;
    private Double peso;
    private String raca;
    private final int id;
    private static int proximoId = 1;


    public Pet() {
        this.id = proximoId++;
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

    public int getId() { return id;}

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
            PetService.validarIdade(idade);
        }
        this.idade = idade;
    }

    public void setPeso(Double peso) {
        if (peso != null) {
            PetService.validarPeso(peso);
        }
        this.peso = peso;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }


    public String toFileFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("1 - ").append(getNome()).append("\n");

        sb.append("2 - ").append(getTipo().getNome()).append("\n");

        sb.append("3 - ").append(getSexo().getNome()).append("\n");

        sb.append("4 - ");
        sb.append(getEnderecoPet().getRua()).append(", ");
        if (getEnderecoPet().getNumeroCasa() == null) {
            sb.append(getNAO_INFORMADO());
        } else {
            sb.append(getEnderecoPet().getNumeroCasa());
        }
        sb.append(", ");
        sb.append(getEnderecoPet().getCidade()).append("\n");

        sb.append("5 - ");
        if (getIdade() == null) sb.append(getNAO_INFORMADO()).append("\n");
        else sb.append(getIdade()).append(" anos").append("\n");

        sb.append("6 - ");
        if (getPeso() == null) sb.append(getNAO_INFORMADO()).append("\n");
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

       if (enderecoPet.getRua() == null) sb.append(getEnderecoPet().getRua());
       else sb.append(enderecoPet.getRua()).append(", ");

       if (enderecoPet.getNumeroCasa() == null) sb.append(getNAO_INFORMADO()).append(" - ");
       else sb.append(enderecoPet.getNumeroCasa()).append(" - ");

       if (enderecoPet.getCidade() == null) sb.append(getEnderecoPet().getCidade());
       else sb.append(enderecoPet.getCidade()).append(" - ");


       if (idade == null) sb.append(getNAO_INFORMADO()).append(" - ");
       else sb.append(idade).append(" anos - ");
       if (peso == null) sb.append(getNAO_INFORMADO()).append(" - ");
       else sb.append(peso).append("kg - ");
       sb.append(raca);

       return sb.toString();
    }
}
