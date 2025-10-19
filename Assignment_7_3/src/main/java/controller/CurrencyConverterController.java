package controller;

import dao.CurrencyDao;
import entity.Currency;
import view.CurrencyConverterView;
import view.AddCurrencyDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.util.List;

public class CurrencyConverterController {
    private CurrencyConverterView view;
    private CurrencyDao currencyDao;
    private ObservableList<Currency> currencies;
    private Stage primaryStage;

    public CurrencyConverterController(CurrencyConverterView view, Stage primaryStage) {
        this.view = view;
        this.primaryStage = primaryStage;
        this.currencyDao = new CurrencyDao();
        this.currencies = FXCollections.observableArrayList();

        initializeApplication();
    }

    private void initializeApplication() {
        try {
            // Check database connection
            if (!currencyDao.isDatabaseAvailable()) {
                handleDatabaseConnectionError();
                return;
            }

            // Load currencies from database using JPA
            loadCurrenciesFromDatabase();

            // Set up UI
            setupUI();

            // Set up event handlers
            setupEventHandlers();

            view.setStatus("Connected to database with JPA - " + currencies.size() + " currencies loaded");
            System.out.println("JPA Currency Converter initialized successfully with " + currencies.size() + " currencies");

        } catch (Exception e) {
            handleDatabaseConnectionError();
            e.printStackTrace();
        }
    }

    private void loadCurrenciesFromDatabase() {
        try {
            List<Currency> dbCurrencies = currencyDao.findAll();
            currencies.clear();
            currencies.addAll(dbCurrencies);
            System.out.println("Loaded " + currencies.size() + " currencies using JPA");
        } catch (Exception e) {
            System.err.println("Failed to load currencies from database: " + e.getMessage());
            throw e;
        }
    }

    private void setupUI() {
        // Populate combo boxes
        view.getFromCurrencyBox().setItems(currencies);
        view.getToCurrencyBox().setItems(currencies);

        // Set default selections if currencies available
        if (!currencies.isEmpty()) {
            view.getFromCurrencyBox().setValue(currencies.get(0));
            if (currencies.size() > 1) {
                view.getToCurrencyBox().setValue(currencies.get(1));
            }
        }
    }

    private void setupEventHandlers() {
        // Convert button
        view.getConvertButton().setOnAction(e -> handleConversion());

        // Add currency button
        view.getAddCurrencyButton().setOnAction(e -> handleAddCurrency());
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

            // Fetch fresh exchange rates using JPA
            double fromRate = currencyDao.getExchangeRate(fromCurrency.getAbbreviation());
            double toRate = currencyDao.getExchangeRate(toCurrency.getAbbreviation());

            // Perform conversion
            double result = convertCurrency(amount, fromRate, toRate);

            // Display result
            view.getResultField().setText(String.format("%.2f", result));
            view.setStatus("Conversion completed using JPA data");

        } catch (NumberFormatException e) {
            view.showError("Please enter a valid numeric amount.");
        } catch (Exception e) {
            view.showError("Database error during conversion: " + e.getMessage());
            handleDatabaseError("Failed to fetch exchange rates", e);
        }
    }

    private void handleAddCurrency() {
        try {
            AddCurrencyDialog dialog = new AddCurrencyDialog(primaryStage);

            if (dialog.showAndWait()) {
                Currency newCurrency = dialog.getResult();

                // Check if currency already exists
                Currency existing = currencyDao.findByAbbreviation(newCurrency.getAbbreviation());
                if (existing != null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Currency Exists");
                    alert.setHeaderText("Currency Already Exists");
                    alert.setContentText("A currency with abbreviation '" + newCurrency.getAbbreviation() + "' already exists in the database.");
                    alert.showAndWait();
                    return;
                }

                // Persist new currency using JPA
                boolean success = currencyDao.persist(newCurrency);

                if (success) {
                    // Refresh currency list
                    refreshCurrencyList();

                    // Select the new currency in dropdowns
                    selectNewCurrency(newCurrency);

                    // Show success message
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Currency Added");
                    alert.setContentText("Currency '" + newCurrency.getAbbreviation() + "' has been successfully added to the database.");
                    alert.showAndWait();

                    view.setStatus("New currency added: " + newCurrency.getAbbreviation());

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to Add Currency");
                    alert.setContentText("Could not add the currency to the database. Please check the database connection and try again.");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            handleDatabaseError("Failed to add new currency", e);
        }
    }

    private void refreshCurrencyList() {
        try {
            // Store current selections
            Currency currentFrom = view.getFromCurrencyBox().getValue();
            Currency currentTo = view.getToCurrencyBox().getValue();

            // Reload currencies from database
            loadCurrenciesFromDatabase();

            // Update combo boxes
            view.getFromCurrencyBox().setItems(currencies);
            view.getToCurrencyBox().setItems(currencies);

            // Restore selections if still available
            if (currentFrom != null) {
                Currency newFrom = findCurrencyByAbbreviation(currentFrom.getAbbreviation());
                if (newFrom != null) {
                    view.getFromCurrencyBox().setValue(newFrom);
                }
            }

            if (currentTo != null) {
                Currency newTo = findCurrencyByAbbreviation(currentTo.getAbbreviation());
                if (newTo != null) {
                    view.getToCurrencyBox().setValue(newTo);
                }
            }

        } catch (Exception e) {
            System.err.println("Failed to refresh currency list: " + e.getMessage());
        }
    }

    private void selectNewCurrency(Currency newCurrency) {
        // Find the currency in the refreshed list and select it
        Currency foundCurrency = findCurrencyByAbbreviation(newCurrency.getAbbreviation());
        if (foundCurrency != null) {
            view.getFromCurrencyBox().setValue(foundCurrency);
        }
    }

    private Currency findCurrencyByAbbreviation(String abbreviation) {
        return currencies.stream()
                .filter(c -> c.getAbbreviation().equals(abbreviation))
                .findFirst()
                .orElse(null);
    }

    private double convertCurrency(double amount, double fromRate, double toRate) {
        // Convert to USD first, then to target currency
        double amountInUSD = amount / fromRate;
        return amountInUSD * toRate;
    }

    private void handleDatabaseConnectionError() {
        view.showError("Cannot connect to database. Please check that MariaDB is running and the database exists.");
        view.setStatus("Database connection failed");

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Connection Error");
        alert.setHeaderText("Cannot Connect to Database");
        alert.setContentText("Unable to establish connection to the MariaDB database.\n\n" +
                "Please ensure:\n" +
                "1. MariaDB service is running\n" +
                "2. Database 'currency_converter' exists\n" +
                "3. User 'appuser' has proper permissions\n" +
                "4. Connection settings in persistence.xml are correct");
        alert.showAndWait();
    }

    private void handleDatabaseError(String message, Exception e) {
        String errorMessage = message + "\n\nError: " + e.getMessage();

        view.showError("Database error occurred. Please check the console for details.");

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Error");
        alert.setHeaderText(message);
        alert.setContentText("Error: " + e.getMessage());
        alert.showAndWait();

        System.err.println("Database Error: " + errorMessage);
        e.printStackTrace();
    }
}