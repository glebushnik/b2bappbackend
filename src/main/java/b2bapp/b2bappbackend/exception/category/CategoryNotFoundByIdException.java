package b2bapp.b2bappbackend.exception.category;

public class CategoryNotFoundByIdException extends Exception{
    public CategoryNotFoundByIdException(String message) {
        super(message);
    }
}
