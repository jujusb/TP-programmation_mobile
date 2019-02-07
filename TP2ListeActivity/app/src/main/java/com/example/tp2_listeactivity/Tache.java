package com.example.tp2_listeactivity;



public class Tache {

    public enum Categorie {Travail, Sport, Menage, Lecture, Enfants, Courses, Inconnu};


    private String nom;
    private int duree;
    private String description;
    private Categorie categorie;

    /**
     * Contructeur d'une tâche
     * @param nom Nom de la tâche
     * @param categorie Catégorie de la tâche
     * @param duree Durée de la tâche
     * @param desc Description de la tâche
     */
    public Tache(String nom, String categorie, String duree, String desc) {
        this.nom = nom;
        // Association de la catégorie à partir d'une chaine de caractères (String)
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
                this.categorie = Categorie.Inconnu;
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

    /**
     * Getter pour récupérer le nom d'une tâche
     * @return Le nom de la tâche
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter pour la durée d'une tâche
     * @return La durée de la tâche
     */
    public int getDuree() {
        return duree;
    }

    /**
     * Getter pour la description d'une tâche
     * @return La description de la tâche
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter pour la catégorie d'une tâche
     * @return La catégorie de la tâche
     */
    public Categorie getCategorie() {
        return categorie;
    }
}
