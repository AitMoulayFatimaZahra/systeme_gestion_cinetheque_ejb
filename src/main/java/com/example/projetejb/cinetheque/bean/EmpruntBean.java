package com.example.projetejb.cinetheque.bean;
import com.example.projetejb.cinetheque.model.Emprunt;
import com.example.projetejb.cinetheque.service.EmpruntService;
import jakarta.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class EmpruntBean {
    @EJB
    private EmpruntService empruntService;

    public List<Emprunt> getEmpruntsUtilisateur() {
        return empruntService.listerEmprunts("utilisateur"); // Remplacer par l'ID de l'utilisateur
    }

    public void retournerCD(Long empruntId) {
        empruntService.retournerCD(empruntId);
    }
}
