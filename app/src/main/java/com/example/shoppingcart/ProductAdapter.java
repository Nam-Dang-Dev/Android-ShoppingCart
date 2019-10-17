package com.example.shoppingcart;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppingcart.database.Product;

import java.io.ByteArrayOutputStream;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> Products;
    private OnItemClicked onClick;

    public ProductAdapter(Runnable runnable, List<Product> products) {
        Products = products;
    }

    public interface OnItemClicked {
        void onClickAddToCart(int position);
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, final int position) {

        holder.viewName.setText(Products.get(position).product_name);
        holder.viewImage.setImageBitmap(base64SringToImage(Products.get(position).product_image));
        holder.viewPrice.setText(Products.get(position).price + "");

        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onClickAddToCart(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return Products.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView viewName, viewPrice;
        ImageView viewImage;
        Button btnAddToCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            viewName = itemView.findViewById(R.id.viewName);
            viewImage = itemView.findViewById(R.id.viewImage);
            viewPrice = itemView.findViewById(R.id.Price);
            btnAddToCart = itemView.findViewById(R.id.btn_add_to_cart);
        }
    }

    private Bitmap base64SringToImage(String imageString) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();
        imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        return decodedImage;
    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }
}
