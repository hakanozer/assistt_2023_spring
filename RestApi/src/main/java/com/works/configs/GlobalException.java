package com.works.configs;

import com.works.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValid( MethodArgumentNotValidException ex ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, false);
        hm.put(REnum.errors, parseError( ex.getFieldErrors() ));
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }

    private List parseError(List<FieldError> fieldErrors) {
        List ls = new ArrayList();
        for( FieldError error : fieldErrors ) {
            String field = error.getField();
            String message = error.getDefaultMessage();
            Map map = new LinkedHashMap();
            map.put("field", field);
            map.put("message", message);
            ls.add(map);
        }
        return ls;
    }

}
