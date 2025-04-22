package service;

public class PetService {

    public static String validarTipoPet(String petTipo) {
        String primeiraLetra = petTipo.toLowerCase();
        if (!(primeiraLetra.startsWith("c") || primeiraLetra.startsWith("g")
                || primeiraLetra.equals("cachorro") || primeiraLetra.equals("gato"))) {
            throw new IllegalArgumentException("Tipo inválido. Apenas cachorro ou gato!");
        }
        return petTipo;
    }

    public static void validarSexoPet(String petSexo) {
        String primeiraLetra = petSexo.toLowerCase();
        if (!(primeiraLetra.startsWith("m") || primeiraLetra.equals("f")
                || primeiraLetra.equals("macho") || primeiraLetra.equals("femea"))) {
            throw new IllegalArgumentException("Sexo inválido. Apenas macho ou femea!");
        }
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
}
