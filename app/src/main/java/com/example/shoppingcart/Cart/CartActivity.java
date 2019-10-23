package com.example.shoppingcart.Cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.shoppingcart.R;
import com.example.shoppingcart.database.AppDatabase;
import com.example.shoppingcart.database.Cart;

import java.util.List;

public class CartActivity extends AppCompatActivity implements com.example.shoppingcart.Cart.cartAdapter.OnItemClicked {
    RecyclerView recyclerViewCart;
    cartAdapter cartAdapter;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-shoppingCart").build();
        cartAdapter = new cartAdapter();
        recyclerViewCart = findViewById(R.id.recycleViewCart);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter.setOnClick(CartActivity.this);
        recyclerViewCart.setAdapter(cartAdapter);
    }

    public void onResume() {
        super.onResume();
        getAndShowCarts();

    }

    private void getAndShowCarts() {
        new AsyncTask<Void, Void, List<Cart>>() {
            @Override
            protected List<Cart> doInBackground(Void... voids) {
                return db.CartDao().getAllCart();
            }

            @Override
            protected void onPostExecute(List<Cart> carts) {
                super.onPostExecute(carts);
                cartAdapter.Carts = carts;
                cartAdapter.notifyDataSetChanged();
                showTotalPrice();
            }
        }.execute();
    }

    private  void  showTotalPrice(){
        int totalPrice = 0;
        for(int i=0; i< cartAdapter.Carts.size(); i++){
            totalPrice += cartAdapter.Carts.get(i).price * cartAdapter.Carts.get(i).quantity;
        }
        TextView textViewTotalPrice = findViewById(R.id.totalPrice);
        textViewTotalPrice.setText("  "+totalPrice+"");
    }

    private void updateQuantity(String calculate, final int position) {

        if (calculate == "plus") {
            cartAdapter.Carts.get(position).quantity += 1;
        } else if (calculate == "reduction") {
            int OldQuantity = cartAdapter.Carts.get(position).quantity;
            if (OldQuantity > 0) {
                cartAdapter.Carts.get(position).quantity -= 1;
            }
        }


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.CartDao().updateCart(cartAdapter.Carts.get(position));
                return null;
            }
        }.execute();

        cartAdapter.notifyItemChanged(position);
        showTotalPrice();
    }

    @Override
    public void onClickReductionQuantity(final int position) {
        updateQuantity("reduction", position);
    }

    @Override
    public void onClickPlusQuantity(final int position) {
        updateQuantity("plus", position);

    }

    private void deleteCartItem(final int position) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.CartDao().deleteCart(cartAdapter.Carts.get(position));
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                cartAdapter.Carts.remove(position);
                cartAdapter.notifyDataSetChanged();
                showTotalPrice();
            }
        }.execute();

    }

    @Override
    public void onClickDelete(final int position) {
        deleteCartItem(position);

    }
}
