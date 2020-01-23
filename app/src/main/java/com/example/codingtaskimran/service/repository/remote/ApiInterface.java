package com.example.codingtaskimran.service.repository.remote;


import com.example.codingtaskimran.service.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("core/public/categories/")
    Call<List<Category>> getCategoryList();

}
