package booksy.bookshelfservice.models.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookshelfDto {

    private String bookshelfName;
}
