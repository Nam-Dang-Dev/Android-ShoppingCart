package com.example.shoppingcart.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class, Cart.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract productDao ProductDao();
    public abstract cartDao CartDao();
}