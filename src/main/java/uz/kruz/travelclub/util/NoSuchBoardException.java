package uz.kruz.travelclub.util;

import java.io.Serial;

public class NoSuchBoardException extends RuntimeException {
    //
    @Serial
    private static final long serialVersionUID = 5867172506387382920L;

    public NoSuchBoardException(String message) {
        super(message);
    }
}