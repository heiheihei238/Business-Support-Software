package boundary;


import entities.Kunde;
import service.KundeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class KundeOverview {
    @Inject
    KundeService ks;

    public List<Kunde> getAll() {
        return ks.all();
    }
}
