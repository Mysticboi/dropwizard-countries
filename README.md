# dropwizard-countries

Trying the [Dropwizard Java framework](https://www.dropwizard.io/en/stable/) by creating a REST API for countries from a
json file.

## To run the project locally:

1. You must have a MySQL instance running, you can use [docker mysql](https://hub.docker.com/_/mysql) to create one
    1. Use the command `docker run --name mysql -e  MYSQL_ROOT_PASSWORD=root -d -p 3306:3306 mysql` to create a running
       container
    2. Copy the CreateTable SQL file into the running container `docker cp ./sql/create_tables.sql mysql:/home`
    3. Connect to the mysql container `docker exec -it mysql sh`
    4. Connect to MySQL `mysql -p` use `root` for password
    5. Run the script file `source /home/create_tables.sql`
    6. You can check the database and tables were created by running `USE countries;` and `SHOW tables;`
2. (Skip this if you've done point 5 of the previous step) Once the sql instance is running, run the script
   `sql/create_tables.sql` in your fav sql editor or through the mysql
   cli to create the database and tables. You can also use this script to drop the tables and recreate them from scratch
3. In `config.yml` set the `shouldInsertDate` to `true` to insert data from the json file. You can set this back
   to `false` when the database has been populated
4. Run `mvn package` to create the jar
5. Run the app: `java -jar target/CountryServer-1.0-SNAPSHOT.jar server config.yml`
6. Go to `localhost:5000/country`
7. More endpoints available that you can see in `resources.CountryResource`