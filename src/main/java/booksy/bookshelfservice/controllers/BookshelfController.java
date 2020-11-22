package booksy.bookshelfservice.controllers;

import booksy.bookshelfservice.models.dto.BookshelfDto;
import booksy.bookshelfservice.models.dto.AddBookToShelfDto;
import booksy.bookshelfservice.models.dto.CreateBookshelfDto;
import booksy.bookshelfservice.models.dto.UpdateBookshelfDto;
import booksy.bookshelfservice.services.BookshelfService;
import booksy.bookshelfservice.utils.ResponseMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/shelves")
public class BookshelfController {

   BookshelfService bookshelfService;


    @GetMapping("/")
    public ResponseEntity<List<BookshelfDto>> getBookshelves(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt != null && jwt.getClaim("sub") != null ? jwt.getClaim("sub").toString().split("\\|")[1] : "noid";
        return new ResponseEntity<>(bookshelfService.getAllUsersBookshelves(userId), HttpStatus.OK);
    }

    @GetMapping("/{bookshelfId}")
    public ResponseEntity<BookshelfDto> getBookkshelfById(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable(name = "bookshelfId")  String bookshelfId
    ){
        String userId = jwt != null && jwt.getClaim("sub") != null ? jwt.getClaim("sub").toString().split("\\|")[1] : "noid";
        return new ResponseEntity<>(bookshelfService.getBookshelfById(bookshelfId), HttpStatus.OK);
    }

    // TODO : handle noid
    @PostMapping("/")
    public ResponseEntity<BookshelfDto> createBookshelf(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody CreateBookshelfDto createBookshelfDto
            ){
        String userId = jwt != null && jwt.getClaim("sub") != null ? jwt.getClaim("sub").toString().split("\\|")[1] : "noid";
        return new ResponseEntity<>(bookshelfService.createBookshelf(createBookshelfDto.getBookshelfName(),userId), HttpStatus.OK);

    }
    @PutMapping("/{bookshelfId}")
    public ResponseEntity<BookshelfDto> putBookIntoBookshelf(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable String bookshelfId,
            @RequestBody AddBookToShelfDto addBookToShelfDto
            ){
        String userId = jwt != null && jwt.getClaim("sub") != null ? jwt.getClaim("sub").toString().split("\\|")[1] : "noid";
        return new ResponseEntity<>(bookshelfService.addBookToBookshelf(addBookToShelfDto.getBookId(),bookshelfId), HttpStatus.OK);
    }

    @PatchMapping("/{bookshelfId}")
    public ResponseEntity<BookshelfDto> renameBookshelf(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody UpdateBookshelfDto updateBookshelfDto,
            @PathVariable(name = "bookshelfId") String bookshelfId
    ){
        String userId = jwt != null && jwt.getClaim("sub") != null ? jwt.getClaim("sub").toString().split("\\|")[1] : "noid";
        return new ResponseEntity<>(bookshelfService.updateBooksehlfName(updateBookshelfDto.getNewName(),bookshelfId,userId), HttpStatus.OK);
    }
    @DeleteMapping("/{bookshelfId}")
    public ResponseEntity<ResponseMessage> removeBookshelf(@PathVariable String bookshelfId){
        bookshelfService.removeBookshelf(bookshelfId);
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.OK,"Bookshelf removed"), HttpStatus.OK);
    }




}
