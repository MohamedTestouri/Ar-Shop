package com.example.easylearn.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylearn.ProductDetailsActivity;
import com.example.easylearn.ProductsActivity;
import com.example.easylearn.R;
import com.example.easylearn.models.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>{
    private Context context;
    private ArrayList<Product> productArrayList;
    private OnTapListener onTapListener;
    public ProductsAdapter(Context context, ArrayList<Product> productArrayList, OnTapListener onTapListener){
        this.context = context;
        this.productArrayList = productArrayList;
        this.onTapListener = onTapListener;
    }
    public void setOnTapListener(OnTapListener onTapListener ) {
        this. onTapListener  =onTapListener;
    }
    @NonNull
    @Override
    public ProductsAdapter.ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_products, parent, false);
        return new ProductsViewHolder(view, onTapListener);
        // return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ProductsViewHolder holder, int position) {
        Product product = productArrayList.get(position);
        holder.descriptionTextView.setText(product.getDescription());
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText(product.getPrice()+"$");
        Log.d("PRICE: ", product.getPrice()+"$");
       Picasso.get()
                .load(product.getImage())
                .fit().centerCrop()
                .into(holder.productImageView);
       // Glide.with(context).load(exercice.getImage()).into(holder.exerciceImageView);
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder  {
        private ImageView productImageView;
        private TextView nameTextView;
        private TextView descriptionTextView;
        private TextView priceTextView;
        private LinearLayout item;
        OnTapListener onTapListener;
        public ProductsViewHolder(@NonNull View itemView, OnTapListener onTapListener) {
            super(itemView);
            this.onTapListener = onTapListener;
            productImageView = itemView.findViewById(R.id.productImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            item = itemView.findViewById(R.id.item);
            item.setOnClickListener(l ->{
                onTapListener.OnSelect(getAdapterPosition());
              /*  Intent intent = new Intent(this, ProductDetailsActivity.class)
                intent.putExtra("name",   productArrayList.get(getAdapterPosition()).getName());
                intent.putExtra("description",  productArrayList.get(getAdapterPosition()).getDescription());
                intent.putExtra("image",  productArrayList.get(getAdapterPosition()).getImage());
                intent.putExtra("price",  productArrayList.get(getAdapterPosition()).getPrice());
                intent.putExtra("url",productArrayList.get(getAdapterPosition()).getUrl());
                startActivity(intent);*/
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("name",   productArrayList.get(getAdapterPosition()).getName());
                intent.putExtra("description",  productArrayList.get(getAdapterPosition()).getDescription());
                intent.putExtra("image",  productArrayList.get(getAdapterPosition()).getImage());
                intent.putExtra("price",  productArrayList.get(getAdapterPosition()).getPrice()+"$");
                intent.putExtra("url",productArrayList.get(getAdapterPosition()).getUrl());
                context.startActivity(intent);

            });
        }
    }
    private void navigate(){

    }
    public interface OnTapListener {
        void OnSelect(int position);
    }
}
