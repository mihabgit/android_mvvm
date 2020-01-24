package com.example.codingtaskimran.viewmodel;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ProductViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;
    private String slug;
    private Integer number;

    public ProductViewModelFactory(Application mApplication, String slug) {
        this.mApplication = mApplication;
        this.slug = slug;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProductListViewModel(mApplication, slug);
    }
}
