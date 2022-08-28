/**
 *
 * @author:akhil
 *
 * **/

package com.example.w22comp1011gctest2student.configuration;

import com.example.w22comp1011gctest2student.Customer;
import com.example.w22comp1011gctest2student.Product;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GsonServiceConfiguration {

    private static GsonServiceConfiguration instance = null;

    private Gson gson;

    public GsonServiceConfiguration(){
        gson = new Gson();
    }

    public String convertCustumToJson(Customer customer) {
        return new Gson().toJson(customer);
    }

    public String convertProductToJson(Product product){
        return new Gson().toJson(product);
    }

    public List<Customer> loadCustumersFromJson() throws IOException {
        List<Customer> customers = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get("customers.json"));
            JsonObject object = new Gson().fromJson(reader, new TypeToken<JsonObject>() {}.getType());
            JsonArray listOfElemens = object.get("Customers").getAsJsonArray();
            listOfElemens.forEach((jsonElement -> {
                JsonObject objectCustum = jsonElement.getAsJsonObject();
                Customer customer = new Customer();
                customer.id = objectCustum.get("id").getAsLong();
                customer.firstName = objectCustum.get("firstName").getAsString();
                customer.lastName = objectCustum.get("lastName").getAsString();
                customer.streetAddress = objectCustum.get("streetAddress").getAsString();
                customer.city = objectCustum.get("city").getAsString();
                customer.province = objectCustum.get("province").getAsString();
                customer.postalCode = objectCustum.get("postalCode").getAsString();
                customer.emailAddress = objectCustum.get("emailAddress").getAsString();
                customer.ccType  = objectCustum.get("ccType").getAsString();
                customer.bloodType = objectCustum.get("bloodType").getAsString();
                customer.phoneNumber = objectCustum.get("phoneNumber").getAsString();
                customer.heightInCM = objectCustum.get("heightInCM").getAsInt();
                customer.pounds = objectCustum.get("pounds").getAsDouble();
                JsonArray listOfProducts = objectCustum.get("purchases").getAsJsonArray();
                listOfProducts.forEach((elementObject)->{
                    JsonObject objectProduct = elementObject.getAsJsonObject();
                    Product product = new Product();
                    product.id=objectProduct.get("id").getAsLong();
                    product.sku=objectProduct.get("sku").getAsString();
                    product.name=objectProduct.get("name").getAsString();
                    product.salePrice=objectProduct.get("salePrice").getAsDouble();
                    product.regularPrice=objectProduct.get("regularPrice").getAsDouble();
                    product.image=objectProduct.get("image").getAsString();
                    customer.purchases.add(product);
                });
                customers.add(customer);

            }));
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customers;
    }


    public static GsonServiceConfiguration getInstance(){
        if(instance == null)
         instance = new GsonServiceConfiguration();
        return instance;
    }
}
