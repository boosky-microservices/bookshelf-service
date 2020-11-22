package booksy.bookshelfservice.repository;

import booksy.bookshelfservice.models.entities.Bookshelf;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookshelfRepository extends MongoRepository<Bookshelf, String> {

    List<Bookshelf> findAllByUserId(String userId);

    Optional<Bookshelf> findBookshelfBy_id(ObjectId _id);

    Optional<Bookshelf> findBookshelfBy_idAndUserId(ObjectId _id , String userId );

    Optional<Bookshelf> removeBookshelfByUserIdAndName(String userId, String name );




    //@Query("SELECT * FROM Bookshelf b WHERE b.userId = :userId AND b.name = :name ")
    Optional<Bookshelf> findBookshelfByUserIdAndName(String userId, String name);
}
