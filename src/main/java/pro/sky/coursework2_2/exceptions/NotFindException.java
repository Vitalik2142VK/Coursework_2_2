package pro.sky.coursework2_2.exceptions;

public class NotFindException extends RuntimeException{
    public NotFindException() {
    }

    public NotFindException(String message) {
        super(message);
    }
}
