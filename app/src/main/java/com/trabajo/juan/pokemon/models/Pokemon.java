package com.trabajo.juan.pokemon.models;


import java.util.ArrayList;

public class Pokemon {
    private Integer id;
    private String name;
    private String url;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public Integer getNumber() {

        String[] urlPartes = url.split("/");
        return Integer.parseInt(urlPartes[urlPartes.length - 1]);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setNumber(Integer number)
    {
        this.number = number;
    }

    private Integer number;

    private ArrayList<Habilidad> abilities;
    public ArrayList<Habilidad> getAbilities() {return abilities;}

    public void setAbilities(ArrayList<Habilidad> abilities) {this.abilities = abilities;}

    private ArrayList<Type> types;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Type> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Type> types) {
        this.types = types;
    }


}
