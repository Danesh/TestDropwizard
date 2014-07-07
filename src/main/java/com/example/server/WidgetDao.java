package com.example.server;

public class WidgetDao {

    private int id;
    private String name;
    private String dateCreated;

    public WidgetDao(int id, String name, String dateCreated) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateCreated() {
        return dateCreated;
    }

}
