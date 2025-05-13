package service.validators;

import exception.ValidationException;
import model.enums.Tipo;
import util.ValidacoesUtils;
import util.Validator;

public class TipoValidator implements Validator<Tipo> {
    @Override
    public Tipo validate(String input) throws ValidationException {
        return ValidacoesUtils.validarTipoPet(input);
    }
}
