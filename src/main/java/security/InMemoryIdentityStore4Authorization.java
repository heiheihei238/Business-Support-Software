/*
package security;

import controller.StaffController;
import entities.Staff;
import service.StaffService;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.io.Serializable;
import java.util.*;

@Named
@RequestScoped
class InMemoryIdentityStore4Authorization implements IdentityStore {

    private static final Map<String, List<String>> userRoles = new HashMap<>();

    @Inject
    private StaffService ss;

    public InMemoryIdentityStore4Authorization() {
        // Init users and roles
        init();
//        List<String> roles = new ArrayList<>();
//        roles.add("admin");
//        roles.add("user1");
//        roles.add("user2");
//        userRoles.put("fabiola.jackson@bikes.shop", roles);
    }

    public void init() {
        if (ss != null) {
            // admin
            List<String> roles = new ArrayList<>();
            roles.add("admin");
            roles.add("user1");
            roles.add("user2");
            userRoles.put("fabiola.jackson@bikes.shop", roles);
            // user1
            roles = new ArrayList<>();
            roles.add("user1");
            roles.add("user2");
            List<String> roles2 = roles;
            ss.findByManagerId(1).forEach(s -> userRoles.put(s.getEmail(), roles2));
            // user2
            roles = new ArrayList<>();
            roles.add("user2");
            List<String> roles3 = roles;
            ss.findByManagerIdNot(1).forEach(s -> userRoles.put(s.getEmail(), roles3));
            userRoles.put("fabiola.jackson@bikes.shop", roles3);
        }
    }

    @Override
    public int priority() {
        return 80;
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return EnumSet.of(ValidationType.PROVIDE_GROUPS);
    }

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        List<String> roles = userRoles.get(validationResult.getCallerPrincipal().getName());
        return new HashSet<>(roles);
    }
}
*/
