package model;

import util.ValidacoesUtils;

public class EnderecoPet {
    private int numeroCasa;
    private String cidade;
    private String rua;

    public EnderecoPet() {
    }

    public EnderecoPet(int numeroCasa, String cidade, String rua) {
        this.numeroCasa = numeroCasa;
        this.cidade = cidade;
        this.rua = rua;
    }

    public int getNumeroCasa() {
        return numeroCasa;
    }

    public String getCidade() {
        return cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setNumeroCasa(int numeroCasa) {
        ValidacoesUtils.validacaoNumero(numeroCasa);
        this.numeroCasa = numeroCasa;
    }

    public void setCidade(String cidade) {
        ValidacoesUtils.validarUltimate(cidade);
        this.cidade = cidade;
    }

    public void setRua(String rua) {
        ValidacoesUtils.validarUltimate(rua);
        this.rua = rua;
    }
}
