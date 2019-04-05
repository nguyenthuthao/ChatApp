package com.thaont.chatapp;

import android.Manifest;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    // Khai bao FirebaseUser.
    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();

        // Khoi tao fi
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        // Kiem tra xem nguoi dung co trong khong.
        if (firebaseUser != null){
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        // Time chuyển activity.
        CountDownTimer countDownTimer= new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent= new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        }.start();
    }
}
