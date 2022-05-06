package security;

import service.StaffService;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.io.Serializable;
import java.util.*;

import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;


@Named
@RequestScoped
public class InMemoryIdentityStore4Authentication implements IdentityStore{

    private static final Map<String, String> users = new HashMap<>();

    @Inject
    private StaffService ss;

    public InMemoryIdentityStore4Authentication() {
        // init users and passwords
        init();
    }

    public void init() {
        // init username and password
        if (ss != null) {
            ss.findAll().forEach(s -> users.put(s.getEmail(), s.getPhone()));
        }
        // users.put("fabiola.jackson@bikes.shop", "(831) 555-5554");
    }

    @Override
    public int priority() {
        return 70;
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return EnumSet.of(ValidationType.VALIDATE);
    }

    public CredentialValidationResult validate(UsernamePasswordCredential credential) {
        String password = users.get(credential.getCaller());
        if (password != null && password.equals(credential.getPasswordAsString())) {
            return new CredentialValidationResult(credential.getCaller());
        }
        return INVALID_RESULT;
    }
}
