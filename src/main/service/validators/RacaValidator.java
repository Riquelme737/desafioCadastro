package service.validators;

import exception.ValidationException;
import util.Constantes;
import util.Validator;

public class RacaValidator implements Validator<String> {
    @Override
    public String validate(String input) throws ValidationException {
        String regex = "^[\\p{L}\\p{M} .'-]+$";

        if (input.isBlank()) {
            return Constantes.NAO_INFORMADO;
        }

        if (!input.matches(regex)) {
            throw new ValidationException("Escreva corretamente.");
        }

        return input;
    }
}
