package security;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;


@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/sc/login.xhtml",
                errorPage = "/sc/login-error.xhtml"))
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:jboss/datasources/bikes9",
        callerQuery = "select password from users where username = ?",
        groupsQuery = "select GROUPNAME from groups where username = ?"
)
@ApplicationScoped
public class AppConfig {
}
