package com.example.adcitymart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    private FirebaseAuth Auth;

    TextInputLayout layoutEmail,layoutPassword,layoutCpassword,layoutName;
    EditText user, pasword, Uname, cpassword;
    ProgressDialog progressDialog;
    Pattern pattern;
    TextView textView;
    Button signup;
    FirebaseUser firebaseUser;
    Matcher matcher;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Auth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initialize();
    }

    public void signIn(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Method to signup user
    public void mysignup(View view)
    {
        boolean flag = true;
        email = user.getText().toString().trim();
        String password = pasword.getText().toString().trim();
        String cPassword = cpassword.getText().toString().trim();
        String name = Uname.getText().toString().trim();
        boolean flag1 = validation(email, password, cPassword, name, flag);

        if (flag1) {

            progressDialog.setMessage("Signing Up");
            progressDialog.show();

            Auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                firebaseUser = Auth.getCurrentUser();
                                progressDialog.dismiss();
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(Signup.this, "success", Toast.LENGTH_SHORT).show();
                                verify();


                            } else
                                {
                                    progressDialog.dismiss();
                                // If sign in fails, display a message to the user.
                                Toast.makeText(Signup.this, "Sorry, Not possible this time", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
        }
    }

    //Method o validate the imput data

    private boolean validation(String email, String password, String cPassword, String name, boolean flag) {

        pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(email);

        if (matcher.find() != true) {
            layoutEmail.setError("Please Enter Valid Email");
            flag = false;
        }
        else {
            layoutEmail.setErrorEnabled(false);
        }
        if (password.equals(cPassword) == false) {
            layoutCpassword.setError("Password and Confirm Password Must be Same");
            flag = false;
        } else {
            layoutCpassword.setErrorEnabled(false);
        }
        if (password.length() < 6) {
            layoutPassword.setError("Minimum length for Password is 6");
            flag = false;
        } else {
            layoutPassword.setErrorEnabled(false);
        }
        if (name.isEmpty()) {
            layoutName.setError("Plaese Enter Name");
            flag = false;
        } else {
            layoutName.setErrorEnabled(false);
        }

        return flag;
    }
    public void initialize()
    {
        //ASDFGHJKL
        textView=findViewById(R.id.sign_in);
        user = findViewById(R.id.email);
        pasword = findViewById(R.id.password);
        Uname  =findViewById(R.id.name);
        cpassword = findViewById(R.id.confirmPassword);
        Auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        signup = findViewById(R.id.signup);
        layoutEmail = findViewById(R.id.inputEmail);
        layoutPassword = findViewById(R.id.layoutPassword);
        layoutCpassword = findViewById(R.id.layoutConfirmPassword);
        layoutName = findViewById(R.id.inputName);
    }
public void verify()
{
firebaseUser.sendEmailVerification().addOnCompleteListener(Signup.this, new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task)
    {
        if(task.isSuccessful())
        {
            Toast.makeText(Signup.this, "Verification Email Sent To "+email, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(Signup.this, "Email Sending Failed", Toast.LENGTH_SHORT).show();
        }

    }
});

}
}
