package pl.sdacademy.dbConnecton.repository;

import pl.sdacademy.dbConnecton.model.BookBorrow;
import pl.sdacademy.dbConnecton.service.repository.BookBorrowRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JpaBookBorrowRepository implements BookBorrowRepository {
    @Override
    public Optional<BookBorrow> findByUserIdAndBookId(Long id, Long bookId) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<BookBorrow> query =
                entityManager.createQuery("FROM BookBorrow bb JOIN fetch bb.user user join fetch bb.book book where user.id=:id and book.id = :bookId and bb.returnDate = null", BookBorrow.class);
        query.setParameter("id", id);
        query.setParameter("bookId", bookId);
        BookBorrow result = query.getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(result);
    }

    @Override
    public boolean isBookBorrowed(Long bookId) {
        Boolean result = false;
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<BookBorrow> query = entityManager.createQuery("from BookBorrow bb left join bb.book book where book.id = :bookId and bb.returnDate = null ", BookBorrow.class);
        query.setParameter("bookId", bookId);
        BookBorrow bookBorrow = query.getSingleResult();
        if (bookBorrow != null) {
            result = true;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    @Override
    public List<BookBorrow> findByUserId(Long readerNumber) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<BookBorrow> query = entityManager.createQuery("FROM BookBorrow bb left join bb.user user where user.id = :id and bb.returnDate= null", BookBorrow.class);
        query.setParameter("id", readerNumber);
        List<BookBorrow> resultList = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return resultList;
    }

    @Override
    public Optional<BookBorrow> findById(Long id) {
        EntityManager em = HibernateEntityManagerFactory.get().createEntityManager();
        em.getTransaction().begin();
        BookBorrow bb = em.find(BookBorrow.class, id);
        em.getTransaction().commit();
        em.close();
        return Optional.ofNullable(bb);
    }

    @Override
    public BookBorrow save(BookBorrow entity) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    @Override
    public BookBorrow update(BookBorrow entity) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }
}
