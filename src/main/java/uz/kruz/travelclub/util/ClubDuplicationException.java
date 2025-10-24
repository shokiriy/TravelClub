package uz.kruz.travelclub.util;

import java.io.Serial;

public class ClubDuplicationException extends RuntimeException {
    //
    @Serial
    private static final long serialVersionUID = -7196327736293090552L;

    public ClubDuplicationException(String message) {
        super(message);
    }
}