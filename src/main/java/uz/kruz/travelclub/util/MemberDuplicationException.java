package uz.kruz.travelclub.util;

import java.io.Serial;

public class MemberDuplicationException extends RuntimeException {
    //
    @Serial
    private static final long serialVersionUID = -7196327736293090551L;

    public MemberDuplicationException(String message) {
        super(message);
    }
}