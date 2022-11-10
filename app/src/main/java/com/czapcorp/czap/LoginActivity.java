//Author: Vishnu V
//Project Name: Containment Zone Alerting Application
//MD5 hash : 763f8185a7ba4ebea28cd69d233fb6c1 (Cannot be decrypted except by the author)

package com.czapcorp.czap;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText emailTextView, passwordTextView;
    private ImageButton Btn;
    private ProgressBar progressbar;
    private Button regis;
    private Button forgot;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // CZAP FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();
        regis = findViewById(R.id.btn_go_to_register);

        // initialising all views and buttons for CZAP app through id defined above
        emailTextView = findViewById(R.id.logemail);
        passwordTextView = findViewById(R.id.et_password);
        Btn = findViewById(R.id.btn_login);
        progressbar = findViewById(R.id.progressbar);
        forgot = findViewById(R.id.btn_forgot_password);

        //Go to register page
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regact = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(regact);
            }
        });

        //Go to Forgot Password page
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotpage = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(forgotpage);
            }
        });

        // Set on Click Listener on Sign-in button
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });
    }

    private void loginUserAccount() {
        progressbar.setVisibility(View.VISIBLE);
        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email!!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!!", Toast.LENGTH_LONG).show();
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                                    "Login successful!!",
                                                    Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar
                                    progressbar.setVisibility(View.GONE);

                                    // if sign-in is successful
                                    // intent to home activity
                                    Intent intent = new Intent(LoginActivity.this, homepage.class);
                                    startActivity(intent);
                                } else {

                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(), "Login failed!!", Toast.LENGTH_LONG).show();

                                    // hide the progress bar
                                    progressbar.setVisibility(View.GONE);
                                }
                            }
                        });
    }
}

