package com.example.shoppingcart.Cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.shoppingcart.MainActivity;
import com.example.shoppingcart.ProductAdapter;
import com.example.shoppingcart.R;
import com.example.shoppingcart.database.AppDatabase;
import com.example.shoppingcart.database.Cart;
import com.example.shoppingcart.database.Product;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements com.example.shoppingcart.Cart.cartAdapter.OnItemClicked {
    RecyclerView recyclerViewCart;
    cartAdapter cartAdapter;
    AppDatabase db;
    TextView  textViewQuantity;
    private static List<Cart> Carts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-shoppingCart").build();



        recyclerViewCart = findViewById(R.id.recycleViewCart);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
    }
    public void onResume() {
        super.onResume();
        getAndShowCarts();
    }

    private void getAndShowCarts() {
        new AsyncTask<Void, Void, List<Product>>() {
            @Override
            protected List<Product> doInBackground(Void... voids) {
                Carts = db.CartDao().getAllCart();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cartAdapter = new cartAdapter(this, Carts);
                        cartAdapter.setOnClick(CartActivity.this);
                        recyclerViewCart.setAdapter(cartAdapter);


                    }
                });
                return null;
            }
        }.execute();

    }

    @Override
    public void onClickReductionQuantity(final int position) {
        int OldQuantity =Carts.get(position).quantity;
       Carts.get(position).quantity-=1;


        if (OldQuantity>0){
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    db.CartDao().updateCart(Carts.get(position));
                    return null;
                }
            }.execute();
        }
        cartAdapter.notifyItemChanged(position);

    }

    @Override
    public void onClickPlusQuantity(final int position) {
       int OldQuantity =Carts.get(position).quantity;
       Carts.get(position).quantity+=1;


        if (OldQuantity>0){
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    db.CartDao().updateCart(Carts.get(position));
                    return null;
                }
            }.execute();
        }
        cartAdapter.notifyItemChanged(position);
    }

    @Override
    public void onClickDelete(final int position) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.CartDao().deleteCart(Carts.get(position));
                return null;
            }
        }.execute();
        cartAdapter.notifyItemChanged(position);
        Carts.remove(position);
    }
}
