package com.example.projetejb.cinetheque.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class CDService {
    @PersistenceContext
    private EntityManager em;

    // Liste les CD disponibles
    public List<CD> listerCDsDisponibles() {
        return em.createQuery("SELECT c FROM CD c WHERE c.disponible = TRUE", CD.class).getResultList();
    }

    // Trouve un CD par son ID
    public CD trouverCD(Long id) {
        return em.find(CD.class, id);
    }

    // Ajoute un nouveau CD à la base de données
    public void ajouterCD(CD cd) {
        em.persist(cd);
    }

    // Modifie un CD existant dans la base de données
    public void modifierCD(CD cd) {
        em.merge(cd);
    }

    // Supprime un CD par son ID
    public void supprimerCD(Long id) {
        CD cd = trouverCD(id);
        if (cd != null) {
            em.remove(cd);
        }
    }

    // Liste tous les CD
    public List<CD> listerCDs() {
        return em.createQuery("SELECT c FROM CD c", CD.class).getResultList();
    }
}
