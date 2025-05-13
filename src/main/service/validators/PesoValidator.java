package service.validators;

import exception.ValidationException;
import util.Validator;

public class PesoValidator implements Validator<Double>{
    @Override
    public Double validate(String input) throws ValidationException {

        if (input == null || input.isBlank()) {
            return (double) -1;
        }

        try {
            double peso = Double.parseDouble(input.trim());
            if (peso > 60 || peso < 0.5) {
                throw new ValidationException("Peso tem que ser entre 0.5kg e 60kg.");
            }
            return peso;
        } catch (NumberFormatException e) {
            throw new ValidationException("A entrada deve ser número decimal.");
        }

    }
}
