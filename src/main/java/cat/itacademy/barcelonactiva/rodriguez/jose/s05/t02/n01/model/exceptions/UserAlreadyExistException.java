package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.exceptions;

public class UserAlreadyExistException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public UserAlreadyExistException(String msg) {
        super(msg);
    }
}