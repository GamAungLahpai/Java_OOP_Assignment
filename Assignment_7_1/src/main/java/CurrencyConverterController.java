import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.List;

public class CurrencyConverterController {
    private CurrencyConverterView view;
    private ObservableList<Currency> currencies;
    private CurrencyDAO currencyDAO;

    public CurrencyConverterController(CurrencyConverterView view) {
        this.view = view;
        this.currencies = FXCollections.observableArrayList();

        try {
            // Initialize database connection and DAO
            this.currencyDAO = new CurrencyDAO();

            // Load currencies from database
            loadCurrenciesFromDatabase();

            // Populate combo boxes
            view.getFromCurrencyBox().setItems(currencies);
            view.getToCurrencyBox().setItems(currencies);

            // Set default selections
            if (!currencies.isEmpty()) {
                view.getFromCurrencyBox().setValue(currencies.get(0));
                view.getToCurrencyBox().setValue(currencies.get(1));
            }

            // Set up event handling
            view.getConvertButton().setOnAction(e -> handleConversion());

        } catch (SQLException e) {
            showDatabaseError("Failed to connect to database", e);
            // Fallback to hardcoded currencies if database fails
            initializeHardcodedCurrencies();
        }
    }

    /**
     * Load currencies from the database
     */
    private void loadCurrenciesFromDatabase() throws SQLException {
        List<Currency> dbCurrencies = currencyDAO.getAllCurrencies();
        currencies.clear();
        currencies.addAll(dbCurrencies);

        System.out.println("Loaded " + currencies.size() + " currencies from database");
    }

    /**
     * Fallback method: Initialize hardcoded currencies if database fails
     */
    private void initializeHardcodedCurrencies() {
        currencies.clear();
        currencies.addAll(List.of(
                new Currency("USD", "US Dollar", 1.00),
                new Currency("EUR", "Euro", 0.92),
                new Currency("GBP", "British Pound", 0.79),
                new Currency("JPY", "Japanese Yen", 149.50),
                new Currency("CAD", "Canadian Dollar", 1.37),
                new Currency("AUD", "Australian Dollar", 1.54),
                new Currency("CHF", "Swiss Franc", 0.88),
                new Currency("CNY", "Chinese Yuan", 7.24),
                new Currency("SEK", "Swedish Krona", 10.87),
                new Currency("NZD", "New Zealand Dollar", 1.68)
        ));

        // Populate combo boxes
        view.getFromCurrencyBox().setItems(currencies);
        view.getToCurrencyBox().setItems(currencies);

        // Set default selections
        if (!currencies.isEmpty()) {
            view.getFromCurrencyBox().setValue(currencies.get(0));
            view.getToCurrencyBox().setValue(currencies.get(1));
        }

        // Set up event handling
        view.getConvertButton().setOnAction(e -> handleConversion());

        System.out.println("Using hardcoded currencies (database unavailable)");
    }

    private void handleConversion() {
        view.hideError();

        try {
            // Validate input amount
            String amountText = view.getAmountField().getText().trim();
            if (amountText.isEmpty()) {
                view.showError("Please enter an amount to convert.");
                return;
            }

            double amount = Double.parseDouble(amountText);

            if (amount < 0) {
                view.showError("Amount cannot be negative.");
                return;
            }

            // Validate currency selections
            Currency fromCurrency = view.getFromCurrencyBox().getValue();
            Currency toCurrency = view.getToCurrencyBox().getValue();

            if (fromCurrency == null || toCurrency == null) {
                view.showError("Please select both currencies.");
                return;
            }

            // Perform conversion
            double result = convertCurrency(amount, fromCurrency, toCurrency);

            // Display result with 2 decimal places
            view.getResultField().setText(String.format("%.2f", result));

        } catch (NumberFormatException e) {
            view.showError("Please enter a valid numeric amount.");
        } catch (Exception e) {
            view.showError("An error occurred during conversion: " + e.getMessage());
        }
    }

    /**
     * Converts an amount from one currency to another.
     * Uses the same logic as before but currencies now come from database.
     */
    private double convertCurrency(double amount, Currency from, Currency to) {
        // Convert to USD first, then to target currency
        double amountInUSD = amount / from.getRateToUSD();
        double result = amountInUSD * to.getRateToUSD();
        return result;
    }

    /**
     * Refresh currencies from database
     */
    public void refreshCurrencies() {
        try {
            loadCurrenciesFromDatabase();
            view.showError(""); // Clear any previous errors
        } catch (SQLException e) {
            showDatabaseError("Failed to refresh currencies", e);
        }
    }

    /**
     * Add a new currency to the database
     */
    public boolean addCurrency(String abbreviation, String name, double rateToUSD) {
        try {
            Currency newCurrency = new Currency(abbreviation, name, rateToUSD);
            boolean success = currencyDAO.addCurrency(newCurrency);

            if (success) {
                refreshCurrencies(); // Reload from database
                return true;
            }
        } catch (SQLException e) {
            showDatabaseError("Failed to add currency", e);
        }
        return false;
    }

    /**
     * Update exchange rate for a currency
     */
    public boolean updateCurrencyRate(String abbreviation, double newRate) {
        try {
            boolean success = currencyDAO.updateCurrencyRate(abbreviation, newRate);

            if (success) {
                refreshCurrencies(); // Reload from database
                return true;
            }
        } catch (SQLException e) {
            showDatabaseError("Failed to update currency rate", e);
        }
        return false;
    }

    private void showDatabaseError(String message, SQLException e) {
        String errorMessage = message + "\n\nDatabase Error: " + e.getMessage();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Error");
        alert.setHeaderText(message);
        alert.setContentText(e.getMessage());
        alert.showAndWait();

        System.err.println(errorMessage);
        e.printStackTrace();
    }

    public ObservableList<Currency> getCurrencies() {
        return currencies;
    }

    public CurrencyDAO getCurrencyDAO() {
        return currencyDAO;
    }
}