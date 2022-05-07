package security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.sql.DataSource;

@Singleton
@Startup
public class DatabaseSetup {

    // The default datasource that is bundled with GlassFish is used to store // credentials.
    @Resource(lookup="java:jboss/datasources/bikes9")
    private DataSource dataSource;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @PostConstruct
    public void init() {

        executeUpdate(dataSource, "DROP TABLE IF EXISTS USERS");
        executeUpdate(dataSource, "DROP TABLE IF EXISTS GROUPS");

        executeUpdate(dataSource, "CREATE TABLE IF NOT EXISTS USERS(username VARCHAR(64) PRIMARY KEY, password VARCHAR(255))");
        executeUpdate(dataSource, "CREATE TABLE IF NOT EXISTS GROUPS(username VARCHAR(64), GROUPNAME VARCHAR(64))");

        executeUpdate(dataSource, "INSERT INTO USERS VALUES('fabiola.jackson@bikes.shop', '" + passwordHash.generate("(831) 555-5554".toCharArray()) + "')");
        executeUpdate(dataSource, "INSERT INTO USERS VALUES('mireya.copeland@bikes.shop', '" + passwordHash.generate("(831) 555-5555".toCharArray()) + "')");

        executeUpdate(dataSource, "INSERT INTO GROUPS VALUES('fabiola.jackson@bikes.shop', 'admin')");
        executeUpdate(dataSource, "INSERT INTO GROUPS VALUES('mireya.copeland@bikes.shop', 'user1')");
        //executeUpdate(dataSource, "INSERT INTO GROUPS VALUES('user', 'user_role')");
    }

    @PreDestroy
    public void destroy() {
        // ...
    }

    private void executeUpdate(DataSource dataSource, String query) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}