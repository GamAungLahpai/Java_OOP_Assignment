package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "abbreviation", unique = true, nullable = false, length = 3)
    private String abbreviation;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "rate_to_usd", nullable = false)
    private double rateToUSD;

    // Default constructor (required by JPA)
    public Currency() {}

    // Constructor for creating new currencies
    public Currency(String abbreviation, String name, double rateToUSD) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.rateToUSD = rateToUSD;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRateToUSD() {
        return rateToUSD;
    }

    public void setRateToUSD(double rateToUSD) {
        this.rateToUSD = rateToUSD;
    }

    @Override
    public String toString() {
        return abbreviation + " - " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Currency currency = (Currency) obj;
        return abbreviation != null ? abbreviation.equals(currency.abbreviation) : currency.abbreviation == null;
    }

    @Override
    public int hashCode() {
        return abbreviation != null ? abbreviation.hashCode() : 0;
    }
}