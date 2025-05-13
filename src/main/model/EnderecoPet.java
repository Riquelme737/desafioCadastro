package model;


import util.ValidacoesUtils;

public class EnderecoPet {
    private Integer numeroCasa;
    private String cidade;
    private String rua;

    public EnderecoPet(Integer numeroCasa, String cidade, String rua) {
        this.numeroCasa = numeroCasa;
        this.cidade = cidade;
        this.rua = rua;
    }

    public EnderecoPet() {}

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
        if (numeroCasa != -1) numeroCasa = ValidacoesUtils.validarNumeroPositivo(numeroCasa);
        this.numeroCasa = numeroCasa;
    }

    public void setCidade(String cidade) {
        cidade = ValidacoesUtils.validarRua_Cidade(cidade);
        this.cidade = cidade;
    }

    public void setRua(String rua) {
        rua = ValidacoesUtils.validarRua_Cidade(rua);
        this.rua = rua;
    }

    @Override
    public String toString() {
        return getRua() + ", " + getNumeroCasa() + " - " + getCidade();
    }
}
