package com.macadamian.car.service.common.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import com.macadamian.car.service.common.enums.Error;

public class ValidationResult {

    private boolean valid;

    private List<Error> errors;

    private List<String> messages;

    public ValidationResult() {
        // Empty constructor
        valid = true;
        errors = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public void put(final Error code, final String message) {
        this.valid = false;
        this.errors.add(code);
        this.messages.add(message);
    }

    public void merge(final ValidationResult validationResult) {
        if(!validationResult.valid){
            final int size = validationResult.errors.size();
            for(int i = 0; i < size; i++){
                final String message = validationResult.messages.size() > i ? validationResult.messages.get(i) : "";
                this.put(validationResult.errors.get(i), message);
            }
        }
    }

    public boolean isValid() {
        return valid;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public List<String> getMessages() {
        return messages;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        final ValidationResult rhs = (ValidationResult) obj;
        return new EqualsBuilder()
                .append(this.valid, rhs.valid)
                .append(this.errors, rhs.errors)
                .append(this.messages, rhs.messages)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(valid)
                .append(errors)
                .append(messages)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("valid", valid)
                .append("errors", errors)
                .append("messages", messages)
                .toString();
    }
}
