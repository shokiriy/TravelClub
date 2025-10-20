package uz.kruz.travelclub.util;

import org.springframework.http.HttpStatus;
import uz.kruz.travelclub.util.exceptionHandler.BaseException;

public class ClubDuplicationException extends BaseException {
    //
    public ClubDuplicationException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}