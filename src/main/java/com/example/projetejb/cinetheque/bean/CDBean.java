import com.example.projetejb.cinetheque.model.CD;
import com.example.projetejb.cinetheque.service.CDService;
import jakarta.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class CDBean {
    @EJB
    private CDService cdService;

    public List<CD> getCdsDisponibles() {
        return cdService.listerCDsDisponibles();
    }

    public void emprunterCD(Long cdId) {
        cdService.emprunterCD(cdId, "utilisateur"); // Utilisateur temporaire, remplacer par identifiant réel
    }
}
