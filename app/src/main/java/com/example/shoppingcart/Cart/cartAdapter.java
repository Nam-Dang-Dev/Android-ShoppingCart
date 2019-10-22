package com.example.shoppingcart.Cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingcart.Image.Image;
import com.example.shoppingcart.R;
import com.example.shoppingcart.database.Cart;

import java.util.List;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.CartViewHolder> {
    List<Cart> Carts;
    private OnItemClicked onClick;
    Image image = new Image();

    public interface OnItemClicked {
        void onClickReductionQuantity(int position);
        void onClickPlusQuantity(int position);
        void onClickDelete(int position);
    }

//    public cartAdapter(Runnable runnable, List<Cart> carts) {
//        Carts = carts;
//    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);

        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, final int position) {

        holder.nameCartItem.setText(Carts.get(position).product_name);
        holder.quantityCartItem.setText(Carts.get(position).quantity +"");
        holder.quantityCartItem.setId(position);
        holder.PriceCartItem.setText(Carts.get(position).price+" VND");
        holder.imageCartItem.setImageBitmap(image.base64SringToImage(Carts.get(position).product_image));

        holder.btn_reduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClickReductionQuantity(position);
            }
        });

        holder.btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClickPlusQuantity(position);
            }
        });

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClickDelete(position);
            }
        });




    }

    @Override
    public int getItemCount() {
        if (Carts == null) {
            return 0;
        }
        return Carts.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{
        TextView nameCartItem, PriceCartItem, quantityCartItem;
        ImageView imageCartItem;
        Button btn_reduction, btn_plus, btn_delete;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCartItem = itemView.findViewById(R.id.nameCartItem);
            PriceCartItem = itemView.findViewById(R.id.priceCartItem);
            quantityCartItem = itemView.findViewById(R.id.textViewQuantityCartItem);
            imageCartItem = itemView.findViewById(R.id.ImageCartItem);

            btn_reduction= itemView.findViewById(R.id.btn_reduction);
            btn_plus =itemView.findViewById(R.id.btn_plus);
            btn_delete = itemView.findViewById(R.id.btn_delete_cartItem);
        }
    }
    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }

}
