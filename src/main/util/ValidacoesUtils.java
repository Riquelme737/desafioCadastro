package util;

import exception.ValidationException;
import model.enums.Sexo;
import model.enums.Tipo;

public class ValidacoesUtils {

    public static String validarNomeCompleto(String petNome){
        String regex = "^(?=.{5,100}$)" +
                "\\p{L}{2,}(?:['-]\\p{L}{2,})?" +
                "(?:\\s+\\p{L}{2,}(?:['-]\\p{L}{2,})?)*" +
                "(?:\\s+(?:Jr|Sr|Filho|Neto)\\.)?$";

        petNome = petNome.trim();

        if (!petNome.contains(" ")) {
            throw new IllegalArgumentException("É necessário o nome ser completo!");
        }
        if (!petNome.matches(regex)) {
            throw new IllegalArgumentException("Nome está escrito errado! Digite novamente");
        }
        return petNome;
    }


    public static int validarNumeroPositivo(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Número não pode ser negativo!");
        }
        return numero;
    }

    public static Tipo validarTipoPet(String petTipo) throws ValidationException {
        String tipoAux = petTipo.trim().toUpperCase();
        if (tipoAux.equals("CACHORRO") || tipoAux.startsWith("C")) {
            return Tipo.CACHORRO;
        } else if (tipoAux.equals("GATO") || tipoAux.startsWith("G")) {
            return Tipo.GATO;
        } else {
            throw new ValidationException("Tipo inválido. Deve ser Cachorro ou Gato.");
        }
    }

    public static Sexo validarSexoPet(String petSexo) throws ValidationException {
        String generoAux = petSexo.trim().toUpperCase();
        if (generoAux.equals("MACHO") || generoAux.startsWith("M")) {
            return Sexo.MACHO;
        } else if (generoAux.equals("FEMA") || generoAux.startsWith("F")) {
            return Sexo.FEMEA;
        } else {
            throw new ValidationException("Gênero inválido. Apenas macho ou femea.");
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

    public static String validarRaca(String raca) {
        String regex = "^[\\p{L}\\s-]+$";

        if (raca == null || raca.isBlank()) {
            return Constantes.NAO_INFORMADO;
        }

        if (!raca.matches(regex)) {
            throw new IllegalArgumentException("Nome da raça inválido.");
        }

        return raca;
    }

    public static String validarRua_Cidade(String input){
        String regex = "^[\\p{L}0-9\\s\\-.]+$";
        if (!input.matches(regex)) {
            throw new IllegalArgumentException("Essa cidade/rua não está escrita da forma correta.");
        }
        return input.trim();
    }
}
