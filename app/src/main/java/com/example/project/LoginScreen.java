package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {
Button btn_login;
ConstraintLayout register;
EditText ed_Email, ed_Pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btn_login = (Button) findViewById(R.id.btn_login);
        ed_Email = (EditText) findViewById(R.id.ed_Email);
        ed_Pass = (EditText)  findViewById(R.id.ed_Pass);
        register = (ConstraintLayout) findViewById(R.id.register);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences s = getSharedPreferences("LoginInformation", MODE_PRIVATE);
                String name = s.getString("name","");
                String email = s.getString("email", "");
                String pass = s.getString("pass","");
                User user = new User(name, email, pass);
                if(user==null){
                    Toast.makeText(LoginScreen.this, "Vui lòng nhập thông tin đăng nhập", Toast.LENGTH_LONG).show();
                    return;
                }
                String Email=ed_Email.getText().toString();
                String Pass=ed_Pass.getText().toString();
                if(Email.equals(user.getEmail()) && Pass.equals(user.getPass())){
                    Toast.makeText(LoginScreen.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginScreen.this, HomeScreen.class));
                }else {
                    Toast.makeText(LoginScreen.this, "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginScreen.this,RegisterScreen.class));
            }
        });
    }
}