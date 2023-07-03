package com.carmengitit.ws03.controller;

import com.carmengitit.ws03.model.ItemInCart;
import com.carmengitit.ws03.model.ItemList;
import com.carmengitit.ws03.model.Item;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class ShoppingCartController {
    @FXML
    ComboBox<Item> itemComboBox;
    @FXML
    Slider unitsSlider;
    @FXML
    Label unitQtyLabel;
    @FXML
    Label unitLabel;
    @FXML
    Label unitPriceLabel;
    @FXML
    TableView<ItemInCart> cartTableView;
    @FXML
    TableColumn<ItemInCart, String> nameTableColumn;
    @FXML
    TableColumn<ItemInCart, Double> unitsTableColumn;
    @FXML
    TableColumn<ItemInCart, Double> priceTableColumn;
    @FXML
    Label totalLabel;
    @FXML
    ImageView itemImageView;
    final private ObservableList<ItemInCart> itemsInCart = FXCollections.observableArrayList();
    final private SimpleDoubleProperty total = new SimpleDoubleProperty(0);

    private void setupAction() {
        Image milkImage = new Image(getClass().getResourceAsStream("/com/carmengitit/ws03/milk.png"));
        Image eggsImage = new Image(getClass().getResourceAsStream("/com/carmengitit/ws03/eggs.png"));
        Image cerealImage = new Image(getClass().getResourceAsStream("/com/carmengitit/ws03/cereal.png"));
        Image butterImage = new Image(getClass().getResourceAsStream("/com/carmengitit/ws03/butter.png"));
        Image breadImage = new Image(getClass().getResourceAsStream("/com/carmengitit/ws03/bread.png"));
        ItemList itemList = new ItemList();
        itemComboBox.setItems(itemList.getItems());
        unitsSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                unitQtyLabel.setText(String.format("%.2f", newValue.doubleValue()));
            }
        });
        itemComboBox.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {
            unitLabel.setText(String.format("%.2f", newValue.getQuantity()) + " " + newValue.getUnit());
            unitPriceLabel.setText("$" + String.format("%.2f", newValue.getUnitPrice()));
            if (newValue.getName().equalsIgnoreCase("bread")) {
                itemImageView.setImage(breadImage);
            } else if (newValue.getName().equalsIgnoreCase("butter")) {
                itemImageView.setImage(butterImage);
            } else if (newValue.getName().equalsIgnoreCase("cereal")) {
                itemImageView.setImage(cerealImage);
            } else if (newValue.getName().equalsIgnoreCase("eggs")) {
                itemImageView.setImage(eggsImage);
            } else if (newValue.getName().equalsIgnoreCase("milk")) {
                itemImageView.setImage(milkImage);
            }
        });
        cartTableView.setItems(itemsInCart);
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<ItemInCart, String>("name"));
        unitsTableColumn.setCellValueFactory(new PropertyValueFactory<ItemInCart, Double>("quantity"));
        priceTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ItemInCart, Double>, ObservableValue<Double>>() {
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<ItemInCart, Double> p) {
                ObservableList<Item> items = itemList.getItems();
                double value = 0;
                for (Item item : items) {
                    if (item.getName().equals(p.getValue().getName())) {
                        value = item.getUnitPrice() * p.getValue().getQuantity();
                    }
                }
                return new ReadOnlyObjectWrapper<Double>(value);
            }
        });
        itemsInCart.addListener(new ListChangeListener<ItemInCart>() {
            @Override
            public void onChanged(Change<? extends ItemInCart> change) {
                total.setValue(0);
                for (int i = 0; i < itemsInCart.size(); i++) {
                    total.setValue(total.getValue() + priceTableColumn.getCellData(i));
                }
            }
        });
        totalLabel.textProperty().bind(total.asString("$%.2f"));
        cartTableView.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue != null) {
                int index = 0;
                for (int i = 0; i < itemList.getItems().size(); i++) {
                    if (itemList.getItems().get(i).getName().equals(newValue.getName())) {
                        index = i;
                        break;
                    }
                }
                itemComboBox.getSelectionModel().select(index);
                unitsSlider.setValue(newValue.getQuantity());

                if (newValue.getName().equalsIgnoreCase("bread")) {
                    itemImageView.setImage(breadImage);
                } else if (newValue.getName().equalsIgnoreCase("butter")) {
                    itemImageView.setImage(butterImage);
                } else if (newValue.getName().equalsIgnoreCase("cereal")) {
                    itemImageView.setImage(cerealImage);
                } else if (newValue.getName().equalsIgnoreCase("eggs")) {
                    itemImageView.setImage(eggsImage);
                } else if (newValue.getName().equalsIgnoreCase("milk")) {
                    itemImageView.setImage(milkImage);
                }
            }
        });
    }

    public void initialize() {
        setupAction();
    }

    @FXML
    protected void onAddButtonClick() {
        ItemInCart item = new ItemInCart(itemComboBox.getValue().getName(), unitsSlider.getValue());
        itemsInCart.add(item);
    }

    @FXML
    protected void onRemoveButtonClick() {
        int index = cartTableView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            itemsInCart.remove(index);
        }
    }
}