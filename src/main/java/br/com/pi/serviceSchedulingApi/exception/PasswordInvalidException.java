package br.com.pi.serviceSchedulingApi.exception;

public class PasswordInvalidException extends RuntimeException{
    public PasswordInvalidException() {
        super("Password invalid");
    }
}
