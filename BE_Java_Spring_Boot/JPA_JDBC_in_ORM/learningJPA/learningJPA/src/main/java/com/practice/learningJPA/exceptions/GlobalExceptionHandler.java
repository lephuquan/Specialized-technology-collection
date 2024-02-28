package com.practice.learningJPA.exceptions;

import com.practice.learningJPA.utils.ErrorUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.time.LocalDateTime;
import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final MessageSource messageSource;

    public GlobalExceptionHandler(
            MessageSource messageSource
    ) {
        this.messageSource = messageSource;
    }

//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
//        logger.warn("Exception: {} - Message: {} - Stack: {}",
//                ex.getClass().getName(),
//                ex.getMessage(),
//                Arrays.toString(ex.getStackTrace()));
//
//        ErrorMessage error = ErrorMessage.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .code(HttpStatus.BAD_REQUEST.value())
//                .message(ex.getLocalizedMessage())
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseExceptionBuilder.build(error);
//
//    }
//
//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
//        logger.warn("Exception: {} - Message: {} - Stack: {}",
//                ex.getClass().getName(),
//                ex.getMessage(),
//                Arrays.toString(ex.getStackTrace()));
//
//        ErrorMessage error = ErrorMessage.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .code(HttpStatus.BAD_REQUEST.value())
//                .message(ex.getLocalizedMessage())
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseExceptionBuilder.build(error);
//
//    }
//
//    @ExceptionHandler(HttpMessageConversionException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleHttpMessageConversionException(HttpMessageConversionException ex) {
//        logger.warn("Exception: {} - Message: {} - Stack: {}",
//                ex.getClass().getName(),
//                ex.getMessage(),
//                Arrays.toString(ex.getStackTrace()));
//
//        ErrorMessage error = ErrorMessage.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .code(HttpStatus.BAD_REQUEST.value())
//                .message(ex.getLocalizedMessage())
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseExceptionBuilder.build(error);
//    }
//
//    /**
//     * Triggers when @Valid argument fails
//     *
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
//        logger.warn("Exception: {} - Message: {} - Stack: {}",
//                ex.getClass().getName(),
//                ex.getMessage(),
//                Arrays.toString(ex.getStackTrace()));
//
//        List<String> errorMessages = new ArrayList<>();
//        errorMessages.addAll(ErrorUtils.getFieldErrorMessages(ex.getFieldErrors()));
//        errorMessages.addAll(ErrorUtils.getObjectErrorMessages(ex.getGlobalErrors()));
//        ErrorMessage error = ErrorMessage.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .code(HttpStatus.BAD_REQUEST.value())
//                .message(String.join(" ", errorMessages))
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseExceptionBuilder.build(error);
//    }
//
//    /**
//     * Triggers when there are missing parameters
//     *
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
//        logger.warn("Exception: {} - Message: {} - Stack: {}",
//                ex.getClass().getName(),
//                ex.getMessage(),
//                Arrays.toString(ex.getStackTrace()));
//
//        ErrorMessage error = ErrorMessage.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .code(HttpStatus.BAD_REQUEST.value())
//                .message(ex.getLocalizedMessage())
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseExceptionBuilder.build(error);
//    }
//
//    /**
//     * Triggers when missed argument type in the method
//     *
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
//        logger.warn("Exception: {} - Message: {} - Stack: {}",
//                ex.getClass().getName(),
//                ex.getMessage(),
//                Arrays.toString(ex.getStackTrace()));
//
//        ErrorMessage error = ErrorMessage.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .code(HttpStatus.BAD_REQUEST.value())
//                .message(ex.getLocalizedMessage())
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseExceptionBuilder.build(error);
//    }
//
//    @ExceptionHandler(BindException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleBindException(BindException ex) {
//        logger.warn("Exception: {} - Message: {} - Stack: {}",
//                ex.getClass().getName(),
//                ex.getMessage(),
//                Arrays.toString(ex.getStackTrace()));
//
//        List<FieldError> fieldErrorsTypeMisMatch = ex.getFieldErrors().stream()
//                .filter(fieldError -> Arrays.stream(
//                                Objects.requireNonNull(fieldError.getCodes()))
//                        .anyMatch(s -> s.equalsIgnoreCase("typeMismatch"))
//                ).collect(Collectors.toList());
//
//        if (!fieldErrorsTypeMisMatch.isEmpty()) {
//            ErrorMessage error = ErrorMessage.builder()
//                    .status(HttpStatus.BAD_REQUEST)
//                    .code(HttpStatus.BAD_REQUEST.value())
//                    .message("Data type not match")
//                    .timestamp(LocalDateTime.now())
//                    .build();
//
//            return ResponseExceptionBuilder.build(error);
//        }
//
//        List<String> errorMessages = new ArrayList<>();
//        errorMessages.addAll(ErrorUtils.getFieldErrorMessages(ex.getFieldErrors()));
//        errorMessages.addAll(ErrorUtils.getObjectErrorMessages(ex.getGlobalErrors()));
//        ErrorMessage error = ErrorMessage.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .code(HttpStatus.BAD_REQUEST.value())
//                .message(String.join(" ", errorMessages))
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseExceptionBuilder.build(error);
//    }
//
//    /**
//     * Triggers when @Validated fails
//     *
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
//        logger.warn("Exception: {} - Message: {} - Stack: {}",
//                ex.getClass().getName(),
//                ex.getMessage(),
//                Arrays.toString(ex.getStackTrace()));
//
//        List<String> errorMessages = ex.getConstraintViolations().stream()
//                .map(ConstraintViolation::getMessage)
//                .collect(Collectors.toList());
//
//        ErrorMessage error = ErrorMessage.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .code(HttpStatus.BAD_REQUEST.value())
//                .message(String.join(" ", errorMessages))
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseExceptionBuilder.build(error);
//    }
//
    /**
     * Triggers when there is not resource with the specified ID in BDD
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        logger.warn("Exception: {} - Message: {} - Stack: {}",
                ex.getClass().getName(),
                ex.getMessage(),
                Arrays.toString(ex.getStackTrace()));

        ErrorMessage error = ErrorMessage.builder()
                .status(HttpStatus.NOT_FOUND)
                .code(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseExceptionBuilder.build(error);
    }

//    /**
//     * Triggers when throw Business Exception
//     *
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler(ResourceExistingException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleResourceExistingException(ResourceExistingException ex) {
//        logger.warn("Exception: {} - Message: {} - Stack: {}",
//                ex.getClass().getName(),
//                ex.getMessage(),
//                Arrays.toString(ex.getStackTrace()));
//
//        ErrorMessage error = ErrorMessage.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .code(HttpStatus.BAD_REQUEST.value())
//                .message(ex.getMessage())
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseExceptionBuilder.build(error);
//    }
//
//    /**
//     * Triggers when the handler method is invalid
//     *
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler(NoHandlerFoundException.class)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {
//        logger.warn("Exception: {} - Message: {} - Stack: {}",
//                ex.getClass().getName(),
//                ex.getMessage(),
//                Arrays.toString(ex.getStackTrace()));
//
//        ErrorMessage error = ErrorMessage.builder()
//                .status(HttpStatus.NOT_FOUND)
//                .code(HttpStatus.NOT_FOUND.value())
//                .message(ex.getLocalizedMessage())
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseExceptionBuilder.build(error);
//
//    }

    /**
     * @param ex
     * @return
     */
//    @ExceptionHandler({AuthenticationException.class}) // spring-boot-starter-security
//    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
//        logger.warn("Exception: {} - Message: {} - Stack: {}",
//                ex.getClass().getName(),
//                ex.getMessage(),
//                Arrays.toString(ex.getStackTrace()));
//
//        ErrorMessage error = ErrorMessage.builder()
//                .status(HttpStatus.UNAUTHORIZED)
//                .message(ex.getMessage())
//                .code(401)
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseExceptionBuilder.build(error);
//    }

//    @ExceptionHandler({Exception.class})
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<Object> handleAll(Exception ex) {
//        logger.warn("Exception: {} - Message: {} - Stack: {}",
//                ex.getClass().getName(),
//                ex.getMessage(),
//                Arrays.toString(ex.getStackTrace()));
//        String message = messageSource.getMessage("api-internal-server-error",
//                null, LocaleContextHolder.getLocale());
//        ErrorMessage error = ErrorMessage.builder()
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .message(message)
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseExceptionBuilder.build(error);
//    }
//
//    @ExceptionHandler({IOException.class})
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleIOException(IOException ex) {
//        logger.warn("Exception: {} - Message: {} - Stack: {}",
//                ex.getClass().getName(),
//                ex.getMessage(),
//                Arrays.toString(ex.getStackTrace()));
//
//        ErrorMessage error = ErrorMessage.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .code(HttpStatus.BAD_REQUEST.value())
//                .message(ex.getMessage())
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseExceptionBuilder.build(error);
//    }
//
//    @ExceptionHandler({IllegalArgumentException.class})
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
//        logger.warn("Exception: {} - Message: {} - Stack: {}",
//                ex.getClass().getName(),
//                ex.getMessage(),
//                Arrays.toString(ex.getStackTrace()));
//
//        ErrorMessage error = ErrorMessage.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .code(HttpStatus.BAD_REQUEST.value())
//                .message(ex.getMessage())
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseExceptionBuilder.build(error);
//    }
//
//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
//        logger.warn("Exception: {} - Message: {} - Stack: {}",
//                ex.getClass().getName(),
//                ex.getMessage(),
//                Arrays.toString(ex.getStackTrace()));
//
//        ErrorMessage error = ErrorMessage.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .code(HttpStatus.BAD_REQUEST.value())
//                .message(messageSource.getMessage("api.file.max-size", null,
//                        "api.file.max-size", LocaleContextHolder.getLocale()))
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseExceptionBuilder.build(error);
//    }

}
