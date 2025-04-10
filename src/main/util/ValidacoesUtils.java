package util;

public class ValidacoesUtils {

    public static void validacaoNomeCompleto(String petNome) {
        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(?:\\s+[A-Za-zÀ-ÖØ-öø-ÿ]+)*$";

        if (petNome == null || petNome.trim().isBlank()) {
            throw new IllegalArgumentException("Digite algo! O nome não pode ser nulo ou está em branco.");
        }

        if (!petNome.contains(" ")) {
            throw new IllegalArgumentException("É necessário o nome ser completo!");
        }

        if (!petNome.trim().matches(regex)) {
            throw new IllegalArgumentException("Nome está escrito errado! Digite novamente");
        }
    }

    public static void validacaoPeso(double petPeso) {
        if (petPeso > 60 || petPeso < 0.5) {
            throw new IllegalArgumentException("Peso tem que ser entre 0.5 kg ou 60kg");
        }
    }

    public static void  validacaoIdade(int petIdade) {
        if (petIdade <= 0 ||petIdade > 20) {
            throw new IllegalArgumentException("Tem que ser maior que 0 e menor ou igual a 20.");
        }
    }

    public static void validarUltimate(String input) {
        String regex = "^[a-zA-Z\\s]+$";

        if (input == null || !input.matches(regex)) {
            throw new IllegalArgumentException("Nada números ou caracteres especiais.");
        }
    }

    public static void validarTipoPet(String petTipo) {
        if (petTipo == null || petTipo.trim().isBlank()) throw new IllegalArgumentException("O tipo não pode ser vázio.");

        char primeiraLetra = petTipo.toLowerCase().charAt(0);
        if (primeiraLetra != 'c' && primeiraLetra != 'g') {
            throw new IllegalArgumentException("Tipo inválido. Apenas cachorro ou gato!");
        }
    }

    public static void validarSexoPet(String petSexo) {
        if (petSexo == null || petSexo.trim().isBlank()) throw new IllegalArgumentException("O sexo não pode ser vázio.");

        char primeiraLetra = petSexo.toLowerCase().charAt(0);
        if (primeiraLetra != 'm' && primeiraLetra != 'f') {
            throw new IllegalArgumentException("Sexo inválido. Apenas macho ou femea!");
        }
    }


    public static int validacaoNumero(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Digite corretamente, por favor.");
        }

        return numero;
    }
}
