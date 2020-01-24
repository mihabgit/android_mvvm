package com.example.codingtaskimran.service.repository.remote;


import com.example.codingtaskimran.service.model.Category;
import com.example.codingtaskimran.service.model.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @GET("core/public/categories/")
    Call<List<Category>> getCategoryList();

    /*@GET("core/public/inventory/products/")
    Call<List<Product>> getProducts();*/

    @GET("core/public/inventory/products/")
    Call<Product> getProducts(@QueryMap Map<String, Object> map);

    //https://api-dev.evaly.com.bd/core/public/inventory/products/?page=1&category=books-f810ccc6a&limit=10
}
