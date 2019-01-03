package pl.sdacademy.dbConnecton.repository;

import pl.sdacademy.dbConnecton.model.Writer;
import pl.sdacademy.dbConnecton.service.repository.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class JpaWriterRepository implements AuthorRepository {
    @Override
    public Optional<Writer> findByFirstAndLastName(String firstName, String lastName) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Writer> query = entityManager.createQuery("FROM Writer w WHERE w.personalData. firstName = :firstName AND w.personalData. lastName = :lastName", Writer.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        query.setMaxResults(1);
        Writer foundWriter = query.getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(foundWriter);
    }

    @Override
    public Optional<Writer> findById(Long id) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();
        Writer foundWriter = entityManager.find(Writer.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(foundWriter);
    }

    @Override
    public Writer save(Writer entity) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(entity);

        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    @Override
    public Writer update(Writer entity) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();

        Writer foundWriter = entityManager.find(Writer.class, entity.getId());
        if (foundWriter != null) {
            foundWriter.setPersonalData(entity.getPersonalData());

            entityManager.merge(foundWriter);
        } else {
            entityManager.persist(entity);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }
}
