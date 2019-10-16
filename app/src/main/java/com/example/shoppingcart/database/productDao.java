package com.example.shoppingcart.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
@Dao
public interface productDao {
    @Query("SELECT * FROM product")
    List<Product> getAll();

    @Query("SELECT * FROM product WHERE Tid IN (:userIds)")
    List<Product> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(Product... products);

    @Delete
    void delete(Product Product);

    @Query("UPDATE product SET productName = :productName WHERE Tid = :tid")
    void updateProduct(int tid, String productName);

    @Query("DELETE FROM product")
    void deleteAll();
}
