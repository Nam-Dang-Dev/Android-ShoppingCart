package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.shoppingcart.database.AppDatabase;
import com.example.shoppingcart.database.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    AppDatabase db;
    RecyclerView recyclerViewProduct;
    ProductAdapter productAdapter;
    private static List<Product> Products = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-shoppingCart").build();

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                Product iphone = new Product("Iphone",12, 100000, "@mipmap/ghost");
//                Product samsung = new Product("Samsung",12, 100000);
//                Product Iphone5 = new Product("Iphone5",12, 100000);
//                Product Iphone11 = new Product("Iphone11",12, 100000);
//                Product Nokia = new Product("Nokia",12, 100000);

               // db.ProductDao().insertAll(samsung, iphone,samsung, Iphone5, Iphone11, Nokia);
                db.ProductDao().insertAll( iphone);
                return null;
            }
        }.execute();

        recyclerViewProduct = findViewById(R.id.recyclerView);
        recyclerViewProduct.setLayoutManager(new LinearLayoutManager(this));

    }

    public void onResume() {
        super.onResume();
        getAndShowProducts();
    }
    private void getAndShowProducts(){
        new AsyncTask<Void, Void, List<Product>>() {
            @Override
            protected List<Product> doInBackground(Void... voids) {
                Products = db.ProductDao().getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        productAdapter = new ProductAdapter(this, Products);
//                        productAdapter.setOnClick(MainActivity.this);
                        recyclerViewProduct.setAdapter(productAdapter);


                    }
                });
                return null;
            }
        }.execute();

    }
}
