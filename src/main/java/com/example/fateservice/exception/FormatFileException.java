package com.example.fateservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FormatFileException extends RuntimeException{
    private String fileName;
    public FormatFileException(String fileName){
        super(String.format("File %s not correct format file", fileName));
    }
}
