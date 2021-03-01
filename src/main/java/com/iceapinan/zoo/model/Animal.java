package com.iceapinan.zoo.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    public String name;
    public String family;
    public String type; // Herbivore or Carnivore

    public List<String> getInfo() {
        List<String> listInfo = new ArrayList<String>();
        listInfo.add("Name: " + name);
        listInfo.add("Family: " + family);
        listInfo.add("Type: " + type);
        return listInfo;
    }
    public String say() { return ""; };
}
