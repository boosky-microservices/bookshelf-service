package booksy.bookshelfservice.models.dto;

import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Objects;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookshelfDto {
    private ObjectId  _id;
    private String name;
    private String userId;
    private List<String> bookIds;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookshelfDto that = (BookshelfDto) o;
        return Objects.equals(_id, that._id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(bookIds, that.bookIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, name, userId, bookIds);
    }
}
