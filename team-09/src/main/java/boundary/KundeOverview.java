package boundary;


import entities.Kunde;
import service.KundeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class KundeOverview {

    private Kunde kunde;

    @Inject
    KundeService ks;

    public KundeOverview() {
        kunde = new Kunde();
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public Kunde update() {
        return ks.update(this.kunde);
    }

    public String save() {
        Logger.getLogger(KundeOverview.class.getCanonicalName()).info("kunde saved: "+ kunde);
        ks.save(kunde);
        return null;
    }

    public void remove() {
        ks.remove(this.kunde);
    }

    public Kunde find(Long kunde_id) {
        return ks.find(kunde_id);
    }

    public List<Kunde> getAll() {
        return ks.all();
    }


}
