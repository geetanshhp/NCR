package com.example.adcitymart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextInputLayout inputNamee, inputPasswordd;
    EditText Uname, password;
    ProgressDialog progressDialog;
    FirebaseAuth Auth;
    TextView signupp,forgetpassword;
    FirebaseUser firebaseUser;
    Boolean islogin = false;
    SharedPreferences sharedPreferences;
    Button button;
    Typeface typeface;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        setContentView(R.layout.activity_main);
    }

    private void initialize() {
        FirebaseApp.initializeApp(this);
        Auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        inputNamee = findViewById(R.id.inputName);
        inputPasswordd = findViewById(R.id.inputPassword);
        Uname = findViewById(R.id.name);
        password = findViewById(R.id.password);
        signupp = findViewById(R.id.sign_up);
        button=findViewById(R.id.sign_in);
        forgetpassword =findViewById(R.id.forget);
        //Uname.setText("");
        //password.setText("");


    }




        // if user has its account

    public void methodsignin(View view)
    {

        boolean flag=true;
        if (Uname.getText().toString().isEmpty()) {
            inputNamee.setError("Please Enter Email");
            flag = false;
        } else {
            inputNamee.setErrorEnabled(false);
        }
        if (password.getText().toString().isEmpty()) {
            inputPasswordd.setError("Please Enter Password");
            flag = false;
        } else {
            inputPasswordd.setErrorEnabled(false);
        }
        if (flag) {
            inputNamee.setErrorEnabled(false);
            inputPasswordd.setErrorEnabled(false);
            final String email = Uname.getText().toString().trim();
            String pasword = password.getText().toString().trim();


            progressDialog.setMessage("Signing In");
            progressDialog.show();
            Auth.signInWithEmailAndPassword(email, pasword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, Home.class);
                        startActivity(intent);
                        finish();
                        islogin = true;
                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                        sharedPreferences.edit().putBoolean("Islogin", islogin).commit();


                    }
                    else
                        {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Not Done", Toast.LENGTH_SHORT).show();
                    }
                }

                });
            }

    }
    public void signup(View view)
    {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
        //Uname.setText("");
        //password.setText("");

    }
    public void forgetpass(View view)
            {
                if(Uname.getText().toString().equals(""))
                {
                    Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        Auth.getInstance().sendPasswordResetEmail(Uname.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                if (task.isComplete()) {
                                    Toast.makeText(MainActivity.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Please Enter Email Above", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
    }
        }