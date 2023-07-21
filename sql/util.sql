CREATE DATABASE IF NOT EXISTS countries;
USE countries;

SELECT * FROM country;
SELECT * FROM country_geo;
SELECT * FROM language;
SELECT * FROM currency;

-- Change currency.symbol column to varchar(20) instead of 5
ALTER TABLE currency MODIFY symbol varchar(20);

-- Delete all from country
DELETE FROM country WHERE country_code != "";

-- Delete all from currency
DELETE FROM currency WHERE id >0;


-- Get info on France
SELECT latitude,longitude,population
FROM country c INNER JOIN country_geo cg ON c.country_code = cg.country_code
WHERE c.common_name="France";

-- For each country get its languages
SELECT l1.country_code, GROUP_CONCAT(DISTINCT(l1.name)) FROM language l1, language l2
WHERE l1.country_code = l2.country_code
GROUP BY l1.country_code;