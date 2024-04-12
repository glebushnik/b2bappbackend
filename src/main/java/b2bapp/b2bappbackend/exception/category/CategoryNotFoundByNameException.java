package b2bapp.b2bappbackend.exception.category;

public class CategoryNotFoundByNameException extends Exception{
    public CategoryNotFoundByNameException(String message) {
        super(message);
    }
}
