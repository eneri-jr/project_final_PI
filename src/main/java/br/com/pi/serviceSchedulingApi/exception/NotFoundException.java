package br.com.pi.serviceSchedulingApi.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String id, String model) {
        super(String.format("%s with ID %s not found.",model, id));
    }

}
