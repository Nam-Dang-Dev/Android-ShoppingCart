package com.example.shoppingcart.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface cartDao {
    @Query("SELECT * FROM Cart")
    List<Cart> getAllCart();

    @Update
    int updateCart(Cart... Carts);

    @Insert
    void insertAllCart(Cart... Carts);

    @Delete
    void deleteCart(Cart Cart);

    @Query("DELETE FROM Cart")
    void deleteAllCart();
}
