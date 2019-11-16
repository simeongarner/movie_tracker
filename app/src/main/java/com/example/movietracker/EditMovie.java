package com.example.movietracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class EditMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_movie_view);

        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.NAME_MOVIE);
        TextView PrintName = findViewById(R.id.edit_movie_textView);
        PrintName.setText(name);

    }
}
