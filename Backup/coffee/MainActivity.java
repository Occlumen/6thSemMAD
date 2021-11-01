package com.example.coffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity  {

    EditText txtfullname;
    EditText txtemail;
    EditText txtphone;
    EditText txtpassword1;
    EditText txtpassword2;

    Button btnSignup;
    Button btnlogin1;

    DBHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtfullname=(EditText)findViewById(R.id.sename);
        txtemail=(EditText)findViewById(R.id.seemail);
        txtphone=(EditText)findViewById(R.id.sephone);
        txtpassword1=(EditText)findViewById(R.id.sepassword1);
        txtpassword2=(EditText)findViewById(R.id.sepassword2);

        btnSignup=(Button)findViewById(R.id.btn_sign);
        btnlogin1=(Button)findViewById(R.id.btn_log1);

        myDB = new DBHelper(this);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=txtfullname.getText().toString();
                String email=txtemail.getText().toString();
                String phone=txtphone.getText().toString();
                String pass=txtpassword1.getText().toString();
                String repass=txtpassword2.getText().toString();


                if(username.equals("")|| email.equals("")||phone.equals("")||pass.equals("")|| repass.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Fill all this field", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(repass))
                    {
                        Boolean usercheckResult = myDB.checkusername(email);
                        if(usercheckResult == false)
                        {
                            Boolean regResult = myDB.insertData(username,email, pass);
                            if(regResult==true)
                            {
                                Toast.makeText(MainActivity.this, "Registraion sucessfull", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);

                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "user already exist. \n please login again", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Password doesn't matches", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnlogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
