package com.example.carpool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class register extends AppCompatActivity {
    EditText editTextemail,editTextpassword,editTextname,editTextrepasswe;
    Button signup_buttom;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        mAuth= FirebaseAuth.getInstance();
        editTextname= findViewById(R.id.editTextText03);
        editTextemail= findViewById(R.id.editText04);
        editTextpassword= findViewById(R.id.editText05);
        editTextrepasswe= findViewById(R.id.editText06);
        signup_buttom=findViewById(R.id.button05);
        signup_buttom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password, name, retypepass;
                email=editTextemail.getText().toString();
                password=editTextpassword.getText().toString();
                name=editTextname.getText().toString();
                retypepass=editTextrepasswe.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(register.this, "Enter Email",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(register.this, "Enter Password",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(register.this, "Enter name",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(retypepass)){
                    Toast.makeText(register.this, "Please retype password",Toast.LENGTH_LONG).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                    @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(register.this, "Account created",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),choicelayout.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });


    }
}