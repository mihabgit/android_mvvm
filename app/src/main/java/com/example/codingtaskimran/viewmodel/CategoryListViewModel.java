package com.example.codingtaskimran.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.codingtaskimran.service.model.Category;
import com.example.codingtaskimran.service.repository.remote.CategoryRepository;

import java.util.List;

public class CategoryListViewModel extends AndroidViewModel {

    private final LiveData<List<Category>> categoryListObservable;

    public CategoryListViewModel(@NonNull Application application) {
        super(application);

        categoryListObservable = CategoryRepository.getInstance().getCategoryList();
    }

    public LiveData<List<Category>> getCategoryListObservable() {
        return categoryListObservable;
    }
}

















