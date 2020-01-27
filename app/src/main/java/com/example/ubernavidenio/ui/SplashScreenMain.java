package com.example.ubernavidenio.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.ubernavidenio.R;
import com.example.ubernavidenio.ui.login.LoginActivity;
import com.example.ubernavidenio.ui.register.RegisterActivity;

public class SplashScreenMain extends AppCompatActivity {
    Button signIn, signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_main);

        signIn = (Button)findViewById(R.id.id_sign_in);
        signUp = (Button)findViewById(R.id.id_sign_up);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashScreenMain.this, LoginActivity.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashScreenMain.this, RegisterActivity.class));
            }
        });
    }
}