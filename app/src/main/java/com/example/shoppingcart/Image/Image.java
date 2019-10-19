package com.example.shoppingcart.Image;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.shoppingcart.Cart.cartAdapter;
import com.example.shoppingcart.MainActivity;
import com.example.shoppingcart.ProductAdapter;

import java.io.ByteArrayOutputStream;


public class Image {
    Context context;
    public Image(Context context) {
        this.context = context;
    }



    public Image(MainActivity productAdapter) {
    }

    public Image(cartAdapter cartAdapter) {

    }

    public Image(ProductAdapter productAdapter) {

    }

    public String imageToBase64String(int c) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), c);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return imageString;
    }

    public Bitmap base64SringToImage(String imageString) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();
        imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        return decodedImage;
    }


}
