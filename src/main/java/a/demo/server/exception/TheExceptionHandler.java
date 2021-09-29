package a.demo.server.exception;

import a.demo.server.tools.TheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class TheExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public String ExceptionHandler(Exception e){
        log.error(e.getMessage());
        return new TheResult().failureString(e.getMessage(),"500",e);
    }
}
