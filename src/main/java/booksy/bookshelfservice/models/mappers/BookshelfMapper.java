package booksy.bookshelfservice.models.mappers;

import booksy.bookshelfservice.models.dto.BookshelfDto;
import booksy.bookshelfservice.models.entities.Bookshelf;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookshelfMapper {

   //@Mapping(target = "userId", ignore = true)
    BookshelfDto bookshelfToBookshelfDto(Bookshelf bookshelf);
}
