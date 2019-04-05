package com.thaont.chatapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    //FirebaseUser declaration.
    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();

        //Get the currently signed-in user
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        // User is signed in.
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


        // Activity time is transferred.
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
