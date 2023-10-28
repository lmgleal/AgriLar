package br.com.gerenciamento.exception;

import java.io.Serial;

public class UserExistsException extends Exception{
    public UserExistsException(String message) {
        super(message);
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
