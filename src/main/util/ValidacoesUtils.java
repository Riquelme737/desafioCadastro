package util;

public class ValidacoesUtils {

    public static void validarNomeCompleto(String petNome) {
        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(?:\\s+[A-Za-zÀ-ÖØ-öø-ÿ]+)*$";

        if (!petNome.contains(" ")) {
            throw new IllegalArgumentException("É necessário o nome ser completo!");
        }

        if (!petNome.trim().matches(regex)) {
            throw new IllegalArgumentException("Nome está escrito errado! Digite novamente");
        }
    }

    public static void validarPeso(double petPeso) {
        if (petPeso > 60 || petPeso < 0.5) {
            throw new IllegalArgumentException("Peso tem que ser entre 0.5 kg ou 60kg");
        }
    }

    public static void validarIdade(int petIdade) {
        if (petIdade <= 0 ||petIdade > 20) {
            throw new IllegalArgumentException("Tem que ser maior que 0 e menor ou igual a 20.");
        }
    }

    public static void validarUltimate(String input) {
        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(?:\\s+[A-Za-zÀ-ÖØ-öø-ÿ]+)*$";

        if (!input.trim().matches(regex)) {
            throw new IllegalArgumentException("Nada números ou caracteres especiais.");
        }
    }

    public static void validarTipoPet(String petTipo) {
        String primeiraLetra = petTipo.toLowerCase();
        if (!(primeiraLetra.startsWith("c") || primeiraLetra.startsWith("g")
                || primeiraLetra.equals("cachorro") || primeiraLetra.equals("gato"))) {
            throw new IllegalArgumentException("Tipo inválido. Apenas cachorro ou gato!");
        }
    }

    public static void validarSexoPet(String petSexo) {
        String primeiraLetra = petSexo.toLowerCase();
        if (!(primeiraLetra.startsWith("m") || primeiraLetra.equals("f")
                || primeiraLetra.equals("macho") || primeiraLetra.equals("femea"))) {
            throw new IllegalArgumentException("Sexo inválido. Apenas macho ou femea!");
        }
    }

    public static void validarNumeroCasa(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Digite corretamente, por favor.");
        }

    }
}
