package service.validators;

import exception.ValidationException;
import model.enums.Sexo;
import util.ValidacoesUtils;
import util.Validator;

public class GeneroValidator implements Validator<Sexo> {
    @Override
    public Sexo validate(String input) throws ValidationException {
        return ValidacoesUtils.validarSexoPet(input);
    }
}
