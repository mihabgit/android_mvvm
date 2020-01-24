package com.example.codingtaskimran.service.repository.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.codingtaskimran.service.repository.local.table.CategoryTable;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(CategoryTable categoryTable);

    @Query("SELECT * from category_table")
    LiveData<List<CategoryTable>> getAllCategory();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CategoryTable> categoryTables);

}
