package service;



public class PetService {

    public static String validarTipoPet(String petTipo) {
        String petNome = petTipo.toUpperCase();
        if (!(petNome.startsWith("C") || petNome.startsWith("G")
                || petNome.equals("CACHORRO") || petNome.equals("GATO"))) {
            throw new IllegalArgumentException("Tipo inválido. Apenas cachorro ou gato!");
        }
        return petTipo;
    }

    public static String validarSexoPet(String petSexo) {
        String primeiraLetra = petSexo.toLowerCase();
        if (!(primeiraLetra.startsWith("m") || primeiraLetra.equals("f")
                || primeiraLetra.equals("macho") || primeiraLetra.equals("femea"))) {
            throw new IllegalArgumentException("Sexo inválido. Apenas macho ou femea!");
        }
        return petSexo;
    }

    public static Double validarPeso(double petPeso) {
        if (petPeso > 60 || petPeso < 0.5) {
            throw new IllegalArgumentException("Peso tem que ser entre 0.5 kg ou 60kg");
        }
        return petPeso;
    }

    public static Integer validarIdade(int petIdade) {
        if (petIdade <= 0 ||petIdade > 20) {
            throw new IllegalArgumentException("Tem que ser maior que 0 e menor ou igual a 20.");
        }
        return petIdade;
    }

    public static String validarRaca(String raca) {
        String regex = "^[a-zA-Z\\s-]*$";
        if (!raca.matches(regex)) {
            throw new IllegalArgumentException("Escreva corretamente.");
        }
        return raca;
    }

    public static String validarRua_Cidade(String input) {
        String regex = "^[\\p{L}0-9\\s\\-.]+$";
        if (!input.matches(regex)) {
            throw new IllegalArgumentException("Escreva corretamente.");
        }
        return input;
    }
}
