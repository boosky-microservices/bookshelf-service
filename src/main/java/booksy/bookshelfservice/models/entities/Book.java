package booksy.bookshelfservice.models.entities;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.math.BigInteger;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Document(collection = "books")
public class Book {
    @Id
    ObjectId _id;
    private String title;
    private String subtitle;
    private String publisher;
    private String description;
    private int pageCount;
    private Set<String> authors;
    private Set<String> categories;
    private String thumbnail;
    private String publishedDate;


}