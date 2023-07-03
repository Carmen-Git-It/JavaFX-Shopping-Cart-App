package com.carmengitit.ws03.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;

public class ItemList {
    final private ObservableList<Item> items = FXCollections.observableArrayList();

    private void loadData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("itemsMaster.csv"));
            String line;
            while (br.ready()) {
                line = br.readLine();
                if (line.length() > 0) {
                    String[] tokens = line.split(",");
                    Item item = new Item(tokens[0], tokens[1], Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
                    items.add(item);
                }
            }
            br.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public ItemList() {
        loadData();
    }

    public ObservableList<Item> getItems() {
        return items;
    }
}
