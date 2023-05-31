package pro.sky.coursework2_2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotEnoughQuestionsInCollectionException extends RuntimeException{
    public NotEnoughQuestionsInCollectionException() {}

    public NotEnoughQuestionsInCollectionException(String message) {
        super(message);
    }
}
