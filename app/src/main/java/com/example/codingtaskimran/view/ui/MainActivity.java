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
import com.example.codingtaskimran.view.adapter.CategoryAdapter;
import com.example.codingtaskimran.viewmodel.CategoryListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CategoryAdapter categoryAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        RecyclerView rvCategory = findViewById(R.id.rvCategory);
        List<Category> categoryList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(this, categoryList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCategory.setLayoutManager(linearLayoutManager);
        rvCategory.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvCategory.setAdapter(categoryAdapter);

        if (isOnline()) {
            final CategoryListViewModel categoryListViewModel =
                    ViewModelProviders.of(this).get(CategoryListViewModel.class);
            observeCategoryViewModel(categoryListViewModel);
        } else {
            showNoInternetDialog();
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

    private void observeCategoryViewModel(CategoryListViewModel viewModel) {
        viewModel.getCategoryListObservable().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                if (categories != null) {
                    progressDialog.dismiss();
                    categoryAdapter.setCategoryList(categories);
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
                .setPositiveButton(R.string.ok, (dialog, which) -> dialog.dismiss()).show();
    }


}
