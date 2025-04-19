package Utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Hibernate {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistence");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
