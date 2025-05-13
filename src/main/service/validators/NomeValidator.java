package service.validators;

import exception.ValidationException;
import util.Constantes;
import util.ValidacoesUtils;
import util.Validator;

public class NomeValidator implements Validator<String>{
    @Override
    public String validate(String input) throws ValidationException, IllegalArgumentException{
        if (input.isBlank()) {
            return Constantes.NAO_INFORMADO;
        }
        ValidacoesUtils.validarNomeCompleto(input);

        return input;
    }
}
