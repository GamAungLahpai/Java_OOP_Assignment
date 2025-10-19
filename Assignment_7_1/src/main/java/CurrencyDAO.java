import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDAO {
    private Connection connection;

    public CurrencyDAO() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    /**
     * Retrieve all currencies from the database
     */
    public List<Currency> getAllCurrencies() throws SQLException {
        List<Currency> currencies = new ArrayList<>();
        String query = "SELECT abbreviation, name, rate_to_usd FROM CURRENCY ORDER BY abbreviation";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

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
     * Add a new currency to the database
     */
    public boolean addCurrency(Currency currency) throws SQLException {
        String query = "INSERT INTO CURRENCY (abbreviation, name, rate_to_usd) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, currency.getAbbreviation());
            stmt.setString(2, currency.getName());
            stmt.setDouble(3, currency.getRateToUSD());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    /**
     * Update exchange rate for an existing currency
     */
    public boolean updateCurrencyRate(String abbreviation, double newRate) throws SQLException {
        String query = "UPDATE CURRENCY SET rate_to_usd = ? WHERE abbreviation = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, newRate);
            stmt.setString(2, abbreviation);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    /**
     * Delete a currency from the database
     */
    public boolean deleteCurrency(String abbreviation) throws SQLException {
        String query = "DELETE FROM CURRENCY WHERE abbreviation = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, abbreviation);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    /**
     * Get a specific currency by abbreviation
     */
    public Currency getCurrencyByAbbreviation(String abbreviation) throws SQLException {
        String query = "SELECT abbreviation, name, rate_to_usd FROM CURRENCY WHERE abbreviation = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, abbreviation);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    double rateToUSD = rs.getDouble("rate_to_usd");
                    return new Currency(abbreviation, name, rateToUSD);
                }
            }
        }
        return null; // Currency not found
    }

    /**
     * Get the total count of currencies in the database
     */
    public int getCurrencyCount() throws SQLException {
        String query = "SELECT COUNT(*) as total FROM CURRENCY";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }
}