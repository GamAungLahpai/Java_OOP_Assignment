package datasource;

import jakarta.persistence.*;

public class MariaDbJpaConnection {

    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    public static EntityManager getInstance() {
        if (em == null || !em.isOpen()) {
            if (emf == null) {
                try {
                    emf = Persistence.createEntityManagerFactory("CurrencyConverterUnit");
                } catch (Exception e) {
                    System.err.println("Failed to create EntityManagerFactory: " + e.getMessage());
                    throw e;
                }
            }
            em = emf.createEntityManager();
        }
        return em;
    }

    public static void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public static boolean isConnectionAvailable() {
        try {
            EntityManager testEm = getInstance();
            return testEm != null && testEm.isOpen();
        } catch (Exception e) {
            return false;
        }
    }
}