package com.enjoytrip.utils.exceptionUtils;

import com.enjoytrip.utils.businessLogicException.ExceptionCode;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
    private int status;
    private String message;
//    private List<FieldError> fieldErrors;

    private ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorResponse of(ExceptionCode exceptionCode) {
        return new ErrorResponse(exceptionCode.getStatus(), exceptionCode.getMessage());
    }

    public static ErrorResponse of(HttpStatus httpStatus) {
        return new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase());
    }

//    @Getter
//    @AllArgsConstructor
//    public static class FieldError {
//        private String field;
//        private Object rejectedValue;
//        private String reason;
//    }
}
