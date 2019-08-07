package uk.co.syntaxa.openbanking.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class ValidationResult {

    private List<String> errors;

    public boolean isValid() {
        return CollectionUtils.isEmpty(this.errors);
    }

    public ValidationResult addError(String error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }

        this.errors.add(error);

        return this;
    }
}
