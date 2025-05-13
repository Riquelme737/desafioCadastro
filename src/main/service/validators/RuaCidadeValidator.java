package service.validators;

import exception.ValidationException;
import util.Validator;

public class RuaCidadeValidator implements Validator<String> {
    @Override
    public String validate(String input) throws ValidationException {
        String regex = "^[\\p{L}0-9\\s\\-.]+$";
        if (!input.matches(regex)) {
            throw new ValidationException("Algo deu errado. Escreva novamente.");
        }
        return input;
    }
}
