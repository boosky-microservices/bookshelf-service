package booksy.bookshelfservice.models.entities;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
@Document(collection = "users")
public class User {
     @NonNull
     ObjectId _id;
     @NonNull
     String email;

     String familyName;
     String givenName;
     String picture;
     String genres;
     List<Bookshelf> bookshelves;
}
