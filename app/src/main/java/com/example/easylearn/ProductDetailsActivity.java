package com.example.easylearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {

    private Button showModelButton;
    private ImageView productImageView;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private  TextView priceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        showModelButton  = findViewById(R.id.showModelButton);
        productImageView = findViewById(R.id.productDetailsImageView);
        nameTextView = findViewById(R.id.nameDetailsTextView);
        descriptionTextView = findViewById(R.id.descriptionDetailsTextView);
        priceTextView = findViewById(R.id.priceDetailsTextView);

        Picasso.get()
                .load(getIntent().getStringExtra("image"))
                .fit().centerCrop()
                .into(productImageView);
        nameTextView.setText(getIntent().getStringExtra("name"));
        descriptionTextView.setText(getIntent().getStringExtra("description"));
        priceTextView.setText(getIntent().getStringExtra("price") );
        showModelButton.setOnClickListener(l -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("url", getIntent().getStringExtra("url"));
            startActivity(intent);
        });
    }
}