package com.example.codingtaskimran.service.repository.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.codingtaskimran.service.model.Category;
import com.example.codingtaskimran.service.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {
    private static CategoryRepository categoryRepository;
    private ApiInterface apiInterface;

    public CategoryRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public synchronized static CategoryRepository getInstance() {
        if (categoryRepository == null) {
            categoryRepository = new CategoryRepository();
        }
        return categoryRepository;
    }

    public LiveData<List<Category>> getCategoryList() {
        final MutableLiveData<List<Category>> data = new MutableLiveData<>();

        apiInterface.getCategoryList().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<Product> getProductList(String slug) {
        final MutableLiveData<Product> data = new MutableLiveData<>();

        Map<String, Object > map = new HashMap<>();
        map.put("page", 1);
        map.put("category", slug);
        map.put("limit", "10");

        apiInterface.getProducts(map).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}