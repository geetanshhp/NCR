package com.example.adcitymart;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    SharedPreferences preferences;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        flag = preferences.getBoolean("Islogin",false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(!flag)
        {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }


//ALERT DILAOG BOX

    @Override
    public void onBackPressed()
    {
        final AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("CONFIRM EXIT");
        alert.setTitle("ARE YOU SURE TO EXIT");
        alert.setIcon(R.drawable.close);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                    finish();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }

        });
            alert.show();

    }

    public void LogoutMethod(View view)
    {
        flag = false;
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putBoolean("Islogin",flag).commit();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        else
        {
            return super.onOptionsItemSelected(item);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.idhome:
                    Toast.makeText(Home.this, "This is home", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.idcontact:
                    Toast.makeText(Home.this, "This is contact page", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.aboutus:
                    Toast.makeText(Home.this, "This is About us", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    };

}



