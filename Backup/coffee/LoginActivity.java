package com.example.coffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class LoginActivity extends AppCompatActivity {
    EditText txtLoginemail;
    EditText txtLoginPassword;

    Button btnLogin;
    Button btnsignup;

    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtLoginemail = findViewById(R.id.leemail);
        txtLoginPassword = findViewById(R.id.lepassword);

        myDB = new DBHelper(this);
        btnLogin = findViewById(R.id.btn_login2);
        btnsignup = findViewById(R.id.btn_sing2);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtLoginemail.getText().toString();
                String pass = txtLoginPassword.getText().toString();


                if (email.equals("") || pass.equals("") ) {
                    Toast.makeText(LoginActivity.this, "Fill all this field", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean result = myDB.checkusernamePassword(email, pass);
                    if (result == true)
                    {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this,CoffeeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Failed ", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });



        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
