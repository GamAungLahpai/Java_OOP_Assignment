
-- 1. Create the database
CREATE DATABASE IF NOT EXISTS currency_converter2;

-- 2. Grant privileges to appuser
GRANT SELECT, INSERT, UPDATE, DELETE ON currency_converter2.* TO 'appuser'@'localhost';

-- 3. Grant schema manipulation privileges (needed for drop-and-create)
GRANT CREATE, DROP, ALTER ON currency_converter2.* TO 'appuser'@'localhost';

-- 4. Flush privileges
FLUSH PRIVILEGES;

-- 5. Use the new database
USE currency_converter2;

