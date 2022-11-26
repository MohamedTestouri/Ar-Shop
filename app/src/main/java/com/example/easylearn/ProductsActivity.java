package com.example.easylearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.easylearn.adapters.ProductsAdapter;
import com.example.easylearn.models.Product;
import com.example.easylearn.utils.GetData;
import com.example.easylearn.utils.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity implements ProductsAdapter.OnTapListener {

    private RecyclerView recyclerView;
private ProductsAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        recyclerView = findViewById(R.id.recyclerView);
        loadData();
    }

    private void loadData(){
        ArrayList<Product> productArrayList = new ArrayList<>();
        productArrayList.add(new Product(1, "Astronaut", 350, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",   "https://modelviewer.dev/shared-assets/models/Astronaut.glb", "https://nypost.com/wp-content/uploads/sites/2/2022/02/astronaut-306.jpg"));
        productArrayList.add(new Product(2, "Astronaut", 350, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",   "https://modelviewer.dev/shared-assets/models/Astronaut.glb", "https://nypost.com/wp-content/uploads/sites/2/2022/02/astronaut-306.jpg"));
        productArrayList.add(new Product(3, "Astronaut", 350, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",   "https://modelviewer.dev/shared-assets/models/Astronaut.glb", "https://nypost.com/wp-content/uploads/sites/2/2022/02/astronaut-306.jpg"));
        setData(productArrayList);
       /* RetrofitClientInstance.getRetrofitInstance().create(GetData.class).getAll().enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                setData(response.body());
              }
            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Toast.makeText(ProductsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        }); */
    }
    private void setData(ArrayList<Product> productsArrayList){
        productAdapter = new ProductsAdapter(this, productsArrayList, ProductsActivity.this::OnSelect);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ProductsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(productAdapter);
    }

    @Override
    public void OnSelect(int position) {

    }
}