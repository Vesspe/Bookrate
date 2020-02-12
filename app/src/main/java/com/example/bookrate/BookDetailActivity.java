package com.example.bookrate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        String test;
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                test = null;
            }else{
                test = extras.getString("EXTRA_MESSAGE");
            }
        }else
        {
            test = (String)savedInstanceState.getSerializable("EXTRA_MESSAGE");
        }
        Toast.makeText(this, test, Toast.LENGTH_LONG).show();

    }
}
