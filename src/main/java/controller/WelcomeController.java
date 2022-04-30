package controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class WelcomeController {

    public String goAdmin() {
        return "/sc/admin/home.xhtml?faces-redirect=true";
    }

    public String goManager() {
        return "/sc/user1/home.xhtml?faces-redirect=true";
    }

    public String goUser() {
        return "/sc/user2/home.xhtml?faces-redirect=true";
    }
}
