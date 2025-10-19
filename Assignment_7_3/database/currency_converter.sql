
-- 1. Drop the database if it exists
DROP DATABASE IF EXISTS currency_converter;

-- 2. Create the database
CREATE DATABASE currency_converter;

-- Select the database to use
USE currency_converter;

-- 3. Create the CURRENCY table
CREATE TABLE CURRENCY (
                          id INT NOT NULL AUTO_INCREMENT,
                          abbreviation VARCHAR(10) NOT NULL UNIQUE,
                          name VARCHAR(100) NOT NULL,
                          rate_to_usd DECIMAL(15, 6) NOT NULL,
                          PRIMARY KEY (id)
);

-- 4. Insert currency data with up-to-date exchange rates (as of 2025)
-- Rate represents how many of this currency equals 1 USD
INSERT INTO CURRENCY (abbreviation, name, rate_to_usd) VALUES
                                                           ('USD', 'US Dollar', 1.000000),
                                                           ('EUR', 'Euro', 0.920000),
                                                           ('GBP', 'British Pound', 0.790000),
                                                           ('JPY', 'Japanese Yen', 149.500000),
                                                           ('CAD', 'Canadian Dollar', 1.370000),
                                                           ('AUD', 'Australian Dollar', 1.540000),
                                                           ('CHF', 'Swiss Franc', 0.880000),
                                                           ('CNY', 'Chinese Yuan', 7.240000),
                                                           ('SEK', 'Swedish Krona', 10.870000),
                                                           ('NZD', 'New Zealand Dollar', 1.680000);

-- 5. Drop the user account if it exists
DROP USER IF EXISTS 'appuser'@'localhost';

-- 6. Create the user account appuser
CREATE USER 'appuser'@'localhost' IDENTIFIED BY 'password123';

-- 7. Grant privileges to the user account
-- The application needs SELECT (read currencies), INSERT (add new currencies),
-- UPDATE (update exchange rates), and DELETE (remove currencies) privileges
GRANT SELECT, INSERT, UPDATE, DELETE ON currency_converter.* TO 'appuser'@'localhost';

-- Flush privileges to ensure changes take effect
FLUSH PRIVILEGES;