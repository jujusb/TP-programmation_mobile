package com.example.tp2_listeactivity;



public class Tache {

    public enum Categorie {Travail, Sport, Menage, Lecture, Enfants, Courses};


    private String nom;
    private int duree;
    private String description;
    private Categorie categorie;

    public Tache(String nom, String categorie, String duree, String desc) {
        this.nom = nom;
        switch (categorie) {
            case "Travail":
                this.categorie = Categorie.Travail;
                break;
            case "Sport":
                this.categorie = Categorie.Sport;
                break;
            case "Menage":
                this.categorie = Categorie.Menage;
                break;
            case "Lecture":
                this.categorie = Categorie.Lecture;
                break;
            case "Enfants":
                this.categorie = Categorie.Enfants;
                break;
            case "Courses":
                this.categorie = Categorie.Courses;
                break;
            default:
                System.out.println("Erreur catégorie non trouvée!");
        }
        this.duree = Integer.parseInt(duree);
        this.description = desc;
    }

    public Tache(String nom, Categorie categorie, int duree, String desc) {
        this.nom = nom;
        this.categorie = categorie;
        this.duree = duree;
        this.description = desc;
    }

    public Tache(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public int getDuree() {
        return duree;
    }

    public String getDescription() {
        return description;
    }

    public Categorie getCategorie() {
        return categorie;
    }
}
