package com.example.projetejb.cinetheque.client;

import com.example.projetejb.cinetheque.model.CD;
import com.example.projetejb.cinetheque.service.CDService;
import com.example.projetejb.cinetheque.model.Emprunt;
import com.example.projetejb.cinetheque.service.EmpruntService;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GestionCDFrame extends JFrame {
    private CDService cdService = new CDService();
    private DefaultListModel<CD> cdListModel;

    public GestionCDFrame() {
        setTitle("Gestion des CD");
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Panneau principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Liste des CD
        cdListModel = new DefaultListModel<>();
        JList<CD> cdJList = new JList<>(cdListModel);
        JScrollPane scrollPane = new JScrollPane(cdJList);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Composants
        JButton ajouterCDButton = new JButton("Ajouter CD");
        JButton modifierCDButton = new JButton("Modifier CD");
        JButton supprimerCDButton = new JButton("Supprimer CD");
        JButton voirEmpruntsButton = new JButton("Voir Emprunts");

        // Ajout des boutons au panneau
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ajouterCDButton);
        buttonPanel.add(modifierCDButton);
        buttonPanel.add(supprimerCDButton);
        buttonPanel.add(voirEmpruntsButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);

        // Action des boutons
        ajouterCDButton.addActionListener(e -> afficherAjouterCD());
        modifierCDButton.addActionListener(e -> {
            int index = cdJList.getSelectedIndex();
            if (index != -1) {
                afficherModifierCD(index);
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un CD à modifier.");
            }
        });
        supprimerCDButton.addActionListener(e -> {
            int index = cdJList.getSelectedIndex();
            if (index != -1) {
                cdService.supprimerCD(index);
                mettreAJourListe();
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un CD à supprimer.");
            }
        });
        voirEmpruntsButton.addActionListener(e -> afficherEmprunts());
    }

    private void mettreAJourListe() {
        cdListModel.clear();
        List<CD> cds = cdService.listerCDs();
        for (CD cd : cds) {
            cdListModel.addElement(cd);
        }
    }

    private void afficherAjouterCD() {
        JTextField titreField = new JTextField();
        JTextField artisteField = new JTextField();
        Object[] message = {
                "Titre:", titreField,
                "Artiste:", artisteField
        };
        int option = JOptionPane.showConfirmDialog(this, message, "Ajouter CD", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            CD nouveauCD = new CD(titreField.getText(), artisteField.getText());
            cdService.ajouterCD(nouveauCD);
            mettreAJourListe();
        }
    }

    private void afficherModifierCD(int index) {
        CD cd = cdService.listerCDs().get(index);
        JTextField titreField = new JTextField(cd.getTitre());
        JTextField artisteField = new JTextField(cd.getAuteur());
        Object[] message = {
                "Titre:", titreField,
                "Artiste:", artisteField
        };
        int option = JOptionPane.showConfirmDialog(this, message, "Modifier CD", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            cd.setTitre(titreField.getText());
            cd.setAuteur(artisteField.getText());
            cdService.modifierCD(index, cd);
            mettreAJourListe();
        }
    }

    private void afficherEmprunts() {
        List<Emprunt> emprunts = EmpruntService.listerEmprunts();
        StringBuilder sb = new StringBuilder();
        for (Emprunt emprunt : emprunts) {
            sb.append(emprunt.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.length() == 0 ? "Aucun emprunt en cours." : sb.toString(), "Emprunts en cours", JOptionPane.INFORMATION_MESSAGE);
    }
}
