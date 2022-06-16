package com.syndicate.vshark;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private TextInputEditText userNameEdt, emailEdt, pwdEdt;
    private Button registerBtn;
    private ProgressBar loadingPB;
    private FirebaseAuth mAuth;
    private TextView loginTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        userNameEdt = findViewById(R.id.idEditUserName);
        emailEdt = findViewById(R.id.idEdtemail);
        pwdEdt = findViewById(R.id.idedtpwd);
        registerBtn = findViewById(R.id.idBtnRegister);
        loadingPB = findViewById(R.id.idPBLoading);
        loginTV = findViewById(R.id.idTVLogin);
        mAuth = FirebaseAuth.getInstance();

        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);

                String userName = userNameEdt.getText().toString();
                String email = emailEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();

                if(TextUtils.isEmpty(userName)){
                    loadingPB.setVisibility(View.GONE);
                    Toast.makeText(RegistrationActivity.this, "Please Enter User Name", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(email)){
                    loadingPB.setVisibility(View.GONE);
                    Toast.makeText(RegistrationActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(pwd)){
                    loadingPB.setVisibility(View.GONE);
                    Toast.makeText(RegistrationActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                } else{
                    mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegistrationActivity.this, "Registration Succesfull!!!", Toast.LENGTH_SHORT).show();
                                loadingPB.setVisibility(View.GONE);
                                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(i);
                            }else{
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}