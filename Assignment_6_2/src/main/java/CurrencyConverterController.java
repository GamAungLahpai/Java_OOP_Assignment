import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CurrencyConverterController {
    private CurrencyConverterView view;
    private ObservableList<Currency> currencies;

    public CurrencyConverterController(CurrencyConverterView view) {
        this.view = view;
        this.currencies = FXCollections.observableArrayList();

        // Initialize hardcoded currencies (rates to USD)
        initializeCurrencies();

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
    }

    private void initializeCurrencies() {
        // Hardcoded currencies with conversion rates to USD
        // Rate represents how many of this currency equals 1 USD
        currencies.add(new Currency("USD", "US Dollar", 1.00));
        currencies.add(new Currency("EUR", "Euro", 0.92));
        currencies.add(new Currency("GBP", "British Pound", 0.79));
        currencies.add(new Currency("JPY", "Japanese Yen", 149.50));
        currencies.add(new Currency("CAD", "Canadian Dollar", 1.37));
        currencies.add(new Currency("AUD", "Australian Dollar", 1.54));
        currencies.add(new Currency("CHF", "Swiss Franc", 0.88));
        currencies.add(new Currency("CNY", "Chinese Yuan", 7.24));
        currencies.add(new Currency("SEK", "Swedish Krona", 10.87));
        currencies.add(new Currency("NZD", "New Zealand Dollar", 1.68));
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
     *
     * The conversion works by:
     * 1. Converting the source amount to USD using the source currency's rate
     * 2. Converting the USD amount to the target currency using the target currency's rate
     *
     * Formula: result = (amount / fromRate) * toRate
     *
     * Example: Converting 100 EUR to GBP
     * - EUR rate to USD: 0.92 (1 USD = 0.92 EUR)
     * - GBP rate to USD: 0.79 (1 USD = 0.79 GBP)
     * - First convert EUR to USD: 100 / 0.92 = 108.70 USD
     * - Then convert USD to GBP: 108.70 * 0.79 = 85.87 GBP
     */
    private double convertCurrency(double amount, Currency from, Currency to) {
        // Convert to USD first, then to target currency
        double amountInUSD = amount / from.getRateToUSD();
        double result = amountInUSD * to.getRateToUSD();
        return result;
    }

    public ObservableList<Currency> getCurrencies() {
        return currencies;
    }
}