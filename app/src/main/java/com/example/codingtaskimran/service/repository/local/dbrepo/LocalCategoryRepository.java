package com.example.codingtaskimran.service.repository.local.dbrepo;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.codingtaskimran.service.repository.local.dao.CategoryDao;
import com.example.codingtaskimran.service.repository.local.table.CategoryTable;

import java.util.List;

public class LocalCategoryRepository {

    private CategoryDao mCategoryDao;
    private LiveData<List<CategoryTable>> mCategoryList;

    public LocalCategoryRepository(Context application) {
        CategoryRoomDb categoryRoomDb = CategoryRoomDb.getInstance(application);
        mCategoryDao = categoryRoomDb.categoryDao();
        mCategoryList = mCategoryDao.getAllCategory();
    }

    public LiveData<List<CategoryTable>> getmCategoryList() {
        return mCategoryList;
    }

    public void insert(CategoryTable category) {
        new InsertAsyncTask(mCategoryDao).execute(category);
    }

    public static class InsertAsyncTask extends AsyncTask<CategoryTable, Void, Void> {
        private CategoryDao categoryDao;

        public InsertAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(CategoryTable... categoryTables) {
            categoryDao.insertCategory(categoryTables[0]);
            return null;
        }
    }

}

