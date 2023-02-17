package com.works.configs;

import com.works.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValid( MethodArgumentNotValidException ex ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, false);
        hm.put(REnum.errors, parseError(ex.getFieldErrors()));
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity methodArgumentTypeMismatch( MethodArgumentTypeMismatchException ex ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, false);
        hm.put(REnum.errors, ex.getMessage() );
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }

    private List parseError(List<FieldError> errors) {
        List ls = new ArrayList();
        for( FieldError error : errors ) {
            String field = error.getField();
            String message = error.getDefaultMessage();
            Map hm = new HashMap();
            hm.put("field", field);
            hm.put("message", message);
            ls.add(hm);
        }
        return ls;
    }

}
