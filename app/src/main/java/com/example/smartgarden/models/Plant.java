package com.example.smartgarden.MODELS;

public class Plant {
    String id_pl;
    String name;
    String species;
    String desc;
    String image;

    public Plant() {
    }

    public Plant(String id_pl, String name, String species, String desc, String image) {
        this.id_pl = id_pl;
        this.name = name;
        this.species = species;
        this.desc = desc;
        this.image = image;
    }

    public String getId_pl() {
        return id_pl;
    }

    public void setId_pl(String id_pl) {
        this.id_pl = id_pl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
