package com.example.shoppingcart.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cart {
    @PrimaryKey(autoGenerate = true)
    public int Cid;

    @ColumnInfo(name = "productName")
    public String product_name;

    @ColumnInfo(name = "quantity")
    public int quantity;

    @ColumnInfo(name = "price")
    public int price;


    @ColumnInfo(name = "image")
    public String product_image;

    public Cart(String product_name, int quantity, int price, String product_image) {
        this.product_name = product_name;
        this.quantity = quantity;
        this.price = price;
        this.product_image = product_image;
    }
}

