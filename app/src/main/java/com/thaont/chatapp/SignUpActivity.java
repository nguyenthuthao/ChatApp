package com.thaont.chatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    private EditText editEmail;
    private EditText editPassword;
    private EditText editRePassword;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                String rePassword = editRePassword.getText().toString().trim();
                if (password.length()<6 || password.isEmpty() || password.equals(rePassword)==false){
                    if (email.isEmpty()){
                        editEmail.setError(getString(R.string.validateEmail));
                        return;
                    }if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                        editEmail.setError(getString(R.string.validateFormEmail));
                        return;
                    }if (password.isEmpty()){
                        editPassword.setError(getString(R.string.validatePassword1));
                        return;
                    }if (password.length()<6){
                        editPassword.setError(getString(R.string.validatePassword2));
                        return;
                    }if (password.equals(rePassword)==false) {
                        editRePassword.setError(getString(R.string.validateRePassword));
                        return;
                    }
                }else {
                    Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.validateFormSignUp),Toast.LENGTH_SHORT);
                    toast.show();
                    startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                }
            }
        });
    }
    public void initView(){
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        editRePassword = findViewById(R.id.edit_rePassword);
        btnSignUp = findViewById(R.id.btnSignUp);
    }
}
