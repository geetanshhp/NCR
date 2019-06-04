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
import android.view.Window;
import android.view.WindowManager;
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
    EditText name, password;
    TextInputLayout inputname, inputpassword;
    TextView forgetText, Signuptext;
    ProgressDialog progressDialog;
    FirebaseUser firebaseUser;
    FirebaseAuth Auth;
    Boolean islogin ;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        Auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.idusername);
        password = findViewById(R.id.password);
        inputname = findViewById(R.id.firstInput);
        inputpassword = findViewById(R.id.secondinput);
        forgetText = findViewById(R.id.textforget);
        Signuptext = findViewById(R.id.textSignup);
    }


    //SighIN Method
    public void SignInMethod(View view) {
        boolean flag = true;
        if (name.getText().toString().isEmpty()) {
            inputname.setError("Plz Enter UserName");
            flag = false;
        } else {
            inputname.setErrorEnabled(false);
        }
        if (password.getText().toString().isEmpty()) {
            inputpassword.setError("Plz Enter Password");
            flag = false;
        } else {
            inputpassword.setErrorEnabled(false);
        }
        if (flag) {
            inputname.setErrorEnabled(false);
            inputpassword.setErrorEnabled(false);
            final String email = name.getText().toString().trim();
            String pasword = password.getText().toString().trim();
            progressDialog.setMessage("Signing In");
            progressDialog.show();
            Auth.signInWithEmailAndPassword(email, pasword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        boolean s = Auth.getCurrentUser().isEmailVerified();
                        if(s)
                        {
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            Intent intent = new Intent(MainActivity.this, Not_Verified.class);
                            startActivity(intent);
                            finish();
                            islogin = true;
                            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                            sharedPreferences.edit().putBoolean("Islogin", islogin).commit();
                        }
                      /*  else
                        {
                            Intent intent = new Intent(MainActivity.this,Not_Verified.class);
                            startActivity(intent);

                        }*/

                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Not Done", Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }


    }


    public void methodSignUp(View view)
    {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }


    public void forgetpasssordmethod(View view) {
        if (name.getText().toString().equals("")) {
            Toast.makeText(this, "Plz enter username", Toast.LENGTH_SHORT).show();
        } else
            {
                Auth.getInstance().sendPasswordResetEmail(name.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
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