package pl.sdacademy.dbConnecton.repository;

import pl.sdacademy.dbConnecton.model.Location;
import pl.sdacademy.dbConnecton.service.repository.LocationRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class JpaLocationRepository implements LocationRepository {

    @Override
    public Optional<Location> find(String rackSymbol, String shelfSymbol, Long position) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Location> locationQuery = entityManager.createQuery("FROM Location l WHERE l.rackSymbol = :rackSymbol AND l.shelfSymbol = :shelfSymbol AND l.position = :myPosition", Location.class);
        locationQuery.setParameter("rackSymbol", rackSymbol);
        locationQuery.setParameter("shelfSymbol", shelfSymbol);
        locationQuery.setParameter("myPosition", position);
        locationQuery.setMaxResults(1);
        Location foundLocation = locationQuery.getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(foundLocation);
    }

    @Override
    public Optional<Location> findById(Long id) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();
        Location foundLocation = entityManager.find(Location.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(foundLocation);
    }

    @Override
    public Location save(Location entity) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(entity);

        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    @Override
    public Location update(Location entity) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();

        Location foundLocation = entityManager.find(Location.class, entity.getId());
        if (foundLocation != null) {
            foundLocation.setPosition(entity.getPosition());
            foundLocation.setRackSymbol(entity.getRackSymbol());
            foundLocation.setShelfSymbol(entity.getShelfSymbol());
            entityManager.merge(foundLocation);
        } else {
            entityManager.persist(entity);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }
}
