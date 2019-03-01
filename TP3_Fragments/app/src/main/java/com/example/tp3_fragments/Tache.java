package com.example.tp3_fragments;

import android.os.Parcel;
import android.os.Parcelable;

public class Tache implements Parcelable {

    public enum Categorie {Travail, Sport, Menage, Lecture, Enfants, Courses, Inconnu};


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

    // Méthode Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nom);
        dest.writeInt(this.duree);
        dest.writeString(this.description);
        dest.writeString(this.categorie.toString());
    }

    public static final Parcelable.Creator<Tache> CREATOR
            = new Parcelable.Creator<Tache>() {
        public Tache createFromParcel(Parcel in) {
            return new Tache(in);
        }

        public Tache[] newArray(int size) {
            return new Tache[size];
        }
    };


    private Tache(Parcel in) {
        this.nom = in.readString();
        this.duree = in.readInt();
        this.description = in.readString();

        switch (in.readString()) {
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

    }

}
