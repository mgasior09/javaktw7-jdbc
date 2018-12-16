package pl.sdacademy.dbConnecton.repository;

import pl.sdacademy.dbConnecton.model.LibraryUser;
import pl.sdacademy.dbConnecton.service.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class JpaLibraryUserRepository implements UserRepository {
    @Override
    public Optional<LibraryUser> findByUsernameAndPassword(String username, String password) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<LibraryUser> query = entityManager.createQuery("FROM LibraryUser libraryUser where libraryUser.username = :username and libraryUser.password = :password", LibraryUser.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        query.setMaxResults(1);
        LibraryUser foundLibraryUser = query.getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(foundLibraryUser);
    }

    @Override
    public Optional<LibraryUser> findById(Long id) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();
        LibraryUser libraryUser = entityManager.find(LibraryUser.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(libraryUser);
    }

    @Override
    public LibraryUser save(LibraryUser entity) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    @Override
    public LibraryUser update(LibraryUser entity) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.merge(entity);

        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }
}
