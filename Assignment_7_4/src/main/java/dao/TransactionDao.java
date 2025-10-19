package dao;

import entity.Transaction;
import datasource.MariaDbJpaConnection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.PersistenceException;
import java.util.List;
import java.util.ArrayList;

public class TransactionDao {

    /**
     * Persist a new transaction to the database
     */
    public boolean persist(Transaction transaction) {
        EntityManager em = null;
        try {
            em = MariaDbJpaConnection.getInstance();
            em.getTransaction().begin();
            em.persist(transaction);
            em.getTransaction().commit();

            System.out.println("Successfully persisted transaction: " +
                    transaction.getSourceAmount() + " " +
                    transaction.getSourceCurrency().getAbbreviation() +
                    " -> " + transaction.getTargetAmount() + " " +
                    transaction.getTargetCurrency().getAbbreviation());
            return true;

        } catch (PersistenceException e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error persisting transaction: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Unexpected error persisting transaction: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Find transaction by ID
     */
    public Transaction findById(int transactionId) {
        try {
            EntityManager em = MariaDbJpaConnection.getInstance();
            return em.find(Transaction.class, transactionId);
        } catch (Exception e) {
            System.err.println("Error finding transaction by ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieve all transactions
     */
    public List<Transaction> findAll() {
        try {
            EntityManager em = MariaDbJpaConnection.getInstance();
            TypedQuery<Transaction> query = em.createQuery(
                    "SELECT t FROM Transaction t ORDER BY t.transactionTimestamp DESC", Transaction.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error retrieving all transactions: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Find transactions by source currency abbreviation
     */
    public List<Transaction> findBySourceCurrency(String currencyAbbreviation) {
        try {
            EntityManager em = MariaDbJpaConnection.getInstance();
            TypedQuery<Transaction> query = em.createQuery(
                    "SELECT t FROM Transaction t WHERE t.sourceCurrency.abbreviation = :abbreviation ORDER BY t.transactionTimestamp DESC",
                    Transaction.class);
            query.setParameter("abbreviation", currencyAbbreviation);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error finding transactions by source currency: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Find transactions by target currency abbreviation
     */
    public List<Transaction> findByTargetCurrency(String currencyAbbreviation) {
        try {
            EntityManager em = MariaDbJpaConnection.getInstance();
            TypedQuery<Transaction> query = em.createQuery(
                    "SELECT t FROM Transaction t WHERE t.targetCurrency.abbreviation = :abbreviation ORDER BY t.transactionTimestamp DESC",
                    Transaction.class);
            query.setParameter("abbreviation", currencyAbbreviation);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error finding transactions by target currency: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Get count of all transactions
     */
    public long getTransactionCount() {
        try {
            EntityManager em = MariaDbJpaConnection.getInstance();
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(t) FROM Transaction t", Long.class);
            return query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error getting transaction count: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Update existing transaction
     */
    public boolean update(Transaction transaction) {
        EntityManager em = null;
        try {
            em = MariaDbJpaConnection.getInstance();
            em.getTransaction().begin();
            em.merge(transaction);
            em.getTransaction().commit();
            System.out.println("Successfully updated transaction: " + transaction.getTransactionId());
            return true;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error updating transaction: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete transaction from database
     */
    public boolean delete(Transaction transaction) {
        EntityManager em = null;
        try {
            em = MariaDbJpaConnection.getInstance();
            em.getTransaction().begin();

            // Make sure entity is managed before removing
            Transaction managedTransaction = em.find(Transaction.class, transaction.getTransactionId());
            if (managedTransaction != null) {
                em.remove(managedTransaction);
            }

            em.getTransaction().commit();
            System.out.println("Successfully deleted transaction: " + transaction.getTransactionId());
            return true;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error deleting transaction: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}