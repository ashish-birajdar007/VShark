package com.syndicate.vshark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private TextInputEditText userNameEdt;
    private Button resetBtn;
    private ProgressBar loadingPB;
    private TextView registerTV;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        userNameEdt = findViewById(R.id.fpidEditUserName);
        loadingPB = findViewById(R.id.idPBLoading);
        resetBtn = findViewById(R.id.fpidBtnLogin);
        registerTV = findViewById(R.id.fpidTVRegister);
        mAuth = FirebaseAuth.getInstance();

        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ForgotPassword.this, RegistrationActivity.class);
                startActivity(i);
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = userNameEdt.getText().toString();
                if(email.isEmpty()){
                    Toast.makeText(ForgotPassword.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(
                            new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(ForgotPassword.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(ForgotPassword.this, LoginActivity.class));
                                        finish();
                                    }else{
                                        Toast.makeText(ForgotPassword.this, "Error " + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                    );
                }
            }
        });

    }
}