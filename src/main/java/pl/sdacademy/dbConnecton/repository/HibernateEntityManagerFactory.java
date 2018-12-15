package pl.sdacademy.dbConnecton.repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateEntityManagerFactory {
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("persistence");


    public static EntityManagerFactory get() {
        return EMF;
    }

}
