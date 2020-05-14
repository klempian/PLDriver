package pl.coderslab.exception;

public class TagNotFoundException extends RuntimeException {

    public TagNotFoundException(Long id) {
        super("Tag with id '" + id + "' not found");
    }
    public TagNotFoundException(String name) { super("Tag with name '" + name + "' not found"); }
}
