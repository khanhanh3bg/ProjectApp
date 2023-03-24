package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterScreen extends AppCompatActivity {
EditText ed_fullname, ed_email, ed_pass;
Button btn_register;
ConstraintLayout login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ed_fullname=findViewById(R.id.ed_fullname);
        ed_email=findViewById(R.id.ed_email);
        ed_pass=findViewById(R.id.ed_pass);
        btn_register=(Button) findViewById(R.id.btn_register);
        login=(ConstraintLayout) findViewById(R.id.login);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed_fullname.getText().toString();
                String email = ed_email.getText().toString();
                String pass = ed_pass.getText().toString();
                SharedPreferences s = getSharedPreferences("LoginInformation",MODE_PRIVATE);
                SharedPreferences.Editor edit = s.edit();
                edit.putString("name",name);
                edit.putString("email",email);
                edit.putString("pass",pass);
                edit.commit();
                finish();

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterScreen.this,LoginScreen.class));
            }
        });
    }
}