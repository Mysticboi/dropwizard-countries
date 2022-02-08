use countries;

DROP TABLE IF EXISTS language;
DROP TABLE IF EXISTS country_geo;
DROP TABLE IF EXISTS currency;
DROP TABLE IF EXISTS country;

CREATE TABLE country (
country_code varchar(5) PRIMARY KEY,
capital varchar(50),
official_name varchar(200),
common_name varchar(200),
flag varchar(200),
population int,
timezone varchar(20),
independent bool
);

CREATE TABLE IF NOT EXISTS language(
id int PRIMARY KEY AUTO_INCREMENT,
language_code varchar(5),
name varchar(50),
country_code varchar(5),
FOREIGN KEY(country_code) REFERENCES country(country_code)
);


CREATE TABLE country_geo(
country_code varchar(5) PRIMARY KEY,
area float,
latitude float,
longitude float,
region varchar(30),
subregion varchar(100),
continent varchar(20),
FOREIGN KEY(country_code) REFERENCES country(country_code)
);

CREATE TABLE currency(
id int PRIMARY KEY AUTO_INCREMENT,
currency_code varchar(10),
name varchar(50),
symbol varchar(20),
country_code varchar(5),
FOREIGN KEY(country_code) REFERENCES country(country_code)
);