package com.example.codingtaskimran.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codingtaskimran.R;
import com.example.codingtaskimran.service.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private Context context;
    private List<Category> categoryList;
    private OnCategoryItemClickListener onCategoryItemClickListener;

    public void setOnCategoryItemClickListener(OnCategoryItemClickListener onCategoryItemClickListener) {
        this.onCategoryItemClickListener = onCategoryItemClickListener;
    }

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = categoryList.get(position);

        Picasso.get().load(category.getImageUrl()).placeholder(R.drawable.ic_launcher_background).resize(80,80).into(holder.ivCategoryImage);
        holder.tvCategoryName.setText(category.getName());

        holder.itemView.setOnClickListener(v -> {
            onCategoryItemClickListener.onItemClickListener(category, position);
        });
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCategoryImage;
        TextView tvCategoryName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivCategoryImage = itemView.findViewById(R.id.ivCategoryImage);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
        }
    }

    public interface OnCategoryItemClickListener {
        void onItemClickListener(Category category, int position);
    }
}
