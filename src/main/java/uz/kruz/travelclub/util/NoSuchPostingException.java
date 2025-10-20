package uz.kruz.travelclub.util;

import java.io.Serial;

public class NoSuchPostingException extends RuntimeException {
    //
    @Serial
    private static final long serialVersionUID = 5867172506387382920L;

    public NoSuchPostingException(String message) {
        super(message);
    }
}