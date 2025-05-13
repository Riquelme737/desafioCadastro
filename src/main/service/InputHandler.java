package service;

import exception.ValidationException;
import util.Constantes;
import util.Validator;

public class InputHandler {
    public <T> T getInput(String msg, Validator<T> validator) {
        while (true) {
            System.out.println(msg);
            System.out.print(">>> ");
            String input = Constantes.scanner.nextLine();
            try {
                return validator.validate(input);
            } catch (ValidationException | IllegalArgumentException e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
    }
}
