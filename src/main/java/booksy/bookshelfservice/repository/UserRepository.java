package booksy.bookshelfservice.repository;

import booksy.bookshelfservice.models.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository  extends MongoRepository<User, String> {
}
