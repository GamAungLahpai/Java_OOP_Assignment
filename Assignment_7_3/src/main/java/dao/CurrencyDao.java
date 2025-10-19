package dao;

import entity.Currency;
import datasource.MariaDbJpaConnection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.PersistenceException;
import java.util.List;
import java.util.ArrayList;

public class CurrencyDao {

    /**
     * Retrieve all currencies from database
     */
    public List<Currency> findAll() {
        try {
            EntityManager em = MariaDbJpaConnection.getInstance();
            TypedQuery<Currency> query = em.createQuery("SELECT c FROM Currency c ORDER BY c.abbreviation", Currency.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error retrieving all currencies: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Find currency by abbreviation
     */
    public Currency findByAbbreviation(String abbreviation) {
        try {
            EntityManager em = MariaDbJpaConnection.getInstance();
            TypedQuery<Currency> query = em.createQuery(
                    "SELECT c FROM Currency c WHERE c.abbreviation = :abbreviation", Currency.class);
            query.setParameter("abbreviation", abbreviation);
            List<Currency> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } catch (Exception e) {
            System.err.println("Error finding currency by abbreviation: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Find currency by ID
     */
    public Currency findById(int id) {
        try {
            EntityManager em = MariaDbJpaConnection.getInstance();
            return em.find(Currency.class, id);
        } catch (Exception e) {
            System.err.println("Error finding currency by ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Persist new currency to database
     */
    public boolean persist(Currency currency) {
        EntityManager em = null;
        try {
            em = MariaDbJpaConnection.getInstance();
            em.getTransaction().begin();
            em.persist(currency);
            em.getTransaction().commit();
            System.out.println("Successfully persisted currency: " + currency.getAbbreviation());
            return true;
        } catch (PersistenceException e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error persisting currency: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Unexpected error persisting currency: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update existing currency
     */
    public boolean update(Currency currency) {
        EntityManager em = null;
        try {
            em = MariaDbJpaConnection.getInstance();
            em.getTransaction().begin();
            em.merge(currency);
            em.getTransaction().commit();
            System.out.println("Successfully updated currency: " + currency.getAbbreviation());
            return true;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error updating currency: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete currency from database
     */
    public boolean delete(Currency currency) {
        EntityManager em = null;
        try {
            em = MariaDbJpaConnection.getInstance();
            em.getTransaction().begin();

            // Make sure entity is managed before removing
            Currency managedCurrency = em.find(Currency.class, currency.getId());
            if (managedCurrency != null) {
                em.remove(managedCurrency);
            }

            em.getTransaction().commit();
            System.out.println("Successfully deleted currency: " + currency.getAbbreviation());
            return true;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error deleting currency: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get exchange rate for specific currency
     */
    public double getExchangeRate(String abbreviation) throws Exception {
        Currency currency = findByAbbreviation(abbreviation);
        if (currency == null) {
            throw new Exception("Currency not found: " + abbreviation);
        }
        return currency.getRateToUSD();
    }

    /**
     * Check if database connection is available
     */
    public boolean isDatabaseAvailable() {
        return MariaDbJpaConnection.isConnectionAvailable();
    }
}