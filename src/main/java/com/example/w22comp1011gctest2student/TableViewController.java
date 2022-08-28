/**
 *
 * @author:akhil
 *
 * **/
package com.example.w22comp1011gctest2student;

import com.example.w22comp1011gctest2student.configuration.GsonServiceConfiguration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class TableViewController implements Initializable {
    @FXML
    private Label saleLabel;

    @FXML
    private Label msrpLabel;

    @FXML
    private Label savingsLabel;

    @FXML
    private Label rowsInTableLabel;

    @FXML
    private TableView<Customer> tableView;

    @FXML
    private TableColumn<Customer, Long> idColumn;

    @FXML
    private TableColumn<Customer, String> firstNameColumn;

    @FXML
    private TableColumn<Customer, String> lastNameColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TableColumn<Customer, String> totalPurchaseColumn;

    @FXML
    private ListView<Product> purchaseListView;

    @FXML
    private ImageView imageView;

    private ObservableList<Customer> data = FXCollections.observableArrayList();

    private List<Customer> customers;

    @FXML
    private void top10Customers()
    {
        System.out.println("called method top10Customers()");
    }

    @FXML
    private void customersSavedOver5()
    {
        System.out.println("called method customersSavedOver5()");
    }

    @FXML
    private void loadAllCustomers()
    {
        System.out.println("called method loadAllCustomers");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GsonServiceConfiguration gsonService = GsonServiceConfiguration.getInstance();
        try {
            this.customers = gsonService.loadCustumersFromJson();
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            totalPurchaseColumn.setCellValueFactory(new PropertyValueFactory<>("totalPurchasesString"));

            for (Customer customer :customers) {
                data.add(customer);
            }

            tableView.setItems(data);

            rowsInTableLabel.setText("Rows in table: " + tableView.getItems().size());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }


    }

    @FXML
    public void onClick(Event event) {
        TablePosition position = this.tableView.getFocusModel().getFocusedCell();
        Customer customer = this.customers.get(position.getColumn());

        ObservableList<Product> dataProduct = FXCollections.observableArrayList();
        List<Product> products = null;
        if(customer.purchases.size() == 0){
            products = new ArrayList<>();
            Product temp = new Product();
            temp.name = "fake";
            temp.salePrice = 8d;
            products.add(temp);
        }else{
            products = customer.purchases;
        }

        for(Product product:products){
            dataProduct.add(product);
        }

        msrpLabel.setText("Total regular price :" + customer.getTotalRegularPrice() + " $");

        saleLabel.setText("Total saling price : " + customer.getTotalPurchases() + " $");

        savingsLabel.setText("Total saving price :" + customer.getTotalSaved() + " $");

        this.purchaseListView.setItems(dataProduct);
    }

    @FXML
    public void on5DollarsClick(Event event) {
        data = FXCollections.observableArrayList();
        for (Customer customer :customers) {
            if(customer.isFiveDollarsSaved())
                data.add(customer);
        }
        tableView.setItems(data);
        rowsInTableLabel.setText("Rows in table: " + tableView.getItems().size());

    }

    @FXML
    public void onCustumersUpdate(Event event) {
        data = FXCollections.observableArrayList();
        for (Customer customer :customers) {
                data.add(customer);
        }
        tableView.setItems(data);
        rowsInTableLabel.setText("Rows in table: " + tableView.getItems().size());
    }

    @FXML
    public void displayImage(Event event) {
        Product product = this.purchaseListView.getSelectionModel().getSelectedItems().get(0);
        this.imageView.setImage(new Image(product.image));
    }

    @FXML
    public void sort10Custumers(Event event) {


    }
}
