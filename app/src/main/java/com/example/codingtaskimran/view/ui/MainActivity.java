package com.example.codingtaskimran.view.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codingtaskimran.R;
import com.example.codingtaskimran.service.model.Category;
import com.example.codingtaskimran.service.repository.local.dbrepo.CategoryRoomDb;
import com.example.codingtaskimran.service.repository.local.dbrepo.LocalCategoryRepository;
import com.example.codingtaskimran.service.repository.local.table.CategoryTable;
import com.example.codingtaskimran.view.adapter.CategoryAdapter;
import com.example.codingtaskimran.viewmodel.CategoryListViewModel;
import com.example.codingtaskimran.viewmodel.LocalCategoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CategoryAdapter categoryAdapter;
    private ProgressDialog progressDialog;
    private CategoryListViewModel categoryListViewModel;
    private LocalCategoryViewModel localCategoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        categoryListViewModel = ViewModelProviders.of(this).get(CategoryListViewModel.class);
        localCategoryViewModel = ViewModelProviders.of(this).get(LocalCategoryViewModel.class);

        RecyclerView rvCategory = findViewById(R.id.rvCategory);
        List<Category> categoryList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(this, categoryList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCategory.setLayoutManager(linearLayoutManager);
        rvCategory.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvCategory.setAdapter(categoryAdapter);

        LocalCategoryRepository localCategoryRepository = new LocalCategoryRepository(this);

        if (isOnline()) {
            categoryListViewModel.getCategoryListObservable().observe(this, new Observer<List<Category>>() {
                @Override
                public void onChanged(List<Category> categories) {
                    if (categories != null) {
                        progressDialog.dismiss();
                        categoryAdapter.setCategoryList(categories);

                        for (Category category : categories) {
                            CategoryTable categoryTable = new CategoryTable();
                            categoryTable.name = category.getName();
                            categoryTable.slug = category.getSlug();
                            categoryTable.image_url = category.getImageUrl();
                            localCategoryViewModel.insert(categoryTable);
                        }
                    }
                }
            });
        } else {
            showNoInternetDialog();
            getCategoryFromLocal();
        }

        categoryAdapter.setOnCategoryItemClickListener(new CategoryAdapter.OnCategoryItemClickListener() {
            @Override
            public void onItemClickListener(Category category, int position) {
                if (category != null) {
                    Intent intent = new Intent(MainActivity.this, ProductsActivity.class);
                    intent.putExtra("slug", category.getSlug());
                    startActivity(intent);
                }
            }
        });
    }

    private void getCategoryFromLocal() {
        localCategoryViewModel.getAllCategoriesFromLocal().observe(this, new Observer<List<CategoryTable>>() {
            @Override
            public void onChanged(List<CategoryTable> categoryTables) {
                if (categoryTables != null) {
                    progressDialog.dismiss();

                    List<Category> categoriesFromLocal = new ArrayList<>();
                    for (CategoryTable categoryTable : categoryTables) {
                        Category category = new Category();
                        category.setName(categoryTable.name);
                        category.setSlug(category.getSlug());
                        category.setImageUrl(category.getImageUrl());
                        categoriesFromLocal.add(category);
                    }
                    categoryAdapter.setCategoryList(categoriesFromLocal);
                }
            }
        });
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void showNoInternetDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.txt_no_internet_connection)
                .setMessage(R.string.msg_no_internet_connection)
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    dialog.dismiss();
                    progressDialog.dismiss();
                }).show();
    }
}
