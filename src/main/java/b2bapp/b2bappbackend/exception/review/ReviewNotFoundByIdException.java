package b2bapp.b2bappbackend.exception.review;

public class ReviewNotFoundByIdException extends Exception{
    public ReviewNotFoundByIdException(String message) {
        super(message);
    }
}
