# GitPod POC (Java)

This is a basic Java Dropwizard HTTP server. 

The main libraries used in this server are [Dropwizard 1.3](https://www.dropwizard.io/en/stable/index.html), an HTTP web framework, and [JDBI 3](https://jdbi.org/), a database access framework.

## Getting Started

To get started, make sure you have shutdown both the webserver and database server from the envcheck.  Then, start the database with:
```bash
$ ./start_db.sh
```

Then, either use your IDE to build, run migrations, and run the server with main class `ServiceApplication.java`, or build, run migrations, and run the server in a new terminal window:
```bash
$ mvn install
$ java -jar "./target/poc-dropwizard-docker-0.0.1-SNAPSHOT.jar" db migrate configuration.yml
$ java -jar "./target/poc-dropwizard-docker-0.0.1-SNAPSHOT.jar" server configuration.yml
```

The server starts by default on `localhost:8080`.

To test further changes, you'll probably just want to re-run `mvn install`, and then restart the server.

You can see a test account with:
```bash
$ curl localhost:8080/accounts/1
```

## Migrations
Migrations are run via [Dropwizard Flyway](https://github.com/dropwizard/dropwizard-flyway).  You can add new migrations in the `migrations` directory with a `V` prefix and a `.sql` suffix.  To run a new migration, you must rebuild with `mvn install`, and then you can run migrations with the `db migrate` command as described above.
