package uk.co.syntaxa.openbanking.api.model.exception;

import uk.co.syntaxa.openbanking.api.model.ValidationResult;

import java.util.List;

public class ValidationException extends RuntimeException {
    final List<String> errors;

    public ValidationException(ValidationResult validationResult) {
        super();

        this.errors = validationResult.getErrors();
    }
}
