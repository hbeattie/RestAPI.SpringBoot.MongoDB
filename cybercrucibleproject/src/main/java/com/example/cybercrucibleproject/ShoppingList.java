package com.example.cybercrucibleproject;

import org.springframework.data.annotation.Id;

public class ShoppingList {

    @Id
    public String id;

    public String name;
    public String [] items;

    public ShoppingList() {}
    public ShoppingList(String name, String [] items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }
    public String [] getItems() {
        return items;
    }
    public String getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setItems(String [] items) {
        this.items = items;
    }
    public void setId(String id) {
        this.id = id;
    }
}
