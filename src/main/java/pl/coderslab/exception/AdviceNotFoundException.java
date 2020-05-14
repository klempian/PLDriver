package pl.coderslab.exception;

public class AdviceNotFoundException extends RuntimeException {

    public AdviceNotFoundException(Long id) {
        super("Advice with id '" + id + "' not found ");
    }
}
