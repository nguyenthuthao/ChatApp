package com.thaont.chatapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText editEmail;
    EditText editPassword;
    Button btnLogin;
    TextView txt_Register;

    //FirebaseAuth declaration.
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        //FirebaseAuth initialization.
        auth = FirebaseAuth.getInstance();

        // Sign in a user with an email address and password and check form.
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edt_email = editEmail.getText().toString().trim();
                String edt_password = editPassword.getText().toString().trim();
                if (TextUtils.isEmpty(edt_email) || TextUtils.isEmpty(edt_password)){
                    Toast.makeText(LoginActivity.this, getString(R.string.validateFormLogin1), Toast.LENGTH_SHORT).show();
                }else {
                    auth.signInWithEmailAndPassword(edt_email,edt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(LoginActivity.this, getString(R.string.validateFormLogin2), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        //From activity Login to activity Register.
        txt_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
    public void initView(){
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        btnLogin = findViewById(R.id.btnLogin);
        txt_Register = findViewById(R.id.txt_Register);
    }
}
