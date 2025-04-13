package model;

import util.ValidacoesUtils;

public class EnderecoPet {
    private Integer numeroCasa;
    private String cidade;
    private String rua;

    public EnderecoPet() {
    }

    public Integer getNumeroCasa() {
        return numeroCasa;
    }

    public String getCidade() {
        return cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setNumeroCasa(Integer numeroCasa) {
        if (numeroCasa != null) {
            ValidacoesUtils.validarNumeroPositivo(numeroCasa);
        }
        this.numeroCasa = numeroCasa;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    @Override
    public String toString() {
        return "EnderecoPet{" +
                "numeroCasa=" + numeroCasa +
                ", cidade='" + cidade + '\'' +
                ", rua='" + rua;
    }
}
