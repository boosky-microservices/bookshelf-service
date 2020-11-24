package booksy.bookshelfservice.services;

import booksy.bookshelfservice.exceptions.BookshelfNotFoundException;
import booksy.bookshelfservice.exceptions.NameNotUniqueException;
import booksy.bookshelfservice.models.dto.BookshelfDto;
import booksy.bookshelfservice.models.entities.Bookshelf;
import booksy.bookshelfservice.models.mappers.BookshelfMapper;
import booksy.bookshelfservice.repository.BookshelfRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookshelfService {

    private BookshelfRepository bookshelfRepository;

    private BookshelfMapper bookshelfMapper;

    public BookshelfDto getBookshelfById(String bookshelfId){
        return bookshelfRepository.findBookshelfBy_id(new ObjectId(bookshelfId))
                .map(bookshelfMapper::bookshelfToBookshelfDto)
                .orElseThrow(() -> new BookshelfNotFoundException("bookshelf.error.not-found"));

    }

    public List<BookshelfDto> getAllUsersBookshelves(String userId){
        return bookshelfRepository.findAllByUserId(userId).stream()
                .map(bookshelfMapper::bookshelfToBookshelfDto)
                .collect(Collectors.toList());
    }

    public BookshelfDto createBookshelf(String name, String userId){
       bookshelfRepository.findBookshelfByUserIdAndName(userId, name)
               .ifPresent((bookshelf) -> { throw new NameNotUniqueException("bookshelf.error.name-not-unique");});
       Bookshelf bookshelf= Bookshelf.builder().name(name).userId(userId).bookIds(new ArrayList<>()).build();
       return bookshelfMapper.bookshelfToBookshelfDto(bookshelfRepository.save(bookshelf));
    }

    public BookshelfDto updateBooksehlfName(String newName,String bookshelfId, String userId){
        Optional<Bookshelf> bookshelfOptional = bookshelfRepository.findBookshelfByUserIdAndName(userId, newName);
        if(bookshelfOptional.isPresent())
            throw new NameNotUniqueException("Name is not unique");
        Bookshelf bookshelf = bookshelfRepository.findBookshelfBy_idAndUserId(new ObjectId(bookshelfId),userId)
                .orElseThrow(() -> new BookshelfNotFoundException("bookshelf.error.not-found"));
        bookshelf.setName(newName);
        return bookshelfMapper.bookshelfToBookshelfDto(bookshelfRepository.save(bookshelf));
    }

    public void deleteBookshelf(String name, String userId){
        bookshelfRepository.removeBookshelfByUserIdAndName(userId,name)
                .orElseThrow(() -> new BookshelfNotFoundException("bookshelf.error.not-found"));

    }

    public BookshelfDto addBookToBookshelf(String bookId, String bookshelfId){
        Bookshelf bookshelf = bookshelfRepository.findBookshelfBy_id(new ObjectId(bookshelfId))
                .orElseThrow(() -> new BookshelfNotFoundException("bookshelf.error.not-found"));
        List<String> booksId = bookshelf.getBookIds();
        booksId.add(bookId);
        return bookshelfMapper.bookshelfToBookshelfDto(bookshelfRepository.save(bookshelf));
    }

    public BookshelfDto removeBookFromBookshelf(String bookId, String bookshelfId){
        Bookshelf bookshelf = bookshelfRepository.findBookshelfBy_id(new ObjectId(bookshelfId))
                .orElseThrow(() -> new BookshelfNotFoundException("bookshelf.error.not-found"));
        List<String> booksId = bookshelf.getBookIds();
        booksId.remove(bookId);
        return bookshelfMapper.bookshelfToBookshelfDto(bookshelfRepository.save(bookshelf));
    }

    public void removeBookshelf(String bookshelfId){
        bookshelfRepository.deleteById(bookshelfId);
    }


}
