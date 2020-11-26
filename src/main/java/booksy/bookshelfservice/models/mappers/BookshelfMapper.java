package booksy.bookshelfservice.models.mappers;

import booksy.bookshelfservice.models.dto.BookshelfDto;
import booksy.bookshelfservice.models.entities.Bookshelf;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@FunctionalInterface()
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookshelfMapper {

    //@Mapping(target = "userId", ignore = true)
    @Mapping(target = "_id", expression = "java(idToString(bookshelf))")
    BookshelfDto bookshelfToBookshelfDto(Bookshelf bookshelf);

    default String idToString(Bookshelf bookshelf) {
        return bookshelf.get_id().toHexString();
    }
}
