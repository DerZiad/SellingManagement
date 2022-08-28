/**
 *
 * @author:akhil
 *
 * **/
package com.example.w22comp1011gctest2student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Customer implements Comparable<Customer>{

    public Long id;

    public String firstName;

    public String lastName;

    public String streetAddress;

    public String city;

    public String province;

    public String postalCode;

    public String emailAddress;

    public String ccType;

    public String bloodType;

    public String phoneNumber;

    public Double pounds;

    public Integer heightInCM;

    public List<Product> purchases = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getCcType() {
        return ccType;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Double getPounds() {
        return pounds;
    }

    public Integer getHeightInCM() {
        return heightInCM;
    }

    public List<Product> getPurchases() {
        return purchases;
    }

    public Double getTotalPurchases(){
        AtomicReference<Double> amount = new AtomicReference<>(0d);
        this.purchases.stream().forEach((purchase)->{
            amount.updateAndGet(v -> v + purchase.salePrice);
        });
        return amount.get();
    }

    public Double getTotalSaved(){
        AtomicReference<Double> amount = new AtomicReference<>(0d);
        this.purchases.stream().forEach((purchase)->{
            amount.updateAndGet(v -> v + (purchase.regularPrice - purchase.salePrice));
        });
        return amount.get();
    }

    public Double getTotalRegularPrice(){
        AtomicReference<Double> amount = new AtomicReference<>(0d);
        this.purchases.stream().forEach((purchase)->{
            amount.updateAndGet(v -> v + purchase.regularPrice);
        });
        return amount.get();
    }


    public boolean isFiveDollarsSaved(){
        return this.getTotalSaved() >= 5d;
    }

    public String getTotalPurchasesString(){
        return this.getTotalPurchases() + " $";
    }

    @Override
    public int compareTo(Customer o) {
        return (int)(getTotalPurchases() - o.getTotalPurchases());
    }
}
