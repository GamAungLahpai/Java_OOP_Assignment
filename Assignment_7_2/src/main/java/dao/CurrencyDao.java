package dao;

import entity.Currency;
import datasource.MariaDbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDao {

    /**
     * Get exchange rate for a specific currency
     */
    public double getExchangeRate(String abbreviation) throws SQLException {
        Connection conn = MariaDbConnection.getConnection();
        String sql = "SELECT rate_to_usd FROM CURRENCY WHERE abbreviation = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, abbreviation);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("rate_to_usd");
                } else {
                    throw new SQLException("Currency not found: " + abbreviation);
                }
            }
        }
    }

    /**
     * Get all currencies from database
     */
    public List<Currency> getAllCurrencies() throws SQLException {
        Connection conn = MariaDbConnection.getConnection();
        String sql = "SELECT abbreviation, name, rate_to_usd FROM CURRENCY ORDER BY abbreviation";
        List<Currency> currencies = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String abbreviation = rs.getString("abbreviation");
                String name = rs.getString("name");
                double rateToUSD = rs.getDouble("rate_to_usd");

                currencies.add(new Currency(abbreviation, name, rateToUSD));
            }
        }
        return currencies;
    }

    /**
     * Get a specific currency by abbreviation
     */
    public Currency getCurrency(String abbreviation) throws SQLException {
        Connection conn = MariaDbConnection.getConnection();
        String sql = "SELECT abbreviation, name, rate_to_usd FROM CURRENCY WHERE abbreviation = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, abbreviation);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    double rateToUSD = rs.getDouble("rate_to_usd");
                    return new Currency(abbreviation, name, rateToUSD);
                } else {
                    return null;
                }
            }
        }
    }
}