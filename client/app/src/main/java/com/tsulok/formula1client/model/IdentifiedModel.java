package com.tsulok.formula1client.model;

import java.io.Serializable;

public abstract class IdentifiedModel implements Serializable{
    private int id;

    protected IdentifiedModel(int id) {
        this.id = id;
    }
}
