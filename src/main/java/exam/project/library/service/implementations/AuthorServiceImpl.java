package exam.project.library.service.implementations;

import exam.project.library.model.Author;
import exam.project.library.repository.AuthorRepository;
import exam.project.library.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
//    @Cacheable(value = "author")
    public List<Author> getAllAuthor() {
        return authorRepository.getAllAuthor();
    }

    @Override
//    @Cacheable(value = "author", key = "#authorId")
    public List<Author> getAuthorById(Long authorId) {
        return authorRepository.getAuthorById(authorId);
    }

    @Override
    public int saveNewAuthor(Author author) {
        return authorRepository.saveNewAuthor(author);
    }

    @Override
    public void saveWriteBook(Long authorId, Long bookId) {
        authorRepository.saveWriteBook(authorId, bookId);
    }

    @Override
//    @CachePut(value = "author", key = "#authorId")
    public void updateAuthor(Long authorId, Author author) {
        authorRepository.updateAuthor(authorId, author);
    }

    @Override
//    @CacheEvict(value = "author", key = "#authorId")
    public void deleteAuthor(Long authorId) {
        authorRepository.deleteAuthor(authorId);
    }
}
