package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.exceptions;

public class UserNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String msg) {
        super(msg);
    }
}