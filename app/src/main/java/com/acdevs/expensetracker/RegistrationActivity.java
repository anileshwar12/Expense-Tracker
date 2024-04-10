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

public class RegistrationActivity extends AppCompatActivity {
    private EditText mEmail;
    private EditText mPassword;

    private Button mRegister;
    private TextView mLogin;
    private ProgressDialog mDialog;

    // Firebase...
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mEmail = findViewById(R.id.email_reg);
        mPassword = findViewById(R.id.password_reg);
        mRegister =findViewById(R.id.button_reg);
        mLogin = findViewById(R.id.textlogin);

        mDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        registration();
    }

    private void registration() {


        mRegister.setOnClickListener(new View.OnClickListener() {
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

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isComplete()) {

                            progressDialog.dismiss();
                            Toast.makeText(RegistrationActivity.this, "Registration Completed", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        }
                        else {
                            Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                            progressDialog.dismiss();

                        }
                    }
                });

            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }
}