package com.bnksys.onemind.supports.responses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String message; // be into Body (NOT NULL)
    private List<FieldError> errors; // be into Body (Nullable)


    protected ErrorResponse(final String message) {
        this.message = message;
    }

    @Builder
    protected ErrorResponse(final String message, final List<FieldError> errors) {
        this.message = message;
        this.errors = errors;
    }

    public static ErrorResponse of(final String message) {
        return new ErrorResponse(message);
    }

    public static ErrorResponse of(final String message, final BindingResult bindingResult) {
        return new ErrorResponse(message, FieldError.of(bindingResult));
    }

    public static class FieldError {

        private final String field;
        private final String value;
        private final String reason;

        public static List<FieldError> of(final String field, final String value,
            final String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                              .map(error -> new FieldError(
                                  error.getField(),
                                  error.getRejectedValue() == null ? "" : error
                                      .getRejectedValue()
                                      .toString(),
                                  error.getDefaultMessage()))
                              .collect(Collectors.toList());
        }

        @Builder
        FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }
    }

}
