package com.example.crudadmin;

public class Component {
    Integer id ;
    String Nom ;
    String Description;
    Double Prix;
    String Stock;



    public Component(Integer id, String nom, String description, Double prix, String stock) {
        this.id = id;
        Nom = nom;
        Description = description;
        Prix = prix;
        Stock = stock;

    }

    public Component(String nom, String description, Double prix, String stock) {
        Nom = nom;
        Description = description;
        Prix = prix;
        Stock = stock;
    }

    public Component() {
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrix() {
        return Prix;
    }

    public void setPrix(Double prix) {
        Prix = prix;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        Stock = stock;
    }

}
