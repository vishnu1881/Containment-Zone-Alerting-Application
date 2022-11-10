//Author: Vishnu V
//Project Name: Containment Zone Alerting Application
//MD5 hash for project authentic verification : 763f8185a7ba4ebea28cd69d233fb6c1 (Cannot be decrypted except by the author)

package com.czapcorp.czap;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    private static final String TAG = "Forgot Password";
    private Button backtologin;
    private EditText fpmail;
    private ImageButton resetpwd;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        backtologin = findViewById(R.id.backtologin);
        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtolog = new Intent(ForgotPassword.this, LoginActivity.class);
                startActivity(backtolog);
            }
        });


        fpmail = findViewById(R.id.fpemail);
        resetpwd = findViewById(R.id.resetpassword);
        auth = FirebaseAuth.getInstance();

        resetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddress = fpmail.getText().toString();
                if (TextUtils.isEmpty(emailAddress)) {
                    Toast.makeText(getApplicationContext(),
                                    "Please provide email address",
                                    Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgotPassword.this, "Password reset mail sent !\nCheck Spam folder also ", Toast.LENGTH_LONG).show();
                                    Log.d(TAG, "Email sent.");
                                    Intent backtologg = new Intent(ForgotPassword.this, LoginActivity.class);
                                    startActivity(backtologg);
                                } else {
                                    Toast.makeText(ForgotPassword.this, "Invalid email\n Please enter valid email.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }
}

