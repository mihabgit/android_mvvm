package com.example.codingtaskimran.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.codingtaskimran.service.repository.local.dbrepo.LocalCategoryRepository;
import com.example.codingtaskimran.service.repository.local.table.CategoryTable;

import java.util.List;

public class LocalCategoryViewModel extends AndroidViewModel {
    private LocalCategoryRepository localCategoryRepository;
    private LiveData<List<CategoryTable>> mAllCategories;

    public LocalCategoryViewModel(@NonNull Application application) {
        super(application);

        localCategoryRepository = new LocalCategoryRepository(application);
        mAllCategories = localCategoryRepository.getmCategoryList();
    }

    public LiveData<List<CategoryTable>> getAllCategoriesFromLocal() {
        return mAllCategories;
    }

    public void insert(CategoryTable categoryTable) {
        localCategoryRepository.insert(categoryTable);
    }
}
