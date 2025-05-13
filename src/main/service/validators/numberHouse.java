package service.validators;

import exception.ValidationException;
import util.Validator;

public class numberHouse implements Validator<Integer> {
    @Override
    public Integer validate(String input) throws ValidationException {
        if (input == null || input.isBlank()) {
            return -1;
        }
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new ValidationException("A entrada deve ser um número inteiro");
        }
    }
}
