package com.example.projetejb.cinetheque.service;
import com.example.projetejb.cinetheque.model.Emprunt;
import java.util.ArrayList;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EmpruntService {
    @PersistenceContext
    private EntityManager em;

    @EJB
    private CDService cdService;

    public void emprunterCD(Long cdId, String emprunteur) {
        CD cd = cdService.trouverCD(cdId);
        if (cd != null && cd.isDisponible()) {
            cd.setDisponible(false);
            em.merge(cd);

            Emprunt emprunt = new Emprunt();
            emprunt.setCd(cd);
            emprunt.setEmprunteur(emprunteur);
            emprunt.setDateEmprunt(LocalDate.now());
            em.persist(emprunt);
        }
    }

    public List<Emprunt> listerEmprunts(String emprunteur) {
        return em.createQuery("SELECT e FROM Emprunt e WHERE e.emprunteur = :emprunteur", Emprunt.class)
                .setParameter("emprunteur", emprunteur)
                .getResultList();
    }

    public void retournerCD(Long empruntId) {
        Emprunt emprunt = em.find(Emprunt.class, empruntId);
        if (emprunt != null) {
            CD cd = emprunt.getCd();
            cd.setDisponible(true);
            em.merge(cd);
            em.remove(emprunt);
        }
    }

        private List<Emprunt> empruntList = new ArrayList<>();

        public void emprunterCD(CD cd, String emprunteur) {
            Emprunt emprunt = new Emprunt(cd, java.time.LocalDate.now(), emprunteur);
            empruntList.add(emprunt);
        }

        public List<Emprunt> listerEmprunts() {
            return new ArrayList<>(empruntList);
        }

}
