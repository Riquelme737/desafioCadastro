package service.validators;

import exception.ValidationException;
import util.Validator;

public class IdadeValidator implements Validator<Integer> {
    @Override
    public Integer validate(String input) throws ValidationException {
        if (input == null || input.isBlank()) {
            return -1;
        }
        try {
            int idade = Integer.parseInt(input);
            if (idade <= 0 || idade >= 20) {
                throw new ValidationException("Idade tem que ser maior ou igual a 0 ou menor ou igual a 20");
            }
            return idade;
        } catch (NumberFormatException e) {
            throw new ValidationException("");
        }
    }
}
