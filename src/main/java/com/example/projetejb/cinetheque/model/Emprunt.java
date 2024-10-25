package com.example.projetejb.cinetheque.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "emprunts") // Nom de la table dans la base de données
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Génération automatique de l'ID
    private Long id; // Identifiant unique pour chaque emprunt

    @ManyToOne // Relation many-to-one avec CD
    @JoinColumn(name = "cd_id") // Nom de la colonne dans la table d'emprunt
    private CD cd;

    @Column(name = "date_emprunt") // Nom de la colonne dans la base de données
    private LocalDate dateEmprunt;

    @Column(name = "emprunteur") // Nom de la colonne dans la base de données
    private String emprunteur;

    // Constructeur
    public Emprunt(CD cd, LocalDate dateEmprunt, String emprunteur) {
        this.cd = cd;
        this.dateEmprunt = dateEmprunt;
        this.emprunteur = emprunteur;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CD getCd() {
        return cd;
    }

    public void setCd(CD cd) {
        this.cd = cd;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public String getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(String emprunteur) {
        this.emprunteur = emprunteur;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "cd=" + cd.getTitre() +
                ", dateEmprunt=" + dateEmprunt +
                ", emprunteur='" + emprunteur + '\'' +
                '}';
    }
}
