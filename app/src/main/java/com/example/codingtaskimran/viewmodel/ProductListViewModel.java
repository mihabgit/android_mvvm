package com.example.codingtaskimran.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.codingtaskimran.service.model.Product;
import com.example.codingtaskimran.service.repository.remote.CategoryRepository;

public class ProductListViewModel extends AndroidViewModel {

    private LiveData<Product> productListObservable;

    public ProductListViewModel(@NonNull Application application, String string, int number) {
        super(application);

        productListObservable = CategoryRepository.getInstance().getProductList(string, number);
    }

    public LiveData<Product> getProductListObservable() {
        return productListObservable;
    }
}
