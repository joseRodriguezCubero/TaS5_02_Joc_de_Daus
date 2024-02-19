package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.exceptions;

public class PlayerAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PlayerAlreadyExistException(String msg) {
        super(msg);
    }
}

