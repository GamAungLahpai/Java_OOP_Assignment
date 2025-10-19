
-- Make sure we're using the correct database
USE currency_converter;

-- Query 1: Retrieve all currencies from the database
SELECT * FROM CURRENCY;

-- Query 2: Retrieve the currency with abbreviation EUR
SELECT * FROM CURRENCY WHERE abbreviation = 'EUR';

-- Query 3: Retrieve the number of currencies in the database
SELECT COUNT(*) AS total_currencies FROM CURRENCY;

-- Query 4: Retrieve the currency with the highest exchange rate
SELECT * FROM CURRENCY ORDER BY rate_to_usd DESC LIMIT 1;