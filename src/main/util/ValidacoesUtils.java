package util;

public class ValidacoesUtils {

    public static String validarNomeCompleto(String petNome) {
        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(?:\\s+[A-Za-zÀ-ÖØ-öø-ÿ]+)*$";

        if (!petNome.contains(" ")) {
            throw new IllegalArgumentException("É necessário o nome ser completo!");
        }

        if (!petNome.trim().matches(regex)) {
            throw new IllegalArgumentException("Nome está escrito errado! Digite novamente");
        }
        return petNome;
    }

    public static String validarUltimate(String input) {
        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(?:\\s+[A-Za-zÀ-ÖØ-öø-ÿ]+)*$";

        if (!input.trim().matches(regex)) {
            throw new IllegalArgumentException("Nada números ou caracteres especiais.");
        }
        return input;
    }

    public static int validarNumeroPositivo(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Número não pode ser negativo!");
        }

        return numero;
    }
}
