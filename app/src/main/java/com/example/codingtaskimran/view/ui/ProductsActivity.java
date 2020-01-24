package com.example.codingtaskimran.view.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codingtaskimran.EndlessRecyclerViewScrollListener;
import com.example.codingtaskimran.R;
import com.example.codingtaskimran.service.model.Product;
import com.example.codingtaskimran.service.model.Result;
import com.example.codingtaskimran.view.adapter.ProductsAdapter;
import com.example.codingtaskimran.viewmodel.ProductListViewModel;
import com.example.codingtaskimran.viewmodel.ProductViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    private ProductsAdapter productsAdapter;
    private ProgressDialog progressDialog;
    private String slug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        slug = getIntent().getStringExtra("slug");

        initViews();
    }

    private void initViews() {

        progressDialog = new ProgressDialog(ProductsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        Toast.makeText(this, "slug "+slug, Toast.LENGTH_SHORT).show();

        RecyclerView rvProduct = findViewById(R.id.rvProduct);
        List<Result> productList = new ArrayList<>();
        productsAdapter = new ProductsAdapter(this, productList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvProduct.setLayoutManager(linearLayoutManager);
        rvProduct.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvProduct.setAdapter(productsAdapter);

        ProductListViewModel productListViewModel = ViewModelProviders.of(this,
                new ProductViewModelFactory(this.getApplication(), slug)).get(ProductListViewModel.class);
        observeProductViewModel(productListViewModel);

    }

    private void observeProductViewModel(ProductListViewModel productListViewModel) {
        productListViewModel.getProductListObservable().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product products) {
                if (products != null) {
                    progressDialog.dismiss();
                    List<Result> productList = products.getResults();
                    productsAdapter.setProductList(productList);
                } else {
                    progressDialog.dismiss();
                }
            }
        });
    }
}
