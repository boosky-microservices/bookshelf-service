package booksy.bookshelfservice.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ResponseMessage {
    private HttpStatus httpStatus;
    private String message;
}
