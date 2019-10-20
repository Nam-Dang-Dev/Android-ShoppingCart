package com.example.shoppingcart.Image;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppingcart.Cart.cartAdapter;
import com.example.shoppingcart.MainActivity;
import com.example.shoppingcart.ProductAdapter;

import java.io.ByteArrayOutputStream;


public class Image extends Activity {

    public Bitmap base64SringToImage(String imageString) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();
        imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        return decodedImage;
    }


}
