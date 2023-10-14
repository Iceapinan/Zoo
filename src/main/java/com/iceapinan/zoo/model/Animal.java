package com.iceapinan.zoo.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public abstract class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String family;
    private String type; // Herbivore or Carnivore

    public Animal() {}

    public Animal(String name, String family, String type) {
        this.name = name;
        this.family = family;
        this.type = type;
    }

    public List<String> getInfo() {
        List<String> listInfo = new ArrayList<>();
        listInfo.add("Name: " + name);
        listInfo.add("Family: " + family);
        listInfo.add("Type: " + type);
        return listInfo;
    }

    public abstract String say();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
