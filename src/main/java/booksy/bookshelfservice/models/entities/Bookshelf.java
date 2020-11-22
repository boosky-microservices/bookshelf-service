package booksy.bookshelfservice.models.entities;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
@Document(collection = "bookshelves")
public class Bookshelf {
    @Id
    ObjectId _id;
    private String name;
    private String userId;
    private List<String> bookIds;
}
