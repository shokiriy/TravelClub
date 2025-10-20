package uz.kruz.travelclub.util;

import java.io.Serial;

public class InvalidEmailException extends RuntimeException{
    //
    @Serial
    private static final long serialVersionUID = -8812955226330753784L;

    public InvalidEmailException(String message) {
        super(message);
    }
}
