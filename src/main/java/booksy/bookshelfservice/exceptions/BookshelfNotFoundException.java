package booksy.bookshelfservice.exceptions;

public class BookshelfNotFoundException extends RuntimeException {

    public BookshelfNotFoundException(String message){
        super(message);
    }
}
