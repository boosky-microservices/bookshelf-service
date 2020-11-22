package booksy.bookshelfservice.exceptions;

public class NameNotUniqueException extends RuntimeException {

    public NameNotUniqueException(String message){
        super(message);
    }

}
