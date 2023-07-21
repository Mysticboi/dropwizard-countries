# dropwizard-countries

Trying the [Dropwizard Java framework](https://www.dropwizard.io/en/stable/) by creating a REST API for countries from a
json file.

## To run the project locally:

1. You must have a MySQL instance running, you can use [docker mysql](https://hub.docker.com/_/mysql) to create one
2. Once the sql instance is running, run the script `sql/create_tables.sql` in your fav sql editor or through the mysql
   cli to create the database and tables. You can also use this script to drop the tables and recreate them from scratch
3. In `config.yml` set the `shouldInsertDate` to `true` to insert data from the json file. You can set this back
   to `false` when the database has been populated
4. Run `mvn package` to create the jar
5. Run the app: `java -jar target/CountryServer-1.0-SNAPSHOT.jar server config.yml`
6. Go to `localhost:5000/country`
7. More endpoints available that you can see in `resources.CountryResource`