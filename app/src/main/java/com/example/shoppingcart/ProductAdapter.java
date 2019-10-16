package com.example.shoppingcart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppingcart.database.Product;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> Products;

    public ProductAdapter(Runnable runnable, List<Product> products) {
        Products = products;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent,false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        holder.viewName.setText(Products.get(position).product_name);

    }

    @Override
    public int getItemCount() {
        return Products.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView viewName;
        ImageView viewImage;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            viewName = itemView.findViewById(R.id.viewName);
            viewName = itemView.findViewById(R.id.viewImage);
        }
    }
}
