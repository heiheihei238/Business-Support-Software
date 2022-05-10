package security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.sql.DataSource;
import javax.transaction.Transactional;

@Singleton
@Startup
public class DatabaseSetup {

    // The default datasource that is bundled with GlassFish is used to store // credentials.
    @Resource(lookup="java:jboss/datasources/bikes9")
    private DataSource dataSource;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @PostConstruct
    public void init() throws SQLException {
        // create table users and groups
        executeUpdate(dataSource, "DROP TABLE IF EXISTS USERS");
        executeUpdate(dataSource, "DROP TABLE IF EXISTS GROUPS");
        executeUpdate(dataSource, "CREATE TABLE IF NOT EXISTS USERS(username VARCHAR(64) PRIMARY KEY, password VARCHAR(255))");
        executeUpdate(dataSource, "CREATE TABLE IF NOT EXISTS GROUPS(username VARCHAR(64), GROUPNAME VARCHAR(64))");

        // insert data into users and groups
        authentication(dataSource);
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

    // find username(email) and passwords(phone) in the staffs table
    private void authentication(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM STAFFS")) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String email = resultSet.getString("EMAIL");
                    String phone = resultSet.getString("PHONE");
                    String phoneWithoutAreaCode = getPhoneNumberWithoutAreaCode(phone);
                    int managerId = resultSet.getInt("MANAGER_ID");
                    // insert username and password into the user table
                    executeUpdate(dataSource, "INSERT INTO USERS VALUES ('" + email + "','" + passwordHash.generate(phoneWithoutAreaCode.toCharArray()) + "')");
                    // insert username and role into the groups' table
                    // admin
                    if(managerId == 0) {
                        executeUpdate(dataSource, "INSERT INTO GROUPS VALUES ('" + email + "','admin')");
                        executeUpdate(dataSource, "INSERT INTO GROUPS VALUES ('" + email + "','user1')");
                        executeUpdate(dataSource, "INSERT INTO GROUPS VALUES ('" + email + "','user2')");
                    }
                    // user1
                    else if(managerId == 1) {
                        executeUpdate(dataSource, "INSERT INTO GROUPS VALUES ('" + email + "','user1')");
                        executeUpdate(dataSource, "INSERT INTO GROUPS VALUES ('" + email + "','user2')");
                    }
                    // user2
                    else executeUpdate(dataSource, "INSERT INTO GROUPS VALUES ('" + email + "','user2')");
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private String getPhoneNumberWithoutAreaCode(String phoneNumber){
        String[] parts = phoneNumber.split(" ");
        return parts[1];
    }
}