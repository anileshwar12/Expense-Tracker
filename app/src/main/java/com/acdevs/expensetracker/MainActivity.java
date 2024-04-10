package com.acdevs.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private Button mLogin;
    private TextView mForgetPassword;
    private TextView mSignup;
    private ProgressDialog mDialog;

    // Firebase..
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.email_login);
        mPassword = findViewById(R.id.password_login);
        mLogin = findViewById(R.id.button_login);
        mForgetPassword = findViewById(R.id.forget_password);
        mSignup = findViewById(R.id.signup);
        mDialog = new ProgressDialog(this);

        login();
    }

    private void login() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    mEmail.setError("Email Required");
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    mPassword.setError("Password Required");
                    return;
                }
                ProgressDialogFragment progressDialog = new ProgressDialogFragment();
                progressDialog.show(getSupportFragmentManager(), "ProgressDialog");

                mAuth.signInWithEmailAndPassword( email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isComplete()) {
                            progressDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                            Toast.makeText(MainActivity.this, "Login Succesful", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ForgetPassActivity.class);

                startActivity(intent);

            }
        });

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);

                startActivity(intent);
            }
        });

    }


}
