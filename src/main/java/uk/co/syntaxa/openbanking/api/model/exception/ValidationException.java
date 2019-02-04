package uk.co.syntaxa.openbanking.api.model.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import uk.co.syntaxa.openbanking.api.model.ValidationResult;

import java.util.List;

@Getter
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class ValidationException extends RuntimeException {
    final List<String> errors;

    public ValidationException(ValidationResult validationResult) {
        super();

        this.errors = validationResult.getErrors();
    }
}
