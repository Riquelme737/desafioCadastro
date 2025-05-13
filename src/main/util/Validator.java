package util;

import exception.ValidationException;

public interface Validator<T> {
    T validate(String input) throws ValidationException;
}
