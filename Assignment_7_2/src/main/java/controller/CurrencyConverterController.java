package controller;

import dao.CurrencyDao;
import entity.Currency;
import view.CurrencyConverterView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.sql.SQLException;
import java.util.List;

public class CurrencyConverterController {
    private CurrencyConverterView view;
    private CurrencyDao currencyDao;
    private ObservableList<Currency> currencies;

    public CurrencyConverterController(CurrencyConverterView view) {
        this.view = view;
        this.currencyDao = new CurrencyDao();
        this.currencies = FXCollections.observableArrayList();

        initializeApplication();
    }

    private void initializeApplication() {
        try {
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

            System.out.println("Application initialized successfully with " + currencies.size() + " currencies from database");

        } catch (SQLException e) {
            handleDatabaseError("Failed to load currencies from database", e);
        }
    }

    private void loadCurrenciesFromDatabase() throws SQLException {
        List<Currency> dbCurrencies = currencyDao.getAllCurrencies();
        currencies.clear();
        currencies.addAll(dbCurrencies);
    }

    private void handleConversion() {
        view.hideError();

        try {
            // Validate input
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

            // Get selected currencies
            Currency fromCurrency = view.getFromCurrencyBox().getValue();
            Currency toCurrency = view.getToCurrencyBox().getValue();

            if (fromCurrency == null || toCurrency == null) {
                view.showError("Please select both currencies.");
                return;
            }

            // Fetch fresh exchange rates from database
            double fromRate = currencyDao.getExchangeRate(fromCurrency.getAbbreviation());
            double toRate = currencyDao.getExchangeRate(toCurrency.getAbbreviation());

            // Perform conversion
            double result = convertCurrency(amount, fromRate, toRate);

            // Display result
            view.getResultField().setText(String.format("%.2f", result));

        } catch (NumberFormatException e) {
            view.showError("Please enter a valid numeric amount.");
        } catch (SQLException e) {
            handleDatabaseError("Failed to fetch exchange rates", e);
        } catch (Exception e) {
            view.showError("An error occurred during conversion: " + e.getMessage());
        }
    }

    private double convertCurrency(double amount, double fromRate, double toRate) {
        // Convert to USD first, then to target currency
        double amountInUSD = amount / fromRate;
        return amountInUSD * toRate;
    }

    private void handleDatabaseError(String message, SQLException e) {
        String errorMessage = message + "\n\nDatabase Error: " + e.getMessage() +
                "\n\nPlease check that MariaDB is running and the database is accessible.";

        // Show error in UI
        view.showError("Database connection failed. Please check database connection.");

        // Show detailed error dialog
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Error");
        alert.setHeaderText(message);
        alert.setContentText("Error: " + e.getMessage() +
                "\n\nSuggestions:\n" +
                "1. Ensure MariaDB is running\n" +
                "2. Check database connection settings\n" +
                "3. Verify database and user exist");
        alert.showAndWait();

        System.err.println("Database Error: " + errorMessage);
        e.printStackTrace();
    }
}