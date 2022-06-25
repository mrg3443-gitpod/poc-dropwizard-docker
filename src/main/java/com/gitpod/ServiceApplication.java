package com.gitpod;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayBundle;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class ServiceApplication extends Application<ServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new ServiceApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
        // Register the flyway commands
        bootstrap.addBundle(new FlywayBundle<ServiceConfiguration>(){
            @Override
            public DataSourceFactory getDataSourceFactory(ServiceConfiguration config) {
                return config.getDatabase();
            }

            @Override
            public FlywayFactory getFlywayFactory(ServiceConfiguration config) {
                return config.getFlyway();
            }
        });
    }

    @Override
    public void run(ServiceConfiguration config, Environment env) throws Exception {
        // Get a database handle
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(env, config.getDatabase(), "poc");

        // Build and register a resource
        AccountDAO accountDAO = new AccountDAO(jdbi);
        env.jersey().register(new AccountsResource(accountDAO));
    }
}
