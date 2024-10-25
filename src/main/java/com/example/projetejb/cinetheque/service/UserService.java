package com.example.projetejb.cinetheque.service;


import com.example.projetejb.cinetheque.model.CD;
import com.example.projetejb.cinetheque.model.Emprunt;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    private List<Emprunt> emprunts = new ArrayList<>(); // Liste des emprunts en mémoire

    public void emprunterCD(CD cd, String emprunteur) {
        Emprunt nouvelEmprunt = new Emprunt(cd, LocalDate.now(), emprunteur);
        emprunts.add(nouvelEmprunt);
        // Vous pouvez également persister l'emprunt dans la base de données
        entityManager.persist(nouvelEmprunt);
    }

    public void retournerCD(Emprunt emprunt) {
        emprunts.remove(emprunt);
        // Ajoutez ici la logique pour mettre à jour l'état dans la base de données si nécessaire
    }

    public List<Emprunt> voirEmprunts() {
        return new ArrayList<>(emprunts);
    }
}

