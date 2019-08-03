package quick.boot.validation.common;

import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(HttpMessageNotReadableException.class)
  private ResponseEntity<CommonResponse> httpMessageNotReadableException(
      HttpMessageNotReadableException e) {

    CommonResponse errorResponse = CommonResponse.builder().status(400).message("参数有误").build();

    log.error("handle HttpMessageNotReadableException, message = {}", e.getMessage());
    return ResponseEntity.ok(errorResponse);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  private ResponseEntity<CommonResponse> methodArgumentNotValidException(
      MethodArgumentNotValidException e) {

    String messages = e.getBindingResult().getAllErrors().stream()
           .map(ObjectError::getDefaultMessage).sorted().collect(Collectors.joining(","));

    CommonResponse errorResponse = CommonResponse.builder().status(400).message(messages).build();


    log.error("handle MethodArgumentNotValidException, message = {}", messages);
    return ResponseEntity.ok(errorResponse);
  }
}
