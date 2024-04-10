package com.acdevs.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPassActivity extends AppCompatActivity {

    private EditText mEmail;
    private TextView mSignup;
    private Button mSendOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        mEmail = findViewById(R.id.email_otp);

        mSendOtp =findViewById(R.id.button_otp);
        mSignup = findViewById(R.id.signup);

        forgetpassword();

    }

    private void forgetpassword() {


        mSendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    mEmail.setError("Email Required");
                    return;
                }


            }
        });

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPassActivity.this, RegistrationActivity.class);

                startActivity(intent);
            }
        });
    }

}
