package com.iceapinan.zoo.model;

import java.util.List;

import javax.persistence.*;

public class Zoo implements java.io.Serializable {
    public List<Cage> cages;

    public int numberOfCages;

    public Zoo(List<Cage> cages, int numberOfCages) {
        this.cages = cages;
        this.numberOfCages =  numberOfCages;
    }

    public List<Cage> getCages() {
        return this.cages;
    }

    public int getNumberOfCages() {
        return this.numberOfCages;
    }

    public void setCages() {
        this.cages = cages;
    }

    public void setNumberOfCages() {
        this.numberOfCages = numberOfCages;
    }
}
