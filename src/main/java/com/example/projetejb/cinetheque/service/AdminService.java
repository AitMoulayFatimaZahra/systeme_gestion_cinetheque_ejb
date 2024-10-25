package com.example.projetejb.cinetheque.service;

import com.example.projetejb.cinetheque.model.CD;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class AdminService {

    @PersistenceContext
    private EntityManager entityManager;

    public void ajouterCD(CD cd) {
        entityManager.persist(cd);
    }

    public void modifierCD(CD cd) {
        entityManager.merge(cd);
    }

    public void supprimerCD(Long cdId) {
        CD cd = entityManager.find(CD.class, cdId);
        if (cd != null) {
            entityManager.remove(cd);
        }
    }

    public List<CD> listerCDs() {
        return entityManager.createQuery("SELECT c FROM CD c", CD.class).getResultList();
    }
}
