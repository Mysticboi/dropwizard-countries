use countries;

# 34 seconds for country insertion (Done alone)
SELECT * FROM country;

# Delete all from country 
DELETE FROM country WHERE country_code != "";

# 91 seconds for country_geo insertion (Done alone)
SELECT * FROM country_geo;

# 94 seconds for language insertion (Done alone)
SELECT * FROM language;

# Change currency.symbol column to varchar(20) instead of 5
ALTER TABLE currency MODIFY symbol varchar(20);

# 61 seconds for currency insertion (Done alone)
SELECT * FROM currency;

# Delete all from currency
DELETE FROM currency WHERE id >0;

# Total time for all insertion using handle only: 282 seconds = 4,7 minutes
# Check jdbi batches in documentation for better performance

# Get info on France
SELECT latitude,longitude,population
FROM country c INNER JOIN country_geo cg ON c.country_code = cg.country_code
WHERE c.common_name="France";

# For each country get its languages
SELECT l1.country_code, GROUP_CONCAT(DISTINCT(l1.name)) FROM language l1, language l2
WHERE l1.country_code = l2.country_code
GROUP BY l1.country_code;