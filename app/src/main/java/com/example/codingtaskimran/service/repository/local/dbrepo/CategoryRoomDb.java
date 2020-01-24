package com.example.codingtaskimran.service.repository.local.dbrepo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.codingtaskimran.service.repository.local.dao.CategoryDao;
import com.example.codingtaskimran.service.repository.local.table.CategoryTable;

@Database(entities = {CategoryTable.class}, version = 1)
public abstract class CategoryRoomDb extends RoomDatabase {

    public abstract CategoryDao categoryDao();

    private static CategoryRoomDb INSTANCE;

    public static synchronized CategoryRoomDb getInstance(final Context context) {
        if (INSTANCE == null) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        CategoryRoomDb.class, "category")
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return INSTANCE;
    }

}

