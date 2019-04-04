package com.thaont.chatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText editEmail;
    private EditText editPassword;
    private Button btnLogin;
    private TextView txtSignUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        //Check form when login.
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                if (password.length()<6 || password.isEmpty()){
                    if (email.isEmpty()){
                        editEmail.setError(getString(R.string.validateEmail));
                    }if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {

                        editEmail.setError(getString(R.string.validateFormEmail));

                    }if (password.isEmpty()){
                        editPassword.setError(getString(R.string.validatePassword1));
                    }if (password.length()<6){
                        editPassword.setError(getString(R.string.validatePassword2));
                    }
                }else {
                    Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.validateFormLogin),Toast.LENGTH_SHORT);
                    toast.show();
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                }
            }
        });
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
    }
    public void initView(){
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        btnLogin = findViewById(R.id.btnLogin);
        txtSignUp = findViewById(R.id.txtSign_up);
    }
}
