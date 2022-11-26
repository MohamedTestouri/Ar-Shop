package com.example.easylearn.utils;

import com.example.easylearn.models.Product;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.Call;

public interface GetData {
    @GET("/products")
        Call<ArrayList<Product>> getAll();
}
