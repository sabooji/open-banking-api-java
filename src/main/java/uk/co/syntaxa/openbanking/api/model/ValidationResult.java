package uk.co.syntaxa.openbanking.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class ValidationResult {
    private boolean valid = true;
    private List<String> errors;

    public ValidationResult addError(String error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }

        this.errors.add(error);
        this.valid = false;

        return this;
    }
}
