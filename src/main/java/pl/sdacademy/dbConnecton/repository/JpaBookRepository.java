package pl.sdacademy.dbConnecton.repository;

import pl.sdacademy.dbConnecton.model.Book;
import pl.sdacademy.dbConnecton.model.LibraryUser;
import pl.sdacademy.dbConnecton.service.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JpaBookRepository implements BookRepository {
    @Override
    public List<Book> findByBorrowingUserId(Long id) {
        EntityManager em = HibernateEntityManagerFactory.get().createEntityManager();
        em.getTransaction().begin();
        List<Book> borrowedBooks = new ArrayList<>();
        JpaLibraryUserRepository jpaLibraryUserRepository = new JpaLibraryUserRepository();
        Optional<LibraryUser> user = jpaLibraryUserRepository.findById(id);
        if (user.isPresent()) {
            LibraryUser libraryUser = user.get();
            
        }
        em.getTransaction().commit();
        em.close();
        return borrowedBooks;
    }

    @Override
    public List<Book> findByTitleAndAuthorLastName(String title, String author) {
        EntityManager em = HibernateEntityManagerFactory.get().createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Book> query = em.createQuery(" FROM Book book left JOIN book.authors a where a.lastName like :lastName and book.name like :name", Book.class);
        query.setParameter("name", "%" + title + "%");
        query.setParameter("lastName", "%" + author + "%");
        List<Book> books = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return books;
    }

    @Override
    public Optional<Book> findById(Long id) {
        EntityManager em = HibernateEntityManagerFactory.get().createEntityManager();
        em.getTransaction().begin();
        Book book = em.find(Book.class, id);
        em.getTransaction().commit();
        em.close();
        return Optional.ofNullable(book);
    }

    @Override
    public Book save(Book entity) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    @Override
    public Book update(Book entity) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }
}
