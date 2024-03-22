package b2bapp.b2bappbackend.exception.user;

public class UserIsNotAdminException extends Exception{
    public UserIsNotAdminException(String message) {
        super(message);
    }
}
