package com.jaats.agrovehicle.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jaats.agrovehicle.R;

import java.util.Locale;

public class LanguagePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_page);
    }
   public void HindiLang(View v){

                Locale locale = new Locale("hi");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
               Intent i=new Intent(LanguagePage.this,WelcomeActivity.class);
               startActivity(i);



    }

    public  void EnglishLang(View v){
        Locale locale = new Locale("EN");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        Intent a=new Intent(LanguagePage.this,WelcomeActivity.class);
        startActivity(a);
    }
}
