package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FoodScreen extends AppCompatActivity {
    Button btn_create;
    ListView listView;
    ProductAdapter adapter;
    List<Product> data= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_screen);
        DBHelper.getInstance(FoodScreen.this);
        data=Modify.getAllProduct();
        listView=(ListView)findViewById(R.id.list_view);
        adapter= new ProductAdapter(data,this);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        btn_create=(Button) findViewById(R.id.btn_create);
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themsanpham();
            }
        });
        adapter.notifyDataSetChanged();

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menueditdelete,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public void themsanpham() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        View v = LayoutInflater.from(this).inflate(R.layout.hopthoaithem, null);
        EditText namep=v.findViewById(R.id.inputname);
        EditText urlp=v.findViewById(R.id.inputurl);
        EditText quantilyp=v.findViewById(R.id.inputquantity);
        b.setView(v);
        b.setPositiveButton("save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String ten = namep.getText().toString();
                String anh = urlp.getText().toString();
                int soluong = Integer.parseInt(quantilyp.getText().toString());
                Product p = new Product(ten, anh, soluong);
                Modify.insert(p);
                data.add(p);
                adapter.notifyDataSetChanged();
                //data.add(ten);
                //adapter.notifyDataSetChanged();

            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }

    public void timkiem(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        EditText nameproduct = findViewById(R.id.in_search);
        b.setPositiveButton("Find", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tensp=nameproduct.getText().toString();
                boolean timkiem=false;
                data=Modify.getSearch(tensp);
                adapter= new ProductAdapter(data,FoodScreen.this);
                listView.setAdapter(adapter);
                registerForContextMenu(listView);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo infor = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = infor.position;
        switch (item.getItemId()){
            case R.id.delete:
                data.remove(index);
                adapter.notifyDataSetChanged();
                break;
            case R.id.edit:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                View v = LayoutInflater.from(this).inflate(R.layout.hopthoaithem, null);
                EditText namep = v.findViewById(R.id.inputname);
                EditText urlp= v.findViewById(R.id.inputurl);
                EditText quantityp = v.findViewById(R.id.inputquantity);
                b.setView(v);
                b.setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String ten=namep.getText().toString();
                        String anh=urlp.getText().toString();
                        int soluong= Integer.parseInt(quantityp.getText().toString());
                        Product p= new Product(ten, anh, soluong);
                        Modify.update(p);
                        data.set(index,p);
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
        }
        return super.onOptionsItemSelected(item);
    }

}