package com.example.project;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.project.DBHelper;
import com.example.project.Product;

import java.util.ArrayList;
import java.util.List;

public class Modify {
    public static void insert(Product product) {
        SQLiteDatabase sqLiteDatabase = com.example.project.DBHelper.getInstance(null).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("url", product.getUrl());
        values.put("quantity", product.getQuantity());
        sqLiteDatabase.insert("Qlproduct", null, values);
        Log.d(String.valueOf(Modify.class), "Tạo thành công");
    }

    public static void update(Product product) {
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("url", product.getUrl());
        values.put("quantity", product.getQuantity());
        sqLiteDatabase.update("Qlproduct", values, "id=" + product.getId(), null);
    }

    public static void delete(int id) {
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();
        sqLiteDatabase.delete("Qlproduct", "id" + id, null);
    }

    public static List<Product> getAllProduct() {
        List<Product> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();
        String sql = "select * from product";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") Product p = new Product(cursor.getInt(cursor.getColumnIndex("id")), cursor.getString(cursor.getColumnIndex("url")), cursor.getString(cursor.getColumnIndex("name")), cursor.getInt(cursor.getColumnIndex("quantity")));
            data.add(p);
        }
        return data;
    }

    public static List<Product> getSearch(String search) {
        List<Product> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();
        String sql = "select * from Qlproduct where name like '0'||:search";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") Product p = new Product(cursor.getInt(cursor.getColumnIndex("id")), cursor.getString(cursor.getColumnIndex("url")), cursor.getString(cursor.getColumnIndex("name")), cursor.getInt(cursor.getColumnIndex("quantity")));
            data.add(p);
        }
        return data;
    }
}

