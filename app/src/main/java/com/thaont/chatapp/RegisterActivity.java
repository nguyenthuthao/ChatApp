package com.thaont.chatapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText editUsername;
    EditText editEmail;
    EditText editPassword;
    Button btnRegister;

    //
    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        //FirebaseAuth initialization.
        auth = FirebaseAuth.getInstance();
        // Check email, password.
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edt_username = editUsername.getText().toString().trim();
                String edt_email = editEmail.getText().toString().trim();
                String edt_password = editPassword.getText().toString();
                if (TextUtils.isEmpty(edt_username)|| TextUtils.isEmpty(edt_email) || TextUtils.isEmpty(edt_password)){
                    Toast.makeText(RegisterActivity.this, getString(R.string.alertRegister2), Toast.LENGTH_SHORT).show();
                }else if (edt_password.length() < 6){
                    Toast.makeText(RegisterActivity.this, getString(R.string.alertRegister3), Toast.LENGTH_SHORT).show();
                }else {
                    creatCount(edt_username,edt_email,edt_password);
                }
            }
        });
    }
    //Create a password-based account.
    private void creatCount(final String username, final String email, final String password) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                FirebaseUser firebaseUser = auth.getCurrentUser();
                    assert firebaseUser != null;
                    String userId = firebaseUser.getUid();

                reference = FirebaseDatabase.getInstance().getReference(getString(R.string.userId)).child(userId);
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id", userId);
                hashMap.put("username", username);
                hashMap.put("imageURL", "default");

                reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }else{
                    Toast.makeText(RegisterActivity.this, getString(R.string.alertRegister1), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void initView(){
        editUsername = findViewById(R.id.edit_username);
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        btnRegister = findViewById(R.id.btnRegister);
    }
}
