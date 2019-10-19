package com.example.shoppingcart.Cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingcart.Image.Image;
import com.example.shoppingcart.R;
import com.example.shoppingcart.database.Cart;

import java.util.List;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.CartViewHolder> {
    private List<Cart> Carts;
    private OnItemClicked onClick;
    Image image = new Image(this);
    public interface OnItemClicked {
        void onClickAddToCart(int position);
    }

    public cartAdapter(Runnable runnable, List<Cart> carts) {
        Carts = carts;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);

        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        holder.nameCartItem.setText(Carts.get(position).product_name);
        holder.quantityCartItem.setText(Carts.get(position).quantity +"");
        holder.PriceCartItem.setText(Carts.get(position).price+"");
        holder.imageCartItem.setImageBitmap(image.base64SringToImage(Carts.get(position).product_image));
    }

    @Override
    public int getItemCount() {
        return Carts.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{
        TextView nameCartItem, PriceCartItem, quantityCartItem;
        ImageView imageCartItem;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCartItem = itemView.findViewById(R.id.nameCartItem);
            PriceCartItem = itemView.findViewById(R.id.priceCartItem);
            quantityCartItem = itemView.findViewById(R.id.quantityCartItem);
            imageCartItem = itemView.findViewById(R.id.ImageCartItem);
        }
    }
}
